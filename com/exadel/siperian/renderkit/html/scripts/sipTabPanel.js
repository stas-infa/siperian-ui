TabPanel = Class.create();

TabPanel.isTabActive = function (tab) {
		if (tab) {
			return Element.hasClassName(tab, "containerActive") || Element.hasClassName(tab, "sip-static-pnl-tabContainerActive");
		}
		return false;
};

TabPanel.CLASSES = {
	STATIC: {
		ARROWLEFT: 'sip-static-pnl-arrowLeft',
		ARROWRIGHT: 'sip-static-pnl-arrowRight',
		ACTIVETAB: 'sip-static-pnl-tabContainerActive',
		INACTIVETAB: 'sip-static-pnl-tabContainerInactive'
	},
	
	DYNAMIC: {
		ARROWLEFT: 'arrowLeft',
		ARROWRIGHT: 'arrowRight',
		ACTIVETAB: 'containerActive',
		INACTIVETAB: 'containerInActive'
	}
};

TabPanel.findByType = function (value, type) {
	return TabPanel.CLASSES[type.toUpperCase()][value];
};

TabPanel.IMAGES = {
	CLOSE : 'spn_close_btn.png',
	LONG_RUNNING: 'spn_ico_tab_process.gif'
};


TabPanel.STATIC= 'static';
TabPanel.DYNAMIC = 'dynamic';

Object.extend(TabPanel.prototype, {

	//TODO nick - decrease number of constructor arguments
	initialize: function(id, containerId, selectedTabName, maxTabWidth, ontabchange, ontabclosed, tabsInfo, type, addNewTabScript, highlightStyleClass, tabListHeight) {
		this.id = id;
		this.containerId = containerId;
		this.maxTabWidth = (maxTabWidth > 700) ? 700 : maxTabWidth;
		this.ontabchange = ontabchange;
		this.ontabclosed = ontabclosed;
		this.selectedTabName = selectedTabName;
		this.type = type;
		this.highlightStyleClass = highlightStyleClass;
		this.tabListHeight = tabListHeight;
		this.addNewTabScript=addNewTabScript;
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
		
		var selectedItem;
		var regex = new RegExp(/url\((.+)\)+/);
		var reg = regex.exec(this.left.getStyle('background-image'));
		if (reg && reg["1"]) {
			var src = reg["1"];
			src = src.replace("\"", "");
			this.srcPrefix = PNGFIX.getImagePrefix(src);
			this.srcPostfix = PNGFIX.initPostFix();
		}
	
		this.tabs2Delete = new Array();
		this.longRunningTabs = new Hash();
				 
		this.tabListVisible = false;
		this.maxLabelWidth = null;
		window.setTimeout(this.init.bind(this), 100);
		Event.observe(window, 'resize', this.onresize.bind(this));
		this.element.component = this;
		this["rich:destructor"] = "destroy";
	},
	
	destroy: function() {
		Event.stopObserving(window, 'resize', this.onresize);
		if (this.scroller) {		
			this.scroller.unbindControls(this.left);	
			this.scroller.unbindControls(this.right);
			delete this.scroller;
		}
		
		this.finishSwitchMode();

		delete this.tabsInfo;
		delete this.tabs2Delete;
		
		this.tabListHeaderDiv= null;
		this.tabListContentDiv = null;
		this.tabListLeftDiv = null;
		this.tabListDiv = null;
		this.left = null;
		this.right = null;
		this.tabsContainer = null;
		this.tabs = null;
		this.content = null;
		this.contentBg = null;
		this.contentContainer = null;
		this.element.component = null;
		this.element = null;
	
	},
	
	isReady: function() {
		return this.tabs.getWidth() != 0;
	},
	
	init: function () {
		if (this.tabsInfo && this.tabsInfo.length == 0) {
			return;
		}
		if (!this.isReady()) {
			clearTimeout(this.initTimeout);
			this.initTimeout = window.setTimeout(new Function("", "var d = $('"+this.id+"'); if(d && d.component && d.component.init) { d.component.init(); }"), 200);
			return;
		}
		this.collectTabsInfo(this.tabsInfo);
		this.initTabList();
		this.initScrolling();
		this.tabs.style.visibility = 'visible';
		this.content.style.visibility = 'visible';
		if (this.activeTabId) {
			this.showContent($(this.getTabId(this.activeTabId)));
		}
		/*A4J.AJAX.AddListener({
			onafterajax: function(req, event, data) {
				if(!$(this.id) && this.eventKeyPress) {
					Event.stopObserving(document, "keydown", this.eventKeyPress);		
				}
			}.bind(this)
		});*/

	},
	
	isDynamic: function () {
		return this.type == TabPanel.DYNAMIC;
	},
		
	collectTabsInfo: function (tabsInfo) {
		this.tabsInfo = new Array();
		var tabs = this.tabs.firstChild.childNodes;
		for (var i = 0; i < tabs.length; i++) {
			if (tabs[i].id.indexOf(':header') != -1) {
				var tabHeader = tabs[i];
				this.collectTabInfo(tabHeader, tabsInfo[tabHeader.id]);
			}
			//this.tabsInfo[i] = tabHeader;
		}
		this.tabsInfo = tabs;
	},
	
	collectTabInfo: function (tabHeader, info) {
			var componentId = info['0'];
			var id = tabHeader.id;
			tabHeader.name = info['1'];
			if (tabHeader.name == this.selectedTabName) {
				this.activeTabId = id;
			}
			tabHeader.title = '';
			tabHeader.componentId = componentId;
			tabHeader.ontabenter = info['5'];
			tabHeader.ontableave = info['4'];
			
			if (info['3']) {
				this.longRunningTabs.set(componentId, true);
			}
			
			this.initTabWidth(tabHeader); 
			Event.observe(tabHeader, 'mouseover',new Function("$('"+this.id+"').component.initTitle(this);"));
	},
	
	getLeftDiv: function (header) {
		var leftDiv = this.getTabId(header.id) + ":headerLeft";
		return document.getElementById(leftDiv);
	},
	
	getRightDiv: function (header) {
		var rightDiv = this.getTabId(header.id) + ":headerRight";
		return document.getElementById(rightDiv);
	},
	
	getLabelDiv: function(header) {
		var labelId = this.getTabId(header.id) + ":headerLabel";
		return document.getElementById(labelId);
	},
	
	getLinkDiv: function(header) {
		if (!this.isDynamic()) {
			return header; 
		}
		return header.firstChild;
	},
	
	calculateMaxLabelWidth: function(tab) {
		var maxTabWidth = this.maxTabWidth;
		if (!maxTabWidth) {
			return;
		}
		var left = this.getLeftDiv(tab);
		var right = this.getRightDiv(tab);
		var label = this.getLabelDiv(tab);
		
		this.getOffsets(label);
		
		if (this.isDynamic()) {
			this.maxLabelWidth = maxTabWidth - Richfaces.getComputedStyleSize(left, 'padding-left') - Richfaces.getComputedStyleSize(right, 'width') - Richfaces.getComputedStyleSize(label, 'margin-right');
		}else {
			this.maxLabelWidth = maxTabWidth - Richfaces.getComputedStyleSize(right, 'width');
		}
	},
	
	getOffsets: function (label) {
		if (!this.offsets) {
			var active = $(this.activeTabId);
			if (!active && !label) {
				return;
			}
			var label = label || this.getLabelDiv(active);
			this.offsets = {'label' : 
		 		{
					'marginRight' : Richfaces.getComputedStyleSize(label, 'margin-right') || Richfaces.getComputedStyleSize(label, 'marginRight'),
					'paddingLeft' : Richfaces.getComputedStyleSize(label, 'padding-left') || Richfaces.getComputedStyleSize(label, 'paddingLeft'), 
					'paddingRight' : Richfaces.getComputedStyleSize(label, 'padding-right') || Richfaces.getComputedStyleSize(label, 'paddingRight')
		 		},	
		 		'tabs' : 
		 		{
		 			'marginLeft' : Richfaces.getComputedStyleSize(this.tabs, 'margin-left') || Richfaces.getComputedStyleSize(this.tabs, 'marginLeft')
		 		}
			};
		}
		
		return this.offsets;
	},
	
	initTitle: function (elt) {
		var label = this.getLabelDiv(elt);
		if (label.cutOff && label.origText) {
			elt.title = label.origText;
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
			var actualWidth = this.getScrollWidth();
			if (!this.isDynamic()) {
				this.element.className = 'sip-static-pnl-searchContainer';
			}else {
				this.tabListHeaderDiv.style.visibility = 'visible';
			}
			if (this.tabsContainer && this.tabsContainer.firstChild) {
				this.tabsContainer.firstChild.style.width = actualWidth + 'px';
			}
			this.onleft(false);
			this.onright(true);
		}else {
			this.right.style.visibility = 'hidden';
			this.left.style.visibility = 'hidden';
			this.tabListHeaderDiv.style.visibility = 'hidden';
			if (this.tabsContainer && this.tabsContainer.firstChild) {
				this.tabsContainer.firstChild.style.width = '100%';
			}
			if (!this.isDynamic()) {
				this.element.className = 'sip-static-pnl-searchContainerNoScroll';
			}
		}
		this.scroller.init(true);
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
		var widthCorrection = 0;/*this.isDynamic() ? 0 : 3;*/// This is a right button's shadow width. Ignore it.
		this.tabs.getScrollWidth = this.getScrollWidth.bind(this);
		this.scroller = new hScroller(this.id,this.tabs, this.tabsInfo, {'onleft' : function(enabled) {this.onleft(enabled);}.bind(this), 'onright' : function(enabled) {this.onright(enabled);}.bind(this)}, widthCorrection);
		this._initScrolling();
	},
	
	getScrollWidth: function () {
		var marginLeft = this.getOffsets() ? this.getOffsets().tabs.marginLeft : (Richfaces.getComputedStyleSize(this.tabs, 'margin-left') || Richfaces.getComputedStyleSize(this.tabs, 'marginLeft'));
		return this.tabsContainer.offsetWidth - marginLeft - this.tabListHeaderDiv.getWidth() - this.right.getWidth() + 2; 
	},
	
	onresize: function () {
		if (this.scroller && !this.scroller.initialized) {
			return;
		}
	    clearTimeout(this.onResizeTimeoutId);
	    if (this.tabsContainer && this.tabsContainer.firstChild) {
	    	this.tabsContainer.firstChild.style.width = '100%';
	    }
	    this.onResizeTimeoutId = setTimeout(function(){
	        if (this.scroller) {
				this._initScrolling();
			}
	    }.bind(this), 2000);
	},
	
	onleft: function(enabled) {
		this.left.className = TabPanel.findByType('ARROWLEFT', this.type) + (enabled ? '' : 'Dis');
	},
	
	onright: function (enabled) {
	   this.right.className = TabPanel.findByType('ARROWRIGHT', this.type) + (enabled ? '' : 'Dis');
	},
	
	initTabWidth: function (tab) {
		if (!this.maxLabelWidth) {
			this.calculateMaxLabelWidth(tab);
		}
		var maxLabelWidth = this.maxLabelWidth;
		var active = TabPanel.isTabActive(tab);
		var label = this.getLabelDiv(tab);
		label.origText = (label.textContent || label.innerText);
		label.origWidthActive = label.scrollWidth;
			
		//label.origWidthInactive = label.origWidthInactive || label.scrollWidth;			
	
		var cutOff = false;
		if (label.origWidthActive > this.maxLabelWidth) {
			cutOff = true;
		}
					
		
		if (cutOff) {
			this.cutOffLabel(label, label.origWidthActive);
		}
	
		this.setActualLabelWidth(label, (cutOff) ? this.maxLabelWidth : label.offsetWidth);
		if (this.isDynamic()) {
			//label.className = 'spn_sr_tabpanel_content'; //TabPanel.isTabActive(tab) ? 'spn_sr_tabpanel_contentActive' : 'spn_sr_tabpanel_content';
			label.className = TabPanel.isTabActive(tab) ? 'spn_sr_tabpanel_contentActive' : 'spn_sr_tabpanel_content';
		}else {
			this.getLeftDiv(tab).className = 'sip-static-pnl-left';
		}
		label.style.overflow = 'hidden';
		
	},
	
	adjustTabWidth: function (tab) {
		var label = this.getLabelDiv(tab);
		var active = TabPanel.isTabActive(tab);
		this.cutOffLabel(label, active ? label.origWidthActive : label.origWidthInactive);
	},
	
	setActualLabelWidth: function (label, width) {
		/**if (!this.isDynamic()) {
			width = width - this.getOffsets(label).label.paddingLeft - this.getOffsets(label).label.paddingRight;
		}*/
		
		
		
		if(width) {
			label.style.width = width +'px';
		}
		
	},
	
	cutOffLabel: function (label, origWidth) {
		if (!this.maxLabelWidth) {
			return;
		}
		var k = (this.maxLabelWidth/origWidth);
		var l = label.origText.length;
		var text;
		if (k < 1) {
				l = l * k - '...'.length;
				if (l > 0) {
					text = label.origText.substring(0, l) + '...';
					label.cutOff = true;
				}		
		} else {
			text = label.origText;
		}
				
		if (label.innerHTML) {
			var c = this.findTextContentHolder(label, label.origText);
			if (c && text) {
				c.innerHTML = text;
			}
		}
	},
	
	findTextContentHolder: function (label, _text) {
		if (label.firstChild && label.firstChild.data && label.firstChild.data == _text) {
			return label;
		}
		var children = Element.select(label, '*');
		for (var i = 0; i < children.length; i++) {
			var c = children[i];
			if (c && c.innerHTML && c.innerHTML == _text) {
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
		tab = null;
	},
	
	deleteTabContent: function(tab) {
		if (!tab) {
			return;
		}
		var contentTab = $(tab.componentId);
		if (contentTab) {
			if (window.RichFaces && window.RichFaces.Memory) {
				window.RichFaces.Memory.clean(contentTab, true);
			}
			contentTab.innerHTML = '';
			contentTab.parentNode.removeChild(contentTab);
			contentTab = null;
		}
		
	},
	
	_findNextTab: function (id) {
		if (this.activeTabId != null && id != this.activeTabId) return null;
		var n = this.findTabNumber(id);
		var next;
		if (n > 0) {
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
			
			if(next) {
				options['parameters'][this.id + ':tabName'] = next.name;
				options['parameters'][next.componentId] = next.componentId;
			}
			
			options['onbeforedomupdate'] = function (request, event, data) {
				if (next && !$(next.componentId)) {
					var content = document.createElement('div');
					content.id = next.componentId;
					content.noloaded = true;
					this.content.appendChild(content);
				}
			}.bind(this);
				
			options['oncomplete'] = function (request, event, data) {
			var content = next ? $(next.componentId) : null;
			if (!data) {
				if (content && content.noloaded) {
					content.innerHTML = '';
					content.parentNode.removeChild(content);
					content = null;
				}
				return;
			}
			this._closeTab(id, event);
				if (next && this.onTabChange(event, next)) {
					this.switchTabs(next, options);
				}	
			}.bind(this);
		}
		
		this.loadTab(event, options);
		return false;
	},
	
	selectTab:  function (tab, active) {
		if (!tab) return false;
		var a = this.getLinkDiv(tab);
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
		var label = this.getLabelDiv(tab);
		if (this.isDynamic()) {
			label.className = active ? 'spn_sr_tabpanel_contentActive' : 'spn_sr_tabpanel_content';
		}
		
		//this.adjustTabWidth(tab);
	
		if (active && tab.ontabenter) {
			this.fireEvent(tab.ontabenter);
		}

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
				
		var old = this.activeTabId ? $(this.getTabId(this.activeTabId)) : null;	
		if (old) 
			old.style.display = 'none';
			
		var tab = $(this.getTabId(id));
	
		if (tab) {
			this.showContent(tab);
		}
		if (old) {
			this.deleteTabContent($(this.activeTabId));
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
		return id.substring(0, id.lastIndexOf(':'));
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
			this.finishSwitchMode();
			return false;
		}
			if (!this.onTabChange(event, env)) return false;
							
			var content = $(env.componentId);
			if (content) {
				content.noloaded = true;
			}
			options['oncomplete'] = function (request, event, data) {
				var content = $(env.componentId);
				if (content && content.noloaded) {
					content.innerHTML = '';
					content.parentNode.removeChild(content);
					content = null;
					return;
				}
				this.switchTabs(env, options);
			}.bind(this);
			
			options['onbeforedomupdate'] = function (request, event, data) {
				if (!$(env.componentId)) {
					var content = document.createElement('div');
					content.id = env.componentId;
					content.noloaded = true;
					this.content.appendChild(content);
					content = null;
				}
			}.bind(this);
					
			this.loadTab(event, options);
					
			if (env.ontableave) {
				this.fireEvent(env.ontableave, event);
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
	
	onloaded: function (tab) {
		this.deleteTabs();
		this.finishSwitchMode();
	},
	
	deleteTabs: function () {
		if (this.tabs2Delete && this.tabs2Delete.each) {
			this.tabs2Delete.each(function (id) {this.deleteTabContent($(id));}.bind(this));
		}
		this.tabs2Delete = new Array();
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
		tab.innerHTML = '';
		tab.parentNode.removeChild(tab);
		tab = null;
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
				var tabLabel = this.getLabelDiv(tab);
				var alreadyInList=false;
				
				var currentElements = this.tabListContentDiv.getElementsByTagName('div')
				for (var i = 0; i < currentElements.length; i++) {
					var c = currentElements[i];
					if (c && c.innerHTML && c.innerHTML == tabLabel.innerHTML) {
						alreadyInList = true;
					}
				}		
				if (!alreadyInList){
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
				}
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
		var tabListHeight = this.tabListHeight;
		var height = null;
		
		if(tabListHeight == 0) {
			var tabListHeight = this.tabListContentDiv.scrollHeight;
			var contentHeight = this.content.offsetHeight;
			tabListHeight = contentHeight;
			height = (tabListHeight > contentHeight) ? contentHeight - 5 : tabListHeight;
			height = height + 'px';
		} else {
			height = tabListHeight + 'px';
		}
		
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
				this.selectedItem = item;
			}			
		}
	},
	
	isWithin:function (event, element){
		var within = false;
		Event.extend(event);

		var targetElement = event.relatedTarget;

		try {
			if (targetElement && targetElement.className=="anonymous-div")
			 return false;
		} catch (e) {
			return false;
		}
		
		while (targetElement && targetElement.nodeType!=1) 
		{
			targetElement = targetElement.parentNode;
		}
		
		if (targetElement) {
			within = targetElement == element || 
				$(targetElement).descendantOf(element);
			}

		return within;
	},			
	tabListItemMouseOutListener: function(e){
		//TODO nick - what about IE?
		if (e && e.stopPropagation) {
			e.stopPropagation();
		}
		var item = this.selectedItem;
		if (this.isWithin(e, item)){
			return ;
		}
		//TODO nick - what about IE?
		if(item && item.tab){
			var tab = item.tab;
			                                          //TODO nick - apply normal class to active tab too
			if (!TabPanel.isTabActive(tab)){
				item.className = "tabListItemNormal";
			}
		}
		this.selectedItem=null;
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
			tabListItem.innerHTML = null;
			tabListItem.parentNode.removeChild(tabListItem);
			tabListItem = null;
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
	},
	
	emptyFocusObserver: function(e) { return false; },
	
	_coverTabPanelContent: function (show, tab) {
		if (show) {
			if (!this.contentCoverage) {
				var c = document.createElement('div');
				c.className = 'tabContentCoverage';
				if (tab) {
					c.style.height = tab.offsetHeight + 'px';
				}
				this.contentCoverage = c;
				Event.observe(this.contentCoverage, "focus", this.emptyFocusObserver);
				this.content.appendChild(c);
			}
		}else {
			if (this.contentCoverage) {
				Event.stopObserving(this.contentCoverage, "focus", this.emptyFocusObserver); 
				this.contentCoverage.parentNode.removeChild(this.contentCoverage);
				this.contentCoverage = null;
			}
		}
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
		/*
		for (var i=0; i < args.tabsId.length; i++) {
			tabId = this.getClientIdPrefix() + args.tabsId[i];  //args.tabId;
			this.creatNewTab(tabId);
			tabsIds += tabId + ',';
			param += args.tabsId[i] + ',';
		}*/
		
		this.addNewTabScript(param);
		
	},
	
	onNewTabLoaded: function (clientIds, infos) {
		var active;
		var _activeTabId = this.activeTabId;
		for (var i=0; i < clientIds.length; i++) {
			var tabId = clientIds[i];
			var info = infos[i]; 
			var headerId = tabId + ":header"
			var header = $(headerId);
			if (header) {
				Element.show(header);
			}
			this.collectTabInfo(header, info);
			this.addTabListItem(header);

			if ($(tabId)) {
				active = header;
			}
		}
		this.activeTabId = _activeTabId;			
		this.scroller.items = this.tabs.firstChild.childNodes;
		this.switchTabs(active);
		this._initScrolling();
	},
	
	creatNewTabs: function (tabsId) {
		if (!tabsId) {
			return;
		}
		for (var i=0; i<tabsId.length;i++) {
			var tabId = tabsId[i];
			var headerId = tabId + ':header';
			if (!$(headerId)) {
				var header = document.createElement('div');
					Element.hide(header);
					header.id = headerId;
					this.tabs.firstChild.appendChild(header);
			}
		}
		if (!$(tabId)) {
			var content = document.createElement('div');
			Element.hide(content);
			content.id = tabId;
			this.content.appendChild(content);
		}

		
	},
	
	startSwitchMode: function () {
		var active = $(this.activeTabId);
		if (this.switchMode || !active) {
			return;
		}
		
		if (active.click) {
			$(active).click();
		}

		this.highlightTabId = this.activeTabId;
		this.highlightTab(active, true);
		
		this.eventKeyPress = this.processKeyDown.bindAsEventListener(this);
		Event.observe(document, "keydown", this.eventKeyPress);
		
		this.eventLostFocus = this.processLostFocus.bindAsEventListener(this);
		Event.observe(document, "click", this.eventLostFocus);

		this.eventPreventLostFocus = this.processPreventLostFocus.bindAsEventListener(this);
		Event.observe(this.element, "click", this.eventPreventLostFocus);
		
		this.switchModeAjaxListener = new A4J.AJAX.Listener(function(req, event, data) {
			if(!$(this.id) && this.eventKeyPress) {
				Event.stopObserving(document, "keydown", this.eventKeyPress);		
			}
		}.bind(this));
		
		A4J.AJAX.AddListener(this.switchModeAjaxListener);
		
		this.switchMode = true;
		this.inFocus = true;
		this.switcherLocked = false;
	},
	
	removeKeyListeners: function() {
		Event.stopObserving(document, "keydown", this.eventKeyPress);
		Event.stopObserving(document, "click", this.eventLostFocus);
		Event.stopObserving(this.element, "click", this.eventPreventLostFocus);
		if (this.switchModeAjaxListener) {
			A4J.AJAX.removeListener(this.switchModeAjaxListener);
		}
	},
	
	processPreventLostFocus: function() {
		this.inFocus = true;
		this.preventLostFocus = true;
	},

	processLostFocus: function() {
		if (!this.preventLostFocus) {
			this.lostFocus();
			this.finishSwitchMode();
		} else {
			this.preventLostFocus = false;
		}
	},
	
	finishSwitchMode: function () {
		if (this.switchMode) {
			this.lostFocus();
			this.removeKeyListeners();
			this.switchMode = false;
			var active = $(this.highlightTabId);
			if (active) {
				this.highlightTab(active, false);
			}
			this.highlightTabId = null;
		}
	},
	
	lostFocus: function() {
		this.inFocus = false;
	},
	
	processKeyDown: function(event) {
		switch (event.keyCode || event.charCode) {
			case Event.KEY_LEFT:
				if (this.inFocus && !this.scroller.scrollMutex) {
					var active = $(this.highlightTabId);
					var prev = active ? active.previousSibling : null;
					if (active && prev) {
						this.highlightTab(active, false);
						this.highlightTab(prev, true);
						this.highlightTabId = prev.id;
					}
				}
				break;
			case Event.KEY_RIGHT:
				if (this.inFocus && !this.scroller.scrollMutex) {
					var active = $(this.highlightTabId);
					var next = active ? active.nextSibling : null;
					if (active && next) {
						this.highlightTab(active, false);
						this.highlightTab(next, true);
						this.highlightTabId = next.id;
					}
				}
				break;
			case 13:
				if (this.inFocus  && !this.scroller.scrollMutex) {
					var active = $(this.highlightTabId);
					if (active && active.onclick) {
						active.onclick(event);
					}
				}
				break;
			case Event.KEY_TAB:
				this.finishSwitchMode();
				break;
			case Event.KEY_ESC:
				this.finishSwitchMode();
				break;
		}
	},
	
	highlightTab: function (tab, f) {
		var cssClass = this.isDynamic() ? (TabPanel.isTabActive(tab) ? 'containerActiveSelected' : 'containerInActiveSelected') : (TabPanel.isTabActive(tab) ? 'sip-static-pnl-tabContainerActiveSelected' : 'sip-static-pnl-tabContainerInactiveSelected');
		if (f) {
			this.scroller.activateTargetItem(function () {$(tab).addClassName(cssClass);}, tab);
		}else {
			$(tab).removeClassName(cssClass);
		}
	}
});