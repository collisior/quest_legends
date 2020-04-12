package quest_legends.QuestCharacters;

import quest_legends.Quest;
import quest_legends.GameBoard.CellType;
import quest_legends.Helpers.Color;

public abstract class QuestCharacter implements Color, CellType, DodgeAttack, Damage {
	protected String name;
	protected double hp;
	protected int level;
	protected boolean dodgedAttack = false;
	protected String color = GREEN;
	public int current_row = -1, current_col = -1;
	private int home_lane = 0;
	
	QuestCharacter(String name, int level) {
		setName(name);
		setHp(level * 100);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		if(hp < 0) {
			this.hp = 0;
		}
		else {
			this.hp = hp;
		}
	}

	public void resetHp() {
		this.hp = 100 * this.level;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean hasDodgedAttack() {
		return dodgedAttack;
	}
	
	public void updatePosition(int row, int col) {
		current_row = row;
		current_col = col;
	}

	public void setHasDodgedAttack(boolean dodgedAttack) {
		this.dodgedAttack = dodgedAttack;
	}

	public boolean isAlive() {
		if (getHp() <= 0) {
			return false;
		}
		return true;
	}
	public int getHomeLane() {
		return home_lane;
	}

	public void setHomeLane(int home_lane) {
		this.home_lane = home_lane;
	}
	public String toString() {
		return name ;
	}

	public abstract String displayDetails();

	/*
	 * Return true if Hero/Monster are lucky to dodge an incoming attack.
	 */
	public boolean dodgeAttack() {
		double chance = getDodgeChance();
		if (Quest.random.nextInt(100) <= chance) {
			setHasDodgedAttack(true);
			return true;
		}
		setHasDodgedAttack(false);
		return false;
	}

	public void applyDamage(double damage) {	
		setHp(getHp() - damage);
	}

	protected abstract void upgradeSkills();

	protected abstract void levelUp();
	

	public abstract void information();
	public abstract String image() ;

}
