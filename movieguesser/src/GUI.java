import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {

    private JFrame window;
    private JPanel layout;

    private JLabel gameTitle;
    private JLabel quoteNum;
    private JLabel quoteLabel;
    private JLabel correctLabel;
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
        
        gameTitle = new JLabel("Java Movie Guesser");
        gameTitle.setBounds(150,120,200,30);
        layout.add(gameTitle);

        startGame = new JButton("Start Game");
        startGame.setBounds(200,160,100,30);
        startGame.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // This button should call the quoteScreen method
                    quoteScreen();
                }
            }
        );
        layout.add(startGame);

        window.setVisible(true);
    }
    public void quoteScreen() {
        // This button should remove all the components from the screen and add the ones for the game
        layout.remove(gameTitle);
        layout.remove(startGame);

        quoteNum = new JLabel("Quote No. " + (quoteIndex + 1));
        quoteNum.setBounds(100,90,100,30);
        layout.add(quoteNum);

        layout.revalidate();
        layout.repaint();
    }
    public static void main(String[] args) throws Exception {
        new GUI();
    }
}
