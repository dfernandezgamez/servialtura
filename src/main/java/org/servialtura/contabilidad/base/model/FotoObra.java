package org.servialtura.contabilidad.base.model;
// default package
// Generated 08-ene-2017 17:17:12 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * FotosObra generated by hbm2java
 */
@Entity
@Table(name = "FOTO_OBRA", catalog = "SERVIALTURA")
public class FotoObra implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4057766663975813037L;
	private Integer idFile;
	private FileStore fileStore;
	private Obra obra;

	public FotoObra() {
	}

	public FotoObra(FileStore fileStore) {
		this.fileStore = fileStore;
	}



	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "fileStore"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "ID_FILE", unique = true, nullable = false)
	public Integer getIdFile() {
		return this.idFile;
	}

	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public FileStore getFileStore() {
		return this.fileStore;
	}

	public void setFileStore(FileStore fileStore) {
		this.fileStore = fileStore;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obra")
	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

}
