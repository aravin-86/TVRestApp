package com.bbc.media.tv.programmes.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Programmes {
	
	private List<Programme> programmes=new ArrayList<Programme>();

	public List<Programme> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(List<Programme> programmes) {
		this.programmes = programmes;
	}

	
	
	

}
