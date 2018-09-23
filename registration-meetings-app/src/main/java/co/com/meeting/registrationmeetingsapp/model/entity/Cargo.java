package co.com.meeting.registrationmeetingsapp.model.entity;

import java.io.Serializable;

public enum Cargo implements Serializable{
	
	ADMINISTRADOR("Administrador"), OTRO("Otro");
	
	private String value;
	
	private Cargo(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
