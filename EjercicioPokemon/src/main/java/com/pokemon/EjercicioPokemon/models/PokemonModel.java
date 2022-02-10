package com.pokemon.EjercicioPokemon.models;

import java.util.ArrayList;

public class PokemonModel {
	private Integer id;
	private String nombre;
	private Float peso;
	private Float altura;
	private String imagen;
	private ArrayList<String> tipos;
	
	public PokemonModel(Integer id, String nombre, Float peso, Float altura, String imagen, ArrayList<String> tipos) {		
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.imagen = imagen;
		this.tipos = tipos;
	}
	
	public PokemonModel(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Float getAltura() {
		return altura;
	}
	public void setAltura(Float altura) {
		this.altura = altura;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getTipos() {
		return tipos;
	}

	public void setTipos(ArrayList<String> tipos) {
		this.tipos = tipos;
	}
	
}
