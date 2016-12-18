package org.servialtura.contabilidad.base.model;
// default package
// Generated 18-dic-2016 18:36:13 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Servicio generated by hbm2java
 */
@Entity
@Table(name = "SERVICIO", catalog = "SERVIALTURA")
public class Servicio implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 611856476345902453L;
	private Integer idServicio;
	private CategoriaServicio categoriaServicio;
	private UnidadServicio unidadServicio;
	private String nombreServicio;
	private BigDecimal precioUnitario;
	private String tipoPrecioUnitario;

	public Servicio() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idServicio", unique = true, nullable = false)
	public Integer getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoriaServicio")
	public CategoriaServicio getCategoriaServicio() {
		return this.categoriaServicio;
	}

	public void setCategoriaServicio(CategoriaServicio categoriaServicio) {
		this.categoriaServicio = categoriaServicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUnidadServicio")
	public UnidadServicio getUnidadServicio() {
		return this.unidadServicio;
	}

	public void setUnidadServicio(UnidadServicio unidadServicio) {
		this.unidadServicio = unidadServicio;
	}

	@Column(name = "nombreServicio")
	public String getNombreServicio() {
		return this.nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	@Column(name = "precioUnitario", precision = 34)
	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Column(name = "tipoPrecioUnitario")
	public String getTipoPrecioUnitario() {
		return this.tipoPrecioUnitario;
	}

	public void setTipoPrecioUnitario(String tipoPrecioUnitario) {
		this.tipoPrecioUnitario = tipoPrecioUnitario;
	}

}
