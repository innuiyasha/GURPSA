package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAttribute;

public class Advantage {

	@XmlAttribute
	String name;
	@XmlAttribute
	String description;
	@XmlAttribute
	String cost; //String isn't a useful data type for us. ENUM perhaps?
	//This is also likely where we're going to deal with archetypes.
	//This might need to be abstract or an interface or something
	
	public Advantage()
	{
		name = "NaA";
		description = "";
		cost = "";
	}
	
	public Advantage(String name, String cost, String description)
	{
		this.name = name;
		this.cost = cost;
		this.description = description;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getCost()
	{
		return cost;
	}
	
	public String toString()
	{
		return getName() + " which means: " + getDescription() + " This costs: " + getCost();
	}
}
