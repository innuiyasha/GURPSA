package dataContainers.CharacterFields;

import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class Skill {
	private String name;
	private String attr;
	private String diff;
	private boolean mustSpec;
	private boolean TL;
	private Vector<SkillDefault> skillDefault;
	private Vector<Specialty> skillSpecialty;
	private Vector<Prerequisite> skillPrerequisite;
	private String desc;
	
	public Skill(String name, String baseAttribute, String difficulty, boolean TL, Vector<SkillDefault> skillDefault, Vector<Specialty> skillSpecialty, String description, boolean mustSpec)
	{
		this.name = name;
		this.attr = baseAttribute;
		this.diff = difficulty;
		this.TL = TL;
		this.mustSpec = mustSpec;
		this.skillDefault = skillDefault;
		this.skillSpecialty = skillSpecialty;
		this.desc = description;
	}
	
	public Skill(String name, String baseAttribute, String difficulty, boolean TL, Vector<SkillDefault> skillDefault, String description)
	{
		this.name = name;
		this.attr = baseAttribute;
		this.diff = difficulty;
		this.TL = TL;
		this.mustSpec = false;
		this.skillSpecialty = new Vector<Specialty>();
		this.skillDefault = skillDefault;
		this.desc = description;
	}
	
	public Skill()
	{
		this.name = "";
		this.attr = "";
		this.diff = "";
		this.TL = false;
		this.mustSpec = false;
		this.skillSpecialty = new Vector<Specialty>();
		this.skillDefault = new Vector<SkillDefault>();
		this.skillPrerequisite = new Vector<Prerequisite>();
		this.desc = "";
	}

//	@XmlElement(name = "name")
	public void setName(String name)
	{
		this.name = name;
	}
	
//	@XmlElement(name = "diff")
	public void setDifficulty(String difficulty)
	{
		this.diff = difficulty;
	}
	
//	@XmlElement(name = "attr")
	public void setBaseAttribute(String baseAttribute)
	{
		this.attr = baseAttribute;
	}
	
//	@XmlElement(name = "TL")
	public void setTLDependancy(boolean TL)
	{
		this.TL = TL;
	}
	
//	@XmlElement(name = "mustSpec")
	public void setSpecializationRequirement(boolean mustSpec)
	{
		this.mustSpec = mustSpec;
	}
	
//	@XmlElement(name = "default")
	public void setDefaults(Vector<SkillDefault> skillDefault)
	{
		this.skillDefault = skillDefault;
	}
	
//	@XmlElement(name = "specialty")
	public void setSpecialties(Vector<Specialty> skillSpecialty)
	{
		this.skillSpecialty = skillSpecialty;
	}
	
//	@XmlElement(name = "prerequisite")
	public void setPrerequisites(Vector<Prerequisite> skillPrerequisite)
	{
		this.skillPrerequisite = skillPrerequisite;
	}
	
//	@XmlElement(name = "desc")
	public void setDescription(String description)
	{
		this.desc = description;
	}
	
	public String getDifficulty()
	{
		return diff;
	}
	
	public String getName()
	{
		return name;
	}

	public String getBaseAttribute() {
		return attr;
	}
	
	public boolean getTLDependancy()
	{
		return TL;
	}
	
	public boolean getSpecFlag()
	{
		return mustSpec;
	}
	
	public List<SkillDefault> getDefaults()
	{
		return skillDefault;
	}
	
	public List<Specialty> getSpecialties()
	{
		return skillSpecialty;
	}
	
	public List<Prerequisite> getPrerequisite()
	{
		return skillPrerequisite;
	}
	
	public String getDescription()
	{
		return desc;
	}

	public String toString()
	{
		String tempString = name + " " + attr + "/" + diff + ". " + desc + ". Its defaults are ";
		if(!skillDefault.isEmpty())
			for(int i = 0 ; i < skillDefault.size() ; i++)
			{
				tempString = tempString.concat(skillDefault.get(i).toString() + " ");
			}
		return tempString;
	}
}
