package quest_legends.Ammunitions;

import quest_legends.QuestCharacters.Monster;

public class Armor extends Ammunition {
	private double damage_reduction;
	
	public Armor(String name, double cost, int required_level, double damage_reduction) {
		super(name, cost, required_level);
		setDamageReduction(damage_reduction);
	}
	
	public void setDamageReduction(double damage_reduction) {
		this.damage_reduction = damage_reduction;
	}
	
	public double getDamageReduction(){
		return this.damage_reduction;
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDamage(double damage) {
		// TODO Auto-generated method stub
	}

	@Override
	public void applyExtraDamage(Monster monster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String image() {
		String image = 
				"  |`-._/\\_.-`|\n" + 
				"  |    ||    |\n" + 
				"  |___o()o___|\n" + 
				"  |__((<>))__|\n" + 
				"  \\   o\\/o   /\n" + 
				"   \\   ||   /\n" + 
				"    \\  ||  /\n" + 
				"     '.||.'\n";
		return image;
	}
	
}
