package dataContainers.CharacterFields.ReactionModifiers;

import javax.xml.bind.annotation.XmlAttribute;

public class StatusModifier extends ReactionModifier {

	@XmlAttribute
	private String context;
	@XmlAttribute
	private int statusValue;
	
	public StatusModifier()
	{
		context = "NaRM";
		statusValue = -1;
	}
	
	public StatusModifier(String statusContext, int status)
	{
		this.context = statusContext;
		this.statusValue = status;
	}
	
	public String toString()
	{
		return "As a " + context + " this character has a status of " + statusValue;
	}

	public ReactionType getReactionType() {
		return ReactionType.STATUS;
	}
}
