package com.example.kafkaStreamProducer.util;

public class Handset {
private String id;
private String modelVersion;
private String company;
private String price;

public Handset() {
	
	
}

public Handset(String id, String modelVersion, String company, String price) {
	super();
	this.id = id;
	this.modelVersion = modelVersion;
	this.company = company;
	this.price = price;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getModelVersion() {
	return modelVersion;
}
public void setModelVersion(String modelVersion) {
	this.modelVersion = modelVersion;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}


	
}
