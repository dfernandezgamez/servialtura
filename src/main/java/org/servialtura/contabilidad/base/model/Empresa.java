package org.servialtura.contabilidad.base.model;
// default package
// Generated 18-dic-2016 13:04:56 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Empresa generated by hbm2java
 */
@Entity
@Table(name = "EMPRESA", catalog = "SERVIALTURA")
public class Empresa implements java.io.Serializable {

	private static final long serialVersionUID = 5537411214887274014L;
	private Integer idEmpresa;
	private String nombreEmpresa;
	private String nifEmpresa;
	private String emailEmpresa;
	private String telefonoEmpresa;
	private String direccionEmpresa;

	public Empresa() {
	}

	public Empresa(String nombreEmpresa, String nifEmpresa, String emailEmpresa, String telefonoEmpresa,
			String direccionEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
		this.nifEmpresa = nifEmpresa;
		this.emailEmpresa = emailEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.direccionEmpresa = direccionEmpresa;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idEmpresa", unique = true, nullable = false)
	public Integer getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Column(name = "nombre_empresa")
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Column(name = "nif_empresa")
	public String getNifEmpresa() {
		return this.nifEmpresa;
	}

	public void setNifEmpresa(String nifEmpresa) {
		this.nifEmpresa = nifEmpresa;
	}

	@Column(name = "email_empresa")
	public String getEmailEmpresa() {
		return this.emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	@Column(name = "telefono_empresa")
	public String getTelefonoEmpresa() {
		return this.telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	@Column(name = "direccion_empresa")
	public String getDireccionEmpresa() {
		return this.direccionEmpresa;
	}

	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}


	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Empresa))
			return false;
		Empresa other = (Empresa) obj;
		if (idEmpresa == null) {
			if (other.getIdEmpresa() != null)
				return false;
		} else if (!idEmpresa.equals(other.getIdEmpresa()))
			return false;
		return true;
	}

}
