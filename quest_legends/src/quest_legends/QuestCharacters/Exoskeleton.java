package quest_legends.QuestCharacters;

public class Exoskeleton extends Monster {
	//	exoskeletons have increased defense 
	public Exoskeleton(String name, int level, int damage, int defense, double dodgeChance) {
		super(name, level, damage, defense, dodgeChance);
		// TODO Auto-generated constructor stub
	}
	
	public String image() {
		String image = 
				"                              _.--\"\"-._\n" + 
				"  .                         .\"         \".\n" + 
				" / \\    ,^.         /(     Y             |      )\\\n" + 
				"/   `---. |--'\\    (  \\__..'--   -   -- -'\"\"-.-'  )\n" + 
				"|        :|    `>   '.     l_..-------.._l      .'\n" + 
				"|      __l;__ .'      \"-.__.||_.-'v'-._||`\"----\"\n" + 
				" \\  .-' | |  `              l._       _.'\n" + 
				"  \\/    | |                   l`^^'^^'j\n" + 
				"        | |                _   \\_____/     _\n" + 
				"        j |               l `--__)-'(__.--' |\n" + 
				"        | |               | /`---``-----'\"1 |  ,-----.\n" + 
				"        | |               )/  `--' '---'   \\'-'  ___  `-.\n" + 
				"        | |              //  `-'  '`----'  /  ,-'   I`.  \\\n" + 
				"      _ L |_            //  `-.-.'`-----' /  /  |   |  `. \\\n" + 
				"     '._' / \\         _/(   `/   )- ---' ;  /__.J   L.__.\\ :\n" + 
				"      `._;/7(-.......'  /        ) (     |  |            | |\n" + 
				"      `._;l _'--------_/        )-'/     :  |___.    _._./ ;\n" + 
				"        | |                 .__ )-'\\  __  \\  \\  I   1   / /\n" + 
				"        `-'                /   `-\\-(-'   \\ \\  `.|   | ,' /\n" + 
				"                           \\__  `-'    __/  `-. `---'',-'\n" + 
				"                              )-._.-- (        `-----'\n" + 
				"                             )(  l\\ o ('..-.\n" + 
				"                       _..--' _'-' '--'.-. |\n" + 
				"                __,,-'' _,,-''            \\ \\\n" + 
				"               f'. _,,-'                   \\ \\\n" + 
				"              ()--  |                       \\ \\\n" + 
				"                \\.  |                       /  \\\n" + 
				"                  \\ \\                      |._  |\n" + 
				"                   \\ \\                     |  ()|\n" + 
				"                    \\ \\                     \\  /\n" + 
				"                     ) `-.                   | |\n" + 
				"                    // .__)                  | |\n" + 
				"                 _.//7'                      | |\n" + 
				"               '---'                         j_| `\n" + 
				"                                            (| |\n" + 
				"                                             |  \\\n" + 
				"                                             |lllj\n" + 
				"                                             ||||| \n";
		return (color + image + RESET);
	}

}
