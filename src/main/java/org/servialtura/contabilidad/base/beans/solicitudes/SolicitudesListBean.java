package org.servialtura.contabilidad.base.beans.solicitudes;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.model.Solicitud;
import org.servialtura.contabilidad.base.service.SolicitudesService;


@ManagedBean(name = "solicitudesListBean")
@ViewScoped
public class SolicitudesListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

	/**
	 * 
	 */
	private List<Solicitud> solicitudes;
    private Solicitud newSolicitud ;
    private Solicitud editSolicitud;
    
    
    @ManagedProperty(value="#{solicitudesService}")
    private SolicitudesService solicitudesService;
    
    
    @PostConstruct
    public void init(){
    	
    	try {
			this.solicitudes= solicitudesService.getSolicitudes();
		} catch (SystemException e) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error recibiendo las solicitudes"));
		}
    }
    
    public void redirectNewPresupuesto(Solicitud sol){
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../presupuestos/newPresupuesto.xhtml?idSolicitud="+sol.getIdSolicitud());
		} catch (IOException e) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error redirecting"));
		}
    }
    
    public void redirectExistingPresupuesto(Solicitud sol){
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../presupuestos/editPresupuesto.xhtml?idSolicitud="+sol.getIdSolicitud());
		} catch (IOException e) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error redirecting"));
		}
    }
    
    public void addSolicitud(){
    	if(newSolicitud.getEmailContacto().isEmpty() && newSolicitud.getTelefonoContacto().isEmpty()){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Al menos el teléfono o el email son obligatorios"));
    		FacesContext.getCurrentInstance().validationFailed();
    		return;
    	}
    	
    	try {
    		solicitudesService.createSolicitud(newSolicitud);
    		this.solicitudes= solicitudesService.getSolicitudes();
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando solicitud"));
		}
    	
    }
    
    public void updateSolicitud(){
    	try {
    		solicitudesService.updateSolicitud(editSolicitud);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error updating solicitud"));
		}
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Solicitud actualizada correctamente"));
    }
    
    public void prepareSolicitud(){
    	this.newSolicitud = new Solicitud();
    	newSolicitud.setFechaSolicitud( new Date());
    }
    
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}


	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}


	public Solicitud getNewSolicitud() {
		return newSolicitud;
	}


	public void setNewSolicitud(Solicitud newSolicitud) {
		this.newSolicitud = newSolicitud;
	}


	public Solicitud getEditSolicitud() {
		return editSolicitud;
	}


	public void setEditSolicitud(Solicitud editSolicitud) {
		this.editSolicitud = editSolicitud;
	}


	public SolicitudesService getSolicitudesService() {
		return solicitudesService;
	}


	public void setSolicitudesService(SolicitudesService solicitudesService) {
		this.solicitudesService = solicitudesService;
	}
    
}
