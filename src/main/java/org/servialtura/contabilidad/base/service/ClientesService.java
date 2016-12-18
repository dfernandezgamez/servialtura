package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Cliente;
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
	GenericDAO<Cliente, Serializable> genericDao;
	  
	
	@Transactional(readOnly = true)
	public Cliente getClienteById(int id){
		return genericDao.read(Cliente.class,id);
	}
	
	
	@Transactional
	public Cliente getClienteByName(String userName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Cliente.class);
		
		filters.addCriterion(Restrictions.like("nombreCliente", userName));

		return genericDao.findSingleByCriteria(filters);

	}
	
	@Transactional
	public List<Cliente> findClients(String clientName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Cliente.class);
		
		filters.addCriterion(Restrictions.like("nombreCliente", clientName, MatchMode.ANYWHERE));

		return genericDao.findByCriteria(filters);

	}
	
	@Transactional
	public List<Cliente> listClientes() throws SystemException{
				
		return genericDao.findAll(Cliente.class);

	}
	
	@Transactional
	public void createCliente(Cliente cliente) throws SystemException{
		 genericDao.create(cliente);
	}
	
	@Transactional
	public void updateCliente(Cliente cliente) throws SystemException{
		 genericDao.update(cliente);
	}
	
	public GenericDAO<Cliente, Serializable> getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDAO<Cliente, Serializable> genericDao) {
		this.genericDao = genericDao;
	}
	
}
