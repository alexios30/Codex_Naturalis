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



public record SimpleGameView() {

	// ecran de debut
	public static void intitialisation(ApplicationContext context) { //, BackPack backPack
		var screenInfo = context.getScreenInfo();
		var width = screenInfo.getWidth();
		var height = screenInfo.getHeight();

		context.renderFrame(graphics -> {
			graphics.clearRect(0, 0, 1920, 1080);
			
			try {
				SimpleGameView.image(graphics, ImageIO.read(Files.newInputStream(Path.of("include" + "/" + "img" + "/" +"Background.png"))),
						0, 0, width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void drawcard(ApplicationContext context, int x, int y ) {
		context.renderFrame(graphics -> {
			graphics.setColor(Color.ORANGE);
			graphics.fill(new Rectangle2D.Float(x, y, 350, 150));
		});
	}
	
	public static void dessincardRessource(ApplicationContext context, RessourceCard card,int x, int y, int width, int height) {
		var bottomLeft = RessourceCard.getcornerBottomLeft(card);
		var topLeft = RessourceCard.getcornerTopLeft(card);
		var topRight = RessourceCard.getcornerTopRight(card);
		var bottomright = RessourceCard.getcornerBottomRight(card);
		var scoring = RessourceCard.getscoring(card);
	    int squareSize = 50;
		
		if (bottomLeft.equals("Animal") ){ 
		        int squareY = y + height - squareSize;
			context.renderFrame(graphics -> {
				graphics.setColor(Color.RED);
				graphics.fill(new Rectangle2D.Float(x, squareY, squareSize, squareSize));
			});
		}
		if (bottomLeft.equals("Empty")) {
		    int startX = x ; 
		    int endX = x + squareSize ; 
		    int lineY = y + height - squareSize ; 

		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(startX, lineY, endX, lineY);
		        graphics.drawLine(endX, lineY, endX, lineY + squareSize);
		    });
		}
		if (topLeft.equals("Animal")) {
		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.RED);
		        graphics.fill(new Rectangle2D.Float(x, y, squareSize, squareSize));
		    });
		}
		if (topLeft.equals("Empty")) {
		    int startX = x; 
		    int startY = y ; 
		    int endX = x + squareSize; 

		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(startX, startY + squareSize , endX, startY + squareSize); 
		        graphics.drawLine(startX + squareSize, startY, startX + squareSize, startY + squareSize); 
		    });
		}


		if (topRight.equals("Animal")) {
		    int squareX = x + width - squareSize; 
		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.RED);
		        graphics.fill(new Rectangle2D.Float(squareX, y, squareSize, squareSize));
		    });
		}
		if (topRight.equals("Empty")) {
		    context.renderFrame(graphics -> {
		        int startX = x + squareSize;  
		        int startY = y;               
		        int endX = x + squareSize * 7;    

		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(endX - squareSize, startY + squareSize, endX, startY + squareSize); 
		        graphics.drawLine(endX - squareSize, startY, endX - squareSize, startY + squareSize); 
		    });
		}
		
		if (bottomright.equals("Animal")) {
		    int squareX = x + width - squareSize;
		    int squareY = y + height - squareSize; 
		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.RED);
		        graphics.fill(new Rectangle2D.Float(squareX, squareY, squareSize, squareSize));
		    });
		}
		
		if (bottomright.equals("Empty")) {
		    context.renderFrame(graphics -> {
		        int startX = x + squareSize * 7; 
		        int startY =y + height - squareSize ;  

		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(startX - squareSize, startY, startX, startY); 
		        graphics.drawLine(startX - squareSize, startY, startX - squareSize, startY + squareSize); 
		    });
		}
		if (scoring.equals("1")) {
		    context.renderFrame(graphics -> {
		        String lettre = " S : 1";
		        int tailleLettre = 20;
		        Font font = new Font("Arial", Font.PLAIN, tailleLettre);

		  
		        int startX = x + squareSize * 3 ; 
		        int startY = y + tailleLettre;
		        graphics.setFont(font);
		        graphics.drawString(lettre, startX, startY);
		    });
		}



		
	}
	public static void dessincardGolden(ApplicationContext context, GoldenCard card,int x, int y, int width, int height) {
		var bottomLeft = GoldenCard.getcornerBottomLeft(card);
		var topLeft =  GoldenCard.getcornerTopLeft(card);
		var topRight =  GoldenCard.getcornerTopRight(card);
		var bottomright =  GoldenCard.getcornerBottomRight(card);
		var kingdom =  GoldenCard.getKingdom(card);
		var cost = GoldenCard.getCost(card);
		var typescoring = GoldenCard.gettypescoring(card);
		var scoring = GoldenCard.getscoring(card);
	    int squareSize = 50;
		
		if (bottomLeft.equals("Animal") ){ 
		        int squareY = y + height - squareSize;
			context.renderFrame(graphics -> {
				graphics.setColor(Color.RED);
				graphics.fill(new Rectangle2D.Float(x, squareY, squareSize, squareSize));
			});
		}
		if (bottomLeft.equals("Empty")) {
		    int startX = x ; 
		    int endX = x + squareSize ; 
		    int lineY = y + height - squareSize ; 

		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(startX, lineY, endX, lineY);
		        graphics.drawLine(endX, lineY, endX, lineY + squareSize);
		    });
		}
		if (topLeft.equals("Animal")) {
		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.RED);
		        graphics.fill(new Rectangle2D.Float(x, y, squareSize, squareSize));
		    });
		}
		if (topLeft.equals("Empty")) {
		    int startX = x; 
		    int startY = y ; 
		    int endX = x + squareSize; 

		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(startX, startY + squareSize , endX, startY + squareSize); 
		        graphics.drawLine(startX + squareSize, startY, startX + squareSize, startY + squareSize); 
		    });
		}


		if (topRight.equals("Animal")) {
		    int squareX = x + width - squareSize; 
		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.RED);
		        graphics.fill(new Rectangle2D.Float(squareX, y, squareSize, squareSize));
		    });
		}
		if (topRight.equals("Empty")) {
		    context.renderFrame(graphics -> {
		        int startX = x + squareSize;  
		        int startY = y;               
		        int endX = x + squareSize * 7;    

		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(endX - squareSize, startY + squareSize, endX, startY + squareSize); 
		        graphics.drawLine(endX - squareSize, startY, endX - squareSize, startY + squareSize); 
		    });
		}
		
		if (bottomright.equals("Animal")) {
		    int squareX = x + width - squareSize;
		    int squareY = y + height - squareSize; 
		    context.renderFrame(graphics -> {
		        graphics.setColor(Color.RED);
		        graphics.fill(new Rectangle2D.Float(squareX, squareY, squareSize, squareSize));
		    });
		}
		
		if (bottomright.equals("Empty")) {
		    context.renderFrame(graphics -> {
		        int startX = x + squareSize * 7; 
		        int startY =y + height - squareSize ;  

		        graphics.setColor(Color.BLACK);
		        graphics.drawLine(startX - squareSize, startY, startX, startY); 
		        graphics.drawLine(startX - squareSize, startY, startX - squareSize, startY + squareSize); 
		    });
		}
		if (kingdom.equals("Animal")) {
			context.renderFrame(graphics -> {
		        String lettre = "Animal";
		        int tailleLettre = 20;
		        Font font = new Font("Arial", Font.PLAIN, tailleLettre);

		  
		        int startX = x + squareSize * 3 ; 
		        int startY = y + tailleLettre * 4;
		        graphics.setFont(font);
		        graphics.drawString(lettre, startX, startY);
		    });
		}
		
		if (cost>0) {
			context.renderFrame(graphics -> {
			String lettre= Integer.toString(cost);
			String newlettre= "Cost: A:" + lettre ;
			int tailleLettre= 20;
			Font font = new Font("Arial",Font.PLAIN,tailleLettre);
			
	        int startX = x + squareSize * 3 ; 
	        int startY = y + tailleLettre;
	        graphics.setFont(font);
	        graphics.drawString(newlettre, startX, startY);
	    });
			
		}
		context.renderFrame(graphics ->{
			String lettre = Character.toString(typescoring);
			String lettre1 = Character.toString(scoring);
			String fusion = "Score:" + lettre + lettre1;
			int tailleLettre= 20;
			Font font = new Font("Arial",Font.PLAIN,tailleLettre);
			
			int startX = x + squareSize * 3 ; 
	        int startY = y + tailleLettre * 7;
	        graphics.setFont(font);
	        graphics.drawString(fusion, startX, startY);
		});
		
	}
	public static void startpiocheleft(ApplicationContext context, ArrayList<RessourceCard> packRessource) {
		int x = 35;
		int y=300;
		int y1 = 500;
		var cartetop = SimpleGameController.piocheRessource(packRessource);
	    var cartebottom = SimpleGameController.piocheRessource(packRessource);
	    
		SimpleGameView.drawcard(context,35,100);
		SimpleGameView.drawcard(context,x,y);
		SimpleGameView.drawcard(context,x,y1);
		
		SimpleGameView.dessincardRessource(context, cartetop, x, y, 350, 150);
		SimpleGameView.dessincardRessource(context, cartebottom, x, y1, 350, 150);
	}
	
	public static void startpiocheright(ApplicationContext context, ArrayList<GoldenCard> packGolden) {
		int x = 35;
		int y=300;
		int y1 = 500;
		int xgolden= 1535;
		
		var cartetop = SimpleGameController.piocheGolden(packGolden);
	    var cartebottom = SimpleGameController.piocheGolden(packGolden);
	    
		SimpleGameView.drawcard(context,1535,100);
		SimpleGameView.drawcard(context,xgolden,y1);
		SimpleGameView.drawcard(context, xgolden, y);
		
		SimpleGameView.dessincardGolden(context, cartetop, xgolden, y, 350, 150);
		SimpleGameView.dessincardGolden(context, cartebottom, xgolden, y1, 350, 150);
	}
	
	public static void startmaincard(ApplicationContext context, ArrayList<RessourceCard> packRessource) {
		int width = 350;
		int height = 150;
		int y = 875;
		int xleft = 200;
		int xmiddle= 800;
		int xright = 1400;
		
		SimpleGameView.drawcard(context,xleft,y);
		SimpleGameView.drawcard(context,xmiddle,y);
		SimpleGameView.drawcard(context,xright,y);
		
		 var carteleft = SimpleGameController.piocheRessource(packRessource);
	     var cartemiddle = SimpleGameController.piocheRessource(packRessource);
	     var carteright = SimpleGameController.piocheRessource(packRessource);
	     
		SimpleGameView.dessincardRessource(context, carteleft, xleft, y, width, height);
		SimpleGameView.dessincardRessource(context, cartemiddle, xmiddle, y, width, height);
		SimpleGameView.dessincardRessource(context, carteright, xright, y, width, height);
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



	/**
	 * Draws the game board from its data, using an existing
	 * {@code ApplicationContext}.
	 * 
	 * @param context {@code ApplicationContext} of the game.
	 * @param data    GameData containing the game data.
	 * @param view    GameView on which to draw.
	 */
	public static void draw(ApplicationContext context, SimpleGameData data, SimpleGameView view) {
		//context.renderFrame(graphics -> view.draw(graphics, data)); // do not modify
	}
}
