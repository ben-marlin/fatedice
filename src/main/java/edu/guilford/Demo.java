package edu.guilford;

// a FATE die roll results in either +, -, or blank
// four dice are rolled, each + adds, each - subtracts
// results will range between -4 and +4, with larger
// values being less likely

// if unicode will not display on your monitor, use '+', '-', and ' '

import java.util.Random;

public class Demo {
    
    public static void main(String[] args) {
        System.out.println("Rolling FATE dice...");
        
        // create the dice & roll them
        char[] dice = new char[4];

        for (int i = 0; i < 4; i++) {
            dice[i] = roll();
        }

        // count the plus
        int plus = 0;
        for(int i = 0; i < 4; i++) {
            if (dice[i] == '\u229E') {
                plus++;
            }
        }

        // count the minus
        int minus = 0;
        for(int i = 0; i < 4; i++) {
            if (dice[i] == '\u229F') {
                minus++;
            }
        }

        // calculate roll
        int total = plus - minus;

        // print results
        for (int i = 0; i < 4; i++) {
            System.out.print(dice[i] + " ");
        }

        System.out.println("\tTotal = " + total);
    }

    private static char roll() {
        Random rand = new Random();             // randomize choice
        int choice = rand.nextInt(3);

        char placeHolder = '\u25A1';                 // default option
        
        if (choice == 0) {
            placeHolder = '\u229E';
        }

        if (choice == 1) {
            placeHolder = '\u229F';
        }

        return placeHolder;
    }
}