package com.tts.PulsarDetector;

import lombok.Data;

@Data
public class Request {
	
    private int layers;


	public int getthickness() {
		return this.layers;
	}

	public void setthickness(int layers) {
		this.layers = layers;
	}
}
