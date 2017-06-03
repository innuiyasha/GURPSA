package characterManager;

import java.io.File;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import dataContainers.CharacterFields.CharactersSkill;
import dataContainers.CharacterFields.Skill;

public class CharacterManager {

	Vector<Character> players;
	
	//This needs to be changed later. Right now, it's a toggle between read/write the XML file.
	public CharacterManager()
	{
		players = new Vector<Character>();
		File folder = new File("GURPSA\\characters");
		for(File file : folder.listFiles()) {
			AddCharacter(file);
		}
	}
	
	//Generates a character from an XML file.
	public Character makeCharacter(File XMLInput)
	{
		Character character = null;
		try {	
			JAXBContext jaxbContext = JAXBContext.newInstance(Character.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			character = (Character) jaxbUnmarshaller.unmarshal(XMLInput);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return character;
	}
	
	//Adds a character to the manager's list, using the function above
	public void AddCharacter(File file)
	{
		players.add(makeCharacter(file));
	}
	
	public void AddCharacter(Character newChara)
	{
		players.add(newChara);
	}
	
	public Character getCharacter(int i)
	{
		return players.get(i);
	}
	
	public Vector<Character> getCharacters()
	{
		return players;
	}
	
	public Character getCharacter(String name)
	{
		for(int i = 0 ; i < players.size() ; i++)
		{
			if(players.get(i).getName().equals(name))
				return players.get(i);
		}
		return null;
	}
	
	//Turns a character into an XML file. Currently overwrites a single file.
	public void toXMLFile(Character character)
	{
		try {

			//File file = new File("defaultCharacter.xml");
			File file = new File("GURPSA\\characters\\" + character.getName() + ".xml");
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
		for(int i = 0 ; i < players.size() ; i++)
		{
			System.out.println(players.get(i).toString());
		}
		
	}
}

