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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.helpers.WordHelper;
import org.servialtura.contabilidad.base.model.Partida;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.service.ClientesService;
import org.servialtura.contabilidad.base.service.PresupuestosService;


@ManagedBean(name = "editPresupuestoBean")
@ViewScoped
public class EditPresupuestoBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

    private Presupuesto selectedPresupuesto;
    private List<Partida> partidas;
    private Partida newPartida;
     
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    
    @ManagedProperty(value="#{clientesService}")
    private ClientesService clientesService;
    
    @PostConstruct
    public void init(){
    	String idPresupuesto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPresupuesto");
    	selectedPresupuesto=presupuestosService.getPresupuesto(Integer.valueOf(idPresupuesto));
    }
    
    public void guardarPresupuesto(){
    	try {
    		presupuestosService.updatePresupuesto(selectedPresupuesto);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error actualizando  presupuesto"));
		}
    	
    }
    
    public void prepareNewPartida(){
    	this.newPartida = new Partida();
    }
    

	public StreamedContent getPresupuestoFile(){

		StreamedContent file = null;
		XWPFDocument document= WordHelper.getPresupuesto(this.selectedPresupuesto);
    	
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

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public Partida getNewPartida() {
		return newPartida;
	}

	public void setNewPartida(Partida newPartida) {
		this.newPartida = newPartida;
	}


}
