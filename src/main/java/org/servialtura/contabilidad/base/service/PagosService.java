package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("pagosService")
@Transactional(readOnly = true)
public class PagosService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Pago, Serializable> genericDao;
	
	  
	
	@Transactional(readOnly = true)
	public Pago getPago(int id){
		return genericDao.read(Pago.class,id);
	}
	

	
	@Transactional
	public List<Pago> getPagos() throws SystemException{
		return genericDao.findAll(Pago.class);
	}
	
	@Transactional
	public void createPago(Pago pago) throws SystemException{
		 genericDao.create(pago);
	}

	@Transactional
	public void updatePago(Pago pago) throws SystemException{
		 genericDao.update(pago);
	}
}
