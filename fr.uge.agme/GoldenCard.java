package fr.uge.game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public record GoldenCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight,String kingdom, int cost, char typescoring,char scoring) {
	
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
    public static String getcornerTopLeft(GoldenCard card) {
    	return card.cornerTopLeft;
    }
    
    public static String getcornerBottomLeft(GoldenCard card) {
    	return card.cornerBottomLeft;
    }
    
    public static String getcornerTopRight(GoldenCard card) {
    	return card.cornerTopRight;
    }
    public static String getcornerBottomRight(GoldenCard card) {
    	return card.cornerBottomRight;
    }
    public static String getKingdom(GoldenCard card) {
    	return card.kingdom;
    }
    public static int getCost(GoldenCard card) {
    	return card.cost;
    }
    
    public static char gettypescoring(GoldenCard card) {
    	return card.typescoring;
    }

    public static char getscoring(GoldenCard card) {
    	return card.scoring;
    }
    
	@Override
	public String toString() {
		return "GoldenCard [cornerTopLeft=" + cornerTopLeft + ", cornerBottomLeft=" + cornerBottomLeft
				+ ", cornerTopRight=" + cornerTopRight + ", cornerBottomRight=" + cornerBottomRight + ", kindgom="
				+ kingdom + ", cost=" + cost + ", typescoring=" + typescoring + ", scoring=" + scoring + "]" + "\n";
	}
    
    
	
}
