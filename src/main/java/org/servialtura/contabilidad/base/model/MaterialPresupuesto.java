package org.servialtura.contabilidad.base.model;

import java.io.Serializable;

public class MaterialPresupuesto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4287933066470219300L;
	private Presupuesto presupuesto;
	private Material material;
	private Long cantidad;
	
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}
