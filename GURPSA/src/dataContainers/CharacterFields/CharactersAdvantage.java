package dataContainers.CharacterFields;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CharactersAdvantage {

	private String advantageName;
	
	public CharactersAdvantage(String name)
	{
		this.advantageName = name;
	}
	
	public CharactersAdvantage(Advantage advantage, int relLevel)
	{
		this.advantageName = advantage.getName();
	}
	
	public CharactersAdvantage()
	{
		this.advantageName = "Not a Skill";
	}
	
	public void setAdvantageName(String name)
	{
		this.advantageName = name;
	}
	
	public String getAdvantageName()
	{
		return advantageName;
	}
	
	public String toString()
	{
		return advantageName;
	}
	
}
