package org.servialtura.contabilidad.base.beans.viewUtils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class PropertyUtils {

	public static String getMessage(String property, FacesContext context){
		ResourceBundle labels = null;
		labels = ResourceBundle.getBundle("org/servialtura/contabilidad/i18n/applicationProperties", context.getExternalContext().getRequestLocale());
		return labels.getString(property);
	}
	/**
	 * @param context
	 * @param componentId
	 * @param property
	 * @param severity
	 */
	public static void addMessage(FacesContext context,String componentId,String property, Severity severity){
		FacesMessage message = new FacesMessage(getMessage(property,context));
		if(severity == null){
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}else{
			message.setSeverity(severity);
		}
		context.addMessage(componentId, message);
	}
	

}
