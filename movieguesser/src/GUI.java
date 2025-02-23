import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {

    private JFrame window;
    private JPanel layout;

    private JLabel gameTitle;
    private JLabel quoteNum;
    private JLabel submitLabel;
    private JLabel correctLabel;
    private JTextArea quoteText;
    private JButton startGame;
    private JButton submitGuess;
    private JButton nextButton;
    private JTextField guessBox;

    private Quotes quotes;

    private int quoteIndex;
    private int lives;
    private String currentQuote;

    public GUI() {
        window = new JFrame();
        layout = new JPanel();

        quotes = new Quotes();
        quotes.initialize();
        quoteIndex = 0;

        window.setSize(500, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Movie Guesser");
        window.add(layout);

        layout.setLayout(null);
        
        gameTitle = new JLabel("Java Movie Guesser", JLabel.CENTER);
        gameTitle.setBounds(150,120,200,30);
        layout.add(gameTitle);

        startGame = new JButton("Start Game");
        startGame.setBounds(200,160,100,30);
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
        // New components: quoteNum, quoteText, submitLabel, guessBox, submitGuess

        quoteNum = new JLabel("Quote No. " + (quoteIndex + 1));
        quoteNum.setBounds(100,60,100,30);
        layout.add(quoteNum);

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
        layout.remove(quoteText);
        layout.remove(submitLabel);
        layout.remove(guessBox);
        layout.remove(submitGuess);

        // New components: correctLabel, nextButton
        String labelText;
        if (guessCorrect) {
            // The guess made by the user is correct
            labelText = "Your guess is CORRECT!";
        }
        else {
            // The guess made by the user is not correct
            labelText = "Your guess is incorrect.";
        }
        correctLabel = new JLabel(labelText, JLabel.CENTER);
        correctLabel.setBounds(150,120,200,30);
        layout.add(correctLabel);

        nextButton = new JButton("Next Quote");
        nextButton.setBounds(200,160,100,30);
        nextButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // This button should remove all the components from the screen and add the ones for the next screen
                    layout.remove(correctLabel);
                    layout.remove(nextButton);

                    // This button should call the quoteScreen method
                    quoteScreen();
                }
            }
        );
        layout.add(nextButton);

        layout.revalidate();
        layout.repaint();
    }
    public static void main(String[] args) throws Exception {
        new GUI();
    }
}
