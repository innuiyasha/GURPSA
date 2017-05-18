package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Prerequisite {
	
	String name;
	int minimum;
	
	public Prerequisite()
	{
		this.name = "";
		this.minimum = -1;
	}
	
	public Prerequisite(String name, int minimum)
	{
		this.name = name;
		this.minimum = minimum;
	}
	
//	@XmlElement(name = "name")
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
//	@XmlElement(name = "minimum")
	public void setMinimum(int minimum)
	{
		this.minimum = minimum;
	}
	
	public int getMinimum()
	{
		return minimum;
	}
	
	public String toString()
	{
		return name;
	}
}
