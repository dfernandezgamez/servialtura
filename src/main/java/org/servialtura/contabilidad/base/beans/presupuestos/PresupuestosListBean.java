package org.servialtura.contabilidad.base.beans.presupuestos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.service.PresupuestosService;


@ManagedBean(name = "presupuestosListBean")
@ViewScoped
public class PresupuestosListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

	/**
	 * 
	 */
	private List<Presupuesto> presupuestos;
    private Presupuesto newPresupuesto = new Presupuesto();
    private Presupuesto editPresupuesto = new Presupuesto();
    
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    
    @PostConstruct
    public void init(){
    	
    	try {
			this.presupuestos= presupuestosService.getPresupuestos();
		} catch (SystemException e) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error reciviendo los presupuestos"));
		}
    }
    
    
    public void addSolicitud(){
    	try {
    		presupuestosService.createPresupuesto(newPresupuesto);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando presupuesto"));
		}
    }
    
    public void updateSolicitud(){
    	try {
    		presupuestosService.updatePresupuesto(editPresupuesto);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error actualizando presupuesto"));
		}
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Presupuesto actualizado correctamente"));
    }


	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}


	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}


	public Presupuesto getNewPresupuesto() {
		return newPresupuesto;
	}


	public void setNewPresupuesto(Presupuesto newPresupuesto) {
		this.newPresupuesto = newPresupuesto;
	}


	public Presupuesto getEditPresupuesto() {
		return editPresupuesto;
	}


	public void setEditPresupuesto(Presupuesto editPresupuesto) {
		this.editPresupuesto = editPresupuesto;
	}


	public PresupuestosService getPresupuestosService() {
		return presupuestosService;
	}


	public void setPresupuestosService(PresupuestosService presupuestosService) {
		this.presupuestosService = presupuestosService;
	}
    
}
