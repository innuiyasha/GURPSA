package turnManager;

import actionManager.ActionManager;
import characterManager.Character;

public class Turn {

	Character initiator;
	ActionManager actionManager;

	public Turn(ActionManager actionManager, Character initiator)
	{
		this.actionManager = actionManager;
		this.initiator = initiator;
	}

	public String getCharacterName()
	{
		return initiator.getName();
	}
	
	public Character getCharacter()
	{
		return initiator;
	}
	
	public void skillCheck()
	{
			actionManager.SkillCheck(initiator);
	}
	
	public void skillCheck(String skillName)
	{
		actionManager.SkillCheck(initiator, skillName);
	}
	
	public void skillCheck(String skillName, int modifier)
	{
		actionManager.SkillCheck(initiator, skillName, modifier);
	}
	
	public void perceptionCheck()
	{
		actionManager.PerceptionCheck(initiator);
	}
	
}
