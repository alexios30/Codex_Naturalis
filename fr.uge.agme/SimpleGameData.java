package fr.uge.game;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class SimpleGameData {

	private RessourceCard[] ressourceTable; 
	private GoldenCard[] goldenTable; 
	private Card[] mainTable; 

	
	private ArrayList<RessourceCard> packRessource;
	private ArrayList<GoldenCard> packGolden;
	
	private static HashMap<Pair, Card> plateau;
	private static HashMap<Integer, Pair> ordre;
	private static HashMap<Card, Pair> coordinatesMap;
	private static HashMap<String, Integer> nbRessource;
	private static int numOrdre;
	/**private RessourceCard RessourceCard1;
	private RessourceCard RessourceCard2;
	private RessourceCard RessourceCard3;
	
	private GoldenCard GoldenCard1;
	private GoldenCard GoldenCard2;
	private GoldenCard GoldenCard3;
	
	private RessourceCard MainCard1;
	private RessourceCard MainCard2;
	private RessourceCard MainCard3;**/
	
	public SimpleGameData() {
		
		// Ouverture des fichiers pour créer les decks resssources et les dorées
		try {
			packRessource = RessourceCard.createRessourceCard(Path.of("include/Ressource.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			packGolden = GoldenCard.createRessourceCard(Path.of("include/Golden.txt"));
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
		
		
		// initialisation des pioches
		for (int i = 0; i < 3; i++) {
			ressourceTable[i] = piocheRessource(getPackRessource());
			goldenTable[i] = piocheGolden(getPackGolden());
		}
		
		// initialisation des cartes en main
		mainTable[0] = piocheRessource(getPackRessource());
		mainTable[1] = piocheRessource(getPackRessource());
		mainTable[2] = piocheGolden(getPackGolden());
		
//		// carte de départ
//		plateau.put(new Pair(0, 0), piocheRessource(getPackRessource()));
//		plateau.put(new Pair(1, 1), piocheRessource(getPackRessource()));
//		plateau.put(new Pair(2, 2), piocheRessource(getPackRessource()));
//		plateau.put(new Pair(-1, -1), piocheRessource(getPackRessource()));
//		plateau.put(new Pair(-1, 1), piocheRessource(getPackRessource()));
//
//		ordre.put(0, new Pair(0, 0));
//		ordre.put(1, new Pair(1, 1));
//		ordre.put(2, new Pair(2, 2));
//		ordre.put(3, new Pair(-1, -1));
//		ordre.put(4, new Pair(-1, 1));
		
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
	
	public HashMap<Pair, Card> getPlateau() {
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

    public static void BottomRight(Card card) {
        Pair newPair;

        if (plateau.isEmpty()) {
            newPair = new Pair(0, 0);
        } else {
            int maxX = 0;
            int maxY = 0;
            for (Pair pair : plateau.keySet()) {
                if (pair.getX() > maxX && pair.getY()>maxY) {
                    maxX = pair.getX();
                    maxY = pair.getY();
                }
            }

            newPair = new Pair(maxX + 1, maxY + 1);
        }

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }
    
    public static void TopLeft(Card card) {
        Pair newPair;

        if (plateau.isEmpty()) {
            newPair = new Pair(0, 0);
        } else {
            int minX = 0;
            int minY = 0;
            for (Pair pair : plateau.keySet()) {
                if (pair.getX() < minX && pair.getY() < minY) {
                    minX = pair.getX();
                    minY = pair.getY();
                }
            }

            newPair = new Pair(minX - 1, minY - 1);
        }

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }
    
    public static void BottomLeft(Card card) {
        Pair newPair;

        if (plateau.isEmpty()) {
            newPair = new Pair(0, 0);
        } else {
            int minX = 0;
            int maxY = 0;
            for (Pair pair : plateau.keySet()) {
                if (pair.getX() < minX) {
                    minX = pair.getX();
                }
                if (pair.getY() > maxY) {
                    maxY = pair.getY();
                }
            }

            newPair = new Pair(minX - 1, maxY + 1);
        }

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }

    public static void TopRight(Card card) {
        Pair newPair;

        if (plateau.isEmpty()) {
            newPair = new Pair(0, 0);
        } else {
            int maxX = 0;
            int minY = 0;
            for (Pair pair : plateau.keySet()) {
                if (pair.getX() > maxX) {
                    maxX = pair.getX();
                }
                if (pair.getY() < minY) {
                    minY = pair.getY();
                }
            }

            newPair = new Pair(maxX + 1, minY - 1);
        }

        numOrdre++;
        plateau.put(newPair, card);
        ordre.put(numOrdre, newPair);
    }
	public static Card[] ajoutercarteplateau() {
		Collection<Card> values = plateau.values();
	    Card[] cards = values.toArray(new Card[0]);
	    return cards;
	}
	
	//public static RessourceCard firstCard = new RessourceCard("animal", "void", "animal", "void", null, 2); 
	}
