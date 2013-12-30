package com.exadel.sample;

import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateValidator implements Validator {
	
	static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	static {
		format.setLenient(false);
	}
	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		try {
			format.parse((String)value);
		}catch (Exception e) {
			throw new ValidatorException(new FacesMessage("Not valid date"));
		}
		
	}
}
