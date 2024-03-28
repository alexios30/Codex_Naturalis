package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import fr.umlv.zen5.Application;

public record ResourceCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight,int scoring, int resource) {
    public ResourceCard {
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
    
    
    public static void createCard(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
            	ResourceCard.fromLine(line);

        } 
    }
 }

    
    
    public static ResourceCard fromLine(String line) {
        String[] parts = line.split(" ");

        String cornerTopLeft = parts[2].substring(2);
        String cornerBottomLeft = parts[3];
        String cornerTopRight = parts[4];
        String cornerBottomRight = parts[5];
        int scoring = 0;
        int resource = 0;

        int animalCount = 0;

        for (String part : parts) {
            if (part.equalsIgnoreCase("Animal")) {
                animalCount++;
            }
        }
        
        ResourceCard card = new ResourceCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, scoring, animalCount);
        System.out.println(card);
        return card;
    }

}