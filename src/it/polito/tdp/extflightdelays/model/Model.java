package it.polito.tdp.extflightdelays.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Map<String, State> stateIdMap;
	private Map<Integer, Flight> flightsIdMap;
	private Graph<State, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> airportsIdMap;
	private Simulatore sim;

	public Model() {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		this.stateIdMap = new HashMap<>();
		this.flightsIdMap = new HashMap<>();
		this.airportsIdMap = new HashMap<>();

		for (String s : dao.loadAllStates()) {
			stateIdMap.put(s, new State(s));
			stateIdMap.get(s).setAirports(dao.loadAllStateAirports(s));
		}
		for (Flight f : dao.loadAllFlights()) {
			this.flightsIdMap.put(f.getId(), f);
		}
		for (Airport a : dao.loadAllAirports()) {
			this.airportsIdMap.put(a.getId(), a);
		}
	}

	public Graph<State, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public void creaGrafo() {
		this.grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, this.stateIdMap.values());

		for (State s1 : grafo.vertexSet()) {
			for (State s2 : grafo.vertexSet()) {
				if (this.controllaArco(s1, s2)) {
					grafo.addEdge(s1, s2);
				}
			}
		}

		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		for (SimpleFlight sf : dao.loadAllDistinctFlights()) {
			for (DefaultWeightedEdge df : grafo.edgeSet()) {
				if (grafo.getEdgeSource(df).getAirports().contains(this.airportsIdMap.get(sf.getSrc_id()))
						&& grafo.getEdgeTarget(df).getAirports().contains(this.airportsIdMap.get(sf.getDest_id()))) {
					grafo.setEdgeWeight(df, grafo.getEdgeWeight(df) + 1);
				}
			}
		}

	}

	public List<State> getStatiOrdinati() {
		List<State> result = new LinkedList<>(this.stateIdMap.values());
		Collections.sort(result);
		return result;
	}

	public String getVelivoli(String state_code) {
		String result = "";
		List<SortableState> temp = new LinkedList<SortableState>();
		for (DefaultWeightedEdge df : grafo.outgoingEdgesOf(this.stateIdMap.get(state_code))) {
			temp.add(new SortableState(grafo.getEdgeTarget(df), grafo.getEdgeWeight(df)));
		}
		Collections.sort(temp);
		for (SortableState s : temp) {
			result += s.getState() + ", Voli: " + s.getFlights() + "\n";
		}

		return result;
	}

	private boolean controllaArco(State s1, State s2) {
		for (Flight f : this.flightsIdMap.values()) {
			if (s1.getAirports().contains(this.airportsIdMap.get(f.getOriginAirportId()))
					&& s2.getAirports().contains(this.airportsIdMap.get(f.getDestinationAirportId()))) {
				return true;
			}
		}
		return false;
	}

	public String simula(int T, int G, String state) {
		String result = "";
		this.sim = new Simulatore();
		sim.init(T, G, grafo, state, this.stateIdMap);
		sim.run();
		for (State s : this.grafo.vertexSet()) {
			int counter = 0;
			for (Turista t : sim.getTuristi()) {
				if (t.getCurrent_state().equals(s.getState_code())) {
					counter++;
				}
			}
			result += s.getState_code()+ " ha alla fine "+counter+" turisti\n";
		}
		return result;
	}

}
