package fr.isika.cda22.projet1.entites;

import java.util.regex.Pattern;
/**
 * Cette interface regroupe les differents methodes de vérification données
 * @author shmilany
 *
 */
public interface Verificateur {
	
	/**
	 * methode permettant de mettre tous les lettres d'un String en majuscule
	 * @param original String
	 * @return String dont tous les lettres sont au majuscule
	 */
	public static String upperCase(String original) {
		if(original != null) { //verifier si pas null
			return original.toUpperCase(); //mettre en majuscule
		}
		return original; //retourner le String
	}
	
	/**
	 * methode permmettant de mettre le 1e lettre de chaque mot (separé par " " ou "-" d'un String en majuscule
	 * @param original
	 * @return String
	 */
	public static String capitalize(String original) {
		if(original == null) { //verifier si null
			return null; //retourner null
		}
		if(original.contains(" ")) { //verifier si le séparateur est " "
			String[] s = original.split(" "); //decomposer le string en tableau sur les " "
			if(s.length == 1) { //le tableau contient qu'un seul mot
				return capitalizeWord(original); //mettre le 1er lettre de l'origianl en majuscule
			} else {
				String capitalized = ""; //initialiser un String
				for(String sub: s) { //boucle sur le tableau
					capitalized += capitalizeWord(sub) + " "; //concatener chq mot du tableau apres avoir mis en majuscule son 1er lettre + " " 
				}
				return capitalized.substring(0,capitalized.length()-1); //retourner le string concatené - " "
			}
		} else if(original.contains("-")) { //cas d'un "-"
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
	
	/**
	 * methode permmettant de mettre le 1e lettre d'un String en majuscule
	 * @param word
	 * @return String dont le 1er lettre est en majuscule
	 */
	public static String capitalizeWord(String word) {
		if(word.length() > 1) { //verifeir si le string contient > 1 char
			return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();	
		}
		return word;
	}
	
	/**
	 * methode permettant de verifier si un String contient que des chiffres
	 * @param strNum
	 * @return true si le string contient que des chiffres
	 * @return false s'il contient des autres caracteres
	 */
	public static boolean isNumeric(String strNum) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");  //creer un pattern que des chiffres avec les regular expressions (regex)
    	if(strNum == null) { //verifier si string est null
    		return false;
    	} else {
    		return pattern.matcher(strNum).matches(); //verifier si le string match avec le pattern ou pas
    	}
   	}
	
}
