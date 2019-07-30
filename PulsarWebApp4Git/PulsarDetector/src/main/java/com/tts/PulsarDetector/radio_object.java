package com.tts.PulsarDetector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="radiosources")
public class radio_object {
	
	//Private attributes 
	
	@Id

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int ID;
	
	@Column(name="i1")
	private double i1;
	
	@Column(name="i2")
	private double i2;
	
	@Column(name="i3")
	private double i3;
	
	@Column(name="i4")
	private double i4;
	
	@Column(name="i5")
	private double i5;

	@Column(name="i6")
	private double i6;
	
	@Column(name="i7")
	private double i7;
	
	@Column(name="i8")
	private double i8;
	
	@Column(name="target")
	private int target;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getI1() {
		return i1;
	}

	public void setI1(double i1) {
		this.i1 = i1;
	}

	public double getI2() {
		return i2;
	}

	public void setI2(double i2) {
		this.i2 = i2;
	}

	public double getI3() {
		return i3;
	}

	public void setI3(double i3) {
		this.i3 = i3;
	}

	public double getI4() {
		return i4;
	}

	public void setI4(double i4) {
		this.i4 = i4;
	}

	public double getI5() {
		return i5;
	}

	public void setI5(double i5) {
		this.i5 = i5;
	}

	public double getI6() {
		return i6;
	}

	public void setI6(double i6) {
		this.i6 = i6;
	}

	public double getI7() {
		return i7;
	}

	public void setI7(double i7) {
		this.i7 = i7;
	}

	public double getI8() {
		return i8;
	}

	public void setI8(double i8) {
		this.i8 = i8;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	//CONSTRUCTOR FOR JPA TO FUNCTION PROPERLY (Non-Argument)
	radio_object(){
	}
	
}