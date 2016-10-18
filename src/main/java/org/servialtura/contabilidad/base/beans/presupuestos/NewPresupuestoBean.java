package org.servialtura.contabilidad.base.beans.presupuestos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.servialtura.contabilidad.base.helpers.WordHelper;
import org.servialtura.contabilidad.base.model.LineaPartida;
import org.servialtura.contabilidad.base.model.Partida;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.model.Solicitud;
import org.servialtura.contabilidad.base.service.PresupuestosService;
import org.servialtura.contabilidad.base.service.SolicitudesService;


@ManagedBean(name = "newPresupuestoBean")
@ViewScoped
public class NewPresupuestoBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

	/**
	 * 
	 */
    private Presupuesto newPresupuesto = new Presupuesto();
    private List<Partida> partidas = new ArrayList<Partida>();
    private List<LineaPartida> lineas = new ArrayList<LineaPartida>();
    private Boolean necesitaLicencia;
    private Boolean licenciaNuestra;
     
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    @ManagedProperty(value="#{solicitudesService}")
    private SolicitudesService solicitudesService;
    
    @PostConstruct
    public void init(){
    	String idSolicitud = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idSolicitud");
    	Solicitud sol=solicitudesService.getSolicitud(Integer.valueOf(idSolicitud));
    	this.newPresupuesto.setSolicitud(sol);
    	this.newPresupuesto.getSolicitud().setFechaSolicitud( new Date());
    	partidas = new ArrayList<Partida>();
    }
    
    
    public void guardarPresupuesto(){
    	try {
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



	public Boolean getNecesitaLicencia() {
		return necesitaLicencia;
	}


	public void setNecesitaLicencia(Boolean necesitaLicencia) {
		this.necesitaLicencia = necesitaLicencia;
	}

	public Boolean getLicenciaNuestra() {
		return licenciaNuestra;
	}

	public void setLicenciaNuestra(Boolean licenciaNuestra) {
		this.licenciaNuestra = licenciaNuestra;
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

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public List<LineaPartida> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPartida> lineas) {
		this.lineas = lineas;
	}


	public SolicitudesService getSolicitudesService() {
		return solicitudesService;
	}


	public void setSolicitudesService(SolicitudesService solicitudesService) {
		this.solicitudesService = solicitudesService;
	}

}
