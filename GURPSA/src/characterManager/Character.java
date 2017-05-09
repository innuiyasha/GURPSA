package characterManager;

import java.util.Vector;

import javax.xml.bind.annotation.*;

import dataContainers.CharacterFields.*;
import dataContainers.CharacterFields.ReactionModifiers.*;

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
		name = "Teresa";
		player = "David Clare";
		height = "5'8";
		weight = "200lbs";
		appearance = "Purple hair??";
		SM = 0;
		age = 24;
		ST = 15;
		DX = 12;
		IQ = 8;
		HT = 15;
		HP = 18;
		FP = 18;
		Will = 13;
		PER = 6;
		TL = 4;
		speed = 7.0f;
		move = 7;

		playerSkills = new Vector<CharactersSkill>();
		playerSkills.add(new CharactersSkill("Acrobatics",Skill.BaseAttribute.DX, Skill.Difficulty.HARD, 3));
		playerSkills.add(new CharactersSkill("Bow", Skill.BaseAttribute.DX, Skill.Difficulty.AVERAGE, 1));
		playerSkills.add(new CharactersSkill("Brawling", Skill.BaseAttribute.DX, Skill.Difficulty.EASY, 0));
		playerSkills.add(new CharactersSkill("Running", Skill.BaseAttribute.HT, Skill.Difficulty.AVERAGE, 0));
		
		playerAdvantages = new Vector<Advantage>();
		playerAdvantages.add(new Advantage("Very Fit", "15 Points", "Placeholder for Very Fit description."));
		playerAdvantages.add(new Advantage("Ambidexterity", "5 Points", "Placeholder for Ambidexterity description."));
		playerAdvantages.add(new Advantage("Fast Swimmer (+1 basic move)", "5 Points", "Placeholder for increased Move Speed description."));
		
		playerCultures = new Vector<CulturalFamiliarity>();
		playerCultures.add(new CulturalFamiliarity("Human"));
		playerCultures.add(new CulturalFamiliarity("Lynian"));
		playerCultures.add(new CulturalFamiliarity("Wyverian"));
		
		playerLanguages = new Vector<Language>();
		playerLanguages.add(new Language("Human",Language.Fluency.NATIVE,Language.Fluency.NATIVE));
		
		playerAppearance = new AppearanceModifier(0,0);
		playerReputations = new Vector<ReputationModifier>();
		playerReputations.add(new ReputationModifier("Loc Lac Guild workers", +2, "recognized on a 10- roll."));
		playerReputations.add(new ReputationModifier("Loc Lac Guild workers", -2, "recognized on a 7- roll."));
		playerStatuses = new Vector<StatusModifier>();
		playerStatuses.add(new StatusModifier("Hunter",1));
		
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
	
	private void setSkills(Vector<CharactersSkill> newSkills)
	{
		this.playerSkills = newSkills;
	}

	private void setName(String name)
	{
		this.name = name;
	}

	private void setPlayer(String player)
	{
		this.player = player;
	}

	private void setHeight(String height)
	{
		this.height = height;
	}

	private void setWeight(String weight)
	{
		this.weight = weight;
	}

	private void setAppearance(String appearance)
	{
		this.appearance = appearance;
	}

	private void setSM(int SM)
	{
		this.SM = SM;
	}

	private void setAge(int age)
	{
		this.age = age;
	}

	private void setST(int ST)
	{
		this.ST = ST;
	}

	private void setDX(int DX)
	{
		this.DX = DX;
	}

	private void setIQ(int IQ)
	{
		this.IQ = IQ;
	}

	private void setHT(int HT)
	{
		this.HT = HT;
	}

	private void setHP(int HP)
	{
		this.HP = HP;
	}

	private void setFP(int FP)
	{
		this.FP = FP;
	}

	private void setWill(int Will)
	{
		this.Will = Will;
	}

	private void setPER(int PER)
	{
		this.PER = PER;
	}

	private void setSpeed(float speed)
	{
		this.speed = speed;
	}

	private void setMove(int move)
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
			output += playerSkills.get(i).getName() + " at " + playerSkills.get(i).getLevel() + "\r\n";

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
}
