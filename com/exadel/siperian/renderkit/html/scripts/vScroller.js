vScroller = Class.create();

vScroller.CONTROL = {};
vScroller.CONTROL.FORWARD = '1'; 
vScroller.CONTROL.BACK = '2';
vScroller.SCROLLING_STEP = 10;
vScroller.SCROLLING_DELAY = 200;
vScroller.SCROLLING_SPEED = 50;
vScroller.INC_VALUE = 9;
	
	
Object.extend(vScroller.prototype, {
	
	scrollingStep: vScroller.SCROLLING_STEP,

	scrollMutex: false,
	
	initialize: function(id, target, items, handlers) {
		this.id = id;
		this.target = target;
		this.items = items;
		this.handlers = handlers;
		this["rich:destructor"] = "destroy";

		this.initialized = false;		
		
	},
	destroy: function () {		
		this.target = null;
		this.items = null;
		this.handlers = null;		
	
	},
	
	init: function () {
		this.targetHeight = this.target.getHeight();// - (isIE() ? Richfaces.getComputedStyleSize(this.target, 'marginTop') : Richfaces.getComputedStyleSize(this.target, 'margin-top'));
		
		if(this.targetHeight == 0 ){
			this.targetHeight = Richfaces.getComputedStyleSize(this.target, this.isAllIE()?'marginTop':'margin-top');			
			this.targetHeight+=vScroller.INC_VALUE;
		}
		this.calculateScrollHeight();
		this.calculateScrollPosition();
		this.initialized = true;
	},
	isAllIE: function(){
		return Richfaces.browser.isIE;
	},
	isScrollNeed: function () {
		this.targetHeight = this.target.getHeight();// - (isIE() ? Richfaces.getComputedStyleSize(this.target, 'marginTop') : Richfaces.getComputedStyleSize(this.target, 'margin-top'));
	
		if(this.targetHeight == 0 ){			
			this.targetHeight = Richfaces.getComputedStyleSize(this.target, this.isAllIE()?'marginTop':'margin-top');
			this.targetHeight+=vScroller.INC_VALUE;
		}

		this.calculateScrollHeight();
		this.calculateScrollPosition();
//		alert("this.targetHeight = "+this.targetHeight+"this.scrollWidth= "+this.scrollWidth);
		return (this.targetHeight < this.scrollHeight);
	},
		
	calculateScrollHeight: function () {
		var w = 0;
		for (var i = 0; i < this.items.length; i++) {
			if (!this.items[i]) {
				continue;
			}
			var item = $(this.items[i]);
			item.start  = w;
			w += item.offsetHeight;
			w += Richfaces.getComputedStyleSize(item, this.isAllIE()?'marginTop':'margin-top');
			w += Richfaces.getComputedStyleSize(item, this.isAllIE()?'marginBottom':'margin-bottom');
			item.end  = w;
		} 
		
		this.scrollHeight = w;
	},
	
	calculateScrollPosition: function () {
		var l = this.target.scrollTop;
		var r = l + this.targetHeight;
		this.top = 0;
		for (var i = 0; i < this.items.length; i++) {
			if (!this.items[i]) {
				continue;
			}
			if (this.items[i].start <= l) {
				this.top = i;
			}
			if (this.items[i].start <= r) {
				this.bottom = i;
				
				//TODO nick - break here
			}
		}
		if (l == 0 && this.handlers['ontop']) {
			this.handlers['ontop'](false);
		}else if (this.handlers['ontop']) {
			this.handlers['ontop'](true);
		}
		
		//TODO nick - what 3 stands for?
		if (Richfaces.browser.isFF3) {
			r+=3;
		}
		
		if (r ==  this.scrollHeight && this.handlers['onbottom']) {
			this.handlers['onbottom'](false);
		}else if (this.handlers['onbottom']) {
			this.handlers['onbottom'](true);
		} 
	},

	
	forward: function () {
		
		var l = this.target.scrollTop;
		var r = l + this.targetHeight;
		if (r < this.scrollHeight)
		{
			var item = this.items[this.bottom];
			var end;
			if (item.end > r) {
				end = item.end - this.targetHeight;
			}else if (this.bottom + 1 < this.items.length) {
				end = this.items[this.bottom + 1].end - this.targetHeight;
			}
			this._scroll(end);
			
		}
		//this.scrollMutex = false;
	},
	
	back: function () {
		var l = this.target.scrollTop;
		
		//TODO nick - r not used anywhere
		var r = l + this.targetHeight;
		if (l > 0)
		{
			var item = this.items[this.top];
			var start;
			if (item.start < l) {
				start = item.start;
			}else if (this.top - 1 >= 0) {
				start = this.items[this.top - 1].start;
			}
			this._scroll(start);
			
		}
		//this.scrollMutex = false;
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
		if (p > this.target.scrollTop) {
			this.scrollInterval = window.setInterval(function () { this._scrollF(p)}.bind(this), vScroller.SCROLLING_SPEED);
		}else {
			this.scrollInterval = window.setInterval(function () { this._scrollB(p)}.bind(this), vScroller.SCROLLING_SPEED);
		} //TODO nick - check for p == this.target.scrollTop?

	},

	_scrollF: function (end) {
				var i =  this.target.scrollTop;
				i = i + this.scrollingStep;
				if (i >= end)
				{
					this.target.scrollTop = end;
					this.calculateScrollPosition();
					this._finish();
					return;
				}else {
					this.target.scrollTop = i;
				}
	},

	_scrollB: function (start) {
			var i =  this.target.scrollTop
			i = i - this.scrollingStep;
			if (i <= start)
			{
				this.target.scrollTop = start;
				this.calculateScrollPosition();
				this._finish();
				return;
			}else {
				this.target.scrollTop = i;
			
			}

	},
	
	_finish: function () {
		if (this.scrollInterval) {
			window.clearInterval(this.scrollInterval);
		}
		this.scrollInterval = null;
		this.scrollMutex = false;
		this.scrollingStep = vScroller.SCROLLING_STEP;
		if (this.callback) {
			this.callback();
		}
	},

	//TODO nick - there's already similar functionality implemented in _scroll
	_doScrolling: function (forward) {
		var scrollTop = this.target.scrollTop;
			if (forward)
			{
				if (scrollTop + this.scrollingStep < this.scrollHeight - this.targetHeight)
				{
					this.target.scrollTop = scrollTop + this.scrollingStep;	
				}else {
					this.target.scrollTop = this.scrollHeight - this.targetHeight;
				}
			}else {
				if (scrollTop - this.scrollingStep > 0 )
				{
					this.target.scrollTop = scrollTop - this.scrollingStep;	
				}else {
					this.target.scrollTop = 0;
				}
			}
	},

	fireScrolling: function (forward) {
		if (this.intervalId)
		{
			return;
		}
		if (forward) {
			this.intervalId = window.setInterval(function() { this._doScrolling(true); }.bind(this), vScroller.SCROLLING_SPEED);
		} else {
			this.intervalId = window.setInterval(function() { this._doScrolling(false); }.bind(this), vScroller.SCROLLING_SPEED);
		}
	},	
	
	startScrolling: function(e, forward) {
		if (!this.initialized) {
			this.init();
		}
		if (this.scrollMutex || this.timeoutId)
		{
			return;
		}
		this.scrollMutex = true;
		//if (e.preventDefault) e.preventDefault();
		this.timeoutId = window.setTimeout(function() { this.fireScrolling(forward); }.bind(this), vScroller.SCROLLING_DELAY);
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
				
		var isForward = (vScroller.CONTROL.FORWARD == controlType);
		var control = isForward ? this.forward: this.back;		
		Event.observe(env, 'click', control.bindAsEventListener(this));
		Event.observe(env, 'mousedown', function (e) { this.startScrolling(e, isForward); }.bind(this));
		Event.observe(env, 'mouseup', function () { this.endScrolling(isForward); }.bind(this));
		Event.observe(env, 'mouseout', function () { this.endScrolling(isForward); }.bind(this));
		
		Event.observe(env, 'drag', function () {return false;});
		
	},
	
	unbindControls: function (env, controlType) {
			Event.stopObserving(env, 'click');
			Event.stopObserving(env, 'mousedown');
			Event.stopObserving(env, 'mouseup');
			Event.stopObserving(env, 'mouseout');
			Event.stopObserving(env, 'drag');
	},
	
	activateTargetItem: function (callback,n) {
		if (!this.initialized) {
			this.init();
		}
		this.callback = callback;
		
		//TODO nick - why not pass valid argument?
		n--; 
		var start = this.items[n].start;
		var end = this.items[n].end;
		
		var l = this.target.scrollTop;
		var r = l + this.targetHeight;

		//TODO nick - what 5 and 30 stand for?
		if (start < l) {
			this.scrollingStep = (l-start)/5 > 30 ? (l-start)/5 : 30;
			this._scroll(start);
		} else if (end > r) {
			this.scrollingStep = (end-r)/5 > 30 ? (end-r)/5 : 30;
			this._scroll(end - this.targetHeight);
		}else if (this.callback) {
			this.callback();
			this.callback = null;
		}		
		
	}

	
});