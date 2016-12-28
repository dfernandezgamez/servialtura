package org.servialtura.contabilidad.base.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@ViewScoped
public class GenericEntityConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3333701009143094087L;

	private Map<String, Object> entities;
	
	public GenericEntityConverter(){
		entities = new HashMap<String, Object>();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		if (entity != null) {
			String entityId = String.format("%s_%s", entity.getClass().getCanonicalName(), entity.hashCode());
			
			if (!entities.containsKey(entityId)) {
				entities.put(entityId, entity);
			}

			return entityId;
		} 
		return null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String entityId) {
		if (entityId != null && !entityId.isEmpty()) {
			return entities.get(entityId);
		}

		return null;
	}
	
	@PreDestroy
	public void destroy(){
		entities.clear();
		entities = null;
	}
}
