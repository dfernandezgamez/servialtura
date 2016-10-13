package org.servialtura.contabilidad.base.beans.presupuestos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.model.LineaPresupuesto;
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
    private Presupuesto newPresupuesto;
    private List<LineaPresupuesto> lineas;
    private Boolean necesitaLicencia;
    private Boolean licenciaNuestra;
    private LineaPresupuesto newLineaPresupuesto;
     
    
    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    @ManagedProperty(value="#{solicitudesService}")
    private SolicitudesService solicitudesService;
    
    
    @PostConstruct
    public void init(){
    	String idSolicitud = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idSolicitud");
    	Solicitud sol=solicitudesService.getSolicitud(Integer.valueOf(idSolicitud));
    	this.newPresupuesto = new Presupuesto();
    	newPresupuesto.setSolicitud(sol);
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
    
    public void createPresupuesto(){
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


	public SolicitudesService getSolicitudesService() {
		return solicitudesService;
	}


	public void setSolicitudesService(SolicitudesService solicitudesService) {
		this.solicitudesService = solicitudesService;
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
		// Create a new empty document
    	PDDocument document = new PDDocument();
    	PDStream ps=new PDStream(document);
    	StreamedContent file = null;
    	
    	
    	// Create a new blank page and add it to the document
    	PDPage page = new PDPage();
    	document.addPage( page );

    	// Create a new font object selecting one of the PDF base fonts
    	PDFont font = PDType1Font.HELVETICA_BOLD;
    	

    	
    	try {
    		// Start a new content stream which will "hold" the to be created content
        	PDPageContentStream contentStream = new PDPageContentStream(document, page);
        	// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        	contentStream.beginText();
        	contentStream.setFont( font, 12 );
        	contentStream.drawString("hello");
        	contentStream.endText();

        	// Make sure that the content stream is closed:
        	contentStream.close();
			
//			InputStream stream = ps.createInputStream();
//			file = new DefaultStreamedContent(stream, "application/pdf", "presupuesto.pdf");
			document.save("presupuesto.pdf");
			document.close();
			
			File newFile = new File("presupuesto.pdf");
			InputStream stream = new FileInputStream(newFile);
			file = new DefaultStreamedContent(stream, "application/pdf", "presupuesto.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return file;
	}

}
