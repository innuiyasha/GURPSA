package skillManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import characterManager.Character;
import dataContainers.CharacterFields.CharactersSkill;
import dataContainers.CharacterFields.Skill;
import dataContainers.CharacterFields.Skill.BaseAttribute;
import dataContainers.CharacterFields.Skill.Difficulty;
import dataContainers.CharacterFields.SkillDefault;

@XmlRootElement
public class SkillManager {

	@XmlElementWrapper(name = "skillList")
	private Map<String, Skill> skillMap;
	
	public SkillManager()
	{
		skillMap = new HashMap<String, Skill>();
	}
	
	public SkillManager(boolean makeXML) {
		if(makeXML)
		{
			skillMap = new HashMap<String, Skill>();
			
			Vector<SkillDefault> tempList1 = new Vector<SkillDefault>(1,1);
			tempList1.add(new SkillDefault("DX",6));
			skillMap.put("Acrobatics", new Skill("Acrobatics", BaseAttribute.DX, Difficulty.HARD, false, tempList1, "showoff skill"));
			
			Vector<SkillDefault> tempList2 = new Vector<SkillDefault>(1,1);
			tempList2.add(new SkillDefault("DX",5));
			skillMap.put("Bow", new Skill("Bow", BaseAttribute.DX, Difficulty.AVERAGE, false, tempList2, "legolas"));
			
			Vector<SkillDefault> tempList3 = new Vector<SkillDefault>(0,1);
			skillMap.put("Brawling", new Skill("Brawling", BaseAttribute.DX, Difficulty.EASY, false, tempList3, "punching, but not very good"));
			
			Vector<SkillDefault> tempList4 = new Vector<SkillDefault>(4,1);
			tempList4.add(new SkillDefault("IQ",4));
			tempList4.add(new SkillDefault("Esoteric Medicine",0));
			tempList4.add(new SkillDefault("Physician",0));
			tempList4.add(new SkillDefault("Veterinary",4));
			
			skillMap.put("First Aid", new Skill("First Aid", BaseAttribute.IQ, Difficulty.EASY, false, tempList4, "you will be bullied"));
		}
		else
		{
			skillMap = new HashMap<String, Skill>();
		}
	}

	private Map<String,Skill> getMap()
	{
		return skillMap;
	}
	
	private void setMap(Map<String,Skill> newMap)
	{
		skillMap = newMap;
	}
	
	public void toXMLFile() {
		try {

			File file = new File("skillList.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(SkillManager.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(this, file);
			jaxbMarshaller.marshal(this, System.out);

		      } catch (JAXBException e) {
		    	  e.printStackTrace();
		      }
	}

	public void GenerateSkills(File XMLInput) {
		try {	
			JAXBContext jaxbContext = JAXBContext.newInstance(SkillManager.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			this.setMap(((SkillManager) jaxbUnmarshaller.unmarshal(XMLInput)).getMap());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		String output = "";
		
		Iterator<Entry<String, Skill>> i = skillMap.entrySet().iterator();
		while(i.hasNext())
		{
			Map.Entry<String, Skill> pair = (Entry<String, Skill>)i.next();
			output += pair.getKey() + " corresponds to " + pair.getValue().toString() + "\r\n";
		}
		return output;
	}

	public void displaySkills() {
		System.out.println(toString());
	}
}
