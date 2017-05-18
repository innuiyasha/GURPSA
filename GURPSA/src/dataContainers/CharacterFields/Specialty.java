package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Specialty {
	
	String name;
	
	public Specialty()
	{
		this.name = "";
	}
	
	public Specialty(String name)
	{
		this.name = name;
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
	
	public String toString()
	{
		return name;
	}
}
