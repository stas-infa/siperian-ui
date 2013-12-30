Calendar.prototype.eventCellOnMouseOver = function (e, obj) {
	var daydata = this.days[parseInt(obj.id.substr(this.DATE_ELEMENT_ID.length),10)];
	if (this.invokeEvent("datemouseover", obj, e, daydata.date) && daydata.enabled)
	{
		if (daydata._month==0) Element.addClassName(obj,'rich-calendar-hover');
	}
};

Calendar.prototype.eventCellOnMouseOut = function (e, obj) {
	var daydata = this.days[parseInt(obj.id.substr(this.DATE_ELEMENT_ID.length),10)];
	if (this.invokeEvent("datemouseout", obj, e, daydata.date) && daydata.enabled)
	{
		if (daydata._month==0) Element.removeClassName(obj,'rich-calendar-hover');
	}
};

Calendar.prototype.render = function() {
	this.isRendered = true;
	this.todayDate = new Date();		
	
	var currentYear = this.getCurrentYear();
	var currentMonth = this.getCurrentMonth();
	
	var todayflag = (currentYear == this.todayDate.getFullYear() && currentMonth == this.todayDate.getMonth());
	var todaydate =  this.todayDate.getDate();
	
	var selectedflag = this.selectedDate && (currentYear == this.selectedDate.getFullYear() && currentMonth == this.selectedDate.getMonth())
	var selecteddate = this.selectedDate && this.selectedDate.getDate();

	var wd = getDay(this.currentDate, this.params.firstWeekDay);
	var currentMonthDays = daysInMonthByDate(this.currentDate);
	var previousMonthDays = daysInMonth(currentYear, currentMonth-1);
	
	var p=0;
	var month=-1;
	this.days = [];
	var dayCounter = previousMonthDays  - wd + 1;
	
	// previuos month days
	if (wd>0) while (dayCounter<=previousMonthDays)
	{
		this.days.push({day:dayCounter, isWeekend: this.isWeekend(p), _month:month}); dayCounter++; p++;
	}
		
	dayCounter = 1;
	month=0;
	
	this.firstDateIndex = p;

	// current month days
	if (this.daysData && this.daysData.index[currentYear+'-'+currentMonth]!=undefined)
	{
		var idx = this.daysData.index[currentYear+'-'+currentMonth];
		if (this.daysData.startDate.getFullYear()==currentYear && this.daysData.startDate.getMonth()==currentMonth)
		{
			var firstDay = firstDay=(this.daysData.days[idx].day ? this.daysData.days[idx].day : this.daysData.startDate.getDate());
			while (dayCounter<firstDay)
			{
				this.days.push({day:dayCounter, isWeekend:this.isWeekend(p%7), _month:month});
			
				dayCounter++;
				p++;
			}
		}
		
		var len = this.daysData.days.length;
		var obj;
		var flag;
		while (idx<len && dayCounter<=currentMonthDays)
		{
			flag = this.isWeekend(p%7);
			obj = this.daysData.days[idx];
			obj.day = dayCounter;
			obj.isWeekend = flag;
			obj._month = month;
			this.days.push(obj);
			idx++;
			dayCounter++;
			p++;
		}
	}
	while (p<42)
	{
		if (dayCounter>currentMonthDays) {dayCounter=1; month=1;}
		this.days.push({day:dayCounter, isWeekend: this.isWeekend(p%7), _month:month});
		dayCounter++;
		p++;
	}
	
	// render
	this.renderHF();
	
	//days render
	p=0;
	var element;
	var dataobj;
	var wn;
	if (this.params.showWeeksBar) wn = weekNumber(currentYear, currentMonth, this.params.minDaysInFirstWeek, this.params.firstWeekDay); /// fix it
	this.selectedDayElement=null;
	var weekflag=true;

	var e;
	
	var boundaryDatesModeFlag = (this.params.boundaryDatesMode == "scroll" || this.params.boundaryDatesMode == "select");
	
	this.todayCellId = this.clearEffect(this.todayCellId, this.highlightEffect);
	this.selectedDateCellId = this.clearEffect(this.selectedDateCellId, this.highlightEffect2);
	
	var obj = $(this.params.weekNumberBarId+"1");
	for (var k=1;k<7;k++)
	{
		//
		dataobj = this.days[p];
		
		element = obj.firstChild;
		var weeknumber; 

		// week number update			
		if (this.params.showWeeksBar)
		{
			if (weekflag && currentMonth==11 &&
			   (k==5||k==6) &&
			   (dataobj._month==1 || (currentMonthDays-dataobj.day+1)<this.params.minDaysInFirstWeek) )
			{
				wn=1;
				weekflag=false;
			}
			weeknumber = wn;
		    element.innerHTML = this.evaluateMarkup(this.params.weekNumberMarkup, {weekNumber: wn++, elementId:element.id, component:this} );
		    if (k==1&&wn>52) wn=1;
		    element = element.nextSibling;
		}
		
		var weekdaycounter = this.params.firstWeekDay;
		var contentElement = null;

		while (element)
		{
			dataobj.elementId=element.id;
			dataobj.date=new Date(currentYear, currentMonth+dataobj._month, dataobj.day);
			dataobj.weekNumber = weeknumber;
			dataobj.component = this;
			dataobj.isCurrentMonth = (dataobj._month==0);
			dataobj.weekDayNumber = weekdaycounter;

			// call user function to get day state
			if (dataobj.enabled != false) dataobj.enabled = this.params.isDayEnabled(dataobj);
			// call user function to custom class style
			if (!dataobj.styleClass) dataobj.customStyleClass = this.params.dayStyleClass(dataobj);
			else
			{
				var styleclass = this.params.dayStyleClass(dataobj);
				dataobj.customStyleClass = dataobj.styleClass;
				if (styleclass) dataobj.customStyleClass += " " + styleclass;
			}

			contentElement = (this.customDayListMarkup ? element.firstChild : element);
			contentElement.innerHTML = this.evaluateMarkup(this.params.dayListMarkup, dataobj );

			if (weekdaycounter==6) weekdaycounter=0; else weekdaycounter++;
			
			var classNames = this.dayCellClassName[p];
			
			// class styles
			if (dataobj._month!=0) 
			{
				classNames+=' rich-calendar-boundary-dates';
				if (!this.params.disabled && !this.params.readonly && boundaryDatesModeFlag)
				{
					classNames+=' rich-calendar-btn';
				}
			}
			else 
			{
				if (todayflag && dataobj.day==todaydate) 
				{
					this.todayCellId = element.id;
					this.todayCellColor = this.getCellBackgroundColor(element);
					classNames+=" rich-calendar-today";
				}
			
				if (selectedflag && dataobj.day==selecteddate)
				{
					this.selectedDateCellId = element.id;
					this.selectedDateCellColor = this.getCellBackgroundColor(element);
					classNames+=" rich-calendar-select";
				} 
				if (!this.params.disabled && !this.params.readonly && dataobj.enabled) classNames+=' rich-calendar-btn';

				// add custom style class
				if (dataobj.customStyleClass) 
				{
					classNames+=' '+dataobj.customStyleClass;
				}
			}
			element.className = classNames;
			
			p++;

			dataobj = this.days[p];
			element=element.nextSibling;
		}
		obj = obj.nextSibling;
	}
};

Calendar.prototype.selectDate = function(date, noUpdate, eventData) {
	if (!eventData)
	{
		eventData = {event: null, element: null};
	}
	var oldSelectedDate = this.selectedDate;
	var newSelectedDate;
	if (date)
	{
		if (typeof date=='string') 
		{
			date = Richfaces.Calendar.parseDate(date,this.params.datePattern, this.params.monthLabels, this.params.monthLabelsShort);
		}
		newSelectedDate = date;
	}
	else
	{
		newSelectedDate = null;
	}

	// fire user event
	var flag = true;
	var isDateChange = false;
	if ( (oldSelectedDate - newSelectedDate) && (oldSelectedDate!=null || newSelectedDate!=null) )
	{
		isDateChange = true;
		flag = this.invokeEvent("dateselect", eventData.element, eventData.event, date);
	}	
	
	if (flag)
	{		   
		if (newSelectedDate!=null)
		{
			if (newSelectedDate.getMonth()==this.currentDate.getMonth() && newSelectedDate.getFullYear()==this.currentDate.getFullYear())
			{
				this.selectedDate = newSelectedDate;
				if (!oldSelectedDate || (oldSelectedDate - this.selectedDate))
				{
					// find cell and change style class
					var e = $(this.DATE_ELEMENT_ID+(this.firstDateIndex + this.selectedDate.getDate()-1));
					
					this.clearEffect(this.selectedDateCellId, this.highlightEffect2, "rich-calendar-select", (this.params.disabled || this.params.readonly ? null : "rich-calendar-btn"));
					this.selectedDateCellId = e.id;
					this.selectedDateCellColor = this.getCellBackgroundColor(e);

					Element.removeClassName(e, "rich-calendar-hover");
					Element.addClassName(e, "rich-calendar-select");

					this.renderHF();
				}
				else if (this.timeType!=0) this.renderHF();
			}
			else
			{
				//RF-5600
				this.selectedDate = newSelectedDate;

				// change currentDate and call this.onUpdate();
				if (this.changeCurrentDate(newSelectedDate.getFullYear(), newSelectedDate.getMonth(), noUpdate))
				{
					//this.selectedDate = newSelectedDate;
				} else {
					this.selectedDate = oldSelectedDate;
					isDateChange = false;
				}
			}
		}
		else
		{
			this.selectedDate = null;

			this.clearEffect(this.selectedDateCellId, this.highlightEffect2, "rich-calendar-select", (this.params.disabled || this.params.readonly ? null : "rich-calendar-btn"));
			
			if (this.selectedDateCellId)
			{
				this.selectedDateCellId = null;
				this.renderHF();					
			}
			
			var date = new Date();
			if (this.currentDate.getMonth()==date.getMonth() && this.currentDate.getFullYear()==date.getFullYear())
			{
				this.renderHF();
			}
			
			var todayControlMode = this.todayControlMode;
			this.todayControlMode = '';
			this.today(noUpdate, true);
			this.todayControlMode = todayControlMode;
		}
		
		// call user event
		if (isDateChange)
		{
			this.invokeEvent("dateselected", eventData.element, eventData.event, this.selectedDate);
			if (!this.showApplyButton)
			{
				this.setInputField(this.selectedDate!=null ? this.getSelectedDateString(this.params.datePattern) : "", eventData.event);
			}
		}
	}
	return isDateChange;			
};

CalendarView.closeControl = function (context) {
	return (context.calendar.params.popup ? CalendarView.getControl("", CalendarView.toolButtonAttributes, "close", "false") : "");
};

CalendarView.footer = [
   	new E('table',{'border': '0', 'cellpadding': '0', 'cellspacing': '0', 'width': '100%'},
   		[
   			new E('tbody',{},
   			[
   				new E('tr',{},
   				[
   					new E('td',{'class': 'rich-calendar-toolfooter', 'style':function(context){return (this.isEmpty ? 'display:none;' : '');}},
   					[
   						new ET(function (context) { return Richfaces.evalMacro("selectedDateControl", context)})
   					]),
   					new E('td',{'class': 'rich-calendar-toolfooter', 'style':function(context){return (this.isEmpty ? 'display:none;' : '');}},
   					[
   						new ET(function (context) { return Richfaces.evalMacro("cleanControl", context)})
   					]),
   					new E('td',{'class': 'rich-calendar-toolfooter', 'style':function(context){return (this.isEmpty ? 'display:none;' : '');}},
   					[
   						new ET(function (context) { return Richfaces.evalMacro("timeControl", context)})
   					]),
   					new E('td',{'class': 'rich-calendar-toolfooter', 'style':function(context){return (this.isEmpty ? 'display:none;' : '')+(context.calendar.params.disabled || context.calendar.params.readonly || !context.calendar.showApplyButton ? 'background-image:none;' : '');}},
   							[
   								new ET(function (context) { return Richfaces.evalMacro("todayControl", context)})
   							]),
   					new E('td',{'class': 'rich-calendar-toolfooter', 'style': 'background-image:none;', 'width': '100%'}, []),
   					new E('td',{'class': 'rich-calendar-toolfooter rich-calendar-apply', 'style':function(context){return (this.isEmpty ? 'display:none;' : '')+'background-image:none;';}},
   					[
   						new ET(function (context) { return Richfaces.evalMacro("applyControl", context)})
   					])
   				])
   			])
   		]
   	)];

CalendarContext = Class.create();
Object.extend(CalendarContext.prototype, {
    initialize: function(calendar) {
    	this.calendar=calendar;
		this.monthLabels=calendar.params.monthLabels;
		this.monthLabelsShort=calendar.params.monthLabelsShort;
		this.weekDayLabels=calendar.params.weekDayLabels;
		this.weekDayLabelsShort=calendar.params.weekDayLabelsShort;
		this.controlLabels=calendar.params.labels;
	},
	nextYearControl: CalendarView.nextYearControl,
	previousYearControl: CalendarView.previousYearControl,
	nextMonthControl: CalendarView.nextMonthControl,
	previousMonthControl: CalendarView.previousMonthControl,
	currentMonthControl: CalendarView.currentMonthControl,
	selectedDateControl: CalendarView.selectedDateControl,
	cleanControl: CalendarView.cleanControl,
	timeControl: CalendarView.timeControl,
	todayControl: CalendarView.todayControl,
	closeControl: CalendarView.closeControl,
	applyControl: CalendarView.applyControl,
	timeEditorFields: CalendarView.timeEditorFields,
	timeEditorLayout: CalendarView.timeEditorLayout
});