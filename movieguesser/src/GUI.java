import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {

    // GUI window and layout
    private JFrame window;
    private JPanel layout;

    // GUI objects
    private JLabel gameTitle;
    private JLabel quoteNum;
    private JLabel livesNum;
    private JLabel submitLabel;
    private JLabel correctLabel;
    private JLabel resultLabelA;
    private JLabel resultLabelB;
    private JTextArea quoteText;
    private JButton startGame;
    private JButton submitGuess;
    private JButton nextButton;
    private JButton endButton;
    private JTextField guessBox;

    // Quotes class
    private Quotes quotes;

    // Game attributes
    private int quoteIndex;
    private int correctGuesses;
    private int neededToWin;
    private int correctStreak;
    private int lives;
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
        neededToWin = 5;
        lives = 3;

        // initialize GUI window and layout
        window.setSize(500, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Movie Guesser");
        layout.setLayout(null);
        window.add(layout);
        
        gameTitle = new JLabel("<html><h1>Java Movie Guesser</h1></html>", JLabel.CENTER);
        gameTitle.setBounds(100,120,300,40);
        layout.add(gameTitle);

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
        // New components: quoteNum, livesNum, quoteText, submitLabel, guessBox, submitGuess

        quoteNum = new JLabel("Quote No. " + (quoteIndex + 1));
        quoteNum.setBounds(100,60,100,30);
        layout.add(quoteNum);

        livesNum = new JLabel("Lives: " + lives, JLabel.RIGHT);
        livesNum.setBounds(300,60,100,30);
        layout.add(livesNum);

        currentQuote = quotes.getNextQuote(quoteIndex);
        quoteIndex++;
        quoteText = new JTextArea("\"" + currentQuote + "\"",5,5);
        quoteText.setBounds(100,100,300,70);
        quoteText.setEditable(false);
        quoteText.setLineWrap(true);
        quoteText.setWrapStyleWord(true);
        layout.add(quoteText);

        submitLabel = new JLabel("Enter guess:");
        submitLabel.setBounds(100,180,100,30);
        layout.add(submitLabel);

        guessBox = new JTextField();
        guessBox.setBounds(100,210,300,30);
        layout.add(guessBox);

        submitGuess = new JButton("Submit guess");
        submitGuess.setBounds(190,250,120,35);
        submitGuess.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
            labelText = "Your guess is CORRECT!";
            correctGuesses++;
            correctStreak++;
            if (correctGuesses == neededToWin)
            {
                buttonText = "Next";
            }
        }
        else {
            // The guess made by the user is not correct
            labelText = "Your guess is incorrect.";
            lives--;
            if (correctStreak > 0) {
                correctStreak = 0;
            }
            if (lives == 0)
            {
                buttonText = "Next";
            }
        }

        correctLabel = new JLabel(labelText, JLabel.CENTER);
        correctLabel.setBounds(150,120,200,30);
        layout.add(correctLabel);

        nextButton = new JButton(buttonText);
        nextButton.setBounds(200,160,100,30);
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
        // New components: resultLabel

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

        resultLabelA = new JLabel(labelTextA, JLabel.CENTER);
        resultLabelA.setBounds(150,120,200,40);
        layout.add(resultLabelA);

        resultLabelB = new JLabel(labelTextB, JLabel.CENTER);
        resultLabelB.setBounds(100,160,300,30);
        layout.add(resultLabelB);

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
