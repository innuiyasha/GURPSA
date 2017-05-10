package adventureMode;

import java.io.File;
import java.util.Scanner;

import characterManager.CharacterManager;
import skillManager.SkillManager;

//This will one day be one of our most important classes, but for now I'm using this as a test main.
public class AdventureMain {

	//This is a toggle for read/write XML
	static boolean MakeXML = false;
	static boolean Characters = true;
	static boolean Skill = false;
	static boolean Interactive = true;

	public static void main(String[] args) {

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
		}
	}

}
