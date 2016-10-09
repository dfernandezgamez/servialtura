package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.CategoriaMaterial;
import org.servialtura.contabilidad.base.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("gastosService")
@Transactional(readOnly = true)
public class GastosService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Material, Serializable> genericDao;
	
	@Autowired
	GenericDAO<CategoriaMaterial, Serializable> genericDaoCategoria;
	  
	
	@Transactional(readOnly = true)
	public Material getMaterial(int id){
		return genericDao.read(Material.class,id);
	}
	
	@Transactional
	public List<Material> getMateriales() throws SystemException{
		return genericDao.findAll(Material.class);
	}
	
	@Transactional
	public void createMaterial(Material solicitud) throws SystemException{
		 genericDao.create(solicitud);
	}

	@Transactional
	public void updateMaterial(Material solicitud) throws SystemException{
		 genericDao.update(solicitud);
	}
	
	@Transactional(readOnly = true)
	public CategoriaMaterial getCategoriaMaterial(int id){
		return genericDaoCategoria.read(CategoriaMaterial.class,id);
	}
	
	@Transactional
	public List<CategoriaMaterial> getCategoriasMaterial() throws SystemException{
		return genericDaoCategoria.findAll(CategoriaMaterial.class);
	}
	
	@Transactional
	public void createCategoriaMaterial(CategoriaMaterial cate) throws SystemException{
		genericDaoCategoria.create(cate);
	}

	@Transactional
	public void updateCategoriaMaterial(CategoriaMaterial cate) throws SystemException{
		genericDaoCategoria.update(cate);
	}
}
