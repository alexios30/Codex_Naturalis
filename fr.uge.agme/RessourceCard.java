package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

import fr.umlv.zen5.Application;

public record ResourceCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight,String scoring, int resource) {
    public ResourceCard {
        Objects.requireNonNull(cornerTopLeft);
        Objects.requireNonNull(cornerBottomLeft);
        Objects.requireNonNull(cornerTopRight);
        Objects.requireNonNull(cornerBottomRight);
    }

    public void CardLeft() {
            context.renderFrame(graphics -> {
                graphics.setColor(Color.BLACK);
                graphics.fill(new Rectangle2D.Float(400, 800, 350, 150));
            });
        });
    }
    
    public static ResourceCard fromLine(String line) {
        String[] parts = line.split(" ");

        // Récupérer les éléments appropriés de la ligne en fonction de son format
        String cornerTopLeft = parts[2].substring(2); // Supprimer "R:"
        String cornerBottomLeft = parts[3];
        String cornerTopRight = parts[4];
        String cornerBottomRight = parts[5];
        String scoring = "";
        int resource = 0;

        int animalCount = 0;

        // Si la ligne contient des informations de score, les récupérer
        if (parts.length > 6 && parts[6].startsWith("Scoring")) {
            scoring = parts[6];
            resource = Integer.parseInt(parts[7].substring(2)); // Supprimer "D:"
        }

        // Compter les occurrences de "Animal" dans chaque coin de la carte
        for (String part : parts) {
            if (part.equalsIgnoreCase("Animal")) {
                animalCount++;
            }
        }

        ResourceCard card = new ResourceCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, scoring, animalCount);
        return card;
    }

}
