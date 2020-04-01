package quest_legends.Ammunitions;

import quest_legends.QuestCharacters.Monster;

public class Fists extends Ammunition {

	public Fists() {
		super("Fists", 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canSell() {
		return false;
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
				"                        _    ,-,    _\n" + 
				"                 ,--, /: :\\/': :`\\/: :\\\n" + 
				"                |`;  ' `,'   `.;    `: |\n" + 
				"                |    |     |  '  |     |.\n" + 
				"                | :  |     | pb  |     ||\n" + 
				"                | :. |  :  |  :  |  :  | \\\n" + 
				"                 \\__/: :.. : :.. | :.. |  )\n" + 
				"                      `---',\\___/,\\___/ /'\n" + 
				"                           `==._ .. . /'\n" + 
				"                                `-::-'\n\n";
		return image;
	}

}
