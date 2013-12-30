PNGFIX= {};
PNGFIX.getContextPath=function(){
	//
    var href = window.location.href;
//    alert(href);
    if(href!=void(0)){
   		 var array = href.split("/");
    
   		 if(array.length >= 3){
//   		 	alert(array[3]);
	 		return "/"+array[3];
   		 }
    }
//    alert("null");
	return "";
}


//TODO nick - will work for this context only 
//ES: fixed.
PNGFIX.CLEAR=PNGFIX.getContextPath()+"/images/clear.gif";
PNGFIX.POSTFIX = '.jsf';
PNGFIX.STACK = new Array();
PNGFIX.RE_PNG=/\.png/i;
PNGFIX.RE_PNGJSF=/\.png\.jsf/i;;

PNGFIX.put = function (e, processChild) {
	//TODO nick - IE check?
	//ES:done.
	if(!isIE()){
		return;
	}
	var i = {};
	i.processChild = processChild;
	i.element = e;
	PNGFIX.STACK.push(i);
	
};

PNGFIX.pngfix=function()
{
	if(!isIE()){
		return;
	}
	PNGFIX.initPostFix();
	var l = PNGFIX.STACK.length;
	for (var i=0; i < l; i++) {
		var element = PNGFIX.STACK[i].element;
		var processChild = PNGFIX.STACK[i].processChild;
		PNGFIX.pngfixElement(element, processChild)
	}
};

PNGFIX.pngfixElement=function(element, processChild) {
	//TODO nick - IE check?
	//ES:done.
	if(!isIE()){
		return;
	}
	//TODO nick - rewrite code to remove "if else if"
	if (processChild && element) {
			PNGFIX._pngfix(element);
			//TODO nick - ineffecient, use element.all or element.firstChild/nextSibling iteration
			var children = element.getElementsByTagName('*');
			for (var j = 0; j < children.length; j++) {
				PNGFIX._pngfix(children[j]);
			}
		}else if(element){
			PNGFIX._pngfix(element);
	}
},

PNGFIX.pngfixElements=function() {
	if (!isIE()) {
		return;
	}
	for (var i=0; i<arguments.length; i++) {
		PNGFIX.pngfixElement(arguments[i], true);
	}
},

PNGFIX.initPostFix = function () {
	//TODO nick - people call it suffix
	if (!PNGFIX.POSTFIX) {
		var postFix = PNGFIX.CLEAR.substring(PNGFIX.CLEAR.lastIndexOf('.') + 1);
		postFix = (postFix == 'jsf') ? 'jsf' : '';
		PNGFIX.POSTFIX = postFix;
	}
	return PNGFIX.POSTFIX;
};


PNGFIX.pngfixImg = function (img) {
	if (!isIE()) {
		return;
	}
	var es=img.style;
	
	//TODO nick - code formatting is awful
	if(img.src&&img.src.match(PNGFIX.RE_PNG)&&!es.filter)
		{
			//TODO nick - 0 is also false 
			if (img.height && img.height != 0) {
				es.height = img.height + 'px';
			}else if (img.clientHeight){
				es.height = img.clientHeight + 'px';
			}
			if (img.width && img.width != 0) {
				es.width = img.width;
			} else if (img.clientHeight) {
				es.width = img.clientWidth + 'px';
			}
			es.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+img.src+"',sizingMethod='crop')";
			//img.parentNode.style.zIndex = -1;
			img.src=PNGFIX.CLEAR;
		}
};
PNGFIX.pngfixImgPanel=function(element){
	 if (isIE())
    {
        var src;
      
        	//TODO nick - how does that relate to "PNGFIX.POSTFIX"?
            if (/\.png.jsf$/.test(element.src))
            {
                src = element.src;
                element.src = PNGFIX.CLEAR
            }
      
        
        if (src){
        	 element.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "',sizingMethod='scale')";
        }
    }
}

PNGFIX._pngfix = function (el) {
		//TODO nick - how does that relate to "PNGFIX.POSTFIX"?
		//var ip=/\.png\.jsf/i;
		//var pngexp=/\.png/i;
		var es=el.style;
		var elb=el.currentStyle.backgroundImage;
		
		//TODO nick - why is it so???
		if (elb.indexOf('spn_close_btn.png') != -1) {
			return;
		}
		
		//TODO nick - why onload is checked only here?
		if(el.src&&(el.src.match(PNGFIX.RE_PNGJSF) || el.src.match(PNGFIX.RE_PNG))&&!es.filter&&!el.onload)
		{
			PNGFIX.pngfixImg(el);
		}else
		{
			if(elb.match(PNGFIX.RE_PNGJSF)) {
				//TODO nick - what if ' will be used for background-image?
				var path=elb.split('"');
				//TODO nick - move to function
				var rep=(el.currentStyle.backgroundRepeat=='no-repeat')?'crop':'scale';
				//TODO nick - move to function
				es.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+path[1]+"',sizingMethod='"+rep+"')";
				
				//TODO nick - what about width?
				es.height= Richfaces.getComputedStyle(el, 'height');
				es.backgroundImage='none';
			}
		}
};


PNGFIX.pngChangeFilter  = function (id, el, img, height, srcPrefix) {
	PNGFIX.initPostFix();
	//TODO nick - what about width?
	if (!height) {
		height = Richfaces.getComputedStyle(el, 'height');
	}
	try {
		var es=el.style;
		//TODO nick - move to function
		var rep=(el.currentStyle.backgroundRepeat=='no-repeat')?'crop':'scale';
		//TODO nick - move to function
		es.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+srcPrefix + img + PNGFIX.POSTFIX + "',sizingMethod='"+rep+"')";
		es.height=height;
		es.backgroundImage='none';
	}catch(e) {
	}
};

PNGFIX.getImagePrefix = function(imgSrc) {
	return imgSrc.substring(0, imgSrc.lastIndexOf('/') + 1);
};




isIE = function(){
	//TODO nick - only IE 6
	//ES:done.
	return /MSIE (5\.5|6).+Win/.test(navigator.userAgent);//Prototype.Browser.IE;
};

