package org.servialtura.contabilidad.base.viewObjects;

public class Result implements Comparable<Result>{

	private String userName;
	private int resultat;
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(String userName, int resultat) {
		super();
		this.userName = userName;
		this.resultat = resultat;
	}

	public void increment(){
		resultat ++;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getResultat() {
		return resultat;
	}

	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

	@Override
	public int compareTo(Result o) {
		
		if(this.resultat == o.resultat){
			return 0;
		}
		return this.resultat > o.resultat ?  1 :  -1;
		
	}
	
	
}
