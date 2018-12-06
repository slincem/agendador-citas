package co.com.meeting.registrationmeetingsapp.model.entity;

import java.io.Serializable;

public enum Cargo implements Serializable{
	
	ADMINISTRADOR("Administrador"), OTRO("Otro");
	
	private String value;
	
	Cargo(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
