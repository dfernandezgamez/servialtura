package org.servialtura.contabilidad.base.beans.proveedores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;

import org.primefaces.event.RowEditEvent;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.model.Proveedor;
import org.servialtura.contabilidad.base.service.ProveedoresService;


@ManagedBean(name = "proveedoresListBean")
@ViewScoped
public class ProveedoresListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

	/**
	 * 
	 */
	private List<Proveedor> proveedores;
    private Proveedor newProveedor = new Proveedor();
    
    
    @ManagedProperty(value="#{proveedoresService}")
    private ProveedoresService proveedoresService;
    
    
    @PostConstruct
    public void init(){
    	
    	try {
			this.proveedores= proveedoresService.listProveedores();
		} catch (SystemException e) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error reciviendo los proveedores"));
		}
    }
    
    public void addProveedor(){
    	try {
    		proveedoresService.createProveedor(newProveedor);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creating user"));
		}
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Proveedor creado correctamente"));
    }
    
    public List<Proveedor> searchProveedor(String query) throws SystemException {
        return proveedoresService.findProveedores(query);
    }
    public void onRowEdit(RowEditEvent event) {
    	Proveedor proveedor=(Proveedor) event.getObject();
    	try {
    		proveedoresService.updateProveedor(proveedor);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creating user"));
		}
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Proveedor actualizado correctamente"));
    }
     
    public void onRowCancel(RowEditEvent event) {

    }
    
    

	public List<Proveedor> getProveedores() {
		return proveedores;
	}


	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}


	public ProveedoresService getProveedoresService() {
		return proveedoresService;
	}


	public void setProveedoresService(ProveedoresService proveedoresService) {
		this.proveedoresService = proveedoresService;
	}


	public Proveedor getNewProveedor() {
		return newProveedor;
	}


	public void setNewProveedor(Proveedor newProveedor) {
		this.newProveedor = newProveedor;
	}

}
