package adventureMode;

import java.io.File;

import characterManager.CharacterManager;

//This will one day be one of our most important classes, but for now I'm using this as a test main.
public class AdventureMain {

//This is a toggle for read/write XML
static boolean MakeXML = false;

	public static void main(String[] args) {
		CharacterManager playerManager = new CharacterManager(MakeXML);
		if(MakeXML == true)
			playerManager.toXMLFile(playerManager.getCharacter(0));
		else
		{
			playerManager.AddCharacter(new File("defaultCharacter.xml"));
			playerManager.displayCharacters();
		}
	}

}
