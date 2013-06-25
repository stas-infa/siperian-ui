if (!window.DW) {
	window.DW = {};
}

if (!window.Siperian) {
	window.Siperian = {};
}

function discardElement(element) {
    var garbageBin = document.getElementById('IELeakGarbageBin');
    if (!garbageBin) {
        garbageBin = document.createElement('DIV');
        garbageBin.id = 'IELeakGarbageBin';
        garbageBin.style.display = 'none';
        document.body.appendChild(garbageBin);
    }

	window.RichFaces.Memory.clean(element);
    // move the element to the garbage bin
    garbageBin.appendChild(element);
    garbageBin.innerHTML = '';
}

Selection = {};
Selection.eventHandler=function(event){Event.stop(event);};
Selection.eventHandler = Selection.eventHandler.bindAsEventListener(Selection);
Selection.disableSelection = function (element)
{


	if (typeof element.onselectstart!="undefined") //IE
	{
		Event.observe(element, 'selectstart', this.eventHandler);
	}
	else if (typeof element.style.MozUserSelect!="undefined") //Firefox
	{
		element.style.MozUserSelect="none";
	}
	else //All other (ie: Opera)
	{
		Event.observe(element, 'mousedown', this.eventHandler);
	}
}

Selection.enableSelection = function (element)
{
	if (typeof element.onselectstart!="undefined") //IE
	{
		Event.stopObserving(element, 'selectstart', this.eventHandler);
	}
	else if (typeof element.style.MozUserSelect!="undefined") //Firefox
	{
		element.style.MozUserSelect="";
	}
	else //All other (ie: Opera)
	{
		Event.stopObserving(element, 'mousedown', this.eventHandler);
	}
}

SipModalPanel = Class.create();

{
	var ieVersion = RichFaces.getIEVersion();
	if (ieVersion && ieVersion < 7) {
		SipModalPanel.disableSelects = true;
	}
}

SipModalPanel.panels = new Array();
SipModalPanel.activePanels = new Array();

function getSizeElement() {
	var element;
	if (true/*RichFaces.navigatorType() != RichFaces.OPERA && document.compatMode=='CSS1Compat'*/) {
		element = document.documentElement;
	} else {
		element = document.body;
	}

	return element;
}

SipModalPanel.getMinimumSize = function(size) {
	return Math.max(size, 2*SipModalPanel.Sizer.INITIAL_MIN + 2);
};

SipModalPanel.prototype = {
	initialize: function(id, options) {
		this["rich:destructor"] = "destroy";
	
		this.markerId = $(id);

		this.id = $(id + "Container");

		this.options = options;
		this.resizedAction = false;

		this.baseZIndex = this.options.zindex ? this.options.zindex : 100;

		this.minWidth = SipModalPanel.getMinimumSize(this.options.minWidth);
		this.minHeight = SipModalPanel.getMinimumSize(this.options.minHeight);

		this.div = id + "Div";
		this.cursorDiv = id + "CursorDiv";
		this.cdiv = id + "CDiv";
		this.contentDiv = id + "ContentDiv";
		this.contentTable = id + "ContentTable";
		this.shadowDiv = id + "ShadowDiv";
		this.backgroundDiv = id + "Bg";
		this.backgroundImage = id + "BgImg";
		
		this.panelDOMAttachment = this.options.panelDOMAttachment; 
		this.attachmentFormId = this.options.attachmentFormId; 
		this.isModal = this.options.isModal;
		
		//IE7 zoom fix			 
		this.zoomFixNeeded = ((RichFaces.getIEVersion() || 0) ==7);
		
		this.borders = new Array();

		if (this.options.resizeable) {
			this.borders.push(new SipModalPanel.Border(id + "ResizerN", this, "N-resize", SipModalPanel.Sizer.N));
			this.borders.push(new SipModalPanel.Border(id + "ResizerE", this, "E-resize", SipModalPanel.Sizer.E));
			this.borders.push(new SipModalPanel.Border(id + "ResizerS", this, "S-resize", SipModalPanel.Sizer.S));
			this.borders.push(new SipModalPanel.Border(id + "ResizerW", this, "W-resize", SipModalPanel.Sizer.W));

			this.borders.push(new SipModalPanel.Border(id + "ResizerNWU", this, "NW-resize", SipModalPanel.Sizer.NWU));
			this.borders.push(new SipModalPanel.Border(id + "ResizerNEU", this, "NE-resize", SipModalPanel.Sizer.NEU));
			this.borders.push(new SipModalPanel.Border(id + "ResizerNEL", this, "NE-resize", SipModalPanel.Sizer.NEL));
			this.borders.push(new SipModalPanel.Border(id + "ResizerSEU", this, "SE-resize", SipModalPanel.Sizer.SEU));
			this.borders.push(new SipModalPanel.Border(id + "ResizerSEL", this, "SE-resize", SipModalPanel.Sizer.SEL));
			this.borders.push(new SipModalPanel.Border(id + "ResizerSWL", this, "SW-resize", SipModalPanel.Sizer.SWL));
			this.borders.push(new SipModalPanel.Border(id + "ResizerSWU", this, "SW-resize", SipModalPanel.Sizer.SWU));
			this.borders.push(new SipModalPanel.Border(id + "ResizerNWL", this, "NW-resize", SipModalPanel.Sizer.NWL));
		}

		if (this.options.moveable && $(id + "Header")) {
			this.header = new SipModalPanel.Border(id + "Header", this, "move", SipModalPanel.Header);
		}

		this.markerId.component = this;
		
		var eDiv = $(this.div);
		if (eDiv.style.setExpression)
			if (SipModalPanel.disableSelects /* IE 6 */ || Richfaces.getComputedStyle(eDiv, "position") != "fixed" /* IE again, not in strict mode*/)

		{
			eDiv.style.position = "absolute";
			
			var eCursorDiv = $(this.cursorDiv);
			eCursorDiv.style.position = "absolute";

			//that is to apply filter
			eDiv.style.zoom = "1";
			eCursorDiv.style.zoom = "1";

			var eCdiv = $(this.cdiv);
			eCdiv.style.position = "absolute";
            eCdiv.parentNode.style.position = "absolute";


			eCdiv.mpUseExpr = true;
		}
		
		this.pushPanel(id);

		this.eventFirstOnfocus = this.firstOnfocus.bindAsEventListener(this);
		this.eventLastOnfocus = this.lastOnfocus.bindAsEventListener(this);
		this.eventResizeHandler = this.onresizeHandler.bindAsEventListener(this);
		this.firstHref = id + "FirstHref";
		this.lastHref = id + "LastHref";
		
		this.selectBehavior = options.selectBehavior;
		
		if(!this.isModal){
			var eDiv = $(this.div);
			eDiv.style.display = "none";
			var eCursorDiv = $(this.cursorDiv);
			eCursorDiv.style.display = "none";
		}
		
		this.pngfix();
		if(!this.options.moveable){
			Event.observe(window, 'resize',this.eventResizeHandler);
		}
	},
	
	pushPanel: function (id) {
		for (var i = 0; i < SipModalPanel.panels.length; i++) {
			var p = SipModalPanel.panels[i];
			if (p && p.id && p.id.id == (id + 'Container')) {
				SipModalPanel.panels = SipModalPanel.panels.without(p);
				break;
			}
		}
		SipModalPanel.panels.push(this);
	},
	
	pngfix: function () {
		if (!Richfaces.browser.isIE)return;		
		PNGFIX.pngfixImg($(this.backgroundImage + 'TL'));
		PNGFIX.pngfixImg($(this.backgroundImage + 'TR'));
		PNGFIX.pngfixImg($(this.backgroundImage + 'BL'));
		PNGFIX.pngfixImg($(this.backgroundImage + 'BR'));
	},
	isSafari: function(){
		
		return /.Safari./.test(navigator.userAgent);
	},
	updateBackroundsStyle: function () {
		var isIE7 = /MSIE (7|8).+Win/.test(navigator.userAgent);
		if  (!Richfaces.browser.isIE|| isIE7 ) return;

		var div = $(this.cdiv);
		var width = div.offsetWidth;
		var height = div.offsetHeight;
		var halfWidth = new Number(parseInt(width / 2));
		var halfHeight = new Number(parseInt(height / 2 ));
		
		$(this.backgroundDiv + 'TL').style.width = (halfWidth) + 'px';
		$(this.backgroundDiv + 'TR').style.width = (halfWidth) + 'px';
		$(this.backgroundDiv + 'BL').style.width = (halfWidth) + 'px';
		$(this.backgroundDiv + 'BR').style.width = (halfWidth) + 'px';
		
		$(this.backgroundDiv + 'TL').style.height = (halfHeight) + 'px';
		$(this.backgroundDiv + 'TR').style.height = (halfHeight) + 'px';
		$(this.backgroundDiv + 'BL').style.height = (halfHeight) + 'px';
		$(this.backgroundDiv + 'BR').style.height = (halfHeight) + 'px';
		
	},

	width: function() {
		return this.getSizedElement().offsetWidth;
	},

	height: function() {
		return this.getSizedElement().offsetHeight;
	},
	
	getSizedElement: function() {
		if (!this._sizedElement) {
			this._sizedElement = $(this.cdiv);
		}

		return this._sizedElement;
	},

	getContentElement: function() {
		if (!this._contentElement) {
			this._contentElement = this.options.autosized ? $(this.contentTable) : $(this.contentDiv);
		}

		return this._contentElement;
	},
	
	destroy: function() {
		this._contentElement = null;
		this._sizedElement = null;
		
		SipModalPanel.panels = SipModalPanel.panels.without(this);
		
        this.enableSelects();

        SipModalPanel.activePanels = SipModalPanel.activePanels.without(this);
        
        this.parent = null;
        this.firstOutside = null;
        this.lastOutside = null;
        if (this.header) {
        	this.header.destroy();
			this.header=null;        	
        }

		for (var k = 0; k < this.borders.length; k++ ) {
			this.borders[k].destroy();
		}
		this.borders = null;

		setTimeout(function() {
			if (this.floatedToBody || this.floatedToForm) {
				var element = this.id;
				var parent = element.parentNode;
				if (parent) {
					parent.removeChild(element);
					discardElement(element);
				}
			}
		}.bind(this), 0);
		
        this.markerId.component = null;
        this.markerId = null;
    	if (this.correctSizeListener) {
			A4J.AJAX.removeListener(this.correctSizeListener);
		}

	},

	initIframe : function() {
        if (this.contentWindow) {
			Element.setStyle(this.contentWindow.document.body, { "margin" : "0px 0px 0px 0px" });
		} else {
			//TODO opera etc.

		}

		if("transparent" == Element.getStyle(document.body, "background-color")) {
			this.style.filter = "alpha(opacity=0)";
			this.style.opacity = "0";
		}
		
		this.style.backgroundColor = "transparent";
		//this.style.opacity = "0.5";
		//this.style.filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=50)';
		//
		//iframeBodyStyle.backgroundColor = "#d0d0d0";
		//iframeBodyStyle.filter = "alpha(opacity=50)";
		//iframeBodyStyle.opacity = "0.5";
		//iframeBodyStyle.zIndex = "99";
	},
	
	enableSelect: function(elt) {
		if (elt._mdwProcessed) {
			elt._mdwProcessed = undefined;

			if (elt._mdwDisabled) {
				elt.disabled = false;
				elt._mdwDisabled = undefined;
			}

			if (typeof elt._mdwHidden != "undefined") {
				elt.style.visibility = elt._mdwHidden;
				elt._mdwHidden = undefined;
			}
		}
	},
	
	disableSelect: function(elt) {
		if (!elt._mdwProcessed) {
			elt._mdwProcessed = true;
 
			if ("hide" == this.selectBehavior) {
				if (elt.style.visibility != "hidden") {
					elt._mdwHidden = elt.style.visibility;
					elt.style.visibility = "hidden";
				}
			} else {
				if (!elt.disabled) {
					elt.disabled = true;
					elt._mdwDisabled = true;
				}
			}
		}
	},

	enableInnerSelects: function() {
		if (SipModalPanel.disableSelects) {
			var selects = this.id.getElementsByTagName("SELECT");
			for (var k = 0; k < selects.length; k++) {
				this.enableSelect(selects[k]);
			}
		}
	},
	
	disableInnerSelects: function() {
		if (SipModalPanel.disableSelects) {
			var selects = this.id.getElementsByTagName("SELECT");
			for (var k = 0; k < selects.length; k++) {
				this.disableSelect(selects[k]);
			}
		}
	},
	
	enableSelects: function() {
		if (!SipModalPanel.disableSelects) {
			return ;
		}

		var lastPanel = SipModalPanel.activePanels[SipModalPanel.activePanels.length - 1];
		var newLastPanel = SipModalPanel.activePanels[SipModalPanel.activePanels.length - 2];
		
		if (newLastPanel) {
			if (lastPanel == this) {
				//we've just closed top panel, re-enable next panel if any
				newLastPanel.enableInnerSelects();
			}
		} else {
			var children = document.body.childNodes;
			for (var k = 0; k < children.length; k++) {
				var child = children[k];
				
				if (!child.getElementsByTagName) {
					continue;
				}
				
				var selects = child.getElementsByTagName("SELECT");

				for (var i = 0; i < selects.length; i++) {
					this.enableSelect(selects[i]);
				}
			}
		}
	},

	disableOuterSelects: function() {
		if (!SipModalPanel.disableSelects) {
			return ;
		}

		var lastPanel = SipModalPanel.activePanels.last();
		
		if (lastPanel) {
			//we need to disable only the last opened panel
			lastPanel.disableInnerSelects();
			this.enableInnerSelects();
		} else {
			//disable all outer 
			var children = document.body.childNodes;
			var panel = $(this.id);
			for (var k = 0; k < children.length; k++) {
				var child = children[k];
				
				if (child == panel) {
					continue;
				}
				
				if (!child.getElementsByTagName) {
					continue;
				}
				
				var selects = child.getElementsByTagName("SELECT");

				for (var i = 0; i < selects.length; i++) {
					if(!Element.isChildOf(selects[i], panel)){
						this.disableSelect(selects[i]);
					}
				}
			}				
		}
	},
	
	setLeft: function(pos) {
		var eCdiv = $(this.cdiv);
		if (eCdiv.mpUseExpr) {
			eCdiv.mpLeft = pos;
		} else {
			eCdiv.style.left = pos + "px";
		}
	},

	setTop: function(pos) {
		var eCdiv = $(this.cdiv);
		if (eCdiv.mpUseExpr) {
			eCdiv.mpTop = pos;
		} else {
			eCdiv.style.top = pos + "px";
		}
	},

	firstOnfocus: function(event) {
		var e = $(this.firstHref)
		if (this.isModal && e && (SipModalPanel.activePanels.last() == this)) {
			e.focus();
		}
	},

	lastOnfocus: function(event) {
		var e = $(this.lastHref);
		if (this.isModal && e && (SipModalPanel.activePanels.last() == this)) {
			e.focus();
		}
	},
	
	formElements: "|a|input|select|button|textarea|",
	
	processAllFocusElements: function(root, callback) {
		var idx = -1;
		var tagName;

		if (root.focus && root.nodeType == 1 && (tagName = root.tagName) &&
			// Many not visible elements have focus method, we is had to avoid processing them.
			(idx = this.formElements.indexOf(tagName.toLowerCase())) != -1 &&
			this.formElements.charAt(idx - 1) === '|' && 
			this.formElements.charAt(idx + tagName.length) === '|' &&
			!root.disabled && root.type!="hidden"
			&& (Richfaces.getComputedStyle(root,'display')!='none')
			&& (Richfaces.getComputedStyle(root,'visibility')!='hidden')){
				callback.call(this, root);
		} else {
			if (root != this.id) {
				var child = root.firstChild;
				while (child) {				
					if (!child.style || child.style.display != 'none') {
						this.processAllFocusElements(child, callback);
					}
					child = child.nextSibling;
				}
			}
		}
	},

	processTabindexes:	function(input) {
		
		if (!this.firstOutside && !(input.tagName.toLowerCase()=="select" && ModalPanel.disableSelects)) {
			this.firstOutside = input;
		}
		this.lastOutside = input;
		if (input.tabIndex && !input.prevTabIndex) {
			input.prevTabIndex = input.tabIndex;
		}
		input.tabIndex = this.isSafari() ?  -1 : undefined;
		//alert("tabIndex="+input.tabIndex);
		if (input.accesskey  && !input.prevAccesskey) {
			input.prevAccesskey = input.accesskey;
		}
		input.accesskey = undefined;
	
	},

	restoreTabindexes:	function(input) {
		if (input.prevTabIndex) {
			input.tabIndex = input.prevTabIndex;
			input.prevTabIndex = undefined;
		}else if(!input.prevTabIndex && this.isSafari()){
			//alert("restore isSafari");
			input.tabIndex = undefined;
		}
		if (input.prevAccesskey) {
			input.accesskey = input.prevAccesskey;
			input.prevAccesskey = undefined;
		}
	},

	preventFocus:	function() {
		this.processAllFocusElements(document, this.processTabindexes);
		
		if (this.firstOutside) {
			Event.observe(this.firstOutside, "focus", this.eventFirstOnfocus); 
		}
		if (this.lastOutside && this.lastOutside != this.firstOutside) {
			Event.observe(this.lastOutside, "focus", this.eventLastOnfocus); 
		}
	},

	restoreFocus: function() {
		this.processAllFocusElements(document, this.restoreTabindexes);
		
		if (this.firstOutside) {
			Event.stopObserving(this.firstOutside, "focus", this.eventFirstOnfocus);
			this.firstOutside = null;
		}
		if (this.lastOutside) {
			Event.stopObserving(this.lastOutside, "focus", this.eventLastOnfocus);
			this.lastOutside = null;
		}
	},
	
	replacePanelInDOM: function(element){
		if (this.panelDOMAttachment && this.panelDOMAttachment == 'body' && !this.floatedToBody) {
			this.parent = element.parentNode;
			document.body.insertBefore(this.parent.removeChild(element), null);
			this.floatedToBody = true;
		}
		
		
		if (this.panelDOMAttachment && this.panelDOMAttachment == 'form' && !this.floatedToForm) {
			this.parent = element.parentNode;
			if(this.attachmentFormId && this.attachmentFormId!="" && $(this.attachmentFormId)){
				this.form = $(this.attachmentFormId);
				this.form.insertBefore(this.parent.removeChild(element), null);
				this.floatedToForm = true;
			}else{
				this.form = this.getPanelParentForm(element);
				if(this.form){
					this.form.insertBefore(this.parent.removeChild(element), null);
					this.floatedToForm = true;
				}
			}
		}
	},
	
	replacePanelInDOMBack: function(element){
		if (this.floatedToBody && this.parent) {				
			document.body.removeChild(element);
			this.parent.appendChild(element);
			this.floatedToBody = false;
		}
		if (this.floatedToForm && this.parent) {				
			this.form.removeChild(element);
			this.parent.appendChild(element);
			this.floatedToForm = false;
		}
	},
	
	getPanelParentForm: function(element){
		var forms = document.body.getElementsByTagName("form");
		if (forms) {
			for (var i = 0; i < forms.length; i++) {
				if(Element.isChildOf(element, forms[i])){
					return forms[i];
				} 
			}
		}
	},
	
	adjustZoominIE7: function(){	
		var rect = document.body.getBoundingClientRect();		
		var newZoomLevel = Math.round((rect.right-rect.left)/document.body.clientWidth * 100)+"%";
		if(this.zoomLevel != newZoomLevel){
			this.zoomLevel =newZoomLevel;
			$(this.cdiv).setStyle({zoom:  newZoomLevel});
			// EI7 does not render the layout correctly in some cases 
			// after the changing the zoom property. 
			// However if we use scroll or resize the window
			// right after the zoom was applied the EI7 rearrange the layout in
			// a right way. As a workaround we will call scrollBy() function.
			// We would use resizeTo() or resizeBy() to workaround the broken content issue, 
			// but IE7 blocks it if several browser tabs are opened.		
			window.scrollBy(1, 0);
			window.scrollBy(-1, 0);
			window.resizeBy(1,0);
			window.resizeBy(-1,0);
		}
	},
	cancelTimer: function(){
		if(this.timerId){	
			clearInterval(this.timerId);
			this.timerId = undefined;
		}	
	},
	onresizeHandler:function(){
		if(this.shown){
			this.doShowAction(null,this.options,this.id);
		}
	},
	show: function(event, opts) {

		if(!this.shown && this.invokeEvent("beforeshow",event,null,element)) {
			
			var element = this.id;
			this.doShowAction(event,opts,element);
		}
		this.correctSizeListener = new A4J.AJAX.Listener(function(req, event, data) {
				this.correctDimensions();		
		}.bind(this));
		A4J.AJAX.AddListener(this.correctSizeListener);
	},	
	doShowAction:function(event,opts,element){
		if(this.isModal){
			this.preventFocus();
		}				       
		
		this.replacePanelInDOM(element);
		
		//for IE7 we will setup a listener to check 
		//if zoom feature is used
		if(this.zoomFixNeeded){
			this.cancelTimer();		
			this.timerId=setInterval(this.adjustZoominIE7.bind(this),100);
		}
		
		var eCdiv = $(this.cdiv);
		var forms = eCdiv.getElementsByTagName("form");

		if (this.options.keepVisualState && forms) {
			this.formOnsubmit = this.setStateInput.bindAsEventListener(this); 
			for (var i = 0; i < forms.length; i++) {
				Event.observe(forms[i], "submit", this.formOnsubmit); 
			}
		}

		var eIframe;
		if (SipModalPanel.disableSelects && !this.iframe) {
                        this.iframe = this.id.id + "IFrame";
			new Insertion.Top(eCdiv,
                     	"<iframe src=\"javascript:''\" frameborder=\"0\" scrolling=\"no\" id=\"" + this.iframe + "\" " +								
			"class=\"spn-mpnl-iframe\" style=\"width: 1px; height: 1px;\">" +
			"</iframe>");
			
			eIframe = $(this.iframe); 

			//alert("IFrame:" + eIframe + "created!");

			//eIframe.onload = this.initIframe.bind(eIframe);
			Event.observe(eIframe, 'load', this.initIframe.bindAsEventListener(eIframe));
		}

		var options = {};
		this.userOptions = {};

		if (!eCdiv.mpSet) {
			Object.extend(options, this.options);
		}

		if (opts) {
			Object.extend(options, opts);
			Object.extend(this.userOptions, opts);
		}
		
		this.currentMinHeight = SipModalPanel.getMinimumSize((options.minHeight || options.minHeight == 0) ? options.minHeight : this.minHeight); 
		this.currentMinWidth = SipModalPanel.getMinimumSize((options.minWidth || options.minWidth == 0) ? options.minWidth : this.minWidth);
		
		var eContentElt = this.getContentElement();

		if (!this.options.autosized) {
			if (options.width && options.width == -1) 
				options.width = 300;
			if (options.height && options.height == -1) 
				options.height = 200;
		}
			
		if (options.width && options.width != -1) {
			if (this.currentMinWidth > options.width) {
				options.width = this.currentMinWidth;
			}
	
			eContentElt.style.width = options.width + (/px/.test(options.width) ? '' : 'px');
		}

		if (options.height && options.height != -1) {
			if (this.currentMinHeight > options.height) {
				options.height = this.currentMinHeight;
			}

			eContentElt.style.height = options.height + (/px/.test(options.height) ? '' : 'px');
		}

		eCdiv.mpSet = true;

		if(this.isModal){
			this.disableOuterSelects();
		}
		SipModalPanel.activePanels = SipModalPanel.activePanels.without(this);
		SipModalPanel.activePanels.push(this);

		//this.shape.init(eCdiv, this.options);

		var eDiv = $(this.div);
		if (eDiv.style.position == "absolute")
		{
			var we = "getSizeElement().clientWidth + \"px\"";
			var he = "getSizeElement().clientHeight + \"px\"";
			eDiv.style.setExpression("width", we);
			eDiv.style.setExpression("height", he);

			var eCursorDiv = $(this.cursorDiv);
			eCursorDiv.style.setExpression("width", we);
			eCursorDiv.style.setExpression("height", he);

			var le = "-Position.cumulativeOffset(this.parentNode)[0] + getSizeElement().scrollLeft + \"px\"";
			var te = "-Position.cumulativeOffset(this.parentNode)[1] + getSizeElement().scrollTop + \"px\"";

			eDiv.style.setExpression("left", le);
			eDiv.style.setExpression("top", te);

			eCursorDiv.style.setExpression("left", le);
			eCursorDiv.style.setExpression("top", te);


			var leftExpr = "(this.mpLeft || 0) + -Position.cumulativeOffset(this.parentNode)[0] + getSizeElement().scrollLeft + \"px\"";
			var topExpr = "(this.mpTop || 0) + -Position.cumulativeOffset(this.parentNode)[1] + getSizeElement().scrollTop + \"px\"";

			eCdiv.style.setExpression("left", leftExpr);
			eCdiv.style.setExpression("top", topExpr);

		}

		element.style.visibility='hidden';
		Element.show(element);
		
		this.correctDimensions();
		this.correctShadowSizeEx();

		if (options.left) {
			var _left;
			if (options.left != "auto") {
				_left = parseInt(options.left, 10);
			} else {
				
				var cw =  getSizeElement().clientWidth;
			 	var _width = this.width(); 
				if (cw >= _width) {
				 	_left = (cw - _width) / 2;
				} else {
					_left = 0;
				}
			}

			this.setLeft(Math.round(_left));
		}

		if (options.top) {
			var _top;
			if (options.top != "auto") {
				_top = parseInt(options.top, 10);
			} else {
				var cw = getSizeElement().clientHeight;
				var _height = this.height();
				if (cw >= _height) {
					_top = (cw - _height) / 2;
				} else {
					_top = 0;
				}
			}

			this.setTop(Math.round(_top));
		}
		
		if (this.options.autosized) {
			this.observerSize =
		        window.setInterval(this.correctShadowSize.bindAsEventListener(this), 500);
		}

		this.doResizeOrMove(SipModalPanel.Sizer.Diff.EMPTY);

		for (var k = 0; k < this.borders.length; k++ ) {
			this.borders[k].doPosition();
		}

		if (this.header) {
			this.header.doPosition();
		}

        Element.hide(eCdiv);
        element.style.visibility = "";

		this.lastOnfocus();
    	
    	Element.show(eCdiv);
    	
    	this.updateBackroundsStyle();

    	var event = {};
    	event.parameters = opts || {};
    	this.shown = true;
    	this.invokeEvent("show",event,null,element);
	},
	
	correctDimensions: function() {
		if (!this.isMacSafari()) {
			return;
		}
		var cdiv = $(this.cdiv);
		if (cdiv.style.width || cdiv.style.height) {
			this.removeSize(cdiv);
		}
		this.correctDimension(cdiv);
	},
	
	correctDimension: function(el) {
		el.style.width = this.correctSize(el.offsetWidth);
		el.style.height = this.correctSize(el.offsetHeight);
	},
	
	removeSize: function (v) {
		v = $(v);
		if (v.style.removeProperty) {
			v.style.removeProperty('width');
			v.style.removeProperty('height');
		}else if (v.style.removeExpression) {
			v.style.removeExpression('width');
			v.style.removeExpression('height');
		}
	},
	
	correctSize: function (v) {
		var val = v + '';
		if (val) {
			val = parseInt(val.replace('px', ''));
			if (val % 2 != 0) {
				val = (val+1) + 'px';
				return val + '';
			}
		}
		return v;
	},
	
	
	isMacSafari: function() {
		return (Prototype.Browser.WebKit /*&& navigator.platform.indexOf('Mac') != -1*/);
	},
	
	startDrag: function(border) {
		for (var k = 0; k < this.borders.length; k++ ) {
			this.borders[k].hide();
		}
		Selection.disableSelection(document.body);
		Selection.disableSelection($(this.cdiv));
	},

	endDrag: function(border) {
		this.correctDimensions();
		for (var k = 0; k < this.borders.length; k++ ) {
			this.borders[k].show();
			this.borders[k].doPosition();
		}
		Selection.enableSelection(document.body);
		Selection.enableSelection($(this.cdiv));
	},

	hide: function(event, opts) {
		if (this.shown && this.invokeEvent("beforehide",event,null,element)) {

			this.currentMinHeight = undefined; 
			this.currentMinWidth = undefined;

			if(this.isModal){
				this.restoreFocus();
	        	this.enableSelects();
			}
			
			if(this.zoomFixNeeded)
				this.cancelTimer();
				
			SipModalPanel.activePanels = SipModalPanel.activePanels.without(this);			
			
			var eDiv = $(this.div);
			var eCdiv = $(this.cdiv);
	
            if (eDiv.style.position == "absolute") {
				eDiv.style.removeExpression("width");
				eDiv.style.removeExpression("height");
	
				eDiv.style.removeExpression("left");
				eDiv.style.removeExpression("top");
	
				var eCursorDiv = $(this.cursorDiv);
				eCursorDiv.style.removeExpression("width");
				eCursorDiv.style.removeExpression("height");
	
				eCursorDiv.style.removeExpression("left");
				eCursorDiv.style.removeExpression("top");
	
				eCdiv.style.removeExpression("left");
				eCdiv.style.removeExpression("top");
			}
	
			var element = $(this.id);
			Element.hide(element);			
			
			this.replacePanelInDOMBack(element);
			
			var event = {};
			event.parameters = opts || {};
			if (this.options && this.options.onhide) {
				this.options.onhide(event);
			}
			
			var forms = eCdiv.getElementsByTagName("form");
			if (this.options.keepVisualState && forms) {
				for (var i = 0; i < forms.length; i++) {
					Event.stopObserving(forms[i], "submit", this.formOnsubmit);
				}
			}
	
			this.shown = false;
			
			if (this.options.autosized) {
				window.clearInterval(this.observerSize);
			}
			
			if (SipModalPanel.activePanels.length > 0) {
				SipModalPanel.activePanels.last().preventFocus();
			}
			
			if (this.correctSizeListener) {
				A4J.AJAX.removeListener(this.correctSizeListener);
			}

		}
	},

	_getStyle: function(elt, name) {
		return parseInt(elt.style[name].replace("px", ""), 10);
	},
	
	doResizeOrMove: function(diff) {
		var vetoes = {};
		var cssHash = {};
		var cssHashWH = {};

		var vetoeChange = false;
		var newSize;
		
		var eContentElt = this.getContentElement();
		
		newSize = this._getStyle(eContentElt, "width");//Richfaces.getComputedStyleSize(eContentDiv, "width");

		var oldSize = newSize;
		newSize += diff.deltaWidth || 0;

		if (newSize >= this.currentMinWidth || this.options.autosized) {
			if (diff.deltaWidth) {
				cssHashWH.width = newSize + 'px';
			}
		} else {
			if (diff.deltaWidth) {
				cssHashWH.width = this.currentMinWidth + 'px';

				vetoes.vx = oldSize - this.currentMinWidth;
			}

			vetoes.x = true;
		}

		if (vetoes.vx && diff.deltaX) {
			diff.deltaX = -vetoes.vx;
		}
		
		var eCdiv = $(this.cdiv); 

		if (diff.deltaX && (vetoes.vx || !vetoes.x)) {
			if (vetoes.vx) {
				diff.deltaX = vetoes.vx;
			}
			var newPos;
			
			newPos = this._getStyle(eCdiv, "left");//Richfaces.getComputedStyleSize(eCdiv, "left");
			newPos += diff.deltaX;
			cssHash.left = newPos + 'px';
		}

		newSize = this._getStyle(eContentElt, "height")//;Richfaces.getComputedStyleSize(eContentDiv, "height");

		var oldSize = newSize;
		newSize += diff.deltaHeight || 0;

		if (newSize >= this.currentMinHeight || this.options.autosized) {
			if (diff.deltaHeight) {
				cssHashWH.height = newSize + 'px';
			}
		} else {
			if (diff.deltaHeight) {
				cssHashWH.height = this.currentMinHeight + 'px';

				vetoes.vy = oldSize - this.currentMinHeight;
			}

			vetoes.y = true;
		}

		if (vetoes.vy && diff.deltaY) {
			diff.deltaY = -vetoes.vy;
		}

		if (diff.deltaY && (vetoes.vy || !vetoes.y)) {
			if (vetoes.vy) {
				diff.deltaY = vetoes.vy;
			}

			var newPos;
			if (eCdiv.mpUseExpr) {
				newPos = eCdiv.mpTop || 0;
				newPos += diff.deltaY;

				eCdiv.mpTop = newPos;
				cssHash.top = newPos + 'px';
			} else {
				newPos = this._getStyle(eCdiv, "top");//Richfaces.getComputedStyleSize(eCdiv, "top");
				newPos += diff.deltaY;
				cssHash.top = newPos + 'px';
			}
		}

		Element.setStyle(eContentElt, cssHashWH);

		Element.setStyle(eCdiv, cssHash);
		
		this.correctShadowSizeEx();
		
		Object.extend(this.userOptions, cssHash);
		Object.extend(this.userOptions, cssHashWH);

		var w = this.width();
		var h = this.height();

		this.reductionData = null;

		if (w <= 2*SipModalPanel.Sizer.INITIAL_MAX) {
			this.reductionData = {};
			this.reductionData.w = w;
		}

		if (h <= 2*SipModalPanel.Sizer.INITIAL_MAX) {
			if (!this.reductionData) {
				this.reductionData = {};
			}

			this.reductionData.h = h;
		}

		if (this.header) {
			this.header.doPosition();
		}
		
		this.updateBackroundsStyle();
		
		return vetoes;
	},

	_findForm: function(elt) {
		var target = elt;
		while (target) {
			if (!target.tagName /* document node doesn't have tagName */ 
					|| target.tagName.toLowerCase() != "form") {
				
				target = target.parentNode;
			} else {
				break;
			}
		}
		
		return target;
	},
	
	setStateInput: function(e) {
		var target = Event.element(e);
		if (e && target) {
			// Concret input but not entire form is a target element for onsubmit in FF
			target = this._findForm(target);
			
			var input = document.createElement("input");
			input.type = "hidden";
			input.id = this.markerId.id + "OpenedState";
			input.name = this.markerId.id + "OpenedState";
			input.value = this.shown ? "true" : "false";
			target.appendChild(input);

			var keys = $H(this.userOptions).keys();
			if (keys) {
				for (var i = 0; i < keys.length; i++) {
					input = document.createElement("input");
					input.type = "hidden";
					input.id = this.id.id + "StateOption_" + keys[i];
					input.name = this.id.id + "StateOption_" + keys[i];
					input.value = this.userOptions[keys[i]];
					target.appendChild(input);

				}
			}
			
			return true;
		}
	},
	
	correctShadowSize: function(event) {
		this.correctShadowSizeEx();
	},
	
	correctShadowSizeEx: function() {
		var eShadowDiv = $(this.shadowDiv);
		if (!eShadowDiv) {
			return;
		}
		var eIframe = $(this.iframe);
		
		var dx = 0;
		var dy = 0;
		if (!Richfaces.browser.isIE)
		{
			dx = eShadowDiv.offsetWidth-eShadowDiv.clientWidth;
			dy = eShadowDiv.offsetHeight-eShadowDiv.clientHeight;
		}
		var w = this.width();
		var h = this.height();
		eShadowDiv.style.width = (w-dx)+"px";
		eShadowDiv.style.height = (h-dy)+"px";
		
		if (eIframe) {
			eIframe.style.width = w+"px";
			eIframe.style.height = h+"px";
		}
	},
	
	invokeEvent: function(eventName, event, value, element) {
	
		var eventFunction = this.options['on'+eventName];
		var result;

		if (eventFunction) {
			var eventObj;
			if (event) {
				eventObj = event;
			}
			else if(document.createEventObject) {
				eventObj = document.createEventObject();
			}
			else if( document.createEvent ) {
				eventObj = document.createEvent('Events');
				eventObj.initEvent( eventName, true, false );
			}
			
			eventObj.rich = {component:this};
			eventObj.rich.value = value;

			try	{
				result = eventFunction.call(element, eventObj);
			}
			catch (e) { LOG.warn("Exception: "+e.Message + "\n[on"+eventName + "]"); }
		}
		
		if (result!=false) {
			 result = true;
		}	 
		return result;
	}	
}

Siperian.findModalPanel = function (id) {
	if (id) {
		var prefId = (id.charAt(0) == ':' ? id : ':' + id);

		for (var i = 0; i < SipModalPanel.panels.length; i++ ) {
			var pnl = SipModalPanel.panels[i];
			if (pnl && pnl.markerId) {
				var pnlId = pnl.markerId.id;

				if (pnlId) {
					//try to match ids
					if (pnlId.length >= prefId.length) {
						var substr = pnlId.substring(pnlId.length - prefId.length, pnlId.length);
						if (substr == prefId) {
							return pnl.markerId;
						}
					}
				}
			}
		}
	}
}

Siperian.showModalPanel = function (id, opts, event) {
	
	var invoke = 
		function(f) {
			if (document.readyState != "complete") {
					var args = arguments;
					var dis = this;
				window.setTimeout(
					function() {
						args.callee.apply(dis,args );
					}, 50);
			} else {
				f();
			}
		};
	
	var panel = $(id);
	if (!panel) {
		panel = Siperian.findModalPanel(id);
	}
	invoke(function() {
		panel.component.show(event, opts);
	});
};

Siperian.hideModalPanel = function (id, opts, event) {
	var panel = $(id);
	if (!panel) {
		panel = Siperian.findModalPanel(id);
	}
	panel.component.hide(event, opts);
};
