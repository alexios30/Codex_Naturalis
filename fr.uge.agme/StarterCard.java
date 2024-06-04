package fr.uge.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class StarterCard implements Card {
    private final String rectoCornerTopLeft;
    private final String rectoCornerBottomLeft;
    private final String rectoCornerTopRight;
    private final String rectoCornerBottomRight;
    private final String versoCornerTopLeft;
    private final String versoCornerBottomLeft;
    private final String versoCornerTopRight;
    private final String versoCornerBottomRight;
    private final String[] versoResources;
    private boolean verso;

    public StarterCard(
        String rectoCornerTopLeft,
        String rectoCornerBottomLeft,
        String rectoCornerTopRight,
        String rectoCornerBottomRight,
        String versoCornerTopLeft,
        String versoCornerBottomLeft,
        String versoCornerTopRight,
        String versoCornerBottomRight,
        String[] versoResources
    ) {
        this.rectoCornerTopLeft = Objects.requireNonNull(rectoCornerTopLeft);
        this.rectoCornerBottomLeft = Objects.requireNonNull(rectoCornerBottomLeft);
        this.rectoCornerTopRight = Objects.requireNonNull(rectoCornerTopRight);
        this.rectoCornerBottomRight = Objects.requireNonNull(rectoCornerBottomRight);
        this.versoCornerTopLeft = Objects.requireNonNull(versoCornerTopLeft);
        this.versoCornerBottomLeft = Objects.requireNonNull(versoCornerBottomLeft);
        this.versoCornerTopRight = Objects.requireNonNull(versoCornerTopRight);
        this.versoCornerBottomRight = Objects.requireNonNull(versoCornerBottomRight);
        this.versoResources = Objects.requireNonNull(versoResources);
        this.verso = false;
    }

    // Getters
    public String getRectoCornerTopLeft() {
        return rectoCornerTopLeft;
    }

    public String getRectoCornerBottomLeft() {
        return rectoCornerBottomLeft;
    }

    public String getRectoCornerTopRight() {
        return rectoCornerTopRight;
    }

    public String getRectoCornerBottomRight() {
        return rectoCornerBottomRight;
    }

    public String getVersoCornerTopLeft() {
        return versoCornerTopLeft;
    }

    public String getVersoCornerBottomLeft() {
        return versoCornerBottomLeft;
    }

    public String getVersoCornerTopRight() {
        return versoCornerTopRight;
    }

    public String getVersoCornerBottomRight() {
        return versoCornerBottomRight;
    }

    public List<String> getRectoCorner() {
    	ArrayList<String> rectoCorner = new ArrayList<>();
    	rectoCorner.add(rectoCornerTopLeft);
    	rectoCorner.add(rectoCornerBottomLeft);
    	rectoCorner.add(rectoCornerTopRight);
    	rectoCorner.add(rectoCornerBottomRight);
		return rectoCorner;
    }
    
    public List<String> getVersoCorner() {
    	ArrayList<String> versoCorner = new ArrayList<>();
    	versoCorner.add(versoCornerTopLeft);
    	versoCorner.add(versoCornerBottomLeft);
    	versoCorner.add(versoCornerTopRight);
    	versoCorner.add(versoCornerBottomRight);
		return versoCorner;
    }
    
    
    public String[] getVersoResources() {
        return versoResources;
    }

    @Override
    public ArrayList<String> getCorner() {
    	ArrayList<String> list = new ArrayList<>();
    	list.add(rectoCornerTopLeft);
    	list.add(rectoCornerBottomLeft);
    	list.add(rectoCornerTopRight);
    	list.add(rectoCornerBottomRight);
    	list.add(versoCornerTopLeft);
    	list.add(versoCornerBottomLeft);
    	list.add(versoCornerTopRight);
    	list.add(versoCornerBottomRight);
        return list;
    }

    @Override
    public String cornerBottomLeft() {
        return verso ? versoCornerBottomLeft : rectoCornerBottomLeft;
    }

    @Override
    public String cornerTopLeft() {
        return verso ? versoCornerTopLeft : rectoCornerTopLeft;
    }

    @Override
    public String cornerBottomRight() {
        return verso ? versoCornerBottomRight : rectoCornerBottomRight;
    }

    @Override
    public String cornerTopRight() {
        return verso ? versoCornerTopRight : rectoCornerTopRight;
    }

    @Override
    public String getKingdom() {
        return "Starter";
    }
    

    @Override
    public RessourceCard versoCard() {
        return null;
    }

    @Override
    public boolean isVerso() {
        return verso;
    }

    @Override
    public void setVerso(boolean verso) {
        this.verso = verso;
    }
    
    @Override
    public String toString() {
        return "StarterCard{" +
            "rectoCornerTopLeft='" + rectoCornerTopLeft + '\'' +
            ", rectoCornerBottomLeft='" + rectoCornerBottomLeft + '\'' +
            ", rectoCornerTopRight='" + rectoCornerTopRight + '\'' +
            ", rectoCornerBottomRight='" + rectoCornerBottomRight + '\'' +
            ", versoCornerTopLeft='" + versoCornerTopLeft + '\'' +
            ", versoCornerBottomLeft='" + versoCornerBottomLeft + '\'' +
            ", versoCornerTopRight='" + versoCornerTopRight + '\'' +
            ", versoCornerBottomRight='" + versoCornerBottomRight + '\'' +
            ", versoResources=" + Arrays.toString(versoResources) +
            ", verso=" + verso +
            '}';
    }

	@Override
	public int getrealscoring() {
		return 0;
	}

	@Override
	public HashMap<String, Integer> returncost() {
		return null;
	}

	
}
