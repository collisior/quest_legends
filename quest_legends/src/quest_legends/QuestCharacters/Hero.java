package quest_legends.QuestCharacters;

import java.util.ArrayList;

import quest_legends.Fight;
import quest_legends.PersonalStorage;
import quest_legends.Player;
import quest_legends.Quest;
import quest_legends.Ammunitions.Ammunition;
import quest_legends.Ammunitions.Armor;
import quest_legends.Ammunitions.Fists;
import quest_legends.Ammunitions.Potion;
import quest_legends.Ammunitions.Spell;
import quest_legends.Ammunitions.Weapon;
import quest_legends.GameBoard.CellType;
import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.InputHandler;

public class Hero extends QuestCharacter {

	private double strength, dexterity, agility, mana, experience, money;
	private PersonalStorage storage;
	private double dodgeProbability = 0.02;
	private Ammunition regularAttack = new Fists();
	private Ammunition currentAmmunition = null;
	private int fightsWon = 0;
	private ArrayList<Monster> totalDefeatedMonsters = new ArrayList<Monster>();
	private ArrayList<Monster> defeatedMonstersFight = new ArrayList<Monster>();
	
	Hero(String name, double mana, double strength, double agility, double dexterity, double money, double experience) {
		super(name, 1); // default starting level = 1
		setMana(mana);
		setStrength(strength);
		setAgility(agility);
		setDexterity(dexterity);
		setMoney(money);
		setExperience(experience);
		setCurrentAmmunition(regularAttack);
		storage = new PersonalStorage();
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public double getStrength() {
		return this.strength;
	}

	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}

	public double getDexterity() {
		return this.dexterity;
	}

	public void setAgility(double agility) {
		if (agility <= 5000) {
			this.agility = agility;
		}
	}

	public double getAgility() {
		return this.agility;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMana() {
		return mana;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public double getDodgeChance() {
		return getAgility() * dodgeProbability;
	}

	public Ammunition getCurrentAmmunition() {
		return currentAmmunition;
	}

	public void setCurrentAmmunition(Ammunition item) {
		this.currentAmmunition = item;
	}



	/*
	 * During a round of the fight, when it is the turn of the heroes, the player
	 * chooses for each hero whether they will do a regular attack or if they will
	 * cast a spell or if they will use a potion or if they will change their
	 * armor/weapon.
	 */
	public void chooseCurrentAmmunition() {
		Ammunition item = regularAttack; // default regular attack
		boolean choiceAccepted = false;
		do {
			System.out.println(
					"Choose an attack: \n [0]: Regular attack (fists). \n [1]: Use different ammunition from my storage. \n [2]: Get random ammunition from my storage.");
			int choice = InputHandler.getInteger(0, 2);

			switch (choice) {
			case 0:
				choiceAccepted = true;
				setCurrentAmmunition(regularAttack);
				break;
			case 1:
				if (getStorage().size() > 0) {
					item = getStorage().chooseFromStorage(this);
					if (item != null) {
						choiceAccepted = true;
						break;
					}
				} else {
					System.out.println("Your storage is empty!");
				}

			case 2:
				if (getStorage().size() > 0) {
					item = getStorage().getRandomAmmunition(this);
					if (item != null) {
						choiceAccepted = true;
						break;
					}
				} else {
					System.out.println("Your storage is empty! Can't choose random ammunnition.");
				}

			default:
				System.out.println("Invalid choice");
				break;
			}
		} while (!choiceAccepted);

		setCurrentAmmunition(item);
	}

	@Override
	public double damageCalculation(QuestCharacter monster) {
		double damage = getStrength() * 0.05;
		if (currentAmmunition instanceof Fists) {
			damage = getStrength() * 0.05;
		} else if (currentAmmunition instanceof Weapon) {
			damage = (this.strength + currentAmmunition.getDamage()) * 0.05;
		} else if (currentAmmunition instanceof Spell) {
			setMana(getMana() - ((Spell) currentAmmunition).getManaCost());
			damage = currentAmmunition.getDamage() + this.dexterity / 10000 * currentAmmunition.getDamage();
			// if monster don't dodge, do applyExtraDamage(monster)
		} else if (currentAmmunition instanceof Potion) {
			Potion potion = (Potion) currentAmmunition;
			potion.usePotion(this);
			storage.remove(currentAmmunition);
		} else if (currentAmmunition instanceof Armor) {
			// increase
		}
		return damage;
	}

	public void showStorage() {
		storage.showStorage();
	}

	public PersonalStorage getStorage() {
		return storage;
	}

	public void setStorage(PersonalStorage storage) {
		this.storage = storage;
	}

	/*
	 * The heroes regain some of their hp at every round if they are still alive.
	 * You can assume that during every round of a fight the heroes regain 5% of
	 * their hp and 5% of their mana.
	 */
	public void endOfRound() {
		if (isAlive()) {
			setHp(getHp() + getHp() * 0.05);
			setMana(getMana() + getMana() * 0.05);
		}
	}

	/*
	 * Upon exiting fight Hero who did not faint gains 150 coins and 2 experience
	 * points, then resurrects, and levels up. Total Defeated Monsters in fight
	 * resets.
	 */
	public void exitFight(int monsterLevel, QuestBoard board, Player player) {
		if (isAlive()) {
			setMoney(getMoney() + 100 * monsterLevel);
			setExperience(getExperience() + 2);
			setFightsWon(getFightsWon() + 1);
		} else {
			board.updateBoard(player, board.rows - 1, Quest.random.nextInt(10) % 2 + getHomeLane());
		}
		levelUp();
		resurrect();
		for (Monster monster : getDefeatedMonstersFight()) {
			getTotalDefeatedMonsters().add(monster);
		}
		getDefeatedMonstersFight().clear();
	}

	/*
	 * 
	 */
	public void levelUp() {
		if (level == 10) {
			return;
		}
		if (level * 10 <= getExperience()) {
			setLevel(level + 1);
			setMana(mana + mana * 0.1);
			upgradeSkills();
		}
	}

	/*
	 * Resurrect fainted hero. Call this method upon exiting the fight into new cell
	 * to reset Health power.
	 */
	public void resurrect() {
		resetHp();
	}

	/*
	 * Upgrade skills when Hero levels up. Each type of Hero has its own skill
	 * upgrade.
	 */
	public void upgradeSkills() {
	}

	@Override
	public String displayDetails() {
		// private double strength, dexterity, agility, mana, experience, money;

		return "Name: " + name + "\nHp: " + hp + "\nLevel: " + level + "\nStrength: " + strength + "\nDexterity: "
				+ dexterity + "\nAgility: " + agility + "\nMana: " + mana + "\nExperience: " + experience + "\nMoney: "
				+ money + "\nCurrent Ammunition: " + currentAmmunition;
	}

	public void information() {
		String format = "%-22s%-10s%-28s%-20s%-20s%-10s";
		System.out.println("Hero name: " + BACKGROUND_GREEN + BLACK + this.toString() + RESET);
		System.out.println(
				"____________________________________________________________________________________________________ ");
		String l1 = String.format(format, "\nGereral information:", "", "| Skills/Ammunition info:", "",
				"| Storage information:", "");
		System.out.print(
				"                               |                                               |                     ");

		String l0 = String.format(format, "\n_____________________", "__________", "|___________________________",
				"____________________", "|___________________", "_");
		String l2 = String.format(format, "\n Level:", (int) level, "|  Strength:", (int) strength, "|  Total items:",
				getStorage().size());
		String l3 = String.format(format, "\n Health Power:", (int) hp, "|  Agility:", (int) agility, "|  Armors:",
				getStorage().totalArmors());
		String l4 = String.format(format, "\n Mana:", (int) mana, "|  Dexterity:", (int) dexterity, "|  Weapons:",
				getStorage().totalWeapons());
		String l5 = String.format(format, "\n Experience:", (int) experience, "|  Current Ammunition:",
				getCurrentAmmunition().getName(), "|  Spells:", getStorage().totalSpells());
		String l6 = String.format(format, "\n Money:", (int) money, "|  Mascot", 0, "|  Potions:",
				getStorage().totalPotions());
		String l7 = String.format("%-32s%-48s%-30s", "\nTotal Monsters defeated: " + getTotalDefeatedMonsters().size(),
				"|", "|");
		System.out.println(l1 + l0 + l2 + l3 + l4 + l5 + l6 + l0 + l7 + l0);
	}

	public ArrayList<Monster> getTotalDefeatedMonsters() {
		return totalDefeatedMonsters;
	}

	public void setTotalDefeatedMonsters(ArrayList<Monster> totalDefeatedMonsters) {
		this.totalDefeatedMonsters = totalDefeatedMonsters;
	}

	public ArrayList<Monster> getDefeatedMonstersFight() {
		return defeatedMonstersFight;
	}

	public void setDefeatedMonstersFight(ArrayList<Monster> defeatedMonstersFight) {
		this.defeatedMonstersFight = defeatedMonstersFight;
	}

	public int getFightsWon() {
		return fightsWon;
	}

	public void setFightsWon(int fightsWon) {
		this.fightsWon = fightsWon;
	}

	public String image() {
		return null;
	}

}
