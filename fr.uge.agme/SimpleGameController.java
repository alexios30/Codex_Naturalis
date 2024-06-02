
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

	private static ApplicationContext context;
	private static float height;
	private static float width;
	/**
	 * Default constructor, which does basically nothing.
	 */
	public SimpleGameController() {
		
	}

	private static boolean gameLoop(ApplicationContext context, SimpleGameView view, SimpleGameData data) throws IOException {
		var event = context.pollOrWaitEvent(10);

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
		var width = (int) screenInfo.getWidth();
		var height = (int) screenInfo.getHeight();
		var view = new SimpleGameView((int)height, (int)width );
		var data = new SimpleGameData();
		startMenu(context, height, width);
		SimpleGameView.refreshScreen(context, data, 140);
		choixStarterCard(context, data, height, width);
		while (true) {			

			if (!gameLoop(context, view, data)) {
				System.out.println("Thank you for quitting!");
				context.exit(0);
				return;
			}
		}
		
		
	}
	
	
private static void choixStarterCard(ApplicationContext context, SimpleGameData data, int height, int width) {
		
		
	}

public static void startMenu(ApplicationContext context, int height, int width) {
	SimpleGameView.drawStartMenu(context, height, width);
	while (true) {
    	
        var event = context.pollOrWaitEvent(10);
        if (event != null && (event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty())) {
            return;
        }
        else if (event != null && event.getKey() == KeyboardKey.SPACE) {
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
        	ArrayList<Pair> plateau= getXYCardPlateauPair(data);
            var event = context.pollOrWaitEvent(10);
            if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
                var location = event.getLocation();
                SimpleGameController.detectmaincardleft(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau);
                SimpleGameController.detectmaincardmiddle(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau);
                SimpleGameController.detectmaincardright(location.x, location.y, cardWidth, cardHeight, data, context,widthCardPlateau);
                
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

			 //Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
			 	var maintable = data.getMainTable();
			    ArrayList<Pair> plateau= getXYCardPlateauPair(data);
			 	Card card=(maintable[0]);
			 	
			 	if(plateau.size()==0) {
			 	SimpleGameData.BottomRight(maintable[0]);
			 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			    data.removeCardFromMainTable(0);
			    }else {
			 	SimpleGameController.detectecoinplateau(plateau,data,context,card);
			 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			    data.removeCardFromMainTable(0);
			    }
			}
	 }
 
 public static void detectecoinplateau(ArrayList<Pair>plateau,SimpleGameData data,ApplicationContext context, Card card) {
	 while (true) {
	     	int cardWidth = 350; 
	     	int cardHeight = 150; 
		 var event = context.pollOrWaitEvent(10);
         if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
             var location = event.getLocation();
             	SimpleGameController.detectcardplateau(plateau,location.x, location.y, cardWidth, cardHeight, data, context,card);
             	break;
             }
		 
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
		    ArrayList<Pair> plateau= getXYCardPlateauPair(data);
		 	Card card=(maintable[1]);
		 	
		 	if(plateau.size()==0) {
		 	SimpleGameData.BottomRight(maintable[1]);
		 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
		    data.removeCardFromMainTable(1);
		    }else {
		 	SimpleGameController.detectecoinplateau(plateau,data,context,card);
		 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
		    data.removeCardFromMainTable(1);
		    }
		}
 }

 public static void detectmaincardright( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context, int widthCardPlateau ) {
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
			 	var maintable = data.getMainTable();
			    ArrayList<Pair> plateau= getXYCardPlateauPair(data);
			 	Card card=(maintable[2]);
			 	
			 	if(plateau.size()==0) {
			 	SimpleGameData.BottomRight(maintable[2]);
			 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			    data.removeCardFromMainTable(2);
			    }else {
			 	SimpleGameController.detectecoinplateau(plateau,data,context,card);
			 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			    data.removeCardFromMainTable(2);
			    }
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
 public static void detectcardplateau(ArrayList<Pair> plateau,float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ,Card card) {
	 for (Pair carte : plateau) {
		 float x = getXplateau(carte);
		 float y = getYplateau(carte);
		    if (detectleftTopcoin(x, y, largeur, hauteur, x2, y2)) {
		        System.out.println("Le clic est dans le haut rectangle gauche de la première carte.");
		        SimpleGameData.TopLeft(card);
		    }
		    if(detectrightTopcoin(x, y, largeur, hauteur, x2, y2)) {
		    	System.out.println("Le clic est dans le haut rectangle droite de la première carte.");
		    	 SimpleGameData.TopRight(card);
		    }if(detectleftBottomcoin(x, y, largeur, hauteur, x2, y2)) {
		        System.out.println("Le clic est dans le bas rectangle gauche de la première carte.");	
		        SimpleGameData.BottomLeft(card);
		    }if(detectrightBottomcoin(x, y, largeur, hauteur, x2, y2)) {
		    	System.out.println("Le clic est dans le bas rectangle droite de la première carte.");
		    	 SimpleGameData.BottomRight(card);
		    }}
	 }

 
 public static boolean detectleftTopcoin(float x, float y, float largeur, float hauteur, float clicX, float clicY) {
	    return clicX >= x && clicX <= x + (largeur/7) && clicY >= y && clicY <= y + (hauteur/7);
	}

 public static boolean detectrightTopcoin(float x, float y, float largeur, float hauteur, float clicX, float clicY) {
	 int carre = (int) (largeur/7);
	 return clicX >= x + (carre*2) && clicX <= x + (carre*3) && clicY >= y && clicY <= y + (hauteur/7);
	}
 
 public static boolean detectleftBottomcoin(float x, float y, float largeur, float hauteur, float clicX, float clicY) {
	 int test =(int) (hauteur/7); 
	    return clicX >= x && clicX <= x + (largeur/7) && clicY >= y +(test*2) && clicY <= y + (test*3);
	}
 public static boolean detectrightBottomcoin(float x, float y, float largeur, float hauteur, float clicX, float clicY) {
	 int test =(int) (hauteur/7); 
	 int carre = (int) (largeur/7);
	 return clicX >= x + (carre*2) && clicX <= x + (carre*3) && clicY >= y +(test*2) && clicY <= y + (test*3);
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
	    //System.out.println( "Le GET XYCardPlateauPair" + allCoordinates);
	    return allCoordinates;
	}
 
 public static float getXplateau(Pair pair) {
	    float x= pair.x();
	    return x;
	}
 public static float getYplateau(Pair pair) {
	    float y= pair.y();
	    return y;
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
