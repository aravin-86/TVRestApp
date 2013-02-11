package com.bbc.media.tv.programmes.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Programme {
	
	public Programme(){
		
	}

	public Programme(String id, String title, String description,
			String category, boolean isAvailable) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.isAvailable = isAvailable;
	}

	public Programme(String id, String title, String description,
			String category) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
	}

	private String id;
	private String title;
	private String description;
	private String category;
	private boolean isAvailable;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder=new StringBuilder("[");
		strBuilder.append("id="+id);
		strBuilder.append(",title="+title);
		strBuilder.append(",description="+description);
		strBuilder.append(",category="+category);
		strBuilder.append(",isAvailable="+isAvailable);
		strBuilder.append("]");
		return strBuilder.toString();
	}
	
}
