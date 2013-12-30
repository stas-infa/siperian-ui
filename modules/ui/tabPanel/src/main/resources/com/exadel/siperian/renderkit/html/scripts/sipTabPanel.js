TabPanel = Class.create();

TabPanel.isTabActive = function (tab) {
		if (tab) {
			return Element.hasClassName(tab, "containerActive");
		}
		return false;
};

TabPanel.CLASSES = {
	STATIC: {
		ARROWLEFT: 'sip-static-pnl-arrowLeft',
		ARROWLEFTBGD: 'sip-static-pnl-arrow-left.png',
		ARROWLEFTBGDDIS: 'sip-static-pnl-arrow-left-dis.png',
		ARROWLEFTBGD_H: '26px',
		ARROWRIGHT: 'sip-static-pnl-arrowRight',
		ARROWRIGHTBGD: 'sip-static-pnl-arrow-right.png',
		ARROWRIGHTBGDDIS: 'sip-static-pnl-arrow-right-dis.png',
		ARROWRIGHTBGD_H: '27px',
		ACTIVETABLEFTBGD: 'sip-static-pnl-active-tab.png',
		ACTIVETABLEFTBGD_H: '19px',
		INACTIVETABLEFTBGD: 'sip-static-pnl-inactive-tab.png',
		INACTIVETABLEFTBGD_H: '17px',
		ACTIVETABRIGHTBGD: 'sip-static-pnl-active-tab.png',
		ACTIVETABRIGHTBGD_H: '27px',
		INACTIVETABRIGHTBGD: 'sip-static-pnl-inactive-tab.png',
		INACTIVETABRIGHTBGD_H: '21px',
		ACTIVETAB: 'sip-static-pnl-tabContainerActive',
		INACTIVETAB: 'sip-static-pnl-tabContainerInactive'
	},
	
	DYNAMIC: {
		ARROWLEFT: 'arrowLeft',
		ARROWLEFTBGD: 'arrow-left.png',
		ARROWLEFTBGDDIS: 'arrow-left-dis.png',
		ARROWLEFTBGD_H: '27px',
		ARROWRIGHT: 'arrowRight',
		ARROWRIGHTBGD: 'arrow-right.png',
		ARROWRIGHTBGDDIS: 'arrow-right-dis.png',
		ACTIVETABLEFTBGD: 'spn_tabpanel_activetab.png',
		ACTIVETABLEFTBGD_H: '19px',
		INACTIVETABLEFTBGD: 'spn_tabpanel_tab.png',
		INACTIVETABLEFTBGD_H: '18px',
		ACTIVETABRIGHTBGD: 'spn_tabpanel_activetab_righ.png',
		ACTIVETABRIGHTBGD_H: '27px',
		INACTIVETABRIGHTBGD: 'spn_tabpanel_tab_right.png',
		INACTIVETABRIGHTBGD_H: '22px',
		ACTIVETAB: 'containerActive',
		INACTIVETAB: 'containerInActive'
	}
};

TabPanel.IMAGES = {
	CLOSE : 'spn_close_btn.png',
	LONG_RUNNING: 'spn_ico_tab_process.gif'
};

TabPanel.findByType = function (value, type) {
	return TabPanel.CLASSES[type.toUpperCase()][value];
};

TabPanel.STATIC= 'static';
TabPanel.DYNAMIC = 'dynamic';

Object.extend(TabPanel.prototype, {

	//TODO nick - decrease number of constructor arguments
	initialize: function(id, containerId, activeTabId, loadedTabs ,maxTabWidth, ontabchange, ontabclosed, tabsInfo, imageSrc, type, maxTabsLoaded, addNewTabScript) {
		this["rich:destructor"] = "destroy";
		this.id = id;
		this.containerId = containerId;
		this.maxTabWidth = (maxTabWidth > 700) ? 700 : maxTabWidth;
		this.maxTabsLoaded = maxTabsLoaded;
		this.ontabchange = ontabchange;
		this.ontabclosed = ontabclosed;
		this.addNewTabScript = addNewTabScript;
		this.type = type;
		this.element = $(this.id);
		this.tabs = $(this.id + ':tabs');
		this.tabsContainer = $(this.id + ':tabsContainer');
		this.tabsInfo = tabsInfo;
		this.left = $(this.id + ':left');
		this.right = $(this.id + ':right');
		this.content = $(this.id + ':content');		
		this.rightTop = $(this.id + ':rightTop');
		this.contentContainer = $(this.id + ':contentContainer');
		this.tabListHeaderDiv = $(this.id + ':tabListHeaderDiv');
		this.tabListContentDiv = $(this.id + ':tabListContentDiv');
		this.tabListLeftDiv = $(this.id + ':tabListLeft');
		this.tabListDiv = $(this.id + ':tabListDiv');
		this.renderedTabNameHolder = $(this.id + ':renderedTabName');
		this.srcPrefix = PNGFIX.getImagePrefix(imageSrc);
		this.srcPostfix = PNGFIX.initPostFix();
		this.loadedTabs = $H(loadedTabs);
		this.loadingTabs = new Hash();
		this.tabs2Delete = new Array();
		this.longRunningTabs = new Hash();
		this.longRunningTabsHolder = $(this.id + ':longRunningTabName');
		this.activeTabId = activeTabId;
				 
		this.tabListVisible = false;
		this.maxLabelWidth = null;
		window.setTimeout(this.init.bind(this), 300);
		Event.observe(window, 'resize', this.onresize.bind(this));
		this.element.component = this;
	},
	
	destroy: function() {
		Event.stopObserving(window, 'resize', this.onresize);
		if (this.scroller) {		
			this.scroller.unbindControls(this.left);	
			this.scroller.unbindControls(this.right);
			delete this.scroller;
		}

		delete this.loadingTabs;
		delete this.longRunningTabs;
		delete this.longRunningTabsHolder;
		delete this.tabsInfo;
		delete this.tabs2Delete;
		if(this.loadedTabs){
			this.loadedTabs = null;
		}
		this.ontabchange = null;
		this.ontabclosed = null;
		this.srcPostfix = null;
		this.srcPrefix = null;
		this.tabListHeaderDiv= null;
		this.tabListContentDiv = null;
		this.tabListLeftDiv = null;
		this.tabListDiv = null;
		this.renderedTabNameHolder = null;
		this.left = null;
		this.right = null;
		this.rightTop = null;
		this.tabsContainer = null;
		this.tabs = null;
		this.content = null;
		this.contentBg = null;
		this.contentContainer = null;
		this.addNewTabScript = null;
		this.element.component = null;
		this.element = null;
		this.type = null;
	
	},
	
	isReady: function() {
		return this.tabs.getWidth() != 0;
	},
	
	init: function () {
		if (!this.isReady()) {
			clearTimeout(this.initTimeout);
			this.initTimeout = window.setTimeout(new Function("", "var d = $('"+this.id+"'); if(d && d.component && d.component.init) { d.component.init(); }"), 200);
			return;
		}
		this.collectTabsInfo(this.tabsInfo);
		this.pngfix();
		this.initTabList();
		this.holdLongRunningValues();
		this.initScrolling();
		this.tabs.style.visibility = 'visible';
		if (this.activeTabId) {
			this.renderedTabNameHolder.value = $(this.activeTabId).name;
			this.showContent($(this.getTabId(this.activeTabId)));
		}
	},
	
	isDynamic: function () {
		return this.type == TabPanel.DYNAMIC;
	},
	
	pngfix: function () {
		if (!isIE())return;
		for (var i = 0; i < this.tabsInfo.length; i++) {
			var tab = this.tabsInfo[i];
			var id = tab.id;
			PNGFIX.pngfixElement(tab.leftDiv, false); 
			PNGFIX.pngfixElement(tab.rightDiv, true);
		}
		PNGFIX.pngfixElement(this.tabListHeaderDiv, true);
		PNGFIX.pngfixElement($(this.id + ':tabListBottom'), false);
		PNGFIX.pngfixElement(this.tabListLeftDiv, false);
		PNGFIX.pngfixElement($(this.id + ':tabListCorner'), false);
		if (!this.isDynamic()) {
			PNGFIX.pngfixElements($(this.id + ':tl'), $(this.id + ':tr'), $(this.id + ':bl'), $(this.id + ':br'));
		}
	},
	
	pngfixContent: function () {
		PNGFIX.pngfixElements($(this.id + ':tl'), $(this.id + ':tr'), $(this.id + ':bl'), $(this.id + ':br'));
	},

	collectTabsInfo: function (tabsInfo) {
		this.tabsInfo = new Array();
		for (var i = 0; i < tabsInfo.length; i++) {
			var tabHeader = $(tabsInfo[i].id + ':header');
			this.collectTabInfo(tabHeader, tabsInfo[i]);
			this.tabsInfo[i] = tabHeader;
		}
	},
	
	collectTabInfo: function (tabHeader, info) {
			var componentId = info.id;
			var id = tabHeader.id;
			tabHeader.rowKey = info.rowKey;
			tabHeader.name = info.name;
			tabHeader.title = '';
			tabHeader._title = null;
			tabHeader.leftDiv = $(id + 'Left');
			tabHeader.rightDiv = $(id + 'Right');
			tabHeader.labelDiv = $(id + 'Label') ? $(id + 'Label') : $(id + 'Left');
			tabHeader.linkDiv = $(id + 'Link');
			tabHeader.componentId = componentId;
			tabHeader.ontabenter = info.ontabenter;
			tabHeader.ontableave = info.ontableave;
			
			if (info.longRunning) {
				this.longRunningTabs.set(componentId, true);
			}
			
			this.initTabWidth(tabHeader); 
			Event.observe(tabHeader, 'mouseover',new Function("$('"+this.id+"').component.initTitle(this);"));
	},
	
	calculateMaxLabelWidth: function(tab) {
		var maxTabWidth = this.maxTabWidth;
		if (!maxTabWidth) {
			return;
		}
		var left = tab.leftDiv;
		var right = tab.rightDiv;
		var label = tab.labelDiv;
		if (this.isDynamic()) {
			//TODO nick - will likely fail in IE
			this.maxLabelWidth = maxTabWidth - Richfaces.getComputedStyleSize(left, 'padding-left') - Richfaces.getComputedStyleSize(right, 'width') - Richfaces.getComputedStyleSize(label, 'margin-right');
		}else {
			//TODO nick - will likely fail in IE
			this.maxLabelWidth = maxTabWidth - Richfaces.getComputedStyleSize(right, 'width');
		}
	},
	
	initTitle: function (elt) {
		var label = elt.labelDiv;
		if (label && label.offsetWidth != label.scrollWidth) {
			elt.title = (label.textContent) ? label.textContent : (label.innerText ? label.innerText : '');
		}else {
			elt.title = '';
		}
	},
	
	_initScrolling: function () {
		if (this.scroller.isScrollNeed()) {
			this.scroller.bindControl(this.right, hScroller.CONTROL.FORWARD);
			this.scroller.bindControl(this.left , hScroller.CONTROL.BACK);
			this.right.style.visibility = 'visible';
			this.left.style.visibility = 'visible';
			this.tabListHeaderDiv.style.visibility = 'visible';
			if (!this.isDynamic()) {
				this.element.className = 'sip-static-pnl-searchContainer';
			}else {
				this.tabListHeaderDiv.style.visibility = 'visible';
			}
			this.onleft(false);
			this.onright(true);
		}else {
			this.right.style.visibility = 'hidden';
			this.left.style.visibility = 'hidden';
			this.tabListHeaderDiv.style.visibility = 'hidden';
			if (!this.isDynamic()) {
				this.element.className = 'sip-static-pnl-searchContainerNoScroll';
			}
			//this.tabs.scrollLeft = 0;
		}
		this.scroller.init(true);
		//this.tabs.style.width = this.tabsContainer.offsetWidth - (Richfaces.getComputedStyleSize(this.tabs, 'marginLeft') || Richfaces.getComputedStyleSize(this.tabs, 'margin-left'));
		var active = (this.activeTabId) ? $(this.activeTabId) : null;
		if (active) {
			this.scroller.activateTargetItem(null, active);
		}
	},
	
	initScrolling: function() {
		if (this.scroller) {
			this.scroller.unbindControls(this.left);	
			this.scroller.unbindControls(this.right);
			delete this.scroller;
		}
		var widthCorrection = this.isDynamic() ? 0 : 3;// This is a right button's shadow width. Ignore it.
		this.tabs.getScrollWidth = this.getScrollWidth.bind(this);
		this.scroller = new hScroller(this.id,this.tabs, this.tabsInfo, {'onleft' : function(enabled) {this.onleft(enabled);}.bind(this), 'onright' : function(enabled) {this.onright(enabled);}.bind(this)}, widthCorrection);
		this._initScrolling();
	},
	
	getScrollWidth: function () {
		var tabsMarginLeft = Richfaces.getComputedStyleSize(this.tabs, 'marginLeft') || Richfaces.getComputedStyleSize(this.tabs, 'margin-left');
		return this.tabsContainer.offsetWidth - tabsMarginLeft - this.tabListHeaderDiv.getWidth() - this.right.getWidth(); 
	},
	
	onresize: function () {
		if (this.scroller && !this.scroller.initialized) {
			return;
		}
	    clearTimeout(this.onResizeTimeoutId);
	    this.onResizeTimeoutId = setTimeout(function(){
	        if (this.scroller) {
				this._initScrolling();
			}
	    }.bind(this), 2000);
	},
	
	onleft: function(enabled) {
		this.left.className = TabPanel.findByType('ARROWLEFT', this.type) + (enabled ? '' : 'Dis');
		if (isIE()) {
			var b = TabPanel.findByType((enabled) ? 'ARROWLEFTBGD' : 'ARROWLEFTBGDDIS', this.type);
			PNGFIX.pngChangeFilter(this.id, this.left, b, TabPanel.findByType('ARROWLEFTBGD_H',this.type), this.srcPrefix);
		}
	},
	
	onright: function (enabled) {
	   this.right.className = TabPanel.findByType('ARROWRIGHT', this.type) + (enabled ? '' : 'Dis');
	   	if (isIE()) {
	   		var b = TabPanel.findByType((enabled) ? 'ARROWRIGHTBGD' : 'ARROWRIGHTBGDDIS', this.type);
			PNGFIX.pngChangeFilter(this.id, this.right, b, TabPanel.findByType('ARROWRIGHTBGD_H',this.type), this.srcPrefix);
		}
	   
	},
	
	initTabWidth: function (tab) {
		if (!this.maxLabelWidth) {
			this.calculateMaxLabelWidth(tab);
		}
		var MAX_WIDTH = this.maxLabelWidth;
		if (!MAX_WIDTH) {
			return;
		}
		var label = tab.labelDiv;
		var labelWidth = label.scrollWidth;
		//TODO nick - how about innerText?
		tab.label = tab.textContent;
		label.origWidth = labelWidth;
		if (label && labelWidth > MAX_WIDTH) {
			if (!Prototype.Browser.IE) {
				this.cutOffLabel(tab, label, labelWidth, MAX_WIDTH);
			}
			this.setActualLabelWidth(label, MAX_WIDTH);
		}else {
			this.setActualLabelWidth(label, labelWidth);
		}
		if (this.isDynamic()) {
			label.className = TabPanel.isTabActive(tab) ? 'spn_sr_tabpanel_contentActive' : 'spn_sr_tabpanel_content';
		}else {
			label.className = 'sip-static-pnl-left';
		}
		label.style.overflow = 'hidden';
	},
	
	setActualLabelWidth: function (label, width) {
		var paddingLeft = Richfaces.getComputedStyleSize(label, 'padding-left') || Richfaces.getComputedStyleSize(label, 'paddingLeft');
		var paddingRight = Richfaces.getComputedStyleSize(label, 'padding-right') || Richfaces.getComputedStyleSize(label, 'paddingRight');
		var w = width - paddingLeft - paddingRight;
		label.style.width = w + 'px';
	},
	
	cutOffLabel: function (tab, label, labelWidth, maxWidth) {
		if (!this.maxLabelWidth) {
			return;
		}
		var labelWidth =  (labelWidth > label.origWidth)? labelWidth : label.origWidth;
		var k = (maxWidth/labelWidth);
		var _text = tab.label;
		var l = _text.length;
		var text;
		if (k < 1) {
				l = l * k - '...'.length;
				if (l > 0) {
					label.title = _text;
					
					//TODO nick - dots should be appended to text and not inserted before it, aren't they?
					text = _text.substring(0, l) + '...';
				}		
		} else {
			_text = label.textContent;
			text = tab.label;
		}
				
		if (label.textContent) {
			var c = this.findTextContentHolder(label, _text);
			if (c && text) {
				c.textContent = text;
			}
		}else if (label.innerText) {
			label.innerText = text;
		}
	},
	
	//TODO nick - what is this for???
	findTextContentHolder: function (label, _text) {
		var children = label.childNodes;
		for (var i = 0; i < children.length; i++) {
			var c = children[i];
			if (c && c.data && c.data == _text) {
				return c;
			}
		}
		
	},
	
	_closeTab: function (id, event) {
		var tab = $(id);
		this.removeFromTabList(id);
		this.deleteTab(tab);
		this.deleteTabContent(tab);
		this.initScrolling();
		this.scroller.correctScroll();
	},
	
	deleteTabContent: function(tab) {
		var contentTab = $(tab.componentId);
		this.loadedTabs.unset(tab.id);
		if (contentTab) {
			contentTab.parentNode.removeChild(contentTab);
		}
	},
	
	_findNextTab: function (id) {
		if (this.activeTabId != null && id != this.activeTabId) return null;
		var n = this.findTabNumber(id);
		var next;
		if (n > 0) {
			//TODO nick - maybe this method should be called _findPreviousTab?
			next = n-1;
		}else if (this.tabsInfo.length > 0) {
			next = 1;
		}
		
		return this.tabsInfo[next];
	},
	
	closeTab: function(id, event, options) {
		Event.stop(event);
		var tab = $(id);
		
		if (this.longRunningTabs.get(tab.componentId)) {
			return;
		}
		
		var result = true;
		if (this.ontabclosed) {
			//result = 
			new Function("event", "current" ,this.ontabclosed)(event, tab.name);
		}
		if (result) {
			var next = this._findNextTab(id);
			
			options['parameters'][this.id + ':close'] = tab.name;
			options['parameters'][tab.componentId] = null;
			
			if (next && !this.isTabAlreadyLoaded(next)) {
				options['parameters'][this.id + ':tabName'] = next.name;
				options['parameters'][next.componentId] = next.componentId;
				
				options['oncomplete'] = function () {
					this._closeTab(id, event);
					if (next && this.onTabChange(event, next)) {
						this.switchTabs(next, options);
					}	
				}.bind(this);
				
				if (next) {
					this.createTabContent(next);
					this.loadingTabs.set(next.id, true);
				}
				
			}else {
				this._closeTab(id, event);
					if (next && this.onTabChange(event, next)) {
						this.switchTabs(next, options);
					}
			}
		}
		
		this.loadTab(event, options);
		return false;
	},
	
	selectTab:  function (tab, active) {
		if (!tab) return false;
		var a = tab.linkDiv;		
		if (active) {
			if (a) {
				a.className = 'activeTabLink';
			}
			tab.className = TabPanel.findByType('ACTIVETAB', this.type);
		} else {
			if (a) {
				a.className = 'activeTabLink';
			}
			tab.className = TabPanel.findByType('INACTIVETAB', this.type);
		}
		var label = tab.labelDiv;
		if (this.isDynamic()) {
			label.className = active ? 'spn_sr_tabpanel_contentActive' : 'spn_sr_tabpanel_content';
		}
		if (isIE()) {
			var l = tab.leftDiv;
			var r = (this.isDynamic()) ? tab.rightDiv : tab.rightDiv.getElementsByTagName('div')[0];
			var lb = TabPanel.findByType((active) ? 'ACTIVETABLEFTBGD' : 'INACTIVETABLEFTBGD', this.type);
			var rb = TabPanel.findByType((active) ? 'ACTIVETABRIGHTBGD' : 'INACTIVETABRIGHTBGD', this.type);
			var lbh = TabPanel.findByType((active) ? 'ACTIVETABLEFTBGD_H' : 'INACTIVETABLEFTBGD_H', this.type);
			var rbh = TabPanel.findByType((active) ? 'ACTIVETABRIGHTBGD_H' : 'INACTIVETABRIGHTBGD_H', this.type);
			PNGFIX.pngChangeFilter(this.id, l, lb, lbh, this.srcPrefix);
			PNGFIX.pngChangeFilter(this.id, r, rb, rbh, this.srcPrefix);
		}
		if (active && tab.ontabenter) {
			this.fireEvent(tab.ontabenter);
		}
		//var handler = (active) ? tab.ontabenter : tab.ontableave;
		//this.fireEvent
	},
	
	fireEvent: function(handler, ev) {
	var e = ev;
	if (handler) {
		if (typeof(handler) == "function") {
			handler(e);
		}else {
			new Function("event", handler)(e);
		}
	}
	},
	
	findTabNumber: function (id) {
		for (var i = 0; i < this.tabsInfo.length ; i++) {
			if (this.tabsInfo[i] && id == this.tabsInfo[i].id) {
				return i;
			}
		}
		return null;
	},
	
	//TODO nick - what does env stand for?	
	switchTabs: function (env, options) {
		if (!env) {
			return;
		}
		var id = env.id;
		this.selectTab($(this.activeTabId), false);
		this.selectTab(env, true);
				
		if (this.activeTabId && $(this.getTabId(this.activeTabId))) 
			$(this.getTabId(this.activeTabId)).style.display = 'none';
		var tab = $(this.getTabId(id));
		if (tab) {
			this.showContent(tab);
		}
		this.activeTabId = id;
		this.activateTabListItem(id);
		this.onloaded(env);

	},
	
	showContent: function (tab) {
		if (this.isDynamic() && tab) {
			tab.style.visibility = 'hidden';
			tab.style.display = '';
			this._coverTabPanelContent(this.longRunningTabs.get(tab.id), tab);			
			tab.style.visibility = 'visible';
		}else if (tab) {
			this.contentContainer.style.visibility = 'visible';
			tab.style.display = '';
			if (this.scroller && !this.scroller.isScrollNeed()) {
				this.rightTop.className = 'sip-static-pnl-bg-right-top';
			}else {
				this.rightTop.className = 'sip-static-pnl-bg-right-top-noround';
			}
		}
	},
	
	getTabId: function (id) {
		return id = id.substring(0, id.lastIndexOf(':'));
	},
	

	createTabContent: function (tab) {
		if (!$(tab.componentId)) {
			var d = document.createElement('div');
			d.id = tab.componentId;
			d.style.display = 'none';
			this.content.appendChild(d);
		}
	},
	
	isTabAlreadyLoaded: function(env) {
		if (this.loadedTabs.get(env.id)) {
			return true;
		}
		return false;
	}, 
	
	_onTabClick: function() {
		if (!this.callbackParams) {
			return;
		}
		var event = this.callbackParams.event;
		var env =  this.callbackParams.env;
		var options = this.callbackParams.options;
		
		if (TabPanel.isTabActive(env)) {
			var c = $(env.componentId);
			if (c && !Element.visible(c)) {
				this.showContent(c);
			} 
			return false;
		}
			if (!this.onTabChange(event, env)) return false;
			
			if (!this.isTabAlreadyLoaded(env)) {
			
					this.createTabContent(env);
					
					if (this.ifMaxTabsLoaded() && !this.isLoading()) {
						var tab = this.getFirstLoadedTab();
						if (tab) {
							this.tabs2Delete.push(tab.id);
						}
					}
					
					options['oncomplete'] = function () {
						this.switchTabs(env, options);
					}.bind(this);
					
					this.loadingTabs.set(env.id, true);
					this.loadTab(event, options);
					
					if (env.ontableave) {
						this.fireEvent(env.ontableave, event);
					}
					
			}else {
				this.switchTabs(env, options);
			}
		this.callbackParams = null;
	}, 

	onTabClick: function(event, env, options) {
		this.callbackParams = {};
		this.callbackParams.event = A4J.AJAX.CloneObject(event, false);;
		this.callbackParams.env = env;
		this.callbackParams.options = options;
		
		this.scroller.activateTargetItem(this._onTabClick.bind(this), env);		
		return false;
	},
	
	isLoading: function () {
		return this.loadingTabs.size() != 0;
	},
	
	onloaded: function (tab) {
		this.loadingTabs.unset(tab.id);
		this.loadedTabs.set(tab.id,tab.name);
		this.holdLoadedTabName(tab.name);
		this.deleteTabs();
	},
	
	deleteTabs: function () {
		if (this.tabs2Delete && this.tabs2Delete.each) {
			this.tabs2Delete.each(function (id) {this.deleteTabContent($(id));}.bind(this));
		}
		this.tabs2Delete = new Array();
	},
	
	holdLoadedTabName: function (name) {
		var s = '';
		for (var i = 0; i < this.loadedTabs.size(); i++) {
			var n = this.loadedTabs.values()[i];
			if (n != name) {
				s += n + ',';
			}
		} 
		
		s += name;
		this.renderedTabNameHolder.value = s;
	},
	
	ifMaxTabsLoaded: function () {
		return this.maxTabsLoaded !=0 && this.loadedTabs.size() + this.loadingTabs.size() == this.maxTabsLoaded;
	},
	
	getFirstLoadedTab: function () {
		var tab;
		if (this.loadedTabs.size() > 0) {
			tab = $(this.loadedTabs.keys()[0]);
		}
		return tab;
	},
	
	loadTab: function(event, options) {
		if (!this.form) {
			this.form = A4J.AJAX.locateForm($(this.id));
		}
		var formId = (this.form) ? this.form.id : null; 

		//TODO nick - you mustn't do this. Parameters of A4J.AJAX.Submit can be changed in next release
		A4J.AJAX.Submit(this.containerId, formId, event, options);
	},
	
	deleteTab: function (tab) {
		Event.stopObserving(tab, 'mouseover');
		this.tabsInfo = this.tabsInfo.without(tab);
		tab.parentNode.removeChild(tab);
	},
	
	onTabChange: function (event, tab) {
		if (this.ontabchange) {
			return this.ontabchange();
		}
		return true;
	},
	
		
	initTabList : function(){
	
		var tabs = this.tabsInfo;
		var tabListHeader = this.tabListHeaderDiv;
		var tabListContent = this.tabListContentDiv;
		var tabList = this.tabListDiv;
		if (tabs) {
				
			for( var i=0; i<tabs.length; i++){
				this.addTabListItem(tabs[i]);
			}	
			
			Event.observe(tabListHeader, 'click', this.showHideTabList.bindAsEventListener(this));
			Event.observe(tabList, 'mouseout', this.hideTabList.bindAsEventListener(this));
		}
	},
	
	addTabListItem: function (tab) {
				var id = tab.id
				var tabLabel = tab.labelDiv;
				var divTag = document.createElement("div");
		
				Event.observe(divTag, 'click', new Function("event", "this.selectTabInList(event, '" + id + "')").bind(this));
				Event.observe(divTag, 'mouseover', this.tabListItemMouseOverListener.bindAsEventListener(this));
				Event.observe(divTag, 'mouseout', this.tabListItemMouseOutListener.bindAsEventListener(this));
				if (TabPanel.isTabActive(tab)){
					divTag.className = "tabListItemActive";
				}else{
					divTag.className = "tabListItemNormal";
				}
				divTag.id = id + ":tabListItem";
				divTag.innerHTML = tabLabel.innerHTML;
				divTag.tab = tab;
				this.tabListContentDiv.appendChild(divTag); 	
	},
	
	selectTabInList: function(e, tabId){
		this.hideTabList();
		
		//TODO nick - why do you call one event handler from another?
		this.tabListItemMouseOutListener(e);		
		var tab = $(tabId);

		//TODO nick - how many times will be tab.onclick() called: one or two?
		if(tab.onclick){
			tab.onclick();
		}
		return false;
	},
	
	initTabListPosition: function () {
		var contentHeight = this.content.offsetHeight;
		var tabListHeight = this.tabListContentDiv.scrollHeight;
		
		//TODO nick - what does 5 stand for?
		var height = (tabListHeight > contentHeight) ? contentHeight - 5 : tabListHeight;
		height = height + 'px';
	   	this.tabListContentDiv.style.height = height;
	   	this.tabListLeftDiv.style.height = height;
	},
	
	showHideTabList: function (e){
	this.initTabListPosition();
		if(this.tabListVisible){
			this.hideTabList();
		}else {
			//TODO nick - special method to show TabList?
			this.tabListDiv.style.visibility = 'visible';
			this.tabListVisible = true;
		}
	},
	
	hideTabList : function (e) {
	
		this.tabListDiv.style.visibility = 'hidden';
		this.tabListVisible = false;

	},
	
	tabListItemMouseOverListener: function(e){
		//TODO nick - what about IE?
		if (e && e.stopPropagation) {
			e.stopPropagation();
		}
		
		//TODO nick - what about IE?
		var item = e.target;
		if(item && item.tab){			
			var tab = item.tab;
             //TODO nick - apply hovered class to active tab too
			if (!TabPanel.isTabActive(tab)){
				item.className = "tabListItemHovered";
			}			
		}
	},
	
	tabListItemMouseOutListener: function(e){
		//TODO nick - what about IE?
		if (e && e.stopPropagation) {
			e.stopPropagation();
		}
		//TODO nick - what about IE?
		var item = e.target;
		if(item && item.tab){
			var tab = item.tab;
			                                          //TODO nick - apply normal class to active tab too
			if (!TabPanel.isTabActive(tab)){
				item.className = "tabListItemNormal";
			}
		}
	},
	
	activateTabListItem: function(tabId){
		var tabListItemId = "";
		tabListItemId += tabId;
		tabListItemId += ':tabListItem';
		var tabListContent = this.tabListContentDiv;
		if(tabListContent){
			for(var i=0; i<tabListContent.childNodes.length; i++){
				var el = tabListContent.childNodes[i];
				if(el.tagName.toLowerCase() == 'div'){
					if (el.id == tabListItemId){
						el.className = "tabListItemActive";
					}else{
						el.className = "tabListItemNormal";
					}
				} 
			} 
		} 
	},
	
	removeFromTabList: function (id) {
			var tabListItem = $(id + ':tabListItem');
			Event.stopObserving(tabListItem, 'click');
			Event.stopObserving(tabListItem, 'mouseover');
			Event.stopObserving(tabListItem, 'mouseout');
			tabListItem.parentNode.removeChild(tabListItem);
	},
	
	findTabByName: function (name) {
		for (var i=0; i < this.tabsInfo.length; i++) {
		var tab = this.tabsInfo[i];
			if (tab && tab.name == name) {	
				return tab;
			}
		}
	},
	
	setLongRunning: function (tabName) {
		if (!this.isDynamic()) {
			return; // YES?
		}
		var tab = this.findTabByName(tabName);
		if (tab) {
			this.longRunningTabs.set(tab.componentId, true);
			var img = $(tab.id + 'Close');
			if (img) {
				img.src = this.srcPrefix + TabPanel.IMAGES.LONG_RUNNING + this.srcPostfix;
			}
			if (TabPanel.isTabActive(tab)) {
				this._coverTabPanelContent(true, $(tab.componentId));
			}
		}
		this.holdLongRunningValues();
	},
	
	releseLongRunning: function (tabName) {
		if (!this.isDynamic()) {
			return; // YES?
		}
		var tab = this.findTabByName(tabName);
		if (tab) {
			this.longRunningTabs.unset(tab.componentId);
			var img = $(tab.id + 'Close');
			if (img) {
				img.src = this.srcPrefix + TabPanel.IMAGES.CLOSE + this.srcPostfix;
			}
			if (TabPanel.isTabActive(tab) && this.contentCoverage) {
				this._coverTabPanelContent(false);
			}	
		}
		if (TabPanel.isTabActive(tab)) {
			this.showContent($(tab.componentId));
		}
		this.holdLongRunningValues();
	},
	
	_coverTabPanelContent: function (show, tab) {
		if (show) {
			if (!this.contentCoverage) {
				var c = document.createElement('div');
				c.className = 'tabContentCoverage';
				if (tab) {
					c.style.height = tab.offsetHeight + 'px';
				}
				this.contentCoverage = c;
				Event.observe(c, 'focus', function (e) {return false});
				this.content.appendChild(c);
			}
		}else {
			if (this.contentCoverage) {
				this.contentCoverage.parentNode.removeChild(this.contentCoverage);
				this.contentCoverage = null;
			}
		}
	},
	
	holdLongRunningValues: function () {
		var s = '';
		for (var i = 0; i < this.longRunningTabs.size(); i++) {
			s += this.longRunningTabs.keys()[i] + ',';
		} 
		
		this.longRunningTabsHolder.value = s;
	},
	
	activateFirstTab: function (e) {
		var tab = this.tabsInfo.first();
		if (tab && tab.onclick) {
			tab.onclick(e);
		}
	},
	
	getClientIdPrefix: function () {
		return this.getTabId(this.id) + ':';
	},
	
	addNewTabs: function (e, args) {
		if (!args || (args && !args.tabsId)) {
			return;
		}
		var tabsIds = '';
		var param = '';
		var toDelete;
		
		if (args.tabsId.length > 0 && this.ifMaxTabsLoaded() && !this.isLoading()) {
				var tab = this.getFirstLoadedTab();
				this.loadingTabs.set(this.getClientIdPrefix() + args.tabsId[args.tabsId.length-1] + ':header', true);
				this.tabs2Delete.push(tab.id);
		}
		
		for (var i=0; i < args.tabsId.length; i++) {
			tabId = this.getClientIdPrefix() + args.tabsId[i];  //args.tabId;
			this.creatNewTab(tabId);
			tabsIds += tabId + ',';
			param += args.tabsId[i] + ',';
		}
		this.addNewTabScript(param);
		
	},
	
	onNewTabLoaded: function (clientIds, infos) {
		var active;
		for (var i=0; i < clientIds.length; i++) {
			var tabId = clientIds[i];
			var info = infos[i]; 
			var headerId = tabId + ":header"
			var header = $(headerId);
			if (header) {
				Element.show(header);
			}
			this.collectTabInfo(header, info);
			var n = this.findTabNumber(headerId);
			if (!n) {
				this.tabsInfo[this.tabsInfo.size()] = header;
				this.addTabListItem(header);
			} else {
				this.tabsInfo[n] = header; 
			}
			active = header;
		}
					
		this.scroller.items = this.tabsInfo;
		this.switchTabs(active);
		this._initScrolling();
	},
	
	creatNewTab: function (tabId) {
		if (!$(tabId)) {
			var headerId = tabId + ':header';
			if (!$(headerId)) {
				var header = document.createElement('div');
					Element.hide(header);
					header.id = headerId;
					this.tabs.firstChild.appendChild(header);
			}
			var content = document.createElement('div');
				Element.hide(content);
				content.id = tabId;
			this.content.appendChild(content);
		}
		
	}
});