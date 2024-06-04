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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;


//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.util.Objects;

import fr.umlv.zen5.ApplicationContext;



public record SimpleGameView(int height, int width) {

	// ecran de partie
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
	
	public static void drawStartMenu(ApplicationContext context, int height, int width) {
		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, (int) width, (int) height);
			try {
				SimpleGameView.image(graphics, ImageIO.read(Files.newInputStream(Path.of("include" + "/" + "img" + "/" +"start.png"))),
						0, 0, width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int tailleLettre = 70;
	        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect((int) ((width/2)-4.7*tailleLettre), height-180, 670, 120);
			drawString(context, (int) ((width/2)-4.5*tailleLettre), height-100, "Click or Press space", font);
		});
	}
	public static void drawInventaire(ApplicationContext context, int height, int width) {
		int animal=SimpleGameData.returnAnimal();
		String realanimal = String.valueOf(animal);
		
		int plant=SimpleGameData.returnPlant();
		String realplant = String.valueOf(plant);
		
		int insect=SimpleGameData.returnInsect();
		String realinsect = String.valueOf(insect);
		
		int fungi=SimpleGameData.returnFungi();
		String realfungi = String.valueOf(fungi);
		
		int inkwell=SimpleGameData.returnInkwell();
		String realinkwell = String.valueOf(inkwell);
		
		int manuscript=SimpleGameData.returnManuscript();
		String realmanuscript = String.valueOf(manuscript);
		
		int quill=SimpleGameData.returnQuill();
		String realquill = String.valueOf(quill);
		
		int turn=SimpleGameData.returnturn();
		String realturn = String.valueOf(turn);
		 
		int score= SimpleGameData.returnScore();
		String realscore = String.valueOf(score);
		
		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, (int) width, (int) height);
			try {
				SimpleGameView.image(graphics, ImageIO.read(Files.newInputStream(Path.of("include" + "/" + "img" + "/" +"Background.png"))),
						0, 0, width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int tailleLettre = 50;
	        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
	        graphics.setColor(Color.WHITE);
	        //Affiche les différents ressouces
			drawString(context, (int) ((width/2)-11*tailleLettre), 100, "Ressources : ", font);
			drawString(context, (int) ((width/2)-4.5*tailleLettre), 200, "- Animal: ", font);
			drawString(context, (int) ((width/2)), 200, realanimal, font);
			drawString(context, (int) ((width/2)-4.5*tailleLettre), 300, "- Plant: ", font);
			drawString(context, (int) ((width/2)), 300, realplant, font);
			drawString(context, (int) ((width/2)-4.5*tailleLettre), 400, "- Insect: ", font);
			drawString(context, (int) ((width/2)), 400, realinsect, font);
			drawString(context, (int) ((width/2)-4.5*tailleLettre), 500, "- Fungi: ", font);
			drawString(context, (int) ((width/2)), 500, realfungi, font);
			
			//Affiche les différents artéfacts
			drawString(context, (int) ((width/1.23)), 100, "Artefact : ", font);
			drawString(context, (int) ((width/1.21)), 200, ":Inkwell", font);
			drawString(context, (int) ((width/1.23)), 200, realinkwell, font);
			drawString(context, (int) ((width/1.21)), 300, ":Manuscript", font);
			drawString(context, (int) ((width/1.23)), 300, realmanuscript, font);
			drawString(context, (int) ((width/1.21)), 400, ":Quill ", font);
			drawString(context, (int) ((width/1.23)), 400, realquill, font);
			
			drawString(context, (int) ((width/2)-18*tailleLettre), 100, "Score : ", font);
			drawString(context, (int) ((width/2)-14*tailleLettre), 100, realscore, font);
			
			drawString(context, (int) ((width/2)-18*tailleLettre), 950, "Tour : ", font);
			drawString(context, (int) ((width/2)-15*tailleLettre), 950, realturn, font);
		});
	}
	
	public static void drawWinner(ApplicationContext context) {
		int score= SimpleGameData.returnScore();
		String realscore = String.valueOf(score);
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();
		
		context.renderFrame(graphics -> {
			try {
				SimpleGameView.image(graphics, ImageIO.read(Files.newInputStream(Path.of("include" + "/" + "img" + "/" +"Background.png"))),
						0, 0, width, height);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int tailleLettre = 50;
	        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
	        graphics.setColor(Color.WHITE);
			drawString(context, (int) ((width/2)-11*tailleLettre), 100, "Vous avez gagné avec un total de", font);
			drawString(context, (int) ((width/1.50)), 100, realscore, font);
		});
	}
	
	public static void drawBackCard(ApplicationContext context, int x, int y , int width, int height, String kingdom) {
		Objects.requireNonNull(kingdom);
		
		context.renderFrame(graphics -> {
			switch (kingdom) {
			case "Animal" -> graphics.setColor(Color.BLUE);
			case "Fungi" -> graphics.setColor(Color.RED);
			case "Insect" -> graphics.setColor(Color.MAGENTA);
			case "Plant" -> graphics.setColor(Color.GREEN);
			case "Starter" -> graphics.setColor(Color.ORANGE);
			}
			
			graphics.fill(new Rectangle2D.Float(x, y, width, height));
		});
	}
	
	
	public static void drawCard(ApplicationContext context, Card card,int x, int y, int width, int height) {
		/*if (card.isVerso()) {
			draw(context, card.versoCard(), x, y, width, height);
		}*/
		if (card instanceof GoldenCard) {
			drawGoldenCard(context, ((GoldenCard) card), x, y, width, height); 
		} else if (card instanceof RessourceCard) {
			drawRessourceCard(context, ((RessourceCard) card), x, y, width, height); 
		}else {
			drawStarterCard(context, (StarterCard) card, x, y, width, height);
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
		case "Invisible" -> {} // ne rien faire 
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
		case "Invisible" -> {} // ne rien faire 
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
		case "Invisible" -> {} // ne rien faire 
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
			graphics.setColor(Color.BLACK);
	        graphics.drawRect(x, y, squareSize, squareSize);
		});
	}
	
	private static void drawVerso(ApplicationContext context, Card card, int x, int y, int width, int height) {
		var card2 = card.versoCard();		
		drawRessourceCard(context, card2, x, y, width, height);
		drawCenterRessource(context, card2.getKingdom(), x, y, width, height);
		
		if (card instanceof GoldenCard) {
			context.renderFrame(graphics -> {	
		        graphics.setColor(Color.YELLOW);
		        graphics.drawRect(x, y, width, height);
			});
		}		
	}
	
	public static void drawRessourceCard(ApplicationContext context, RessourceCard card,int x, int y, int width, int height) {
	    int squareSize = width / 7;  
	    
	    if (!card.isVerso()) {
	    	var scoring = card.getScoring();
		    
	    
		    drawBackCard(context, x, y, width, height, card.getKingdom());
		    drawCorner(context, card, x, y, width, height);	    
		    context.renderFrame(graphics -> {
		    	graphics.setColor(Color.BLACK);
		        graphics.drawRect(x, y, width, height);
		        if (scoring.substring(2).equals("1")) {
			        String lettre = " S : 1";
			        int tailleLettre = (int) (width/11.667);
			        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
		
			  
			        int startX = x + squareSize * 3 ; 
			        int startY = y + tailleLettre;
			        graphics.setFont(font);
			        graphics.drawString(lettre, startX, startY);    
		        }
	        /*if (card.isVerso()) {
	        	
	        	drawCornerColor(context, x, (y + height - squareSize), squareSize, Color.WHITE)
	        }*/
		    });
	    }else {
	    	drawVerso(context, card, x, y, width, height);
	    }
	}


	public static void drawGoldenCard(ApplicationContext context, GoldenCard card,int x, int y, int width, int height) {
		int squareSize = width / 7; 
		if (!card.isVerso()) {
			var kingdom =  card.getKingdom();
			var cost = card.getCost();
			var typescoring = card.gettypescoring();
			var scoring = card.getScoring();
			
			drawBackCard(context, x, y, width, height, kingdom);
		    drawCorner(context, card, x, y, width, height);	  
		    
		    int tailleLettre = (int) (width/11.667);
	        Font font = new Font("Arial", Font.PLAIN, tailleLettre);
		    
		    
		    //System.out.println(cost);
		    String strCost = card.generateCost(cost);
		    context.renderFrame(graphics -> {	
		    	// dessin du coûts de la carte
		    	int startX = (int) (x + squareSize * 3); 
		        int startY = y + tailleLettre * 5;
		        graphics.setFont(font);
		        graphics.drawString(strCost, startX, startY);
		     
		        // dessin du score de la carte
				String lettre = Character.toString(typescoring);
				String lettre1 = Character.toString(scoring);
				String fusion = "Score:" + lettre + lettre1;
				
				int startX2 = (int) (x + squareSize * 2.5); 
		        int startY1 = y + tailleLettre ;
		        graphics.setFont(font);
		        graphics.drawString(fusion, startX2, startY1);
		        graphics.setColor(Color.YELLOW);
		        graphics.drawRect(x, y, width, height);
			});
		}else {
	    	drawVerso(context, card, x, y, width, height);
	    }
	}
	
	public static void drawStarterCard(ApplicationContext context, StarterCard card,int x, int y, int width, int height) {
	    int squareSize = width / 7;  
	    String kingdom = "Starter";
	    drawBackCard(context, x, y, width, height, kingdom);
	    drawCorner(context, card, x, y, width, height);	    
	    context.renderFrame(graphics -> {
	    	graphics.setColor(Color.BLACK);
	        graphics.drawRect(x, y, width, height);
	    });
	    if (card.isVerso()) {
		    drawVersoRessourceStarter(context, x, y, width, height, card.getVersoResources());
	    }	    
	}
	
	private static void drawVersoRessourceStarter(ApplicationContext context, int x, int y, int widthCard, int heightCard, String[] versoResources) {
		int squareSize = widthCard/7;
		int lenght = versoResources.length;
		int newX = x + widthCard/2 - squareSize*lenght/2;
		int newY = y + heightCard/2 - squareSize/2;
		
		
		context.renderFrame(graphics -> {
			for (int i = 0; i < lenght; i++) {
				switch (versoResources[i]) {
					case "Animal" -> graphics.setColor(Color.BLUE);
					case "Fungi" -> graphics.setColor(Color.RED);
					case "Insect" -> graphics.setColor(Color.MAGENTA);
					case "Plant" -> graphics.setColor(Color.GREEN);
				}
				graphics.fill(new Rectangle2D.Float(newX+i*squareSize, newY, squareSize, squareSize));
				graphics.setColor(Color.BLACK);
				graphics.drawRect(newX+i*squareSize, newY, squareSize, squareSize);
			}

		});
		
		
	}

	private static void drawCenterRessource(ApplicationContext context, String kingdom, int x, int y, int widthCard, int heightCard) {
		int squareSize = widthCard/7;
		int newX = x + widthCard/2 - squareSize/2;
		int newY = y + heightCard/2 - squareSize/2;
		
		context.renderFrame(graphics -> {
			switch (kingdom) {
			case "Animal" -> graphics.setColor(Color.BLUE);
			case "Fungi" -> graphics.setColor(Color.RED);
			case "Insect" -> graphics.setColor(Color.MAGENTA);
			case "Plant" -> graphics.setColor(Color.GREEN);
			}
			graphics.fill(new Rectangle2D.Float(newX, newY, squareSize, squareSize));
			graphics.setColor(Color.BLACK);
			graphics.drawRect(newX, newY, squareSize, squareSize);
		});
		
		
	}

	public static void drawLeftPack(ApplicationContext context, SimpleGameData data) {
		int x = 35;
		int y=300;
		int y1 = 500;
		var carteTop = data.getRessourceTable()[1];
		var carteBottom = data.getRessourceTable()[2];
		var cartePile = data.getRessourceTable()[0];		
		
		drawVerso(context, cartePile, 35,100, 350, 150);
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
		var cartePile = data.getGoldenTable()[0];
		
		drawVerso(context, cartePile, xgolden, 100, 350, 150);
		drawGoldenCard(context, carteTop, xgolden, y, 350, 150);
		drawGoldenCard(context, carteBottom, xgolden, y1, 350, 150);
	}

	public static void drawMainPack(ApplicationContext context, SimpleGameData data) {
		var mainTable = data.getMainTable();	     
	    for (int i = 0; i < mainTable.length; i++) {
	    	drawCard(context, mainTable[i], 200+600*i,875, 350, 150);
	    	//System.out.println(mainTable[i]);
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
		
			Card firstCard = plateau.get(paire1);
			//System.out.println(firstCard);
			coordinateCardplateau.put(firstCard, new Pair(xFirstCard, yFirstCard));
			//drawBackCard(context, xFirstCard, yFirstCard, widthCard, heightCard);
			drawCard(context, firstCard , xFirstCard, yFirstCard, widthCard, heightCard);
			if (plateau.size()>=2) {
				for (Map.Entry<Integer, Pair> entry : ordre.entrySet()) {
					int ordeDeJeu = entry.getKey();
					Pair pair = entry.getValue();
					Card card = plateau.get(pair); // Récupérer la carte à partir de la deuxième map
					//System.out.println("Ordre: " + ordeDeJeu + ", Position: "+ pair + ", Carte: " + card);
					int xCard = xFirstCard + (widthCard - widthCard/7) * pair.x();
					int yCard = yFirstCard + (heightCard - heightCard/3) * pair.y();
					if (!cardExistsAtCoordinates(coordinateCardplateau, xCard, yCard)) {
					coordinateCardplateau.put(card, new Pair(xCard, yCard));
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
	@SuppressWarnings("unused")
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
