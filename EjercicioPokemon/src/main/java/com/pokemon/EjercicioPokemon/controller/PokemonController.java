package com.pokemon.EjercicioPokemon.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.EjercicioPokemon.models.PokemonModel;
import com.pokemon.EjercicioPokemon.models.StatsModel;
import com.pokemon.EjercicioPokemon.service.PokemonService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/pokedex")
public class PokemonController {
	@Autowired
	PokemonService pokemonService;
	
	@GetMapping()
	public ArrayList<PokemonModel> pokedex(@RequestParam String offset){
		return pokemonService.readAllPoke(offset);
	}
	
	@GetMapping("/pokemon")
	public PokemonModel pokemon(@RequestParam String name){
		return pokemonService.readPoke(name);
	}
	
	@GetMapping("/evolution")
	public ArrayList<PokemonModel> evolution(@RequestParam String name){
		return pokemonService.evolutive(name);
	}
	
	@GetMapping("/filtro")
	public ArrayList<PokemonModel> filtro(@RequestParam String ...tipo){
		return pokemonService.filtrarPorTipo(tipo);
	}
	
	@GetMapping("/movimientos")
	public ArrayList<String> moves(@RequestParam String name){
		return pokemonService.movimientos(name);
	}
	
	@GetMapping("/stats")
	public StatsModel stats(@RequestParam String name) {
		return pokemonService.stats(name);
	}
	
	@GetMapping("/debil")
	public ArrayList<String> debilcontra(@RequestParam String ...tipo){
		return pokemonService.debil(tipo);
	}
}
