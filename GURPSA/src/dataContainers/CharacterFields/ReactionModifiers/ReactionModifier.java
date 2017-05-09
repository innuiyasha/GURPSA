package dataContainers.CharacterFields.ReactionModifiers;

import javax.xml.bind.annotation.XmlEnum;

public abstract class ReactionModifier {

	@XmlEnum
	public enum ReactionType {
		APPEARANCE, REPUTATION, STATUS
	}
	
	
}
