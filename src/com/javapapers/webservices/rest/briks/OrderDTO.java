package com.javapapers.webservices.rest.briks;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderDTO
{
	private String id;
	private String qty;
	private Date date;
	private Date updateOn;
	private String status;
	private String respStatus;
	private List<OrderDTO> ordList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getUpdateOn() {
		return updateOn;
	}
	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderDTO> getOrdList() {
		return ordList;
	}
	public void setOrdList(List<OrderDTO> ordList) {
		this.ordList = ordList;
	}
	public String getRespStatus() {
		return respStatus;
	}
	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}
}
