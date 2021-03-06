package quest_legends;

import java.util.ArrayList;
import java.util.Deque;
import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.GenericDeque;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.Vizualization;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.Monster;

public class Fight implements Color, Vizualization, QuestDetails {

	QuestBoard board = null;
	Monster monster;
	ArrayList<Player> players = new ArrayList<Player>();
	Deque<Player> playersQueue;

	public Fight(QuestBoard board, Monster monster, ArrayList<Player> players) {
		this.board = board;
		this.monster = monster;
		this.players = players;
		this.playersQueue = GenericDeque.setQueue(players);
	}

	public void startFight() {
		Vizualization.fightCountdown();
		boolean fightStop = false;
		while (!fightStop) {
			if (fightContinues()) {
				Hero hero = nextAliveHero();
				if (hero.isAlive() && monster.isAlive()) {
					System.out.println(GREEN + "\nHero " + hero + " VERSUS Monster " + monster + RESET + "\n");
					printStatistics(hero);
					round(hero);
					hero.endOfRound();
				}
			} else {
				showWinner();
				fightStop = true;
			}
		}
		exitFight();
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
		}
		for (Player player : players) {
			Hero hero = (Hero) player.getHero();
			hero.exitFight(getMonstersLevel(), board, player);
		}
	}

	/*
	 * Fight round: 1) Hero makes one attack, 2) Monster makes one attack. Round
	 * Ends.
	 */
	private void round(Hero hero) {
		System.out.println(hero.image());
		heroAttacks(hero, monster);
		System.out.println(GREEN + "Monster's turn to attack ...\n\n" + RESET);
		System.out.println(monster.image());
		Vizualization.countdown(2);
		if (monster.isAlive()) {
			monsterAttacks(hero, monster);
		} else {
			hero.getDefeatedMonstersFight().add(monster);
			board.deadMonsters.add((Monster) monster);
			board.aliveMonsters.remove(monster);
			board.getBoard()[monster.current_row][monster.current_col].removePiece(MONSTER_PIECE);
			System.out.println(MONSTER + "\n" + DEFEATED);
		}
		hero.setHasDodgedAttack(false);
		monster.setHasDodgedAttack(false);
	}

	public void heroAttacks(Hero hero, Monster monster) {
		System.out.print(CYAN + "\nH" + playersQueue.getLast().getId() + " " + hero.getName() + ", " + RESET);
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
		Player player = playersQueue.pollFirst();
		Hero hero = (Hero) player.getHero();
		if (hero.isAlive()) {
			playersQueue = GenericDeque.enqueue(playersQueue, player);
			return hero;
		}
		return nextAliveHero();
	}

	private void showWinner() {
		if ((totalPlayersHpValue() > 0) && (monster.getHp() <= 0)) {
			((TeamQuest) players.get(0).getTeam()).addFightsWon();
			System.out.println("Players wins the fight!");
		} else {
			((TeamQuest) players.get(0).getTeam()).addFightsLost();
			System.out.println("Monster won the fight!");
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
		for (Monster monster : board.deadMonsters) {
			if (level < monster.getLevel()) {
				level = monster.getLevel();
			}
		}
		return level;
	}

	// print statistic of heros and monsters
	public void printStatistics(Hero hero) {

		System.out.println("----------------------------------------------------------");
		System.out.println("Heroes Statistics:");
		System.out.println("----------------------------------------------------------");
		for (Player player : players) {
			System.out.println(RED + "Player " + "H" + player.getId() + ":" + RESET);
			System.out.println("Name: " + player.getHero().getName() + "\nHp:    " + (int) player.getHero().getHp()
					+ "\nMana: " + ((Hero) player.getHero()).getMana());
			System.out.println();
		}

		System.out.println("----------------------------------------------------------");
		System.out.println("Monsters Statistics:");
		System.out.println("----------------------------------------------------------");
		System.out.println(RED + "Monster " + "M" + monster.id + ":" + RESET);
		System.out.println(monster.displayDetails());
		System.out.println("----------------------------------------------------------");
	}

}