package ia.battle.example;

import java.util.ArrayList;

import ia.battle.core.FieldCell;
import ia.battle.core.actions.Move;


public class WarriorMove extends Move {
	
	private ArrayList<FieldCell> steps = new ArrayList<>();
	
	@Override
	public ArrayList<FieldCell> move() {
		
		return steps;
	}
	
	void setSteps(ArrayList<FieldCell> steps) {
		this.steps = steps;
	}
	
	
}
