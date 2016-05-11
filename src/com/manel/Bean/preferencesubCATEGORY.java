package com.manel.Bean;

public class preferencesubCATEGORY {
	public int idsubCAT;
	@Override
	public String toString() {
		return "preferencesubCATEGORY [idsubCAT=" + idsubCAT + ", wording="
				+ wording + ", idCAT=" + idCAT + ", getIdsubCAT()="
				+ getIdsubCAT() + ", getWording()=" + getWording()
				+ ", getIdCAT()=" + getIdCAT() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public int getIdsubCAT() {
		return idsubCAT;
	}
	public void setIdsubCAT(int idsubCAT) {
		this.idsubCAT = idsubCAT;
	}
	public int getWording() {
		return wording;
	}
	public void setWording(int wording) {
		this.wording = wording;
	}
	public int getIdCAT() {
		return idCAT;
	}
	public void setIdCAT(int idCAT) {
		this.idCAT = idCAT;
	}
	public preferencesubCATEGORY() {
		super();
		// TODO Auto-generated constructor stub
	}
	public preferencesubCATEGORY(int idsubCAT, int wording, int idCAT) {
		super();
		this.idsubCAT = idsubCAT;
		this.wording = wording;
		this.idCAT = idCAT;
	}
	//wording c'est libellé
	public int wording;
	public int idCAT ;

}
