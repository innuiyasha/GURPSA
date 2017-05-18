package skillManager;

import java.io.File;
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

import dataContainers.CharacterFields.Prerequisite;
import dataContainers.CharacterFields.Skill;
import dataContainers.CharacterFields.SkillDefault;
import dataContainers.CharacterFields.Specialty;

@XmlRootElement
public class SkillManager {

	@XmlElementWrapper(name = "skillList")
	private Map<String, Skill> skillMap;
	
	public SkillManager() {
		skillMap = new HashMap<String, Skill>();
	}
	
	public SkillManager(File file)
	{
		skillMap = new HashMap<String, Skill>();
		generateSkills(file);
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

	public void generateSkills(File XMLInput) {
		try {	
			JAXBContext jaxbContext = JAXBContext.newInstance(SkillManager.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			this.setMap(((SkillManager) jaxbUnmarshaller.unmarshal(XMLInput)).getMap());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		forwardSpecializations();	
	}
	
	private void forwardSpecializations() {
		Iterator<Entry<String, Skill>> i = skillMap.entrySet().iterator();
		
		while(i.hasNext())
			{
				Map.Entry<String, Skill> pair = (Entry<String, Skill>)i.next();
				
				List<Specialty> specs = pair.getValue().getSpecialties();
				
				if(!specs.isEmpty()) {
					Skill genSkill = pair.getValue();
					for(Specialty spec : specs) {
						Skill specSkill = skillMap.get(pair.getKey() + " | " + spec.getName());
						
						if(specSkill.getBaseAttribute() == "") {
							specSkill.setBaseAttribute(genSkill.getBaseAttribute());
						}
						
						if(specSkill.getDifficulty() == "") {
							specSkill.setDifficulty(genSkill.getDifficulty());
						}
						
						if(specSkill.getTLDependancy() == false) {
							specSkill.setTLDependancy(genSkill.getTLDependancy());
						}
						
						specSkill.getDefaults().addAll(genSkill.getDefaults());
					}
				}
				
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
	
	private String getSpec(String skillName) {
		Skill skill = skillMap.get(skillName);
		List<Specialty> ls = skill.getSpecialties();
		
		if( !ls.isEmpty()) {
			String returning;
			if(skill.getSpecFlag()) {
				returning = "Must Specialize";
			} else {
				returning = "May Specialize";
			}
			
			for(Specialty spec : ls) {
				returning += "\n\t" + spec.getName();
			}
			
			return returning;
			
		} else {
			return "Cannot Specialize";
		}
	}
	
	public String[] getSpecialties(String skillName) {
		List<Specialty> ls = skillMap.get(skillName).getSpecialties();
		
		String[] specs = new String[ls.size()];
	
		for(int i = 0; i < ls.size(); i++) {
			specs[i] = ls.get(i).getName();
		}
		
		return specs;
	}
	
	public String getRequirements(String skillName) {
		List<Prerequisite> ls = skillMap.get(skillName).getPrerequisite();
		
		String reqs = "";
		
		if(ls.size() > 0) {
			reqs += ls.get(0).getName();
		}
		
		for(int i = 1; i < ls.size(); i++) {
			reqs += ", " + ls.get(i).getName();
		}
		
		return reqs;
	}
	
	public Boolean canSpec(String skillName) {
		return ! skillMap.get(skillName).getSpecialties().isEmpty();
	}
	
	public Boolean mustSpec(String skillName) {
		return skillMap.get(skillName).getSpecFlag();
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
		case "specialize":
			return getSpec(skillName);
		case "requirement":
			return getRequirements(skillName);
		default:
			return "Invalid Entry: " + element;
		}
	}
	
	public Boolean isSkill(String skillName) {
		skillName = formatSkillName(skillName);
		
		return skillMap.containsKey(skillName);
	}
	
	public Skill getSkill(String skillName)
	{
		return skillMap.get(skillName);
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
