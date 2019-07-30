package com.tts.PulsarDetector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class radioservice {
	
	
	
	private radio_objectrepository radiorepo;
	
	@Autowired
	public radioservice(radio_objectrepository radiorepotoinject) {
		this.radiorepo = radiorepotoinject;
	}
	
	public List<radio_object> get_all_radio_objects() {
		List<radio_object> recordings = (List<radio_object>) radiorepo.findAll();
		return recordings;
	}
	
	
	

}
