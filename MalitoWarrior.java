package ia.battle.example;

import ia.battle.core.actions.Attack;
import ia.battle.core.actions.Move;
import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;
import ia.battle.core.actions.Action;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorData;
import ia.exceptions.RuleException;
import java.util.ArrayList;
import java.util.List;


public class MalitoWarrior extends Warrior {
	
	private FieldCell lastPosition;
	
	public MalitoWarrior(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	@Override
	public Action playTurn(long tick,int actionNumber) {

		Action action;		
		int closerDistance = Integer.MAX_VALUE, distance;
		
		BattleField batalla= BattleField.getInstance();		
		WarriorData enemigo = batalla.getEnemyData();
				
		this.useSpecialItem();
		
		if(enemigo.getInRange()) 
		{
			this.enemyKilled();
			action = new Attack(enemigo.getFieldCell());			
		} 
		else 
		{			
			List<FieldCell> adj = BattleField.getInstance().getAdjacentCells(this.getPosition());
			FieldCell nextMove = this.getPosition();
			
			closerDistance = Integer.MAX_VALUE;
			
			for(FieldCell cell : adj) {
				distance = computeDistance(cell, enemigo.getFieldCell());
				System.out.println("COMPUTEDISTANCE " + distance);
				if ((closerDistance > distance) && (cell.getFieldCellType() != FieldCellType.BLOCKED) &&
						!cell.equals(this.getPosition()) && !cell.equals(lastPosition)) {
					nextMove = cell;
					lastPosition = nextMove;
					closerDistance = distance;
				} else if(cell == lastPosition) {
					System.out.println("REPITE POSICION");
					this.setHealth(0);		
				}
			}
					
			System.out.println("La ultima posicion fue" + lastPosition);
			action = new WarriorMove(nextMove);					
		}
		return action;
	}		
		
	@Override
	public void wasAttacked(int damage, FieldCell source) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void enemyKilled() {
		System.out.println("FREE KILL, ASTRAL");		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub		
	}
	
	private int computeDistance(FieldCell source, FieldCell target) {
		int distance = 0;

		distance = Math.abs(target.getX() - source.getX());
		distance += Math.abs(target.getY() - source.getY());

		return distance;
	}
	
	class WarriorMove extends Move {

		private FieldCell nextMove;
		
		WarriorMove (FieldCell cell) {
			nextMove = cell;
		}

		@Override
		public ArrayList<FieldCell> move() {
			ArrayList<FieldCell> cells = new ArrayList<FieldCell>();
			cells.add(nextMove);
			return cells;
		}
	}

}
