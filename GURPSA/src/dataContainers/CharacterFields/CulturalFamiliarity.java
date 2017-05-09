package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAttribute;

public class CulturalFamiliarity {

	@XmlAttribute
	private String cultureName;
	
	public CulturalFamiliarity()
	{
		cultureName = "NaC";
	}
	
	public CulturalFamiliarity(String cultureName)
	{
		this.cultureName = cultureName;
	}
	
	public String toString()
	{
		return "Familiar with " + cultureName;
	}
}
