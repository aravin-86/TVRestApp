package com.bbc.media.tv.programmes.service;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import com.bbc.media.tv.programmes.exception.ValidationException;
import com.bbc.media.tv.programmes.model.Programme;

/**
 * 
 * In-Memory implementation of TV Programmes data store.
 *
 */
public class InMemoryTVProgrammesDataStore implements TVProgrammesDataStore {
	
	private InMemoryTVProgrammesDataStore(){
		
	}
	
	private static TVProgrammesDataStore s_instance=new InMemoryTVProgrammesDataStore();
	
	private Map<String, Programme> programmeMap = new ConcurrentHashMap<String, Programme>();
	
	public static TVProgrammesDataStore getInstance(){
		return s_instance;
	}

	@Override
	public Programme getProgrammeById(String id) {
		return programmeMap.get(id);
	}

	@Override
	public List<Programme> getProgrammesByCategory(String category) {
		List<Programme> pList=new ArrayList<Programme>();
		Iterator<String> keySetIterator=programmeMap.keySet().iterator();
		while(keySetIterator.hasNext()){
			Programme p=programmeMap.get(keySetIterator.next());
			if(p.getCategory().equalsIgnoreCase(category)){
				pList.add(p);
			}
		}
		
		return pList;
	}

	@Override
	public List<Programme> getProgrammesByAvailability(boolean isAvailable) {
		List<Programme> pList=new ArrayList<Programme>();
		Iterator<String> keySetIterator=programmeMap.keySet().iterator();
		while(keySetIterator.hasNext()){
			Programme p=programmeMap.get(keySetIterator.next());
			if(p.isAvailable()==isAvailable){
				pList.add(p);
			}
		}
		
		return pList;
	}

	@Override
	public boolean addProgramme(Programme prog)throws ValidationException {
		Pattern p= Pattern.compile("^[1-9]{1,3}$");
		if(!p.matcher(prog.getId()).matches()){
			throw new ValidationException("Programme Id format is not correct. It should be a number from 1 to 999");
		}
		if(!programmeMap.containsKey(prog.getId())){			 
			 programmeMap.put(prog.getId(), prog);
			 return true;
		}
		
		return false;
	}

	@Override
	public boolean updateProgramme(Programme prog) {
		if(programmeMap.containsKey(prog.getId())){
			programmeMap.put(prog.getId(), prog);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteProgramme(String id) {
		Programme p=programmeMap.remove(id);
		if(p != null){
			return true;
		}
		return false;
	}

}
