import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {

    // GUI window and layout
    private JFrame window;
    private JPanel layout;

    // GUI objects
    private JLabel gameTitle; // title screen
    private JButton startGame;

    private JLabel quoteNum; // quote screen
    private JLabel livesNum;
    private JTextArea quoteText;
    private JLabel hintsNum;
    private JButton getHint;
    private JTextArea hintText;
    private JLabel submitLabel;
    private JTextField guessBox;
    private JButton submitGuess;

    private JLabel correctLabel; // result screen
    private JButton nextButton;

    private JLabel endLabelA; // end screen
    private JLabel endLabelB;
    private JButton endButton;

    // Quotes class
    private Quotes quotes;

    // Game attributes
    private int quoteIndex;
    private int correctGuesses;
    private int neededToWin;
    private int correctStreak;
    private int lives;
    private int hints;
    private String currentQuote;

    public GUI() {
        // create GUI window and layout
        window = new JFrame();
        layout = new JPanel();

        // Initialize quotes and game attributes
        quotes = new Quotes();
        quotes.initialize();
        quoteIndex = 0;
        correctGuesses = 0;
        correctStreak = 0;
        hints = 0;
        neededToWin = 5;
        lives = 3;

        // initialize GUI window and layout
        window.setSize(500, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Movie Guesser");
        layout.setLayout(null);
        window.add(layout);

        // gameTitle - label for title of game
        gameTitle = new JLabel("<html><h1>Java Movie Guesser</h1></html>", JLabel.CENTER);
        gameTitle.setBounds(100,120,300,40);
        layout.add(gameTitle);

        // startGame - button for starting the game
        startGame = new JButton("Start Game");
        startGame.setBounds(200,170,100,30);
        startGame.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // This button should remove all the components from the screen and add the ones for the game
                    layout.remove(gameTitle);
                    layout.remove(startGame);

                    // This button should call the quoteScreen method
                    quoteScreen();
                }
            }
        );
        layout.add(startGame);

        window.setVisible(true);
    }
    public void quoteScreen() {
        // New components: quoteNum, livesNum, quoteText, hintsNum, submitLabel, guessBox, submitGuess
        // getHint is added based on game conditions
        // hintText is added based on player actions

        // quoteNum - label for quote's order number
        quoteNum = new JLabel("Quote No. " + (quoteIndex + 1));
        quoteNum.setBounds(100,40,100,30);
        layout.add(quoteNum);

        // livesNum - label for the number of lives
        livesNum = new JLabel("Lives: " + lives, JLabel.RIGHT);
        livesNum.setBounds(300,40,100,30);
        layout.add(livesNum);

        // quoteText - text area for the quote in question
        currentQuote = quotes.getNextQuote(quoteIndex);
        quoteIndex++;
        quoteText = new JTextArea("\"" + currentQuote + "\"",5,5);
        quoteText.setBounds(100,75,300,70);
        quoteText.setEditable(false);
        quoteText.setLineWrap(true);
        quoteText.setWrapStyleWord(true);
        layout.add(quoteText);

        // hintsNum - label for the number of hints available
        hintsNum = new JLabel("Hints: " + (hints));
        hintsNum.setBounds(100,155,100,30);
        layout.add(hintsNum);

        // getHint - button for using a hint
        getHint = new JButton("Get hint");
        getHint.setBounds(160,155,80,32);
        getHint.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // This button should remove the hintsNum and getHint components and add the hintText component
                    layout.remove(hintsNum);
                    layout.remove(getHint);

                    String currentHint = quotes.getHint(currentQuote);

                    // hintText - text area for the movie hint
                    hintText = new JTextArea("Hint: " + currentHint,5,5);
                    hintText.setBounds(100,155,300,45);
                    hintText.setEditable(false);
                    hintText.setLineWrap(true);
                    hintText.setWrapStyleWord(true);
                    layout.add(hintText);

                    hints--;

                    layout.revalidate();
                    layout.repaint();
                }
            }
        );
        if (hints > 0) {
            layout.add(getHint);
        }

        // submitLabel - label for the player's text field
        submitLabel = new JLabel("Enter guess:");
        submitLabel.setBounds(100,200,100,30);
        layout.add(submitLabel);

        // guessBox - text field for the player's movie guess
        guessBox = new JTextField();
        guessBox.setBounds(100,230,300,30);
        layout.add(guessBox);

        // submitGuess - button for the player to submit their movie guess
        submitGuess = new JButton("Submit guess");
        submitGuess.setBounds(190,270,120,35);
        submitGuess.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (layout.isAncestorOf(hintsNum)) {
                        layout.remove(hintsNum);
                    }
                    if (layout.isAncestorOf(getHint)) {
                        layout.remove(getHint);
                    }
                    if (layout.isAncestorOf(hintText)) {
                        layout.remove(hintText);
                    }

                    // This button should call the submissionResult method
                    submissionResult();
                }
            }
        );
        layout.add(submitGuess);

        layout.revalidate();
        layout.repaint();
    }
    public void submissionResult() {
        String guess = guessBox.getText();
        boolean guessCorrect = quotes.checkGuess(currentQuote, guess);

        // This method should remove all the components from the screen and add the ones for the submission result
        layout.remove(quoteNum);
        layout.remove(livesNum);
        layout.remove(quoteText);
        layout.remove(submitLabel);
        layout.remove(guessBox);
        layout.remove(submitGuess);

        // New components: correctLabel, nextButton
        String labelText;
        String buttonText = "Next Quote";
        if (guessCorrect) {
            // The guess made by the user is correct
            labelText = "<html><h2>Your guess is CORRECT!</h2></html>";
            correctGuesses++;
            correctStreak++;
            if (correctGuesses == neededToWin) {
                buttonText = "Next";
            }
            if (correctStreak == 3) {
                hints++;
                correctStreak = 0;
            }
        }
        else {
            // The guess made by the user is not correct
            labelText = "<html><h2>Your guess is incorrect.</h2></html>";
            lives--;
            if (correctStreak > 0) {
                correctStreak = 0;
            }
            if (lives == 0) {
                buttonText = "Next";
            }
        }

        // correctLabel - label for the validity of the guess
        correctLabel = new JLabel(labelText, JLabel.CENTER);
        correctLabel.setBounds(100,120,300,40);
        layout.add(correctLabel);

        // nextButton - button for proceeding
        nextButton = new JButton(buttonText);
        nextButton.setBounds(200,170,100,30);
        nextButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // This button should remove all the components from the screen and add the ones for the next screen
                    layout.remove(correctLabel);
                    layout.remove(nextButton);

                    // This button should call a different method depending on whether the game has ended
                    if (correctGuesses == neededToWin || lives == 0) {
                        // The game has ended
                        endScreen();
                    }
                    else {
                        // The game continues
                        quoteScreen();
                    }
                }
            }
        );
        layout.add(nextButton);

        layout.revalidate();
        layout.repaint();
    }
    public void endScreen() {
        // New components: endLabelA, endLabelB, endButton

        String labelTextA;
        String labelTextB;
        if (correctGuesses == neededToWin)
        {
            labelTextA = "<html><h1>YOU WON!</h1></html>";
            labelTextB = "You're a certified movie star!";
        }
        else {
            labelTextA = "<html><h1>You Lose.</h1></html>";
            labelTextB = "Try again after watching more movies!";
        }

        // endLabelA - label for the outcome of the game
        endLabelA = new JLabel(labelTextA, JLabel.CENTER);
        endLabelA.setBounds(150,120,200,40);
        layout.add(endLabelA);

        // endLabelB - label for an additional message to the player
        endLabelB = new JLabel(labelTextB, JLabel.CENTER);
        endLabelB.setBounds(100,160,300,30);
        layout.add(endLabelB);

        // endButton - button for closing the window
        endButton = new JButton("OK");
        endButton.setBounds(200,190,100,30);
        endButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // This button should remove all the components from the screen and add the ones for the next screen
                    window.dispose();
                }
            }
        );
        layout.add(endButton);

        layout.revalidate();
        layout.repaint();
    }
    public static void main(String[] args) throws Exception {
        new GUI();
    }
}
