
package fr.uge.game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public record GoldenCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight,String kingdom, int cost, char typescoring,char scoring) implements Card {
	
	public GoldenCard{
		Objects.requireNonNull(cornerTopLeft);
        Objects.requireNonNull(cornerBottomLeft);
        Objects.requireNonNull(cornerTopRight);
        Objects.requireNonNull(cornerBottomRight);
	}
	
	
    public static ArrayList<GoldenCard> createRessourceCard(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            ArrayList<GoldenCard>golden= new ArrayList<>();
            while ((line = reader.readLine()) != null) {
            	 var card =GoldenCard.addgoldencard(line);
            	 golden.add(card);

        } 
            return golden;
    }
 }
    
    public static GoldenCard addgoldencard(String line) {
        String[] parts = line.split(" ");

        String cornerTopLeft = parts[2];
        String cornerBottomLeft = parts[3];
        String cornerTopRight = parts[4];
        String cornerBottomRight = parts[5];
        String kindgom =  parts[7];
        String dernier = parts[parts.length - 1];
        
        var typescoring = dernier.charAt(0);
        
        var scoring = dernier.charAt(2);
        
        boolean countAnimals = false; 
        int animalCount = 0;

        for (String part : parts) {
            if (part.equalsIgnoreCase("Cost")) {
                countAnimals = true; 
            } else if (part.equalsIgnoreCase("Scoring")) {
                break; 
            } else if (countAnimals && part.equalsIgnoreCase("Animal")) {
                animalCount++;
            }
            
            
        }
        GoldenCard card = new GoldenCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, kindgom, animalCount, typescoring,scoring);
        return card;
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
    public int getCost() {
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
