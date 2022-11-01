package it.polito.tdp.itunes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private Graph<Album, DefaultWeightedEdge> grafo;
	
	private ItunesDAO dao;
	
	private Map<Integer, Album> idMap;
	
	public Model() {
		
		this.dao = new ItunesDAO();
		idMap = new HashMap<Integer, Album>();
	}
	
	public String creaGrafo(int n) {
		
		this.dao.riempiMappa(n, idMap);
		
		grafo = new SimpleWeightedGraph<Album, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//Vertici
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		//Archi
		for(Album a1: this.grafo.vertexSet()) {
			for(Album a2: this.grafo.vertexSet()) {
				
				if(!a1.equals(a2)) {
					
				Double peso = a1.getTotPrezzo()-a2.getTotPrezzo();
				
				if(peso != 0.00) {
					System.out.println(a1.toString()+" "+a2.toString()+ " "+peso);
					Graphs.addEdgeWithVertices(this.grafo, a1, a2, Math.abs(peso));
					}
				}
			}
		}
		
		return "Grafo creato!\n"+ "Numero vertici: "+this.grafo.vertexSet().size()+"\n"+
		"Numero archi: "+this.grafo.edgeSet().size()+"\n";
		
	}
	
	public AlbumBilancio getBilancio(Album a) {
		
		List<Album> incidenti = Graphs.neighborListOf(this.grafo, a);
		Double media = 0.00;
		Double somma = 0.00;
		for(Album a1: incidenti) {
			
			somma+= this.grafo.getEdgeWeight(this.grafo.getEdge(a, a1));
		}
		media = somma/incidenti.size();
		
		AlbumBilancio ab = new AlbumBilancio(a, media);
		return ab;
	}
	
	public List<AlbumBilancio> getAdiacenze(Album a){
		
		List<Album> incidenti = Graphs.neighborListOf(this.grafo, a);
		List<AlbumBilancio> result = new LinkedList<AlbumBilancio>();
		
		for(Album a1: incidenti) {
			AlbumBilancio ab = this.getBilancio(a1);
			result.add(ab);
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Album> getAlbums(int n){
		
		List<Album> temp = new LinkedList<Album>(idMap.values());
		Collections.sort(temp);
		return temp;
	}
}
