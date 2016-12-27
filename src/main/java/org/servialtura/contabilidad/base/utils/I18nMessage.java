package org.servialtura.contabilidad.base.utils;

import java.io.Serializable;
import java.util.List;

public class I18nMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7119517003915005302L;
	private String key;
	private List<Object> params;

	public I18nMessage(String key, List<Object> params) {
		this.key = key;
		this.params = params;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

}
