package com.manel.Bean;

public class degre {
	int id ;
	int idCAT ;
	int numbreofCLICK;
	public degre(int id, int idCAT, int numbreofCLICK) {
		super();
		this.id = id;
		this.idCAT = idCAT;
		this.numbreofCLICK = numbreofCLICK;
	}
	public degre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCAT() {
		return idCAT;
	}
	public void setIdCAT(int idCAT) {
		this.idCAT = idCAT;
	}
	public int getNumbreofCLICK() {
		return numbreofCLICK;
	}
	public void setNumbreofCLICK(int numbreofCLICK) {
		this.numbreofCLICK = numbreofCLICK;
	}
	@Override
	public String toString() {
		return "degre [id=" + id + ", idCAT=" + idCAT + ", numbreofCLICK="
				+ numbreofCLICK + ", getId()=" + getId() + ", getIdCAT()="
				+ getIdCAT() + ", getNumbreofCLICK()=" + getNumbreofCLICK()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
