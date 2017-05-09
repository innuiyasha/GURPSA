package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;


public class Language {

	@XmlAttribute
	private String languageName;
	@XmlAttribute
	private Fluency written;
	@XmlAttribute
	private Fluency spoken;
	
	public Language()
	{
		languageName = "NaL";
		written = Fluency.NONE;
		spoken = Fluency.NONE;
	}
	
	public Language(String languageName, Fluency written, Fluency spoken)
	{
		this.languageName = languageName;
		this.written = written;
		this.spoken = spoken;
	}
	
	public String toString()
	{
		String tempString = "";
		tempString += languageName + ". Spoken proficiency: ";
		
		if(spoken == Fluency.NATIVE)
			tempString += "Native.";
		else if(spoken == Fluency.ACCENTED)
			tempString += "Accented.";
		else if(spoken == Fluency.BROKEN)
			tempString += "Broken.";
		else
			tempString += "None.";
		
		tempString += " Written proficiency: ";
		
		if(written == Fluency.NATIVE)
			tempString += "Native.";
		else if(written == Fluency.ACCENTED)
			tempString += "Accented.";
		else if(written == Fluency.BROKEN)
			tempString += "Broken.";
		else
			tempString += "None.";
		return tempString;
	}
	

	@XmlEnum
	public enum Fluency {
		NONE,BROKEN,ACCENTED,NATIVE
	}
	
}
