package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class SkillDefault {
	
	String name;
	int penalty;
	
	public SkillDefault()
	{
		this.name = "";
		this.penalty = 0;
	}
	
	public SkillDefault(String name, int penalty)
	{
		this.name = name;
		this.penalty = penalty;
	}
	
//	@XmlElement(name = "name")
	public void setName(String name)
	{
		this.name = name;
	}
	
//	@XmlElement(name = "penalty")
	public void setPenalty(int penalty)
	{
		this.penalty = penalty;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPenalty()
	{
		return penalty;
	}
	
	public String toString()
	{
		return name + "-" + penalty;
	}
}
