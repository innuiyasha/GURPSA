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
			skillMap.put("Acrobatics", new Skill("Acrobatics", "DX", "Hard", false, tempList1, "showoff skill"));
			
			Vector<SkillDefault> tempList2 = new Vector<SkillDefault>(1,1);
			tempList2.add(new SkillDefault("DX",5));
			skillMap.put("Bow", new Skill("Bow", "DX", "Average", false, tempList2, "legolas"));
			
			Vector<SkillDefault> tempList3 = new Vector<SkillDefault>(0,1);
			skillMap.put("Brawling", new Skill("Brawling", "DX", "Easy", false, tempList3, "punching, but not very good"));
			
			Vector<SkillDefault> tempList4 = new Vector<SkillDefault>(4,1);
			tempList4.add(new SkillDefault("IQ",4));
			tempList4.add(new SkillDefault("Esoteric Medicine",0));
			tempList4.add(new SkillDefault("Physician",0));
			tempList4.add(new SkillDefault("Veterinary",4));
			
			skillMap.put("First Aid", new Skill("First Aid", "IQ", "Easy", false, tempList4, "you will be bullied"));
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
			output += pair.getKey() + " corresponds to " + pair.getValue().getName() + " at difficulty " 
					+ pair.getValue().getBaseAttribute() + "/" + pair.getValue().getDifficulty() + "\r\n";
		}
		return output;
	}

	public void displaySkills() {
		System.out.println(toString());
	}
	
	private String getDifficulty(String name) {
		return skillMap.get(name).getDifficulty();
	}
	
	private String getDescription(String name) {
		return skillMap.get(name).getDescription();
	}
	
	private String getTL(String name) {
		return "" + skillMap.get(name).getTLDependancy();
	}
	
	private String getAttribute(String name) {
		return skillMap.get(name).getBaseAttribute();
	}
	
	private String getDefault(String name) {
		List<SkillDefault> list = skillMap.get(name).getDefaults();
		if(list != null) {
			String defaults = "\n\t\t";
			for (SkillDefault entry : list) {
				defaults += entry.getName() + " at -" + entry.getPenalty() + "\n\t\t";
			}
			return defaults;
		} else {
			return "No defaults";
		}
	}
	
	public String request(String skillName, String element) {
		switch(element) {
		case "difficulty":
			return getDifficulty(skillName);
		case "description": 
			return getDescription(skillName);
		case "attribute": 
			return getAttribute(skillName);
		case "default": 
			return getDefault(skillName);
		case "TL":
			return getTL(skillName);
		default:
			return "Invalid Entry: " + element;
		}
	}
}
