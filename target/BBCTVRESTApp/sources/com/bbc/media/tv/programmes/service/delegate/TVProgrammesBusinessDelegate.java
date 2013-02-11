package com.bbc.media.tv.programmes.service.delegate;

import com.bbc.media.tv.programmes.service.InMemoryTVProgrammesDataStore;
import com.bbc.media.tv.programmes.service.TVProgrammesDataStore;

public class TVProgrammesBusinessDelegate {
	
	private TVProgrammesBusinessDelegate(){
		
	}
	
	private static TVProgrammesBusinessDelegate s_instance=new TVProgrammesBusinessDelegate();
	
	private TVProgrammesDataStore inMemPrograms = InMemoryTVProgrammesDataStore.getInstance();
	
	public static TVProgrammesBusinessDelegate instance(){
		return s_instance;
	}
	public TVProgrammesDataStore getTVProgrammes(){
		return inMemPrograms;
	}

}
