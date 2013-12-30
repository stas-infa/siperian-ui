SipButton = {};

SipToggleButton = {};

SipButton.disable = function (el) {
	var el = $(el);
	var container = $(el.id + 'container');
	var link = $(el.id + 'butLink'); 
	if (container && link) {
		container.className = container.className.replace('sipButCommon', 'disabled');
		link.setAttribute('sipdisabled', 'true');
	}
};


SipButton.enable = function (el) {
	var el = $(el);
	var container = $(el.id + 'container');
	var link = $(el.id + 'butLink'); 
	if (container && link) {
		container.className = container.className.replace('disabled', 'sipButCommon');
		link.setAttribute('sipdisabled', 'false');
	}
};


SipToggleButton.toggle = function (elId) {
    var el = $(elId);
	var container = $(el.id + 'container');
	if (container){
	   container.className = container.className=='pressed' ? 'unpressed' : 'pressed';
	}

};

isIE7 = function(){
	return /MSIE (7|6).+Win/.test(navigator.userAgent);//Prototype.Browser.IE;
};