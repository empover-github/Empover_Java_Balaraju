package com.javapapers.webservices.rest.briks;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrdersListDTO 
{
	private List<OrderDTO> ordersList;

	public List<OrderDTO> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<OrderDTO> ordersList) {
		this.ordersList = ordersList;
	}
}
