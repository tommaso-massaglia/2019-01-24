package it.polito.tdp.extflightdelays.model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		String state = "CA";

		Model model = new Model();
		model.creaGrafo();
		for (DefaultWeightedEdge df : model.getGrafo().edgeSet()) {
			System.out.println("Source: " + model.getGrafo().getEdgeSource(df) + ", Target: "
					+ model.getGrafo().getEdgeTarget(df) + ", Peso: " + model.getGrafo().getEdgeWeight(df));
			;
		}
		System.out.println(model.getStatiOrdinati());
		System.out.println(model.getVelivoli(state));
		System.out.println(model.simula(100, 20, state));
	}

}
