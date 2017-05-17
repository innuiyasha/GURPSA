package actionManager;

import characterManager.Character;
import skillManager.SkillManager;
import advantageManager.AdvantageManager;

public class ActionManager {

	SkillManager skillManager;
	AdvantageManager advantageManager;
	
	public ActionManager(SkillManager skillManager, AdvantageManager advantageManager)
	{
		this.skillManager = skillManager;
		this.advantageManager = advantageManager;
	}
	
	
	public void SkillCheck(Character character, String skillName)
	{
		System.out.println("Eyy we're in the skill check procedure");
	}
}
