package actionManager;

import characterManager.Character;
import dataContainers.CharacterFields.CharactersSkill;
import skillManager.SkillManager;
import utilities.Utilities;

import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import advantageManager.AdvantageManager;

public class ActionManager {

	SkillManager skillManager;
	AdvantageManager advantageManager;

	public ActionManager(SkillManager skillManager, AdvantageManager advantageManager)
	{
		this.skillManager = skillManager;
		this.advantageManager = advantageManager;
	}


	public void SkillCheck(Character character)
	{
		String skill = JOptionPane.showInputDialog("Which Skill do you want to check?");
		
		Vector<CharactersSkill> appropriateSkills = new Vector<CharactersSkill>();
		appropriateSkills = character.checkForAppropriateSkills(skillManager, skill);

		String chosenSkill = JOptionPane.showInputDialog(appropriateSkills.toString() + "\nWhich would you like to use?");
		chosenSkill = utilities.Utilities.formatSkillName(chosenSkill);
		
		CharactersSkill usedSkill = null;
		
		for(int i = 0 ; i < appropriateSkills.size(); i++)
			if(chosenSkill.equals(appropriateSkills.get(i).getSkillName()))
				usedSkill = appropriateSkills.get(i);
		
		String chosenAttribute = JOptionPane.showInputDialog(skillManager.getSkill(skill).getBaseAttribute() + " is the typical base attribute.\n"
				+ "Which would you like to use?");
		
		int attributeValue = 0;

		switch(chosenAttribute) {
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
		case "ST":
			chosenAttribute = "ST";
			attributeValue = character.getST();
			break;
		default:
			System.out.println("Fuck.");
			System.exit(-1);
		}

		System.out.println("Roll " + chosenAttribute + " (" + attributeValue + ") at " + usedSkill.getLevel() + " (" + usedSkill.getSkillName() + ")\n");
	}
	
	public void SkillCheck(Character character, String skillName)
	{
		skillName = utilities.Utilities.formatSkillName(skillName);

		Vector<CharactersSkill> appropriateSkills = new Vector<CharactersSkill>();

		appropriateSkills = character.checkForAppropriateSkills(skillManager, skillName);

		System.out.println(appropriateSkills.toString());

		System.out.println("Which would you like to use?");

		CharactersSkill chosenSkill = null;
		String line;

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		line = utilities.Utilities.formatSkillName(in.nextLine());

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
		case "ST":
			chosenAttribute = "ST";
			attributeValue = character.getST();
			break;
		default:
			System.out.println("Fuck.");
			System.exit(-1);
		}

		System.out.println("Roll " + chosenAttribute + " (" + attributeValue + ") at " + chosenSkill.getLevel() + " (" + chosenSkill.getSkillName() + ")\n");
	}
	public void SkillCheck(Character initiator, String skillName, int modifier) {
		// TODO Auto-generated method stub
		
	}

	public void PerceptionCheck(Character character)
	{
		//TODO: Modifiers should be able to be applied to single characters
		System.out.println("Enter the modifier you want to apply to this roll. 0 is the default.");

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		int modifier = 0;

		try{
			modifier = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			modifier = 0;
		}

		int perception = character.getPER() + modifier;
		int roll = Utilities.standardDiceRoll();
		System.out.println(character.getName() + " rolled " + roll + " and has Perception at " + perception + " for this roll.");

		if(roll <= perception)
			System.out.println("They succeeded by " + (perception - roll) + "\n");
		else
			System.out.println("They failed by " + (perception - roll) + "\n");

	}

	public void FrightCheck()
	{

	}

	public void RecoveryCheck()
	{

	}

	public void SkillContest()
	{

	}

	public void ReactionRoll()
	{

	}


}
