 package fr.uge.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

public class GoldenCard implements Card {
    private final String cornerTopLeft;
    private final String cornerBottomLeft;
    private final String cornerTopRight;
    private final String cornerBottomRight;
    private final String kingdom;
    private final HashMap<String, Integer> cost;
    private final char typescoring;
    private final char scoring;
    private boolean verso;

    public GoldenCard(String cornerTopLeft, String cornerBottomLeft, String cornerTopRight, String cornerBottomRight, String kingdom, HashMap<String, Integer> cost, char typescoring, char scoring, boolean verso) {
        this.cornerTopLeft = Objects.requireNonNull(cornerTopLeft);
        this.cornerBottomLeft = Objects.requireNonNull(cornerBottomLeft);
        this.cornerTopRight = Objects.requireNonNull(cornerTopRight);
        this.cornerBottomRight = Objects.requireNonNull(cornerBottomRight);
        this.kingdom = Objects.requireNonNull(kingdom);
        this.cost = Objects.requireNonNull(cost);
        this.typescoring = typescoring;
        this.scoring = scoring;
        this.verso = verso;
    }

    public RessourceCard versoCard() {
        return new RessourceCard("Empty", "Empty", "Empty", "Empty", kingdom, "None", 1, true);
    }


    public String generateCost(HashMap<String, Integer> map) {
        StringBuilder result = new StringBuilder();
        for (Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();
            char letter = switch (key) {
                case "Animal" -> 'A';
                case "Fungi" -> 'F';
                case "Insect" -> 'I';
                case "Plant" -> 'P';
                default -> 0;
            };
            for (int i = 0; i < count; i++) {
                result.append(letter);
            }
        }
        return result.toString();
    }

    public String cornerTopLeft() {
        return cornerTopLeft;
    }

    public String cornerBottomLeft() {
        return cornerBottomLeft;
    }

    public String cornerTopRight() {
        return cornerTopRight;
    }

    public String cornerBottomRight() {
        return cornerBottomRight;
    }

    public String getKingdom() {
        return kingdom;
    }

    public HashMap<String, Integer> getCost() {
        return cost;
    }

    public char gettypescoring() {
        return typescoring;
    }

    public char getScoring() {
        return scoring;
    }

    public boolean isVerso() {
        return verso;
    }

    public void setVerso(boolean verso) {
        this.verso = verso;
    }

    @Override
    public String toString() {
        return "GoldenCard [cornerTopLeft=" + cornerTopLeft + ", cornerBottomLeft=" + cornerBottomLeft
                + ", cornerTopRight=" + cornerTopRight + ", cornerBottomRight=" + cornerBottomRight + ", kingdom="
                + kingdom + ", cost=" + cost + ", typescoring=" + typescoring + ", scoring=" + scoring + ", verso=" + verso + "]" + "\n";
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
        char type=gettypescoring();
        int resultat=0;
         String lettre = Character.toString(type);
         
         char chiffre= getScoring();
         String chiffre1 = Character.toString(chiffre);
         int convertedNumber = Integer.parseInt(chiffre1);

         if(lettre.equals("D")) {
             resultat=convertedNumber;
         }if(lettre.equals("C")) {
             resultat=convertedNumber;
         }if(lettre.equals("Q")) {
             int quill=SimpleGameData.returnQuill();
             resultat=convertedNumber*quill;
         }if(lettre.equals("M")) {
             int manuscript=SimpleGameData.returnManuscript();
             resultat=convertedNumber*manuscript;
         }if(lettre.equals("I")) {
             int inkwell= SimpleGameData.returnInkwell();
             resultat=convertedNumber*inkwell;
         }
         return resultat;
    }

}
