package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Partida;
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
	@Autowired
	GenericDAO<Partida, Serializable> partidaDao;
	
	  
	
	@Transactional(readOnly = true)
	public Presupuesto getPresupuesto(int id){
		return genericDao.read(Presupuesto.class,id);
	}
	
	@Transactional(readOnly = true)
	public Partida getPartida(int id){
		return partidaDao.read(Partida.class,id);
	}
	
	@Transactional
	public List<Presupuesto> getPresupuestos() throws SystemException{
		return genericDao.findAll(Presupuesto.class);
	}
	
	@Transactional
	public String getNuevoNumeroPresupuesto(Presupuesto presupuesto) throws SystemException{
		return "";
	}
	
	@Transactional
	public void createPresupuesto(Presupuesto pre) throws SystemException{
		 genericDao.create(pre);
	}
	
	@Transactional
	public void createPartida(Partida par) throws SystemException{
		 partidaDao.create(par);
	}

	@Transactional
	public void updatePresupuesto(Presupuesto pre) throws SystemException{
		 genericDao.update(pre);
	}
	
	@Transactional
	public void updatePartida(Partida par) throws SystemException{
		 partidaDao.create(par);
	}

	public GenericDAO<Presupuesto, Serializable> getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO<Presupuesto, Serializable> genericDao) {
		this.genericDao = genericDao;
	}
}
