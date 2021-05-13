package com.kiraly93.wobdemo.Model;

public enum MarketplaceEnum {
	
	EBAY(1, "Ebay"),
	Amazon(2, "Amazon");
	
	private int id;
	private String name;

	MarketplaceEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static MarketplaceEnum getById(int id) {
	    for(MarketplaceEnum e : values()) {
	        if(e.id == id) {
	        	return e;
	        }
	    }
	    return null;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	

}
