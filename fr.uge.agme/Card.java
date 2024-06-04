package fr.uge.game;

import java.util.ArrayList;
import java.util.HashMap;

public interface Card {
	ArrayList<String> getCorner();
	
	String cornerBottomLeft();

	String cornerTopLeft();

	String cornerBottomRight();

	String cornerTopRight();
	
	String getKingdom();
	
	RessourceCard versoCard();
	
	boolean isVerso();
	
	void setVerso(boolean verso);
	
	int getrealscoring();

	HashMap<String, Integer>  returncost();
}
