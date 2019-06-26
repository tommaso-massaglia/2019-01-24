package it.polito.tdp.extflightdelays.model;

public class SortableState implements Comparable<SortableState> {

	private State state;
	private double flights;

	public SortableState(State state, double d) {
		this.state = state;
		this.flights = d;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public double getFlights() {
		return flights;
	}

	public void setFlights(int flights) {
		this.flights = flights;
	}

	@Override
	public int compareTo(SortableState o) {
		return (int)(o.flights-this.flights);
	}

}
