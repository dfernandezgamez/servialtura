package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.criterion.Restrictions;
import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Usuario;
import org.servialtura.contabilidad.base.utils.CriteriaFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("UsersService")
@Transactional(readOnly = true)
public class UsersService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Usuario, Serializable> genericDao;
	  
	
	@Transactional(readOnly = true)
	public Usuario getUsuariById(int id){
		return genericDao.read(Usuario.class,id);
	}
	@Transactional(readOnly = true)
	public boolean login(String uName,String password){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user", uName);
		map.put("contra", password);
		
		List<Object> llista = genericDao.findBySQL("Select id from Usuario where usuario = :user and password = :contra", map );
		if(llista.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	@Transactional
	public Usuario getUsuarioByUserName(String userName) throws SystemException{
		
		CriteriaFilters filters = new CriteriaFilters(Usuario.class);
		
		filters.addCriterion(Restrictions.eq("usuario", userName));

		return genericDao.findSingleByCriteria(filters);

	}
	
	@Transactional
	public List<Usuario> listUsuarios() throws SystemException{
				
		return genericDao.findAll(Usuario.class);

	}
	
	@Transactional
	public void createUsuario(Usuario usuario) throws SystemException{
		 genericDao.create(usuario);
	}
	
	@Transactional
	public void updateUsuario(Usuario usuario) throws SystemException{
		 genericDao.update(usuario);
	}
	
	public GenericDAO<Usuario, Serializable> getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDAO<Usuario, Serializable> genericDao) {
		this.genericDao = genericDao;
	}
	
}
