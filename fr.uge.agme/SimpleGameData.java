package fr.uge.game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimpleGameData {

	private RessourceCard[] ressourceTable; 
	private GoldenCard[] goldenTable; 
	private Card[] mainTable; 

	
	private ArrayList<RessourceCard> packRessource;
	private ArrayList<GoldenCard> packGolden;
	private ArrayList<StarterCard> packStarter;
	private StarterCard firstCard;
	
	private static HashMap<Pair, Card> plateau;
	private static HashMap<Integer, Pair> ordre;
	private static HashMap<Card, Pair> coordinatesMap;
	private static HashMap<String, Integer> nbRessource;
	private static int numOrdre;

	
	public SimpleGameData() {
		packRessource = new ArrayList<>();
        packGolden = new ArrayList<>();
        packStarter = new ArrayList<>();
        try {
            createCards(Path.of("include/deck2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }	
		
		ressourceTable = new RessourceCard[3];
		goldenTable = new GoldenCard[3];
		mainTable = new Card[3];
		plateau = new HashMap<Pair, Card>();
		ordre = new HashMap<Integer, Pair>();
		coordinatesMap = new HashMap<Card,Pair>();
		nbRessource = new HashMap<String, Integer>();
		numOrdre=0;
		
		melangeRessource(packRessource);
        melangeGolden(packGolden);
        //System.out.println(packStarter+"\n\n");
        melangeStarter(packStarter);
       // System.out.println(packStarter+"\n\n");
        
        //forcer la pose de la starter card ------------------------------------------- ! A modifier
        firstCard = piocheStarter(packStarter);
        firstCard.setVerso(true);
        //System.out.println(firstCard);
        Pair firstPair = new Pair(0, 0);
        plateau.put(firstPair, firstCard);
        ordre.put(numOrdre, firstPair);
        
		
		
		// initialisation des pioches
		for (int i = 0; i < 3; i++) {
			ressourceTable[i] = piocheRessource(getPackRessource());
			goldenTable[i] = piocheGolden(getPackGolden());
		}
		
		// initialisation des cartes en main
		mainTable[0] = piocheRessource(getPackRessource());
		mainTable[1] = piocheRessource(getPackRessource());
		mainTable[2] = piocheGolden(getPackGolden());
		

		
	}
	
	
	
	
	
	
	

	// plus utiliser
	public static ArrayList<GoldenCard> createGoldenCard(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            ArrayList<GoldenCard> golden = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                var card = addGoldenCard(line);
                golden.add(card);
            }
            return golden;
        }
    }
    
	// plus utiliser
    public static ArrayList<RessourceCard> createRessourceCard(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            ArrayList<RessourceCard> ressource = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                var card = addRessourceCard(line);
                ressource.add(card);
            }
            return ressource;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void createCards(Path src) throws IOException {
        try (var reader = Files.newBufferedReader(src, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ResourceCard")) {
                    packRessource.add(addRessourceCard(line));
                } else if (line.startsWith("GoldCard")) {
                    packGolden.add(addGoldenCard(line));
                } else if (line.startsWith("StarterCard")) {
                    packStarter.add(addStarterCard(line));
                }
            }
        }
    }
	

	public static RessourceCard addRessourceCard(String line) {
        String[] parts = line.split(" ");

        String cornerTopLeft = readCorner(parts[2]);
        String cornerBottomLeft = readCorner(parts[3]);
        String cornerTopRight = readCorner(parts[4]);
        String cornerBottomRight = readCorner(parts[5]);
        String kingdom = parts[7];
        String scoring = parts[9];

        int animalCount = 0;

        for (String part : parts) {
            if (part.equalsIgnoreCase("Animal")) {
                animalCount++;
            }
        }

        return new RessourceCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, kingdom, scoring, animalCount, false);
    }
	
	public static GoldenCard addGoldenCard(String line) {
        String[] parts = line.split(" ");
        HashMap<String, Integer> cost = new HashMap<>();
        cost.put("Animal", 0);
        cost.put("Fungi", 0);
        cost.put("Insect", 0);
        cost.put("Plant", 0);

        String cornerTopLeft = readCorner(parts[2]);
        String cornerBottomLeft = readCorner(parts[3]);
        String cornerTopRight = readCorner(parts[4]);
        String cornerBottomRight = readCorner(parts[5]);
        String kingdom = parts[7];
        String dernier = parts[parts.length - 1];

        char typescoring = dernier.charAt(0);
        char scoring = dernier.charAt(2);

        for (int i = 9; i < parts.length - 2; i++) {
            String mot = parts[i];
            cost.put(mot, cost.getOrDefault(mot, 0) + 1);
        }

        return new GoldenCard(cornerTopLeft, cornerBottomLeft, cornerTopRight, cornerBottomRight, kingdom, cost, typescoring, scoring, false);
    }
	
	public static StarterCard addStarterCard(String line) {
        String[] parts = line.split(" ");        	
        	
        String rectoCornerTopLeft = readCorner(parts[2]);
        String rectoCornerBottomLeft = readCorner(parts[3]);
        String rectoCornerTopRight = readCorner(parts[4]);
        String rectoCornerBottomRight = readCorner(parts[5]);
        
        String versoCornerTopLeft = readCorner(parts[7]);
        String versoCornerBottomLeft = readCorner(parts[8]);
        String versoCornerTopRight = readCorner(parts[9]);
        String versoCornerBottomRight = readCorner(parts[10]);

        String[] versoResources = new String[parts.length-12];
        int j = 0;
        for (int i = 12; i < parts.length; i++) {
        	versoResources[j] = parts[i].substring(2);
        	j++;
        }
        var card = new StarterCard(rectoCornerTopLeft, rectoCornerBottomLeft, rectoCornerTopRight, rectoCornerBottomRight,
                versoCornerTopLeft, versoCornerBottomLeft, versoCornerTopRight, versoCornerBottomRight,
                versoResources);
        return card;
	}
    
    public static String readCorner(String corner) {
        char firstChar = corner.charAt(0);
        if (firstChar == 'A' || firstChar == 'R') {
            return corner.substring(2);
        }
        return corner;
    }
	
	public static ArrayList<RessourceCard> melangeRessource(ArrayList<RessourceCard> packRessource) {
        Random rand = new Random();
        Collections.shuffle(packRessource, rand);
        return packRessource;
   }

   public static RessourceCard piocheRessource(ArrayList<RessourceCard> packRessource) {
	   int size = packRessource.size();
	   if(size==0) {
		   System.out.println("Partie fini");
		   return null;
	   }
       RessourceCard carte1 = packRessource.remove(0);
       return carte1;
   }
   
   public static ArrayList<GoldenCard> melangeGolden(ArrayList<GoldenCard> packGolden) {
        Random rand = new Random();
        Collections.shuffle(packGolden, rand);
        return packGolden;
   }

   public static GoldenCard piocheGolden(ArrayList<GoldenCard> packGolden) {
	   int size = packGolden.size();
	   if(size==0) {
		   System.out.println("Partie fini");
		   return null;
	   }
       GoldenCard carte1 = packGolden.remove(0);
       return carte1;
   }
   
   public static ArrayList<StarterCard> melangeStarter(ArrayList<StarterCard> packStarter) {
       Random rand = new Random();
       Collections.shuffle(packStarter, rand);
       return packStarter;
  }
   
   public static StarterCard piocheStarter(ArrayList<StarterCard> packStarter) {
	   int size = packStarter.size();
	   if(size==0) {
		   System.out.println("Partie fini");
		   return null;
	   }
	   StarterCard carte1 = packStarter.remove(0);
       return carte1;
   }

	public RessourceCard[] getRessourceTable() {
		return ressourceTable;
	}

	public GoldenCard[] getGoldenTable() {
		return goldenTable;
	}
	public int getGoldenTableSize() {
		return goldenTable.length;
	}
	
	public Card[] getMainTable() {
		return mainTable;
	}
	
	public HashMap<Integer, Pair> getOrdre() {
		return ordre;
	}

	public ArrayList<RessourceCard> getPackRessource() {
		return packRessource;
	}

	public ArrayList<GoldenCard> getPackGolden() {
		return packGolden;
	}
	
	public static HashMap<Pair, Card> getPlateau() {
		return plateau;
	}
	public HashMap<Card, Pair> getcoordinatesMap() {
		return coordinatesMap;
	}
	
	public void removeCardFromMainTable(int index) {
	    if (index >= 0 && index < mainTable.length) {
	        Card[] newMainTable = new Card[mainTable.length - 1];
	        System.arraycopy(mainTable, 0, newMainTable, 0, index);
	        System.arraycopy(mainTable, index + 1, newMainTable, index, mainTable.length - index - 1);
	        mainTable = newMainTable;
	        //displayMainTable(mainTable);
	    }
	}

	public void removeCardFromRessourceTableElement0(int index) {
	    if (index >= 0 && index < ressourceTable.length) {
	        RessourceCard[] newressourceTable = new RessourceCard[ressourceTable.length - 1];
	        System.arraycopy(ressourceTable, 0, newressourceTable, 0, index);
	        System.arraycopy(ressourceTable, 1, newressourceTable, index, ressourceTable.length - index - 1);
	        ressourceTable = newressourceTable;
	        RessourceCard carte = piocheRessource(getPackRessource());
	        addCardToRessourceTable(carte,0);
	    }
	}
	public void removeCardFromRessourceTableElement1(int index) {
	    if (index >= 0 && index < ressourceTable.length) {
	        RessourceCard[] newressourceTable = new RessourceCard[ressourceTable.length - 1];
	        System.arraycopy(ressourceTable, 0, newressourceTable, 0, index);
	        System.arraycopy(ressourceTable, index + 1, newressourceTable, index, ressourceTable.length - index - 1);
	        ressourceTable = newressourceTable;
	        RessourceCard carte = piocheRessource(getPackRessource());
	        addCardToRessourceTable(carte, 1);
	    }
	}

	
	public void removeCardFromRessourceTableElement2(int index) {
	    if (index >= 0 && index < ressourceTable.length) {
	        RessourceCard[] newressourceTable = new RessourceCard[ressourceTable.length - 1];
	        System.arraycopy(ressourceTable, 0, newressourceTable, 0, index);
	        System.arraycopy(ressourceTable, 2, newressourceTable, index, ressourceTable.length - index - 1);
	        ressourceTable = newressourceTable;
	        RessourceCard carte = piocheRessource(getPackRessource());
	        addCardToRessourceTable(carte,2);
	    }
	}
	public void removeCardFromGoldenTableElement0(int index) {
	    if (index >= 0 && index < ressourceTable.length) {
	        GoldenCard[] newgoldenTable = new GoldenCard[goldenTable.length - 1];
	        System.arraycopy(goldenTable, 0, newgoldenTable, 0, index);
	        System.arraycopy(goldenTable, 1, newgoldenTable, index, goldenTable.length - index - 1);
	        goldenTable = newgoldenTable;
	        GoldenCard carte = piocheGolden(getPackGolden());
	        addCardToGoldenTable(carte,0);
	    }
	}
	public void removeCardFromGoldenTableElement1(int index) {
	    if (index >= 0 && index < ressourceTable.length) {
	        GoldenCard[] newgoldenTable = new GoldenCard[goldenTable.length - 1];
	        System.arraycopy(goldenTable, 0, newgoldenTable, 0, index);
	        System.arraycopy(goldenTable, index + 1, newgoldenTable, index, goldenTable.length - index - 1);
	        goldenTable = newgoldenTable;
	        GoldenCard carte = piocheGolden(getPackGolden());
	        addCardToGoldenTable(carte,1);
	    }
	}
	public void removeCardFromGoldenTableElement2(int index) {
	    if (index >= 0 && index < ressourceTable.length) {
	        GoldenCard[] newgoldenTable = new GoldenCard[goldenTable.length - 1];
	        System.arraycopy(goldenTable, 0, newgoldenTable, 0, index);
	        System.arraycopy(goldenTable, 2, newgoldenTable, index, goldenTable.length - index - 1);
	        goldenTable = newgoldenTable;
	        GoldenCard carte = piocheGolden(getPackGolden());
	        addCardToGoldenTable(carte,2);
	    }
	}
	
	public void addCardToMainTable(Card card) {
	    Card[] newMainTable = new Card[mainTable.length + 1];
	    System.arraycopy(mainTable, 0, newMainTable, 0, mainTable.length);
	    newMainTable[mainTable.length] = card;
	    mainTable = newMainTable;
	}
	
	public void addCardToRessourceTable(RessourceCard card, int index) {
	    if (index >= 0 && index <= ressourceTable.length) {
	        RessourceCard[] newRessourceTable = new RessourceCard[ressourceTable.length + 1];
	        System.arraycopy(ressourceTable, 0, newRessourceTable, 0, index);
	        newRessourceTable[index] = card;
	        System.arraycopy(ressourceTable, index, newRessourceTable, index + 1, ressourceTable.length - index);
	        ressourceTable = newRessourceTable;
	    }
	}
	public void addCardToGoldenTable(GoldenCard card, int index) {
	    if (index >= 0 && index <= goldenTable.length) {
	        GoldenCard[] newGoldenCard = new GoldenCard[goldenTable.length + 1];
	        System.arraycopy(goldenTable, 0, newGoldenCard, 0, index);
	        newGoldenCard[index] = card;
	        System.arraycopy(goldenTable, index, newGoldenCard, index + 1, goldenTable.length - index);
	        goldenTable = newGoldenCard;
	    }
	}


	public static void displayMainTable(Card[] mainTable) {
	    //System.out.println("Contenu de mainTable :");
	    for (Card card : mainTable) {
	        //System.out.println(card);
	    }
	}

    public static void BottomRight(Card card, Pair pair) {
        Pair newPair;
        int x = pair.getX();
        int y = pair.getY();
        newPair = new Pair(x + 1, y + 1);

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }
    
    public static void TopLeft(Card card,Pair pair) {
    	  Pair newPair;
          int x = pair.getX();
          int y = pair.getY();
          newPair = new Pair(x - 1, y - 1);


        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }
    
    public static void BottomLeft(Card card,Pair pair) {
        Pair newPair;
        int x = pair.getX();
        int y = pair.getY();
        newPair = new Pair(x - 1, y + 1);

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }

    public static void TopRight(Card card,Pair pair) {
        Pair newPair;
        int x = pair.getX();
        int y = pair.getY();
        newPair = new Pair(x + 1, y - 1);

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);  
    }
	public static Card[] ajoutercarteplateau() {
		Collection<Card> values = plateau.values();
	    Card[] cards = values.toArray(new Card[0]);
	    return cards;
	}
	public static int Score() {
		int score =0;
		 HashMap<Pair, Card> pairplateau = getPlateau();
		 List<Card> cardplateau= new ArrayList<>(pairplateau.values());
		 for(Card searchcard : cardplateau) {
			 
		 }
		 return  score;
	}
	
	//public static RessourceCard firstCard = new RessourceCard("animal", "void", "animal", "void", null, 2); 
	}
