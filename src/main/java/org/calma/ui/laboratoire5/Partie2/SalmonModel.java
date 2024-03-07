package org.calma.ui.laboratoire5.Partie2;

import java.util.ArrayList;

public class SalmonModel {
    private int currentIndex;
    private final int STARTTURNS = 3;
    private ArrayList<String> sequence;
    private final String[] COLORS = {"g", "r", "y", "b"};
    private boolean gameOn;

    private int nbCoups;

    private final int NB_COUPS_MAX = 12;

    public SalmonModel(){
        this.setCurrentIndex(0);
        this.setSequence(new ArrayList<String>());
        this.setGameOn(false);
        this.setNbCoups(0);
    }

    public void start(){
        this.getSequence().clear();

        generateRandomSequence();

        setGameOn(true);

        this.setCurrentIndex(0);
    }

    private void generateRandomSequence(){
        for(int i = 0; i < STARTTURNS; i++){
            addToSequence();
        }
    }

    public boolean checkSequence(String color){
        if((sequence.get(this.getCurrentIndex())).equals(color)){
            if(this.getCurrentIndex() == (sequence.size()-1)){
                this.setNbCoups(this.getNbCoups() + 1);

                if(this.getNbCoups() == NB_COUPS_MAX){
                    endGame();
                    this.setNbCoups(0);
                    return true;
                }

                endGame();
                return true;
            }
            this.setCurrentIndex(this.getCurrentIndex() + 1);

            return true;
        }

        else{
            System.out.println("Dans else");
            endGame();
            this.setNbCoups(0);
            return false;
        }
    }

    public void endGame(){
        this.setCurrentIndex(0);
        this.setSequence(new ArrayList<>());

        setGameOn(false);
    }

    public void addToSequence(){
        this.getSequence().add(randomColor());
    }

    private String randomColor(){
        return COLORS[(int) (Math.random()*4)];
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public ArrayList<String> getSequence() {
        return sequence;
    }

    public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public int getNbCoups() {
        return nbCoups;
    }

    public void setNbCoups(int nbCoups) {
        this.nbCoups = nbCoups;
    }
}