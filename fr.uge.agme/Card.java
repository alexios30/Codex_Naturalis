package fr.uge.game;

import java.util.ArrayList;

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

}
