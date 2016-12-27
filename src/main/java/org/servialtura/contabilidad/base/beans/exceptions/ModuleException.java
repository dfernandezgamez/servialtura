package org.servialtura.contabilidad.base.beans.exceptions;



public class ModuleException extends GeneralException {
	/**
	 *
	 */
	private static final long serialVersionUID = 4773123070435313268L;

	public ModuleException() {
		super(GenericExceptionCon.GENERAL_ERROR_UNKNOWN);
	}

	public ModuleException(String key, Object... params) {
		super(key, params);
	}

	public ModuleException(Throwable t, String key, Object... params) {
		super(t, key, params);
	}

}
