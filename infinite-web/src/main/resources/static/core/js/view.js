(function(win) {
	/**
	 * View
	 * 属性：API_URL | TOKEN | CTX | SYS | 
	 * 方法：
	 */
	var View = {};  

	View.info = info;
	View.warn = warn;
	View.error = error;
	View.init = function(opts) {
		if (!opts.API_URL) {
			warn("API_URL is null");
			return;
		}
		View.API_URL = opts.API_URL;
		View.TOKEN = opts.TOKEN;
		View.CTX = opts.CTX;
		View.SYS = opts.SYS;
		View.SSO_API_URL = opts.SSO_API_URL;

		Page.isTopWin = top.window === window;

	};
	
	View.setApiUrl = function(url) {
		View.API_URL = url;
	}
	
	/**
	 * view加载完成回调
	 */
	View.onLoad = function(call) {
		View._onLoadCallback = call;
	}

	View.setEvent = function(event, callback) {
		if (!View._callbacks) {
			View._callbacks = {};
		}
		if (!View._callbacks[event]) {
			View._callbacks[event] = $.Callbacks();
		}
		View._callbacks[event].add(callback);
	}

	View.fireEvent = function(event, arg) {
		if (!View._callbacks[event]) {
			return;
		}
		View._callbacks[event].fire(arg);
	}

	View.resetUi = resetUi;

	View.getTmpl = function(id) {
		return tmplsMap[id];
	}
	
	View.open = function(url, title, modId, tabIndex) {
		if (!url) {
			warn("url is null");
			return;
		}
	}

	View.openDialog = function(url, title, opts) {
		opts = opts || {
			area : [ '100%', '100%' ]
		};
		opts.type = opts.type || 2;
		opts.title = title;
		opts.shadeClose = opts.shadeClose || true;
		opts.shade = opts.shade || false;
		opts.maxmin = opts.maxmin || false;
		opts.offset = opts.offset || undefined;
		opts.scrollbar = opts.scrollbar || false;
		var connect = (url.indexOf('?') == -1 ? '?' : '&');
		url = "{0}{1}_modId={2}".format(url, connect, Page.modId);
		opts.content = url;
		layer.open(opts);
	}
	
	/**
	 *  获取url中的参数
	 */
	View.getQStr = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	/**
	 * 获取权限资源树对象
	 */
	View.getRes = function() {
		if(!!this._res){
			return this._res;
		}
		try{
			if (top.window.View && top.window.View._res) {
				return top.window.View._res;
			}
		}catch(e){}
		return null;
	}
	/**
	 * 根据路径得到完整的api地址
	 */
	View.getFullApiUrl = function(path) {
		if (!/^https?:/.test(path)) {
			var base = View.API_URL;
			path = base + path;
		}
		var arr = /^(https?:\/\/[^\\/]+)(\/.*)$/.exec(path);
		if (!!arr) {
			path = "{0}{1}".format(arr[1], arr[2].replace(/\/\//g, "/"));
		}
		return path;
	}
	
	View.ajaxReq = ajaxReq;

	/**
	 * ajax-get请求
	 */
	View.get = function(url, param, success, fail, timeout, locale) {
		ajaxReq(url, {
			method : "GET",
			data : param,
			success : success,
			error : fail,
			timeout : timeout,
			locale : locale
		});
	}

	/**
	 * ajax-post请求
	 */
	View.post = function(url, data, success, fail, timeout, locale) {
		ajaxReq(url, {
			method : "POST",
			data : data,
			success : success,
			error : fail,
			timeout : timeout,
			locale : locale
		});
	}
	
	if (!win["View"]) {
		win["View"] = View;
	}

	/*方法集合*/
	function info(msg) {
		if (window.console) {
			window.console.info(msg);
		}
	}
	function warn(msg) {
		if (window.console) {
			window.console.warn(msg);
		}
	}
	function error(msg) {
		if (window.console) {
			window.console.error(msg);
		}
	}
	function kv2json(param) {
		var _p = {};
		var re = new RegExp(/([^?&]+)=([^?&]+)/g);
		var matcher = null;
		while (matcher = re.exec(param)) {
			var val = matcher[2];
			_p[matcher[1]] = val;
		}
		return _p;
	}
	function kv2obj(param) {
		var _p = {};
		var re = new RegExp(/([^?&]+)=([^?&]+)/g);
		var matcher = null;
		while (matcher = re.exec(param)) {
			var val = matcher[2];
			var propStr = matcher[1];
			if (propStr.indexOf(".") > 0) {
				var props = propStr.split(".");
				var funStr = "";
				for (var j = 0; j < props.length; j++) {
					if (j == props.length - 1) {
						funStr += "var " + props[j] + "='" + val + "';"
								+ props[j - 1] + "." + props[j] + "="
								+ props[j] + "; ";
					} else {
						if (j == 0) {
							funStr += "var " + props[j] + "={}; "
						} else {
							funStr += "var " + props[j] + "={};" + props[j - 1]
									+ "." + props[j] + "=" + props[j] + "; ";
						}
					}
				}
				funStr += "return " + props[0] + ";";
				var fun = new Function(funStr);
				_p[props[0]] = fun();
			} else {
				_p[matcher[1]] = val;
			}
		}
		return _p;
	}
	function addUrlParam(url, param) {
		if (url.indexOf("?") === -1) {
			return url + "?" + param;
		} else {
			return url + "&" + param;
		}
	}
	/*
	 * ajaxReq请求封装
	 * */
	function ajaxReq(url, opts) {
		url = View.getFullApiUrl(url);
		opts.locale = opts.locale === undefined ? true : opts.locale;
		var reqOpts = {
			method : opts.method,
			data : opts.data,
			type : opts.type,
			cache : opts.cache,
			timeout : opts.timeout,
			headers : {
				"Content-Type" : "application/json",
				"Auth" : View.TOKEN
			},
			success : function(resp) {
				if (Tool.isStr(resp) || (!resp._data && !resp.code)) {
					opts.success.call(this, resp);
					return;
				}
				try {
					var _data = resp._data;
					if (opts.locale) {
						_data = Locale.format(_data);
					}
					_data = encodeHtml(_data);
				} catch (e) {
				}
				resp.data = JSON.parse(_data || "{}");
				delete resp._data;
				if (resp.code == 200) {
					opts.success.call(this, resp.data);
				} else {
					if (401 == resp.code) {
						Tool.delCookie("_TOKEN", "/", _isHttps);
						Tool.delCookie("_TOKEN", Const.webPath + "/", _isHttps);
						if (Const.security
								&& (url.indexOf("/accessToken") === -1 && url
										.indexOf("/bindUserValidate") === -1)) {
							Tool.toLogin(Const.ssoLoginUrl);
						}
					}
					if (!!opts.error) {
						opts.error.call(this, resp.code, resp);
					} else {
						View.warn("invoke-" + url + "-- code:" + resp.code);
					}
				}
			},
			error : function(code) {
				if (!!opts.error) {
					opts.error.call(this, code);
				}
				View.warn("invoke-" + url + "-- code:" + code);
			}
		};
		Ajax.request(url, reqOpts);
	}
	
	function sort(list, orderBy, type) {
		if ("desc" == type) {
			return list.sort(function(o1, o2) {
				var v1 = o1[orderBy] || 100;
				var v2 = o2[orderBy] || 100;
				return v1 < v2 ? 1 : v1 == v2 ? 0 : -1;
			});
		} else {
			return list.sort(function(o1, o2) {
				var v1 = o1[orderBy] || 100;
				var v2 = o2[orderBy] || 100;
				return v1 > v2 ? 1 : v1 == v2 ? 0 : -1;
			});
		}
	}
	
	function resetUi(funName) {
		switch (key) {
		case "reload":
			window.location.reload();
			break;
		default:
			window.location.reload();
			break;
		}
	}
	
	function encodeHtml(s) {
		var REGX_HTML_ENCODE = /<|>/g;
		return (typeof s != "string") ? s :  s.replace(REGX_HTML_ENCODE,  
			function($0){
			    var c = $0.charCodeAt(0), r = ["&#"];  
				c = (c == 0x20) ? 0xA0 : c;  
				r.push(c); r.push(";");  
				return r.join("");  
			});  
	};
})(window);
