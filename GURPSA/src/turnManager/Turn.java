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

	//This is incomplete as heck atm. This should be when move pieces + archetypes should be determined.
	@SuppressWarnings("resource")
	public void runTurn()
	{
		while(true)
		{
			System.out.println("This is " + initiator.getName() + "'s turn.");
			System.out.println("Enter the name of the skill you want to check.");

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
	}

}
