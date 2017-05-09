package dataContainers.CharacterFields;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CharactersSkill extends Skill {

	private int relativeLevel;
	
	public CharactersSkill(String name, String baseAttribute, String difficulty, int relLevel)
	{
		//super(name, baseAttribute, difficulty);
		this.relativeLevel = relLevel;
	}
	
	public CharactersSkill(Skill skill, int relLevel)
	{
		//super(skill.getName(), skill.getBaseAttribute(), skill.getDifficulty());
		this.relativeLevel = relLevel;
	}
	
	public CharactersSkill()
	{
		super();
		this.relativeLevel = -99;
	}

//	@XmlElement
	public void setLevel(int level)
	{
		this.relativeLevel = level;
	}
	
	
	public int getLevel()
	{
		return relativeLevel;
	}
	
}
