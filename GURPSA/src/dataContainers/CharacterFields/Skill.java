package dataContainers.CharacterFields;

import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlEnum;


@XmlAccessorType(XmlAccessType.FIELD)
public class Skill {
	private String name;
	private BaseAttribute attr;
	private Difficulty diff;
	private boolean TL;
	private Vector<SkillDefault> skillDefault;
	private String desc;
	
	public Skill(String name, BaseAttribute baseAttribute, Difficulty difficulty, boolean TL, Vector<SkillDefault> skillDefault, String description)
	{
		this.name = name;
		this.attr = baseAttribute;
		this.diff = difficulty;
		this.TL = TL;
		this.skillDefault = skillDefault;
		this.desc = description;
	}
	
	public Skill()
	{
		this.name = "";
		this.attr = BaseAttribute.DX;
		this.diff = Difficulty.WILD_CARD;
		this.TL = false;
		this.skillDefault = new Vector<SkillDefault>();
		this.desc = "";
	}

//	@XmlElement(name = "name")
	public void setName(String name)
	{
		this.name = name;
	}
	
//	@XmlElement(name = "diff")
	public void setDifficulty(Difficulty difficulty)
	{
		this.diff = difficulty;
	}
	
//	@XmlElement(name = "attr")
	public void setBaseAttribute(BaseAttribute baseAttribute)
	{
		this.attr = baseAttribute;
	}
	
//	@XmlElement(name = "TL")
	public void setTLDependancy(boolean TL)
	{
		this.TL = TL;
	}
	
//	@XmlElement(name = "default")
	public void setDefaults(Vector<SkillDefault> skillDefault)
	{
		this.skillDefault = skillDefault;
	}
	
//	@XmlElement(name = "desc")
	public void setDescription(String description)
	{
		this.desc = description;
	}
	
	public Difficulty getDifficulty()
	{
		return diff;
	}
	
	public String getName()
	{
		return name;
	}

	public BaseAttribute getBaseAttribute() {
		return attr;
	}
	
	public boolean getTLDependancy()
	{
		return TL;
	}
	
	public List<SkillDefault> getDefaults()
	{
		return skillDefault;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	@XmlEnum
	public enum Difficulty {
		EASY, AVERAGE, HARD, VERY_HARD, WILD_CARD
	}
	
	@XmlEnum
	public enum BaseAttribute {
		ST, DX, IQ, HT, Will, PER
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
