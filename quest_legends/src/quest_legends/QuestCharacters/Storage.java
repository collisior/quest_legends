package quest_legends.QuestCharacters;

import quest_legends.PersonalStorage;

public interface Storage {
	abstract public void setStorage(PersonalStorage storage);
	abstract public void showStorage();
	abstract public PersonalStorage getStorage();
}
