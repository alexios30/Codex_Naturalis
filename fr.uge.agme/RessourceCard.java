package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import fr.umlv.zen5.Application;

public record RessourceCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight, String kingdom, String scoring, int resource) implements Card {
    public RessourceCard {
        Objects.requireNonNull(cornerTopLeft);
        Objects.requireNonNull(cornerBottomLeft);
        Objects.requireNonNull(cornerTopRight);
        Objects.requireNonNull(cornerBottomRight);
        Objects.requireNonNull(kingdom);
        Objects.requireNonNull(scoring);
    }

    //public void CardLeft() {
            //context.renderFrame(graphics -> {
                //graphics.setColor(Color.BLACK);
               // graphics.fill(new Rectangle2D.Float(400, 800, 350, 150));
           // });
        //});
    //}
    
    public String cornerTopLeft() {
    	return cornerTopLeft;
    }
    
    public  String cornerBottomLeft() {
    	return cornerBottomLeft;
    }
    
    public String cornerTopRight() {
    	return cornerTopRight;
    }
    public String cornerBottomRight() {
    	return cornerBottomRight;
    }
    public String getScoring() {
    	return scoring;
    }
    
    public String getKingdom() {
    	return kingdom;
    }
    
    
    
    public static ArrayList<RessourceCard> createRessourceCard(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            ArrayList<RessourceCard>ressource= new ArrayList<>();
            while ((line = reader.readLine()) != null) {
            	 var card =RessourceCard.addressourcecard(line);
            	 ressource.add(card);

        } 
            return ressource;
    }
 }

    
    
    public static RessourceCard addressourcecard(String line) {
        String[] parts = line.split(" ");

        String cornerTopLeft = readCorner(parts[2]);
        String cornerBottomLeft = readCorner(parts[3]);
        String cornerTopRight = readCorner(parts[4]);
        String cornerBottomRight = readCorner(parts[5]);
        String kingdom = parts[7];
        String scoring = parts[9];

        int animalCount = 0;

        for (String part : parts) {
            if (part.equalsIgnoreCase("Animal")) {
                animalCount++;
            }
        }
 
        RessourceCard card = new RessourceCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, kingdom, scoring, animalCount);
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

	@Override
	public String toString() {
		return "RessourceCard [cornerTopLeft=" + cornerTopLeft + ", cornerBottomLeft=" + cornerBottomLeft
				+ ", cornerTopRight=" + cornerTopRight + ", cornerBottomRight=" + cornerBottomRight + ", scoring="
				+ scoring + ", resource=" + resource + "]" + "\n";
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
