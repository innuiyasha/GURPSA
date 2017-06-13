package turnManager;

import java.util.Scanner;

import actionManager.ActionManager;
import characterManager.Character;

public class Turn {

	Character initiator;
	ActionManager actionManager;

	public Turn(ActionManager actionManager, Character initiator)
	{
		this.actionManager = actionManager;
		this.initiator = initiator;
	}

	public String getCharacterName()
	{
		return initiator.getName();
	}
	
	public Character getCharacter()
	{
		return initiator;
	}

	@SuppressWarnings("resource")
	public void runTurn()
	{
		while(true)
		{
			Scanner in = new Scanner(System.in);
			
			System.out.println("This is " + initiator.getName() + "'s turn.");
			System.out.println("What kind of move do they want to do?\n"
					+ "1. Skill Check\n"
					+ "2. Perception Check\n");
			String line = in.nextLine();
			
			switch(line){
			case "1":
				skillCheck();
				break;
			case "2":
				perceptionCheck();
				break;
			case "exit":
				return;
			}
		}
	}
	
	public void skillCheck()
	{
			actionManager.SkillCheck(initiator);
	}
	
	private void skillCheck(String skillName)
	{
		actionManager.SkillCheck(initiator, skillName);
	}
	
	public void skillCheck(String skillName, int modifier)
	{
		actionManager.SkillCheck(initiator, skillName, modifier);
	}
	
	private void perceptionCheck()
	{
		actionManager.PerceptionCheck(initiator);
	}

	public enum moveArchetypes
	{
		SKILL_CHECK, REACTION_CHECK, FRIGHT_CHECK, CONTEST, PHYSICAL_FEAT, COMBAT
	}
	
}
