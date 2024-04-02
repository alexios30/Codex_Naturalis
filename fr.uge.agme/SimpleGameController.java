package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.lang.reflect.AccessFlag.Location;
import java.nio.file.Path;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;



public class SimpleGameController {

	/**
	 * Default constructor, which does basically nothing.
	 */
	public SimpleGameController() {
		
	}

	private static void lanceLeJeu(ApplicationContext context) {

		/**class Area {
			private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);

			void draw(ApplicationContext context, float x, float y) {
				context.renderFrame(graphics -> {
					graphics.setColor(Color.BLACK);
					graphics.fill(ellipse);
				});
			}
		}**/
		
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		SimpleGameView.intitialisation(context);
	}
	
	public static RessourceCard piocheRessource(ArrayList<RessourceCard> packRessource) {
        
		Random rand = new Random();
		int indexAleatoire = rand.nextInt(packRessource.size());
		RessourceCard carte1 = packRessource.get(indexAleatoire);
		packRessource.remove(carte1);

		return carte1;
	}
	
	public static GoldenCard piocheGolden(ArrayList<GoldenCard> packGolden) {
        
		Random rand = new Random();
		int indexAleatoire = rand.nextInt(packGolden.size());
		GoldenCard carte1 = packGolden.get(indexAleatoire);
		packGolden.remove(carte1);
		return carte1;
	}
	
	 public static int positionclicksouris(ApplicationContext context) {
	        while (true) {
	            var event = context.pollOrWaitEvent(10);
	            if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
	                var location = event.getLocation();
	                System.out.println("Coordonnées de la souris - X: " + location.x + ", Y: " + location.y);
	            }
	        }
	    }

	
		public static void main(String[] args) throws IOException {
			
			 var srcRessource= Path.of("include/Ressource.txt");
		     var packRessource = RessourceCard.createRessourceCard(srcRessource);
		     var srcGolden= Path.of("include/Golden.txt");
		     var packGolden = GoldenCard.createRessourceCard(srcGolden);
		     
			Application.run(Color.BLACK, context -> {
				SimpleGameController.lanceLeJeu(context);
				
				//SimpleGameController.positionclicksouris(context);
				
				SimpleGameView.startmaincard(context,packRessource);
				SimpleGameView.startpiocheleft(context, packRessource);
				SimpleGameView.startpiocheright(context, packGolden);
			});
			
	    
		}
}
