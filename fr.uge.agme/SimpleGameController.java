package fr.uge.game;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.nio.file.Path;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

import java.util.ArrayList;
import java.util.Random;




public class SimpleGameController {

	/**
	 * Default constructor, which does basically nothing.
	 */
	public SimpleGameController() {
		
	}

	private static boolean gameLoop(ApplicationContext context, SimpleGameView view, SimpleGameData data) throws IOException {

		/**class Area {
			private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);

			void draw(ApplicationContext context, float x, float y) {
				context.renderFrame(graphics -> {
					graphics.setColor(Color.BLACK);
					graphics.fill(ellipse);
				});
			}
		}**/
		
		var event = context.pollOrWaitEvent(10);
		
		
		/**SimpleGameView.intitialisation(context);
		
				
		int x = 35;
		int y=300;
		int y1 = 500;
		int xgolden= 1535;
		System.out.println("helo");
		SimpleGameView.drawcard(context,35,100);
		SimpleGameView.drawcard(context,1535,100);
		SimpleGameView.drawcard(context,x,y);
		SimpleGameView.drawcard(context,x,y1);
		SimpleGameView.drawcard(context,xgolden,y1);
		SimpleGameView.drawcard(context, xgolden, y);
		SimpleGameView.dessincardRessource(context, data.getRessourceTable()[1], x, y, 350, 150);
		SimpleGameView.dessincardRessource(context, data.getRessourceTable()[2], x, y1, 350, 150);
		SimpleGameView.dessincardGolden(context, data.getGoldenTable()[1], xgolden, y, 350, 150);
		SimpleGameView.dessincardGolden(context, data.getGoldenTable()[2], xgolden, y1, 350, 150);
		**/
		
		
		if (event == null) {
			return true;
		}
		var action = event.getAction();
		if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
			return false;
		}
		
		//SimpleGameController.positionclicksourisdebut(context);
		SimpleGameController.positionclicksouris(context);
		
		
		return true;
		
		
	}
	
	
	private static void codex(ApplicationContext context) throws IOException {

		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		var view = new SimpleGameView((int)height, (int)width );
		var data = new SimpleGameData();
		SimpleGameView.intitialisation(context);
		SimpleGameView.startmaincard(context,data.getPackRessource());
		SimpleGameView.startpiocheleft(context, data.getPackRessource());
		SimpleGameView.startpiocheright(context, data.getPackGolden());
		while (true) {			

			if (!gameLoop(context, view, data)) { //, data, view
				System.out.println("Thank you for quitting!");
				context.exit(0);
				return;
			}
		}
		
	}
	
	
	public static void positionclicksourisdebut(ApplicationContext context) {
        while (true) {
        	int cardWidth = 350; 
        	int cardHeight = 150; 

            var event = context.pollOrWaitEvent(10);
            if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
                var location = event.getLocation();
                SimpleGameController.detectmaincardleft(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectmaincardmiddle(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectmaincardright(location.x, location.y, cardWidth, cardHeight);
                return;
                
               
            }
        }
    }
 public static void positionclicksouris(ApplicationContext context) {
        while (true) {
        	int cardWidth = 350; 
        	int cardHeight = 150; 

            var event = context.pollOrWaitEvent(10);
            if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
                var location = event.getLocation();
                SimpleGameController.detectmaincardleft(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectmaincardmiddle(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectmaincardright(location.x, location.y, cardWidth, cardHeight);
                
                SimpleGameController.detectpiochelefttop(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectpiocheleftmiddle(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectpiocheleftbottom(location.x, location.y,cardWidth, cardHeight);
                
                SimpleGameController.detectpiocherighttop(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectpiocherightmiddle(location.x, location.y, cardWidth, cardHeight);
                SimpleGameController.detectpiocherightbottom(location.x, location.y, cardWidth, cardHeight);
                return;
               
            }
        }
    }
 
 public static void detectmaincardleft( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 35; 
     	int y = 875;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte gauche");
			}
	 }
 
 public static void detectmaincardmiddle( float x2,float y2,  int largeur, int hauteur ) {
 	int x = 800; 
 	int y = 875;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		    System.out.println("carte milieu");
		}
 }

 public static void detectmaincardright( float x2,float y2,  int largeur, int hauteur ) {
 	int x = 1400; 
 	int y = 875;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		    System.out.println("carte droite");
		}
 }

 public static void detectpiochelefttop( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 35; 
     	int y = 100;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte pioche gauche, carte haut");
			}
	 }
 public static void detectpiocheleftmiddle( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 35; 
     	int y = 300;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte pioche gauche, carte moyenne");
			}
	 }
 public static void detectpiocheleftbottom( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 35; 
     	int y = 500;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte pioche gauche, carte bas");
			}
	 }
 public static void detectpiocherighttop( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 1535; 
     	int y = 100;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte pioche droite, carte haut");
			}
	 }
 public static void detectpiocherightmiddle( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 1535; 
     	int y = 300;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte pioche droite, carte moyenne");
			}
	 }
 public static void detectpiocherightbottom( float x2,float y2,  int largeur, int hauteur ) {
     	int x = 1535; 
     	int y = 500;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    System.out.println("carte pioche droite, carte bas");
			}
	 }

	
		public static void main(String[] args) throws IOException {
			Application.run(Color.BLACK, t -> {
				try {
					codex(t);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
}
