package fr.uge.game;

import fr.umlv.zen5.Application;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
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
        
        RessourceCard test = new RessourceCard ("Animal", "Animal", "Empty", "Animal", "2", 0);
        
        Application.run(Color.BLUE, context -> {
			var screenInfo = context.getScreenInfo();
			var width = screenInfo.getWidth();
			var height = screenInfo.getHeight();
			System.out.println("size of the screen (" + width + " x " + height + ")");

			context.renderFrame(graphics -> {
				graphics.setColor(Color.ORANGE);
				graphics.fill(new Rectangle2D.Float(400, 800, 350, 150));
				
			});
			SimpleGameView.dessincard(context, test, 400, 800, 350, 150);
        });
        
    }
}
