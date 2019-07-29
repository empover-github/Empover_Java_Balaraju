package com.javapapers.webservices.rest.briks;

import java.net.URI;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;


public class OrderClient {

	private static final String webServiceURI = "http://localhost:8080/webJersey";

	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		/*ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
		WebTarget webTarget = client.target(serviceURI);*/

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Enter  1: Create, 2: View , 3: View All, 4: Update Order, 5: Fulfill");
		String option = scanner.nextLine();
		getResultsFromService(option);
	}
	
	public static void getResultsFromService(String option)
	{
		try
		{
			ClientConfig clientConfig = new ClientConfig();
			Client client = ClientBuilder.newClient(clientConfig);
			URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
			WebTarget webTarget = client.target(serviceURI).path("rest").path("orders");
			Scanner scanner = new Scanner(System.in);
			if(option != null && !option.equals("")) 
			{
				// Order Creation
				if("1".equals(option))
				{
					System.out.println("Please Enter Briks Quantity");
					String quantity = scanner.nextLine();
					try {
						   	int qty = Integer.parseInt(quantity); 
						   	if(qty>0)
						   	{
								webTarget = webTarget.path("createOrder").path(qty+"");
								Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
								Response response = invocationBuilder.get();
								OrderDTO order = response.readEntity(OrderDTO.class);
								System.out.println(response.getStatus());
								System.out.println("Order Created Succesfully. \n Order Ref. No:"+order.getId()+" \n Ordered Quantity :"+order.getQty());
								System.out.println("");
						   	}
						   	else
						   	{
						   	   System.out.println("Please Enter Positive Numbers only"); 
							   getResultsFromService(option);
						   	}
					 }
					catch(NumberFormatException e)
					{
					   System.out.println("Please Enter Valid input"); 
					   getResultsFromService(option);
					} 
				}
				//View Order Status
				else if("2".equals(option))
				{
					System.out.println("Please Enter Order Ref. No");
					String refNo = scanner.nextLine();
					
					webTarget = webTarget.path("getOrder").path(refNo);
					Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
					Response response = invocationBuilder.get();
					OrderDTO order = response.readEntity(OrderDTO.class);
					System.out.println(response.getStatus());
					System.out.println("Order No :"+order.getId() +"\n Qty:"+order.getQty()+" \n Created On:"+order.getDate()+ "\n Status:"+order.getStatus());
				}
				//View All Orders
				else if("3".equals(option))
				{
					webTarget = webTarget.path("getAllOrders");
					Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
					Response response = invocationBuilder.get();
					OrderDTO order = response.readEntity(OrderDTO.class);
					List<OrderDTO> ordersList = order.getOrdList();
					System.out.println(response.getStatus());
					System.out.println("Order No \t Qty \t Status \t Date \n");
					System.out.println("-------- \t ---- \t -----\t\t ------\n");
					for(OrderDTO ord:ordersList)
					{
						System.out.println(ord.getId()+"\t\t "+ord.getQty()+"\t"+ord.getStatus()+"\t"+ord.getDate());
					}
				}
				//Update Order
				else if("4".equals(option))
				{
					System.out.println("Please Enter Order Ref. No");
					String refNo = scanner.nextLine();
					System.out.println("Please Enter Quantity");
					String quantity = scanner.nextLine();
					try 
					{
						int qty = Integer.parseInt(quantity); 
					   	if(qty>0)
					   	{
							webTarget = webTarget.path("update").path(refNo).path(quantity);
							Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
							Response response = invocationBuilder.get();
							OrderDTO order = response.readEntity(OrderDTO.class);
							if("0".equals(order.getRespStatus()))
								System.out.println("Can not Update Quantity. Order Already Dispatched");
							else
								System.out.println("Order No:"+order.getId()+"\n Qty :"+order.getQty() +"\n Updated Successfully");
					   	}
					   	else
					   	{
					   	   System.out.println("Please Enter Positive Numbers only"); 
						   getResultsFromService(option);
					   	}
					 }
					catch(NumberFormatException e)
					{
					   System.out.println("Please Enter Valid input"); 
					   getResultsFromService(option);
					} 
				}
				//Fulfill Order
				else if("5".equals(option))
				{
					System.out.println("Please Enter Order Ref. No");
					String refNo = scanner.nextLine();
					webTarget = webTarget.path("issued").path(refNo);
					Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
					Response response = invocationBuilder.get();
					OrderDTO order = response.readEntity(OrderDTO.class);
					System.out.println("Order  "+order.getId()+ "\n Qty :"+order.getQty()+"\n Dispatched Successfully");
				}
				else
				{
					System.out.println("Please Enter Valid Option");
				}
			}
				System.out.println("\nPlease Enter  1: Create, 2: View , 3: View All, 4: Update Order, 5: Fulfill, 6: Exit");
				option = scanner.nextLine();
			  
			  if(option != null && !option.equals("6") ) {
				  getResultsFromService(option);
			  }
			  else
			  {
				  System.out.println("\n Actions Performed successfully."); 
			  }
		}
		catch(Exception e)
		{
			
		}
	}
	
	
}
