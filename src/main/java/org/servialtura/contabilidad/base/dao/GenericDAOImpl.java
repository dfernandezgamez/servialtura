package org.servialtura.contabilidad.base.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.SystemException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.utils.CriteriaFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class GenericDAOImpl<T, PK extends Serializable> implements
		GenericDAO<T, PK> {

	private Class<T> type;

	public GenericDAOImpl(Class<T> type) {
		this.type = type;
	}

	public GenericDAOImpl() {
		super();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK create(T newInstance) throws SystemException {
		try{
		return (PK) sessionFactory.getCurrentSession().save(newInstance);
		}catch (Exception e){
			throw new SystemException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T read(Class classe,PK id) {
		return (T) sessionFactory.getCurrentSession().get(classe, id);
	}

	@Override
	public void update(T transientObject) {
		sessionFactory.getCurrentSession().update(transientObject);

	}

	@Override
	public void delete(T persistentObject) {
		sessionFactory.getCurrentSession().delete(persistentObject);

	}

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findBySQL(String sqlQuery, HashMap<String,Object> parameters) {
        Query query = getSessionFactory().getCurrentSession()
                .createSQLQuery(sqlQuery);
        
        for(Entry<String,Object> e : parameters.entrySet()){
        	
        	query.setParameter(e.getKey(), e.getValue());
        	
        }
        return (List<Object>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHQL(String hqlQuery, HashMap<String, Object> parameters) {
        Query query = getSessionFactory().getCurrentSession()
                .createQuery(hqlQuery);
        
        for(Entry<String,Object> e : parameters.entrySet()){
        	
        	query.setParameter(e.getKey(), e.getValue());
        	
        }
        return (List<T>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findSingleByCriteria(CriteriaFilters filters) throws SystemException {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(filters.getClasse());
		if(filters.getCriterionsList() == null || filters.getCriterionsList().isEmpty()){
			throw new SystemException("findByCriteria -> Empty filters");
		}
		for(Criterion criterion : filters.getCriterionsList()){
			criteria.add(criterion);
		}

		List result = criteria.list();
		
		if(result == null || result.isEmpty()){
			throw new SystemException();
		}else{
			return (T) result.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(CriteriaFilters filters) throws SystemException {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(filters.getClasse());
		createAliases(filters, criteria);
		addOrder(filters, criteria);
		
		for(Criterion criterion : filters.getCriterionsList()){
			criteria.add(criterion);
		}

		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class classe) throws SystemException {
		
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(classe);
		
		return criteria.list();
	}
	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	@Override
	public void saveOrUpdate(T newInstance) throws SystemException {
		try{
		sessionFactory.getCurrentSession().saveOrUpdate(newInstance);
		}catch (Exception e){
			throw new SystemException();
		}
	}

	private void createAliases(CriteriaFilters filters, Criteria criteria){
		if(filters.getAliases() != null){
			for (Map.Entry<String, String> entry : filters.getAliases().entrySet())
			{
				criteria.createAlias(entry.getKey(), entry.getValue());
			}
		}
	}
	private void addOrder(CriteriaFilters filters, Criteria criteria){
		
		if(filters.getOrder() != null){
			criteria.addOrder(filters.getOrder());
		}
		
	}

}
