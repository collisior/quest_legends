package quest_legends;

import java.util.ArrayList;

import quest_legends.Helpers.Color;
import quest_legends.Helpers.InputHandler;

/*
 * The Team class models team that consists of players.
 */
public class Team implements Color {

	private ArrayList<Player> team = new ArrayList<Player>();
	private int id;
	private String Name = "";
	private boolean WinnerStatus;
	private int turnIndex = 0; //this team's player turn 
	String color = GREEN; //default green color
	/*
	 * Constructor to initialize team.
	 */
	public Team(int id) {
		setId(id);
	}
	/*
	 * Set this team id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/*
	 * Set this team name.
	 */
	public void setName(String name) {
		this.Name = name;
	}
	/*
	 * Get this team id.
	 */
	public int getId() {
		return this.id;
	}
	/*
	 * Get this team name.
	 */
	public String getName() {
		return this.Name;
	}

	/*
	 * Change this Team's state to Winner
	 */
	public void changeToWinner() {
		if (this.WinnerStatus == false) {
			this.WinnerStatus = true;
		}
	}
	/*
	 * Change this Team's state to Non Winner
	 */
	public void changeToNonWinner() {
		if (this.WinnerStatus == true) {
			this.WinnerStatus = false;
		}
	}

	/*
	 * Returns team's player with next turn. Updates turnIndex.
	 */
	public Player getNextTeamPlayer() {
		int i = this.turnIndex;
		this.turnIndex = (turnIndex + 1) % getTeamSize();
		return team.get(i);
	}
	/*
	 * Returns team's current player with current turn.
	 */
	public Player getCurrentTeamPlayer() {
		return team.get(this.turnIndex);
	}

	/*
	 * Returns team's player with previous turn. Updates turnIndex.
	 */
	public Player getPrevTeamPlayer() {
		int i = this.turnIndex;
		this.turnIndex = (turnIndex - 1) % getTeamSize();
		return team.get(i);
	}

	/*
	 * Returns team (list of all its players)
	 */
	public ArrayList<Player> getTeam() {
		return this.team;
	}
	/*
	 * Set this team size.
	 */
	public int getTeamSize() {
		return this.team.size();
	}

	/*
	 * Add player to this team
	 */
	public void addPlayer(Player player) {
		team.add(player);
		player.setTeamId(this.id);
	}

	/*
	 * Remove player from this team
	 */
	public void removePlayer(Player player) {
		player.clearTeamId();
		team.remove(player);
	}
	/*
	 * Set this team id.
	 */
	public void askTeamName() {
		System.out.printf("Enter Team %d name: ", id);
		String name = InputHandler.getString();
		setName(name);
		System.out.println();
	}
	/*
	 * Display this team's all players
	 */
	public void displayListPlayers() {
		System.out.println(toString() + "'s list of players:");
		for (Player player : team) {
			System.out.println(player);
		}
	}

	/*
	 * Return total wins. Sum up every player's win totals of this Team
	 */
	public int getTotalWins() {
		int total = 0;
		for (Player player : team) {
			total += player.getTotalWins();// this getter should be in Player class
		}
		return total;
	}

	public String toString() {
		return color+"Team " + id + ": " + getName()+RESET;
	}


}
