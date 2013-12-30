if (!window.Richfaces) window.Richfaces = {};
RichFaces.SipComboBox = Class.create();


RichFaces.SipComboBox.prototype = {
	
	initialize: function(combobox, listId, parentListId, valueFieldId, fieldId, buttonId, buttonBGId, shadowId, commonStyles,  userStyles, 
						 listWidth, listHeight, itemsText, directInputSuggestions, filterNewValue, 
						 selectFirstOnUpdate, onlistcall, onlistclose, onselected, defaultMessage, isDisabled, value,
						 showDelay, hideDelay,spacerImg,countOfItems,behavourStrategy) {
		
		this.directInputSuggestions = directInputSuggestions;
		this.filterNewValue = filterNewValue;
		this.comboboxId = combobox;
		this.combobox = $(this.comboboxId); 
		this.previousValue = "";
		this.spacerImg = spacerImg;
	
		this.comboValue = document.getElementById(valueFieldId); 
		this.oldValue = this.comboValue.value;
		this.field = document.getElementById(fieldId);  
		this.virtualField = document.getElementById(this.comboboxId+"comboboxFieldVirtual");
		this.virtualValue = "";
		this.doKeyPress = true;
//		this.field.setAttribute("readonly",true);
		this.tempItem;
	
		this.BUTTON_WIDTH = 17; //px
		
		this.classes = Richfaces.mergeStyles(userStyles,commonStyles.getCommonStyles());
		
		
		this.button = document.getElementById(buttonId);   
		this.buttonBG = document.getElementById(buttonBGId);  
		
		this.setInputWidth();
		
		//listWidth = (!listWidth) ? this.getCurrentWidth() : listWidth;
		this.isSiperianMode = (behavourStrategy == "siperian");

        var newListWidth = listWidth ? listWidth: this.field.parentNode.style.width;
		this.comboList = new Richfaces.SipComboBoxList(this.spacerImg,combobox,listId, parentListId, selectFirstOnUpdate, filterNewValue, this.classes.combolist, newListWidth,
													listHeight, itemsText, onlistcall, onlistclose, fieldId, shadowId, showDelay, hideDelay,countOfItems,null,(behavourStrategy == "siperian"));
		this.defaultMessage = defaultMessage;
		
		if (value) {
			var item = this.comboList.findItemBySubstr(value);
			if (item) {
				this.comboList.doSelectItem(item);
			}
			// RF-5056 
			this.comboValue.value = value; 
			
		} else {
			if (this.defaultMessage) {
				this.applyDefaultText();
			}
		}
		this.onselected = onselected;
		this.isSelection = true;
		this.isDisabled = isDisabled;
		if (this.onselected) {
			this.combobox.observe("rich:onselect", this.onselected);
		}
		if (this.isDisabled) {
			this.disable(); //TODO rename to 'disable'
		}
		
		if (Richfaces.browser.isIE6) {
			this.comboList.createIframe(this.comboList.listParent.parentNode, listWidth, this.combobox.id, 
										"sip-combobox-list-width sip-combobox-list-scroll sip-combobox-list-position");										
		}
		this.combobox.component = this;


	
		this.initHandlers();
		this["rich:destructor"] = "destroy";
	},
	
	destroy: function () {
	
		Event.stopObserving(this.combobox, 'rich:onselect');
		
		Event.stopObserving(this.button, 'click');
		Event.stopObserving(this.button, 'mouseup');
		Event.stopObserving(this.button, 'mousedown');
		Event.stopObserving(this.button, 'mouseover');
		Event.stopObserving(this.button, 'mouseout');
		
		Event.stopObserving(this.field, 'keydown');
		Event.stopObserving(this.field, 'blur');
		Event.stopObserving(this.field, 'focus');
		Event.stopObserving(this.field, 'keyup');
		
		Event.stopObserving(this.comboList.listParent, 'mousedown');
		Event.stopObserving(this.comboList.listParent, 'mouseup');
		Event.stopObserving(this.comboList.listParent, 'mousemove');
		Event.stopObserving(this.comboList.listParent, 'click');
		
		this.comboValue = null;
		this.button = null;
		this.buttonBG = null;
		this.field = null;
		this.virtualField = null;
		this.classes = null;
		
		delete this.comboList;
		this.combobox.component = null;
		this.combobox = null;
	},
	addChar:function(keyChar){
		var enteredValue = this.field.value ? this.field.value.substring(0,1) : ''; 
		this.virtualValue = this.virtualValue + enteredValue;
		LOG.debug("addChar - "+keyChar+",result string ="+this.virtualValue);
	},
	removeChar:function(){
		if(this.virtualValue){
			this.virtualValue = this.virtualValue.substring(0,this.virtualValue.length-1);
		}
		LOG.debug("removeChar - ,result string ="+this.virtualValue);
	},
	setVirtualvalue:function(newValue){
		this.virtualValue = newValue;
	},
	initHandlers : function() {
		Event.observe(this.button, "click", this.buttonClickHandler.bindAsEventListener(this));
		Event.observe(this.button, "mouseup", this.buttonMouseUpHandler.bindAsEventListener(this));
		Event.observe(this.button, "mousedown", this.buttonMousedownHandler.bindAsEventListener(this));
		Event.observe(this.button, "mouseover", this.buttonMouseOverHandler.bindAsEventListener(this));
		Event.observe(this.button, "mouseout", this.buttonMouseOutHandler.bindAsEventListener(this));
		
		Event.observe(this.field, "keydown", this.fieldKeyDownHandler.bindAsEventListener(this));
		Event.observe(this.field, "blur", this.fieldBlurHandler.bindAsEventListener(this));
		Event.observe(this.field, "focus", this.fieldFocusHandler.bindAsEventListener(this));
		Event.observe(this.field, "keyup", this.dataUpdating.bindAsEventListener(this));
		
		Event.observe(this.comboList.listParent, "mousedown", this.listMousedownHandler.bindAsEventListener(this));
		Event.observe(this.comboList.listParent, "mouseup", this.listMouseUpHandler.bindAsEventListener(this));
		Event.observe(this.comboList.listParent, "mousemove", this.listMouseMoveHandler.bindAsEventListener(this));
		Event.observe(this.comboList.listParent, "click", this.listClickHandler.bindAsEventListener(this));
	},
	
	setInputWidth : function() {
		
		if (this.field.parentNode.style.width.indexOf('%') == -1) {
			var width = (parseInt(this.field.parentNode.style.width) - this.BUTTON_WIDTH) - Richfaces.getBorderWidth(this.field, "lr") - Richfaces.getBorderWidth(this.button, "lr") - Richfaces.getPaddingWidth(this.field,"lr");
			this.field.style.width = Math.max(20, width) + "px";
		}else {
			var pw = parseInt(this.field.parentNode.style.width);
			this.field.style.width = Math.max(20, pw) + "px";
		}
	},
	
	buttonClickHandler : function(event) {
		this.comboList.isOpenedByClick =(this.isSiperianMode && (this.field.value==null || this.field.value.length==0));
		this.virtualValue = "";
		if (this.comboList.visible()) {
			this.comboList.hideWithDelay();
		} else {
			this.comboList.createDefaultList();
			this.comboList.showWithDelay();
			if (this.comboList.selectedItem) {
				this.comboList.scrollingUpToItem(this.comboList.selectedItem);
			}
			this.comboList.isList = false;
		}
	},
	
	buttonMouseUpHandler : function(e) {
		this.buttonBG.className = "sip-combobox-font sip-combobox-button-background sip-combobox-button";
		this.button.className = this.classes.button.classes.active + " sip-combobox-button-hovered";
		
		var styleCss = this.classes.button.style;
		if(styleCss && !styleCss.active.blank()) {
			Element.setStyle(this.button,styleCss.active);
		}
		
		this.field.focus();	
	},
	
	buttonMousedownHandler : function(e) {
		this.buttonBG.className = "sip-combobox-font sip-combobox-button-pressed-background sip-combobox-button";
		this.button.className = this.classes.button.classes.active + " sip-combobox-button-hovered";
		
		var styleCss = this.classes.button.style;
		if(styleCss && !styleCss.active.blank()) {
			Element.setStyle(this.button,styleCss.active);
		}
		
		this.comboList.isList = true;
	},
	
	buttonMouseOverHandler : function(e) {
				var classCss = this.classes.button.classes;
		var iconStyles = this.classes.buttonicon.style;
		var styleCss = this.classes.button.style;
		if (this.isActive()) { 
			this.button.className= classCss.active + " " + classCss.hovered;
			
			if(styleCss && !styleCss.active.blank()) {
				Element.setStyle(this.button,styleCss.active);
			}
			
			if (iconStyles && !iconStyles.active.blank()) {
				Element.setStyle(this.button,{backgroundImage : iconStyles.active});
			}	
		
		} else {
			this.button.className = classCss.normal + " " + classCss.hovered;
			
			if(styleCss && !styleCss.normal.blank()) {
				Element.setStyle(this.button,styleCss.normal);
			}	
			
			if (iconStyles && !iconStyles.normal.blank()) {
				Element.setStyle(this.button,{backgroundImage : iconStyles.normal});
			}	
		}
	},
	
	buttonMouseOutHandler : function(e) {
	
		var classCss = this.classes.button.classes;
		var styleCss = this.classes.button.style;
		var iconStyles = this.classes.buttonicon.style;
		
		if (this.isActive()) { 
			this.button.className= classCss.active;
			
			if(styleCss && !styleCss.active.blank()) {
				Element.setStyle(this.button,styleCss.active);
			}
			
			if (iconStyles && !iconStyles.active.blank()) {
				Element.setStyle(this.button,{backgroundImage : iconStyles.active});
			}	
		} else {
			this.button.className = classCss.normal;
			
			if(styleCss && !styleCss.normal.blank()) {
				Element.setStyle(this.button,styleCss.normal);
			}	
			
			if (iconStyles && !iconStyles.normal.blank()) {
				Element.setStyle(this.button,{backgroundImage : iconStyles.normal});
			}	
		}
	},
	
	listMouseMoveHandler : function(event) {
		//changes item's decoration
		var item = event.target;
		if(Element.match(item,"span") && jQuery(item).hasClass('sip-combobox-item')) {
			if (item && this.tempItem != item ) {
				this.comboList.doActiveItem(item);
			}
			this.tempItem = item;
		}
		
	},
	
	listMousedownHandler : function(event) {
		//https://jira.jboss.org/jira/browse/RF-4050
		if (!Prototype.Browser.Firefox) {
			if (!Element.match(event.target,"span")) {
				this.clickOnScroll = true;
			}
		} 
		this.comboList.isList = true;
	},
	
	listMouseUpHandler : function(e) {
		//https://jira.jboss.org/jira/browse/RF-4050
		//if (window.getSelection) {
			//if (window.getSelection().getRangeAt(0).toString() != '') {
				this.field.focus();	
				this.comboList.isList = false;
			//}
		//}
	},
	
	listClickHandler : function(event) {
		LOG.debug("listClickHandler - begin");
		this.isSelection = false;
		//FF
		//this.field.focus();
	
		if (!this.comboList.activeItem && this.comboList.selectedItem) {
			this.comboList.activeItem = this.comboList.selectedItem;
		}
		this.setValue(true);
		this.comboList.hideWithDelay();
	},
	
	fieldKeyDownHandler : function(event) {
		this.processOnblur = true;
		switch (event.keyCode) {
			case Event.KEY_RETURN : 
				LOG.debug("key_return");
				var choosenItem=null;
				if (this.comboList.activeItem) {
					 if (this.comboList.activeItem.text) {
						choosenItem = jQuery(this.comboList.activeItem).text();
					 } else { 
						 choosenItem = this.field.value; 
					 }
				}else {
					choosenItem = this.field.value; 
				}
				if(this.isSiperianMode) {
					this.comboList.selectedItem = (!this.comboList.isListOpen && this.comboList.selectedItem) ? this.comboList.selectedItem :
								this.comboList.createSelectedItemAsDomElement(choosenItem);
					if(!this.comboList.activeItem){
						//console.log(this.comboList.selectedItem.innerHTML);
						this.comboList.activeItem = this.comboList.selectedItem;
					}
				}else {
					var selected = this.comboList.getSelected();
					this.comboList.activeItem = selected ? selected : this.comboList.createSelectedItemAsDomElement(choosenItem);
				}
				
				this.setValue(true);
				if(this.isSiperianMode){
					this.comboValue.value = this.field.value; 
					this.comboList.defaultValue2="";
					RichFaces.SipComboBox.textboxSelect(this.field, this.field.value.length,this.field.value.length);
				}
				//if(true/*!this.isSiperianMode*/){ - why it was added?
				if(this.comboList.isListOpen){
					this.comboList.hideWithDelay();
				}
				this.field.focus();
				this.stopEvent(event);
				//}
				Event.stop(event); // It is necessary for a cancelling of sending form at selecting item

				this.virtualValue=""; // We clear search string

				break;
			case Event.KEY_DOWN : 
				LOG.debug("fieldKeyDownHandler - KEY_DOWN"+this.comboList.isListOpen);
				if(this.comboList.isListOpen){
					LOG.debug("before fieldKeyDownHandler - moveActiveItem");
					if(!this.comboList.activeItem){
					  if(this.comboList.getItems() && this.comboList.getItems().length!=0 ){
					  	this.comboList.doActiveItem(this.comboList.getItems()[0]);
					  }
					}else{
						this.comboList.moveActiveItem(event);
					}
				}else {
					LOG.debug("fieldKeyDownHandler - KEY_DOWN - siperianMode");
					this.comboList.moveNotActiveItem(event, this.field.value);
				}

				this.virtualValue=""; // We clear search string

				break;
			case Event.KEY_UP :
				if(this.comboList.isListOpen){
					this.comboList.moveActiveItem(event);
				}else {
					this.comboList.moveNotActiveItem(event, this.field.value);
				}

				this.virtualValue=""; // We clear search string

				break; 
			case Event.KEY_ESC : 
				this.field.value = this.field.value; //field must lose focus
				this.comboList.hideWithDelay();
				LOG.debug("KEY_ESC - value="+this.field.value);
				LOG.debug("KEY_ESC - defaultValue2="+this.comboList.defaultValue2);
				
				if(this.isSiperianMode){
					var value = this.comboList.defaultValue2;
					if(value && value.length > 0){
						this.field.value = value;
						LOG.debug("KEY_ESC - finisehd value="+this.field.value);
						RichFaces.SipComboBox.textboxSelect(this.field, 0,this.field.value.length);
					}
					this.comboList.defaultValue2="";
				}
				
				this.virtualValue=""; // We clear search string

				break;
			case 8:
				if(this.isSiperianMode){
					this.field.value = this.field.value.substring(0,this.field.value.length);
					this.comboList.activeItem = null;
					this.removeChar();
					this.doKeyPress = false;
					LOG.debug("	fieldKeyDownHandler -Removed char");
					this.stopEvent(event);
					
					
				}
				break;
			default:
//				alert("Deafult keys!");
				break;
		}
	},
	
	stopEvent:function(eventObject){
			if (eventObject.stopPropagation) eventObject.stopPropagation();
			if (eventObject.preventDefault) eventObject.preventDefault();
			if (eventObject.preventCapture) eventObject.preventCapture();
	   		if (eventObject.preventBubble) eventObject.preventBubble();
	   		Event.stop(eventObject);
	},
	handlerVirtualField:function(event){
		if(event.keyCode == 12){
				this.virtualField.value = this.virtualField.value.substring(0,this.virtualField.value.length - 1);
		}else if (RichFaces.SipComboBox.SPECIAL_KEYS.indexOf(event.keyCode) == -1) {
			this.virtualField.value = this.virtualField.value + event.keyCode;
		}
		LOG.debug("handlerVirtualField - virtual value = "+this.virtualField.value);
	},
	fieldFocusHandler : function() {
		this.comboList.defaultValue2=this.field.value;

		this.doActive();
		if ((this.field.value == this.defaultMessage) && (this.comboValue.value == "")) {
			this.field.value = "";
		} else {
			if (this.isSelection) {
				RichFaces.SipComboBox.textboxSelect(this.field, 0, this.field.value.length);
			}
			this.isSelection = true;
		}
	},
	
	fieldBlurHandler : function(event) {
		if (!this.comboList.isList) {
			this.enable();
			var value = this.field.value; 	
			
			
			var foundValueLength = this.comboList.dataFilter(value);
			
			if(value.length > 0 && foundValueLength == 0) {
				var restoredValue =  this.oldValue ?  this.oldValue : this.comboList.defaultValue2;
				value = restoredValue;
				this.field.value = value;
			} 
			
			if (value.length == 0) {
				this.applyDefaultText();
			} else {
//				if (this.comboList.getItems().length==0){
//					this.comboList.createDefaultList();
//				}
				var item = this.comboList.createSelectedItemAsDomElement(this.field.value);
				if (item != null) {
					this.comboList.activeItem = item;
				}
				// var item = this.comboList.findItemBySubstr(value);
//				if (item == null && this.comboList.activeItem) {
//					item = this.comboList.activeItem;
//				if (item == null && this.comboList.activeItem) {
//					item = this.comboList.activeItem;
//				}
				if (item) {
					this.comboList.doSelectItem(item);
					if (this.processOnblur) {
						this.setValue(true);
					}
					//this.combobox.fire("rich:onselect", {});
				}
			}

			if(this.comboList.isListOpen){
				this.comboList.hideWithDelay();
			}
		} else {
			this.doActive();
		}
		
		if (this.clickOnScroll) {
			//after clicking on scroll (IE)
			this.field.focus();
			this.comboList.isList = false;
			this.clickOnScroll = false;
		}
		this.setVirtualvalue("");

	},
	
	dataUpdating : function(event) {
		LOG.debug("data Updating begin");
		if (RichFaces.SipComboBox.SPECIAL_KEYS.indexOf(event.keyCode) == -1) {
			if (!this.isSiperianMode) {
				if (this.comboList.isListOpen) {
					this.comboList.hideWithDelay();
				}
				
				var allFoundLength = this.comboList.dataFilter(this.field.value);
				if (allFoundLength > 0) {
					this.comboList.showWithDelay();
				}
				return;
			}
			
			if(event.keyCode!=8){
				this.addChar(event.keyCode);
			}else if(event.keyCode == 8){
				if(this.virtualValue.length == 0){
					this.field.value = "";
					this.comboList.selectedItem = null;
					this.comboList.createDefaultList();
					this.comboList.showWithDelay();
					return;
				}
			}

			if (this.filterNewValue) {
//				this.comboList.hideWithDelay();
				LOG.debug("DataUpdating "+this.virtualValue);
				var allFoundLength = this.comboList.dataFilter(this.virtualValue);
				
				if (allFoundLength==0){
				  	// Chigrinets: Here we need to restore prevoius state
				  	LOG.debug('Chigrinets: vrtualValue before '+this.virtualValue);
				  	this.removeChar();
	  				if(this.virtualValue.length == 0){
	  					this.field.value = "";
	  					this.comboList.selectedItem = null;
	  					this.comboList.createDefaultList();
	  					this.comboList.showWithDelay();
	  					return;
	  				}
				  	LOG.debug('Chigrinets: vrtualValue after cut '+this.virtualValue);
				  	allFoundLength = this.comboList.dataFilter(this.virtualValue);
				  	LOG.debug('Chigrinets: number of found items on second search '+allFoundLength);
        			}
				
				if (allFoundLength > 0 && this.comboList.getItems() && this.comboList.getItems().length != 0) {
					LOG.debug("dataUpdating list value= "+this.comboList.getItems().length);
					var isSearchSuccessful = true;
					LOG.debug("datUpdating isSearchSuccessul = true");
				
					var item = this.comboList.findItemBySubstr(this.virtualValue);
					LOG.debug("datUpdating found item = "+item);
					var innerHTML = jQuery(item).text();
					if(item){
						if (!this.comboList.visible()) {
							this.comboList.showWithDelay();
						}
						LOG.debug("datUpdating item.innerHTML="+innerHTML);
					  this.comboList.dataFilter2(this.virtualValue,innerHTML); 
					  //Make actived first item in list
//					  if(this.comboList.getItems() && this.comboList.getItems().length!=0 ){
//					  	this.comboList.doActiveItem(this.comboList.getItems()[0]);
//					  }
//					  this.comboList.showWithDelay();
					  if(allFoundLength == 1){
					  	LOG.debug("dataUpdating - found 1 element close");
					  	this.field.value = innerHTML;
					  	this.comboList.selectedItem = this.comboList.createSelectedItemAsDomElement(this.field.value);
//					  	this.setVirtualValue(this.field.value);
					  	RichFaces.SipComboBox.textboxSelect(this.field, 0,this.field.value.length);
					  	this.comboList.hideWithDelay();
					  	 
					  }else{
					 	 var value = innerHTML;
					 	 var startInd = 0;
				 	 	 var endInd = this.virtualValue.length;
					  	this.field.value = value;
					  	LOG.debug("dataUpdating - Set value to the field :value="+this.field.value);
//					  	alert("Item innerHtml = "+innerHTML);
//					  	alert("Item = "+item);
					 	 RichFaces.SipComboBox.textboxSelect(this.field, startInd, endInd);
					  }
//						this.virtualValue = 
//							this.comboList.getItems().shift();
					}else if(this.isSiperianMode){
						if(this.field.value && this.field.value.length > 0){
							this.field.value = this.field.value.substring(0,this.field.value.length-1);
							this.removeChar();
						}
						return;
//					if (!this.comboList.visible()) {
//						this.comboList.showWithDelay();
//					}
				
					}
//					this.comboList.showWithDelay();	
						
				}else if(this.isSiperianMode){
					LOG.debug("dataUpdating - Search not successfully!");
//					Event.stop(event);
					this.stopEvent(event);
					if(this.field.value && this.field.value.length > 0){
						this.field.value = this.field.value.substring(0,this.field.value.length-1);
					}
					this.removeChar();
					this.comboList.createDefaultList();
					this.comboList.showWithDelay();
					return;
				}
			} else {
				LOG.debug("DataUpdating else"+this.field.value);
				if (!this.comboList.visible()) {
					this.comboList.showWithDelay();
				}
				var item = this.comboList.findItemBySubstr(this.field.value);
				if (item) {
					this.comboList.doActiveItem(item);
					this.comboList.scrollingUpToItem(this.comboList.activeItem);
					isSearchSuccessful = true;
				}
			}
			
			if (this.isValueSet(event) && isSearchSuccessful) {
				LOG.debug("dataUpdating () - this.isValueSet(event) && isSearchSuccessful");
				this.setValue(false);
				this.comboValue.value = this.field.value; 
			}
            this.comboList.moveAndResize();
		}
	},
	
	wasTextDeleted : function(event) {
		if ((event.keyCode == Event.KEY_BACKSPACE) 
			|| (event.keyCode == Event.KEY_DELETE) 
			|| (event.ctrlKey && (event.keyCode == 88))) {
			return true;
		}
		return false;
	},
	
	isValueSet : function(event) {
		/*if (this.field.prevValue) {
			if (this.field.prevValue.toLowerCase() != this.field.value.toLowerCase()) {
				return true;
			}
			return false;
		}
		return true;*/
		if (this.wasTextDeleted(event) 
			|| (event.keyCode == 17) 
			|| event.altKey 
			|| event.ctrlKey 
			|| event.shiftKey) {
			return false;
		}
		return true;
	},
	
	setValue : function(toSetOnly) {
		LOG.debug("setValue() - begin");
		if (!this.comboList.activeItem) {
			LOG.debug("setValue() !this.comboList.activeItem- return;");
			return;
		}

		var value = jQuery(this.comboList.activeItem).text();

		LOG.debug("setValue() value= "+value);
//		alert("set value:"+value+","+toSetOnly);
		if (toSetOnly) {
			LOG.debug("toSetOnly = true");
			var oV = this.field.value; 
			if (oV == value) {
				if (Prototype.Browser.Gecko) {
					this.field.value = "";
					this.comboValue.value = "";
				}
			}
			var selValue = this.comboList.selectedItem ? jQuery(this.comboList.selectedItem).text() : null;
			if (value.indexOf(this.virtualValue)==-1){
				value = selValue;
			}
			
			if (value == this.oldValue) {
				this.comboValue.value = value;
				this.field.value = value;
				this.comboList.doSelectItem(this.comboList.activeItem);
				return; 
			}

			this.field.prevValue = value;
			this.field.value = value;
			this.comboValue.value = value;
			this.comboList.doSelectItem(this.comboList.activeItem);
			this.combobox.fire("rich:onselect", {});
			this.oldValue = this.comboValue.value;
			this.processOnblur = false;
		} else {
			LOG.debug("setValue() - this.directInputSuggestions ="+this.directInputSuggestions);
			if (!this.isSiperianMode && this.directInputSuggestions) {
				var startInd = this.field.value.length; 
				var endInd = value.length;
				this.field.value = value;
				LOG.debug("select - startInd="+startInd+" endInd = "+endInd+"value="+this.field.value+",this.field.prevValue="+this.field.prevValue+",value="+value);
				RichFaces.SipComboBox.textboxSelect(this.field, startInd, endInd);
			} 
		}
	},
	
	applyDefaultText : function() {
		this.field.value = this.defaultMessage;
		this.comboValue.value = "";
	},
	
	isActive : function() {
		return (this.field.className == this.classes.field.classes.active); 
	},
	
	doActive : function() {
		if (this.button.className.indexOf(this.classes.button.classes.hovered) != -1) {
			this.button.className = this.classes.button.classes.active + " " + this.classes.button.classes.hovered;
		} else {
			this.button.className = this.classes.button.classes.active ;
		}
		
		var iconStyles = this.classes.buttonicon.style;
		if (!iconStyles.active.blank()) {
			Element.setStyle(this.button, {backgroundImage:iconStyles.active});
		}	
				
		this.field.className = this.classes.field.classes.active;
		Element.setStyle(this.field, this.classes.field.style.active);
		 
		this.isDisabled = false;
	},
		
	disable : function() {
		this.button.className = this.classes.button.classes.disabled;
		this.buttonBG.className = this.classes.buttonbg.classes.disabled;
		this.field.className = this.classes.field.classes.disabled;
		Element.setStyle(this.field, this.classes.field.style.disabled);
		
		var styleCss =  this.classes.button.style;
		if(styleCss && !styleCss.disabled.blank()) {
			Element.setStyle(this.button, styleCss.disabled);
		}	
		
		var iconStyles = this.classes.buttonicon.style;
		if(iconStyles && !iconStyles.disabled.blank()) {
			Element.setStyle(this.button,{backgroundImage : iconStyles.disabled});
		}	
	
		this.button.disabled = true;
		this.field.disabled = true;
		
		this.isDisabled = true;
	},
	
	enable : function() {
		this.button.className = this.classes.button.classes.normal;
		this.buttonBG.className = this.classes.buttonbg.classes.normal;
		this.field.className = this.classes.field.classes.normal;
		var fieldStyles = this.classes.field.style.normal;
		Element.setStyle(this.field, fieldStyles);

		var iconStyles = this.classes.buttonicon.style;
		if(!iconStyles.normal.blank()) {
			Element.setStyle(this.button,{backgroundImage : iconStyles.normal});
		}	
		
		var styleCss =  this.classes.button.style;
		if(styleCss && !styleCss.normal.blank()) {
			Element.setStyle(this.button, styleCss.normal);
		}
						
		this.button.disabled = false;
		this.field.disabled = false;
		this.isDisabled = false;
	},
	
	doDisable : function() {
		this.disable();
	},
	
	doNormal : function() {
		this.enable();
	},
	
	getCurrentWidth : function() {
		return this.combobox.firstChild.offsetWidth;	
	},
	
	/**
	 * user's JavaScript API
	 */
	 showList : function() {
	 	if (this.isDisabled) {
	 		return;
	 	}
	 	this.field.focus();
	 	this.buttonClickHandler();
	 	//this.comboList.isList = false;
	 },
	 
	 hideList : function() {
	 	this.comboList.hideWithDelay();
	 }
};


RichFaces.SipComboBox.textboxSelect = function(oTextbox, iStart, iEnd) {
   if (Prototype.Browser.IE) {
       var oRange = oTextbox.createTextRange();
       oRange.moveStart("character", iStart);
       oRange.moveEnd("character", -oTextbox.value.length + iEnd);      
       oRange.select();                                              
   } else if (Prototype.Browser.Gecko) {
       oTextbox.setSelectionRange(iStart, iEnd);
   } else {
   		//FIXME
   		oTextbox.setSelectionRange(iStart, iEnd);
   }                    
} 

RichFaces.SipComboBox.getSelectedText = function(oTextbox) {
	if (window.getSelection) {
		return window.getSelection().text;
	} else if (document.selection) { 
		// should come last; Opera!
		return document.selection.createRange();
	} else {
		//TODO
	}
}

RichFaces.SipComboBox.SPECIAL_KEYS = [
	Event.KEY_RETURN, Event.KEY_UP, Event.KEY_DOWN, Event.KEY_RIGHT, Event.KEY_LEFT, Event.KEY_ESC, Event.KEY_TAB, 16, 17, 18  /* vladimir claims 16 is shift key code */ 
]