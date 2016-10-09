package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("obrasService")
@Transactional(readOnly = true)
public class ObrasService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Obra, Serializable> genericDao;
	
	  
	
	@Transactional(readOnly = true)
	public Obra getObra(int id){
		return genericDao.read(Obra.class,id);
	}
	

	
	@Transactional
	public List<Obra> getObras() throws SystemException{
		return genericDao.findAll(Obra.class);
	}
	
	@Transactional
	public void createObra(Obra obra) throws SystemException{
		 genericDao.create(obra);
	}

	@Transactional
	public void updateObra(Obra obra) throws SystemException{
		 genericDao.update(obra);
	}
}
