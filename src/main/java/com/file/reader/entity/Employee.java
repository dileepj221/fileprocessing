package com.file.reader.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
	private String name;
	private String branch;
	private String date;
	private String country;
	private BigDecimal salaryamt;
	private String bonusid;
	private BigDecimal bonusamt;
	
	
	
	public Employee(String name, String branch, String date, String country, BigDecimal salaryamt, String bonusid,
			BigDecimal bonusamt) {
		super();
		this.name = name;
		this.branch = branch;
		this.date = date;
		this.country = country;
		this.salaryamt = salaryamt;
		this.bonusid = bonusid;
		this.bonusamt = bonusamt;
	}



	public Employee() {
		super();
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBranch() {
		return branch;
	}



	public void setBranch(String branch) {
		this.branch = branch;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public BigDecimal getSalaryamt() {
		return salaryamt;
	}



	public void setSalaryamt(BigDecimal salaryamt) {
		this.salaryamt = salaryamt;
	}



	public String getBonusid() {
		return bonusid;
	}



	public void setBonusid(String bonusid) {
		this.bonusid = bonusid;
	}



	public BigDecimal getBonusamt() {
		return bonusamt;
	}



	public void setBonusamt(BigDecimal bonusamt) {
		this.bonusamt = bonusamt;
	}



	@Override
	public String toString() {
		return "Employee [name=" + name + ", branch=" + branch + ", date=" + date + ", country=" + country
				+ ", salaryamt=" + salaryamt + ", bonusid=" + bonusid + ", bonusamt=" + bonusamt + "]";
	}

}
