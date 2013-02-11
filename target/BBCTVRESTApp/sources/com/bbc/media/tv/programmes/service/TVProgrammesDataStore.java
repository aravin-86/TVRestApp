package com.bbc.media.tv.programmes.service;

import java.util.List;

import com.bbc.media.tv.programmes.exception.ValidationException;
import com.bbc.media.tv.programmes.model.Programme;

public interface TVProgrammesDataStore {
	
	Programme getProgrammeById(String id);
	List<Programme> getProgrammesByCategory(String category);
	List<Programme> getProgrammesByAvailability(boolean isAvailable);
	boolean addProgramme(Programme prog)throws ValidationException;
	boolean updateProgramme(Programme prog);
	boolean deleteProgramme(String id);
	

}
