package quest_legends.Helpers;

import java.util.Timer;
import java.util.TimerTask;

public interface Vizualization {
	public static final String ONE = 
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |     __       | |\n" + 
			"| |    /  |      | |\n" + 
			"| |    `| |      | |\n" + 
			"| |     | |      | |\n" + 
			"| |    _| |_     | |\n" + 
			"| |   |_____|    | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	
	public static final String TWO =
		    " .----------------. \n" + 
		    "| .--------------. |\n" + 
		    "| |    _____     | |\n" + 
		    "| |   / ___ `.   | |\n" + 
		    "| |  |_/___) |   | |\n" + 
		    "| |   .'____.'   | |\n" + 
		    "| |  / /____     | |\n" + 
		    "| |  |_______|   | |\n" + 
		    "| |              | |\n" + 
		    "| '--------------' |\n" + 
		    " '----------------' ";
	
	public static final String THREE =
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |    ______    | |\n" + 
			"| |   / ____ `.  | |\n" + 
			"| |   `'  __) |  | |\n" + 
			"| |   _  |__ '.  | |\n" + 
			"| |  | \\____) |  | |\n" + 
			"| |   \\______.'  | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	
	public static final String FOUR =
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |   _    _     | |\n" + 
			"| |  | |  | |    | |\n" + 
			"| |  | |__| |_   | |\n" + 
			"| |  |____   _|  | |\n" + 
			"| |      _| |_   | |\n" + 
			"| |     |_____|  | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	
	public static final String FIVE = 
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |   _______    | |\n" + 
			"| |  |  _____|   | |\n" + 
			"| |  | |____     | |\n" + 
			"| |  '_.____''.  | |\n" + 
			"| |  | \\____) |  | |\n" + 
			"| |   \\______.'  | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	public static final String SIX =
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |    ______    | |\n" + 
			"| |  .' ____ \\   | |\n" + 
			"| |  | |____\\_|  | |\n" + 
			"| |  | '____`'.  | |\n" + 
			"| |  | (____) |  | |\n" + 
			"| |  '.______.'  | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	public static final String SEVEN =
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |   _______    | |\n" + 
			"| |  |  ___  |   | |\n" + 
			"| |  |_/  / /    | |\n" + 
			"| |      / /     | |\n" + 
			"| |     / /      | |\n" + 
			"| |    /_/       | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	public static final String EIGHT =
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |     ____     | |\n" + 
			"| |   .' __ '.   | |\n" + 
			"| |   | (__) |   | |\n" + 
			"| |   .`____'.   | |\n" + 
			"| |  | (____) |  | |\n" + 
			"| |  `.______.'  | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	public static final String NINE = 
			" .----------------. \n" + 
			"| .--------------. |\n" + 
			"| |    ______    | |\n" + 
			"| |  .' ____ '.  | |\n" + 
			"| |  | (____) |  | |\n" + 
			"| |  '_.____. |  | |\n" + 
			"| |  | \\____| |  | |\n" + 
			"| |   \\______,'  | |\n" + 
			"| |              | |\n" + 
			"| '--------------' |\n" + 
			" '----------------' ";
	

	public static final String WELCOME = 
			"          _______  _        _______  _______  _______  _______ \n" + 
			"|\\     /|(  ____ \\( \\      (  ____ \\(  ___  )(       )(  ____ \\\n" + 
			"| )   ( || (    \\/| (      | (    \\/| (   ) || () () || (    \\/\n" + 
			"| | _ | || (__    | |      | |      | |   | || || || || (__    \n" + 
			"| |( )| ||  __)   | |      | |      | |   | || |(_)| ||  __)   \n" + 
			"| || || || (      | |      | |      | |   | || |   | || (      \n" + 
			"| () () || (____/\\| (____/\\| (____/\\| (___) || )   ( || (____/\\\n" + 
			"(_______)(_______/(_______/(_______/(_______)|/     \\|(_______/\n" + 
			"                                                               ";
	
	public static final String FINISH = 
			" _______ _________ _       _________ _______           _ \n" + 
			"(  ____ \\\\__   __/( (    /|\\__   __/(  ____ \\|\\     /|( )\n" + 
			"| (    \\/   ) (   |  \\  ( |   ) (   | (    \\/| )   ( || |\n" + 
			"| (__       | |   |   \\ | |   | |   | (_____ | (___) || |\n" + 
			"|  __)      | |   | (\\ \\) |   | |   (_____  )|  ___  || |\n" + 
			"| (         | |   | | \\   |   | |         ) || (   ) |(_)\n" + 
			"| )      ___) (___| )  \\  |___) (___/\\____) || )   ( | _ \n" + 
			"|/       \\_______/|/    )_)\\_______/\\_______)|/     \\|(_)";
	public static final String FIGHT = 
			" _______ _________ _______          _________   _   \n" + 
			"(  ____ \\\\__   __/(  ____ \\|\\     /|\\__   __/  ( )  \n" + 
			"| (    \\/   ) (   | (    \\/| )   ( |   ) (     | |  \n" + 
			"| (__       | |   | |      | (___) |   | |     | |  \n" + 
			"|  __)      | |   | | ____ |  ___  |   | |     | |  \n" + 
			"| (         | |   | | \\_  )| (   ) |   | |     (_)  \n" + 
			"| )      ___) (___| (___) || )   ( |   | |      _   \n" + 
			"|/       \\_______/(_______)|/     \\|   )_(     (_)  \n" + 
			"                                                    ";
	
	public static final String ROUND = 
			" _______  _______           _        ______  \n" + 
			"(  ____ )(  ___  )|\\     /|( (    /|(  __  \\ \n" + 
			"| (    )|| (   ) || )   ( ||  \\  ( || (  \\  )\n" + 
			"| (____)|| |   | || |   | ||   \\ | || |   ) |\n" + 
			"|     __)| |   | || |   | || (\\ \\) || |   | |\n" + 
			"| (\\ (   | |   | || |   | || | \\   || |   ) |\n" + 
			"| ) \\ \\__| (___) || (___) || )  \\  || (__/  )\n" + 
			"|/   \\__/(_______)(_______)|/    )_)(______/ \n" + 
			"                                             ";

	public static final String VICTORY =
			"         _________ _______ _________ _______  _______          \n" + 
			"|\\     /|\\__   __/(  ____ \\\\__   __/(  ___  )(  ____ )|\\     /|\n" + 
			"| )   ( |   ) (   | (    \\/   ) (   | (   ) || (    )|( \\   / )\n" + 
			"| |   | |   | |   | |         | |   | |   | || (____)| \\ (_) / \n" + 
			"( (   ) )   | |   | |         | |   | |   | ||     __)  \\   /  \n" + 
			" \\ \\_/ /    | |   | |         | |   | |   | || (\\ (      ) (   \n" + 
			"  \\   /  ___) (___| (____/\\   | |   | (___) || ) \\ \\__   | |   \n" + 
			"   \\_/   \\_______/(_______/   )_(   (_______)|/   \\__/   \\_/   \n" + 
			"                                                               ";
	public static final String YOU =
			"          _______          \n" + 
			"|\\     /|(  ___  )|\\     /|\n" + 
			"( \\   / )| (   ) || )   ( |\n" + 
			" \\ (_) / | |   | || |   | |\n" + 
			"  \\   /  | |   | || |   | |\n" + 
			"   ) (   | |   | || |   | |\n" + 
			"   | |   | (___) || (___) |\n" + 
			"   \\_/   (_______)(_______)\n" + 
			"                           ";
	public static final String LOST =
			" _        _______  _______ _________\n" + 
			"( \\      (  ___  )(  ____ \\\\__   __/\n" + 
			"| (      | (   ) || (    \\/   ) (   \n" + 
			"| |      | |   | || (_____    | |   \n" + 
			"| |      | |   | |(_____  )   | |   \n" + 
			"| |      | |   | |      ) |   | |   \n" + 
			"| (____/\\| (___) |/\\____) |   | |   \n" + 
			"(_______/(_______)\\_______)   )_(   \n" + 
			"                                    ";
	public static final String MONSTER =
			" _______  _______  _        _______ _________ _______  _______ \n" + 
			"(       )(  ___  )( (    /|(  ____ \\\\__   __/(  ____ \\(  ____ )\n" + 
			"| () () || (   ) ||  \\  ( || (    \\/   ) (   | (    \\/| (    )|\n" + 
			"| || || || |   | ||   \\ | || (_____    | |   | (__    | (____)|\n" + 
			"| |(_)| || |   | || (\\ \\) |(_____  )   | |   |  __)   |     __)\n" + 
			"| |   | || |   | || | \\   |      ) |   | |   | (      | (\\ (   \n" + 
			"| )   ( || (___) || )  \\  |/\\____) |   | |   | (____/\\| ) \\ \\__\n" + 
			"|/     \\|(_______)|/    )_)\\_______)   )_(   (_______/|/   \\__/\n" + 
			"                                                               ";
	public static final String MONSTERS = 
			" _______  _______  _        _______ _________ _______  _______  _______ \n" + 
			"(       )(  ___  )( (    /|(  ____ \\\\__   __/(  ____ \\(  ____ )(  ____ \\\n" + 
			"| () () || (   ) ||  \\  ( || (    \\/   ) (   | (    \\/| (    )|| (    \\/\n" + 
			"| || || || |   | ||   \\ | || (_____    | |   | (__    | (____)|| (_____ \n" + 
			"| |(_)| || |   | || (\\ \\) |(_____  )   | |   |  __)   |     __)(_____  )\n" + 
			"| |   | || |   | || | \\   |      ) |   | |   | (      | (\\ (         ) |\n" + 
			"| )   ( || (___) || )  \\  |/\\____) |   | |   | (____/\\| ) \\ \\__/\\____) |\n" + 
			"|/     \\|(_______)|/    )_)\\_______)   )_(   (_______/|/   \\__/\\_______)\n" + 
			"                                                                        ";
	
	public static final String VS = 
			" /$$    /$$  /$$$$$$ \n" + 
			"| $$   | $$ /$$__  $$\n" + 
			"| $$   | $$| $$  \\__/\n" + 
			"|  $$ / $$/|  $$$$$$ \n" + 
			" \\  $$ $$/  \\____  $$\n" + 
			"  \\  $$$/   /$$  \\ $$\n" + 
			"   \\  $/   |  $$$$$$/\n" + 
			"    \\_/     \\______/ \n";
	public static final String DEFEATED = 
			" ______   _______  _______  _______  _______ _________ _______  ______  \n" + 
			"(  __  \\ (  ____ \\(  ____ \\(  ____ \\(  ___  )\\__   __/(  ____ \\(  __  \\ \n" + 
			"| (  \\  )| (    \\/| (    \\/| (    \\/| (   ) |   ) (   | (    \\/| (  \\  )\n" + 
			"| |   ) || (__    | (__    | (__    | (___) |   | |   | (__    | |   ) |\n" + 
			"| |   | ||  __)   |  __)   |  __)   |  ___  |   | |   |  __)   | |   | |\n" + 
			"| |   ) || (      | (      | (      | (   ) |   | |   | (      | |   ) |\n" + 
			"| (__/  )| (____/\\| )      | (____/\\| )   ( |   | |   | (____/\\| (__/  )\n" + 
			"(______/ (_______/|/       (_______/|/     \\|   )_(   (_______/(______/ \n" + 
			"                                                                        ";
	
	
	public static final String PUNCH = 
			"\n" + 
			"                __________                  __,___/  \",\",\n" + 
			"         ___.--\"          \"\\'.         ____/  l   \\    \",'-,\n" + 
			"  ------f\"               // \\\\\\        \\  (l\\ \\    \\     \\ \\\",\n" + 
			"        |                    |||       /   u       |      \\ \\ \\\n" + 
			"        |                    |||     _ )          /       | |  \\\n" + 
			"    ----L_-XXX-.             .|'    / U          <        | |  |\n" + 
			"                \"\\   -<_____///     \\           6 )       | |  |\n" + 
			"                  \\___)     -\"       '.       -.<\"       / /   |\n" + 
			"                                      |'.___  |       _._.\"   /\n" + 
			"                                      |     ./     _.\".\"   _.\"\n" + 
			"                                     /      |\"----\"     _.\"\n" + 
			"                                  jjs       \\\n" + 
			"\n" + 
			"";
	public static final String FINGER = 
			"           ___________    ____\n" + 
			"    ______/   \\__//   \\__/____\\\n" + 
			"  _/   \\_/  :           //____\\\\\n" + 
			" /|      :  :  ..      /        \\\n" + 
			"| |     ::     ::      \\        /\n" + 
			"| |     :|     ||     \\ \\______/\n" + 
			"| |     ||     ||      |\\  /  |\n" + 
			" \\|     ||     ||      |   / | \\\n" + 
			"  |     ||     ||      |  / /_\\ \\\n" + 
			"  | ___ || ___ ||      | /  /    \\\n" + 
			"   \\_-_/  \\_-_/ | ____ |/__/      \\\n" + 
			"                _\\_--_/    \\      /\n" + 
			"               /____             /\n" + 
			"              /     \\           /\n" + 
			"              \\______\\_________/";
	
	public static final String GREEN1 = "\u001B[32m";
	public static final String CYAN1 = "\u001B[36m";
	public static final String RED1 = "\u001B[31m";
	public static final String PINK1 = "\u001B[35m";
	public static final String PURPLE1 = "\u001B[34m";
	public static final String RESET1 = "\u001B[0m";
	public static final String YELLOW1 = "\u001B[33m";
	public static final String BLACK1 = "\u001B[30m";
	public static final String BLOCKED_CELL_MESSAGE = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" + RED1
			+ "   You shall not pass into this cell! Try another move.\n" + RESET1
			+ "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
	public static final String ANOTHER_HERO_OCCUPIED_MESSAGE = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" + RED1
			+ "   You can't go to this cell! Another Hero is there! Try another move.\n" + RESET1
			+ "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
	public static final String BEHIND_MONSTER_MESSAGE = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" + RED1
			+ "   You can't pass behind a monster without killing it! Try another move.\n" + RESET1
			+ "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - ";
	public static final String BOARD_CELLS_INFO = 
			"Quest Board Cell Details: \n\n" 
					+ "Blocked:   Koulou:    Cave:      Bush:     Nexus:\n"  
					+BLACK1+"\\\\\\\\\\\\"+PURPLE1+"     KKKKKK"+YELLOW1+"     CCCCCC"+GREEN1+"     BBBBBB" +CYAN1+"    NNNNNN\n" 
					+BLACK1+"//////" + PURPLE1 +   "     KKKKKK"+YELLOW1+"     CCCCCC"+GREEN1+"     BBBBBB"+CYAN1+"    NNNNNN\n" 
					+BLACK1+"\\\\\\\\\\\\"+PURPLE1+"     KKKKKK"+YELLOW1+"     CCCCCC"+GREEN1+"     BBBBBB" +CYAN1+"    NNNNNN\n" + RESET1;
			
	public static final String FIGHT_RADIUS_INFO = "\n\nFight radius details:\n\nThe fight will automatically begin if hero-monster is in one of following positions:\n"
			+ "\n+-------+ \t +-------+ \t +-------+ \t +-------+ \t +-------+"
			+ "\n| M | H | \t | M |   | \t | M |   | \t |   | M | \t | MH|   |"
			+ "\n|-------| \t |-------| \t |-------| \t |-------| \t |-------|"
			+ "\n|   |   | \t | H |   | \t |   | H | \t | H |   | \t |   |   |"
			+ "\n+-------+ \t +-------+ \t +-------+ \t +-------+ \t +-------+"
			+ "\n\n ^adjacent row - column, diagonally apart, or in the same cell";
	public static void main(String[] args) {
		String s = 
				"Quest Board Cell Details: \n\n" 
				+ "Blocked:   Koulou:    Cave:      Bush:     Nexus:\n"  
				+BLACK1+"\\\\\\\\\\\\"+PURPLE1+"     KKKKKK"+YELLOW1+"     CCCCCC"+GREEN1+"     BBBBBB" +CYAN1+"    NNNNNN\n" 
				+BLACK1+"//////" + PURPLE1 +   "     KKKKKK"+YELLOW1+"     CCCCCC"+GREEN1+"     BBBBBB"+CYAN1+"    NNNNNN\n" 
				+BLACK1+"\\\\\\\\\\\\"+PURPLE1+"     KKKKKK"+YELLOW1+"     CCCCCC"+GREEN1+"     BBBBBB" +CYAN1+"    NNNNNN\n" + RESET1;
		
		String fightRadius = "\n\nFight radius details:\n\nThe fight will automatically begin if hero-monster is in one of following positions:\n"
				+ "\n+-------+ \t +-------+ \t +-------+ \t +-------+ \t +-------+"
				+ "\n| M | H | \t | M |   | \t | M |   | \t |   | M | \t | MH|   |"
				+ "\n|-------| \t |-------| \t |-------| \t |-------| \t |-------|"
				+ "\n|   |   | \t | H |   | \t |   | H | \t | H |   | \t |   |   |"
				+ "\n+-------+ \t +-------+ \t +-------+ \t +-------+ \t +-------+"
				+ "\n\n ^adjacent row - column, diagonally apart, or in the same cell";
		System.out.println(s + fightRadius);		
	}
	public static void fightCountdown() {
		Timer timer = new Timer();
		int seconds = 3;
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = seconds;

			public void run() {
				if (i == 0) {
					System.out.println(FIGHT);
				} else if (i == 1) {
					System.out.println(ONE);
				} else if (i == 2) {
					System.out.println(TWO);
				} else if (i == 3) {
					System.out.println(THREE);
				}
				i--;
				if (i < 0)
					timer.cancel();
			}
		}, 0, 1000);
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		return;
	}
	public static void countdown(int seconds) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = seconds;

			public void run() {
				i--;
				System.out.print("");
				if (i < 0)
					timer.cancel();
			}
		}, 0, 1000);
		try {
			Thread.sleep(seconds * 750);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		return;
	}
}
