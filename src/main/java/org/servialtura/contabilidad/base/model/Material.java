package org.servialtura.contabilidad.base.model;
// default package
// Generated 18-dic-2016 18:36:13 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Material generated by hbm2java
 */
@Entity
@Table(name = "MATERIAL", catalog = "SERVIALTURA")
public class Material implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 611856476345902453L;
	private Integer idMaterial;
	private CategoriaMaterial categoriaMaterial;
	private UnidadMaterial unidadMaterial;
	private String nombreMaterial;
	private BigDecimal precioUnitario;
	private Proveedor proveedor;
	private Set<MaterialesPresupuesto> materialesPresupuestos = new HashSet<MaterialesPresupuesto>(0);

	public Material() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idMaterial", unique = true, nullable = false)
	public Integer getIdMaterial() {
		return this.idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoriaMaterial")
	public CategoriaMaterial getCategoriaMaterial() {
		return this.categoriaMaterial;
	}

	public void setCategoriaMaterial(CategoriaMaterial categoriaMaterial) {
		this.categoriaMaterial = categoriaMaterial;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUnidadMaterial")
	public UnidadMaterial getUnidadMaterial() {
		return this.unidadMaterial;
	}

	public void setUnidadMaterial(UnidadMaterial unidadMaterial) {
		this.unidadMaterial = unidadMaterial;
	}

	@Column(name = "nombreMaterial")
	public String getNombreMaterial() {
		return this.nombreMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	@Column(name = "precioUnitario", precision = 34)
	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.material")
	public Set<MaterialesPresupuesto> getMaterialesPresupuestos() {
		return this.materialesPresupuestos;
	}

	public void setMaterialesPresupuestos(Set<MaterialesPresupuesto> materialesPresupuestos) {
		this.materialesPresupuestos = materialesPresupuestos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMaterial == null) ? 0 : idMaterial.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Material))
			return false;
		Material other = (Material) obj;
		if (idMaterial == null) {
			if (other.getIdMaterial() != null)
				return false;
		} else if (!idMaterial.equals(other.getIdMaterial()))
			return false;
		return true;
	}

}
