package com.bbc.media.tv.programmes.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.bbc.media.tv.programmes.TVProgrammeResource;
import com.bbc.media.tv.programmes.exception.ErrorMsgConstant;
import com.bbc.media.tv.programmes.model.Programme;
import com.bbc.media.tv.programmes.model.Programmes;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;

/**
 * 
 * Acceptance criteria based Test class.
 *
 */
public class BBCTVRESTAppTest extends JerseyTest{
	
	public BBCTVRESTAppTest() {
		super(TVProgrammeResource.class.getPackage().getName());
	}
	
	@Test
	public void testAddProgramme(){
		Client client=client();
		//Programme Id Validation
		Programme invalidProgramme=new Programme("a1", "Sherlock Holmes", "A British Television crime drama features Sir Arthur Conan Doyle's Sherlock Holmes detective stories", "entertainment", true);
		ClientResponse errorResponse=client.resource("/programmes/add").accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(ClientResponse.class,invalidProgramme);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), errorResponse.getStatus());
		com.bbc.media.tv.programmes.model.Error e=errorResponse.getEntity(com.bbc.media.tv.programmes.model.Error.class);
		assertEquals(ErrorMsgConstant.INVALID_ID_ERROR_MSG, e.getErrorMessage());
		
		// New programme creation
		Programme p=new Programme("13", "Sherlock Holmes", "A British Television crime drama features Sir Arthur Conan Doyle's Sherlock Holmes detective stories", "entertainment", true);
		ClientResponse response=client.resource("/programmes/add").accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(ClientResponse.class,p);
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		// Adding the existing programme
		ClientResponse notModifiedRes=client.resource("/programmes/add").accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(ClientResponse.class,p);
		assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), notModifiedRes.getStatus());
		
	}
	
	@Test
	public void testGetProgrammeById(){
		Client client=client();
		// Trying to retrieve the programme by id which is not in memory
		ClientResponse notFoundRes=client.resource("/programmes/12").get(ClientResponse.class);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), notFoundRes.getStatus());
		//Creating new programme
		Programme p=new Programme("12", "Big Bang Theory", "A Scientific comedy track", "entertainment", false);
		ClientResponse createResponse=client.resource("/programmes/add").accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(ClientResponse.class,p);
		assertEquals(Response.Status.CREATED.getStatusCode(), createResponse.getStatus());
		// Retrieving the programme by id which is in memory
		ClientResponse getResponse=client.resource("/programmes/12").get(ClientResponse.class);
		assertEquals(Response.Status.OK.getStatusCode(), getResponse.getStatus());
	}
	
	@Test
	public void testGetProgrammeByCategory(){
		Client client=client();
		//Retrieving the programme by category
		ClientResponse getResponse=client.resource("/programmes/category/entertainment").get(ClientResponse.class);
		assertEquals(Response.Status.OK.getStatusCode(), getResponse.getStatus());
		Programmes progs=getResponse.getEntity(Programmes.class);
		List<Programme> mockProgList=new ArrayList<Programme>();
		Programme prog1=new Programme("13", "Sherlock Holmes", "A British Television crime drama features Sir Arthur Conan Doyle's Sherlock Holmes detective stories", "entertainment", true);
		Programme prog2=new Programme("12", "Big Bang Theory", "A Scientific comedy track", "entertainment", false);
		mockProgList.add(prog1);
		mockProgList.add(prog2);
		assertEquals(mockProgList.toString(), progs.getProgrammes().toString());
		assertEquals(2, progs.getProgrammes().size());
		
	}
	
	@Test
	public void testGetProgrammeByAvailability(){
		Client client=client();
		ClientResponse getResponse=client.resource("/programmes/available").get(ClientResponse.class);
		assertEquals(Response.Status.OK.getStatusCode(), getResponse.getStatus());
		Programmes progs=getResponse.getEntity(Programmes.class);
		List<Programme> mockProgList=new ArrayList<Programme>();
		Programme prog1=new Programme("13", "Sherlock Holmes", "A British Television crime drama features Sir Arthur Conan Doyle's Sherlock Holmes detective stories", "entertainment", true);
		mockProgList.add(prog1);
		assertEquals(mockProgList.toString(), progs.getProgrammes().toString());
		assertEquals(1, progs.getProgrammes().size());		
		
	}
	
	
	@Test
	public void testUpdateProgramme(){
		Client client=client();
		// Updating a programme in memory
		Programme p=new Programme("12", "The Big Bang Theory", "A Scientific comedy tracks", "comedy", true);
		ClientResponse getResponse=client.resource("/programmes/update").post(ClientResponse.class, p);
		assertEquals(Response.Status.OK.getStatusCode(), getResponse.getStatus());
		// Updating a programme not in memory
		Programme progNotAvailableForUpdate=new Programme("16", "Prison Break", "Thriller Track", "entertainment", true);
		ClientResponse notModifiedRes=client.resource("/programmes/update").post(ClientResponse.class, progNotAvailableForUpdate);
		assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), notModifiedRes.getStatus());
	}
	
	@Test
	public void testDeleteProgramme(){
		Client client=client();
		// Deleting a programme in memory
		ClientResponse deleteResponse=client.resource("/programmes/12").delete(ClientResponse.class);
		assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());
		// Deleting a programme not in memory
		ClientResponse notModifiedRes=client.resource("/programmes/12").delete(ClientResponse.class);
		assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), notModifiedRes.getStatus());
	}
	
	
}
