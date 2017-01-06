package org.servialtura.contabilidad.base.enums;

public enum EstadoPresupuestoEnum {
	EN_PREPARACION("fa fa-pencil", "#2c3e50"),
	ENVIADO("fa fa-send", "#2c3e50"),
	ACEPTADO("fa fa-thumbs-up", "#27ae60"),
	PARCIALMENTE_ACEPTADO("fa fa-thumbs-up", "#f1c40f"),
	RECHAZADO("fa fa-thumbs-down", "#e74c3c");
	
	private final String icon;
	private final String color;

	private EstadoPresupuestoEnum(String icon,String color) {
		this.icon = icon;
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public String getColor() {
		return color;
	}
	
}
