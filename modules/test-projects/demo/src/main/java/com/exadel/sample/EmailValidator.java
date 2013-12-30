package com.exadel.sample;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {
	
	private static Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		 if (!pattern.matcher((String)value).matches()) {
			 throw new ValidatorException(new FacesMessage("Not valid email"));
		 }
	}
}
