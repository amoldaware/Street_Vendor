package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TBL_KEY_VALUE {
@Id
@Column(name="ID")
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@Column(name="TBL_KEY")
private String TBL_KEY;
@Column(name="TBL_VALUE")
private int TBL_VALUE;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTBL_KEY() {
	return TBL_KEY;
}
public void setTBL_KEY(String tBL_KEY) {
	TBL_KEY = tBL_KEY;
}
public int getTBL_VALUE() {
	return TBL_VALUE;
}
public void setTBL_VALUE(int tBL_VALUE) {
	TBL_VALUE = tBL_VALUE;
}

}
