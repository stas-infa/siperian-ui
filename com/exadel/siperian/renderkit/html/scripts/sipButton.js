SipButton = {};

SipButton.disable = function (el) {
	var el = $(el);
	var container = $(el.id + 'container');
	var link = $(el.id + 'butLink'); 
	if (container && link) {
		container.className = 'disabled';
		link.setAttribute('sipdisabled', 'true');
	}
};


SipButton.enable = function (el) {
	var el = $(el);
	var container = $(el.id + 'container');
	var link = $(el.id + 'butLink'); 
	if (container && link) {
		container.className = 'sipButCommon';
		link.setAttribute('sipdisabled', 'false');
	}
}

isIE7 = function(){
	return /MSIE (7|6).+Win/.test(navigator.userAgent);//Prototype.Browser.IE;
};