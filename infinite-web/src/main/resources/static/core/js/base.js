var Ajax = function () {
	function defSuccess() {
	}
	function defFailure(code) {
		Tool.log("requset failure: status" + code);
	}
	function request(req_url, opt) {
		var async = opt.async !== false, method = opt.method || 'POST', data = opt.data
			|| null, success = opt.success || defSuccess, failure = opt.failure
				|| defFailure, headers = opt.headers || {}, method = method
					.toUpperCase();
		var url = req_url;
		var timeout = opt.timeout || 30000;
		var type = opt.type || "json";
		var cache = opt.cache || false;
		if(!!opt.cache){
			Tool.log(cache);
		}
		if (method == 'GET') {
			if (!!data) {
				var connect = (url.indexOf('?') == -1 ? '?' : '&');
				url = "{0}{1}{2}".format(url, connect, Tool.jsonToParam(data));
				data = null;
			}
			if (!cache) {
				var connect = (url.indexOf('?') == -1 ? '?' : '&');
				url = "{0}{1}_t={2}".format(url, connect, new Date() * 1);
			}
		}
		var xhr = new XMLHttpRequest();
		var cors = Tool.isCors(url);
		if ("withCredentials" in xhr || !cors) {
			xhr.open(method, url, async);
			for (var key in headers) {
				xhr.setRequestHeader(key, headers[key]);
			}
			if (!!data) {
				data = Tool.stringify(data);
			}
			xhr.onreadystatechange = function () {
				if (xhr.readyState == 4) {
					var s = xhr.status;
					if (s == 200 || s == 304) {
						var ret = type == "json" ? Tool.parseJSON(xhr.responseText||"{}") : xhr.responseText;
						success(ret, xhr);
					} else {
						failure(xhr.status);
						Tool.log("onerror :" + xhr.status);
					}
				}
			}
		} else if (typeof XDomainRequest != "undefined") {
			xhr = new XDomainRequest();
			var contentType = (headers["Content-Type"] || "").toLowerCase();
			var Auth = headers["Auth"];
			if(!!Auth){
				var connect = (url.indexOf('?') == -1 ? '?' : '&');
				url = "{0}{1}Auth={2}".format(url, connect, Auth);
			}
			if (method == 'POST' && "application/json" == contentType) {
				var str = Tool.stringify(data);
				data = "T_=x-json&D_={0}".format(B64.encode(str));
			}
			xhr.open(method, url);
			
			xhr.onload = function (e) {
				var ret = type == "json" ? Tool.parseJSON(xhr.responseText||"{}") : xhr.responseText;
				success(ret);
			}
			xhr.onerror = function () {
				Tool.log("onerror :" + xhr.status);
				failure(xhr.status);
			}
			xhr.onprogress = function () {
			}
			xhr.ontimeout = function () {
				Tool.log("request timeout :{0}".format(url));
			}
			
		} else {
			Tool.log("The browser does not support");
			return
		}
		xhr.timeout = timeout;

		setTimeout(function () {
			xhr.send(data);
		}, 0);

		setTimeout(function () {
			!!xhr.abort && xhr.abort();
			Tool.log("request timeout :{0}".format(url));
		}, timeout + 500);
		return xhr;
	}

	function sendFile(url, opt) {
		var success = opt.success || defSuccess;
		var failure = opt.failure || defFailure;
		if (!window.FormData) {
			Tool.error("浏览器不支持");
			failure(301, "浏览器不支持");
			return;
		}
		var oMyForm = new FormData();
		if (opt.data) {
			for (var key in opt.data) {
				oMyForm.append(key, opt.data[key]);
			}
		}
		var source = document.getElementById(opt.fileId);
		var file = source.files[0];
		oMyForm.append("file", file);

		var xhr = new XMLHttpRequest();
		xhr.open("POST", url);

		if (opt.header) {
			for (var key in opt.header) {
				xhr.setRequestHeader(key, opt.header[key]);
			}
		}
		xhr.send(oMyForm);
		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4) {
				var s = xhr.status;
				if (s == 200 || s == 304) {
					success(Tool.parseJSON(xhr.responseText));
				} else {
					failure(xhr.status);
				}
			}
		}
	}

	function sendMultiFile(url, opt) {
		var success = opt.success || defSuccess;
		var failure = opt.failure || defFailure;
		if (!window.FormData) {
			Tool.error("浏览器不支持");
			failure(301, "浏览器不支持");
			return;
		}
		var oMyForm = new FormData();
		if (opt.data) {
			for (var key in opt.data) {
				oMyForm.append(key, opt.data[key]);
			}
		}
		/*
		 * var source = document.getElementById(opt.fileId); var file =
		 * source.files[0]; oMyForm.append("file", file);
		 */

		if (opt.files) {
			for (var key in opt.files) {
				oMyForm.append(key, opt.files[key]);
			}
		}

		var xhr = new XMLHttpRequest();
		xhr.open("POST", url);

		if (opt.header) {
			for (var key in opt.header) {
				xhr.setRequestHeader(key, opt.header[key]);
			}
		}
		xhr.send(oMyForm);
		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4) {
				var s = xhr.status;
				if (s == 200 || s == 304) {
					success(Tool.parseJSON(xhr.responseText));
				} else {
					failure(xhr.status);
				}
			}
		}
	}

	return {
		"request": request,
		"sendFile": sendFile,
		"sendMultiFile": sendMultiFile
	};
}();

var Tool = {
	isCors: function (url) {
		var arr1 = /^https?:\/\/([^\\/]+)\/.*$/.exec(url);
		var arr2 = /^https?:\/\/([^\\/]+)\/.*$/.exec(location.href);
		if (!!arr1 && !!arr2) {
			return arr1[1] !== arr2[1];
		}
		return false;
	},
	compress: function (img, extent, quality, output_format) {
		var mime_type = "image/jpeg";
		if (output_format != undefined && output_format == "png") {
			mime_type = "image/png";
		}
		var cvs = document.createElement('canvas');
		var zoom = 1;
		if (img.naturalWidth > img.naturalHeight) {
			zoom = extent / img.naturalWidth;
		} else {
			zoom = extent / img.naturalHeight;
		}
		cvs.width = img.naturalWidth * zoom;
		cvs.height = img.naturalHeight * zoom;
		var ctx = cvs.getContext("2d");
		ctx.drawImage(img, 0, 0, cvs.width, cvs.height);
		var newImageData = cvs.toDataURL(mime_type, quality || 0.5);
		return newImageData;
	},
	each: function (obj, fn, context) {
		if (obj.length === void +0) {
			for (var key in obj)
				if (obj.hasOwnProperty(key))
					fn.call(context, obj[key], key, obj);
			return obj
		}
		for (var i = 0, l = obj.length; i < l; i++)
			fn.call(context, obj[i], i, obj);
		return obj;
	},
	parseJSON: function (json) {
		if (typeof json != 'string')
			return json = json.replace(/^\s+|\s+$/g, '');
		var isValid = /^[\],:{}\s]*$/
			.test(json
				.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@")
				.replace(
				/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
				"]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""));
		if (!isValid) {
			this.error("Invalid JSON -- " + new Date() + "--- " + json);
			return null;
		}
		var JSON = window.JSON;
		return JSON && JSON.parse ? JSON.parse(json) : (new Function("return "
			+ json))();
	},
	isArray: function (obj) {
		return this.type(obj) == "array";
	},
	isFun: function (obj) {
		return this.type(obj) == "function";
	},
	isStr: function (obj) {
		return this.type(obj) == "string";
	},
	type: (function () {
		var class2type = {}, // 用于记录[object class]样式
			objs = "Boolean Number String Function Array Date RegExp Null Undefined"
				.split(" ");
		for (var i = 0, l = objs.length; i < l; i++) {
			class2type["[object " + objs[i] + "]"] = objs[i].toLowerCase();
		}

		return function type(obj) {
			return class2type[Object.prototype.toString.call(obj)] || "object";
		}
	})(),
	isObj: function (obj) {
		return this.type(obj) == "object";
	},
	jsonToParam: function (obj) {
		if (Tool.isStr(obj)) {
			return encodeURI(obj);
		}
		var param = "";
		for (var key in obj) {
			param += "&" + key + "=" + encodeURI(obj[key]);
		}
		if (param.length) {
			param = param.substr(1);
		}
		return param;
	},
	stringify: function (obj) {
		var JSON = window.JSON;
		if (JSON) {
			return JSON.stringify(obj);
		}
		return Tool.jsonToParam(obj);
	},
	log: function (msg) {
		this.info(msg);
	},
	info: function (msg) {
		if (window.console && window.console.info && 1 == Tool.getQStr("show_log")) {
			window.console.info(msg);
		}
	},
	error: function (msg) {
		if (window.console && window.console.error) {
			window.console.error(msg);
		}
	},
	toHttp: function (httpsUrl) {
		if (!!httpsUrl) {
			return httpsUrl.replace("https", "http");
		}
	},
	getQStr: function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	},
	setCookie: function (name, value, time, isHttps) {
		var expires = "";
		if(time>0){
			var _d = new Date(new Date()*1+time);
			expires = ";expires={0}".format(_d.toGMTString());
		}
		var secure = "";
		if(isHttps){
			secure = ";secure";
		}
		var _cookie = "{0}={1}{2}{3}".format(name,escape(value),expires,secure);
		document.cookie = _cookie;
	},
	delCookie: function (name, path, isHttps) {
		var _d = new Date(new Date()*1-1000);
		var secure = "";
		if(isHttps){
			secure = ";secure";
		}
		var _cookie = "{0}=;expires={1};path={2}".format(name,_d.toGMTString(),path,secure);
		document.cookie = _cookie;
	},
	/**
	 * 读cookies
	 */
	getCookie: function (name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	},
	toLogin:function(loginUrl){
		if(!loginUrl){
			return;
		}
		var forwordUrl = "{0}?retUrl={1}".format(loginUrl,top.location.href);
		top.window.location.href = forwordUrl;
	},
	resizeIMG: function (img, width, quality, maxExtent) {
		var that = img;

		// 生成比例
		var w = that.width, h = that.height, scale = w / h;
		w = width || w;
		h = w / scale;

		// 生成canvas
		var canvas = document.createElement('canvas');
		var ctx = canvas.getContext('2d');
		$(canvas).attr({
			width: w,
			height: h
		});
		ctx.drawImage(that, 0, 0, w, h);

		/**
		 * 生成base64 兼容修复移动设备需要引入mobileBUGFix.js
		 */
		var base64 = canvas.toDataURL('image/jpeg', quality || 0.8);

		var mpImg
		// 修复IOS
		if (navigator.userAgent.match(/iphone/i)) {
			mpImg = new MegaPixImage(img);
			mpImg.render(canvas, {
				maxWidth: w,
				maxHeight: h,
				quality: quality || 0.8
			});
			base64 = canvas.toDataURL('image/jpeg', quality || 0.8);
		}
		var encoder;
		// 修复android
		if (navigator.userAgent.match(/Android/i)) {
			encoder = new JPEGEncoder();
			base64 = encoder.encode(ctx.getImageData(0, 0, w, h),
				quality * 100 || 80);
		}

		while (maxExtent && maxExtent > 0 && base64.length > maxExtent) {
			quality = quality - 0.2;

			base64 = canvas.toDataURL('image/jpeg', quality || 0.8);

			// 修复IOS
			if (navigator.userAgent.match(/iphone/i)) {
				mpImg = new MegaPixImage(img);
				mpImg.render(canvas, {
					maxWidth: w,
					maxHeight: h,
					quality: quality || 0.8
				});
				base64 = canvas.toDataURL('image/jpeg', quality || 0.8);
			}

			// 修复android
			if (navigator.userAgent.match(/Android/i)) {
				encoder = new JPEGEncoder();
				base64 = encoder.encode(ctx.getImageData(0, 0, w, h),
					quality * 100 || 80);
			}
		}

		// 生成结果
		var result = {
			base64: base64,
			clearBase64: base64.substr(base64.indexOf(',') + 1)
		};

		return result;
	}

};

Number.prototype.formatMoney = function (places, thousand, decimal) {
	places = !isNaN(places = Math.abs(places)) ? places : 2;
	thousand = thousand || ",";
	decimal = decimal || ".";
	var number = this,
		negative = number < 0 ? "-" : "",
		i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
		j = (j = i.length) > 3 ? j % 3 : 0;
	return negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};

Date.prototype.format = function (fmt) {
	var o = {
		"M+": this.getMonth() + 1,                 // 月份
		"d+": this.getDate(),                    // 日
		"h+": this.getHours(),                   // 小时
		"m+": this.getMinutes(),                 // 分
		"s+": this.getSeconds(),                 // 秒
		"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
		"S": this.getMilliseconds()             // 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
// 两种调用方式
// var template1="我是{0}，今年{1}了";
// var template2="我是{name}，今年{age}了";
// var result1=template1.format("loogn",22);
// var result2=template2.format({name:"loogn",age:22});
String.prototype.format = function (args) {
	var result = this;
	if (arguments.length > 0) {
		if (arguments.length == 1 && typeof (args) == "object") {
			for (var key in args) {
				if (args[key] != undefined) {
					var reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			}
		}
		else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] != undefined) {
					var reg = new RegExp("({)" + i + "(})", "g");
					result = result.replace(reg, arguments[i]);
				}
			}
		}
	}
	return result;
}

function isInArray(arr,value) {
	for (var i = 0; i < arr.length; i++) {
		if (value === arr[i].name) {
			return true;
		}
	}
	return false;
}

function changeBack(str){
	str = str.replace(new RegExp("&#60;",'g'),"<");
	str = str.replace(new RegExp("&#62;",'g'),">");
	return str;
}

/*日期格式化*/
function getMyDate(str) {
	var oDate = new Date(str),
			oYear = oDate.getFullYear(),
			oMonth = oDate.getMonth()+1,
			oDay = oDate.getDate(),
			oHour = oDate.getHours(),
			oMin = oDate.getMinutes(),
			oSen = oDate.getSeconds(),
			oTime = oYear +'-'+ addZero(oMonth) +'-'+ addZero(oDay);
	return oTime;
}

function addZero(num){
	if(parseInt(num) < 10){
		num = '0'+num;
	}
	return num;
}

function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null){
        return true;
    }else if(obj.match(/^\s*$/)){  //空|空格|换行
        return true;
    }else{
    	return false;
	}
}