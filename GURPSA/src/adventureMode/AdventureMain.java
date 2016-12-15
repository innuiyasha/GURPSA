package adventureMode;

import java.io.File;

import characterManager.CharacterManager;


public class AdventureMain {

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
