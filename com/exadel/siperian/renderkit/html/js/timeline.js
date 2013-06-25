FlashTimeline = {
	getComponent: function(componentId) {
	
		return $(componentId).component;
	},
	ASTrace: function (msg)
	{
		console.log(msg);
	},
	ASAlert: function (msg)
	{
		alert(msg);
	}
};

Timeline = Class.create({
	
	flashVersion: '9.0.28',

	initialize: function(id, flashUrl, expressUrl,width,height,flashModeParam,eventMenuId,popupMenuId,cssFile,containerId,onzoomerchange,ontimelineinit) {
		this["rich:destructor"] = "destroy";
		this.id = id;
		this.flashUrl = flashUrl;
		this.expressUrl = expressUrl;
		this.element = $(id);
		this.flashComponent = null;
		this.flashContainerId = id+":flashContainer";
		this.xmlContentId=id+":flashContent";
		this.height = height;
		this.width  = width;
		this.onzoomerchange =onzoomerchange;
		this.ontimelineinit  = ontimelineinit;
		this.element.component = this;
		this.flashModeParam = flashModeParam;
		this.eventMenuId = eventMenuId;
		this.popupMenuId = popupMenuId;
		this.cssFile     = cssFile;
		this.lastSelectedEvent = null;
		this.lastSelectedEventIds =[];
		this.lastEventsInputId = this.id+":lastEvents";
		this.lastEventsTimeInputId = this.id+":eventDate";
		this.timelinePositionStateInputId  = this.id+":timelinePositionState";
		this.timelineRulerStateInputId  = this.id+":timelineRulerState";
		this.timelineEventsStateInputId = this.id+":timelineEventsState";
		this.containerId = containerId;
		this.form = null;
		this.flashOnMouseOutListener = this.flashOnMouseOut.bind(this);
		this.initialized = false;
		this.initFlash();
		this.isMacAndSafari = (Prototype.Browser.WebKit && navigator.platform.indexOf('Mac') != -1);

	},
	destroy:function(){
        	this.releaseEventHandlers();
			if(this.flashComponent){
				this.flashComponent.onmouseout=null;
			}
			this.id = null;
			this.flashUrl = null;
			this.expressUrl = null;
			this.flashComponent = null;
			this.flashContainerId = null;
			this.xmlContentId = null;
			this.height= null;
			this.width  = null;
			this.id = null;
            if(this.element){
			    this.element.component = null;
            }
			this.element = null;	
			this.flashModeParam = null;
			this.eventMenuId  = null;
			this.popupMenuId = null;
			this.container = null;
			this.cssFile  = null;
			this.lastEventsInputId = null;
			this.lastSelectedEvent=null;
			this.lastEventsInputId = null;
			this.form = null;
			this.containerId = null;
			this.timelineStateInputId= null;
			this.timelineRulerStateInputId  =null;
			this.timelineEventsStateInputId = null;
			this.onzoomerchange  = null;
			this.ontimelineinit=null;
            this.flashOnMouseOutListener = null;
	
	},
	releaseEventHandlers : function(){
		RightClick.destroy(this.id, this.flashContainerId);
	},
	initFlash: function () {
//		   alert('initFlash');
			var flashvars = {xmlEmpty: true, id:this.id};
			var params = {
				menu: "false",
				scale: "noScale",
				bgcolor: "#FFFFFF",
				wmode  : this.flashModeParam,
				id: this.flashContainerId,
				name: this.flashContainerId,				
				AllowScriptAccess: "samedomain"
			};
		
		swfobject.embedSWF(this.flashUrl, this.flashContainerId, this.width, this.height, this.flashVersion, this.expressUrl, flashvars, params);
	},
	
	onFlashInit: function (){
			this.flashComponent = (document[this.flashContainerId]) ? document[this.flashContainerId] : (window[this.flashContainerId] ? window[this.flashContainerId] : $(this.flashContainerId));
			//SC-461 flash can be not initialized if browser window was minimized during loading 
			Utils.execOnLoad(
			function(){
				this._onFlashInit();
			}.bind(this),
			function () { return (this.flashComponent && this.flashComponent.setProperties && typeof(this.flashComponent.setProperties) == 'function'); }.bind(this), 200);

	},
	
	_onFlashInit: function () {
			if (this.initialized) {
				return;
			}
	
			var myXML = document.getElementById(this.xmlContentId).value;
			if(this.cssFile!=void(0) && this.cssFile!=""){
				this.flashComponent.setProperties({xml: unescape(myXML), cssUrl: this.cssFile,useFlashContextMenu:this.isMacAndSafari });
			}else{
				this.flashComponent.setProperties({xml: unescape(myXML),useFlashContextMenu:this.isMacAndSafari});
			}
		
			if (this.flashComponent)
			{
				this.flashComponent.onmouseout = this.flashOnMouseOutListener;
			}
		
			if(!this.isMacAndSafari){
				RightClick.init(this.id, this.flashContainerId);
			}
			if (this.initInterval) {
				window.clearInterval(this.initInterval);
				this.initInterval = null;
			}
			this.initialized = true;
	},
	
	flashOnMouseOut: function() {
		this.flashComponent.mouseOut();
	},
	
	initTimelineState : function(){
		//alert("initTimelinestate!");
		if($(this.timelinePositionStateInputId)){
			var variables = $(this.timelinePositionStateInputId).value;
			//alert("variables="+variables);
			if(variables && variables!=void(0) && variables!=""){
				var stateValues = variables.split(",");
				//alert(stateValues);
				if((stateValues.length) == 2 && ((stateValues[0]!="") && (stateValues[1]!="") && stateValues[0]!="null" &&
				stateValues[1]!="null")){
				//	console.log("initTimelineState: stateValue[0]="+stateValues[0]+",stateValues[1]="+stateValues[1]);
					this.setVisibleInterval(new Date(new Number(stateValues[0])),new Date(new Number(stateValues[1])));
				}
				
			}
		
		}
		if($(this.timelineRulerStateInputId)){
			var value = $(this.timelineRulerStateInputId).value;
			if(value && value!="" && new Number(value)!=0){
				this.setVerticalRuller(value);
			}
		}
		if($(this.timelineEventsStateInputId)){
			var value = $(this.timelineEventsStateInputId).value;
			if(value && value.length > 0){
				var events =  value.split(",");
				for(var i = 0 ; i < events.length ; i ++){
					this.hideEventType(events[i]);
				}
			}
		}
		if(this.ontimelineinit){
			new Function("",this.ontimelineinit)();	
		}
		//	alert('inited!');
	},
	setLastSelectedEvent :function(lastSelectedEvent){
		this.lastSelectedEvent = lastSelectedEvent;
	},
	getLastSelectedEvent: function(){
		return this.lastSelectedEvent;
	},
	onContextMenu: function (mouseOffsetX, mouseOffsetY, dateNumber, events) {
		//this.flashComponent.setVerticalRuller(events.length>0 ? events[0].date : dateNumber);
		//return;

		//Disable timeline for mac and safari
//		if (this.isMacAndSafari) this.flashComponent.disable(true);
		
		// TODO: mouse position calculation relative to flash object
		this.lastSelectedEventIds=[];
		if(this.eventMenuId!=void(0) && this.eventMenuId!="" && events!=void(0) && events.length > 0){
			for ( var i = 0 ;i < events.length ; i ++ ){
				this.lastSelectedEventIds[i] = events[i].id;
			}
			if(!this.isMacAndSafari){
				$(this.eventMenuId).component.show(this.getLastSelectedEvent(),null);
				$(this.eventMenuId).component.show(this.getLastSelectedEvent(),null);
				$(this.eventMenuId).component.show(this.getLastSelectedEvent(),null);
			}
		}else if(this.popupMenuId && this.popupMenuId!="" && (events == null || events.length == 0)){
			if(!this.isMacAndSafari){
				$(this.popupMenuId).component.show(this.getLastSelectedEvent(),null);
				$(this.popupMenuId).component.show(this.getLastSelectedEvent(),null);
				$(this.popupMenuId).component.show(this.getLastSelectedEvent(),null);
			}
		
		}
		$(this.lastEventsInputId).value = this.getLastEventIds();

		$(this.lastEventsTimeInputId).value = (events.length>0 ? events[0].date : dateNumber);
		if(!this.options){
			var vId = this.id;

			this.options = {'parameters':{"ajaxSingle":vId}};
		}
    	this.submitForm(this.getLastSelectedEvent(),this.options);
	},
	submitForm : function(eVent,options){
		if (!this.form) {
			this.form = A4J.AJAX.locateForm($(this.id));
		}
		var formId = (this.form) ? this.form.id : null; 

		
		setTimeout(A4J.AJAX.Submit(this.containerId, formId, eVent, options),300);
	},
	onDateRangeChanged : function(viewFromDateNumber, viewToDateNumber){
		//TODO
		var fromDate = new Date(viewFromDateNumber);
		var toDate = new Date(viewToDateNumber);
		if(fromDate && toDate && $(this.timelinePositionStateInputId)){
			$(this.timelinePositionStateInputId).value=viewFromDateNumber+","+viewToDateNumber;
		}
		if(this.onzoomerchange){

				new Function("",this.onzoomerchange)();
		}
		if($(this.timelineRulerStateInputId)){
			var value = $(this.timelineRulerStateInputId).value;
			if(value && value!="" && new Number(value)!=0){
				this.setVerticalRuller(value);
			}
		}
	
	}, 
	
	/*onScroll : function(viewFromDateNumber, viewToDateNumber){
		//TODO
	},*/
	getLastEventIds : function(){
		if(this.lastSelectedEventIds.length > 0){
			return this.lastSelectedEventIds.toString();
		}else if(this.lastSelectedEventIds.lenght == 1){
			return this.lastSelectedEventIds[0];
		}
		return null;
	},
	disable: function (flag) {
		if(this.flashComponent) {
//			alert("disabled as "+flag+", is mac = "+this.isMacOrSafari());
			this.flashComponent.disable(flag);
		}else{
//			alert("Component not found");
		}
	},
	setVerticalRuller : function(dateNumber){
		if(this.flashComponent && dateNumber!=void(0)){
			
			this.flashComponent.setVerticalRuller(dateNumber);
			if($(this.timelineRulerStateInputId)){
				$(this.timelineRulerStateInputId).value = dateNumber;
			}
		}
	},
	hideVerticalRuller : function(){
		if(this.flashComponent){
			this.flashComponent.hideVerticalRuller();
			if($(this.timelineRulerStateInputId)){
				$(this.timelineRulerStateInputId).value =0;
			}
		}	
	},
	getVerticalRullerDate : function(){
		if(this.flashComponent){
			return this.flashComponent.getVerticalRullerDate();
		}	
	},
   /**
	* Event type should be defined through a parameter
	*/
	showEventType: function (type) {
		if (this.flashComponent)
		{
			this.flashComponent.showEventType(type);
			if($(this.timelineEventsStateInputId)){
				var value = $(this.timelineEventsStateInputId).value;
				if(value && value.length > 0){
					var values = value.split(",");
					var new_values =  [];
					var a = 0;
					for(var i = 0 ; i < values.length ; i ++ ){
						if(values[i] != type){
							new_values[a++] = values[i].replace(",","");
						}		
					}
					
				//	alert(new_values.toString());
					
					 $(this.timelineEventsStateInputId).value = new_values.toString();
				}
			}
			return true;
		}
		return false;
	},
	
   /**
	* Event type should be defined through a parameter	
	*/
	hideEventType: function (type) {
		if (this.flashComponent)
		{
			this.flashComponent.hideEventType(type);
			if($(this.timelineEventsStateInputId)){
				var value = $(this.timelineEventsStateInputId).value;
				
				if(value && value.length > 0 && value.indexOf(type) == -1){
					value = value +","+type;
				}else{
					value = type;
				}
				$(this.timelineEventsStateInputId).value = value;
			}
			return true;
		}
		return false;		
	},
	
	switchEventTypes: function(objNames) {
		if (this.flashComponent)
		{
			this.flashComponent.switchEventTypes(objNames);
			return true;
		}
		return false;	
	},
	
   /**
    * The function updates Visible timelineViewer interval
	* fromDate, toDate should be passed as parameters. 
	*  If null, component will display the full time period in Timeline control
    */	
	setVisibleInterval: function (fromDate, toDate) {
		if (this.flashComponent)
		{
			if ( (fromDate!=void(0) && toDate!=void(0)) && (fromDate.getTime()>=toDate.getTime()) ){
				 return false;
			}
			this.flashComponent.setVisibleInterval(fromDate == void(0) ? null : fromDate.getTime(),toDate == void(0) ? null : toDate.getTime());
			return true;
		}
		
		return false;
	},
	
   /**
    * If null, first event date and last event date are returned
    */	
	// return value is an object with two timestamps and two Dates {fromDateNumber, toDateNumber, fromDate, toDate}
	getVisibleInterval: function () {
		var result = null;
		if (this.flashComponent)
		{
			result = this.flashComponent.getVisibleInterval();
			result.fromDate = new Date(result.fromDateNumber);
			result.toDate = new Date(result.toDateNumber);
		}
		
		return result;
	},
	setHeight: function(height){
		//alert('setHeight');
		if(this.flashComponent){
			var incValue = height;
			if(height < new Number(this.height) ){
				incValue +=20;
			}else{
				incValue +=10;
			}
			this.flashComponent.height="100%";
			this.flashComponent.parentNode.style.height= incValue +"px";
			//alert("height = "+incValue)
			setTimeout(function(){this.initTimelineState();}.bind(this),250);
		
		}
	},	
   /**
    * The function updates Limitative timelineViewer interval
	* fromDate, toDate should be passed as parameters. 
	*  If null, component will display the full time period in Zoomer control
    */
    setLimitativeInterval: function () {
    },
    
   /**
    * If null, first event date and last event date are returned
    */ 
    getLimitativeInterval: function () {
    },
    
   /**
    * Invokes IDs of event 
    */ 
    getEventId: function () {
    },
    
   /**
    * FromDate, toDate should be passed as parameters. 
    *  If null, number of events for the full time period is returned
    */ 
    getVisibleEventsNumber: function (fromDate, toDate) {
		if (this.flashComponent)
		{
			
			return this.flashComponent.getVisibleEventsNumber(fromDate == null ? null : fromDate.getTime(), toDate == null ? null :toDate.getTime());
		}
		
		return void(0);	
    },
    
   /**
   	* The function The function updates Visible timelineViewer interval
	*  Coefficient should be passed as a parameter to be divided/  multiplied the Visible timelineViewer interval on 
    *  If null, the Visible timelineViewer interval is not changed
   	*/ 
    zoomIn: function (factor) {
		if (this.flashComponent)
		{
			this.flashComponent.zoomIn(factor);
			return true;
		}
		return false;
    },
    
     /**
   	* The function The function updates Visible timelineViewer interval
	*  Coefficient should be passed as a parameter to be divided/  multiplied the Visible timelineViewer interval on 
    *  If null, the Visible timelineViewer interval is not changed
   	*/ 
    zoomOut: function (factor) {
		if (this.flashComponent)
		{
			this.flashComponent.zoomOut(factor);
			return true;
		}
		return false;
    },
    isMacOrSafari : function(){
    	return this.isMacAndSafari;
    }
});
