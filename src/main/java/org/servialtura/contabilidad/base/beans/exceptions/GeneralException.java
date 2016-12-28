package org.servialtura.contabilidad.base.beans.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.servialtura.contabilidad.base.utils.I18nMessage;

public abstract class GeneralException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3551222104242176674L;
	private final List<I18nMessage> errorMessages = new ArrayList<I18nMessage>();

	public GeneralException(String key, Object... params) {
		super(key);
		addError(key, params);
	}

	public GeneralException(Throwable t, String key, Object... params) {
		super(t);
		addError(key, params);
	}


	public void addErrors(Map<String, List<?>> errorMap) {
		for (Entry<String, List<?>> e : errorMap.entrySet()) {
			addError(e.getKey(), e.getValue());
		}
	}

	public void addError(String key, Object... params) {
		errorMessages.add(new I18nMessage(key, Arrays.asList(params)));
	}

	public List<I18nMessage> getErrorMessages() {
		return errorMessages;
	}

}
