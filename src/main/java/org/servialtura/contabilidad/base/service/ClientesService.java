package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Empresa;
import org.servialtura.contabilidad.base.utils.CriteriaFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("clientesService")
@Transactional(readOnly = true)
public class ClientesService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Empresa, Serializable> genericDao;
	  
	
	@Transactional(readOnly = true)
	public Empresa getClienteById(int id){
		return genericDao.read(Empresa.class,id);
	}
	
	
	@Transactional
	public Empresa getClienteByName(String userName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Empresa.class);
		filters.setOrder(Order.asc("nombreEmpresa"));
		filters.addCriterion(Restrictions.like("nombreEmpresa", userName));

		return genericDao.findSingleByCriteria(filters);

	}
	
	@Transactional
	public List<Empresa> findEmpresas(String clientName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Empresa.class);
		filters.setOrder(Order.asc("nombreEmpresa"));
		filters.addCriterion(Restrictions.like("nombreEmpresa", clientName, MatchMode.ANYWHERE));

		return genericDao.findByCriteria(filters);

	}
	
	@Transactional
	public List<Empresa> listClientes() throws SystemException{
				
		return genericDao.findAll(Empresa.class);

	}
	
	@Transactional
	public void createCliente(Empresa cliente) throws SystemException{
		 genericDao.create(cliente);
	}
	
	@Transactional
	public void updateCliente(Empresa cliente) throws SystemException{
		 genericDao.update(cliente);
	}
	
	public GenericDAO<Empresa, Serializable> getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDAO<Empresa, Serializable> genericDao) {
		this.genericDao = genericDao;
	}
	
}
