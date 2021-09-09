package it.polito.tdp.yelp.model;

public class Arco {
	
	Business b1;
	Business b2;
	Double peso;
	double mediaB1;
	double mediaB2;
	
	public Arco(Business b1, Business b2, Double peso, double mediaB1, double mediaB2) {
		super();
		this.b1 = b1;
		this.b2 = b2;
		this.peso = peso;
		this.mediaB1 = mediaB1;
		this.mediaB2 = mediaB2;
	}
	
	public Business getB1() {
		return b1;
	}
	public void setB1(Business b1) {
		this.b1 = b1;
	}
	public Business getB2() {
		return b2;
	}
	public void setB2(Business b2) {
		this.b2 = b2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public double getMediaB1() {
		return mediaB1;
	}
	public void setMediaB1(double mediaB1) {
		this.mediaB1 = mediaB1;
	}
	public double getMediaB2() {
		return mediaB2;
	}
	public void setMediaB2(double mediaB2) {
		this.mediaB2 = mediaB2;
	}
	

}
