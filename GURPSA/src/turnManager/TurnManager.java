package turnManager;

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
	
	public void addTurn(Character newCharacter)
	{
		turnList.add(new Turn(actionManager, newCharacter));
	}
	
	public void clearTurnList()
	{
		turnList = new Vector<Turn>();
	}
	
	public void runTurn(String characterName)
	{
		for(int i = 0 ; i < turnList.size() ; i++)
		{
			if(characterName == turnList.get(i).getCharacterName())
				turnList.get(i).runTurn();
		}
	}
	
	public void runTurn(Character character)
	{
		for(int i = 0 ; i < turnList.size() ; i++)
		{
			if(character.getName() == turnList.get(i).getCharacterName())
				turnList.get(i).runTurn();
		}
	}
}
