if (!window.Richfaces) window.Richfaces = {};
Richfaces.SipComboBoxStyles = Class.create();

Richfaces.SipComboBoxStyles.prototype = {
	
	initialize : function () {
		this.commonStyles =	{
			
			button : { classes : {
						normal : "sip-combobox-font-inactive sip-combobox-button-icon-inactive sip-combobox-button-inactive", 
						active : "sip-combobox-font sip-combobox-button-icon sip-combobox-button", 
						disabled : "sip-combobox-font-disabled sip-combobox-button-icon-disabled sip-combobox-button-disabled",
						hovered : "sip-combobox-button-hovered"},
						
				   style : {
						normal: "",
					 	active: "",
						disabled: ""}
				 },	 







		
		buttonbg : {
			classes : { 
				normal:"sip-combobox-font-inactive sip-combobox-button-background-inactive sip-combobox-button-inactive", 
				active: "sip-combobox-font sip-combobox-button-background sip-combobox-button",
				disabled : "sip-combobox-font-disabled sip-combobox-button-background-disabled sip-combobox-button-disabled"}
			},    
			
		buttonicon : {style : {
						normal: "",
						active: "",
						disabled: ""}
					 },	 	                 	 	   		   	  

		
		field : {classes: {
					normal : "sip-combobox-font-inactive sip-combobox-input-inactive", 
					active : "sip-combobox-font sip-combobox-input", 
					disabled : "sip-combobox-font-disabled sip-combobox-input-disabled"
			 	},
			 	
				 style : {
					normal : "",
					active : "",
					disabled : ""
				}
		},	
			
		combolist : {
			list : {
				classes :{
					active : "sip-combobox-list-cord sip-combobox-list-scroll sip-combobox-list-decoration sip-combobox-list-position"},
					style : {active: ""






					}
			},  	  		 	  
				
			item : {normal : "sip-combobox-item", 
				selected : "sip-combobox-item sip-combobox-item-selected"
			}
		}
		
		
		
		
		
		}	
	},
	
	getCommonStyles : function() {
		return this.commonStyles;
	}
	

};



