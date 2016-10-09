package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("facturasService")
@Transactional(readOnly = true)
public class FacturasService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Factura, Serializable> genericDao;
	
	  
	
	@Transactional(readOnly = true)
	public Factura getAthleteById(int id){
		return genericDao.read(Factura.class,id);
	}
	

	
	@Transactional
	public List<Factura> getFacturas() throws SystemException{
		return genericDao.findAll(Factura.class);
	}
	
	@Transactional
	public void createFactura(Factura factura) throws SystemException{
		 genericDao.create(factura);
	}

	@Transactional
	public void updateFactura(Factura factura) throws SystemException{
		 genericDao.update(factura);
	}
}
