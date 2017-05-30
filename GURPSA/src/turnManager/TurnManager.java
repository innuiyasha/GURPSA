package turnManager;

import java.util.Collections;
import java.util.Scanner;
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
	
	public void runTurn(int index)
	{
		turnList.get(index).runTurn();
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
	
	@SuppressWarnings("resource")
	public void runCombatTurns()
	{
		enforceTurns(true);
		
		Scanner in = new Scanner(System.in);
		boolean stop = false;
		
		while(!stop)
			for(int i = 0 ; i < turnList.size(); i++)
			{
				if(stop)
					break;

				runTurn(i);

				System.out.println("Type exit if you want to leave combat mode. Anything else will proceed with the next turn.\n");
				if(in.nextLine().equals("exit"))
					stop = true;
			}
		
		enforceTurns(false);
	}
	
	private void enforceTurns(boolean enforce)
	{
		enforceTurnOrder = enforce;
		if(enforce && turnList.size() != 0)
			sortBySpeed();
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
