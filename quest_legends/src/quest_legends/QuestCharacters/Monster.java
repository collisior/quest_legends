package quest_legends.QuestCharacters;

import quest_legends.Game;
import quest_legends.PersonalStorage;
import quest_legends.Ammunitions.Armor;
import quest_legends.Ammunitions.Spell;

public class Monster extends QuestCharacter {

	private double damage, defense, dodgeChance;
	private double dodgeReducer = 0.8;
	public int id;

	public Monster(String name, int level, double damage, double defense, double dodgeChance) {
		super(name, 1);
		setDamage(damage);
		setDefense(defense);
		setDodgeChance(dodgeChance);
		id = Game.getAvailableId();
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	@Override
	public double getDodgeChance() {
		return dodgeChance * dodgeReducer;
	}

	public void setDodgeChance(double dodgeChance) {
		if(dodgeChance <= 100) {
			this.dodgeChance = dodgeChance;
		}
	}

	/*
	 * Calculates Final Damage this Monster will cause to given Hero. 
	 */
	@Override
	public double damageCalculation(QuestCharacter character) {
		double finalDamage = getDamage();
		Hero hero = (Hero) character;
		if (hero.getCurrentAmmunition() instanceof Armor) {
			Armor armor = (Armor) hero.getCurrentAmmunition();
			if ((getDamage() - armor.getDamageReduction()) > 0) {
				finalDamage = getDamage() - armor.getDamageReduction();
			}
		} else if ((dodgedAttack == false) && (hero.getCurrentAmmunition() instanceof Spell)) {
			Spell spell = (Spell) hero.getCurrentAmmunition();
			finalDamage = getDamage() * spell.skillDeterioration;
			spell.applyExtraDamage(this);
		}
		return finalDamage * 0.4;
	}

	@Override
	protected void upgradeSkills() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void levelUp() {
		// TODO Auto-generated method stub
	}

	public String displayDetails() {
		return "Name:    " + name + "\nHp:      " + hp + "\nLevel:   " + level + "\nDefense: " + defense + "\nDamage:  " + damage;
	}

	@Override
	public void information() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String image() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
