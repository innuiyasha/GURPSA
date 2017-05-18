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

	//woefully insufficient. Not sure when a turn is told/determines what move to run, so this is what I have for now.
	public void runTurn()
	{
		while(true)
		{
			System.out.println("Enter the name of the skill you want to check.");

			Scanner in = new Scanner(System.in);
			String line, skillName;

			if(!(line = in.nextLine()).equals("exit")) {
				String[] parts = line.split(" ");
				skillName = parts[0];
				actionManager.SkillCheck(initiator, skillName);
			}
			else
				return;
		}
	}

}
