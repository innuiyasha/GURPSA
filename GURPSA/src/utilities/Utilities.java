package utilities;

import java.util.Random;

public class Utilities {
	
	public static int standardDiceRoll()
	{
		int result = 0;
		Random gen = new Random();
		for(int i = 0 ; i < 3 ; i++)
		{
			result += gen.nextInt(6) + 1;
		}
		return result;
	}
	
	public static String capitalizeFully(String input) {
		String[] parts = input.split(" ");
		String building = "";
		for(String part : parts) {
			
			building += capitalize(part) + " ";
		}
		
		return building.trim();
	}
	
	// Ugly? I know. But it's simple.
	public static String capitalize(String input) {
		char[] letters = input.toCharArray();
		
		switch(letters[0]) {
		case 'a':
			letters[0] = 'A';
			break;
		case 'b':
			letters[0] = 'B';
			break;
		case 'c':
			letters[0] = 'C';
			break;
		case 'd':
			letters[0] = 'D';
			break;
		case 'e':
			letters[0] = 'E';
			break;
		case 'f':
			letters[0] = 'F';
			break;
		case 'g':
			letters[0] = 'G';
			break;
		case 'h':
			letters[0] = 'H';
			break;
		case 'i':
			letters[0] = 'I';
			break;
		case 'j':
			letters[0] = 'J';
			break;
		case 'k':
			letters[0] = 'K';
			break;
		case 'l':
			letters[0] = 'L';
			break;
		case 'm':
			letters[0] = 'M';
			break;
		case 'n':
			letters[0] = 'N';
			break;
		case 'o':
			letters[0] = 'O';
			break;
		case 'p':
			letters[0] = 'P';
			break;
		case 'q':
			letters[0] = 'Q';
			break;
		case 'r':
			letters[0] = 'R';
			break;
		case 's':
			letters[0] = 'S';
			break;
		case 't':
			letters[0] = 'T';
			break;
		case 'u':
			letters[0] = 'U';
			break;
		case 'v':
			letters[0] = 'V';
			break;
		case 'w':
			letters[0] = 'W';
			break;
		case 'x':
			letters[0] = 'X';
			break;
		case 'y':
			letters[0] = 'Y';
			break;
		case 'z':
			letters[0] = 'Z';
			break;
		default:
			break;
		}
		
		return new String(letters);
	}
	
	public static String formatSkillName(String unformated) {
		unformated = unformated.replace('.', ' ');
		String[] components = unformated.split("-");
		String skillName = capitalizeFully(components[0]);
		
		for(int i = 1; i < components.length; i++) {
			skillName += "-" + Utilities.capitalizeFully(components[i]);
		}
		return skillName;
	}
	
	public static Boolean isIn(String a, String[] list) {
		for(String entry : list) {
			if(entry.equals(a))
				return true;
		}
		return false;
	}
}
