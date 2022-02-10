package com.pokemon.EjercicioPokemon.models;

public class StatsModel {
	private Integer hp;
	private Integer attack;
	private Integer defense;
	private Integer special_attack;
	private Integer special_defense;
	private Integer speed;	
		
	public StatsModel(Integer hp, Integer attack, Integer defense, Integer special_attack, Integer special_defense,
			Integer speed) {
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.special_attack = special_attack;
		this.special_defense = special_defense;
		this.speed = speed;
	}
	
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public Integer getAttack() {
		return attack;
	}
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	public Integer getDefense() {
		return defense;
	}
	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	public Integer getSpecial_attack() {
		return special_attack;
	}
	public void setSpecial_attack(Integer special_attack) {
		this.special_attack = special_attack;
	}
	public Integer getSpecial_defense() {
		return special_defense;
	}
	public void setSpecial_defense(Integer special_defense) {
		this.special_defense = special_defense;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	
}
