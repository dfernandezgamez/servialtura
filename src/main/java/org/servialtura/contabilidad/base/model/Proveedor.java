package org.servialtura.contabilidad.base.model;
// default package
// Generated 24-dic-2016 14:17:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Proveedor generated by hbm2java
 */
@Entity
@Table(name = "PROVEEDOR", catalog = "SERVIALTURA")
public class Proveedor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006301295758170283L;
	private Integer idProveedor;
	private String nombreProveedor;
	private String emailProveedor;
	private String direccionProveedor;
	private String telefonoProveedor;
	private String notasProveedor;

	public Proveedor() {
	}

	public Proveedor(String nombreProveedor, String emailProveedor, String direccionProveedor,
			String telefonoProveedor) {
		this.nombreProveedor = nombreProveedor;
		this.emailProveedor = emailProveedor;
		this.direccionProveedor = direccionProveedor;
		this.telefonoProveedor = telefonoProveedor;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idProveedor", unique = true, nullable = false)
	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column(name = "nombre_proveedor")
	public String getNombreProveedor() {
		return this.nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	@Column(name = "email_proveedor")
	public String getEmailProveedor() {
		return this.emailProveedor;
	}

	public void setEmailProveedor(String emailProveedor) {
		this.emailProveedor = emailProveedor;
	}

	@Column(name = "direccion_proveedor")
	public String getDireccionProveedor() {
		return this.direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	@Column(name = "telefono_proveedor")
	public String getTelefonoProveedor() {
		return this.telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	@Column(name = "notas_proveedor")
	public String getNotasProveedor() {
		return notasProveedor;
	}

	public void setNotasProveedor(String notasProveedor) {
		this.notasProveedor = notasProveedor;
	}

}
