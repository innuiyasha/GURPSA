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

