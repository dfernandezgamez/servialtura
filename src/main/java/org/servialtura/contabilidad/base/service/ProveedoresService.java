package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Material;
import org.servialtura.contabilidad.base.model.Proveedor;
import org.servialtura.contabilidad.base.utils.CriteriaFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("proveedoresService")
@Transactional(readOnly = true)
public class ProveedoresService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Material, Serializable> materialesDao;
	
	@Autowired
	GenericDAO<Proveedor, Serializable> proveedoresDao;
	  
	
	@Transactional(readOnly = true)
	public Material getMaterialById(int id){
		return materialesDao.read(Material.class,id);
	}
	
	
	@Transactional
	public Material getMaterialByName(String materialName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Material.class);
		
		filters.addCriterion(Restrictions.like("nombreMaterial", materialName));

		return materialesDao.findSingleByCriteria(filters);

	}
	
	@Transactional
	public List<Material> findMateriales(String materialName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Material.class);
		filters.setOrder(Order.asc("nombreProveedor"));
		filters.addCriterion(Restrictions.like("nombreMaterial", materialName, MatchMode.ANYWHERE));

		return materialesDao.findByCriteria(filters);

	}
	
	@Transactional
	public List<Proveedor> listProveedores() throws SystemException{
		CriteriaFilters filters = new CriteriaFilters(Proveedor.class);
		filters.setOrder(Order.asc("nombreProveedor"));
		return proveedoresDao.findByCriteria(filters);

	}
	
	@Transactional
	public List<Proveedor> findProveedores(String materialName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Proveedor.class);
		
		filters.addCriterion(Restrictions.like("nombreProveedor", materialName, MatchMode.ANYWHERE));

		return proveedoresDao.findByCriteria(filters);

	}
	
	@Transactional(readOnly = true)
	public Proveedor getProveedorById(int id){
		return proveedoresDao.read(Proveedor.class,id);
	}
	
	@Transactional
	public List<Material> listMaterials() throws SystemException{
				
		return materialesDao.findAll(Material.class);

	}
	
	@Transactional
	public void createMaterial(Material cliente) throws SystemException{
		 materialesDao.create(cliente);
	}
	
	@Transactional
	public void updateMaterial(Material cliente) throws SystemException{
		 materialesDao.update(cliente);
	}


	public GenericDAO<Material, Serializable> getMaterialesDao() {
		return materialesDao;
	}


	public void setMaterialesDao(GenericDAO<Material, Serializable> materialesDao) {
		this.materialesDao = materialesDao;
	}


	public GenericDAO<Proveedor, Serializable> getProveedoresDao() {
		return proveedoresDao;
	}


	public void setProveedoresDao(GenericDAO<Proveedor, Serializable> proveedoresDao) {
		this.proveedoresDao = proveedoresDao;
	}
	
	
}
