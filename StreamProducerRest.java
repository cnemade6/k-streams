package com.example.kafkaStreamProducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaStreamProducer.service.StreamPublisher;
import com.example.kafkaStreamProducer.util.Handset;

@RestController
public class StreamProducerRest {

@Autowired
private StreamPublisher publisher;
@GetMapping("/streamNokiaEvent")
	public String publishNokiaModel() {
		Handset s1= new Handset("1","1208","Nokia","2000");
		publisher.publishStreamData("{\"id\":\"1\",\"modelVersion\":\"1208\",\"company\":\"Nokia\",\"price\":\"2000\"}");
		return "Nokia stream event sent successfully";
		
	}

	
	  @GetMapping("/streamMotorolaEvent") public String publishMotorolaModel() {
	  Handset s1= new Handset("1","Moto_S_5_plus","Motorola","12000");
	  publisher.publishStreamData("{\"id\":\"1\",\"modelVersion\":\"5SPlus\",\"company\":\"Motorola\",\"price\":\"12000\"}"); return
	  "motorola stream event sent successfully";
	  
	  }
	 

}
