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

public record RessourceCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight,String scoring, int resource) {
    public RessourceCard {
        Objects.requireNonNull(cornerTopLeft);
        Objects.requireNonNull(cornerBottomLeft);
        Objects.requireNonNull(cornerTopRight);
        Objects.requireNonNull(cornerBottomRight);
    }

    //public void CardLeft() {
            //context.renderFrame(graphics -> {
                //graphics.setColor(Color.BLACK);
               // graphics.fill(new Rectangle2D.Float(400, 800, 350, 150));
           // });
        //});
    //}
    
    
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

        String cornerTopLeft = parts[2].substring(2);
        String cornerBottomLeft = parts[3];
        String cornerTopRight = parts[4];
        String cornerBottomRight = parts[5];
        String scoring = "0";

        int animalCount = 0;

        for (String part : parts) {
            if (part.equalsIgnoreCase("Animal")) {
                animalCount++;
            }
        }
        
        if (parts.length > 8) {
            scoring = parts[8].substring(2);
        } 
        RessourceCard card = new RessourceCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, scoring, animalCount);
        return card;
    }

	@Override
	public String toString() {
		return "RessourceCard [cornerTopLeft=" + cornerTopLeft + ", cornerBottomLeft=" + cornerBottomLeft
				+ ", cornerTopRight=" + cornerTopRight + ", cornerBottomRight=" + cornerBottomRight + ", scoring="
				+ scoring + ", resource=" + resource + "]" + "\n";
	}

}