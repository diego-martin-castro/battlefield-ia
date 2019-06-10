package ia.battle.example;

import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class MalitoWarriorManager extends WarriorManager {
	
	@Override
	public Warrior getNextWarrior() throws RuleException {
		// int health 
		// int defense 
		// int strength 
		// int speed 
		// int range 
		// -----------> 100 Total
		MalitoWarrior rocco = new MalitoWarrior("Malito", 20, 10, 10, 10, 10);
		
		return rocco;
	}

	@Override
	public String getName() {
		return "Malito";
	}
	
}
