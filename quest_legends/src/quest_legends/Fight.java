package quest_legends;

import java.util.ArrayList;
import java.util.PriorityQueue;
import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.Vizualization;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.Monster;

public class Fight implements Color, Vizualization {

	QuestBoard board = null;
	ArrayList<Monster> aliveMonsters = new ArrayList<Monster>();
	Monster monster;
	ArrayList<Monster> deadMonsters = new ArrayList<Monster>();
	PriorityQueue<Player> players = new PriorityQueue<Player>();

	public Fight(QuestBoard board, Monster monster, PriorityQueue<Player> players) {
		this.board = board;
		this.monster = monster;
		this.players = players;
	}

	public void startFight() {
		Vizualization.fightCountdown();
		boolean fightStop = false;
		while (!fightStop) {
			if (fightContinues()) {
				Hero hero = nextAliveHero();
				if (hero.isAlive() && monster.isAlive()) {
					System.out.println(GREEN + "\nHero " + hero + " VERSUS Monster " + monster + RESET + "\n");
					round(hero, monster);
					hero.endOfRound();
				}
			} else {
				showWinner();
				fightStop = true;
			}
		}
		return;
	}

	/*
	 * Update stats for each hero. Resurrect heroes. If the monsters win the fight
	 * the heroes lose half of their money.
	 */
	private void exitFight() {
		// if monsters win, half of money are taken away from each player
		if (totalPlayersHpValue() <= 0) {
			for (Player player : players) {
				Hero hero = (Hero) player.getHero();
				hero.setMoney(hero.getMoney() / 2);
			}
			System.out.println(YOU + "\n" + LOST + "\n" + FIGHT);
		} else {
			System.out.println(VICTORY + "\n" + MONSTERS + "\n" + DEFEATED);
		}
		for (Player player : players) {
			Hero hero = (Hero) player.getHero();
			hero.exitFight(getMonstersLevel());
		}
		// totalDefeatedInFight
		monsterCleaning();
	}

	/*
	 * 
	 */
	private void round(Hero hero, Monster monster) {
		System.out.println(hero.image());
		heroAttacks(hero, monster);
		System.out.println(GREEN + "Monster's turn to attack ...\n\n" + RESET);
		System.out.println(monster.image());
		Vizualization.countdown(2);
		if (monster.isAlive()) {
			monsterAttacks(hero, monster);
		} else {
			hero.getDefeatedMonstersFight().add(monster);
			deadMonsters.add((Monster) monster);
			aliveMonsters.remove(monster);
			if (!aliveMonsters.isEmpty()) {
				System.out.println(MONSTER + "\n" + DEFEATED);
			}
		}
		hero.setHasDodgedAttack(false);
		monster.setHasDodgedAttack(false);
	}

	public void heroAttacks(Hero hero, Monster monster) {
		hero.chooseCurrentAmmunition();
		System.out.println("Your attack: " + hero.getCurrentAmmunition() + "\n" + hero.getCurrentAmmunition().image());
		double damage = hero.damageCalculation(monster);

		if (monster.dodgeAttack()) {
			// monster escapes
			System.out.println(GREEN + "Oh no... Monster dodged your attack!\n\n" + RESET);
		} else {
			monster.applyDamage(damage);
		}
	}

	/*
	 * 
	 */
	public void monsterAttacks(Hero hero, Monster monster) {

		if (hero.dodgeAttack()) {
			// hero escapes
			System.out.println(GREEN + "Lucky son of a witch!!! You just dodged monster's attack!" + RESET);
		} else {
			double damage = monster.damageCalculation(hero);
			hero.applyDamage(damage);
			System.out.println(PUNCH);
			System.out.println(GREEN + "OUCHH...Monster attacked! and you got hurt...\n" + RESET);

		}
	}

	private Hero nextAliveHero() {
		Player player = players.poll();
		Hero hero = (Hero) players.poll().getHero();
		if (hero.isAlive()) {
			players.add(player);
			return hero;
		}
		players.add(player);
		return nextAliveHero();
	}

	private void showWinner() {
		if ((totalPlayersHpValue() > 0) && (monster.getHp() <= 0)) {
			((TeamQuest) players.peek().getTeam()).addFightsWon();
			System.out.println("Team wins the fight!");
		} else {
			((TeamQuest) players.peek().getTeam()).addFightsLost();
			System.out.println("Monsters wins the fight!");
		}
	}

	/*
	 * Return checkWinner
	 */
	public boolean fightContinues() {
		if ((totalPlayersHpValue() > 0) && (monster.getHp() > 0)) {
			return true;
		}
		return false;
	}

	/*
	 * Return total HP for this team.
	 */
	public int totalPlayersHpValue() {
		int totalHp = 0;
		for (Player player : players) {
			totalHp += player.getHero().getHp();
		}
		return totalHp;
	}

	/*
	 * Get Monster's max level from this fight.
	 */
	public int getMonstersLevel() {
		int level = 0;
		for (Monster monster : deadMonsters) {
			if (level < monster.getLevel()) {
				level = monster.getLevel();
			}
		}
		return level;
	}

	public void monsterCleaning() {
		aliveMonsters.clear();
		deadMonsters.clear();
	}

}