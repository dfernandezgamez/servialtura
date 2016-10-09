package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("presupuestosService")
@Transactional(readOnly = true)
public class PresupuestosService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Presupuesto, Serializable> genericDao;
	
	  
	
	@Transactional(readOnly = true)
	public Presupuesto getPresupuesto(int id){
		return genericDao.read(Presupuesto.class,id);
	}
	
	@Transactional
	public List<Presupuesto> getPresupuestos() throws SystemException{
		return genericDao.findAll(Presupuesto.class);
	}
	
	@Transactional
	public void createPresupuesto(Presupuesto pre) throws SystemException{
		 genericDao.create(pre);
	}

	@Transactional
	public void updatePresupuesto(Presupuesto pre) throws SystemException{
		 genericDao.update(pre);
	}
}
