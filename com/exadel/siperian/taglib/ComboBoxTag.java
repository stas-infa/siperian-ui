/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import javax.faces.convert.Converter ;
import java.lang.Object ;
import java.lang.Boolean ;
import java.lang.Integer ;
import java.lang.String ;
import org.ajax4jsf.webapp.taglib.HtmlComponentTagBase ;
import javax.faces.el.MethodBinding ;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.el.MethodExpression;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.el.ValueExpression;
import org.richfaces.webapp.taglib.MethodBindingMethodExpressionAdaptor;
import org.richfaces.webapp.taglib.ValueBindingValueExpressionAdaptor;
import com.exadel.siperian.component.html.HtmlComboBox;

public class ComboBoxTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 		 		 	  			  		  	  
		/*
		 * behaviourStrategy
		 * Determine Sip combox behaviour
		 */
		private ValueExpression _behaviourStrategy;
		/**
		 * Determine Sip combox behaviour
		 * Setter for behaviourStrategy
		 * @param behaviourStrategy - new value
		 */
		 public void setBehaviourStrategy( ValueExpression  __behaviourStrategy ){
			this._behaviourStrategy = __behaviourStrategy;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * buttonClass
		 * Style Class attribute for the button
		 */
		private ValueExpression _buttonClass;
		/**
		 * Style Class attribute for the button
		 * Setter for buttonClass
		 * @param buttonClass - new value
		 */
		 public void setButtonClass( ValueExpression  __buttonClass ){
			this._buttonClass = __buttonClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonDisabledClass
		 * Style Class attribute for the disabled button
		 */
		private ValueExpression _buttonDisabledClass;
		/**
		 * Style Class attribute for the disabled button
		 * Setter for buttonDisabledClass
		 * @param buttonDisabledClass - new value
		 */
		 public void setButtonDisabledClass( ValueExpression  __buttonDisabledClass ){
			this._buttonDisabledClass = __buttonDisabledClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonDisabledStyle
		 * CSS style rules to be applied to disabled button
		 */
		private ValueExpression _buttonDisabledStyle;
		/**
		 * CSS style rules to be applied to disabled button
		 * Setter for buttonDisabledStyle
		 * @param buttonDisabledStyle - new value
		 */
		 public void setButtonDisabledStyle( ValueExpression  __buttonDisabledStyle ){
			this._buttonDisabledStyle = __buttonDisabledStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonIcon
		 * Defines icon for the button element
		 */
		private ValueExpression _buttonIcon;
		/**
		 * Defines icon for the button element
		 * Setter for buttonIcon
		 * @param buttonIcon - new value
		 */
		 public void setButtonIcon( ValueExpression  __buttonIcon ){
			this._buttonIcon = __buttonIcon;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonIconDisabled
		 * Defines disabled icon for the button element
		 */
		private ValueExpression _buttonIconDisabled;
		/**
		 * Defines disabled icon for the button element
		 * Setter for buttonIconDisabled
		 * @param buttonIconDisabled - new value
		 */
		 public void setButtonIconDisabled( ValueExpression  __buttonIconDisabled ){
			this._buttonIconDisabled = __buttonIconDisabled;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonIconInactive
		 * Defines inactive icon for the button element
		 */
		private ValueExpression _buttonIconInactive;
		/**
		 * Defines inactive icon for the button element
		 * Setter for buttonIconInactive
		 * @param buttonIconInactive - new value
		 */
		 public void setButtonIconInactive( ValueExpression  __buttonIconInactive ){
			this._buttonIconInactive = __buttonIconInactive;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonInactiveClass
		 * Style Class attribute for the inactive button
		 */
		private ValueExpression _buttonInactiveClass;
		/**
		 * Style Class attribute for the inactive button
		 * Setter for buttonInactiveClass
		 * @param buttonInactiveClass - new value
		 */
		 public void setButtonInactiveClass( ValueExpression  __buttonInactiveClass ){
			this._buttonInactiveClass = __buttonInactiveClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonInactiveStyle
		 * CSS style rules to be applied to inactive button
		 */
		private ValueExpression _buttonInactiveStyle;
		/**
		 * CSS style rules to be applied to inactive button
		 * Setter for buttonInactiveStyle
		 * @param buttonInactiveStyle - new value
		 */
		 public void setButtonInactiveStyle( ValueExpression  __buttonInactiveStyle ){
			this._buttonInactiveStyle = __buttonInactiveStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * buttonStyle
		 * CSS style rules to be applied to button
		 */
		private ValueExpression _buttonStyle;
		/**
		 * CSS style rules to be applied to button
		 * Setter for buttonStyle
		 * @param buttonStyle - new value
		 */
		 public void setButtonStyle( ValueExpression  __buttonStyle ){
			this._buttonStyle = __buttonStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * converter
		 * Id of Converter to be used or reference to a Converter
		 */
		private ValueExpression _converter;
		/**
		 * Id of Converter to be used or reference to a Converter
		 * Setter for converter
		 * @param converter - new value
		 */
		 public void setConverter( ValueExpression  __converter ){
			this._converter = __converter;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * converterMessage
		 * A ValueExpression enabled attribute that, if present,
			will be used as the text of the converter message,
			replacing any message that comes from the converter
		 */
		private ValueExpression _converterMessage;
		/**
		 * A ValueExpression enabled attribute that, if present,
			will be used as the text of the converter message,
			replacing any message that comes from the converter
		 * Setter for converterMessage
		 * @param converterMessage - new value
		 */
		 public void setConverterMessage( ValueExpression  __converterMessage ){
			this._converterMessage = __converterMessage;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * defaultLabel
		 * Defines default label for the input field element
		 */
		private ValueExpression _defaultLabel;
		/**
		 * Defines default label for the input field element
		 * Setter for defaultLabel
		 * @param defaultLabel - new value
		 */
		 public void setDefaultLabel( ValueExpression  __defaultLabel ){
			this._defaultLabel = __defaultLabel;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * directInputSuggestions
		 * Defines the first value from the suggested in input field. Default value is "false".
		 */
		private ValueExpression _directInputSuggestions;
		/**
		 * Defines the first value from the suggested in input field. Default value is "false".
		 * Setter for directInputSuggestions
		 * @param directInputSuggestions - new value
		 */
		 public void setDirectInputSuggestions( ValueExpression  __directInputSuggestions ){
			this._directInputSuggestions = __directInputSuggestions;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabled
		 * When set for a form control, this boolean attribute disables the control for your input
		 */
		private ValueExpression _disabled;
		/**
		 * When set for a form control, this boolean attribute disables the control for your input
		 * Setter for disabled
		 * @param disabled - new value
		 */
		 public void setDisabled( ValueExpression  __disabled ){
			this._disabled = __disabled;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * enableManualInput
		 * Enables  keyboard input, if "false" keyboard input will be locked. Default value is "true"
		 */
		private ValueExpression _enableManualInput;
		/**
		 * Enables  keyboard input, if "false" keyboard input will be locked. Default value is "true"
		 * Setter for enableManualInput
		 * @param enableManualInput - new value
		 */
		 public void setEnableManualInput( ValueExpression  __enableManualInput ){
			this._enableManualInput = __enableManualInput;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * filterNewValues
		 * Defines the appearance of values in the list. Default value is "true".
		 */
		private ValueExpression _filterNewValues;
		/**
		 * Defines the appearance of values in the list. Default value is "true".
		 * Setter for filterNewValues
		 * @param filterNewValues - new value
		 */
		 public void setFilterNewValues( ValueExpression  __filterNewValues ){
			this._filterNewValues = __filterNewValues;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * hideDelay
		 * Delay between losing focus and pop-up list closing. Default value is "0".
		 */
		private ValueExpression _hideDelay;
		/**
		 * Delay between losing focus and pop-up list closing. Default value is "0".
		 * Setter for hideDelay
		 * @param hideDelay - new value
		 */
		 public void setHideDelay( ValueExpression  __hideDelay ){
			this._hideDelay = __hideDelay;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * immediate
		 * A flag indicating that this component value must be converted
            and validated immediately (that is, during Apply Request Values
            phase), rather than waiting until a Process Validations phase
		 */
		private ValueExpression _immediate;
		/**
		 * A flag indicating that this component value must be converted
            and validated immediately (that is, during Apply Request Values
            phase), rather than waiting until a Process Validations phase
		 * Setter for immediate
		 * @param immediate - new value
		 */
		 public void setImmediate( ValueExpression  __immediate ){
			this._immediate = __immediate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inputClass
		 * Style Class attribute for the input field
		 */
		private ValueExpression _inputClass;
		/**
		 * Style Class attribute for the input field
		 * Setter for inputClass
		 * @param inputClass - new value
		 */
		 public void setInputClass( ValueExpression  __inputClass ){
			this._inputClass = __inputClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inputDisabledClass
		 * Style Class attribute for the disabled input
		 */
		private ValueExpression _inputDisabledClass;
		/**
		 * Style Class attribute for the disabled input
		 * Setter for inputDisabledClass
		 * @param inputDisabledClass - new value
		 */
		 public void setInputDisabledClass( ValueExpression  __inputDisabledClass ){
			this._inputDisabledClass = __inputDisabledClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inputDisabledStyle
		 * CSS style rules to be applied to disabled input
		 */
		private ValueExpression _inputDisabledStyle;
		/**
		 * CSS style rules to be applied to disabled input
		 * Setter for inputDisabledStyle
		 * @param inputDisabledStyle - new value
		 */
		 public void setInputDisabledStyle( ValueExpression  __inputDisabledStyle ){
			this._inputDisabledStyle = __inputDisabledStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inputInactiveClass
		 * Style Class attribute for the inactive input
		 */
		private ValueExpression _inputInactiveClass;
		/**
		 * Style Class attribute for the inactive input
		 * Setter for inputInactiveClass
		 * @param inputInactiveClass - new value
		 */
		 public void setInputInactiveClass( ValueExpression  __inputInactiveClass ){
			this._inputInactiveClass = __inputInactiveClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inputInactiveStyle
		 * CSS style rules to be applied to inactive input
		 */
		private ValueExpression _inputInactiveStyle;
		/**
		 * CSS style rules to be applied to inactive input
		 * Setter for inputInactiveStyle
		 * @param inputInactiveStyle - new value
		 */
		 public void setInputInactiveStyle( ValueExpression  __inputInactiveStyle ){
			this._inputInactiveStyle = __inputInactiveStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inputStyle
		 * CSS style rules to be applied to input field
		 */
		private ValueExpression _inputStyle;
		/**
		 * CSS style rules to be applied to input field
		 * Setter for inputStyle
		 * @param inputStyle - new value
		 */
		 public void setInputStyle( ValueExpression  __inputStyle ){
			this._inputStyle = __inputStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * itemClass
		 * Style Class attribute for the items
		 */
		private ValueExpression _itemClass;
		/**
		 * Style Class attribute for the items
		 * Setter for itemClass
		 * @param itemClass - new value
		 */
		 public void setItemClass( ValueExpression  __itemClass ){
			this._itemClass = __itemClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * itemSelectedClass
		 * Style Class attribute for the selected item
		 */
		private ValueExpression _itemSelectedClass;
		/**
		 * Style Class attribute for the selected item
		 * Setter for itemSelectedClass
		 * @param itemSelectedClass - new value
		 */
		 public void setItemSelectedClass( ValueExpression  __itemSelectedClass ){
			this._itemSelectedClass = __itemSelectedClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * label
		 * A localized user presentable name for this component.
		 */
		private ValueExpression _label;
		/**
		 * A localized user presentable name for this component.
		 * Setter for label
		 * @param label - new value
		 */
		 public void setLabel( ValueExpression  __label ){
			this._label = __label;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * listClass
		 * Style Class attribute for the popup list
		 */
		private ValueExpression _listClass;
		/**
		 * Style Class attribute for the popup list
		 * Setter for listClass
		 * @param listClass - new value
		 */
		 public void setListClass( ValueExpression  __listClass ){
			this._listClass = __listClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * listHeight
		 * Defines height of file pop-up list. Default value is "200px".
		 */
		private ValueExpression _listHeight;
		/**
		 * Defines height of file pop-up list. Default value is "200px".
		 * Setter for listHeight
		 * @param listHeight - new value
		 */
		 public void setListHeight( ValueExpression  __listHeight ){
			this._listHeight = __listHeight;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * listStyle
		 * CSS style rules to be applied to popup list
		 */
		private ValueExpression _listStyle;
		/**
		 * CSS style rules to be applied to popup list
		 * Setter for listStyle
		 * @param listStyle - new value
		 */
		 public void setListStyle( ValueExpression  __listStyle ){
			this._listStyle = __listStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * listWidth
		 * Defines width of file popup list
		 */
		private ValueExpression _listWidth;
		/**
		 * Defines width of file popup list
		 * Setter for listWidth
		 * @param listWidth - new value
		 */
		 public void setListWidth( ValueExpression  __listWidth ){
			this._listWidth = __listWidth;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * onblur
		 * The client side script method to be called when the element loses the focus
		 */
		private ValueExpression _onblur;
		/**
		 * The client side script method to be called when the element loses the focus
		 * Setter for onblur
		 * @param onblur - new value
		 */
		 public void setOnblur( ValueExpression  __onblur ){
			this._onblur = __onblur;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onchange
		 * The client side script method to be called when the element value is changed
		 */
		private ValueExpression _onchange;
		/**
		 * The client side script method to be called when the element value is changed
		 * Setter for onchange
		 * @param onchange - new value
		 */
		 public void setOnchange( ValueExpression  __onchange ){
			this._onchange = __onchange;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * onfocus
		 * The client side script method to be called when the element gets the focus
		 */
		private ValueExpression _onfocus;
		/**
		 * The client side script method to be called when the element gets the focus
		 * Setter for onfocus
		 * @param onfocus - new value
		 */
		 public void setOnfocus( ValueExpression  __onfocus ){
			this._onfocus = __onfocus;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * onlistcall
		 * HTML: script expression; a list is called
		 */
		private ValueExpression _onlistcall;
		/**
		 * HTML: script expression; a list is called
		 * Setter for onlistcall
		 * @param onlistcall - new value
		 */
		 public void setOnlistcall( ValueExpression  __onlistcall ){
			this._onlistcall = __onlistcall;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlistclose
		 * HTML: script expression; a list is closed
		 */
		private ValueExpression _onlistclose;
		/**
		 * HTML: script expression; a list is closed
		 * Setter for onlistclose
		 * @param onlistclose - new value
		 */
		 public void setOnlistclose( ValueExpression  __onlistclose ){
			this._onlistclose = __onlistclose;
	     }
	  
	 	 		 		 		 		 		 		 	  			  		  	  
		/*
		 * onselect
		 * The client side script method to be called when some text is selected in the text field. This attribute can be used with the INPUT and TEXTAREA elements.
		 */
		private ValueExpression _onselect;
		/**
		 * The client side script method to be called when some text is selected in the text field. This attribute can be used with the INPUT and TEXTAREA elements.
		 * Setter for onselect
		 * @param onselect - new value
		 */
		 public void setOnselect( ValueExpression  __onselect ){
			this._onselect = __onselect;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * required
		 * If "true", this component is checked for non-empty input
		 */
		private ValueExpression _required;
		/**
		 * If "true", this component is checked for non-empty input
		 * Setter for required
		 * @param required - new value
		 */
		 public void setRequired( ValueExpression  __required ){
			this._required = __required;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * requiredMessage
		 * A ValueExpression enabled attribute that, if present,
			will be used as the text of the validation message for
			the "required" facility, if the "required" facility is
			used
		 */
		private ValueExpression _requiredMessage;
		/**
		 * A ValueExpression enabled attribute that, if present,
			will be used as the text of the validation message for
			the "required" facility, if the "required" facility is
			used
		 * Setter for requiredMessage
		 * @param requiredMessage - new value
		 */
		 public void setRequiredMessage( ValueExpression  __requiredMessage ){
			this._requiredMessage = __requiredMessage;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectFirstOnUpdate
		 * Defines if the first value from suggested is selected in pop-up list. Default value is "true".
		 */
		private ValueExpression _selectFirstOnUpdate;
		/**
		 * Defines if the first value from suggested is selected in pop-up list. Default value is "true".
		 * Setter for selectFirstOnUpdate
		 * @param selectFirstOnUpdate - new value
		 */
		 public void setSelectFirstOnUpdate( ValueExpression  __selectFirstOnUpdate ){
			this._selectFirstOnUpdate = __selectFirstOnUpdate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * showDelay
		 * Delay between event and pop-up list showing. Default value is "0".
		 */
		private ValueExpression _showDelay;
		/**
		 * Delay between event and pop-up list showing. Default value is "0".
		 * Setter for showDelay
		 * @param showDelay - new value
		 */
		 public void setShowDelay( ValueExpression  __showDelay ){
			this._showDelay = __showDelay;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * suggestionValues
		 * Defines the suggestion collection
		 */
		private ValueExpression _suggestionValues;
		/**
		 * Defines the suggestion collection
		 * Setter for suggestionValues
		 * @param suggestionValues - new value
		 */
		 public void setSuggestionValues( ValueExpression  __suggestionValues ){
			this._suggestionValues = __suggestionValues;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tabindex
		 * This attribute specifies the position of the current element in the tabbing order for the current document. This value must be a number between 0 and 32767. User agents should ignore leading zeros
		 */
		private ValueExpression _tabindex;
		/**
		 * This attribute specifies the position of the current element in the tabbing order for the current document. This value must be a number between 0 and 32767. User agents should ignore leading zeros
		 * Setter for tabindex
		 * @param tabindex - new value
		 */
		 public void setTabindex( ValueExpression  __tabindex ){
			this._tabindex = __tabindex;
	     }
	  
	 	 		 		 	  	  	  
		/*
		 * validator
		 * MethodBinding pointing at a method that is called during
            Process Validations phase of the request processing lifecycle,
            to validate the current value of this component
		 */
		private MethodExpression _validator;
		/**
		 * MethodBinding pointing at a method that is called during
            Process Validations phase of the request processing lifecycle,
            to validate the current value of this component
		 * Setter for validator
		 * @param validator - new value
		 */
		 public void setValidator( MethodExpression  __validator ){
			this._validator = __validator;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * validatorMessage
		 * A ValueExpression enabled attribute that, if present,
			will be used as the text of the validator message,
			replacing any message that comes from the validator
		 */
		private ValueExpression _validatorMessage;
		/**
		 * A ValueExpression enabled attribute that, if present,
			will be used as the text of the validator message,
			replacing any message that comes from the validator
		 * Setter for validatorMessage
		 * @param validatorMessage - new value
		 */
		 public void setValidatorMessage( ValueExpression  __validatorMessage ){
			this._validatorMessage = __validatorMessage;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * value
		 * The current value of this component
		 */
		private ValueExpression _value;
		/**
		 * The current value of this component
		 * Setter for value
		 * @param value - new value
		 */
		 public void setValue( ValueExpression  __value ){
			this._value = __value;
	     }
	  
	 	 		 	  	  	  
		/*
		 * valueChangeListener
		 * Listener for value changes
		 */
		private MethodExpression _valueChangeListener;
		/**
		 * Listener for value changes
		 * Setter for valueChangeListener
		 * @param valueChangeListener - new value
		 */
		 public void setValueChangeListener( MethodExpression  __valueChangeListener ){
			this._valueChangeListener = __valueChangeListener;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * width
		 * Width of the component. Default value is "150".
		 */
		private ValueExpression _width;
		/**
		 * Width of the component. Default value is "150".
		 * Setter for width
		 * @param width - new value
		 */
		 public void setWidth( ValueExpression  __width ){
			this._width = __width;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		 		 		    this._behaviourStrategy = null;
	 		 		 		    this._buttonClass = null;
	 		 		    this._buttonDisabledClass = null;
	 		 		    this._buttonDisabledStyle = null;
	 		 		    this._buttonIcon = null;
	 		 		    this._buttonIconDisabled = null;
	 		 		    this._buttonIconInactive = null;
	 		 		    this._buttonInactiveClass = null;
	 		 		    this._buttonInactiveStyle = null;
	 		 		    this._buttonStyle = null;
	 		 		    this._converter = null;
	 		 		    this._converterMessage = null;
	 		 		    this._defaultLabel = null;
	 		 		    this._directInputSuggestions = null;
	 		 		    this._disabled = null;
	 		 		    this._enableManualInput = null;
	 		 		 		    this._filterNewValues = null;
	 		 		    this._hideDelay = null;
	 		 		 		    this._immediate = null;
	 		 		    this._inputClass = null;
	 		 		    this._inputDisabledClass = null;
	 		 		    this._inputDisabledStyle = null;
	 		 		    this._inputInactiveClass = null;
	 		 		    this._inputInactiveStyle = null;
	 		 		    this._inputStyle = null;
	 		 		    this._itemClass = null;
	 		 		    this._itemSelectedClass = null;
	 		 		    this._label = null;
	 		 		    this._listClass = null;
	 		 		    this._listHeight = null;
	 		 		    this._listStyle = null;
	 		 		    this._listWidth = null;
	 		 		 		 		 		    this._onblur = null;
	 		 		    this._onchange = null;
	 		 		 		 		    this._onfocus = null;
	 		 		 		 		 		    this._onlistcall = null;
	 		 		    this._onlistclose = null;
	 		 		 		 		 		 		 		    this._onselect = null;
	 		 		 		    this._required = null;
	 		 		    this._requiredMessage = null;
	 		 		    this._selectFirstOnUpdate = null;
	 		 		    this._showDelay = null;
	 		 		 		 		 		    this._suggestionValues = null;
	 		 		    this._tabindex = null;
	 		 		 		    this._validator = null;
	 		 		    this._validatorMessage = null;
	 		 		 		    this._value = null;
	 		 		    this._valueChangeListener = null;
	 		 		 		    this._width = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlComboBox comp = (HtmlComboBox) component;
    		 			 
						if (this._behaviourStrategy != null) {
				if (this._behaviourStrategy.isLiteralText()) {
					try {
												
						java.lang.String __behaviourStrategy = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._behaviourStrategy.getExpressionString(), 
											java.lang.String.class);
					
												comp.setBehaviourStrategy(__behaviourStrategy);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("behaviourStrategy", this._behaviourStrategy);
				}
			}
					    		 			 
						if (this._buttonClass != null) {
				if (this._buttonClass.isLiteralText()) {
					try {
												
						java.lang.String __buttonClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonClass(__buttonClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonClass", this._buttonClass);
				}
			}
					   		 			 
						if (this._buttonDisabledClass != null) {
				if (this._buttonDisabledClass.isLiteralText()) {
					try {
												
						java.lang.String __buttonDisabledClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonDisabledClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonDisabledClass(__buttonDisabledClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonDisabledClass", this._buttonDisabledClass);
				}
			}
					   		 			 
						if (this._buttonDisabledStyle != null) {
				if (this._buttonDisabledStyle.isLiteralText()) {
					try {
												
						java.lang.String __buttonDisabledStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonDisabledStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonDisabledStyle(__buttonDisabledStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonDisabledStyle", this._buttonDisabledStyle);
				}
			}
					   		 			 
						if (this._buttonIcon != null) {
				if (this._buttonIcon.isLiteralText()) {
					try {
												
						java.lang.String __buttonIcon = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonIcon.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonIcon(__buttonIcon);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonIcon", this._buttonIcon);
				}
			}
					   		 			 
						if (this._buttonIconDisabled != null) {
				if (this._buttonIconDisabled.isLiteralText()) {
					try {
												
						java.lang.String __buttonIconDisabled = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonIconDisabled.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonIconDisabled(__buttonIconDisabled);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonIconDisabled", this._buttonIconDisabled);
				}
			}
					   		 			 
						if (this._buttonIconInactive != null) {
				if (this._buttonIconInactive.isLiteralText()) {
					try {
												
						java.lang.String __buttonIconInactive = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonIconInactive.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonIconInactive(__buttonIconInactive);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonIconInactive", this._buttonIconInactive);
				}
			}
					   		 			 
						if (this._buttonInactiveClass != null) {
				if (this._buttonInactiveClass.isLiteralText()) {
					try {
												
						java.lang.String __buttonInactiveClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonInactiveClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonInactiveClass(__buttonInactiveClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonInactiveClass", this._buttonInactiveClass);
				}
			}
					   		 			 
						if (this._buttonInactiveStyle != null) {
				if (this._buttonInactiveStyle.isLiteralText()) {
					try {
												
						java.lang.String __buttonInactiveStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonInactiveStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonInactiveStyle(__buttonInactiveStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonInactiveStyle", this._buttonInactiveStyle);
				}
			}
					   		 			 
						if (this._buttonStyle != null) {
				if (this._buttonStyle.isLiteralText()) {
					try {
												
						java.lang.String __buttonStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._buttonStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setButtonStyle(__buttonStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("buttonStyle", this._buttonStyle);
				}
			}
					   		 			setConverterProperty(comp, this._converter);
		   		 			 
						if (this._converterMessage != null) {
				if (this._converterMessage.isLiteralText()) {
					try {
												
						java.lang.String __converterMessage = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._converterMessage.getExpressionString(), 
											java.lang.String.class);
					
												comp.setConverterMessage(__converterMessage);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("converterMessage", this._converterMessage);
				}
			}
					   		 			 
						if (this._defaultLabel != null) {
				if (this._defaultLabel.isLiteralText()) {
					try {
												
						java.lang.String __defaultLabel = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._defaultLabel.getExpressionString(), 
											java.lang.String.class);
					
												comp.setDefaultLabel(__defaultLabel);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("defaultLabel", this._defaultLabel);
				}
			}
					   		 			 
						if (this._directInputSuggestions != null) {
				if (this._directInputSuggestions.isLiteralText()) {
					try {
												
						java.lang.Boolean __directInputSuggestions = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._directInputSuggestions.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setDirectInputSuggestions(__directInputSuggestions);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("directInputSuggestions", this._directInputSuggestions);
				}
			}
					   		 			 
						if (this._disabled != null) {
				if (this._disabled.isLiteralText()) {
					try {
												
						Boolean __disabled = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabled.getExpressionString(), 
											Boolean.class);
					
												comp.setDisabled(__disabled.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabled", this._disabled);
				}
			}
					   		 			 
						if (this._enableManualInput != null) {
				if (this._enableManualInput.isLiteralText()) {
					try {
												
						Boolean __enableManualInput = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._enableManualInput.getExpressionString(), 
											Boolean.class);
					
												comp.setEnableManualInput(__enableManualInput.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("enableManualInput", this._enableManualInput);
				}
			}
					    		 			 
						if (this._filterNewValues != null) {
				if (this._filterNewValues.isLiteralText()) {
					try {
												
						java.lang.Boolean __filterNewValues = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._filterNewValues.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setFilterNewValues(__filterNewValues);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("filterNewValues", this._filterNewValues);
				}
			}
					   		 			 
						if (this._hideDelay != null) {
				if (this._hideDelay.isLiteralText()) {
					try {
												
						java.lang.Integer __hideDelay = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._hideDelay.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setHideDelay(__hideDelay);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("hideDelay", this._hideDelay);
				}
			}
					    		 			 
						if (this._immediate != null) {
				if (this._immediate.isLiteralText()) {
					try {
												
						Boolean __immediate = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._immediate.getExpressionString(), 
											Boolean.class);
					
												comp.setImmediate(__immediate.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("immediate", this._immediate);
				}
			}
					   		 			 
						if (this._inputClass != null) {
				if (this._inputClass.isLiteralText()) {
					try {
												
						java.lang.String __inputClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inputClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInputClass(__inputClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inputClass", this._inputClass);
				}
			}
					   		 			 
						if (this._inputDisabledClass != null) {
				if (this._inputDisabledClass.isLiteralText()) {
					try {
												
						java.lang.String __inputDisabledClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inputDisabledClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInputDisabledClass(__inputDisabledClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inputDisabledClass", this._inputDisabledClass);
				}
			}
					   		 			 
						if (this._inputDisabledStyle != null) {
				if (this._inputDisabledStyle.isLiteralText()) {
					try {
												
						java.lang.String __inputDisabledStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inputDisabledStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInputDisabledStyle(__inputDisabledStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inputDisabledStyle", this._inputDisabledStyle);
				}
			}
					   		 			 
						if (this._inputInactiveClass != null) {
				if (this._inputInactiveClass.isLiteralText()) {
					try {
												
						java.lang.String __inputInactiveClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inputInactiveClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInputInactiveClass(__inputInactiveClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inputInactiveClass", this._inputInactiveClass);
				}
			}
					   		 			 
						if (this._inputInactiveStyle != null) {
				if (this._inputInactiveStyle.isLiteralText()) {
					try {
												
						java.lang.String __inputInactiveStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inputInactiveStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInputInactiveStyle(__inputInactiveStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inputInactiveStyle", this._inputInactiveStyle);
				}
			}
					   		 			 
						if (this._inputStyle != null) {
				if (this._inputStyle.isLiteralText()) {
					try {
												
						java.lang.String __inputStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inputStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInputStyle(__inputStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inputStyle", this._inputStyle);
				}
			}
					   		 			 
						if (this._itemClass != null) {
				if (this._itemClass.isLiteralText()) {
					try {
												
						java.lang.String __itemClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._itemClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setItemClass(__itemClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("itemClass", this._itemClass);
				}
			}
					   		 			 
						if (this._itemSelectedClass != null) {
				if (this._itemSelectedClass.isLiteralText()) {
					try {
												
						java.lang.String __itemSelectedClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._itemSelectedClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setItemSelectedClass(__itemSelectedClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("itemSelectedClass", this._itemSelectedClass);
				}
			}
					   		 			 
						if (this._label != null) {
				if (this._label.isLiteralText()) {
					try {
												
						java.lang.String __label = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._label.getExpressionString(), 
											java.lang.String.class);
					
												comp.setLabel(__label);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("label", this._label);
				}
			}
					   		 			 
						if (this._listClass != null) {
				if (this._listClass.isLiteralText()) {
					try {
												
						java.lang.String __listClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._listClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setListClass(__listClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("listClass", this._listClass);
				}
			}
					   		 			 
						if (this._listHeight != null) {
				if (this._listHeight.isLiteralText()) {
					try {
												
						java.lang.String __listHeight = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._listHeight.getExpressionString(), 
											java.lang.String.class);
					
												comp.setListHeight(__listHeight);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("listHeight", this._listHeight);
				}
			}
					   		 			 
						if (this._listStyle != null) {
				if (this._listStyle.isLiteralText()) {
					try {
												
						java.lang.String __listStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._listStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setListStyle(__listStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("listStyle", this._listStyle);
				}
			}
					   		 			 
						if (this._listWidth != null) {
				if (this._listWidth.isLiteralText()) {
					try {
												
						java.lang.String __listWidth = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._listWidth.getExpressionString(), 
											java.lang.String.class);
					
												comp.setListWidth(__listWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("listWidth", this._listWidth);
				}
			}
					      		 			 
						if (this._onblur != null) {
				if (this._onblur.isLiteralText()) {
					try {
												
						java.lang.String __onblur = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onblur.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnblur(__onblur);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onblur", this._onblur);
				}
			}
					   		 			 
						if (this._onchange != null) {
				if (this._onchange.isLiteralText()) {
					try {
												
						java.lang.String __onchange = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onchange.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnchange(__onchange);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onchange", this._onchange);
				}
			}
					     		 			 
						if (this._onfocus != null) {
				if (this._onfocus.isLiteralText()) {
					try {
												
						java.lang.String __onfocus = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onfocus.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnfocus(__onfocus);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onfocus", this._onfocus);
				}
			}
					      		 			 
						if (this._onlistcall != null) {
				if (this._onlistcall.isLiteralText()) {
					try {
												
						java.lang.String __onlistcall = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlistcall.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlistcall(__onlistcall);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlistcall", this._onlistcall);
				}
			}
					   		 			 
						if (this._onlistclose != null) {
				if (this._onlistclose.isLiteralText()) {
					try {
												
						java.lang.String __onlistclose = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlistclose.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlistclose(__onlistclose);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlistclose", this._onlistclose);
				}
			}
					        		 			 
						if (this._onselect != null) {
				if (this._onselect.isLiteralText()) {
					try {
												
						java.lang.String __onselect = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onselect.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnselect(__onselect);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onselect", this._onselect);
				}
			}
					    		 			 
						if (this._required != null) {
				if (this._required.isLiteralText()) {
					try {
												
						Boolean __required = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._required.getExpressionString(), 
											Boolean.class);
					
												comp.setRequired(__required.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("required", this._required);
				}
			}
					   		 			 
						if (this._requiredMessage != null) {
				if (this._requiredMessage.isLiteralText()) {
					try {
												
						java.lang.String __requiredMessage = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._requiredMessage.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRequiredMessage(__requiredMessage);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("requiredMessage", this._requiredMessage);
				}
			}
					   		 			 
						if (this._selectFirstOnUpdate != null) {
				if (this._selectFirstOnUpdate.isLiteralText()) {
					try {
												
						java.lang.Boolean __selectFirstOnUpdate = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectFirstOnUpdate.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setSelectFirstOnUpdate(__selectFirstOnUpdate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectFirstOnUpdate", this._selectFirstOnUpdate);
				}
			}
					   		 			 
						if (this._showDelay != null) {
				if (this._showDelay.isLiteralText()) {
					try {
												
						java.lang.Integer __showDelay = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._showDelay.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setShowDelay(__showDelay);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("showDelay", this._showDelay);
				}
			}
					      		 			 
						if (this._suggestionValues != null) {
				if (this._suggestionValues.isLiteralText()) {
					try {
												
						java.lang.Object __suggestionValues = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._suggestionValues.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setSuggestionValues(__suggestionValues);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("suggestionValues", this._suggestionValues);
				}
			}
					   		 			 
						if (this._tabindex != null) {
				if (this._tabindex.isLiteralText()) {
					try {
												
						java.lang.String __tabindex = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabindex.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTabindex(__tabindex);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabindex", this._tabindex);
				}
			}
					    		 			setValidatorProperty(comp, this._validator);
		   		 			 
						if (this._validatorMessage != null) {
				if (this._validatorMessage.isLiteralText()) {
					try {
												
						java.lang.String __validatorMessage = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._validatorMessage.getExpressionString(), 
											java.lang.String.class);
					
												comp.setValidatorMessage(__validatorMessage);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("validatorMessage", this._validatorMessage);
				}
			}
					    		 			 
						if (this._value != null) {
				if (this._value.isLiteralText()) {
					try {
												
						java.lang.Object __value = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._value.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setValue(__value);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("value", this._value);
				}
			}
					   		 			setValueChangeListenerProperty(comp, this._valueChangeListener);
		    		 			 
						if (this._width != null) {
				if (this._width.isLiteralText()) {
					try {
												
						java.lang.String __width = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._width.getExpressionString(), 
											java.lang.String.class);
					
												comp.setWidth(__width);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("width", this._width);
				}
			}
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "org.richfaces.SipComboBox";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.renderkit.SipComboBoxRenderer";
			}

}
