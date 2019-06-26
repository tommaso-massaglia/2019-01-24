package it.polito.tdp.extflightdelays.model;

public class Turista {

	private int id;
	private String current_state;

	public Turista(int id, String current_state) {
		this.id = id;
		this.current_state = current_state;
	}

	public int getId() {
		return id;
	}

	public String getCurrent_state() {
		return current_state;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrent_state(String current_state) {
		this.current_state = current_state;
	}

}
