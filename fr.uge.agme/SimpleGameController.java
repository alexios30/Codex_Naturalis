package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;




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
		SimpleGameView.drawcard(context,50,200);
	}

	
		public static void main(String[] args) {
			
			Application.run(Color.BLACK, context -> lanceLeJeu(context));
			  
	    	/**Application.run(Color.ORANGE, context -> {
				var screenInfo = context.getScreenInfo();
				var width = screenInfo.getWidth();
				var height = screenInfo.getHeight();
				System.out.println("size of the screen (" + width + " x " + height + ")");
	    	});**/
	    
		}
}
