package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("empleadosService")
@Transactional(readOnly = true)
public class EmpleadosService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Empleado, Serializable> genericDao;
	
	  
	
	@Transactional(readOnly = true)
	public Empleado getEmpleado(int id){
		return genericDao.read(Empleado.class,id);
	}
	

	
	@Transactional
	public List<Empleado> getEmpleados() throws SystemException{
		return genericDao.findAll(Empleado.class);
	}
	
	@Transactional
	public void createEmpleado(Empleado empleado) throws SystemException{
		 genericDao.create(empleado);
	}

	@Transactional
	public void updateEmpleado(Empleado empleado) throws SystemException{
		 genericDao.update(empleado);
	}
}
