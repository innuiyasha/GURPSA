package adventureMode;

import java.io.File;
import java.util.Scanner;

import actionManager.ActionManager;
import advantageManager.AdvantageManager;
import characterManager.*;
import characterManager.Character;
import skillManager.SkillManager;
import turnManager.TurnManager;
import utilities.Utilities;

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

	public static void main(String[] args) {


		playerManager = new CharacterManager();

		skillManager = new SkillManager(new File("skills.xml"));
		
		advantageManager = new AdvantageManager();
		advantageManager.GenerateAdvantages(new File("advantages.xml"));

		actionManager = new ActionManager(skillManager, advantageManager);

		turnManager = new TurnManager(actionManager);

		System.out.println("MAIN MENU\n\n"
				+ "1. Look up Skills\n"
				+ "2. Look up Advantages\n"
				+ "3. Load Characters\n"
				+ "4. Make Characters\n"
				+ "5. Begin play\n\n"
				+ "Type 'exit' to close.\n");
		
		Scanner in = new Scanner(System.in);
		
		String line;
		while(!(line = in.nextLine()).equals("exit")) {
			
			switch(line) {
			case "1":
				SkillMenu(in);
				break;
			case "2":
				AdvantageMenu(in);
				break;
			case "3": 
				CharacterMenu(in);
				break;
			case "4": 
				CharacterMaker(in);
				break;
			case "5":
				PrototypeSkillCheck(in);
				break;
			}
			
			System.out.println("MAIN MENU\n\n"
					+ "1. Look up Skills\n"
					+ "2. Look up Advantages\n"
					+ "3. Load Characters\n"
					+ "4. Make Characters\n"
					+ "5. Begin play\n\n"
					+ "Type 'exit' to close.\n");
		}
		in.close();
		

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
			String name = utilities.Utilities.formatSkillName(parts[0]);
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
			String name = utilities.Utilities.formatSkillName(parts[0]);
			if(parts.length > 1)
			{
				if(skillManager.isSkill(name)) {
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
						
						
						int level = Integer.parseInt(parts[1]);
						
						System.out.println("Adding skill: " + name + " (" + specs[choice] + ") at: +" + level);
						newChara.addSkill(name + " | " + specs[choice], level);
						
						
						
					} else {
						newChara.addSkill(name, Integer.parseInt(parts[1]));						
					}
				} else {
					System.out.println(name + " is not a skill");
				}
			}
		}
		
		playerManager.AddCharacter(newChara);
		playerManager.toXMLFile(newChara);
	}
	
	private static void AdvantageMenu(Scanner in)
		{
			System.out.println("ADVANTAGE MENU\n");
	
			System.out.println("Please enter advantage of interest and field(s) of interest OR type 'exit' to leave\n"
					+ "If a multi-word skill, use periods rather than spaces\n"
					+ "Valid fields are: 'cost' 'description' 'nums'\n"
					+ "'AdvantageName' Field1 Field2 . . . FieldN");
	
			String line;
			while(!(line = in.nextLine()).equals("exit")) {
				String[] parts = line.split(" ");
				String name = utilities.Utilities.formatSkillName(parts[0]);
				System.out.println(name + ":");
				for (int i = 1; i < parts.length; i++) {
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
