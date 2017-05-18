package actionManager;

import characterManager.Character;
import dataContainers.CharacterFields.CharactersSkill;
import skillManager.SkillManager;

import java.util.Scanner;
import java.util.Vector;

import advantageManager.AdvantageManager;

public class ActionManager {

	SkillManager skillManager;
	AdvantageManager advantageManager;

	public ActionManager(SkillManager skillManager, AdvantageManager advantageManager)
	{
		this.skillManager = skillManager;
		this.advantageManager = advantageManager;
	}


	public void SkillCheck(Character character, String skillName)
	{
		skillName = skillName.replace(".", " ");

		Vector<CharactersSkill> appropriateSkills = new Vector<CharactersSkill>();
		appropriateSkills = character.checkForAppropriateSkills(skillManager, skillName);

		System.out.println(appropriateSkills.toString());

		System.out.println("Which would you like to use?");

		CharactersSkill chosenSkill = null;
		String line;
		Scanner in = new Scanner(System.in);
		line = in.nextLine();

		for(int i = 0 ; i < appropriateSkills.size(); i++)
			if(line.equals(appropriateSkills.get(i).getSkillName()))
				chosenSkill = appropriateSkills.get(i);

		System.out.println(skillManager.getSkill(skillName).getBaseAttribute() + " is the typical base attribute.");

		switch(skillName) {
		case "DX":
			System.out.println("They have " + character.getDX() + " DX.");
		case "IQ":
			System.out.println("They have " + character.getIQ() + " IQ.");
		case "HT":
			System.out.println("They have " + character.getHT() + " HT.");
		case "Will":
			System.out.println("They have " + character.getWill() + " Will.");
		case "Per":
			System.out.println("They have " + character.getPER() + " Per.");
		}

		System.out.println("Which would you like to use?");

		String chosenAttribute = "";
		int attributeValue = 0;
		line = in.nextLine();

		switch(line) {
		case "DX":
			chosenAttribute = "DX";
			attributeValue = character.getDX();
			break;
		case "IQ":
			chosenAttribute = "IQ";
			attributeValue = character.getIQ();
			break;
		case "HT":
			chosenAttribute = "HT";
			attributeValue = character.getHT();
			break;
		case "Will":
			chosenAttribute = "Will";
			attributeValue = character.getWill();
			break;
		case "Per":
			chosenAttribute = "Per";
			attributeValue = character.getPER();
			break;
		}
		
		System.out.println("Roll " + chosenAttribute + " (" + attributeValue + ") at " + chosenSkill.getLevel() + " (" + chosenSkill.getSkillName() + ")");
	}
}
