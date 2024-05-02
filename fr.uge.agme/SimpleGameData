package fr.uge.game;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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
	
	private HashMap<Pair, Card> plateau;
	private HashMap<Integer, Pair> ordre;
	
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
		
		// carte de départ
		plateau.put(new Pair(0, 0), piocheRessource(getPackRessource()));
		plateau.put(new Pair(1, 1), piocheRessource(getPackRessource()));
		plateau.put(new Pair(2, 2), piocheRessource(getPackRessource()));
		plateau.put(new Pair(-1, -1), piocheRessource(getPackRessource()));
		plateau.put(new Pair(-1, 1), piocheRessource(getPackRessource()));
		
		//ordre.put(0, new Pair(0, 0));
		ordre.put(1, new Pair(1, 1));
		ordre.put(2, new Pair(2, 2));
		ordre.put(3, new Pair(-1, -1));
		ordre.put(4, new Pair(-1, 1));
		
	}
	
	
	
	public static ArrayList<RessourceCard> melangeRessource(ArrayList<RessourceCard> packRessource) {
        Random rand = new Random();
        Collections.shuffle(packRessource, rand);
        return packRessource;
   }

   public static RessourceCard piocheRessource(ArrayList<RessourceCard> packRessource) {
       RessourceCard carte1 = packRessource.remove(0);
       return carte1;
   }
   public static ArrayList<GoldenCard> melangeGolden(ArrayList<GoldenCard> packGolden) {
        Random rand = new Random();
        Collections.shuffle(packGolden, rand);
        return packGolden;
   }

   public static GoldenCard piocheGolden(ArrayList<GoldenCard> packGolden) {
       GoldenCard carte1 = packGolden.remove(0);
       return carte1;
   }

	public RessourceCard[] getRessourceTable() {
		return ressourceTable;
	}

	public GoldenCard[] getGoldenTable() {
		return goldenTable;
	}
	
	public Card[] getMainTable() {
		return mainTable;
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
	
	public HashMap<Integer, Pair> getOrdre() {
		return ordre;
	}
	
	public void removeCardFromMainTable(int index) {
	    if (index >= 0 && index < mainTable.length) {
	        // Créer un nouveau tableau avec une taille inférieure de 1
	        Card[] newMainTable = new Card[mainTable.length - 1];
	        System.arraycopy(mainTable, 0, newMainTable, 0, index);
	        System.arraycopy(mainTable, index + 1, newMainTable, index, mainTable.length - index - 1);
	        mainTable = newMainTable;
	        System.out.println("Bien suprimé");
	        displayMainTable(mainTable);
	    } else {
	        System.out.println("L'indice fourni est invalide.");
	    }
	}
	public void addCardToMainTable(Card card) {
	    Card[] newMainTable = new Card[mainTable.length + 1];
	    System.arraycopy(mainTable, 0, newMainTable, 0, mainTable.length);
	    newMainTable[mainTable.length] = card;
	    mainTable = newMainTable;
	}

	public static void displayMainTable(Card[] mainTable) {
	    System.out.println("Contenu de mainTable :");
	    for (Card card : mainTable) {
	        System.out.println(card);
	    }
	}

	//public static RessourceCard firstCard = new RessourceCard("animal", "void", "animal", "void", null, 2); 
	}
