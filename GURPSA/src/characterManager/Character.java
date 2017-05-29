package characterManager;

import java.util.Vector;

import javax.xml.bind.annotation.*;

import dataContainers.CharacterFields.*;
import dataContainers.CharacterFields.ReactionModifiers.*;
import skillManager.SkillManager;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Skill.class, CharactersSkill.class})
public class Character {
	private String name, player, height, weight, appearance;
	private int SM, age;
	private int ST,DX,IQ,HT;
	private int HP,FP;
	private int Will,PER;
	private int TL;
	private float speed;
	private int move;

	//Appearance modifiers, statuses, reputations
	
	@XmlElementWrapper(name = "SkillList")
	@XmlElement(name = "PlayerSkill")
	private Vector<CharactersSkill> playerSkills;
	
	@XmlElementWrapper(name = "AdvantageList")
	@XmlElement(name = "Advantage")
	private Vector<Advantage> playerAdvantages;

	@XmlElementWrapper(name = "LanguageList")
	@XmlElement(name = "Language")
	private Vector<Language> playerLanguages;
	
	@XmlElementWrapper(name = "CultureList")
	@XmlElement(name = "Culture")
	private Vector<CulturalFamiliarity> playerCultures;
	
	/*
	@XmlElementWrapper(name = "ReactionModList")
	@XmlElement(name = "ReactionModifier")
	private Vector<ReactionModifier> playerReactionMods;*/

	@XmlElement(name = "AppearanceModifier")
	private AppearanceModifier playerAppearance;

	@XmlElementWrapper(name = "ReputationList")
	@XmlElement(name = "Reputation")
	private Vector<ReputationModifier> playerReputations;
	@XmlElementWrapper(name = "StatusList")
	@XmlElement(name = "Status")
	private Vector<StatusModifier> playerStatuses;
	
//This constructor is just for generating an XML file to test.
//We'll need much, much more awesome constructors soon.
	public Character()
	{
//		name = "Teresa";
		name = "";
//		player = "David Clare";
		player = "";
//		height = "5'8";
		height = "";
//		weight = "200lbs";
		weight = "";
//		appearance = "Purple hair??";
		appearance = "";
//		SM = 0;
		SM = 0;
//		age = 24;
		age = 0;
//		ST = 15;
		ST = 10;
//		DX = 12;
		DX = 10;
//		IQ = 8;
		IQ = 10;
//		HT = 15;
		HT = 10;
//		HP = 18;
		HP = 10;
//		FP = 18;
		FP = 10;
//		Will = 13;
		Will = 10;
//		PER = 6;
		PER = 10;
//		TL = 4;
		setTL(7);
//		speed = 7.0f;
		speed = 5.0f;
//		move = 7;
		move = 5;
//
		playerSkills = new Vector<CharactersSkill>();
//		
		playerAdvantages = new Vector<Advantage>();
//		playerAdvantages.add(new Advantage("Very Fit", "15 Points", "Placeholder for Very Fit description."));
//		playerAdvantages.add(new Advantage("Ambidexterity", "5 Points", "Placeholder for Ambidexterity description."));
//		playerAdvantages.add(new Advantage("Fast Swimmer (+1 basic move)", "5 Points", "Placeholder for increased Move Speed description."));
//		
		playerCultures = new Vector<CulturalFamiliarity>();
//		playerCultures.add(new CulturalFamiliarity("Human"));
//		playerCultures.add(new CulturalFamiliarity("Lynian"));
//		playerCultures.add(new CulturalFamiliarity("Wyverian"));
//		
		playerLanguages = new Vector<Language>();
//		playerLanguages.add(new Language("Human",Language.Fluency.NATIVE,Language.Fluency.NATIVE));
//		
		playerAppearance = new AppearanceModifier(0,0);
		playerReputations = new Vector<ReputationModifier>();
//		playerReputations.add(new ReputationModifier("Loc Lac Guild workers", +2, "recognized on a 10- roll."));
//		playerReputations.add(new ReputationModifier("Loc Lac Guild workers", -2, "recognized on a 7- roll."));
		
		playerStatuses = new Vector<StatusModifier>();
//		playerStatuses.add(new StatusModifier("Hunter",1));
		
	}

	//A liiiittle slower than it should be.
	public Vector<CharactersSkill> checkForAppropriateSkills(SkillManager skillManager, String skillName)
	{
		Vector<CharactersSkill> possibleSkills = new Vector<CharactersSkill>();
		
		
		Vector<SkillDefault> defaults = new Vector<SkillDefault>();
		
		defaults = (Vector<SkillDefault>) skillManager.getSkill(skillName).getDefaults();
		
		for(int i = 0 ; i < playerSkills.size() ; i++)
		{
			if(playerSkills.get(i).getSkillName().equals(skillName))
				possibleSkills.add(playerSkills.get(i));
			for(int j = 0 ; j < defaults.size(); j++)
			{
				if(playerSkills.get(i).getSkillName().equals(defaults.get(j).getName()))
					possibleSkills.add(new CharactersSkill(playerSkills.get(i).getSkillName(), playerSkills.get(i).getLevel() - defaults.get(j).getPenalty()));
			}
		}
		
		for(int i = 0 ; i < defaults.size(); i++)
		{
			if(defaults.get(i).getName().equals("DX") ||
					defaults.get(i).getName().equals("IQ") ||
					defaults.get(i).getName().equals("HT") ||
					defaults.get(i).getName().equals("Will") ||
					defaults.get(i).getName().equals("Per"))
				possibleSkills.add(new CharactersSkill(defaults.get(i).getName(), -1 * defaults.get(i).getPenalty()));
		}
		
		return possibleSkills;
	}
	
	public String getName()
	{
		return name;
	}

	public String getPlayer()
	{
		return player;
	}

	public String getHeight()
	{
		return height;
	}

	public String getWeight()
	{
		return weight;
	}

	public String getAppearance()
	{
		return appearance;
	}

	public int getSM()
	{
		return SM;
	}

	public int getAge()
	{
		return age;
	}

	public int getST()
	{
		return ST;
	}

	public int getDX()
	{
		return DX;
	}

	public int getIQ()
	{
		return IQ;
	}

	public int getHT()
	{
		return HT;
	}

	public int getHP()
	{
		return HP;
	}

	public int getFP()
	{
		return FP;
	}

	public int getWill()
	{
		return Will;
	}

	public int getPER()
	{
		return PER;
	}

	public float getSpeed()
	{
		return speed;
	}

	public int getMove()
	{
		return move;
	}

	public Vector<CharactersSkill> getSkills()
	{
		return playerSkills;
	}
	
	public void addSkill(String skillName, int relativeLevel)
	{
		this.playerSkills.addElement(new CharactersSkill(skillName, relativeLevel));
	}
	
	public void setSkills(Vector<CharactersSkill> newSkills)
	{
		this.playerSkills = newSkills;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPlayer(String player)
	{
		this.player = player;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public void setAppearance(String appearance)
	{
		this.appearance = appearance;
	}

	public void setSM(int SM)
	{
		this.SM = SM;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setST(int ST)
	{
		this.ST = ST;
	}

	public void setDX(int DX)
	{
		this.DX = DX;
	}

	public void setIQ(int IQ)
	{
		this.IQ = IQ;
	}

	public void setHT(int HT)
	{
		this.HT = HT;
	}

	public void setHP(int HP)
	{
		this.HP = HP;
	}

	public void setFP(int FP)
	{
		this.FP = FP;
	}

	public void setWill(int Will)
	{
		this.Will = Will;
	}

	public void setPER(int PER)
	{
		this.PER = PER;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public void setMove(int move)
	{
		this.move = move;
	}
//Just to help me test reading the XML file
	public String toString()
	{
		String output =  "Name: " + name + "\r\n";
		output += "Player: " + player + "\r\n";
		output += "Height: " + height + "\r\n";
		output += "Weight: " + weight + "\r\n";
		output += "Appearance: " + appearance + "\r\n";
		output += "SM: " + SM + "\r\n";
		output += "Age: " + age + "\r\n";
		output += "ST: " + ST + "\r\n";
		output += "DX: " + DX + "\r\n";
		output += "IQ: " + IQ + "\r\n";
		output += "HT: " + HT + "\r\n";
		output += "Will: " + Will + "\r\n";
		output += "PER: " + PER + "\r\n";
		output += "Basic Speed: " + speed + "\r\n";
		output += "Basic Move: " + move + "\r\n";
		for(int i = 0 ; i < playerSkills.size() ; i++)
			output += playerSkills.get(i).getSkillName() + " at " + playerSkills.get(i).getLevel() + "\r\n";

		for(int i = 0 ; i < playerAdvantages.size() ; i++)
			output += playerAdvantages.get(i).toString() + "\r\n";

		for(int i = 0 ; i < playerLanguages.size() ; i++)
			output += playerLanguages.get(i).toString() + "\r\n";

		for(int i = 0 ; i < playerCultures.size() ; i++)
			output += playerCultures.get(i).toString() + "\r\n";

		output += playerAppearance.toString() + "\r\n";
		
		for(int i = 0 ; i < playerReputations.size() ; i++)
			output += playerReputations.get(i).toString() + "\r\n";

		for(int i = 0 ; i < playerStatuses.size() ; i++)
			output += playerStatuses.get(i).toString() + "\r\n";
		
		return output;
	}


	public int getTL() {
		return TL;
	}


	public void setTL(int tL) {
		TL = tL;
	}
	
	
}
