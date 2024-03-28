package fr.uge.game;

import fr.umlv.zen5.Application;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {

    public static void main(String[] args) throws IOException {
        // Affichage de la largeur et de la hauteur de l'écran
        Application.run(Color.ORANGE, context -> {
            var screenInfo = context.getScreenInfo();
            var width = screenInfo.getWidth();
            var height = screenInfo.getHeight();
            System.out.println(width);
            System.out.println(height);
        });
        
        var src= Path.of("include/Ressource.txt");
        ResourceCard.createCard(src);
        
        //ResourceCard c1 = new ResourceCard("vide", "vide", "vide", "vide", "vide", 10);
        //c1.CardLeft();
    }
}
