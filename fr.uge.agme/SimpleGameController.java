
package fr.uge.game;

import java.awt.Color;
import java.io.IOException;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;




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
		Objects.requireNonNull(context);
		Objects.requireNonNull(view);
		Objects.requireNonNull(data);


		
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
		Objects.requireNonNull(context);

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
	Objects.requireNonNull(context);
	Objects.requireNonNull(data);

	
	int widthCard = 350;
	int heightCard = widthCard / 7 *3;
	int xRecto = width/2 - (30+widthCard);
	int yRecto = height/2 - heightCard/2;
	int xVerso = width/2 + 30;
	int yVerso = height/2 - heightCard/2;
	Pair firstPair = new Pair(0, 0);
	var firstCard = SimpleGameData.piocheStarter(data.getStarterPack());

	SimpleGameView.drawCard(context, firstCard, xRecto, yRecto, widthCard, heightCard);
	firstCard.setVerso(true);
	SimpleGameView.drawCard(context, firstCard, xVerso, yVerso, widthCard, heightCard);
	while (true) {
	    	
	       	var event = context.pollOrWaitEvent(10);
	        if (event != null && (event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty())) {
	        	var location = event.getLocation();
	        	float x = location.x;
	        	float y = location.y;

	        	if (x >= xRecto && x <= xRecto + widthCard && y >= yRecto && y <= yRecto + heightCard) {
	        		firstCard.setVerso(false);
	        		SimpleGameData.getPlateau().put(firstPair, firstCard);
	        	    data.getOrdre().put(0, firstPair);
		        	SimpleGameData.getCardForInventaire(firstCard);
	        		return;
	        	}
	        	else if (x >= xVerso && x <= xVerso + widthCard && y >= yVerso && y <= yVerso + heightCard) {
	        		SimpleGameData.getPlateau().put(firstPair, firstCard);
	        	    data.getOrdre().put(0, firstPair);
		        	SimpleGameData.getstartercardverso(firstCard);
	        		return;
		        }
	        }	
		}
	}


public static void startMenu(ApplicationContext context, int height, int width) {
	Objects.requireNonNull(context);

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
public static void inventaire( ApplicationContext context, int height, int width) {
	Objects.requireNonNull(context);

	SimpleGameView.drawInventaire(context, height, width);

}
	
public static void positionclicksouris(ApplicationContext context, SimpleGameData data, int widthCardPlateau) {
	Objects.requireNonNull(context);
	Objects.requireNonNull(data);

    while (true) {
    	int cardWidth = 350; 
    	int cardHeight = 150; 
    	int widthCardPlateau2 = widthCardPlateau ;
    	//System.out.println(widthCardPlateau2);
    	ArrayList<Pair> plateau= getXYCardPlateauPair(data);
        var event = context.pollOrWaitEvent(10);
        
        if (event != null && event.getAction() == Action.KEY_PRESSED) {
        	if (event.getKey() == KeyboardKey.Q) {
        		System.out.println("Thank you for quitting!");
    			context.exit(0);
            	return;
        	}else if (event.getKey() == KeyboardKey.I) {
        		var screenInfo = context.getScreenInfo();
        		var width = (int) screenInfo.getWidth();
        		var height = (int) screenInfo.getHeight();
        		inventaire(context, height, width);
            	return;
        	}
        	reverseCard(data, context, event.getKey());
        	SimpleGameView.refreshScreen(context, data, widthCardPlateau);
        }
        
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
            
            detectButtonLess(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau2);
            detectButtonMore(location.x, location.y, cardWidth, cardHeight, data, context, widthCardPlateau2);
            SimpleGameView.refreshScreen(context, data, widthCardPlateau);
            SimpleGameData.detectewin(context);
            return;
        }            
    }
}


public static boolean detectecoinplateau(ArrayList<Pair> plateau, SimpleGameData data, ApplicationContext context, Card card) {
	Objects.requireNonNull(plateau);
	Objects.requireNonNull(data);
	Objects.requireNonNull(context);
	Objects.requireNonNull(card);

	 while (true) {
	     	int cardWidth = 350; 
	     	int cardHeight = 150; 
	     	var event = context.pollOrWaitEvent(10);
	     	if (event != null && event.getAction() == Action.POINTER_DOWN && event.getModifiers().isEmpty()) {
	     		var location = event.getLocation();
	     		return SimpleGameController.detectcardplateau(plateau,location.x, location.y, cardWidth, cardHeight, data, context,card);
            }
		 
	 }
}
 
 public static void detectmaincardleft( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context, int widthCardPlateau ) {
		Objects.requireNonNull(data);
		Objects.requireNonNull(context);
	 
     	int x = 200; 
     	int y = 875;
		 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {

			 //Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
			 	var maintable = data.getMainTable();
			    ArrayList<Pair> plateau= getXYCardPlateauPair(data);
			 	Card card=(maintable[0]);
			 	
			 	if(SimpleGameController.detectecoinplateau(plateau,data,context,card)) {
				 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
				    data.removeCardFromMainTable(0);
			 	}
			}
	 }
 
 public static void detectmaincardmiddle( float x2,float y2,  int largeur, int hauteur ,SimpleGameData data, ApplicationContext context, int widthCardPlateau) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);int x = 800; 
		
	 int y = 875;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		 
//Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
		 	var maintable = data.getMainTable();
		    ArrayList<Pair> plateau= getXYCardPlateauPair(data);
		 	Card card=(maintable[1]);
		 	

		 	if(SimpleGameController.detectecoinplateau(plateau,data,context,card)) {
			 	SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			    data.removeCardFromMainTable(1);
		 	}
		}
 }

 public static void detectmaincardright( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context, int widthCardPlateau ) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 int x = 1400; 
	 int y = 875;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {

//	      }//Pour afficher la carte sur le plateur(on ne la voit pas a cause du refresh)
		 var maintable = data.getMainTable();
		 ArrayList<Pair> plateau= getXYCardPlateauPair(data);
		 Card card=(maintable[2]);
		 if(SimpleGameController.detectecoinplateau(plateau,data,context,card)) {
			 SimpleGameView.drawPlateau(context, data, widthCardPlateau);
			 data.removeCardFromMainTable(2);
		 }	
	 }
}
 
 public static void reverseCard(SimpleGameData data, ApplicationContext context, KeyboardKey keyboardKey) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 Objects.requireNonNull(keyboardKey);
	 
	 var maintable = data.getMainTable();
	 int longueur = maintable.length;
	 if (keyboardKey == KeyboardKey.A && longueur > 0) {
		 Card card=(maintable[0]);
		 card.setVerso(!card.isVerso());
	 }else if (keyboardKey == KeyboardKey.Z && longueur > 1) {
    	 Card card=(maintable[1]);
    	 card.setVerso(!card.isVerso());
	 }else if (keyboardKey == KeyboardKey.E && longueur > 2) {
    	 Card card=(maintable[2]);
    	 card.setVerso(!card.isVerso());
    	 
	 }
	 return;
 }


 
 public static void detectpiochelefttop( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 
	 int x = 35; 
     int y = 100;
     if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
    	 var mainTable = data.getMainTable();
    	 RessourceCard card = data.getRessourceTable()[0];
    	 if(mainTable.length<3) {
    		 data.removeCardFromRessourceTableElement0(0);
    		 data.addCardToMainTable(card);
    		 SimpleGameData.addturn();
    	 }
     }
}
 
 public static void detectpiocheleftmiddle( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context ) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 
	 int x = 35; 
	 int y = 300;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		 var mainTable = data.getMainTable();
		 RessourceCard card = data.getRessourceTable()[1];
		 if(mainTable.length<3) {
			 data.removeCardFromRessourceTableElement1(1);
			 data.addCardToMainTable(card);
			 SimpleGameData.addturn();
		 }
	 }
}
 
 public static void detectpiocheleftbottom( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 
	 int x = 35; 
	 int y = 500;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		 var mainTable = data.getMainTable();
		 RessourceCard card = data.getRessourceTable()[2];
		 if(mainTable.length<3) {
			 data.removeCardFromRessourceTableElement2(2);
			 data.addCardToMainTable(card);
			 SimpleGameData.addturn();
		 }
	 }
}
 
 public static void detectpiocherighttop( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 
	 int x = 1535; 
	 int y = 100;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		    var mainTable = data.getMainTable();
		    GoldenCard card = data.getGoldenTable()[0];
		    if(mainTable.length<3) {
		    	data.removeCardFromGoldenTableElement0(0);
		    	data.addCardToMainTable(card);
		    	SimpleGameData.addturn();
		    }
	 }
}

 public static void detectpiocherightmiddle( float x2,float y2,  int largeur, int hauteur, SimpleGameData data, ApplicationContext context) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 int x = 1535; 
	 int y = 300;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		 var mainTable = data.getMainTable();
		 GoldenCard card = data.getGoldenTable()[1];
		 if(mainTable.length<3) {
			 data.removeCardFromGoldenTableElement1(1);
			 data.addCardToMainTable(card);
			 SimpleGameData.addturn();
		 }
	 }
}

 public static void detectpiocherightbottom( float x2,float y2,  int largeur, int hauteur,SimpleGameData data, ApplicationContext context ) {
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(context);
	 int x = 1535; 
	 int y = 500;
	 if (x2 >= x && x2 <= x + largeur && y2 >= y && y2 <= y + hauteur) {
		 var mainTable = data.getMainTable();
		 GoldenCard card = data.getGoldenTable()[2];
		 if(mainTable.length<3) {
			 data.removeCardFromGoldenTableElement2(2);
			 data.addCardToMainTable(card);
			 SimpleGameData.addturn();
		 }
	 }
}
 
 public static boolean detectcardplateau(ArrayList<Pair> plateau,float x2,float y2, int largeur, int hauteur, SimpleGameData data, ApplicationContext context ,Card card) {
	 Objects.requireNonNull(plateau);
	 Objects.requireNonNull(context);
	 Objects.requireNonNull(data);
	 Objects.requireNonNull(card);
	 
	 HashMap<Pair, Card> pairplateau =SimpleGameData.getPlateau();
     HashMap<Card, Pair> getcoordinatesMap=data.getcoordinatesMap();
     List<Pair> pairposition = new ArrayList<>(pairplateau.keySet());
     List<Card> cardplateau= new ArrayList<>(pairplateau.values());
     List<Card> cardcoordinate= new ArrayList<>(getcoordinatesMap.keySet());
     List<Pair> cardcoordinateXY= new ArrayList<>(getcoordinatesMap.values());
     for (Pair carte : plateau) {
         float x = getXplateau(carte);
         float y = getYplateau(carte);
         if(isPointInsideRectangle(x, y, largeur, hauteur, x2, y2)) {
                int calcul=0;
                for(Pair XY : cardcoordinateXY) {
                	if (XY==carte){
                		Card card3 =cardcoordinate.get(calcul);
                		int newcalcul=0;
                		for(Card searchcard : cardplateau) {
                			if(card3.equals(searchcard)) {
                				Pair coordoneecarte = pairposition.get(newcalcul);
                				
                        		ArrayList<String>coin =card3.getCorner();
                        		String hautgauche =coin.get(0);
                        		String basgauche = coin.get(1);
                        		String hautdroite = coin.get(2);
                        		String basdroite = coin.get(3);
                        		
                				if (card3 instanceof StarterCard) {
                					if(card3.isVerso()) {
                                		 hautgauche =coin.get(4);
                                		 basgauche = coin.get(5);
                                		 hautdroite = coin.get(6);
                                		 basdroite = coin.get(7);
                					}
                				}
                        		if(card.isVerso() ||SimpleGameData.verifieCost(card)) {
                        			if(!hautgauche.equals("Invisible")) {
                        				if (detectleftTopcoin(x, y, largeur, hauteur, x2, y2)) {
                        					Pair newpair =SimpleGameData.verifautourcard(data,coordoneecarte);
                        					if(newpair!=null) {
                							SimpleGameData.verifiecarteadjcente(data,newpair);
                        					}
                						
                        					if (!card.isVerso()) {
	                							int score =card.getrealscoring();
	                    						SimpleGameData.addscoring(score);
	                    						SimpleGameData.ajouterInventaire(card.getKingdom());
                        					}else {
	                							card = card.versoCard();
	                							card.setVerso(true);
	                							SimpleGameData.ajouterInventaire(card.getKingdom());
                        					}
                        					if(SimpleGameData.detecteTopLeft(card,coordoneecarte,pairposition)) {
                        					SimpleGameData.TopLeft(card,coordoneecarte);
	                						SimpleGameData.getCardForInventaire(card);
	                						SimpleGameData.VerificationSuperposition(hautgauche);
	                						return true;
                        					}
                        				}
                        			}
                        			
                        			if(!basgauche.equals("Invisible")) {
                        				if(detectleftBottomcoin(x, y, largeur, hauteur, x2, y2)) {
	                						Pair newpair =SimpleGameData.verifautourcard(data,coordoneecarte);
	                						if(newpair!=null) {
	                							SimpleGameData.verifiecarteadjcente(data,newpair);
	                						}
	                						
	                						if (!card.isVerso()) {
	                							int score =card.getrealscoring();
	                    						SimpleGameData.addscoring(score);
	                						}else {
	                							card = card.versoCard();
	                							card.setVerso(true);
	                							SimpleGameData.ajouterInventaire(card.getKingdom());
	                						}
	                						if(SimpleGameData.detecteBottomLeft(card,coordoneecarte,pairposition)) {
	                    	                SimpleGameData.BottomLeft(card,coordoneecarte);
	                    	                SimpleGameData.getCardForInventaire(card);
	                    	                SimpleGameData.VerificationSuperposition(basgauche);
	                    	                return true;
	                						}
                        				}	
                        			}
                        			if(!hautdroite.equals("Invisible")) {
	                					if(detectrightTopcoin(x, y, largeur, hauteur, x2, y2)) {
	                						Pair newpair =SimpleGameData.verifautourcard(data,coordoneecarte);
	                						if(newpair!=null) {
	                							SimpleGameData.verifiecarteadjcente(data,newpair);
	                						}
	                						if (!card.isVerso()) {
	                							int score =card.getrealscoring();
	                    						SimpleGameData.addscoring(score);
	                    						SimpleGameData.ajouterInventaire(card.getKingdom());
	                						}else {
	                							card = card.versoCard();
	                							card.setVerso(true);
	                							SimpleGameData.ajouterInventaire(card.getKingdom());
	                						}
	                						if(SimpleGameData.detecteTopRight(card,coordoneecarte,pairposition)) {
	                						SimpleGameData.TopRight(card,coordoneecarte);
		                   	              	SimpleGameData.getCardForInventaire(card);
		                   	              	SimpleGameData.VerificationSuperposition(hautdroite);
		                   	              	return true;
	                						}
	                					}
                        			}
                        			if(!basdroite.equals("Invisible")) {
	                    				if(detectrightBottomcoin(x, y, largeur, hauteur, x2, y2)) {
	                						Pair newpair =SimpleGameData.verifautourcard(data,coordoneecarte);
	                						if(newpair!=null) {
	                							SimpleGameData.verifiecarteadjcente(data,newpair);
	                						}
	                						if (!card.isVerso()) {
	                							int score =card.getrealscoring();
	                    						SimpleGameData.addscoring(score);
	                    						SimpleGameData.ajouterInventaire(card.getKingdom());
	                						}else {
	                							card = card.versoCard();
	                							card.setVerso(true);
	                							SimpleGameData.ajouterInventaire(card.getKingdom());
	                						}
	
	                						if(SimpleGameData.detecteBottomRight(card,coordoneecarte,pairposition)) {
	                						SimpleGameData.BottomRight(card,coordoneecarte);
	                						SimpleGameData.getCardForInventaire(card);
	                   	                 	SimpleGameData.VerificationSuperposition(basdroite);
	                   	                 	return true;
	                						}
	                    				}
                        			}
                        		}}
                			newcalcul++;
                		}
                	}
                	calcul++;
                }
         }
     }
     return false;
}
 
 public static boolean isPointInsideRectangle(float x, float y, float width, float height, float pointX, float pointY) {
        return pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height;
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
	Objects.requireNonNull(context);
	Objects.requireNonNull(data);
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
	 Objects.requireNonNull(context);
	 Objects.requireNonNull(data);
	 
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
		Objects.requireNonNull(data);
		
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
