package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {

	// Parametri della simulazione
	private int T; // Numero di turisti
	private int G; // Numero di giorni

	// Coda
	private PriorityQueue<Evento> queue;

	// Elementi che servono per simulare
	private Graph<State, DefaultWeightedEdge> grafo;
	private List<Turista> turisti;
	private Map<String, State> states;

	public void init(int T, int G, Graph<State, DefaultWeightedEdge> grafo, String state, Map<String, State> states) {
		this.T = T;
		this.G = G;
		this.grafo = grafo;
		this.queue = new PriorityQueue<Evento>();
		this.turisti = new ArrayList<Turista>();
		this.states = states;

		// Genero la lista di turisti da buttare nello stato giusto
		for (int i = 0; i < T; i++) {
			turisti.add(new Turista(i, ""));
		}

		// Popolo di eventi iniziali
		for (Turista t : turisti) {
			Evento e = new Evento(0, t, state);
			this.queue.add(e);
		}
	}

	

	public void run() {
		Evento e;
		while ((e = queue.poll()) != null) {
			if (e.getGiorno()<G){
				e.getTurista().setCurrent_state(e.getDestinazione());
				Evento nuovo = new Evento(e.getGiorno()+1, e.getTurista(), this.destinazioneCasuale(e.getTurista().getCurrent_state()));
				this.queue.add(nuovo);
			}
			if (e.getGiorno()==G){
				e.getTurista().setCurrent_state(e.getDestinazione());
			}
		}
	}

	private String destinazioneCasuale(String state) {
		List<SortableState> temp = new ArrayList<SortableState>();
		double totflights = 0;
		for (DefaultWeightedEdge df : grafo.outgoingEdgesOf(states.get(state))) {
			temp.add(new SortableState(grafo.getEdgeTarget(df), grafo.getEdgeWeight(df)));
			totflights += grafo.getEdgeWeight(df);
		}
		Random rand = new Random();
		for (;;) {
			SortableState now = temp.get(rand.nextInt(temp.size()));
			if (now.getFlights() / totflights > Math.random() * 100) {
				return now.getState().getState_code();
			}
		}
	}
	public List<Turista> getTuristi() {
		return turisti;
	}

}
