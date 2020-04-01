package quest_legends;

import java.util.ArrayList;

import quest_legends.Ammunitions.Ammunition;
import quest_legends.Ammunitions.Armor;
import quest_legends.Ammunitions.Potion;
import quest_legends.Ammunitions.Spell;
import quest_legends.Ammunitions.Weapon;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.InputHandler;
import quest_legends.QuestCharacters.Hero;

public class PersonalStorage implements Color {

	private ArrayList<Ammunition> storage = new ArrayList<Ammunition>();
	private int counter = 0; // used to display indexes in showDisplay()

	public Ammunition get(int index) {
		return storage.get(index);
	}

	public Ammunition getRandomAmmunition(Hero hero) {
		int randomIndex = 0, tries = 100;
		Ammunition item = null;
		boolean choiceAccepted = false;
		do {
			if (tries == 0) {
				choiceAccepted = true; item = null; break;
			}
			if (storage.size() > 1) {
				randomIndex = Quest.random.nextInt(storage.size() - 1);
			} else {
				randomIndex = 0; 
			}
			if (item instanceof Spell) {
				if (((Spell) item).canUse(hero)) {
					choiceAccepted = true;
					break;
				} else {
					//
				}
			} else { // Weapon, Armor, Potion
				choiceAccepted = true;
				break;
			}
			tries--;
		} while (!choiceAccepted);

		return item;
	}

	public Ammunition chooseFromStorage(Hero hero) {
		showStorage();
		int index = 0;
		Ammunition item = null;

		boolean choiceAccepted = false;
		do {
			System.out.println("Which item do you want to use? Type index # of item to attack.");
			index = InputHandler.getInteger(0, storage.size() - 1);
			item = storage.get(index);

			if (item instanceof Spell) {
				if (((Spell) item).canUse(hero)) {
					choiceAccepted = true;
					break;
				} else {
					System.out.println("Not enough Mana to use this Spell!");
					return item; // null
				}
			} else { // Weapon, Armor, Potion
				choiceAccepted = true;
				break;
			}
		} while (!choiceAccepted);

		return item;

	}

	public void remove(int index) {
		storage.remove(index);
	}

	public void remove(Ammunition item) {
		storage.remove(item);
	}

	public void add(Ammunition a) {
		this.storage.add(a);
	}

	public int size() {
		return this.storage.size();
	}

	/*
	 * Display this storage
	 */
	public void showStorage() {
		System.out.println(BACKGROUND_BLACK + GREEN + "My Armors" + RESET);
		showArmors();
		System.out.println(BACKGROUND_BLACK + GREEN + "My Weapons" + RESET);
		showWeapons();
		System.out.println(BACKGROUND_BLACK + GREEN + "My Potions" + RESET);
		showPotions();
		System.out.println(BACKGROUND_BLACK + GREEN + "My Spells" + RESET);
		showSpells();
		counter = 0;
	}

	private void showArmors() {
		int counter = 0;
		System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s", "#", "Name", "Cost", "Level",
				"Damage Reduction", "Selling cost"));
		for (Ammunition a : storage) {
			if (a instanceof Armor) {
				System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s", counter, a.getName(), a.getCost(),
						a.getRequired_level(), ((Armor) a).getDamageReduction(), (a.getCost() / 2)));
			}
			counter++;
		}
		System.out.println();
	}

	private void showWeapons() {
		int counter = 0;
		System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s%-12s", "#", "Name", "Cost", "Level",
				"Reduction", "Required Hands", "Selling cost"));
		for (Ammunition a : storage) {
			if (a instanceof Weapon) {
				System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s%-12s", counter, a.getName(), a.getCost(),
						a.getRequired_level(), ((Weapon) a).getDamage(), ((Weapon) a).getRequiredHands(), (a.getCost() / 2)));
			}
			counter++;
		}
		System.out.println();
	}

	private void showPotions() {
		int counter = 0;
		System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s", "#", "Name", "Cost", "Level",
				"Attribute Increase", "Selling cost"));
		for (Ammunition a : storage) {
			if (a instanceof Potion) {
				System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s", counter, a.getName(), a.getCost(),
						a.getRequired_level(), ((Potion) a).getAttributeIncrease(), (a.getCost() / 2)));
			}
			counter++;
		}
		System.out.println();
	}

	private void showSpells() {
		int counter = 0;
		System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s%-12s", "#", "Name", "Cost", "Level", "Damage",
				"Mana Cost", "Selling cost"));
		for (Ammunition a : storage) {
			if (a instanceof Spell) {
				System.out.println(String.format("%-4s%-20s%-12s%-12s%-12s%-12s%-12s", counter, a.getName(), a.getCost(),
						a.getRequired_level(), ((Spell) a).getDamage(), ((Spell) a).getManaCost(), (a.getCost() / 2)));
			}
			counter++;
		}
		System.out.println();
	}
	
	public int totalArmors() {
		int num = 0;
		for (Ammunition a : storage) {
			if (a instanceof Armor) {
				num++;
			}
		}
		return num;
	}
	public int totalWeapons() {
		int num = 0;
		for (Ammunition a : storage) {
			if (a instanceof Weapon) {
				num++;
			}
		}
		return num;
	}
	public int totalSpells() {
		int num = 0;
		for (Ammunition a : storage) {
			if (a instanceof Spell) {
				num++;
			}
		}
		return num;
	}
	public int totalPotions() {
		int num = 0;
		for (Ammunition a : storage) {
			if (a instanceof Potion) {
				num++;
			}
		}
		return num;
	}

}
