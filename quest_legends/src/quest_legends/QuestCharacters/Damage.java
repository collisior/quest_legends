package quest_legends.QuestCharacters;

public interface Damage {
	/*
	 * Return damage calculated that must be caused on the passed character.
	 */
	public abstract double damageCalculation(QuestCharacter character);
}
