package org.servialtura.contabilidad.base.model;
// default package
// Generated 08-ene-2017 17:17:12 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * FileStore generated by hbm2java
 */
@Entity
@Table(name = "FILE_STORE", catalog = "SERVIALTURA")
public class FileStore implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 655772133848933226L;
	private Integer idFile;
	private byte[] fileContent;
	private FileMeta fileMeta;

	public FileStore() {
	}

	public FileStore(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_FILE", unique = true, nullable = false)
	public Integer getIdFile() {
		return this.idFile;
	}

	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}

	@Column(name = "FILE_CONTENT", nullable = false)
	public byte[] getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "fileStore", cascade = CascadeType.ALL)
	public FileMeta getFileMeta() {
		return this.fileMeta;
	}

	public void setFileMeta(FileMeta fileMeta) {
		this.fileMeta = fileMeta;
	}

}
