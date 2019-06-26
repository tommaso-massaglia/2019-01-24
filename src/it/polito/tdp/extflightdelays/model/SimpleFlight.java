package it.polito.tdp.extflightdelays.model;

public class SimpleFlight {

	private int src_id;
	private int dest_id;

	public SimpleFlight(int src_id, int dest_id) {
		this.src_id = src_id;
		this.dest_id = dest_id;
	}

	public int getSrc_id() {
		return src_id;
	}

	public void setSrc_id(int src_id) {
		this.src_id = src_id;
	}

	public int getDest_id() {
		return dest_id;
	}

	public void setDest_id(int dest_id) {
		this.dest_id = dest_id;
	}
}
