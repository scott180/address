package com.sudy.model;

import java.io.Serializable;

/**
 * 用户
 * @author sudytech
 */
public class User implements Serializable{
	private static final long serialVersionUID = 3066479573264055052L;
	private Integer id;//用户id
	private String name;//用户名称
	private Integer cityid;//关联的城市id
	private String cityName;//城市名称
	private String tel;//手机
	private String email;//电子邮件
	private Integer addressId;//通讯录id
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
