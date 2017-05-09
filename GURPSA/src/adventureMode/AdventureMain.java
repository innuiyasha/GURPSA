package adventureMode;

import java.io.File;

import characterManager.CharacterManager;
import skillManager.SkillManager;

//This will one day be one of our most important classes, but for now I'm using this as a test main.
public class AdventureMain {

	//This is a toggle for read/write XML
	static boolean MakeXML = false;
	static boolean Characters = false;
	static boolean Skill = true;

	public static void main(String[] args) {

		if(Characters)
		{
			CharacterManager playerManager = new CharacterManager(MakeXML);
			if(MakeXML == true)
				playerManager.toXMLFile(playerManager.getCharacter(0));
			else
			{
				playerManager.AddCharacter(new File("defaultCharacter.xml"));
				playerManager.displayCharacters();
			}
		}
		if(Skill)
		{
			SkillManager skillManager = new SkillManager(MakeXML);
			if(MakeXML == true)
			{
				skillManager.toXMLFile();
			}
			else
			{
				skillManager.GenerateSkills(new File("skills.xml"));
				skillManager.displaySkills();
			}
		}
	}

}
