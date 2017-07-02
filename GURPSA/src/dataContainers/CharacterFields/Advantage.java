package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Advantage {

	String name;
	String desc;
	String stat/*cost*/;
	String nums; //Identifying nums
	
	public Advantage()
	{
		name = "NaA";
		desc = "";
		stat = "";
		nums = "0";
	}
	
	public Advantage(String name, String stat, String description, String nums)
	{
		this.name = name;
		this.stat = stat;
		this.desc = description;
		this.nums = nums;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public String getCost()
	{
		return stat;
	}
	
	public String getNums()
	{
		return nums;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDescription(String desc)
	{
		this.name = desc;
	}
	
	public void setCost(String stat)
	{
		this.stat = stat;
	}
	
	public void setNums(String nums)
	{
		this.nums = nums;
	}
	
	public String toString()
	{
		return getName() + " which means: " + getDescription() + " This costs: " + getCost();
	}
}
