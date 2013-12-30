/**
 * 
 * Copyright 2007
 * 
 * Paulius Uza
 * http://www.uza.lt
 * 
 * Dan Florio
 * http://www.polygeek.com
 * 
 * Project website:
 * http://code.google.com/p/custom-context-menu/
 * 
 * --
 * RightClick for Flash Player. 
 * Version 0.6.2
 * 
 */
var RightClick = {
	
	destroy: function(FlashContainerId, FlashObjectId){
		if(window.removeEventListener){
			window.removeEventListener("mousedown", this.onGeckoMouse,true);
			window.removeEventListener("mousedown", this.onGeckoMouse,false);
			//alert('ff destroy');
		}else if(document.detachEvent){
		//	alert('destroyed for ie')
			document.detachEvent("oncontextmenu",this.ieOnContextMenuHandler);
			if(FlashContainerId && $(FlashContainerId) && $(FlashContainerId).detachEvent){
				$(FlashContainerId).detachEvent("onmouseup",this.ieFlashOnMouseUpHandler);
				$(FlashContainerId).detachEvent("onmousedown",this.onIEMouse);
			//	alert('destroyed for ie2')
			}
		}
		
	},
	
	/**
	 *  Constructor
	 */ 
	init: function (FlashContainerId, FlashObjectId) {
		this.FlashObjectID = FlashObjectId;
		this.FlashContainerID = FlashContainerId;
		this.Cache = this.FlashObjectID;
		if(window.addEventListener){
			 window.addEventListener("mousedown", this.onGeckoMouse,true);
		//	 alert('FF init');
		} else if(document.attachEvent){
			document.attachEvent("oncontextmenu",this.ieOnContextMenuHandler);
		//	alert('init for ie');
			if($(this.FlashContainerID).attachEvent){
				$(this.FlashContainerID).attachEvent("onmouseup",this.ieFlashOnMouseUpHandler);
				$(this.FlashContainerID).attachEvent("onmousedown",this.onIEMouse);
		//		alert('init for ie2');
			}
		}
	},
	ieOnContextMenuHandler:function(){
		 if(window.event.srcElement.id == RightClick.FlashObjectID) { 
		 	return false; 
		 } else { 
		 	RightClick.Cache = "nan"; 
		 }
	},
	ieFlashOnMouseUpHandler: function() {
		document.getElementById(RightClick.FlashContainerID).releaseCapture(); 
	},
	/**
	 * GECKO / WEBKIT event overkill
	 * @param {Object} eventObject
	 */
	killEvents: function(eventObject) {
		if(eventObject) {
			if (eventObject.stopPropagation) eventObject.stopPropagation();
			if (eventObject.preventDefault) eventObject.preventDefault();
			if (eventObject.preventCapture) eventObject.preventCapture();
	   		if (eventObject.preventBubble) eventObject.preventBubble();
		}
	},
	/**
	 * GECKO / WEBKIT call right click
	 * @param {Object} ev
	 */
	onGeckoMouse: function(ev) {
		
		
	    if (ev.button != 0) {
			RightClick.killEvents(ev);
			if(ev.target.id == RightClick.FlashObjectID && RightClick.Cache == RightClick.FlashObjectID) {
	    		RightClick.call(ev);
			}
			RightClick.Cache = ev.target.id;
		}
	},
	/**
	 * IE call right click
	 * @param {Object} ev
	 */
	onIEMouse: function() {
	  	if (event.button > 1) {
			if(window.event.srcElement.id == RightClick.FlashObjectID && RightClick.Cache == RightClick.FlashObjectID) {
				RightClick.killEvents(window.event);
				RightClick.call(window.event); 
			}
			document.getElementById(RightClick.FlashContainerID).setCapture();
			if(window.event.srcElement.id)
			RightClick.Cache = window.event.srcElement.id;
		}
	},
	/**
	 * Main call to Flash External Interface
	 */
	call: function(ev) {
		FlashTimeline.getComponent(this.FlashContainerID).setLastSelectedEvent(ev);
		document.getElementById(this.FlashObjectID).rightClick();
	}
};
