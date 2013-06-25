SipModalPanel.Border = Class.create();

SipModalPanel.Border.prototype = {
	initialize: function(id, modalPanel, cursor, sizer) {
		this.id = id;
		var element = $(id);
		element.style.cursor = cursor;

		this.boundStartDrag = this.startDrag.bindAsEventListener(this, new Date());
		Event.observe(this.id, 'mousedown', this.boundStartDrag);

		this.modalPanel = modalPanel;
		this.sizer = sizer;
		
		this.boundDoDrag = this.doDrag.bindAsEventListener(this);
		this.boundEndDrag = this.endDrag.bindAsEventListener(this);
	},
	
	destroy: function()
	{
		if (this.doingDrag)
		{
			Event.stopObserving(document, 'mousemove', this.boundDoDrag); 
			Event.stopObserving(document, 'mouseup', this.boundEndDrag); 
		}

		Event.stopObserving(this.id, 'mousedown', this.boundStartDrag);
		this.modalPanel=null;
	},

	show: function() {
		Element.show(this.id);
	},

	hide: function() {
		Element.hide(this.id);
	},

	startDrag: function(event) {
		if(SipSingleCalendar){
			SipSingleCalendar.singleCalendarOnScrollHandler();
		}
		this.doingDrag = true;

		this.dragX = event.clientX;
		this.dragY = event.clientY;

		Event.observe(document, 'mousemove', this.boundDoDrag);
		Event.observe(document, 'mouseup', this.boundEndDrag);
		
		var eCursorDiv = $(this.modalPanel.cursorDiv);
		eCursorDiv.style.cursor = $(this.id).style.cursor;
		eCursorDiv.style.zIndex = 10;
	
		this.modalPanel.startDrag(this);
		
		this.onselectStartHandler = document.onselectstart;
		document.onselectstart = function() { return false; }
		this.modalPanel.removeSize(this.modalPanel.cdiv);
	},

	doDrag: function(event) {
		if (!this.doingDrag) {
			return ;
		}	
		
		var evtX = event.clientX;
		var evtY = event.clientY;

		var winSize = Richfaces.getWindowSize();

		//window.status = "" + evtX + " " + evtY;
		
		if (evtX < 0) {
			evtX = 0;
		} else if (evtX >= winSize.width) {
			evtX = winSize.width - 1;
		}
		
		if (evtY < 0) {
			evtY = 0;
		} else if (evtY >= winSize.height) {
			evtY = winSize.height - 1;
		}

		var dx = evtX - this.dragX;
		var dy = evtY - this.dragY;
		
		if (dx != 0 || dy != 0) {
						
			var id = this.id;  
			
			var diff = this.sizer.doDiff(dx, dy);
			var doResize;
			
			var element = $(this.modalPanel.cdiv);
			
			if (diff.deltaWidth || diff.deltaHeight) {
				doResize = this.modalPanel.invokeEvent("resize",event,null,element);
			} else if (diff.deltaX || diff.deltaY) {
				doResize = this.modalPanel.invokeEvent("move",event,null,element);
			}
			
			var vetoes;
			
			if (doResize) {					
				 vetoes = this.modalPanel.doResizeOrMove(diff);
			}	 
			
			if(vetoes){
				if (!vetoes.x) {
					this.dragX = evtX;
				} else {
					if (!diff.deltaX) {
						this.dragX -= vetoes.vx || 0;
					} else {
						this.dragX += vetoes.vx || 0;
					}
				}
	
				if (!vetoes.y) {
					this.dragY = evtY;
				} else {
					if (!diff.deltaY) {
						this.dragY -= vetoes.vy || 0;
					} else {
						this.dragY += vetoes.vy || 0;
					}
				}
			}	
		}
	},

	endDrag: function(event) {
		this.doingDrag = undefined;

		Event.stopObserving(document, 'mousemove', this.boundDoDrag); 
		Event.stopObserving(document, 'mouseup', this.boundEndDrag); 

		this.modalPanel.endDrag(this);
		
		this.modalPanel.doResizeOrMove(SipModalPanel.Sizer.Diff.EMPTY);
		$(this.modalPanel.cursorDiv).style.zIndex = -200;

		document.onselectstart = this.onselectStartHandler;
		this.onselectStartHandler = null;
		
		var id = this.id;
	},

	doPosition: function() {
		this.sizer.doPosition(this.modalPanel, $(this.id));	
	} 
};

SipModalPanel.Sizer = Class.create();

SipModalPanel.Sizer.INITIAL_MIN = 4;
SipModalPanel.Sizer.INITIAL_MAX = 40;

SipModalPanel.Sizer.Diff = Class.create();
SipModalPanel.Sizer.Diff.prototype = {
	initialize: function(dX, dY, dWidth, dHeight) {
		this.deltaX = dX;
		this.deltaY = dY;

		this.deltaWidth = dWidth;
		this.deltaHeight = dHeight;
	}
}

SipModalPanel.Sizer.Diff.EMPTY = new SipModalPanel.Sizer.Diff(0, 0, 0, 0);

SipModalPanel.Sizer.prototype = {
	initialize: function() {

	},
	
	doSetupSize: function (modalPanel, elt) {
		var width = 0;
		var height = 0;

		var reductionData = modalPanel.reductionData;
		
		if (reductionData) {
			if (reductionData.w) {
				width = reductionData.w / 2;
			} 
	
			if (reductionData.h) {
				height = reductionData.h / 2;
			} 
		}
		
		if (width > 0) {
			if (elt.clientWidth > width) {
				if (!elt.reducedWidth) {
					elt.reducedWidth = elt.style.width;
				}
				elt.style.width = width + 'px';
			} else if (width < SipModalPanel.Sizer.INITIAL_MAX && /* TODO fix the dirty code */elt.reducedWidth == SipModalPanel.Sizer.INITIAL_MAX + 'px') {
				elt.style.width = width + 'px';
			}
		} else {
			if (elt.reducedWidth) {
				elt.style.width = elt.reducedWidth;
				elt.reducedWidth = undefined;
			}
		}
		
		if (height > 0) {
			if (elt.clientHeight > height) {
				if (!elt.reducedHeight) {
					elt.reducedHeight = elt.style.height;
				}
				elt.style.height = height + 'px';
			} else if (height < SipModalPanel.Sizer.INITIAL_MAX && /* TODO fix the dirty code */elt.reducedHeight == SipModalPanel.Sizer.INITIAL_MAX + 'px') {
				elt.style.height = height + 'px';
			}
		} else {
			if (elt.reducedHeight) {
				elt.style.height = elt.reducedHeight;
				elt.reducedHeight = undefined;
			}
		}
	},
	
	doSetupPosition: function (modalPanel, elt, left, top) {
		elt.style.left = left + 'px';
		elt.style.top = top + 'px';
	},

	doPosition: function (modalPanel, elt) {
	
	},

	doDiff: function (dx, dy) {
	
	}
}

SipModalPanel.Sizer.NWU = Object.extend(new SipModalPanel.Sizer(), {
	doPosition: function (modalPanel, elt) {
		this.doSetupSize(modalPanel, elt);
		this.doSetupPosition(modalPanel, elt, 0, 0);
	},

	doDiff: function(dx, dy) {
		return new SipModalPanel.Sizer.Diff(dx, dy, -dx, -dy);
	}
});

SipModalPanel.Sizer.N = new SipModalPanel.Sizer();
SipModalPanel.Sizer.N.doPosition = function (modalPanel, elt) {
	elt.style.width = modalPanel.width() + 'px';
	this.doSetupPosition(modalPanel, elt, 0, 0);
};
SipModalPanel.Sizer.N.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, dy, 0, -dy);
};

SipModalPanel.Sizer.NEU = new SipModalPanel.Sizer();
SipModalPanel.Sizer.NEU.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, modalPanel.width() - elt.clientWidth, 0);
};
SipModalPanel.Sizer.NEU.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, dy, dx, -dy);
};

SipModalPanel.Sizer.NEL = new SipModalPanel.Sizer();
SipModalPanel.Sizer.NEL.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, modalPanel.width() - elt.clientWidth, 0);
};
SipModalPanel.Sizer.NEL.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, dy, dx, -dy);
};

SipModalPanel.Sizer.E = new SipModalPanel.Sizer();
SipModalPanel.Sizer.E.doPosition = function (modalPanel, elt) {
	elt.style.height = modalPanel.height() + 'px';
	this.doSetupPosition(modalPanel, elt, modalPanel.width() - elt.clientWidth, 0);
};
SipModalPanel.Sizer.E.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, 0, dx, 0);
};

SipModalPanel.Sizer.SEU = new SipModalPanel.Sizer();
SipModalPanel.Sizer.SEU.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, modalPanel.width() - elt.clientWidth, 
		modalPanel.height() - elt.clientHeight);
};
SipModalPanel.Sizer.SEU.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, 0, dx, dy);
};

SipModalPanel.Sizer.SEL = new SipModalPanel.Sizer();
SipModalPanel.Sizer.SEL.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, modalPanel.width() - elt.clientWidth, 
		modalPanel.height() - elt.clientHeight);
};
SipModalPanel.Sizer.SEL.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, 0, dx, dy);
};

SipModalPanel.Sizer.S = new SipModalPanel.Sizer();
SipModalPanel.Sizer.S.doPosition = function (modalPanel, elt) {
	elt.style.width = modalPanel.width() + 'px';
	this.doSetupPosition(modalPanel, elt, 0, modalPanel.height() - elt.clientHeight);
};
SipModalPanel.Sizer.S.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(0, 0, 0, dy);
};

SipModalPanel.Sizer.SWL = new SipModalPanel.Sizer();
SipModalPanel.Sizer.SWL.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, 0, modalPanel.height() - elt.clientHeight);
};
SipModalPanel.Sizer.SWL.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(dx, 0, -dx, dy);
};

SipModalPanel.Sizer.SWU = new SipModalPanel.Sizer();
SipModalPanel.Sizer.SWU.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, 0, modalPanel.height() - elt.clientHeight);
};
SipModalPanel.Sizer.SWU.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(dx, 0, -dx, dy);
};

SipModalPanel.Sizer.W = new SipModalPanel.Sizer();
SipModalPanel.Sizer.W.doPosition = function (modalPanel, elt) {
	elt.style.height = modalPanel.height() + 'px';
	this.doSetupPosition(modalPanel, elt, 0, 0);
};
SipModalPanel.Sizer.W.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(dx, 0, -dx, 0);
};

SipModalPanel.Sizer.NWL = new SipModalPanel.Sizer();
SipModalPanel.Sizer.NWL.doPosition = function (modalPanel, elt) {
	this.doSetupSize(modalPanel, elt);
	this.doSetupPosition(modalPanel, elt, 0, 0);
};
SipModalPanel.Sizer.NWL.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(dx, dy, -dx, -dy);
};

SipModalPanel.Header = new SipModalPanel.Sizer();
SipModalPanel.Header.doPosition = function (modalPanel, elt) {

};
SipModalPanel.Header.doDiff = function(dx, dy) {
	return new SipModalPanel.Sizer.Diff(dx, dy, 0, 0);
};
