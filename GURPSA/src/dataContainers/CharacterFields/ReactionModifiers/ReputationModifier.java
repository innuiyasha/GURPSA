package dataContainers.CharacterFields.ReactionModifiers;

import javax.xml.bind.annotation.XmlAttribute;

public class ReputationModifier extends ReactionModifier {

	@XmlAttribute
	private String context;
	@XmlAttribute
	private int modifier;
	@XmlAttribute
	private String chanceOfRecog; //We may want a specific data type to suggest a roll.
	
	public ReputationModifier()
	{
		context = "NaRM";
		modifier = -99;
		chanceOfRecog = "NaR";
	}
	
	public ReputationModifier(String context, int modifier, String chanceOfRecog)
	{
		this.context = context;
		this.modifier = modifier;
		this.chanceOfRecog = chanceOfRecog;
	}
	
	public String toString()
	{
		return "Among " + context + " they have a reputation of " + modifier + " when " + chanceOfRecog;
	}
	
	public ReactionType getReactionType() {
		return ReactionType.REPUTATION;
	}

}
