package it.polito.tdp.extflightdelays.model;

public class Evento implements Comparable<Evento>{

	// Mi serve una classe turista che mi dica dove si trova il bastardo, ed un
	// evento che mi sposta il turista verso lo stato
	// giusto nella maniera casuale richiesta, quindi un evento ha un int per il
	// giorno, un turista ed una destinazione dove muovere
	// il turista, c'è un solo tipo di evento quindi non serve SWITCH e non serve
	// ENUM, questa classe deve essere comparable secondo l'id GIORNO

	private int giorno;
	private Turista turista;
	private String destinazione;

	public Evento(int giorno, Turista turista, String destinazione) {
		this.giorno = giorno;
		this.turista = turista;
		this.destinazione = destinazione;
	}

	public int getGiorno() {
		return giorno;
	}

	public Turista getTurista() {
		return turista;
	}

	public String getDestinazione() {
		return destinazione;
	}

	@Override
	public int compareTo(Evento o) {
		return this.giorno-o.getGiorno();
	}

}
