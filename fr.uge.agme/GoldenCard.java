
package fr.uge.game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public record GoldenCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight,String kingdom, HashMap<String, Integer> cost, char typescoring, char scoring) implements Card {
	
	public GoldenCard{
		Objects.requireNonNull(cornerTopLeft);
        Objects.requireNonNull(cornerBottomLeft);
        Objects.requireNonNull(cornerTopRight);
        Objects.requireNonNull(cornerBottomRight);
        Objects.requireNonNull(kingdom);
        Objects.requireNonNull(cost);
	}
	
	
    public static ArrayList<GoldenCard> createRessourceCard(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            ArrayList<GoldenCard>golden= new ArrayList<>();
            while ((line = reader.readLine()) != null) {
            	 var card = GoldenCard.addgoldencard(line);
            	 golden.add(card);

        } 
            return golden;
    }
 }
    
    public static GoldenCard addgoldencard(String line) {
        String[] parts = line.split(" ");
        //System.out.println(line);
        //System.out.println(parts);
        HashMap<String, Integer> cost = new HashMap<String, Integer>();
        //plateau.put(new Pair(0, 0), piocheRessource(getPackRessource()));
        cost.put("Animal", 0);
        cost.put("Fungi", 0);
        cost.put("Insect", 0);
        cost.put("Plant", 0);
        
        String cornerTopLeft = readCorner(parts[2]);
        String cornerBottomLeft = readCorner(parts[3]);
        String cornerTopRight = readCorner(parts[4]);
        String cornerBottomRight = readCorner(parts[5]);
        String kindgom =  parts[7];
        String dernier = parts[parts.length - 1];
           
        var typescoring = dernier.charAt(0);
        var scoring = dernier.charAt(2);
        
        for (int i = 9; i < parts.length - 2; i++) {
            String mot = parts[i];
            cost.put(mot, cost.getOrDefault(mot, 0) + 1);
        }
        System.out.println(cost);
        
//        boolean countCost = false; 
//        int animalCount = 0;
//
//        for (String part : parts) {
//            if (part.equalsIgnoreCase("Cost")) {
//            	countCost = true; 
//            } else if (part.equalsIgnoreCase("Scoring")) {
//                break; 
//            } else if (countCost && part.equalsIgnoreCase("Animal")) {
//                animalCount++;
//            }
//  
//        }
        
        GoldenCard card = new GoldenCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, kindgom, cost, typescoring,scoring);
        return card;
    }
    
    public static String readCorner(String corner) {
    	char firstChar = corner.charAt(0);
    	// si la valeur est un artefact ou une ressource
    	if (firstChar == 'A' || firstChar == 'R') {
			return corner.substring(2);
		}
		return corner;    
    }
    
    public String cornerTopLeft() {
    	return cornerTopLeft;
    }
    
    public String cornerBottomLeft() {
    	return cornerTopRight;
    }
    
    public String cornerTopRight() {
    	return cornerTopRight;
    }
    public String cornerBottomRight() {
    	return cornerBottomRight;
    }
    public String getKingdom() {
    	return kingdom;
    }
    public HashMap<String, Integer> getCost() {
    	return cost;
    }
    
    public char gettypescoring() {
    	return typescoring;
    }

    public char getScoring() {
    	return scoring;
    }
    
	@Override
	public String toString() {
		return "GoldenCard [cornerTopLeft=" + cornerTopLeft + ", cornerBottomLeft=" + cornerBottomLeft
				+ ", cornerTopRight=" + cornerTopRight + ", cornerBottomRight=" + cornerBottomRight + ", kindgom="
				+ kingdom + ", cost=" + cost + ", typescoring=" + typescoring + ", scoring=" + scoring + "]" + "\n";
	}


	public ArrayList<String> getCorner() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(cornerBottomLeft);
		list.add(cornerBottomRight);
		list.add(cornerTopLeft);
		list.add(cornerTopRight);
		return list;
	}




    
	
}
