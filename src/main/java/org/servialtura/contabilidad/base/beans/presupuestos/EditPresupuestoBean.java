package org.servialtura.contabilidad.base.beans.presupuestos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.helpers.WordHelper;
import org.servialtura.contabilidad.base.model.Empresa;
import org.servialtura.contabilidad.base.model.Material;
import org.servialtura.contabilidad.base.model.MaterialPresupuesto;
import org.servialtura.contabilidad.base.model.Partida;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.service.ClientesService;
import org.servialtura.contabilidad.base.service.PresupuestosService;
import org.servialtura.contabilidad.base.service.ProveedoresService;


@ManagedBean(name = "editPresupuestoBean")
@ViewScoped
public class EditPresupuestoBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

    private Presupuesto selectedPresupuesto;
    private List<Material> materiales;
    private Partida newPartida;
    private Material newMaterial;
    private Boolean isEditing;
    private Partida selectedPartida;
    private List<MaterialPresupuesto> materialesPresupuestoList;
     
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    
    @ManagedProperty(value="#{clientesService}")
    private ClientesService clientesService;
    
    @ManagedProperty(value="#{proveedoresService}")
    private ProveedoresService proveedoresService;
    
    @PostConstruct
    public void init(){
    	String idPresupuesto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPresupuesto");
    	selectedPresupuesto=presupuestosService.getPresupuesto(Integer.valueOf(idPresupuesto));
    }
    
    public void editPresupuesto(Boolean value){
    	isEditing=value;
    }
    
    public void guardarPresupuesto(){
    	try {
    		presupuestosService.updatePresupuesto(selectedPresupuesto);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error actualizando  presupuesto"));
		}
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Presupuesto actualizado correctamente"));
    	isEditing=false;
    }
    
    public void prepareNewPartida(){
    	this.newPartida = new Partida();
    	this.newPartida.setPresupuesto(selectedPresupuesto);
    }
    
    public void prepareEditPartida(Partida partida){
    	this.selectedPartida=partida;
    }
    
    public void deletePartida(Partida partida){
    	try {
    		presupuestosService.deletePartida(partida);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error eliminando la partida"));
		}
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Partida eliminada correctamente"));
    }
    
    public void prepareNewMaterial(){
    	this.newMaterial = new Material();
    }
    
    public void onItemSelect(SelectEvent event) {
    }
    
    public void addPartida(){
    	try {
    		presupuestosService.createPartida(newPartida);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando partida"));
		}
    	
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Partida creada correctamente"));
    }
    
    public void updatePartida(){
    	try {
    		presupuestosService.updatePartida(selectedPartida);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error actualizando la partida"));
		}
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Partida actualizada correctamente"));
    }
    
    public List<Material> searchMaterial(String query) throws SystemException {
        return proveedoresService.findMateriales(query);
    }
    
    public List<Empresa> searchEmpresa(String query) throws SystemException {
        return clientesService.findEmpresas(query);
    }
    
    public void addMaterial(){
    }
    

	public StreamedContent getPresupuestoFile(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.selectedPresupuesto.getNumeroPresupuesto());
		sb.append(".docx");
		String nombreFichero= sb.toString();

		StreamedContent file = null;
		XWPFDocument document= WordHelper.getPresupuesto(this.selectedPresupuesto);
    	
    		FileOutputStream ficheroSalida;
			try {
				ficheroSalida = new FileOutputStream(nombreFichero);
				document.write(ficheroSalida);
	    		ficheroSalida.close();
	    		
	    		InputStream stream = new FileInputStream(nombreFichero);
	    		
	    		file = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", nombreFichero);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
    	
		return file;
	}

	public Presupuesto getSelectedPresupuesto() {
		return selectedPresupuesto;
	}

	public void setSelectedPresupuesto(Presupuesto selectedPresupuesto) {
		this.selectedPresupuesto = selectedPresupuesto;
	}

	public PresupuestosService getPresupuestosService() {
		return presupuestosService;
	}

	public void setPresupuestosService(PresupuestosService presupuestosService) {
		this.presupuestosService = presupuestosService;
	}

	public ClientesService getClientesService() {
		return clientesService;
	}

	public void setClientesService(ClientesService clientesService) {
		this.clientesService = clientesService;
	}

	public Partida getNewPartida() {
		return newPartida;
	}

	public void setNewPartida(Partida newPartida) {
		this.newPartida = newPartida;
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

	public ProveedoresService getMaterialesService() {
		return proveedoresService;
	}

	public void setMaterialesService(ProveedoresService materialesService) {
		this.proveedoresService = materialesService;
	}

	public ProveedoresService getProveedoresService() {
		return proveedoresService;
	}

	public void setProveedoresService(ProveedoresService proveedoresService) {
		this.proveedoresService = proveedoresService;
	}

	public Boolean getIsEditing() {
		return isEditing;
	}

	public void setIsEditing(Boolean isEditing) {
		this.isEditing = isEditing;
	}

	public Partida getSelectedPartida() {
		return selectedPartida;
	}

	public void setSelectedPartida(Partida selectedPartida) {
		this.selectedPartida = selectedPartida;
	}

	public List<MaterialPresupuesto> getMaterialesPresupuestoList() {
		return materialesPresupuestoList;
	}

	public void setMaterialesPresupuestoList(List<MaterialPresupuesto> materialesPresupuestoList) {
		this.materialesPresupuestoList = materialesPresupuestoList;
	}


}
