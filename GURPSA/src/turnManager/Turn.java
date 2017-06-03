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
	
	private void skillCheck()
	{
		System.out.println("Enter the name of the skill you want to check. ((This still requires '.'s instead of spaces))");

		Scanner in = new Scanner(System.in);
		String line, skillName;

		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			skillName = parts[0];
			actionManager.SkillCheck(initiator, skillName);
		} else {
			return;
		}
	}
	
	private void skillCheck(String skillName)
	{
		actionManager.SkillCheck(initiator, skillName);
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
