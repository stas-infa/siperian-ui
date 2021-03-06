DnD.ExtSimpleDropZone  = Class.create();
Object.extend(DnD.ExtSimpleDropZone.prototype, DnD.Dropzone.prototype);
Object.extend(DnD.ExtSimpleDropZone.prototype, {
	initialize: function(id, options) {
		this.id = id;
		var element = $(id);
		
		if (!element) {
		//	alert("drop: Element with [" + id + "] ID was not found in the DOM tree. Probably element has no client ID or client ID hasn't been written. DnD's disabled. Check please!");
			return ;
		}
		
		this.element = element;
		if (options.acceptedTypes) {
			this.acceptedTypes = options.acceptedTypes;
		} else {
			this.acceptedTypes = [];
		}
	
		if (options.typeMapping) {
			this.typeMapping = options.typeMapping;
		} else {
			this.typeMapping = {};
		}
		
		if (options.cursorTypeMapping) {
			this.cursorTypeMapping = options.cursorTypeMapping;
		} else {
			this.cursorTypeMapping = {};
		}
		
		this.mouseoverBound = this.mouseover.bindAsEventListener(this);
		this.mouseoutBound = this.mouseout.bindAsEventListener(this);
		this.mouseupBound = this.mouseup.bindAsEventListener(this);
		
		Event.observe(element, "mouseover", this.mouseoverBound);
		Event.observe(element, "mouseout", this.mouseoutBound);
		Event.observe(element, "mouseup", this.mouseupBound);
		
		
			
		this.options = options || {};
		this.enableDropzoneCursors(options.acceptCursor,options.rejectCursor);
		
	},

	getDropzoneOptions: function() {
		return this.options;
	},

	getDnDDropParams: function() {
		if (this.options.dndParams) {
			return this.options.dndParams.parseJSON(EventHandlersWalk);
		}
		
		return null;
	},

	mouseover: function(event) {
		if (window.drag){
			this.dragEnter(event);
			//change dropzone style
			this.element.childNodes[0].style.visibility="visible";//top element
			this.element.childNodes[1].style.visibility="visible";//bottom element
		}
	},

	mouseup: function(event) {
		this.dragUp(event);
	},

	mouseout: function(event) {
		if (window.drag){
			this.dragLeave(event);
			//change dropzone style
			this.element.childNodes[0].style.visibility="hidden";//top element
			this.element.childNodes[1].style.visibility="hidden";//bottom element
		}
	},
	getAcceptedTypes: function() {
		return this.acceptedTypes;
	},
	getTypeMapping: function() {
		return this.typeMapping;
	},
	
	getCursorTypeMapping: function() {
		return this.cursorTypeMapping;
	},
	
	drop: function(event, drag){
	//	alert('I drop');	
	},
	onafterdrag: function(drag) {
		if (this.options.onafterdrag) {
			this.options.onafterdrag.call(this, drag);
		}
	}
});