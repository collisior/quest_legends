package quest_legends.Ammunitions;

import quest_legends.Helpers.Color;
import quest_legends.QuestCharacters.Monster;

public abstract class Ammunition implements Color {
	private String name = null;
	private double cost = 0; 
	private int required_level = 0;	
	
	public Ammunition(String name, double cost, int required_level) {
		this.setName(name);
		this.setCost(cost); 
		this.setRequired_level(required_level);
	}

	public String toString() {
		if (getName() == null) {
			return "Fists";
		}
		return getName();
	}
	public abstract double getDamage();
	
	public abstract void setDamage(double damage);
		
	public abstract void applyExtraDamage(Monster monster);

	public abstract String image();

	public int getRequired_level() {
		return required_level;
	}

	public void setRequired_level(int required_level) {
		this.required_level = required_level;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
