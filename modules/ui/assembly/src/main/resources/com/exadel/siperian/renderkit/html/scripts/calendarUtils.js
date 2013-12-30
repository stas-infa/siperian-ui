CalendarKeyHandler = Class.create({

	initialize: function(calendarId,handler) {
		//alert("init : "+calendarId);
//		this["rich:destructor"] = "destroy";
		this.id = calendarId;
		this.defaultFirstDate = null;

		if(handler){
			this.handler = handler;
		}else{
			this.handler = this.defaultKeyPressHandler.bind(this);
		}
		this.currentYMPopupMode = null;
		this.timePopupActivInput = "H";

  
	},
	init:function(){
//		alert('init');
			Event.observe(document, 'keydown', this.handler);
			this.getComponent().isTimeEditorLayoutCreated = false;
			this.getComponent().isDateEditorLayoutCreated = false;
	},
	
	destroy:function(){
//		alert('destory');
		
		Event.stopObserving(document, 'keydown', this.handler);
		this.getComponent().isTimeEditorLayoutCreated = false;
		this.getComponent().isDateEditorLayoutCreated = false;
//		this.id = null;
//		this.handler = null;
	},
	
	getTimeControlInput:function(){
		if(this.timePopupActivInput == "H"){
			return $(this.id+'TimeHours');
		}else if(this.timePopupActivInput == "M"){
			return $(this.id+'TimeMinutes');
		}
	},
	increaseTimeInput:function(keyCode){
		var timeInput = this.getTimeControlInput();
		var value = timeInput.value;
		if((keyCode == Event.KEY_LEFT) && this.timePopupActivInput == "H"){
			value = value - 1;
		
		}else if(keyCode == Event.KEY_RIGHT && this.timePopupActivInput == "H"){
			
		}else if((keyCode == Event.KEY_LEFT) && this.timePopupActivInput == "M"){
			
		}else if(keyCode == Event.KEY_RIGHT && this.timePopupActivInput == "M"){
			
		}
	},
	changeDate:function(e,changeOffset){
		var selectedDate  = this.getComponent().getSelectedDate();
		if(!selectedDate){
			this.getComponent().today(false,true);
			selectedDate  = this.getComponent().getSelectedDate();
		}
		if(selectedDate){
			var changed = false;
			if(!this.getComponent().showApplyButton){
				this.getComponent().showApplyButton = true;
				changed =  true;
			}
			var newDate = new Date(selectedDate.getTime());
			newDate.setDate(selectedDate.getDate()+changeOffset);
			this.getComponent().selectDate(newDate,false,null);
			if(changed){
				this.getComponent().showApplyButton = false;
			}
		}
	},
	changeMonth:function(e,changeOffset){
		var selectedDate  = this.getComponent().getSelectedDate();

		if(selectedDate){
			var changed = false;
			if(!this.getComponent().showApplyButton){
				this.getComponent().showApplyButton = true;
				changed =  true;
			}
			var newDate = new Date(selectedDate.getTime());

			newDate.setMonth(selectedDate.getMonth()+changeOffset);
			var day = selectedDate.getDate();
			var month = selectedDate.getMonth();
			var year  = selectedDate.getYear();
//			alert("day = "+day+",month="+month+",year="+year);
//			if((day > 28) && (month == 2 && month ==0)){
//				newDate.setDate(28);
//				newDate.setMonth(selectedDate.getMonth()+changeOffset);
//			}else if(day == 31){
//				newDate.setDate(30);
//				newDate.setMonth(selectedDate.getMonth()+changeOffset);
//			}
			this.getComponent().selectDate(newDate,false,null);
			if(changed){
				this.getComponent().showApplyButton = false;
			}
		}
	},
	changeYear:function(e,changeOffset){
		var selectedDate  = this.getComponent().getSelectedDate();

		if(selectedDate){
			var changed = false;
			if(!this.getComponent().showApplyButton){
				this.getComponent().showApplyButton = true;
				changed =  true;
			}
			var newDate = new Date(selectedDate.getTime());
			newDate.setFullYear(selectedDate.getFullYear()+changeOffset);
			this.getComponent().selectDate(newDate,false,null);
			if(changed){
				this.getComponent().showApplyButton = false;
			}
		}
	},
	isYearPanelIsActived:function(){
		return (!this.currentYMPopupMode || this.currentYMPopupMode=="Y" );
	},
	isMonthPanelIsActived:function(){
		return (this.currentYMPopupMode == "M");
	},
	changeYearMonthPopupValue:function(e,changeOffset){
		var value = null;
		var offset = 0;
		var checkedValueLow = 0;
//		alert(this.isMonthPanelIsActived()+","+this.isYearPanelIsActived());
		if(this.isYearPanelIsActived()){
			value = this.getComponent().dateEditorYear - this.getComponent().dateEditorStartYear;
			checkedValueLow = 9;
		}else if(this.isMonthPanelIsActived()){
	
			value = this.getComponent().dateEditorMonth;
			checkedValueLow = 11;
//			alert("v="+value);
		}
		if(value==void(0)){
			value = 0;
		}
	//	alert("value ="+value);
		
		var newValue = value+changeOffset+offset;
//		alert("newValue = "+newValue);
	
		if(newValue > checkedValueLow){
			newValue = checkedValueLow;
		}else if(newValue < 0){
			newValue = 0;
		}
	//	alert("cahnge offset"+changeOffset);
		if(!this.currentYMPopupMode || this.currentYMPopupMode == "Y"){
			this.getComponent().dateEditorSelectYear(newValue);
		}else if(this.currentYMPopupMode == "M"){
			this.getComponent().dateEditorSelectMonth(newValue);
		}
	},
	moveDateUp:function(e){
		this.changeDate(e,-7);

	},
	moveDateDown:function(e){
		this.changeDate(e,+7);
	},
	moveDateLeft:function(e){
		this.changeDate(e,-1);
	},
	moveDateRight:function(e){
		this.changeDate(e,+1);
	},
	upOrDownHoursOrTime:function(e){
		if(e.keyCode == Event.KEY_LEFT){
			
		}else if(e.keyCode == Event.KEY_RIGHT){
			
		}
	},
	handleDaySelection:function(keys,e){
		var selectedDate  = this.getComponent().getSelectedDate();
		if(this.defaultFirstDate == null && selectedDate){
			this.defaultFirstDate = selectedDate;
			//alert("Date :"+this.defaultFirstDate);
		}
		if(keys == Event.KEY_UP){
			this.moveDateUp(e);
		}else if(keys == Event.KEY_DOWN){
			this.moveDateDown(e);
		}else if(keys == Event.KEY_LEFT){

			this.moveDateLeft(e);
		}else if(keys == Event.KEY_RIGHT){
			this.moveDateRight(e);
		}
	},
	getComponent:function(){
		return $(this.id).component;
	},
	changeTimeDialogPanelState:function(){
		this.getTimeControlInput().focus();
		if(this.timePopupActivInput == "M"){
     								this.timePopupActivInput="H";
     							}else if(this.timePopupActivInput == "H"){
     								this.timePopupActivInput = "M";
     	}
	},
	defaultKeyPressHandler:function(e){
		//alert("defaultKeyPressHandler");
		//38 up
		//40 down
		//39 right
		//37 left
//		alert(e.keyCode);
		//alert(e.keyCode);
        switch(e.keyCode){
     		case 188: //<
				if(!this.getComponent().isEditorVisible){
     				if(e.shiftKey){	// shift pressed
     					this.getComponent().prevYear();
     					this.changeYear(e,-1);
     				}else{
     					this.getComponent().prevMonth();
     					this.changeMonth(e,-1);
     				}
				}else if(e.shiftKey){
 						this.getComponent().scrollEditorYear(-1);
 					}
     			break;
     		case 190: //>
				if(!this.getComponent().isEditorVisible){
     				if(e.shiftKey){	//shift pressed
     					this.getComponent().nextYear();
     					this.changeYear(e,1);
     				}else{
     					this.getComponent().nextMonth();
     					this.changeMonth(e,1);
     				}
				}else if(e.shiftKey){
 						this.getComponent().scrollEditorYear(1);
 					}
     		break;
     		case 27://Esc
     			if(this.getComponent().isEditorVisible && !this.getComponent().isTimeEditorLayoutCreated){
     				this.getComponent().hideDateEditor(false);
     				this.getComponent().isDateEditorLayoutCreated = false;
     			}else if(this.getComponent().isEditorVisible && this.getComponent().isTimeEditorLayoutCreated){
     				this.getComponent().hideTimeEditor(false );	
     				this.getComponent().isTimeEditorLayoutCreated = false;
     			}else if(!this.getComponent().isEditorVisible){
					this.defaultFirstDate = null;
     				this.getComponent().close(false);
     				this.destroy();
     			}
     			break;
     		case Event.KEY_UP: // Handle l/r/u/d keys
     		case Event.KEY_DOWN:
     		case Event.KEY_LEFT:
     		case Event.KEY_RIGHT:
		//Ideally this should be refactored. Because isDateEditorLayoutCreated and isDateEditorLayoutCreated
		//donot reseted by original calendar script after closure with internal buttons
		//So both if statements will be true. But this not influence anyhow on current functionality 
		//because corrected when editor next time shown.  
     			if(!this.getComponent().isEditorVisible){
     				//alert("handle");
     				this.handleDaySelection(e.keyCode,e);
     			}else{
     				if(e.keyCode == Event.KEY_LEFT){
     					if(this.getComponent().isDateEditorLayoutCreated){
     							var value = this.isYearPanelIsActived() ? -5 : -6;
     							this.changeYearMonthPopupValue(e,value);
     						}
     					if(this.getComponent().isTimeEditorLayoutCreated){
     							//todo
     						this.changeTimeDialogPanelState();
     					}
     				}else if(e.keyCode == Event.KEY_RIGHT){
     						if(this.getComponent().isDateEditorLayoutCreated){
     							var value = this.isYearPanelIsActived() ? 5 : 6;
     							this.changeYearMonthPopupValue(e,value);
     						}
     						if(this.getComponent().isTimeEditorLayoutCreated){
     							//todo
     							this.changeTimeDialogPanelState();
     						}
     				}else if(!e.shiftKey && Event.KEY_UP == e.keyCode){
     					if(this.getComponent().isDateEditorLayoutCreated){
     							this.changeYearMonthPopupValue(e,-1);
     					}
     					if(this.getComponent().isTimeEditorLayoutCreated){
     							return true;
     					}
     				}else if(!e.shiftKey && Event.KEY_DOWN == e.keyCode){
     					if(this.getComponent().isDateEditorLayoutCreated){
     						  this.changeYearMonthPopupValue(e,1);
     					}if(this.getComponent().isTimeEditorLayoutCreated){
     							//todo
     							return true;
     					}
     				}
     			}
     			break;
     		case 13:
     			if(this.getComponent().isEditorVisible && !this.getComponent().isTimeEditorLayoutCreated){
     				this.getComponent().hideDateEditor(true);
     				this.getComponent().isDateEditorLayoutCreated = false;
     				var date = new Date(this.getComponent().dateEditorYear,this.getComponent().dateEditorMonth,1);
					this.getComponent().selectDate(date,false,null);
				
     			
     			}else if(this.getComponent().isEditorVisible && this.getComponent().isTimeEditorLayoutCreated){
     				//alert('hide time editor');
     				this.getComponent().hideTimeEditor(true);	
     				 this.getComponent().isTimeEditorLayoutCreated = false;
     			}else if(!this.getComponent().isEditorVisible ){
     				this.getComponent().doCollapse();
     				this.getComponent().showApplyButton = false;
     		  	    this.getComponent().setInputField(
     		       this.getComponent().getSelectedDate()!=null ?
     		       this.getComponent().getSelectedDateString(this.getComponent().params.datePattern)
     		   : "" ,e);
     		   		this.destroy();
     			}

     			break;
     		case 191: // clean
     			if(!this.getComponent().isEditorVisible){
     				this.getComponent().resetSelectedDate();
     			}
     			break;
     		case 84: //t.
     			if(!this.getComponent().isEditorVisible){
     				this.isDateEditorLayoutCreated = false;
     				this.isTimeEditorLayoutCreated = true;
				this.getComponent().showTimeEditor();
     				this.getTimeControlInput().focus();
     				
     			}
     			break;
     		case 89: //y
     			//alert("Y");
     			
     			if(!this.getComponent().isEditorVisible){
     				this.isDateEditorLayoutCreated = true;
     				this.isTimeEditorLayoutCreated = false;
     				this.getComponent().showDateEditor();
     			}else if(this.getComponent().isEditorVisible){
     				if(!this.currentYMPopupMode){
     					this.currentYMPopupMode="M";
     				}else if(this.currentYMPopupMode == "Y"){
     					this.currentYMPopupMode = "M";
     				}else if(this.currentYMPopupMode == "M"){
     					this.currentYMPopupMode="Y";
     				}
     			//	alert(this.currentYMPopupMode);
     			}
     			break;     			
     		case 78: //N
     		{
     			if (this.getComponent().isEditorVisible) return; else this.getComponent().today();
     		}
 			break;     		
     		case 82: //R
     		{
     			if (this.getComponent().isEditorVisible) return; else this.getComponent().resetSelectedDate();
     		}
 			break;     		
     		case 9: //tab
     			
     			break;

     		

            default: break;//alert('Context menu key '+e.keyCode);

        }
          Event.stop(e);
         return false;
	},
	handleOnCollapse : function(){
		if(!this.getComponent().isTimeEditorLayoutCreated && !this.getComponent().isDateEditorLayoutCreated){
			this.destroy();

		}
	}
});
var SipSingleCalendar = {
	init :function(){
		this.LastOpenedSingleCalendarInstance = null;
	},
	singleCalendarOnScrollHandler:function(){
		if(SipSingleCalendar.LastOpenedSingleCalendarInstance && $(SipSingleCalendar.LastOpenedSingleCalendarInstance)){
			$(SipSingleCalendar.LastOpenedSingleCalendarInstance).style.display="none";
		}
	},
	addKeyHandler:function(calendar,handler){
		Event.observe(calendar,handler);
	},
	defaultKalendarHandler:function(e){
		var code = e.keyCode;
	}
	
	
 
}

function customExpand(e,input,button) {
	var cooficient = 250;
	if (this.isVisible) {
		this.doCollapse();
		if (input==this.customInput)
			return true;
	} 
	
	this.skipEventOnCollapse = false;
	if (e && e.type=='click') this.skipEventOnCollapse = true;
	if (!this.params.popup || this.isVisible) return;
	
	var element = $(this.id);
	SipSingleCalendar.LastOpenedSingleCalendarInstance = this.id;
	if (this.invokeEvent("expand", element, e))
	{

		var iframe=null;
		if (Richfaces.browser.isIE6) iframe = $(this.IFRAME_ID);

		var base = $(this.POPUP_ID)
		var baseInput = input;
		
		//custom input
		this.customInput = baseInput;
		
		var baseButton = button;
		
		if (baseInput && baseInput.value!=undefined)
		{
			this.selectDate(baseInput.value, false, {event:e, element:element});
			var field = $(this.INPUT_DATE_ID);
			if (field) {
				field.value = baseInput.value;
			}
		}
		
		//rect calculation
		
		//var offsetBase = jQuery(baseButton).offset(); //Position.cumulativeOffset(baseButton);
		var offsetBaseInput = jQuery(baseInput).offset();
		/*
		if (this.params.showInput)
		{
			var offsetBaseInput = jQuery(baseInput).offset();
			var offsetBase1 = offsetBaseInput; //Position.cumulativeOffset(baseInput);
		
			offsetBase = [offsetBase.left<offsetBase1.left ? offsetBase.left : offsetBase1.left,
						  offsetBase.top<offsetBase1.top ? offsetBase.top : offsetBase1.top];
			var offsetDimInput = Richfaces.Calendar.getOffsetDimensions(baseInput);
	
		}
		*/
	
		var inHeight = (window.innerHeight ? window.innerHeight : document.documentElement.clientHeight );
		var eClientY = e.clientY;
		var v1 = inHeight - eClientY;
		if(!this.defaultDirection){
			this.defaultDirection = this.params.direction;
		}
		/*
		if(v1 <= cooficient){
			this.params.direction="top-right";
		}else{
			this.params.direction = this.defaultDirection;
		}
		var offsetDimBase = Richfaces.Calendar.getOffsetDimensions(base);
		var offsetDimButton = Richfaces.Calendar.getOffsetDimensions(baseButton);
		var offsetTemp = (window.opera ? [0,0] : Position.realOffset(baseButton));
	//	alert("offsetBase:"+offsetBase+" offsetTemp:"+offsetTemp+' scrollTop:'+baseButton.offsetParent.scrollTop+" offsetParent:"+baseButton.offsetParent);
		var o = {left: offsetBaseInput.left,
				 top:  offsetBaseInput.top,
				 width: offsetDimBase.width,
				 height: (offsetDimInput && offsetDimInput.height>offsetDimButton.height ? offsetDimInput.height : offsetDimButton.height)};
	//	alert("top:"+element.style.top);	
	//	alert("left:"+element.style.left);
	*/
		var offsetDimBase = Richfaces.Calendar.getOffsetDimensions(base);
		var offsetDimInput = Richfaces.Calendar.getOffsetDimensions(baseInput);
		element.style.visibility = 'visible';
		element.style.display = '';
		element.style.left = offsetBaseInput.left + 'px';
		element.style.top = v1 <= cooficient ? offsetBaseInput.top-element.offsetHeight + 'px' : offsetBaseInput.top + offsetDimInput.height + 'px';
		

		//Richfaces.Calendar.setElementPosition(element, o, this.params.jointPoint, this.params.direction, this.popupOffset);

		if (iframe)
		{
			iframe.style.left = element.style.left;
			iframe.style.top = element.style.top;
			var edim = Richfaces.Calendar.getOffsetDimensions(element);
			iframe.style.width = edim.width+'px';
			iframe.style.height = edim.height+'px';
			Element.show(iframe);
		}
		if (!this.isRendered) {
			this.render();
		}
		Element.show(element);
		
		this.isVisible = true;

		Event.observe(window.document, "click", this.eventOnCollapse, false);
	}
}