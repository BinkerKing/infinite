function Tmpl(tmplId,data){
	this.tmplId = tmplId;
	this.data = data || {};
	this.setData = function(data){
		this.data = data;
	};
	this.reset = function(divId,data){
		var d = data || this.data;
		var html = template(this.tmplId, d);
		document.getElementById(divId).innerHTML = html;
	};
}