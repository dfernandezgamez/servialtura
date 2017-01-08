package org.servialtura.contabilidad.base.beans.obras;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.model.Album;
import org.servialtura.contabilidad.base.model.FileMeta;
import org.servialtura.contabilidad.base.model.FileStore;
import org.servialtura.contabilidad.base.model.FotoObra;
import org.servialtura.contabilidad.base.model.Obra;
import org.servialtura.contabilidad.base.service.ObrasService;
import org.servialtura.contabilidad.base.service.PresupuestosService;


@ManagedBean(name = "editObraBean")
@ViewScoped
public class EditObraBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

    @ManagedProperty(value="#{presupuestosService}")
    private PresupuestosService presupuestosService;
    
    @ManagedProperty(value="#{obrasService}")
    private ObrasService obrasService;
    
    private Obra selectedObra;
    private Album newAlbum = new Album();
    private FotoObra newFoto = new FotoObra();
    private UploadedFile file;
    private FileMeta fileMeta = new FileMeta();
    private FileStore fileStore = new FileStore();
    
    
    @PostConstruct
    public void init(){
    	String idObra = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idObra");
    	selectedObra=obrasService.getObra(Integer.valueOf(idObra));
    }


	public PresupuestosService getPresupuestosService() {
		return presupuestosService;
	}


	public void setPresupuestosService(PresupuestosService presupuestosService) {
		this.presupuestosService = presupuestosService;
	}
	
	public void guardarAlbum(){
		try {
			obrasService.createAlbum(newAlbum);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando álbum"));
		}
    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Álbum creado correctamente"));
    	 selectedObra.getAlbums().add(newAlbum);
	}
	
	public void guardarFoto(){
		if(file != null) {
			transFormarFoto();
			try {
				obrasService.createfoto(newFoto);
			} catch (SystemException e) {
				   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error guardando foto"));
			}
	    	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Foto añadida correctamente"));
		}
	}
	
	private void transFormarFoto() {
		this.fileMeta.setContentType(file.getContentType());
		this.fileMeta.setFileName(file.getFileName());
		this.fileMeta.setFileSize(file.getSize());
		this.fileMeta.setUploadDate(new Date());
		this.fileStore.setFileContent(file.getContents());
	}
	
	public void handleFotoUpload(FileUploadEvent event) {
		this.file=event.getFile();
    }


	public void prepareNewFoto(){
		fileStore.setFileMeta(fileMeta);
		fileMeta.setFileStore(fileStore);
		this.newFoto = new FotoObra();
		newFoto.setFileStore(fileStore);
		newFoto.setObra(this.selectedObra);
	}


	public ObrasService getObrasService() {
		return obrasService;
	}


	public void setObrasService(ObrasService obrasService) {
		this.obrasService = obrasService;
	}


	public Obra getSelectedObra() {
		return selectedObra;
	}


	public void setSelectedObra(Obra selectedObra) {
		this.selectedObra = selectedObra;
	}


	public Album getNewAlbum() {
		return newAlbum;
	}


	public void setNewAlbum(Album newAlbum) {
		this.newAlbum = newAlbum;
	}


	public FotoObra getNewFoto() {
		return newFoto;
	}


	public void setNewFoto(FotoObra newFoto) {
		this.newFoto = newFoto;
	}


	public UploadedFile getFile() {
		return file;
	}


	public void setFile(UploadedFile file) {
		this.file = file;
	}


	public FileMeta getFileMeta() {
		return fileMeta;
	}


	public void setFileMeta(FileMeta fileMeta) {
		this.fileMeta = fileMeta;
	}


	public FileStore getFileStore() {
		return fileStore;
	}


	public void setFileStore(FileStore fileStore) {
		this.fileStore = fileStore;
	}
    




}
