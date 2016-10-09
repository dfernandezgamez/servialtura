package org.servialtura.contabilidad.base.utils;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class CriteriaFilters {

	private ArrayList<Criterion> criterionsList;
	private Class classe;
	Map<String, String> aliases;
	Order order;
	
	public CriteriaFilters(Class classe) {
		criterionsList = new ArrayList<Criterion>();
		this.classe = classe;
	}
	
	public void addCriterion(Criterion criterion){
		criterionsList.add(criterion);
	}

	public ArrayList<Criterion> getCriterionsList() {
		return criterionsList;
	}

	public void setCriterionsList(ArrayList<Criterion> criterionsList) {
		this.criterionsList = criterionsList;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public Map<String, String> getAliases() {
		return aliases;
	}

	public void setAliases(Map<String, String> aliases) {
		this.aliases = aliases;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
