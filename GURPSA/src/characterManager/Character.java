package characterManager;

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
	private CharactersSkill[] playerSkills;
	
	@XmlElementWrapper(name = "AdvantageList")
	@XmlElement(name = "Advantage")
	private Advantage[] advantages;

	@XmlElementWrapper(name = "LanguageList")
	@XmlElement(name = "Language")
	private Language[] playerLanguages;
	
	@XmlElementWrapper(name = "CultureList")
	@XmlElement(name = "Culture")
	private CulturalFamiliarity[] playerCultures;
	
	@XmlElementWrapper(name = "ReactionModList")
	@XmlElement(name = "ReactionModifier")
	private ReactionModifier[] reactionMods;
	
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
		speed = 7.0f;
		move = 7;

		playerSkills = new CharactersSkill[30];
		playerSkills[0] = new CharactersSkill("Acrobatics", 3, Skill.BaseAttribute.DX, Skill.Difficulty.HARD);
		playerSkills[1] = new CharactersSkill("Bow", 1, Skill.BaseAttribute.DX, Skill.Difficulty.AVERAGE);
		playerSkills[2] = new CharactersSkill("Brawling", 0, Skill.BaseAttribute.DX, Skill.Difficulty.EASY);
		playerSkills[3] = new CharactersSkill("Running", 0, Skill.BaseAttribute.HT, Skill.Difficulty.AVERAGE);
		
		
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

	public CharactersSkill[] getSkills()
	{
		return playerSkills;
	}
	
	private void setSkills(CharactersSkill[] newSkills)
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
		for(int i = 0 ; i < playerSkills.length ; i++)
			output += playerSkills[i].getName() + " at " + playerSkills[i].getLevel() + "\r\n";
		return output;
	}
}
