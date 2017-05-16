package skillManager;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.text.WordUtils;

import dataContainers.CharacterFields.Skill;
import dataContainers.CharacterFields.SkillDefault;

@XmlRootElement
public class SkillManager {

	@XmlElementWrapper(name = "skillList")
	private Map<String, Skill> skillMap;
	
	public SkillManager() {
		skillMap = new HashMap<String, Skill>();
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
		skillName = formatSkillName(skillName);
		element = element.toLowerCase();
		
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
	
	public Boolean isSkill(String skillName) {
		skillName = formatSkillName(skillName);
		
		return skillMap.containsKey(skillName);
	}
	
	private String formatSkillName(String unformated) {
		String[] components = unformated.split("-");
		String skillName = WordUtils.capitalizeFully(components[0]);
		
		for(int i = 1; i < components.length; i++) {
			skillName += "-" + WordUtils.capitalizeFully(components[i]);
		}
		
		System.out.println(skillName);
		return skillName;
		
	}
}
