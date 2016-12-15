package dataContainers.CharacterFields;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlEnum;


//@XmlAccessorType(XmlAccessType.FIELD)
public class Skill {
	private String name;
	private Difficulty difficulty;
	private BaseAttribute baseAttribute;
	
	public Skill(String name, BaseAttribute baseAttribute, Difficulty difficulty)
	{
		this.name = name;
		this.baseAttribute = baseAttribute;
		this.difficulty = difficulty;
	}
	
	public Skill()
	{
		this.name = "";
		this.difficulty = Difficulty.WILD_CARD;
	}

	@XmlAttribute
	public void setName(String name)
	{
		this.name = name;
	}
	
	@XmlAttribute
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}
	
	@XmlAttribute
	public void setBaseAttribute(BaseAttribute baseAttribute)
	{
		this.baseAttribute = baseAttribute;
	}
	
	public Difficulty getDifficulty()
	{
		return difficulty;
	}
	
	public String getName()
	{
		return name;
	}
	
	@XmlEnum
	public enum Difficulty {
		EASY, AVERAGE, HARD, VERY_HARD, WILD_CARD
	}
	
	@XmlEnum
	public enum BaseAttribute {
		ST, DX, IQ, HT, Will, PER
	}

	public BaseAttribute getBaseAttribute() {
		return baseAttribute;
	}
}
