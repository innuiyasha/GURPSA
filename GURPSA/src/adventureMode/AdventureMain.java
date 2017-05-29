package adventureMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import actionManager.ActionManager;
import advantageManager.AdvantageManager;
import characterManager.*;
import characterManager.Character;
import dataContainers.CharacterFields.Prerequisite;
import skillManager.SkillManager;
import turnManager.TurnManager;

//This will one day be one of our most important classes, but for now I'm using this as a test main.
public class AdventureMain {
	
	
	//This is a toggle for read/write XML
	static boolean MakeXML = false;
	static boolean Characters = true;
	static boolean Skill = false;
	static boolean Interactive = true;

	static ActionManager actionManager;
	static CharacterManager playerManager;
	static SkillManager skillManager;
	static AdvantageManager advantageManager;
	static TurnManager turnManager;

	public static void MainMenu() {


		playerManager = new CharacterManager();

		skillManager = new SkillManager(new File("skills.xml"));
		
		advantageManager = new AdvantageManager();
		advantageManager.GenerateAdvantages(new File("advantages.xml"));

		actionManager = new ActionManager(skillManager, advantageManager);

		turnManager = new TurnManager(actionManager);

		System.out.println("MAIN MENU\n\n"
				+ "1. Look up Skills\n"
				+ "2. Look up Advantages\n"
				+ "3. Make Characters\n"
				+ "4. Load Characters\n"
				+ "5. Begin play\n\n"
				+ "Type 'exit' to close.\n");

		Scanner in = new Scanner(System.in);
		
		String line;
		while(!(line = in.nextLine()).equals("exit")) {
			System.out.println(line);
			switch(line) {
			case "1":
				SkillMenu(in);
				break;
			case "2":
				AdvantageMenu(in);
				break;
			case "3": 
				CharacterMaker(in);
				break;
			case "4": 
				CharacterMenu(in);
				break;
			case "5":
				PrototypeSkillCheck(in);
				break;
			}
			
			System.out.println("MAIN MENU\n\n"
					+ "1. Look up Skills\n"
					+ "2. Look up Advantages\n"
					+ "3. Make Characters\n"
					+ "4. Load Characters\n"
					+ "5. Begin play\n\n"
					+ "Type 'exit' to close.\n");
		}
		in.close();
		

	}

	private static void SkillMenu(Scanner in)
	{
		System.out.println("SKILL MENU\n");
		String[] fields = {"dificulty", "description", "attribute", "default", "TL"};
		System.out.println("Please enter skill of interest and field(s) of interest OR type 'exit' to leave");
		
		System.out.print("Valid fields are: '" + fields[0] + "'");
		for(int i = 1; i < fields.length; i++) {
			System.out.print(", '" + fields[i] + "'");
		}
		System.out.println("\n'Skill Name' Field1 Field2 . . . FieldN");
		
		String line;
		while(!(line = in.nextLine()).equals("exit")) {		
			line = line.toLowerCase();
			String[] parts = line.split(" ");
			
			int nameEnd = 1;
			String skill = parts[0];
			for(int i = 1; i < parts.length; i++) {
				if(utilities.Utilities.isIn(parts[i], fields)) {
					nameEnd = i;
					break;
				}
				skill += " " + parts[i];
			}
			
			String name = utilities.Utilities.formatSkillName(skill);
			System.out.println(name + ":");
			for (int i = nameEnd; i < parts.length; i++) {
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
		
		System.out.println("Describe the basic appearance of the character in one line.");
		if(!(line = in.nextLine()).equals("exit")) {
			newChara.setAppearance(line);
		} else
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
		
		System.out.println("Enter a language, writen, and spoken proficiency (None, Broken, Accented, Native).");
		System.out.println("Example: English native nAtIVe");
		while(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			newChara.addLanguage(parts[0], parts[1], parts[2]);
			System.out.println("Enter another language or type 'exit'");
		}
		
		System.out.println("Add any cultures other than your character's culture that they are familiar with or type exit.");
		while(!(line = in.nextLine()).equals("exit")) {
			newChara.addCulture(line);
			System.out.println("Enter another culture or type 'exit'");
		}
		
		System.out.println("Enter how overwhelmingly sexy your character is to those attracted to you. (-6 to 8).");
		int interest = 0;
		int other = 0;
		if(!(line = in.nextLine()).equals("exit")) {
			interest = Integer.parseInt(line);
		} else
			return;
		
		System.out.println("Now enter how absolutely repulsive your character is to those who aren't yet into you.(-6 to 8).");
		if(!(line = in.nextLine()).equals("exit")) {
				other = Integer.parseInt(line);
		} else
			return;
		
		newChara.setAppearanceMod(interest, other);
		
		System.out.println("Now enter their advantages. One per line. As always, 'exit' to finish advantage selection.");
		while(!(line = in.nextLine()).equals("exit")) {
			String name = utilities.Utilities.formatSkillName(line);
			
			if(advantageManager.isAdvantage(name)) {
				newChara.addAdvantage(name);
			}
			
		}
		
		
		System.out.println("Now enter their skills and their relative level in that skill, one at a time.\n"
				+ "As an example, 'First Aid -1'. Type 'exit' to finish character creation.");
		while(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			String skill = parts[0];
			
			for (int i = 1; i < parts.length - 1; i++) {
				skill += " " + parts[i];
			}

			int modifier = Integer.parseInt(parts[parts.length - 1]);
			String name = utilities.Utilities.formatSkillName(skill);
			
			if(parts.length > 1)
			{
				if(skillManager.isSkill(name)) {
					if(meetsRequirements(newChara, name)) {
						if(skillManager.canSpec(name) && skillManager.mustSpec(name)) {
							System.out.println("You must specialize in one of the following. Enter the index of the desired specialty.");
							String[] specs = skillManager.getSpecialties(name);
							for(int i = 0; i < specs.length; i++) {
								// People don't like zeroes so I'll increment the index just for display
								System.out.println("\t" + (i + 1) + ": " + specs[i]);
							}
							
							int choice;
							
							while(true) {
								line = in.nextLine();
								choice = Integer.parseInt(line);
								if (choice > 0 && choice <= specs.length) {
									// Then I'll decrement it back to proper index
									choice--;
									break;
								} else {
									System.out.println("Invalid. Please try again.");
								}
							}
							
							System.out.println("Adding skill: " + name + " (" + specs[choice] + ") at: +" + modifier);
							newChara.addSkill(name + " | " + specs[choice], modifier);
							
							
							
						} else {
							newChara.addSkill(name, modifier);						
						}
					}
					
					// Need to skip this is prerequisite is not met
					
				} else {
					System.out.println(name + " is not a skill");
				}
			}
		}
		
		playerManager.AddCharacter(newChara);
		playerManager.toXMLFile(newChara);
	}
	
	private static boolean meetsRequirements(Character newChara, String name) {
		List<Prerequisite> pres = skillManager.getSkill(name).getPrerequisite();
		List<Prerequisite> unmet = new ArrayList<Prerequisite>();
		if(!pres.isEmpty()) {
			for(Prerequisite pre : pres) {
				if( ! newChara.has(pre.getName(), pre.getMinimum())) {
					unmet.add(pre);
				}
			}
		}
		
		if(! unmet.isEmpty()) {
			System.out.print("You require " + unmet.get(0).getName());
			if(unmet.get(0).getMinimum() > 0)
				System.out.print(" at " + unmet.get(0).getMinimum());
			
			for(int i = 1; i < unmet.size(); i++) {
				if(i == unmet.size() - 1) {
					System.out.print(", and " + unmet.get(i).getName());
					if(unmet.get(i).getMinimum() > 0)
						System.out.print(" at " + unmet.get(i).getMinimum());
				} else {
					System.out.print(", " + unmet.get(i).getName());
					if(unmet.get(i).getMinimum() > 0)
						System.out.print(" at " + unmet.get(i).getMinimum());
				}
			}
			
			System.out.println(" to have this skill.");
			return false;
		}
		
		return true;
	}
	
	private static void AdvantageMenu(Scanner in)
		{
			System.out.println("ADVANTAGE MENU\n");
			String[] fields = {"cost", "description", "nums"};
			System.out.println("Please enter advantage of interest and field(s) of interest OR type 'exit' to leave.");
			System.out.print("Valid fields are: '" + fields[0] + "'");
			for(int i = 1; i < fields.length; i++) {
				System.out.print(", '" + fields[i] + "'");
			}
			System.out.println("\n'Advantage Name' Field1 Field2 . . . FieldN");
	
			String line;
			while(!(line = in.nextLine()).equals("exit")) {
				String[] parts = line.split(" ");
				
				int nameEnd = 1;
				String advantage = parts[0];
				for(int i = 1; i < parts.length; i++) {
					if(utilities.Utilities.isIn(parts[i], fields)) {
						nameEnd = i;
						break;
					}
					advantage += " " + parts[i];
				}
				
				
				String name = utilities.Utilities.formatSkillName(advantage);
				System.out.println(name + ":");
				for (int i = nameEnd; i < parts.length; i++) {
					System.out.println("\t" + parts[i] + ": " + advantageManager.request(name, parts[i]));
				}
			}
		}

	private static void PrototypeSkillCheck(Scanner in)
	{
		System.out.println("SKILL CHECK\n");

		System.out.println("Which character do you want to do a skill check on?");

		String line;

		if(!(line = in.nextLine()).equals("exit")) {
			String[] parts = line.split(" ");
			Character characterUnderTest = playerManager.getCharacter(parts[0]);

			turnManager.addTurn(characterUnderTest);

			turnManager.runTurn(characterUnderTest);

			turnManager.clearTurnList();
		}
		else
			return;
	}

}
