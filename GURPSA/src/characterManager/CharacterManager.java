package characterManager;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import dataContainers.CharacterFields.CharactersSkill;
import dataContainers.CharacterFields.Skill;

public class CharacterManager {

	Character[] players;
	
	//This needs to be changed later. Right now, it's a toggle between read/write the XML file.
	public CharacterManager(boolean MakeXML)
	{
		if(MakeXML)
		{
			players = new Character[1];
			players[0] = new Character();
		}
		else
			players = new Character[0];
	}
	
	//Generates a character from an XML file.
	public Character makeCharacter(File XMLInput)
	{
		Character character = null;
		try {	
			JAXBContext jaxbContext = JAXBContext.newInstance(Character.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			character = (Character) jaxbUnmarshaller.unmarshal(XMLInput);
			//System.out.println(character);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return character;
	}
	
	//Adds a character to the manager's list, using the function above
	public void AddCharacter(File file)
	{
		Character[] tempPlayers = new Character[players.length + 1];
		for(int i = 0 ; i < players.length ; i++)
			tempPlayers[i] = players[i];
		tempPlayers[players.length] = makeCharacter(file);
		players = tempPlayers;
	}
	
	public Character getCharacter(int i)
	{
		return players[i];
	}
	
	//Turns a character into an XML file. Currently overwrites a single file.
	public void toXMLFile(Character character)
	{
		try {

			File file = new File("defaultCharacter.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Character.class,CharactersSkill.class,Skill.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(character, file);
			jaxbMarshaller.marshal(character, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
	}

	public void displayCharacters() {
		for(int i = 0 ; i < players.length ; i++)
		{
			System.out.println(players[i].toString());
		}
		
	}
}

