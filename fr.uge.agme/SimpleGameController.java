package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.nio.file.Path;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;

import java.util.ArrayList;
import java.util.Random;



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
	public static RessourceCard pioche(ArrayList<RessourceCard> packRessource) {
        
		Random rand = new Random();
		int indexAleatoire = rand.nextInt(packRessource.size());
		RessourceCard carte1 = packRessource.get(indexAleatoire);
		return carte1;
	}

	
		public static void main(String[] args) throws IOException {
			
			 var srcRessource= Path.of("include/Ressource.txt");
		     var packRessource = RessourceCard.createRessourceCard(srcRessource);
	        
		     var carte1 = SimpleGameController.pioche(packRessource);
		     var carte2 = SimpleGameController.pioche(packRessource);
		     
			Application.run(Color.BLACK, context -> {
				SimpleGameController.lanceLeJeu(context);
				int x = 35;
				int y=300;
				int y1 = 500;
				SimpleGameView.drawcard(context,35,100);
				SimpleGameView.drawcard(context,x,y);
				SimpleGameView.drawcard(context,x,y1);
				SimpleGameView.dessincard(context, carte1, x, y, 350, 150);
				SimpleGameView.dessincard(context, carte2, x, y1, 350, 150);
			});
			
	    
		}
}
