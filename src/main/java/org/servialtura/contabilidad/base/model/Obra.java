package org.servialtura.contabilidad.base.model;
// default package
// Generated 01-oct-2016 18:39:37 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Obra generated by hbm2java
 */
@Entity
@Table(name = "OBRA", catalog = "SERVIALTURA")
public class Obra implements java.io.Serializable {

	private Integer idObra;
	private String nombreObra;
	private String descripcionObra;
	private Date fechaInicio;
	private String estadoObra;
	private Date fechaFin;
	private String tipoLicencia;
	private Integer idPeticion;

	public Obra() {
	}

	public Obra(String nombreObra, String descripcionObra, Date fechaInicio, String estadoObra, Date fechaFin,
			String tipoLicencia, Integer idPeticion) {
		this.nombreObra = nombreObra;
		this.descripcionObra = descripcionObra;
		this.fechaInicio = fechaInicio;
		this.estadoObra = estadoObra;
		this.fechaFin = fechaFin;
		this.tipoLicencia = tipoLicencia;
		this.idPeticion = idPeticion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idObra", unique = true, nullable = false)
	public Integer getIdObra() {
		return this.idObra;
	}

	public void setIdObra(Integer idObra) {
		this.idObra = idObra;
	}

	@Column(name = "nombre_obra", length = 100)
	public String getNombreObra() {
		return this.nombreObra;
	}

	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}

	@Column(name = "descripcion_obra", length = 65535)
	public String getDescripcionObra() {
		return this.descripcionObra;
	}

	public void setDescripcionObra(String descripcionObra) {
		this.descripcionObra = descripcionObra;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "estado_obra", length = 45)
	public String getEstadoObra() {
		return this.estadoObra;
	}

	public void setEstadoObra(String estadoObra) {
		this.estadoObra = estadoObra;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "tipo_licencia", length = 20)
	public String getTipoLicencia() {
		return this.tipoLicencia;
	}

	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	@Column(name = "idPeticion")
	public Integer getIdPeticion() {
		return this.idPeticion;
	}

	public void setIdPeticion(Integer idPeticion) {
		this.idPeticion = idPeticion;
	}

}
