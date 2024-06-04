package fr.uge.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RessourceCard implements Card {
    private final String cornerTopLeft;
    private final String cornerBottomLeft;
    private final String cornerTopRight;
    private final String cornerBottomRight;
    private final String kingdom;
    private final String scoring;
    private final int resource;
    private boolean verso;

    public RessourceCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight, String kingdom, String scoring, int resource, boolean verso) {
        this.cornerTopLeft = Objects.requireNonNull(cornerTopLeft);
        this.cornerBottomLeft = Objects.requireNonNull(cornerBottomLeft);
        this.cornerTopRight = Objects.requireNonNull(cornerTopRight);
        this.cornerBottomRight = Objects.requireNonNull(cornerBottomRight);
        this.kingdom = Objects.requireNonNull(kingdom);
        this.scoring = Objects.requireNonNull(scoring);
        this.resource = resource;
        this.verso = verso;
    }

    public String cornerTopLeft() {
    	return cornerTopLeft;
    }
    
    public  String cornerBottomLeft() {
    	return cornerBottomLeft;
    }
    
    public String cornerTopRight() {
    	return cornerTopRight;
    }
    public String cornerBottomRight() {
    	return cornerBottomRight;
    }

    public String getScoring() {
        return scoring;
    }

    public String getKingdom() {
        return kingdom;
    }

    public int getResource() {
        return resource;
    }

    public boolean isVerso() {
        return verso;
    }

    public void setVerso(boolean verso) {
        this.verso = verso;
    }

    public RessourceCard versoCard() {
        return new RessourceCard("Empty", "Empty", "Empty", "Empty", kingdom, "None", 1, false);
    }

    @Override
    public String toString() {
        return "RessourceCard [cornerTopLeft=" + cornerTopLeft + ", cornerBottomLeft=" + cornerBottomLeft
                + ", cornerTopRight=" + cornerTopRight + ", cornerBottomRight=" + cornerBottomRight + ", kingdom="
                + kingdom + ", scoring=" + scoring + ", resource=" + resource + ", verso=" + verso + "]" + "\n";
    }

    public ArrayList<String> getCorner() {
        ArrayList<String> list = new ArrayList<>();
        list.add(cornerBottomLeft);
        list.add(cornerBottomRight);
        list.add(cornerTopLeft);
        list.add(cornerTopRight);
        return list;
    }
    
    public int getrealscoring() {
        String score= getScoring();
        int resultat =0;
        if(!score.equals("None")) {
        	 for (int i = 0; i < score.length(); i++) {
                 char lettre = score.charAt(i);
                 if(i==2) {
                	 String chiffre1 = Character.toString(lettre);
                	 int convertedNumber = Integer.parseInt(chiffre1);
                	 resultat+=convertedNumber;
                 }
             }
        }
        return resultat;
    }

    public int convertirStringInt(String score) {
          int convertedNumber = Integer.parseInt(score);
          return  convertedNumber;

    }
    public HashMap<String, Integer> returncost(){
		return null;
    	
    }
}
