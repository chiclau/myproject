package com.lyht.business.analysisCalculation.formbean;

import java.io.Serializable;
import java.util.Hashtable;

public class DataTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Hashtable<Object, Object> resultTable = new Hashtable<Object, Object>();
	
	public void put(Object key,Object value){
		resultTable.put(key, value);
	}
	public Object get(Object key){
		return resultTable.get(key);
	}
	public Hashtable<Object, Object> getResultTable() {
		return resultTable;
	}
	public void setResultTable(Hashtable<Object, Object> resultTable) {
		this.resultTable = resultTable;
	}
}
