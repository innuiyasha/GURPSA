package dataContainers.CharacterFields;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CharactersSkill {

	private Skill skill;
	private int relativeLevel;
	//Needs ENUM for default base attribute
	
	public CharactersSkill(String name, int relLevel, Skill.BaseAttribute baseAttribute, Skill.Difficulty difficulty)
	{
		this.skill = new Skill(name, baseAttribute, difficulty);
		this.relativeLevel = relLevel;
	}
	
	public CharactersSkill()
	{
		this.skill = new Skill();
		this.relativeLevel = -99;
	}

//	@XmlElement
	public void setName(String name)
	{
		this.skill.setName(name);
	}
	
//	@XmlElement
	public void setLevel(int level)
	{
		this.relativeLevel = level;
	}
	
	public String getName()
	{
		return skill.getName();
	}
	
	public int getLevel()
	{
		return relativeLevel;
	}
	
	public Skill.Difficulty getDifficulty()
	{
		return skill.getDifficulty();
	}
	
	public Skill.BaseAttribute getBaseAttribute()
	{
		return skill.getBaseAttribute();
	}
	
}
