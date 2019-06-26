package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;

public class State implements Comparable<State> {

	private List<Airport> airports;
	private String state_code;

	public State(String state_code) {
		this.airports = new ArrayList<>();
		this.state_code = state_code;
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports.addAll(airports);
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state_code == null) ? 0 : state_code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (state_code == null) {
			if (other.state_code != null)
				return false;
		} else if (!state_code.equals(other.state_code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return state_code;
	}

	@Override
	public int compareTo(State arg0) {
		return this.state_code.compareTo(arg0.getState_code());
	}

}
