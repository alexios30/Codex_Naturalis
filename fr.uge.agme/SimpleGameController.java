package fr.uge.game;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Path;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import java.util.ArrayList;
import java.util.Random;
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
			
			 var srcRessource= Path.of("include/Ressource.txt");
		     var packRessource = RessourceCard.createRessourceCard(srcRessource);
		     var srcGolden= Path.of("include/Golden.txt");
		     var packGolden = GoldenCard.createRessourceCard(srcGolden);
		     
			Application.run(Color.BLACK, context -> {
				SimpleGameController.lanceLeJeu(context);
				var r1 =SimpleGameView.startmaincard(context,packRessource);
				SimpleGameView.startpiocheleft(context, packRessource);
				SimpleGameView.startpiocheright(context, packGolden);
				SimpleGameController.positionclicksourisdebut(context);
			});
			
	    
		}
}
