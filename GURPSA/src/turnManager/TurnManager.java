package turnManager;

import java.util.Collections;
import java.util.Vector;

import actionManager.ActionManager;
import characterManager.Character;

public class TurnManager {

	boolean enforceTurnOrder = false;
	boolean rebuildTurnOrder = false;
	
	Vector<Turn> turnList;
	
	ActionManager actionManager;
	
	public TurnManager(ActionManager actionManager)
	{
		this.actionManager = actionManager;
		turnList = new Vector<Turn>();
	}
	
	public void buildTurnList(Vector<Character> characters)
	{
		for(int i = 0 ; i < characters.size() ; i++)
		{
			turnList.add(new Turn(actionManager, characters.get(i)));
		}
	}
	
	public void addTurn(Character newCharacter)
	{
		turnList.add(new Turn(actionManager, newCharacter));
	}
	
	public void clearTurnList()
	{
		turnList = new Vector<Turn>();
	}
	
	public Vector<Turn> getTurn(Character[] characters)
	{
		Vector<Turn> turns = new Vector<Turn>();
		for(int i = 0 ; i < characters.length; i++)
			for(int j = 0 ; j < turnList.size() ; j++)
				if(characters[i].getName() == turnList.get(j).getCharacterName())
					turns.add(turnList.get(j));
		return turns;
	}
	
	public Vector<Turn> getTurn(Vector<Character> characters)
	{
		Vector<Turn> turns = new Vector<Turn>();
		for(int i = 0 ; i < characters.size(); i++)
			for(int j = 0 ; j < turnList.size() ; j++)
				if(characters.get(i).getName() == turnList.get(j).getCharacterName())
					turns.add(turnList.get(j));
		return turns;
	}
	
	public Vector<Turn> getTurn(String [] characters)
	{
		Vector<Turn> turns = new Vector<Turn>();
		for(int i = 0 ; i < characters.length; i++)
			for(int j = 0 ; j < turnList.size() ; j++)
				if(characters[i] == turnList.get(j).getCharacterName())
					turns.add(turnList.get(j));
		return turns;
	}
	
	private void sortBySpeed()
	{
		for(int i = 0 ; i < turnList.size(); i++)
		{
			int highestSpeedChara = i;
			for(int j = i ; j < turnList.size() ; j++)
				if(turnList.get(j).getCharacter().getSpeed() > turnList.get(highestSpeedChara).getCharacter().getSpeed())
					highestSpeedChara = j;
			Collections.swap(turnList, i, highestSpeedChara);
		}
	}
}
