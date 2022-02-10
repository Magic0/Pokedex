package com.pokemon.EjercicioPokemon.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemon.EjercicioPokemon.models.PokemonModel;
import com.pokemon.EjercicioPokemon.models.StatsModel;

@Service
public class PokemonService {
	final String url = "https://pokeapi.co/api/v2/pokemon/";
	final String url2 = "https://pokeapi.co/api/v2/pokemon-species/";
	final String url3 = "https://pokeapi.co/api/v2/type/";
	
	public ArrayList<PokemonModel> readAllPoke(String offset){
		ArrayList<PokemonModel> pokemon = new ArrayList<PokemonModel>();
		String url = this.url + "?offset={offs}&limit=20";
		
		RestTemplate restTemplate = new RestTemplate();
		//me comunico con la api donde me lista todos los pokemon y me devuelve un json con la lista(array) de todos los pokemon(nombre y url) y otros datos
		JSONObject  results =  new JSONObject(restTemplate.getForObject(url, String.class,offset));
		//me devuelve el array de json de los pokemon
		JSONArray arreglo = results.getJSONArray("results");
		for(int i = 0; i<arreglo.length();i++) {
			JSONObject pokem = arreglo.getJSONObject(i);
			//entro o me comunico con la api donde detalla al pokemon y me devuelve json con todo los detalles del pokemon
			JSONObject result = new JSONObject(restTemplate.getForObject(pokem.getString("url"), String.class));
			Integer id = result.getInt("id");
			// me devuelve un json con todos los sprite que tiene el pokemon
			JSONObject sprites = result.getJSONObject("sprites");
			String urlimg;
			if(sprites.isNull("front_default")) {
				urlimg = "";
			}else {
				//obtengo la url de la imagen del pokemon
				urlimg = sprites.getString("front_default");
			}
			PokemonModel pok = new PokemonModel(pokem.getString("name"));
			pok.setId(id);
			pok.setImagen(urlimg);
			pokemon.add(pok);
		}
		
		return pokemon;
		
	}
	
	public PokemonModel readPoke(String name) {
		PokemonModel pokemon= null;
		ArrayList<String> tiposs= new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
		//me comunico con la api donde detalla un pokemon y obtengo el json con todos los detalles del pokemon
		JSONObject  result =  new JSONObject(restTemplate.getForObject(url+name, String.class));
		Integer id = result.getInt("id");
		String nombre = result.getString("name");
		JSONObject sprites = result.getJSONObject("sprites");
		String urlimg;
		if(sprites.isNull("front_default")) {
			urlimg = "";
		}else {
			urlimg = sprites.getString("front_default");
		}
		Double peso  = result.getFloat("weight")*0.1;
		Double altura = result.getFloat("height")*0.1;
		JSONArray tipos = result.getJSONArray("types");	
		for(int i = 0;i<tipos.length();i++) {
			JSONObject types = tipos.getJSONObject(i);
			JSONObject type = types.getJSONObject("type");
			String typename = type.getString("name");
			tiposs.add(typename);
		}
		pokemon = new PokemonModel(nombre);
		pokemon.setId(id);
		pokemon.setImagen(urlimg);
		pokemon.setAltura(altura.floatValue());
		pokemon.setPeso(peso.floatValue());
		pokemon.setTipos(tiposs);
		return pokemon;
	}
	
	public ArrayList<PokemonModel> evolutive(String name){
		ArrayList<PokemonModel> pokemon = new ArrayList<PokemonModel>();
		PokemonModel poke;
		
		RestTemplate restTemplate = new RestTemplate();
		//me comunico con la api de pokemon-species la cual es la unica que dentro de su json tiene un atributo de cadena evolutiva
		//y obtengo ese json
		JSONObject result =  new JSONObject(restTemplate.getForObject(url2+name, String.class));
		JSONObject evolution = result.getJSONObject("evolution_chain");
		String urlevo = evolution.getString("url");
		//me comunico con la api donde esta la cadena evolutiva del pokemon y obtengo el json 
		JSONObject resultevo =  new JSONObject(restTemplate.getForObject(urlevo, String.class));
		JSONObject chainevo = resultevo.getJSONObject("chain");
		JSONObject species = chainevo.getJSONObject("species");
		//obtengo el nombre del pokemon
		String nombre = species.getString("name");
		//entro a la api y obtengo los datos detallados del pokemon
		JSONObject pokemondata = new JSONObject(restTemplate.getForObject(url+nombre, String.class));
		Integer id = pokemondata.getInt("id");
		// me devuelve un json con todos los sprite que tiene el pokemon
		JSONObject sprites = pokemondata.getJSONObject("sprites");
		String urlimg;
		if(sprites.isNull("front_default")) {
			urlimg = "";
		}else {
			urlimg = sprites.getString("front_default");
		}
		
		poke = new PokemonModel(nombre);
		poke.setId(id);
		poke.setImagen(urlimg);
		pokemon.add(poke);
		
		JSONArray a = chainevo.getJSONArray("evolves_to");
		while(!a.isNull(0)) {
			JSONObject b = a.getJSONObject(0);
			species = b.getJSONObject("species");			
			//obtengo el nombre del pokemon
			nombre = species.getString("name");
			//entro a la api y obtengo los datos detallados del pokemon
			pokemondata = new JSONObject(restTemplate.getForObject(url+nombre, String.class));
			id = pokemondata.getInt("id");
			// me devuelve un json con todos los sprite que tiene el pokemon
			sprites = pokemondata.getJSONObject("sprites");
			
			if(sprites.isNull("front_default")) {
				urlimg = "";
			}else {
				urlimg = sprites.getString("front_default");
			}
			
			poke = new PokemonModel(nombre);
			poke.setId(id);
			poke.setImagen(urlimg);
			pokemon.add(poke);
			
			a = b.getJSONArray("evolves_to");
		}
		return pokemon;
	}
	
	public ArrayList<PokemonModel> filtrarPorTipo(String ...tipo){
		ArrayList<PokemonModel> pokemon = new ArrayList<PokemonModel>();
		PokemonModel poke;
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result =  new JSONObject(restTemplate.getForObject(url3+tipo[0], String.class));
		JSONArray pokemonlist= result.getJSONArray("pokemon");
		
		if(tipo.length==2) {
			for(int i = 0; i<pokemonlist.length();i++) {
				JSONObject pokem = pokemonlist.getJSONObject(i);
				pokem = pokem.getJSONObject("pokemon");
				String urlpoke = pokem.getString("url");
				JSONObject pokemondata =  new JSONObject(restTemplate.getForObject(urlpoke, String.class));
				JSONArray tipos = pokemondata.getJSONArray("types");
				
				for(int j = 0;j<tipos.length();j++) {
					JSONObject types = tipos.getJSONObject(j);
					JSONObject type = types.getJSONObject("type");
					String typename = type.getString("name");
					if(typename.compareTo(tipo[1])==0) {
						Integer id = pokemondata.getInt("id");
						JSONObject sprites = pokemondata.getJSONObject("sprites");
						String urlimg;
						if(sprites.isNull("front_default")) {
							urlimg = "";
						}else {
							urlimg = sprites.getString("front_default");
						}
						String nombre = pokem.getString("name");
						poke = new PokemonModel(nombre);
						poke.setId(id);
						poke.setImagen(urlimg);
						pokemon.add(poke);
					}
				}
			}
		}else {
			for(int i = 0; i<pokemonlist.length();i++) {
				JSONObject pokem = pokemonlist.getJSONObject(i);
				pokem = pokem.getJSONObject("pokemon");
				String urlpoke = pokem.getString("url");
				JSONObject pokemondata =  new JSONObject(restTemplate.getForObject(urlpoke, String.class));				
				Integer id = pokemondata.getInt("id");
				JSONObject sprites = pokemondata.getJSONObject("sprites");
				String urlimg;
				if(sprites.isNull("front_default")) {
					urlimg = "";
				}else {
					urlimg = sprites.getString("front_default");
				}
				String nombre = pokem.getString("name");
				poke = new PokemonModel(nombre);
				poke.setId(id);
				poke.setImagen(urlimg);
				pokemon.add(poke);
			}
		}
		
		return pokemon;
	}
	
	public ArrayList<String> movimientos(String name){
		ArrayList<String> movimientos = new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result =  new JSONObject(restTemplate.getForObject(url+name, String.class));
		JSONArray moves = result.getJSONArray("moves");
		for(int i = 0; i<moves.length();i++) {
			JSONObject move = moves.getJSONObject(i);
			move = move.getJSONObject("move");
			String moveName = move.getString("name");
			movimientos.add(moveName);
		}
		return movimientos;
	}
	
	public StatsModel stats(String name) {
		StatsModel stat = null;
		Integer hp, attack, defense,special_attack, special_defense, speed;
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result =  new JSONObject(restTemplate.getForObject(url+name, String.class));
		JSONArray stats = result.getJSONArray("stats");
		hp = stats.getJSONObject(0).getInt("base_stat");
		attack = stats.getJSONObject(1).getInt("base_stat");
		defense = stats.getJSONObject(2).getInt("base_stat");
		special_attack = stats.getJSONObject(3).getInt("base_stat");
		special_defense = stats.getJSONObject(4).getInt("base_stat");
		speed = stats.getJSONObject(5).getInt("base_stat");
		stat = new StatsModel(hp, attack, defense, special_attack, special_defense, speed);
		System.out.println(stats);
		return stat;
	}
	
	public ArrayList<String> debil(String ...tipo){
		ArrayList<String> debil = new ArrayList<String>();
		ArrayList<String> fuerte = new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result =  new JSONObject(restTemplate.getForObject(url3+tipo[0], String.class));
		JSONObject dmg_relacion= result.getJSONObject("damage_relations");
		JSONArray debilidades = dmg_relacion.getJSONArray("double_damage_from");
		for(int i = 0; i < debilidades.length();i++) {
			JSONObject debilid = debilidades.getJSONObject(i);
			String debilidad = debilid.getString("name");
			debil.add(debilidad);
		}
		if(tipo.length==2) {
			/*debilidades = dmg_relacion.getJSONArray("half_damage_to");
			for(int i = 0; i < debilidades.length();i++) {
				JSONObject debilid = debilidades.getJSONObject(i);
				String debilidad = debilid.getString("name");
				if(!debil.contains(debilidad)) {
					debil.add(debilidad);
				}
			}*/
			JSONArray fortaleza = dmg_relacion.getJSONArray("double_damage_to");
			for(int i = 0; i < fortaleza.length();i++) {
				JSONObject forte = fortaleza.getJSONObject(i);
				String forta = forte.getString("name");
				fuerte.add(forta);
			}
			fortaleza = dmg_relacion.getJSONArray("half_damage_from");
			for(int i = 0; i < fortaleza.length();i++) {
				JSONObject forte = fortaleza.getJSONObject(i);
				String forta = forte.getString("name");
				if(!fuerte.contains(forta)) {
					fuerte.add(forta);
				}
			}
			fortaleza = dmg_relacion.getJSONArray("no_damage_from");
			for(int i = 0; i < fortaleza.length();i++) {
				JSONObject forte = fortaleza.getJSONObject(i);
				String forta = forte.getString("name");
				if(!fuerte.contains(forta)) {
					fuerte.add(forta);
				}
			}
			
			result =  new JSONObject(restTemplate.getForObject(url3+tipo[1], String.class));
			dmg_relacion= result.getJSONObject("damage_relations");
			debilidades = dmg_relacion.getJSONArray("double_damage_from");
			for(int i = 0; i < debilidades.length();i++) {
				JSONObject debilid = debilidades.getJSONObject(i);
				String debilidad = debilid.getString("name");
				if(!debil.contains(debilidad)) {
					debil.add(debilidad);
				}
			}
			
			/*debilidades = dmg_relacion.getJSONArray("half_damage_to");
			for(int i = 0; i < debilidades.length();i++) {
				JSONObject debilid = debilidades.getJSONObject(i);
				String debilidad = debilid.getString("name");
				if(!debil.contains(debilidad)) {
					debil.add(debilidad);
				}
			}*/
			fortaleza = dmg_relacion.getJSONArray("double_damage_to");
			for(int i = 0; i < fortaleza.length();i++) {
				JSONObject forte = fortaleza.getJSONObject(i);
				String forta = forte.getString("name");
				if(!fuerte.contains(forta)) {
					fuerte.add(forta);
				}
			}
			fortaleza = dmg_relacion.getJSONArray("half_damage_from");
			for(int i = 0; i < fortaleza.length();i++) {
				JSONObject forte = fortaleza.getJSONObject(i);
				String forta = forte.getString("name");
				if(!fuerte.contains(forta)) {
					fuerte.add(forta);
				}
			}
			fortaleza = dmg_relacion.getJSONArray("no_damage_from");
			for(int i = 0; i < fortaleza.length();i++) {
				JSONObject forte = fortaleza.getJSONObject(i);
				String forta = forte.getString("name");
				if(!fuerte.contains(forta)) {
					fuerte.add(forta);
				}
			}
			for (int i = 0;i<fuerte.size();i++) {
				debil.remove(fuerte.get(i));
			}
		}
		return debil;
	}
	
}
