package org.calma.ui.laboratoire5;

import java.util.ArrayList;

public class SalmonModel {
    private int currentIndex;
    private final int STARTTURNS = 3;
    private ArrayList<String> sequence;
    private final String[] COLORS = {"g", "r", "y", "b"};
    private boolean gameOn;

    public SalmonModel(){
        currentIndex = 0;

        sequence = new ArrayList<String>();

        setGameOn(false);
    }

    public void start(){
        sequence.clear();

        generateRandomSequence();

        setGameOn(true);

        currentIndex = 0;
    }

    private void generateRandomSequence(){
        for(int i = 0; i < STARTTURNS; i++){
            addToSequence();
        }
    }

    public boolean checkSequence(String color){
        if((sequence.get(this.getCurrentIndex())).equals(color)){
            if(currentIndex == (sequence.size()-1)){
                endGame();
                return true;
            }

            currentIndex++;
            return true;
        }

        else{
            endGame();

            return false;
        }
    }

    public void endGame(){
        this.setCurrentIndex(0);

        this.setSequence(new ArrayList<>());

        setGameOn(false);
    }

    public void addToSequence(){
        sequence.add(randomColor());
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

}