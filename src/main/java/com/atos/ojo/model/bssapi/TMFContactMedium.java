package com.atos.ojo.model.bssapi;

import java.util.List;

public class TMFContactMedium {
	List<TMFMedium> medium;
	String addressRole;

	public List<TMFMedium> getMedium() {
		return medium;
	}

	public void setMedium(List<TMFMedium> medium) {
		this.medium = medium;
	}

	

	public String getAddressRole() {
		return addressRole;
	}

	public void setAddressRole(String addressRole) {
		this.addressRole = addressRole;
	}

	@Override
	public String toString() {
		return "TMFContactMedium [medium=" + medium + ", addressRole=" + addressRole + "]";
	}
}
