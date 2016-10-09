package org.servialtura.contabilidad.base.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.utils.CriteriaFilters;


public interface GenericDAO <T, PK extends Serializable> {
	  /** Persist the newInstance object into database 
	 * @throws SystemException */
    PK create(T newInstance) throws SystemException;


    /** Save changes made to a persistent object.  */
    void update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
    
    List<Object> findBySQL(String sqlQuery,HashMap<String,Object> parameters);
    
    List<T> findByHQL(String sqlQuery,HashMap<String,Object> parameters);
    
    T findSingleByCriteria(CriteriaFilters filter) throws SystemException;
    
    List<T> findByCriteria(CriteriaFilters filter) throws SystemException;

	List<T> findAll(Class classe) throws SystemException;
	
	void setType(Class<T> type);
	
    void saveOrUpdate(T newInstance) throws SystemException;
    
	T read(Class classe, PK id);




}
