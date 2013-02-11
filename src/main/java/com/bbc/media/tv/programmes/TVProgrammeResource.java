package com.bbc.media.tv.programmes;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import com.bbc.media.tv.programmes.exception.ValidationException;
import com.bbc.media.tv.programmes.model.Error;
import com.bbc.media.tv.programmes.model.Programme;
import com.bbc.media.tv.programmes.model.Programmes;
import com.bbc.media.tv.programmes.service.TVProgrammesDataStore;
import com.bbc.media.tv.programmes.service.delegate.TVProgrammesBusinessDelegate;

@Path("/programmes")
public class TVProgrammeResource {

	private TVProgrammesDataStore inMemPrograms = TVProgrammesBusinessDelegate.instance().getTVProgrammes();

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProgrammesById(@PathParam("id") String id) {
		Programme p = inMemPrograms.getProgrammeById(id);
		if(p == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(p).build();
	}

	@GET
	@Path("/category/{category}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	public Response getProgrammesByCategory(
			@PathParam("category") String category) {
		List<Programme> pList = inMemPrograms.getProgrammesByCategory(category);
		Programmes progs=new Programmes();
		if(pList==null || pList.isEmpty()){
			return Response.status(Status.NOT_FOUND).build();
		}
		progs.setProgrammes(pList);
		return Response.ok(progs).build();
	}

	@GET
	@Path("/available")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getProgrammesByAvailability() {
		List<Programme> pList = inMemPrograms.getProgrammesByAvailability(true);
		Programmes progs=new Programmes();
		if(pList==null || pList.isEmpty()){
			return Response.status(Status.NOT_FOUND).build();
		}
		progs.setProgrammes(pList);
		return Response.ok(progs).build();

	}
	
	@PUT
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addProgramme(JAXBElement<Programme> prog){
		boolean isAdded=false;
		Response res=Response.status(Status.NOT_MODIFIED).build();;
		try{
		isAdded = inMemPrograms.addProgramme(prog.getValue());
		}catch (ValidationException ve) {
			Error e=new Error();
			e.setErrorMessage(ve.getErrMsg());
			return Response.status(Status.BAD_REQUEST).entity(e).build();
			
		}
		if(isAdded){
			res= Response.status(Status.CREATED).build();
		}
		return res; 
	}
	
	@POST
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateProgramme(JAXBElement<Programme> prog){
		boolean isUpdated = inMemPrograms.updateProgramme(prog.getValue());
		if(isUpdated){
			return Response.ok().build();
		}
		return Response.status(Status.NOT_MODIFIED).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteProgramme(@PathParam("id") String id){
		boolean isDeleted=inMemPrograms.deleteProgramme(id);
		if(isDeleted){
			return Response.ok().build();
		}
		return Response.status(Status.NOT_MODIFIED).build();
	}
}
