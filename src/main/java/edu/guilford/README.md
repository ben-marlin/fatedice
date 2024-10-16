# Project XX: The FATE Dice Roller

I know you're probably tired of rolling dice, but this is a new twist. In a roleplaying game called FATE, we frequently roll 4 dice. The sides are labeled ⊞, ⊟, □. Each plus adds 1 to the roll, each minus subtracts 1. The total ranges between -4 and +4, with the more extreme values being less likely.

## Sample Code

I've produced a sample version of the program that operates in the terminal. Run it! You should get results from -4 to +4, as well as listing the individual dice rolls.

Notice how much past code ideas we're using.

- array of char to hold rolls
- method to return each die roll
- randomizer to choose values
- counters for both plus & minus
- arithmetic
- print statement

In some earlier projects, we assumed unicode characters display on your computer. If they don't, it's up to you to substitute +, -, and a blank space where appropriate. Alternately, you might want to research how to get unicode characters to display.

As in the previous project, we'd like to see something more visual. How about a button to roll it and labels to display the results?

## Build & Instantiate the Frame

We've done this before. Use `File > New File > New Java File > Class` and name it FateDiceRoller. Let VSC supply the .java for you so you don't have a typo.

Add the package. Rather than risk problems later, let's add these imports.
```
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
```
Modify the signature for the class to extend JFrame. Add a constructor.

As part of the class, but preceding the constructor, add the following declarations. This will allow us to access these fields directly in all the methods of the class.
```
private static final String[] DICE_RESULTS = { "\u229E", "\u229F", "\u2751" }; // ⊞, ⊟, □
private final Random random = new Random();
private final JLabel[] diceLabels = new JLabel[4];
```
Having DICE_RESULTS pre-defined saves us some typing later. The labels will be used to display the dice rolls. A single randomizer saves some resources.

Inside the constructor, add the following code.
```
// Set up JFrame
setTitle("FATE Dice Roller");
setSize(400, 200);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout());
```
The first three should be self-explanatory. The width of 400 pixels looked good to me, but your monitor may be different. 

The last one simply tells the JFrame how to arrange things. There are five panes: NORTH, SOUTH, EAST, WEST, and CENTER. The center is bigger than the others, and if any panes are empty, adjacent ones expand to fill it. There's nothing in them now, so it won't affect anything.

## Making it run

Add a method at the bottom with the signature `public static void main(String[] args)`. In the body of it, just put `new FateDiceRoller();`. Because you don't need to do anything with the object, creating a new one will let you run the program.

Test it now! It should just be a blank gray window, but at least it's something! Remember our mantra: create a minimal working example, then change it incrementally. Something new should happen at each step.

## Create the Labels

These labels are going to display the dice rolls. Recall that we declared them when creating the class. Now we will instantiate them in the constructor. After the layout line, add this code.
```
// Initialize 4 JLabel squares for dice results
for (int i = 0; i < 4; i++) {
    diceLabels[i] = new JLabel(DICE_RESULTS[2], SwingConstants.CENTER);  // Start with blank square
    diceLabels[i].setFont(new Font("Arial", Font.PLAIN, 100));  // Large font for symbols
}
```
I chose the font size 100 because it was easy for my eyes. If you adjust it up or down, you may want to adjust the width of 400 appropriately.

## Pack the Labels in a Panel

After the layout line, but before the for loop, add the following code.
```
// Panel to display the dice results
JPanel dicePanel = new JPanel();
dicePanel.setLayout(new GridLayout(1, 4, 10, 10));  // 1x4 grid with spacing
```
This will create a "container" that's going to hold the buttons laid out like a grid. The 1x4 should be clear (although I think it was assembled backwards) and the 10s represent "padding" on each spot. To add the buttons to the panel, you need to add the following inside the for loop: `dicePanel.add(diceLabels[i]);`.

## Add the Panel

After the for loop, add the following.
```
add(dicePanel, BorderLayout.CENTER);
```
This puts the dicePanel into the center of the window. The dicePanel contains the four labels that are displaying squares. At this stage, you should be able to test it again. There should be four squares on the gray background now. Not exciting, but it's progress. Remember, incremental change!

## Create & Add the Button

We want a button that users will press to roll the dice, so we add this code after the panel code.
```
// Button to roll the dice
JButton rollButton = new JButton("Roll Dice");
add(rollButton, BorderLayout.SOUTH);
```
You should test it at this point - it should make four squares in the middle and a button across the bottom. The button doesn't do anything, but we'll fix that.

## Make the Button Click

Add `rollButton.addActionListener(new RollDiceAction());` after adding the rollButton. You'll get an error because you haven't defined what this action listener thing should do. 

An ActionListener is a class which is fairly abstract. Whereas buttons and labels are things we can see, the ActionListener is something that resides in memory waiting for you to click the button it's listening to. Then it runs its one method, actionPerformed. To implement this, add the following code after the constructor but before the main method.
```
// Action listener to handle dice rolling
private class RollDiceAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
```
This is an example of a private class, one that is specific to our FateDiceRoller class. It seems a little weird to have a class inside of a class, but it's a common programming trick. In retrospect, maybe our Card and Deck example should have defined Card as a private class inside of Deck.

Notice that we've left the method actionPerformed blank. That's another standard trick - make a blank or minimal method, make sure that worked, and then modify it to get what you want. Test your program here. It still doesn't do anything, but make sure it doesn't have some weird error at this point.

## Make the Click Work

Inside the body of actionPerformed, add this code.
```
// Roll each die and update the labels
for (JLabel label : diceLabels) {
    int result = random.nextInt(3);  // Randomly pick 0, 1, or 2
    label.setText(DICE_RESULTS[result]);  // Update label with corresponding symbol
}
```
This uses a new trick we haven't discussed before, but it will be familiar to anyone who has played with Python. Instead of using integers to loop through an array, this directly loops through the array itself. `JLabel label` declares a variable, then `: diceLabels` tells it what array to loop through and grabs one entry at a time, in order. It's very efficient when you have an array to work with.

As we've done before, we use the randomizer to get a number 0 to 2. Then we do another neat trick for setting the text of label. Rather than writing if statements, we use DICE_RESULTS, and read out the entry corresponding to the random result.

At this point, your button click should make the FATE dice roll. There are legitimately paid phone apps that do just this!

## Wrapping Up

Save, stage, commit, and sync this. Go to Canvas and let me know you're done.

## Challenge

If you're up for it, try to figure out how to expand on this. Add a fifth label. Using the ideas in Demo.java, total the dice results in actionPerformed. For the fifth label, make it display "= 0" when instantiated. Then, in actionPerformed, make it display "= " then + if the result is positive, and then the value of the total.