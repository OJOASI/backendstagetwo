package com.atos.ojo.model.bssapi;

public class TMFCharacteristic {
	String name;
	String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TMFCharacteristic [name=" + name + ", value=" + value + "]";
	}
}
