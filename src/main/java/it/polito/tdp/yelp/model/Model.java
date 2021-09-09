package it.polito.tdp.yelp.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	YelpDao dao = new YelpDao();
	SimpleDirectedWeightedGraph<Business, DefaultWeightedEdge> grafo = new SimpleDirectedWeightedGraph<Business, DefaultWeightedEdge>(DefaultWeightedEdge.class);

	public Collection<String> citta() {
		return this.dao.citta();
	}

	public String creaGrafo(String city, int anno) {
		System.out.println("ciao");
		Graphs.addAllVertices(this.grafo, this.dao.businessVertici(city, anno));
		System.out.println("vertici");
		for(Business b : this.grafo.vertexSet()) {
			b.setMediaRecensioni(this.dao.getMedia(b.getBusinessId(), anno));
		}
		for(Business b1 : this.grafo.vertexSet()) {
			for(Business b2 : this.grafo.vertexSet()) {
				if(b1!=b2 && !this.grafo.containsEdge(b1, b2) && !this.grafo.containsEdge(b2, b1) && b1.getMediaRecensioni()!=b2.getMediaRecensioni()) {
					if(b1.getMediaRecensioni()>b2.getMediaRecensioni()) {
						Graphs.addEdge(this.grafo, b2, b1, b1.getMediaRecensioni()-b2.getMediaRecensioni());
					}
					else if(b2.getMediaRecensioni()>b1.getMediaRecensioni()) {
						Graphs.addEdge(this.grafo, b1, b2, b2.getMediaRecensioni()-b1.getMediaRecensioni());
					}
				}
			}
		}
		String s = "\n";
		s=s+"Grafo creato con "+this.grafo.vertexSet().size()+" vertici e "+this.grafo.edgeSet().size()+" archi."+"\n";
		return s;
	}

	public String trovaLocaleMigliore() {
		List<Business> locali = new LinkedList<Business>(this.grafo.vertexSet());
		double max = 0;
		Business migliore = null;
		for(Business b : locali) {
			double somma = 0;
			for(DefaultWeightedEdge eEntrante : this.grafo.incomingEdgesOf(b))
				somma = somma + this.grafo.getEdgeWeight(eEntrante);
			for(DefaultWeightedEdge eUscente : this.grafo.outgoingEdgesOf(b))
				somma = somma - this.grafo.getEdgeWeight(eUscente);
			if(somma>max) {
				max = somma;
				migliore = b;
			}
		}
		String s="\n";
		s=s+"Il locale migliore è: "+migliore+" con "+max+"\n";
		return s;
		
	}
	
	
}
