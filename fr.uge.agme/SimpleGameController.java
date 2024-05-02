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
		SimpleGameController.positionclicksouris(context, data);
		
		
		return true;
		
		
	}
	
	
	private static void codex(ApplicationContext context) throws IOException {

		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		var view = new SimpleGameView((int)height, (int)width );
		var data = new SimpleGameData();
		SimpleGameView.intitialisation(context);
		SimpleGameView.drawLeftPack(context, data);
		SimpleGameView.drawRightPack(context, data);
		SimpleGameView.drawMainPack(context, data);
		while (true) {			

			if (!gameLoop(context, view, data)) {
				System.out.println("Thank you for quitting!");
				context.exit(0);
				return;
			}
		}
		
	}
	
	
 public static void positionclicksouris(ApplicationContext context, SimpleGameData data) {
        while (true) {
        	int cardWidth = 350; 
        	int cardHeight = 150; 

            var event = context.pollOrWaitEvent(10);
            if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
                var location = event.getLocation();
                SimpleGameController.detectmaincardleft(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectmaincardmiddle(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectmaincardright(location.x, location.y, cardWidth, cardHeight, data, context);
                
                SimpleGameController.detectpiochelefttop(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocheleftmiddle(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocheleftbottom(location.x, location.y,cardWidth, cardHeight, data, context);
                
                SimpleGameController.detectpiocherighttop(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocherightmiddle(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocherightbottom(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameView.refreshScreen(context, data);
                return;
               
            }
            if (event != null && event.getAction() == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
    			System.out.println("Thank you for quitting!");
    			context.exit(0);
            	return;
    		}
        }
    }
 
 public static void detectmaincardleft( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
     	int x = 35; 
     	int y = 875;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
//			    var mainTable = data.getMainTable();
//			    if (mainTable[0] instanceof GoldenCard) {
//				    SimpleGameView.drawcard(context, 1000, 700);
//				    SimpleGameView.dessincardGolden(context, ((GoldenCard) mainTable[0]), 1000, 700, largeur, hauteur);
//	            } else if (mainTable[0] instanceof RessourceCard) {
//	 			    SimpleGameView.drawcard(context, 1000, 700);
//	 			    SimpleGameView.dessincardRessource(context, ((RessourceCard) mainTable[0]), 1000, 700, largeur, hauteur);
//
//	            }
			 //Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
			    data.removeCardFromMainTable(0);
			}
	 }
 
 public static void detectmaincardmiddle( float x2,float y2,  int largeur, int hauteur ,SimpleGameData data, ApplicationContext context) {
 	int x = 800; 
 	int y = 875;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
//		 var mainTable = data.getMainTable();
//		    if (mainTable[1] instanceof GoldenCard) {
//			   
//			    SimpleGameView.drawcard(context, 1000, 700);
//			    SimpleGameView.dessincardGolden(context, ((GoldenCard) mainTable[1]), 1000, 700, largeur, hauteur);
//         } else if (mainTable[1] instanceof RessourceCard) {
//			   
//			    SimpleGameView.drawcard(context, 1000, 700);
//			    SimpleGameView.dessincardRessource(context, ((RessourceCard) mainTable[1]), 1000, 700, largeur, hauteur);
//
//         }//Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
		 
		    data.removeCardFromMainTable(1);
		}
 }

 public static void detectmaincardright( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
 	int x = 1400; 
 	int y = 875;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
//		 var mainTable = data.getMainTable();
//		    if (mainTable[2] instanceof GoldenCard) {
//			   
//			    SimpleGameView.drawcard(context, 1000, 700);
//			    SimpleGameView.dessincardGolden(context, ((GoldenCard) mainTable[2]), 1000, 700, largeur, hauteur);
//      } else if (mainTable[2] instanceof RessourceCard) {
//			    SimpleGameView.drawcard(context, 1000, 700);
//			    SimpleGameView.dessincardRessource(context, ((RessourceCard) mainTable[2]), 1000, 700, largeur, hauteur);
//
//      }//Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
		 
		 
		    data.removeCardFromMainTable(2);
		}
 }

 public static void detectpiochelefttop( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context) {
     	int x = 35; 
     	int y = 100;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    var mainTable = data.getMainTable();
			    	RessourceCard card = data.getRessourceTable()[0];
				    if(mainTable.length<3) {
				    	data.removeCardFromRessourceTableElement0(0);
				    	data.addCardToMainTable(card);
				    }
			}
	 }
 
 public static void detectpiocheleftmiddle( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context ) {
     	int x = 35; 
     	int y = 300;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    var mainTable = data.getMainTable();
			    	RessourceCard card = data.getRessourceTable()[1];
				    if(mainTable.length<3) {
				    	data.removeCardFromRessourceTableElement1(1);
				    	data.addCardToMainTable(card);
			    }
			}
	 }
 
 public static void detectpiocheleftbottom( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context) {
     	int x = 35; 
     	int y = 500;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    var mainTable = data.getMainTable();
			    	RessourceCard card = data.getRessourceTable()[2];
				    if(mainTable.length<3) {
				    	data.removeCardFromRessourceTableElement2(2);
				    	data.addCardToMainTable(card);
				    }
			}
	 }
 
 public static void detectpiocherighttop( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
     	int x = 1535; 
     	int y = 100;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    var mainTable = data.getMainTable();
			    	GoldenCard card = data.getGoldenTable()[0];
				    if(mainTable.length<3) {
				    	data.removeCardFromGoldenTableElement0(0);
					    data.addCardToMainTable(card);
					    }
		 }
	 }

 public static void detectpiocherightmiddle( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context) {
     	int x = 1535; 
     	int y = 300;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    var mainTable = data.getMainTable();
			    	GoldenCard card = data.getGoldenTable()[1];
				    if(mainTable.length<3) {
				    	data.removeCardFromGoldenTableElement1(1);
					    data.addCardToMainTable(card);

			}
		 }
	 }

 public static void detectpiocherightbottom( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
     	int x = 1535; 
     	int y = 500;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    var mainTable = data.getMainTable();
			    	GoldenCard card = data.getGoldenTable()[2];
				    if(mainTable.length<3) {
				    	data.removeCardFromGoldenTableElement2(2);
					    data.addCardToMainTable(card);
			}
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
