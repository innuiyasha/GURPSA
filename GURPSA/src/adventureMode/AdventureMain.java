package adventureMode;

import java.io.File;
import java.util.Scanner;

import characterManager.*;
import characterManager.Character;
import skillManager.SkillManager;

//This will one day be one of our most important classes, but for now I'm using this as a test main.
public class AdventureMain {
	
	
	//This is a toggle for read/write XML
	static boolean MakeXML = false;
	static boolean Characters = true;
	static boolean Skill = false;
	static boolean Interactive = true;
	
	static CharacterManager playerManager;
	static SkillManager skillManager;

	public static void main(String[] args) {

		//Eventually I'll just get rid of the argument of these- it isn't doing anything atm
		playerManager = new CharacterManager(false);
		
		skillManager = new SkillManager(false);
		skillManager.GenerateSkills(new File("skills.xml"));
		
		System.out.println("MAIN MENU\n\n"
				+ "1. Look up Skills\n"
				+ "2. Load Characters\n"
				+ "3. Make Characters\n\n"
				+ "Type 'exit' to close.\n");
		
		Scanner in = new Scanner(System.in);
		
		String line;
		while(!(line = in.nextLine()).equals("exit")) {
			
			switch(line) {
			case "1":
				SkillMenu(in);
				break;
			case "2": 
				CharacterMenu(in);
				break;
			case "3": 
				CharacterMaker(in);
				break;
			}
			
			System.out.println("MAIN MENU\n\n"
					+ "1. Look up Skills\n"
					+ "2. Load Characters\n"
					+ "3. Make Characters\n\n"
					+ "Type 'exit' to close.\n");
		}
		in.close();
		
		/*
		if(Characters)
		{
			CharacterManager playerManager = new CharacterManager(MakeXML);
			if(MakeXML == true)
				playerManager.toXMLFile(playerManager.getCharacter(0));
			else if(Interactive == true)
			{
				//SkillManager skillManager = new SkillManager(MakeXML);
				//skillManager.GenerateSkills(new File("skills.xml"));
				
				playerManager.AddCharacter(new File("defaultCharacter.xml"));
				
				System.out.println("Please enter each skill you want Teresa to have, one at a time. Type 'exit' when you're done.\n"
						+ "The format is: 'skillname relativeLevel', and use periods instead of spaces in skill name.");
				Scanner in = new Scanner(System.in);
				
				String line;
				while(!(line = in.nextLine()).equals("exit")) {
					String[] parts = line.split(" ");
					String name = parts[0].replace(".", " ");
					if(parts.length > 1)
					{
						playerManager.getCharacter(0).addSkill(name, Integer.parseInt(parts[1]));
					}
				}
				in.close();
				playerManager.displayCharacters();
				playerManager.toXMLFile(playerManager.getCharacter(0));
			}
		}
		if(Skill)
		{
			SkillManager skillManager = new SkillManager(MakeXML);
			if(MakeXML == true)
			{
				skillManager.toXMLFile();
			}
			else if(Interactive) {
				// Format of a command looks like the following:
				// SkillName FieldOfInterest FieldOfInterest . . .
				// Valid fields are: 'difficulty' 'description' 'attribute' 'default' 'TL'
				
				skillManager.GenerateSkills(new File("skills.xml"));
				System.out.println("Please enter skill of interest and feild(s) of interest OR type 'exit' to leave\n"
						+ "If a multi-word skill, use periods rather than spaces\n"
						+ "'SkillName' Field1 Field2 . . . FieldN");
				Scanner in = new Scanner(System.in);
				
				String line;
				while(!(line = in.nextLine()).equals("exit")) {
					String[] parts = line.split(" ");
					String name = parts[0].replace(".", " ");
					System.out.println(name + ":");
					for (int i = 1; i < parts.length; i++) {
						System.out.println("\t" + parts[i] + ": " + skillManager.request(name, parts[i]));
					}
				}
				in.close();
			}
			else
			{
				skillManager.GenerateSkills(new File("skills.xml"));
				skillManager.displaySkills();
			}
		}*/
	}

	private static void SkillMenu(Scanner in)
	{
		System.out.println("SKILL MENU\n");
		// Format of a command looks like the following:
		// SkillName FieldOfInterest FieldOfInterest . . .
		// Valid fields are: 'difficulty' 'description' 'attribute' 'default' 'TL'
		
		System.out.println("Please enter skill of interest and field(s) of interest OR type 'exit' to leave\n"
				+ "If a multi-word skill, use periods rather than spaces\n"
				+ "Valid fields are: 'difficulty' 'description' 'attribute' 'default' 'TL'\n"
				+ "'SkillName' Field1 Field2 . . . FieldN");
		
		String line;
		while(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			String name = parts[0].replace(".", " ");
			System.out.println(name + ":");
			for (int i = 1; i < parts.length; i++) {
				System.out.println("\t" + parts[i] + ": " + skillManager.request(name, parts[i]));
			}
		}
	}

	private static void CharacterMenu(Scanner in)
	{
		System.out.println("CHARACTER MENU\n");
		System.out.println("Enter the character's name, and we'll look for their file. Type 'exit' to leave.");
		
		String line;
		while(!(line = in.nextLine()).equals("exit")) {
			playerManager.AddCharacter(new File(line + ".xml"));
			playerManager.displayCharacters();
			
			System.out.println("CHARACTER MENU\n");
			System.out.println("Enter the character's name, and we'll look for their file. Type 'exit' to leave.");
		}
	}

	private static void CharacterMaker(Scanner in)
	{
		System.out.println("CHARACTER MAKER\n");
		System.out.println("Type 'exit' if you want to leave this at any time. However, nothing will be saved unless you finish.");

		Character newChara = new Character();
		String line;
		
		System.out.println("What is the character's name?");
		if(!(line = in.nextLine()).equals("exit")) {
			newChara.setName(line);
		}
		else
			return;
		
		System.out.println("What is the player's name?");
		if(!(line = in.nextLine()).equals("exit")) {
			newChara.setPlayer(line);
		}
		else
			return;
		
		System.out.println("What is the character's height and weight? Only use a space to separate the two.");
		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			newChara.setHeight(parts[0]);
			newChara.setWeight(parts[1]);
		}
		else
			return;
		
		System.out.println("Enter their ST, DX, IQ, and HT, all separated by spaces.");
		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			newChara.setST(Integer.parseInt(parts[0]));
			newChara.setDX(Integer.parseInt(parts[1]));
			newChara.setIQ(Integer.parseInt(parts[2]));
			newChara.setHT(Integer.parseInt(parts[3]));
		}
		else
			return;
		
		System.out.println("Enter their HP, Will, Per, and FP, all separated by spaces.");
		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			newChara.setHP(Integer.parseInt(parts[0]));
			newChara.setWill(Integer.parseInt(parts[1]));
			newChara.setPER(Integer.parseInt(parts[2]));
			newChara.setFP(Integer.parseInt(parts[3]));
		}
		else
			return;
		
		System.out.println("Enter their basic speed and basic move, separated by a space.");
		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			newChara.setSpeed(Float.parseFloat(parts[0]));
			newChara.setMove(Integer.parseInt(parts[1]));
		}
		else
			return;
		
		System.out.println("Enter their TL, SM, and age, separated by spaces.");
		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			newChara.setTL(Integer.parseInt(parts[0]));
			newChara.setSM(Integer.parseInt(parts[1]));
			newChara.setAge(Integer.parseInt(parts[2]));
		}
		else
			return;
		
		System.out.println("Now enter their skills and their relative level in that skill, one at a time.\n"
				+ "As an example, 'First.Aid -1'. Type 'exit' to finish character creation.");
		while(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			String name = parts[0].replace(".", " ");
			if(parts.length > 1)
			{
				if(skillManager.isSkill(name)) {
					newChara.addSkill(name, Integer.parseInt(parts[1]));
				} else {
					System.out.println(name + " is not a skill");
				}
			}
		}
		
		playerManager.AddCharacter(newChara);
		playerManager.toXMLFile(newChara);
	}
	
}
