package dataContainers.CharacterFields;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CharactersSkill {

	private String skillName;
	private int relativeLevel;
	
	public CharactersSkill(String name, int relLevel)
	{
		this.skillName = name;
		this.relativeLevel = relLevel;
	}
	
	public CharactersSkill(Skill skill, int relLevel)
	{
		this.skillName = skill.getName();
		this.relativeLevel = relLevel;
	}
	
	public CharactersSkill()
	{
		this.skillName = "Not a Skill";
		this.relativeLevel = -99;
	}

//	@XmlElement
	public void setLevel(int level)
	{
		this.relativeLevel = level;
	}
	
	public void setSkillName(String name)
	{
		this.skillName = name;
	}
	
	public String getSkillName()
	{
		return skillName;
	}
	
	public int getLevel()
	{
		return relativeLevel;
	}
	
}
