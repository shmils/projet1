package fr.isika.cda22.projet1.entites;

import java.util.regex.Pattern;

public interface Verificateur {
	
	public static String upperCase(String original) {
		if(original != null) {
			return original.toUpperCase();
		}
		return original;
	}
	
	public static String capitalize(String original) {
		if(original == null) {
			return null;
		}
		if(original.contains(" ")) {
			String[] s = original.split(" ");
			if(s.length == 1) {
				return capitalizeWord(original);
			} else {
				String capitalized = "";
				for(String sub: s) {
					capitalized += capitalizeWord(sub) + " ";
				}
				return capitalized.substring(0,capitalized.length()-1);
			}
		} else if(original.contains("-")) {
			String[] s = original.split("-");
			if(s.length == 1) {
				return capitalizeWord(original);
			} else {
				String capitalized = "";
				for(String sub: s) {
					capitalized += capitalizeWord(sub) + "-";
				}
				return capitalized.substring(0,capitalized.length()-1);
			}
		}
		return capitalizeWord(original);
	}
	
	public static String capitalizeWord(String word) {
		if(word.length() > 1) {
			return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();	
		}
		return word;
	}
	
	public static boolean isNumeric(String strNum) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?"); 
    	if(strNum == null) {
    		return false;
    	} else {
    		return pattern.matcher(strNum).matches();
    	}
   	}
	
}
