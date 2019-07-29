package com.javapapers.webservices.rest.briks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/orders")
public class OrderService 
{
	private static Map<String, OrderDTO> orderMap = new HashMap<String, OrderDTO>();
	
	@GET
	@Path("/createOrder/{qty}")
	@Produces(MediaType.APPLICATION_XML)
	public OrderDTO createOrder(@PathParam("qty") String qty) 
	{
		try
		{
			OrderDTO order = new OrderDTO();
			if(orderMap != null) 
			{
				order.setId((""+orderMap.size() == null || "0".equals(""+orderMap.size())) ? ""+1 : ""+(orderMap.size()+1));
				order.setDate(new Date());
				order.setQty(qty);
				order.setStatus("Order Created");
			}
			orderMap.put(order.getId().trim(), order);
			return order;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@GET
	@Path("/getOrder/{orderNo}")
	@Produces(MediaType.APPLICATION_XML)
	public OrderDTO getOrderDetails(@PathParam("orderNo") String orderNo)
	{
		return orderMap.get(orderNo);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getAllOrders")
	@Produces(MediaType.APPLICATION_XML)
	public OrderDTO getAllOrderDetails()
	{
		OrderDTO ord = new OrderDTO();
		ord.setOrdList(new ArrayList(orderMap.values()));
		return ord;
	}
	
	@GET
	@Path("/update/{orderNo}/{qty}")
	@Produces(MediaType.APPLICATION_XML)
	public OrderDTO updateOrder(@PathParam("orderNo") String orderNo,@PathParam("qty") String qty) 
	{
		try
		{
			OrderDTO  order = orderMap.get(orderNo);
			if(order!=null && !order.getStatus().equals("Order Created"))
			{
				order.setRespStatus("0");
			}
			else
			{
				order.setQty(qty);
				order.setUpdateOn(new Date());
				orderMap.put(order.getId().trim(), order);
			}
			return order;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	@GET
	@Path("/issued/{refNo}")
	@Produces(MediaType.APPLICATION_XML)
	public OrderDTO issueOrder(@PathParam("refNo") String refNo) 
	{
		try
		{
			OrderDTO  order = orderMap.get(refNo);
			if(order!=null && order.getStatus().equals("Dispatched"))
			{
				order.setStatus("Order alraedy Dispatched.");
			}
			else
			{
				order.setStatus("Dispatched");
				order.setUpdateOn(new Date());
				orderMap.put(order.getId().trim(), order);
			}
			return order;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
}