package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.MaterialesPresupuesto;
import org.servialtura.contabilidad.base.model.Partida;
import org.servialtura.contabilidad.base.model.Presupuesto;
import org.servialtura.contabilidad.base.utils.CriteriaFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("presupuestosService")
@Transactional(readOnly = true)
public class PresupuestosService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Presupuesto, Serializable> genericDao;
	@Autowired
	GenericDAO<Partida, Serializable> partidaDao;
	@Autowired
	GenericDAO<MaterialesPresupuesto, Serializable> materialDao;
	
	  
	
	@Transactional(readOnly = true)
	public Presupuesto getPresupuesto(int id){
		return genericDao.read(Presupuesto.class,id);
	}
	
	@Transactional(readOnly = true)
	public Partida getPartida(int id){
		return partidaDao.read(Partida.class,id);
	}
	
	@Transactional
	public List<Presupuesto> getPresupuestos() throws SystemException{
		return genericDao.findAll(Presupuesto.class);
	}
	
	@Transactional
	public String getNuevoNumeroPresupuesto(Presupuesto presupuesto) throws SystemException{
		return "";
	}
	
	@Transactional
	public void createPresupuesto(Presupuesto pre) throws SystemException{
		String code=findNextPresupuestoNumber(pre);
		pre.setNumeroPresupuesto(code);
		genericDao.create(pre);
	}
	
	@Transactional
	public void createPartida(Partida par) throws SystemException{
		 partidaDao.create(par);
	}
	
	@Transactional
	public void createMaterialInPresupuesto(MaterialesPresupuesto par) throws SystemException{
		 materialDao.create(par);
	}
	
	@Transactional
	public void deletePartida(Partida par) throws SystemException{
		 partidaDao.delete(par);
	}

	@Transactional
	public void updatePresupuesto(Presupuesto pre) throws SystemException{
		 genericDao.update(pre);
	}
	
	@Transactional
	public void updatePartida(Partida par) throws SystemException{
		 partidaDao.create(par);
	}
	
	 
	private String findNextPresupuestoNumber(Presupuesto presupuesto) throws SystemException{
		String presupuestoNumber;

		//PART code + yyyy + mm
		String strYear = new SimpleDateFormat("yy").format(presupuesto.getFechaCreacion());
		String strMonth = new SimpleDateFormat("MM").format(presupuesto.getFechaCreacion());
		presupuestoNumber ="PRE"+  strYear + strMonth;

		//NUMBER
		NumberFormat numberFourFormat = new DecimalFormat("000");
		Integer nextMeetingNumber =getNextPresupuestoNumber(presupuesto.getFechaCreacion());
		presupuestoNumber += numberFourFormat.format(Integer.valueOf(nextMeetingNumber));
		return presupuestoNumber;
		
	}

	public GenericDAO<Presupuesto, Serializable> getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO<Presupuesto, Serializable> genericDao) {
		this.genericDao = genericDao;
	}
	
	
	
	private Integer getNextPresupuestoNumber(Date date) throws SystemException {
		CriteriaFilters filters = new CriteriaFilters(Presupuesto.class);
		DateTime dt = new DateTime(date);
		DateTime start = dt.withDayOfMonth(1).withTimeAtStartOfDay();
		DateTime end = start.plusMonths(1).minusMillis(1);
		addDateFromTo(filters,"fechaCreacion",start.toDate(),end.toDate(),true);
		List<Presupuesto> list = genericDao.findByCriteria(filters);
		return list.size()+1;
	}
	
	
	
	
	public void addDateFromTo(CriteriaFilters filters, String property, Date from, Date to, Boolean includeLimits) {

		if (from != null && to != null) {
			Criterion dateFrom;
			Criterion dateUntil;
			if (includeLimits){
				dateFrom = Restrictions.ge(property, from);
				dateUntil = Restrictions.le(property, to);
			}
			else{
				dateFrom = Restrictions.gt(property, from);
				dateUntil = Restrictions.lt(property, to);
			}
			filters.addCriterion(dateFrom);
			filters.addCriterion(dateUntil);
		}

		else if (to != null && from == null) {

			Criterion dateUntil;
			if (includeLimits){
				dateUntil = Restrictions.le(property, to);
			}
			else{
				dateUntil = Restrictions.lt(property, to);
			}
			filters.addCriterion(dateUntil);
		}

		else if (to == null && from != null) {
			Criterion dateFrom;
			if (includeLimits){
				dateFrom = Restrictions.ge(property, from);
			}
			else{
				dateFrom = Restrictions.gt(property, from);
			}
			filters.addCriterion(dateFrom);
		}

	}
}
