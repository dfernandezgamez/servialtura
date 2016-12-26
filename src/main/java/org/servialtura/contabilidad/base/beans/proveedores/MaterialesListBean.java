package org.servialtura.contabilidad.base.beans.proveedores;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.servialtura.contabilidad.base.model.CategoriaMaterial;
import org.servialtura.contabilidad.base.model.Material;
import org.servialtura.contabilidad.base.model.Proveedor;
import org.servialtura.contabilidad.base.service.ProveedoresService;


@ManagedBean(name = "materialesListBean")
@ViewScoped
public class MaterialesListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

	/**
	 * 
	 */
	private List<Material> materiales;
    private Material newMaterial = new Material();
    
    private List<Material> filteredMateriales = new ArrayList<Material>();
    
    
    @ManagedProperty(value="#{proveedoresService}")
    private ProveedoresService proveedoresService;
    
    
    @PostConstruct
    public void init(){
    	
    	try {
			this.materiales= proveedoresService.listMaterials();
		} catch (SystemException e) {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error recibiendo los materiales"));
		}
    }
    
    public void addProveedor(){
    	try {
    		proveedoresService.createMaterial(newMaterial);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando material"));
		}
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Material creado correctamente"));
    }
    
    public List<Material> searchMateriales(String query) throws SystemException {
        return proveedoresService.findMateriales(query);
    }
    
    public List<Proveedor> searchProveedores(String query) throws SystemException {
        return proveedoresService.findProveedores(query);
    }
    
    public List<CategoriaMaterial> searchCategorias(String query) throws SystemException {
        return proveedoresService.findCategoriasMaterial(query);
    }
    
    public void onRowEdit(RowEditEvent event) {
    	Material material=(Material) event.getObject();
    	try {
    		proveedoresService.updateMaterial(material);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error actualizando material"));
		}
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Material actualizado correctamente"));
    }

	public List<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	public Material getNewMaterial() {
		return newMaterial;
	}

	public void setNewMaterial(Material newMaterial) {
		this.newMaterial = newMaterial;
	}

	public ProveedoresService getProveedoresService() {
		return proveedoresService;
	}

	public void setProveedoresService(ProveedoresService proveedoresService) {
		this.proveedoresService = proveedoresService;
	}

	public List<Material> getFilteredMateriales() {
		return filteredMateriales;
	}

	public void setFilteredMateriales(List<Material> filteredMateriales) {
		this.filteredMateriales = filteredMateriales;
	}
     

}
