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
import java.util.List;
import java.util.Map;
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
		SimpleGameController.positionclicksouris(context, data, 140);
		
		
		return true;
		
		
	}
	
	
	private static void codex(ApplicationContext context) throws IOException {

		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		var view = new SimpleGameView((int)height, (int)width );
		var data = new SimpleGameData();
		SimpleGameView.refreshScreen(context, data, 140);
		while (true) {			

			if (!gameLoop(context, view, data)) {
				System.out.println("Thank you for quitting!");
				context.exit(0);
				return;
			}
		}
		
	}
	
	
 public static void positionclicksouris(ApplicationContext context, SimpleGameData data, int widthCardPlateau) {
        while (true) {
        	int cardWidth = 350; 
        	int cardHeight = 150; 
        	int widthCardPlateau2 = widthCardPlateau ;
        	//System.out.println(widthCardPlateau2);

            var event = context.pollOrWaitEvent(10);
            if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
                var location = event.getLocation();
                SimpleGameController.detectmaincardleft(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau);
                SimpleGameController.detectmaincardmiddle(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau);
                SimpleGameController.detectmaincardright(location.x, location.y, cardWidth, cardHeight, data, context);
                
                SimpleGameController.detectpiochelefttop(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocheleftmiddle(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocheleftbottom(location.x, location.y,cardWidth, cardHeight, data, context);
                
                SimpleGameController.detectpiocherighttop(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocherightmiddle(location.x, location.y, cardWidth, cardHeight, data, context);
                SimpleGameController.detectpiocherightbottom(location.x, location.y, cardWidth, cardHeight, data, context);
                //detectButtonLess(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau2);
                //detectButtonMore(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau2);
                SimpleGameView.refreshScreen(context, data, widthCardPlateau);
                return;
            }
            if (event != null && event.getAction() == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
    			System.out.println("Thank you for quitting!");
    			context.exit(0);
            	return;
    		}
        }
    }
 
 public static void detectmaincardleft( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context, int widthCardPlateau ) {
     	int x = 200; 
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
			 	var maintable = data.getMainTable();
			 	SimpleGameData.TopLeft(maintable[0]);
			 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			    data.removeCardFromMainTable(0);
			}
	 }
 
 public static void detectmaincardmiddle( float x2,float y2,  int largeur, int hauteur ,SimpleGameData data, ApplicationContext context, int widthCardPlateau) {
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
		 	var maintable = data.getMainTable();
		 	SimpleGameData.BottomRight(maintable[1]);
		 	
		 	ArrayList<Pair> plateau= getXYCardPlateauPair(data);
		 	 boucleplateau(plateau, maintable[1], x, y, hauteur, largeur, data);
		 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
		    data.removeCardFromMainTable(1);
		}
 }

 public static void detectmaincardright( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
	 	int x = 1400; 
	 	int y = 875;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
//			 var mainTable = data.getMainTable();
//			    if (mainTable[2] instanceof GoldenCard) {
//				   
//				    SimpleGameView.drawcard(context, 1000, 700);
//				    SimpleGameView.dessincardGolden(context, ((GoldenCard) mainTable[2]), 1000, 700, largeur, hauteur);
//	      } else if (mainTable[2] instanceof RessourceCard) {
//				    SimpleGameView.drawcard(context, 1000, 700);
//				    SimpleGameView.dessincardRessource(context, ((RessourceCard) mainTable[2]), 1000, 700, largeur, hauteur);
	//
//	      }//Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
			    int squareSize = 50;
			    int squareY = y + hauteur - squareSize;
			    var maintable = data.getMainTable();
			    if (isBottomleftclicked(x2, y2, x, squareY, squareSize, squareSize)) {
			    	SimpleGameData.BottomLeft(maintable[2]);
			        //System.out.println("en bas a gauche !");
		    }
			    if (isBottomRightClicked(x2, y2, x + largeur - squareSize, squareY, squareSize, squareSize)) {
			    	SimpleGameData.BottomRight(maintable[2]);
			        //System.out.println("Coin en bas à droite cliqué !");
			    }
			   if (isTopLeftClicked(x2, y2, x, y, squareSize, squareSize)) {
				   SimpleGameData.TopLeft(maintable[2]);
		            //System.out.println("Coin en haut à gauche cliqué !");	        
		            }
		    if (isTopRightClicked(x2, y2, x, y, squareSize, squareSize)) {
		    	SimpleGameData.TopRight(maintable[2]);
		    	//System.out.println("Coin en haut à droite cliqué !");
		    }
		        
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
 
 public static boolean isBottomleftclicked(float x2, float y2, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
	    return x2 >= rectangleX && x2 <= rectangleX + rectangleWidth && 
	           y2 >= rectangleY && y2 <= rectangleY + rectangleHeight;
	}
public static boolean isBottomRightClicked(float x2, float y2, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
	    return x2 >= rectangleX && x2 <= rectangleX + rectangleWidth && 
	           y2 >= rectangleY && y2 <= rectangleY + rectangleHeight;
	}
public static boolean isTopLeftClicked(float x2, float y2, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
	    return x2 >= rectangleX && x2 <= rectangleX + rectangleWidth / 2 &&
	           y2 >= rectangleY && y2 <= rectangleY + rectangleHeight / 2;
	}
public static boolean isTopRightClicked(float x2, float y2, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
	    return x2 >= rectangleX && x2 >= rectangleX + rectangleWidth / 2 &&
	           y2 >= rectangleY && y2 <= rectangleY + rectangleHeight / 2;
	}


 public static int detectButtonLess( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context, int cardWidth ) {
  	int x = 1440; 
  	int y = 50;
  	int cardWidth2 = cardWidth*(80/100);
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
			    //SimpleGameView.refreshScreen(context, data, cardWidth2);
			    return cardWidth2;
		 }
		 return cardWidth2;
 	}
 
 public static int detectButtonMore( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context, int cardWidth ) {
	  	int x = 1365; 
	  	int y = 50;
	  	int cardWidth2 = cardWidth*(1+20/100);
			 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
				    //SimpleGameView.refreshScreen(context, data, cardWidth2);
				    return cardWidth2;
			 }
			 return cardWidth;
	 	}
	 
 public static ArrayList<Pair> getXYCardPlateauPair(SimpleGameData data) {
	 	var coordinatesMap =data.getcoordinatesMap();
	    ArrayList<Pair> allCoordinates = new ArrayList<>();
	    for (Pair pair : coordinatesMap.values()) {
	        allCoordinates.add(pair);
	    }
	    System.out.println(allCoordinates);
	    return allCoordinates;
	}
 
 public static void detectecardplateau(Card card,float x2,float y2,int x,int y,int hauteur,int largeur,SimpleGameData data) {
	 int squareSize = 50;
	    int squareY = y + hauteur - squareSize;
	    if (isBottomleftclicked(x2, y2, x, squareY, squareSize, squareSize)) {
	    	SimpleGameData.BottomLeft(card);
	        System.out.println("en bas a gauche !");
 }
	    if (isBottomRightClicked(x2, y2, x + largeur - squareSize, squareY, squareSize, squareSize)) {
	    	SimpleGameData.BottomRight(card);
	        System.out.println("Coin en bas à droite cliqué !");
	    }
	   if (isTopLeftClicked(x2, y2, x, y, squareSize, squareSize)) {
		   SimpleGameData.TopLeft(card);
         System.out.println("Coin en haut à gauche cliqué !");	        
         }
		 if (isTopRightClicked(x2, y2, x, y, squareSize, squareSize)) {
		 	SimpleGameData.TopRight(card);
		 	System.out.println("Coin en haut à droite cliqué !");
		 }
 }
 public static float getXplateau(Pair pair) {
	    float x= pair.x();
	    return x;
	}
 public static float getYplateau(Pair pair) {
	    float y= pair.y();
	    return y;
	}
 public static void boucleplateau(ArrayList<Pair> pairs, Card card, int x1, int y1, int hauteur, int largeur, SimpleGameData data) {
	 for (int i = 0; i < pairs.size(); i++) {
	        Pair pair = pairs.get(i);
	        float x = getXplateau(pair);
	        float y = getYplateau(pair);
	        detectecardplateau(card, x, y, x1, y1, hauteur, largeur, data);
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
