package fr.uge.game;

import fr.umlv.zen5.Application;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {

    public static void main(String[] args) throws IOException {
        
        var srcRessource= Path.of("include/Ressource.txt");
        var packRessource = RessourceCard.createRessourceCard(srcRessource);
        System.out.println(packRessource);
        
        
        var srcGolden= Path.of("include/Golden.txt");
        var packGolden = GoldenCard.createRessourceCard(srcGolden);
        System.out.println(packGolden);
    }
}
