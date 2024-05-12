package fr.uge.game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;


//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.util.Objects;

import fr.umlv.zen5.ApplicationContext;



public record SimpleGameView(int height, int width) {

	// ecran de debut
	public static void intitialisation(ApplicationContext context) {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();

		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, (int) width, (int) height);
			drawBackGround(context, width, height);
			
			graphics.setColor(Color.ORANGE);
			graphics.fill(new Rectangle2D.Float(1440, 50, 50, 50));
			graphics.fill(new Rectangle2D.Float(1365, 50, 50, 50));
			
			Font font = new Font("Arial", Font.PLAIN, 50);
			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString("+", 1440+12, 92);
			graphics.drawString("-", 1365+18, 90);
			
						
			
		});
	}
	
	public static void drawBackGround(ApplicationContext context, float width, float height) {
		context.renderFrame(graphics -> {
			try {
				SimpleGameView.image(graphics, ImageIO.read(Files.newInputStream(Path.of("include" + "/" + "img" + "/" +"Background.png"))),
						0, 0, width, height);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public static void drawBackGround2(ApplicationContext context, float width, float height) {
		context.renderFrame(graphics -> {
			graphics.setColor(Color.LIGHT_GRAY);
			graphics.fill(new Rectangle2D.Float(0, 0, width, height));
			graphics.setColor(Color.BLACK);
			graphics.drawLine((int) width/5+30, 0, (int) width/5+30, (int) height);
		});
	}
	
	public static void drawBackCard(ApplicationContext context, int x, int y , int width, int height) {
		context.renderFrame(graphics -> {
			graphics.setColor(Color.ORANGE);
			graphics.fill(new Rectangle2D.Float(x, y, width, height));
		});
	}
	
	
	public static void drawCard(ApplicationContext context, Card card,int x, int y, int width, int height) {
			
		if (card instanceof GoldenCard) {
			drawBackCard(context, x, y, width, height);
			drawGoldenCard(context, ((GoldenCard) card), x, y, width, height); 
		} else if (card instanceof RessourceCard) {
			drawBackCard(context, x, y, width, height);
			drawRessourceCard(context, ((RessourceCard) card), x, y, width, height); 
		}
	}
	
	public static void drawCorner(ApplicationContext context, Card card, int x, int y, int width, int height) {
		//ArrayList<String> Corner = card.getCorner();
		//System.out.println(Corner +"\n");
		int squareSize = width/7;
		int tailleLettre = (int) (width/11.667);
        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
		
		switch (card.cornerBottomLeft()) {
		case "Invisible" -> {} // ne rien faire 
		case "Animal" -> drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.BLUE);
		case "Fungi" -> drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.RED);
		case "Insect" -> drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.MAGENTA);
		case "Plant" -> drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.GREEN);
		case "Empty" -> drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.WHITE);
		default -> {
			drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.WHITE);
			drawString(context, x+squareSize/3 , (y + height - squareSize) + (int) (squareSize/1.5), String.valueOf(card.cornerBottomLeft().charAt(0)), font);
	    	}

		}
		
		switch (card.cornerTopLeft()) {
		case "Animal" -> drawCornerColor(context, x, y, squareSize, Color.BLUE);
		case "Fungi" -> drawCornerColor(context, x, y, squareSize, Color.RED);
		case "Insect" -> drawCornerColor(context, x, y, squareSize, Color.MAGENTA);
		case "Plant" -> drawCornerColor(context, x, y, squareSize, Color.GREEN);
		case "Empty" -> drawCornerColor(context, x, y, squareSize, Color.WHITE);
		default -> {
			drawCornerColor(context, x, y, squareSize, Color.WHITE);
			drawString(context, x+squareSize/3 , (y) + (int) (squareSize/1.5), String.valueOf(card.cornerTopLeft().charAt(0)), font);
	    	}

		}
		
		switch (card.cornerTopRight()) {
		case "Animal" -> drawCornerColor(context, (x + width - squareSize), y, squareSize, Color.BLUE);
		case "Fungi" -> drawCornerColor(context, (x + width - squareSize), y, squareSize, Color.RED);
		case "Insect" -> drawCornerColor(context, (x + width - squareSize), y, squareSize, Color.MAGENTA);
		case "Plant" -> drawCornerColor(context, (x + width - squareSize), y, squareSize, Color.GREEN);
		case "Empty" -> drawCornerColor(context, (x + width - squareSize), y, squareSize, Color.WHITE);
		default -> {
			drawCornerColor(context, (x + width - squareSize), y, squareSize, Color.WHITE);
			drawString(context, (x + width - squareSize) + squareSize/3 , (y) + (int) (squareSize/1.5), String.valueOf(card.cornerTopRight().charAt(0)), font);
	    	}
		}
		
		switch (card.cornerBottomRight()) {
		case "Animal" -> drawCornerColor(context, (x + width - squareSize), (y + height - squareSize), squareSize, Color.BLUE);
		case "Fungi" -> drawCornerColor(context, (x + width - squareSize), (y + height - squareSize), squareSize, Color.RED);
		case "Insect" -> drawCornerColor(context, (x + width - squareSize), (y + height - squareSize), squareSize, Color.MAGENTA);
		case "Plant" -> drawCornerColor(context, (x + width - squareSize), (y + height - squareSize), squareSize, Color.GREEN);
		case "Empty" -> drawCornerColor(context, (x + width - squareSize), (y + height - squareSize), squareSize, Color.WHITE);
		default -> {
			drawCornerColor(context, (x + width - squareSize), (y + height - squareSize), squareSize, Color.WHITE);
			drawString(context, (x + width - squareSize) + squareSize/3 , (y + height - squareSize) + (int) (squareSize/1.5), String.valueOf(card.cornerBottomRight().charAt(0)), font);
	    	}
		}
		
	}
	
	public static void drawString(ApplicationContext context, int x, int y, String string, Font font) {
		context.renderFrame(graphics -> {
			graphics.setFont(font);
	        graphics.drawString(string, x, y);
		});
	}

	public static void drawCornerColor(ApplicationContext context, int x, int y, int squareSize, Color color) {
		context.renderFrame(graphics -> {
			graphics.setColor(color);
			graphics.fill(new Rectangle2D.Float(x, y, squareSize, squareSize));
		});
	}
	
	public static void drawRessourceCard(ApplicationContext context, RessourceCard card,int x, int y, int width, int height) {
		var scoring = card.getScoring();
	    int squareSize = width / 7;  
		
	    drawCorner(context, card, x, y, width, height);	    
	    if (scoring.equals("1")) {
		    context.renderFrame(graphics -> {
		        String lettre = " S : 1";
		        int tailleLettre = (int) (width/11.667);
		        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
	
		  
		        int startX = x + squareSize * 3 ; 
		        int startY = y + tailleLettre;
		        graphics.setFont(font);
		        graphics.drawString(lettre, startX, startY);
		    	});
	    }
	}

	public static void drawGoldenCard(ApplicationContext context, GoldenCard card,int x, int y, int width, int height) {
		int squareSize = width / 7; 
		var kingdom =  card.getKingdom();
		var cost = card.getCost();
		var typescoring = card.gettypescoring();
		var scoring = card.getScoring();
		
	    drawCorner(context, card, x, y, width, height);	  
	    
	    int tailleLettre = (int) (width/11.667);
        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
	    
	    switch (kingdom) {
		case "Animal" -> context.renderFrame(graphics -> {
	        String lettre = "Animal";	
	  
	        int startX = (int) (x + squareSize * 2.5) ; 
	        int startY = y + tailleLettre * 3;
	        graphics.setFont(font);
	        graphics.drawString(lettre, startX, startY);
	    	});
	
		}
		
//		if (cost>0) {
//			context.renderFrame(graphics -> {
//			String lettre= Integer.toString(cost);
//			String newlettre= "Cost: A:" + lettre ;
//			
//	        int startX = (int) (x + squareSize * 2.5) ; 
//	        int startY = y + tailleLettre;
//	        graphics.setFont(font);
//	        graphics.drawString(newlettre, startX, startY);
//	    });
//			
//		}
	    
		context.renderFrame(graphics ->{
			String lettre = Character.toString(typescoring);
			String lettre1 = Character.toString(scoring);
			String fusion = "Score:" + lettre + lettre1;
			
			int startX = (int) (x + squareSize * 2.5); 
	        int startY = y + tailleLettre * 5;
	        graphics.setFont(font);
	        graphics.drawString(fusion, startX, startY);
		});
	    
	    
	}	

	public static void drawLeftPack(ApplicationContext context, SimpleGameData data) {
		int x = 35;
		int y=300;
		int y1 = 500;
		var carteTop = data.getRessourceTable()[1];
		var carteBottom = data.getRessourceTable()[2];
	
		drawBackCard(context,35,100, 350, 150);
		drawBackCard(context,x,y, 350, 150);
		drawBackCard(context,x,y1, 350, 150);
		
		drawRessourceCard(context, carteTop, x, y, 350, 150);
		drawRessourceCard(context, carteBottom, x, y1, 350, 150);
	
	}

	public static void drawRightPack(ApplicationContext context, SimpleGameData data) {
		int x = 35;
		int y=300;
		int y1 = 500;
		int xgolden= 1535;
		
		var carteTop = data.getGoldenTable()[1];
		var carteBottom = data.getGoldenTable()[2];
	    
		drawBackCard(context,xgolden,100, 350, 150);
		drawBackCard(context,xgolden,y1, 350, 150);
		drawBackCard(context, xgolden, y, 350, 150);
		
		drawGoldenCard(context, carteTop, xgolden, y, 350, 150);
		drawGoldenCard(context, carteBottom, xgolden, y1, 350, 150);
	}

	public static void drawMainPack(ApplicationContext context, SimpleGameData data) {
		int width = 350;
		int height = 150;
		int y = 875;
		int xleft = 200;
		int xmiddle= 800;
		int xright = 1400;
		
		
		var mainTable = data.getMainTable();
	     
	    for (int i = 0; i < mainTable.length; i++) {
	    	drawCard(context, mainTable[i], 200+600*i,y, 350, 150);
	    	System.out.println(mainTable[i]);
	    	
	    	/** Vérification du type de la carte
	        if (mainTable[i] instanceof GoldenCard) {
	        	drawBackCard(context,200+600*i,y, 350, 150);
	        	drawGoldenCard(context, ((GoldenCard) mainTable[i]), 200+600*i, y, width, height); 
	        } else if (mainTable[i] instanceof RessourceCard) {
	        	drawBackCard(context,200+600*i,y, 350, 150);
	        	drawRessourceCard(context, ((RessourceCard) mainTable[i]), 200+600*i, y, width, height); 
	        }**/
	    }
	}


	public static void drawPlateau(ApplicationContext context, SimpleGameData data, int widthCard) {
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		//int widthCard = 350;
		int heightCard = widthCard / 7 * 3;
		
		//System.out.println("widthCard : " + widthCard + ", heightCard : " + heightCard);
		
		var plateau = data.getPlateau();
		var ordre = data.getOrdre();
		var coordinateCardplateau = data.getcoordinatesMap();
		Pair paire1 = new Pair(0,0);	
		int xFirstCard = (int) (width/2 - widthCard/2);
		int yFirstCard = (int) (height/2 - heightCard/2);
		if (!plateau.isEmpty()) {
		
			RessourceCard firstCard = (RessourceCard) plateau.get(paire1);
			//System.out.println(firstCard);
			coordinateCardplateau.put(firstCard, new Pair(xFirstCard, yFirstCard));
			drawBackCard(context, xFirstCard, yFirstCard, widthCard, heightCard);
			drawRessourceCard(context, firstCard , xFirstCard, yFirstCard, widthCard, heightCard);
			if (plateau.size()>=2) {
				for (Map.Entry<Integer, Pair> entry : ordre.entrySet()) {
					int ordeDeJeu = entry.getKey();
					Pair pair = entry.getValue();
					Card card = plateau.get(pair); // Récupérer la carte à partir de la deuxième map
					//System.out.println("Ordre: " + ordeDeJeu + ", Position: "+ pair + ", Carte: " + card);
					int xCard = xFirstCard + (widthCard - widthCard/7) * pair.x();
					int yCard = yFirstCard + (heightCard - heightCard/3) * pair.y();
					if (!cardExistsAtCoordinates(coordinateCardplateau, xCard, yCard)) {
					coordinateCardplateau.put(card, new Pair(yCard, yFirstCard));
					}
					
					drawCard(context, card, xCard, yCard, widthCard, heightCard);
				}
				//System.out.println(coordinateCardplateau);
				SimpleGameController.getXYCardPlateauPair(data);
			}
		}
	
	}
	public static boolean cardExistsAtCoordinates(HashMap<Card, Pair> coordinatesMap, int x, int y) {
	    for (Pair pair : coordinatesMap.values()) {
	        if (pair.x() == x && pair.y() == y) {
	            return true; 
	        }
	    }
	    return false; 
	}
	
	public static void refreshScreen(ApplicationContext context, SimpleGameData data, int widthCard) {
		intitialisation(context);
		drawLeftPack(context, data);
		drawRightPack(context, data);
		drawMainPack(context, data);
		drawPlateau(context, data, widthCard);
	}

	
	private static void checkRange(double min, double value, double max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException("Invalid coordinate: " + value);
		}
	}
	
	public static void image(Graphics2D graphics, BufferedImage image, float x, float y, float dimX, float dimY) {
		var width = image.getWidth();
		var height = image.getHeight();
		var scale = Math.min(dimX / width, dimY / height);
		var transform = new AffineTransform(scale, 0, 0, scale, x + (dimX - scale * width) / 2,
				y + (dimY - scale * height) / 2);
		graphics.drawImage(image, transform, null);
	}

	

	/**
	 * Displays an image in a given part of the display area.
	 * 
	 * @param graphics Graphics engine that will actually display the image.
	 * @param image    Image to be displayed.
	 * @param x        Base abscissa of the part in which the image will be
	 *                 displayed.
	 * @param y        Base ordinate of the part in which the image will be
	 *                 displayed.
	 * @param dimX     Width of the part in which the image will be displayed.
	 * @param dimY     Height of the part in which the image will be displayed.
	 */
	private void drawImage(Graphics2D graphics, BufferedImage image, float x, float y, float dimX, float dimY) {
		var width = image.getWidth();
		var height = image.getHeight();
		var scale = Math.min(dimX / width, dimY / height);
		var transform = new AffineTransform(scale, 0, 0, scale, x + (dimX - scale * width) / 2,
				y + (dimY - scale * height) / 2);
		graphics.drawImage(image, transform, null);
	}
	
	private void draw(Graphics2D graphics, SimpleGameData data) {
		// example
		graphics.setColor(Color.RED);
		graphics.fill(new Rectangle2D.Float(0, 0, height, width));
		}
		
	



	/**
	 * Draws the game board from its data, using an existing
	 * {@code ApplicationContext}.
	 * 
	 * @param context {@code ApplicationContext} of the game.
	 * @param data    GameData containing the game data.
	 * @param view    GameView on which to draw.
	 */
	public static void draw(ApplicationContext context, SimpleGameData data, SimpleGameView view) {
		context.renderFrame(graphics -> view.draw(graphics, data)); // do not modify
	}
}
