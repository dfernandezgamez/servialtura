package org.servialtura.contabilidad.base.beans.presupuestos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.enums.EstadoPresupuestoEnum;
import org.servialtura.contabilidad.base.helpers.WordHelper;
import org.servialtura.contabilidad.base.model.Empresa;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.service.ClientesService;
import org.servialtura.contabilidad.base.service.PresupuestosService;


@ManagedBean(name = "newPresupuestoBean")
@ViewScoped
public class NewPresupuestoBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

    private Presupuesto newPresupuesto;
     
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    
    @ManagedProperty(value="#{clientesService}")
    private ClientesService clientesService;
    
    @PostConstruct
    public void init(){
    	this.newPresupuesto=new Presupuesto();
    	this.newPresupuesto.setEstadoPresupuesto(EstadoPresupuestoEnum.EN_PREPARACION);
    	this.newPresupuesto.setFechaSolicitud( new Date());
    }
    
    public void guardarPresupuesto(){
    	try {
    		this.newPresupuesto.setNumeroPresupuesto(presupuestosService.getNuevoNumeroPresupuesto(newPresupuesto));
    		presupuestosService.createPresupuesto(newPresupuesto);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando presupuesto"));
		}
    	
    }
    
	public Presupuesto getNewPresupuesto() {
		return newPresupuesto;
	}


	public void setNewPresupuesto(Presupuesto newPresupuesto) {
		this.newPresupuesto = newPresupuesto;
	}

	public PresupuestosService getPresupuestosService() {
		return presupuestosService;
	}


	public void setPresupuestosService(PresupuestosService presupuestosService) {
		this.presupuestosService = presupuestosService;
	}

	public StreamedContent getPresupuestoFile(){

		StreamedContent file = null;
		XWPFDocument document= WordHelper.getPresupuesto(this.newPresupuesto);
    	
    		FileOutputStream ficheroSalida;
			try {
				ficheroSalida = new FileOutputStream("presupuesto_servialtura.docx");
				document.write(ficheroSalida);
	    		ficheroSalida.close();
	    		
	    		InputStream stream = new FileInputStream("presupuesto_servialtura.docx");
	    		
	    		file = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "presupuesto_servialtura.docx");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
    	
		return file;
	}
	
	 public void onItemSelect(Empresa cliente) {
		 this.newPresupuesto.setEmailContacto(cliente.getEmailEmpresa());
		 this.newPresupuesto.setTelefonoContacto(cliente.getTelefonoEmpresa());
		 this.newPresupuesto.setPersonaContacto(cliente.getNombreEmpresa());
	    }
	
	 public List<Empresa> searchCliente(String query) throws SystemException {
	        return clientesService.findEmpresas(query);
	    }


	public ClientesService getClientesService() {
		return clientesService;
	}

	public void setClientesService(ClientesService clientesService) {
		this.clientesService = clientesService;
	}

}
