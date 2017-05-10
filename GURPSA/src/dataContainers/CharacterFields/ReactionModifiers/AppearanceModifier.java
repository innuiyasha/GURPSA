package dataContainers.CharacterFields.ReactionModifiers;

import javax.xml.bind.annotation.XmlAttribute;

public class AppearanceModifier extends ReactionModifier {

	@XmlAttribute
	private int appropriateSex;
	@XmlAttribute
	private int otherSex;
	
	public AppearanceModifier()
	{
		appropriateSex = -99;
		otherSex = -99;
	}
	
	public AppearanceModifier(int appropriateSex, int otherSex)
	{
		this.appropriateSex = appropriateSex;
		this.otherSex = otherSex;
	}
	
	public String toString()
	{
		return "People attracted to the appropriate sex react at " + appropriateSex + 
				". People attracted to the other sex react at " + otherSex;
	}
	
	public ReactionType getReactionType() {
		return ReactionType.APPEARANCE;
	}

}
