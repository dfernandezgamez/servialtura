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
import org.servialtura.contabilidad.base.model.LineaPresupuesto;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.model.Solicitud;
import org.servialtura.contabilidad.base.service.PresupuestosService;


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
    private List<LineaPresupuesto> lineas = new ArrayList<LineaPresupuesto>();
    private Boolean necesitaLicencia;
    private Boolean licenciaNuestra;
    private LineaPresupuesto newLineaPresupuesto = new LineaPresupuesto();
     
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    

    
    @PostConstruct
    public void init(){
    	this.newPresupuesto.setSolicitud( new Solicitud());
    	this.newPresupuesto.getSolicitud().setFechaSolicitud( new Date());
    	lineas = new ArrayList<LineaPresupuesto>();
    }
    
    public void addLineaLicencia(){
    	LineaPresupuesto lineaLicencia = new LineaPresupuesto();
    	lineaLicencia.setDescripcionLineaPresupuesto("Hacernos cargo de la licencia");
    	lineaLicencia.setIdLinea(Long.valueOf("-1"));
    	if(licenciaNuestra){
    		lineas.add(lineaLicencia);
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención!", "Creada línea automática"));
    	}else{
    		lineas.remove(lineaLicencia);
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención!", "Eliminada línea automática"));
    	}
    	
    }
    
    public void prepareLineaPresupuesto(){
    	newLineaPresupuesto = new LineaPresupuesto();
    }
    public void addLineaPresupuesto(){
    	this.lineas.add(newLineaPresupuesto);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención!", "Creada nueva línea en el presupuesto"));
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

	public List<LineaPresupuesto> getLineas() {
		return lineas;
	}


	public void setLineas(List<LineaPresupuesto> lineas) {
		this.lineas = lineas;
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

	public LineaPresupuesto getNewLineaPresupuesto() {
		return newLineaPresupuesto;
	}

	public void setNewLineaPresupuesto(LineaPresupuesto newLineaPresupuesto) {
		this.newLineaPresupuesto = newLineaPresupuesto;
	}
	
	public StreamedContent getPresupuestoFile(){

		StreamedContent file = null;
		newPresupuesto.setLineas(lineas);
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

}
