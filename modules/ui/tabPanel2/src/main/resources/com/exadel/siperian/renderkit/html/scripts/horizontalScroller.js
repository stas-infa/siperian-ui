hScroller = Class.create();

hScroller.CONTROL = {}
hScroller.CONTROL.FORWARD = '1'; 
hScroller.CONTROL.BACK = '2';


//TODO nick - the same as in concord\trunk\modules\ui\verticalScroller\src\main\resources\com\exadel\siperian\renderkit\html\scripts\verticalScroller.js
Object.extend(hScroller.prototype, {
	
	scrollStep: 0,
	
	scrollRange: 0,
	
	scrollingStep: 30,

	scrollMutex: false,
	
	initialized: false,

	itemsNCache: {},

	initialize: function(id, target, items, handlers, widthCorrection) {
		this.id = id;
		this.target = target;
		this.items = items;
		this.handlers = handlers;
		
		/* this value corrects items container's width  
		 * in case if real width of container depends on 
		 * absolutely positioned elements over container
		 */   
		this.widthCorrection = widthCorrection;
		this.itemsNCache = {};
		this.initialized = false;
	},
	
	getTargetWidth: function() {
		var targetWidth = this.target.getScrollWidth();
		if(this.widthCorrection) {
			targetWidth = targetWidth + this.widthCorrection;
		}
		this.targetWidth = targetWidth;
		return targetWidth;
	},
	
	init: function (force) {
		if (!this.initialized || force) {
			this.targetWidth = this.getTargetWidth();
			this.calculateScrollWidth();
			this.calculateScrollPosition();
			this.initialized = true;
			this.scrollMutex = false;
		}
	},
	
	isReady: function() {
		return this.target.offsetWidth != 0;
	},
	
	isScrollNeed: function () {
		if (!this.isReady()) {
			return false;
		}
		this.calculateScrollWidth();
		return (this.target.offsetWidth < this.scrollWidth);
	},
		
	calculateScrollWidth: function () {
		var w = 0;
		var l = this.items.length; 
		for (var i = 0; i < l; i++) {
		    var item = this.items[i]; 
			if (!item) {
				continue;
			}
			if (item) {			
					item.start  = w;
					w = w + item.offsetWidth;
					if (!isIEBrowser()) {
						w += Richfaces.getComputedStyleSize(item, 'margin-left');
						w += Richfaces.getComputedStyleSize(item, 'margin-right');
					}else {
						ml = Richfaces.getComputedStyleSize(item, 'marginLeft');
						mr = Richfaces.getComputedStyleSize(item, 'marginRight');
						w += (ml) ? ml : 0;
						w += (mr) ? mr : 0;
					}
 					item.end  = w;
			}
		}
		this.scrollWidth = w;
	},
	
	calculateScrollPosition: function () {
		var l = this.target.scrollLeft;
		var r = l + this.targetWidth;
		this.left = 0;
		var length = this.items.length; 
		for (var i = 0; i < length; i++) {
		    var item = this.items[i]; 
			if (!item) {
				continue;
			}
			if (item.start <= l) {
				this.left = i;
			}
			if (item.start <= r) {
				this.right = i;
			}
		}
		if (l == 0 && this.handlers['onleft']) {
			this.handlers['onleft'](false);
		}else if (this.handlers['onleft']) {
			this.handlers['onleft'](true);
		}
		if (r ==  this.scrollWidth && this.handlers['onright']) {
			this.handlers['onright'](false);
		}else if (this.handlers['onright']) {
			this.handlers['onright'](true);
		}
	},

	
	forward: function () {
		
		var l = this.target.scrollLeft;
		var r = l + this.targetWidth;
		if (r < this.scrollWidth)
		{
			var item = this.items[this.right];
			var end;
			if (item.end > r) {
				end = item.end - this.targetWidth;
			}else if (this.right + 1 < this.items.length) {
				end = this.items[this.right + 1].end - this.targetWidth;
			}
			this._scroll(end);
			
		}
	},
	
	back: function () {
		var l = this.target.scrollLeft;
		var r = l + this.targetWidth;
		if (l > 0)
		{
			var item = this.items[this.left];
			var start;
			if (item.start < l) {
				start = item.start;
			}else if (this.left - 1 >= 0) {
				start = this.items[this.left - 1].start;
			}
			this._scroll(start);
			
		}
	},
	
	
	_scroll: function (p) {
		if (this.scrollMutex || Object.isUndefined(p))
		{
			return;
		}
		this.scrollMutex = true;

		if (this.scrollInterval)
		{
			return;
		} 
		if (p > this.target.scrollLeft) {
			this.scrollInterval = window.setInterval(function () { this._scrollF(p)}.bind(this), 50);
		}else {
			this.scrollInterval = window.setInterval(function () { this._scrollB(p)}.bind(this), 50);
		}

	},

	_scrollF: function (end) {
				if (end < 0) {
					this._finish();
					return;
				}
				var i =  this.target.scrollLeft;
				i = i + this.scrollingStep;
				if (i >= end)
				{
					this.target.scrollLeft = end;
					this.calculateScrollPosition();
					this._finish();
					return;
				}else {
					this.target.scrollLeft = i;
				}
	},

	_scrollB: function (start) {
			if (start < 0) {
					this._finish();
					return;
			}
			var i =  this.target.scrollLeft
			i = i - this.scrollingStep;
			if (i <= start)
			{
				this.target.scrollLeft = start;
				this.calculateScrollPosition();
				this._finish();
				return;
			}else {
				this.target.scrollLeft = i;
			
			}

	},
	
	_finish: function () {
		if (this.scrollInterval) {
			window.clearInterval(this.scrollInterval);
		}
		this.scrollInterval = null;
		this.scrollMutex = false;
		this.scrollingStep = 30;
		if (this.callback) {
			this.callback();
		}
	},

	_doScrolling: function (forward) {
		var scrollLeft = this.target.scrollLeft;
			if (forward)
			{
				if (scrollLeft + this.scrollingStep < this.scrollWidth - this.targetWidth)
				{
					this.target.scrollLeft = scrollLeft + this.scrollingStep;	
				}else {
					this.target.scrollLeft = this.scrollWidth - this.targetWidth;
				}
			}else {
				if (scrollLeft - this.scrollingStep > 0 )
				{
					this.target.scrollLeft = scrollLeft - this.scrollingStep;	
				}else {
					this.target.scrollLeft = 0;
				}
			}
	},

	fireScrolling: function (forward) {
		if (this.intervalId)
		{
			return;
		}
		if (forward) {
			this.intervalId = window.setInterval(function() { this._doScrolling(true); }.bind(this), 50);
		} else {
			this.intervalId = window.setInterval(function() { this._doScrolling(false); }.bind(this), 50);
		}
	},	
	
	startScrolling: function(e, forward) {
		this.init();
		if (this.scrollMutex || this.timeoutId)
		{
			return;
		}
		this.scrollMutex = true;
		//if (e.preventDefault) e.preventDefault();
		this.timeoutId = window.setTimeout(function() { this.fireScrolling(forward); }.bind(this), 200);
	},
	
	endScrolling: function (forward) {
		window.clearTimeout(this.timeoutId);
		window.clearInterval(this.intervalId );
		this.intervalId = null;
		this.timeoutId = null;
		this.calculateScrollPosition();
		this.scrollMutex = false;
	},
	
	bindControl: function (env, controlType) {
		this.unbindControls(env, controlType);
		if (hScroller.CONTROL.FORWARD == controlType) {
			Event.observe(env, 'click', this.forward.bindAsEventListener(this));
			Event.observe(env, 'mousedown', function (e) { this.startScrolling(e, true); }.bind(this));
			Event.observe(env, 'mouseup', function () { this.endScrolling(true); }.bind(this));
			Event.observe(env, 'mouseout', function () { this.endScrolling(true); }.bind(this));
		}else {
			Event.observe(env, 'click', this.back.bindAsEventListener(this));
			Event.observe(env, 'mousedown', function (e) { this.startScrolling(e, false); }.bind(this));
			Event.observe(env, 'mouseup', function () { this.endScrolling(false); }.bind(this));
			Event.observe(env, 'mouseout', function () { this.endScrolling(false); }.bind(this));
		}
		Event.observe(env, 'drag', function () {return false;});
		
	},
	
	activateTargetItem: function (callback, env) {

		this.callback = callback;
		
		if (this.isScrollNeed()) {
		
			var n = this.findTargetNumber(env); 
			var start = this.items[n].start;
			var end = this.items[n].end;
			
			var l = this.target.scrollLeft;
			var r = l + this.targetWidth;
	
			if (start < l) {
				this.scrollingStep = (l-start)/5 > 30 ? (l-start)/5 : 30;
				this._scroll(start);
			} else if (end > r) {
				this.scrollingStep = (end-r)/5 > 30 ? (end-r)/5 : 30;
				this._scroll(end - this.targetWidth);
			}else if (this.callback) {
				this.callback();
				this.callback = null;
			}	
		}else {	
			try {
				this.target.scrollLeft = 0;
			}catch(e){};
			if (this.callback) {
				this.callback();
				this.callback = null;
			}	
		}
		
	},
	
	findTargetNumber: function(env) {
		if (this.itemsNCache[env.id])
		{
			return this.itemsNCache[env.id];
		}
		for (var i = 0; i < this.items.length; i++) {
			if (this.items[i] && env.id == this.items[i].id) {
				this.itemsNCache[env.id] = i;
				return i;
			}
			
		}
	},
	
	correctScroll: function (callback) {
		this.callback = callback;
		if ((this.scrollWidth - this.targetWidth) < this.target.scrollLeft) {
				this._scroll(this.scrollWidth - this.targetWidth);
		}else {
			this._finish();
		}
	},
	
	unbindControls: function (env, controlType) {
			Event.stopObserving(env, 'click');
			Event.stopObserving(env, 'mousedown');
			Event.stopObserving(env, 'mouseup');
			Event.stopObserving(env, 'mouseout');
			Event.stopObserving(env, 'drag');
	}

	
	
});