package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("solicitudesService")
@Transactional(readOnly = true)
public class SolicitudesService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Solicitud, Serializable> genericDao;
	
	  
	
	@Transactional(readOnly = true)
	public Solicitud getSolicitud(int id){
		return genericDao.read(Solicitud.class,id);
	}
	
	@Transactional
	public List<Solicitud> getSolicitudes() throws SystemException{
		return genericDao.findAll(Solicitud.class);
	}
	
	@Transactional
	public void createSolicitud(Solicitud solicitud) throws SystemException{
		 genericDao.create(solicitud);
	}

	@Transactional
	public void updateSolicitud(Solicitud solicitud) throws SystemException{
		 genericDao.update(solicitud);
	}
}
