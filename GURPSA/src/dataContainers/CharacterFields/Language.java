package dataContainers.CharacterFields;

import javax.xml.bind.annotation.XmlEnum;

public class Language {

	private String languageName;
	private Fluency written;
	private Fluency spoken;
	
	public Language(String languageName, Fluency written, Fluency spoken)
	{
		this.languageName = languageName;
		this.written = written;
		this.spoken = spoken;
	}
	

	@XmlEnum
	public enum Fluency {
		NONE,BROKEN,ACCENTED,NATIVE
	}
	
}
