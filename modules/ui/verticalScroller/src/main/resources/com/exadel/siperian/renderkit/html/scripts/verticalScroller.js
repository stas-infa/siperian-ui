VerticalScroller = Class.create();

Object.extend(VerticalScroller.prototype, {

	noScroll: false,

	initialize: function(id, submitFunction, currentPage, imageSrc) {
		this.id = id;
		this.submitFunction = submitFunction;
		this.tabs = $(id + ':tabs');
		this.tabs.scrollTop = 0;
		this.up = $(id + ':up');
		this.down = $(id + ':down');
		this.inner = $(id + ':inner');
		this["rich:destructor"] = "destroy";
		this.srcPrefix = PNGFIX.getImagePrefix(imageSrc);
		
		//TODO nick - what if user uses his own DIV elements inside VDS facets
		this.items = this.tabs.getElementsByTagName('div');
		this.currentPage = currentPage;
		currentPage--;
		this.initScrolling();
		PNGFIX.pngfixElements(this.inner);
		
		//TODO nick - what is the problem of getting right value without -- ?
		if (currentPage >= 0 && this.items  && this.items.length > currentPage) {
			this.currentItem = $(this.items[currentPage]);
		}
		
		$(this.id).component = this;
	},
	destroy: function() {	
		$(this.id).component = null;
		this.currentItem = null;
		
		delete this.scroller;
		
		this.items = null;
		this.inner = null;
		this.down.stopObserving();		
		this.down = null;
		this.up.stopObserving();
		this.up = null;
		this.tabs = null;				
	},
	onscroll: function (event) {
		this.callbackParams = {};

		//TODO nick - what for CloneObject is called here?
		this.callbackParams.event = A4J.AJAX.CloneObject(event, false);
		this.callbackParams.page = event.memo.page;
		this.scroller.activateTargetItem(this._onscroll.bind(this), event.memo.page);
	},
	
	_onscroll: function() {
		if (!this.callbackParams) {
			return false;
		}
		var event = this.callbackParams.event;
		
		//TODO nick - how will this work in IE?
		var target = event.target;
		if (!this.isCurrentPage(target)) {
			this.submitFunction(event);
			this.selectPage(this.currentItem, false);
			this.selectPage(target, true);
		}
	},
	
	selectPage: function (env, current) {
		if (current) {
			
			//TODO nick - proper camel case for tabInActive is tabInactive
			this.changeClassName(env, 'tabInActive', 'tabActive');
			this.currentItem = env;
		}else {
			this.changeClassName(env, 'tabActive', 'tabInActive');
		}
		if (isIE()) {
			//TODO nick - externalize this stuff as constants
			PNGFIX.pngChangeFilter(this.id, env, (current) ? 'tab-active.png' : 'tab-inactive.png', '20px', this.srcPrefix);
		}
	},
	
	isCurrentPage: function (env) {
		return $(env).hasClassName('tabActive');
	},
	
	ontop: function (notIsOnTop) {
			if (this.noScroll) return;
			if (notIsOnTop) {
				this.changeClassName(this.up, 'arrowTopInActive', 'arrowTopActive');
			} else {
				this.changeClassName(this.up, 'arrowTopActive', 'arrowTopInActive');
			}
			if (isIE()) {
				//TODO nick - externalize this stuff as constants
				PNGFIX.pngChangeFilter(this.id, this.up, (notIsOnTop) ? 'arrow-top-active.png' : 'arrow-top-inactive.png', '16px', this.srcPrefix);
			}
	},
	
	onbottom: function (notIsOnBottom) {
			if (this.noScroll) return;
			if (notIsOnBottom) {
				this.changeClassName(this.down, 'arrowBottomInActive', 'arrowBottomActive');
			} else {
				this.changeClassName(this.down, 'arrowBottomActive', 'arrowBottomInActive');
			}
			if (isIE()) {
				//TODO nick - externalize this stuff as constants
				PNGFIX.pngChangeFilter(this.id, this.down, (notIsOnBottom) ? 'arrow-bottom-active.png' : 'arrow-bottom-inactive.png', '16px', this.srcPrefix);
			}
			
	},
	
	changeClassName: function (env, old, newClass) {
		if (env.hasClassName(old)) {
					env.removeClassName(old);
		}
		env.addClassName(newClass);
	},
			
	initScrolling: function() {
		this.scroller = new vScroller(this.id, this.tabs, this.items, { 'ontop': function (notIsOnTop) { this.ontop(notIsOnTop); }.bind(this), 'onbottom': function (notIsOnBottom) { this.onbottom(notIsOnBottom); }.bind(this) });
	
		if (this.scroller.isScrollNeed()) {
			this.noScroll = false;
			this.scroller.bindControl(this.down, vScroller.CONTROL.FORWARD);
			this.scroller.bindControl(this.up , vScroller.CONTROL.BACK);
			this.inner.className = 'verticalTabs';
		}else {
			this.noScroll = true;
			
			//TODO nick - should we unbind controls? Looks like they were not bound
			this.scroller.unbindControls(this.down);
			this.scroller.unbindControls(this.up);
			this.inner.className = 'verticalTabsNoScroll';
			if (isIE()) {
				//TODO nick - handle up button?
				this.down.style.backgroundImage="";
				this.down.style.filter=undefined;
				PNGFIX._pngfix(this.down);
			}
		}
		window.setTimeout(function () { this.scroller.activateTargetItem(null, this.currentPage); }.bind(this), 300);
	}
	
});