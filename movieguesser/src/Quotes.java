import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Quotes {
    // Creates a hashmap to store the movie quotes
    private HashMap<String, QuoteValues> movieQuotes = new HashMap<String, QuoteValues>();
    private ArrayList<Integer> quoteOrder = new ArrayList<Integer>();

    public Quotes() {}

    public void initialize() {
        // Initialize the hashmap with quotes
        movieQuotes.put("I've abandoned my child!",new QuoteValues("There Will Be Blood","This film stars Daniel Day-Lewis."));
        movieQuotes.put("Slander is spoken. In print, it's libel.",new QuoteValues("Spider-Man","Chad Kroeger sings the end credits song for this film."));
        movieQuotes.put("A great, big, bushy beard!",new QuoteValues("Hot Fuzz","The Greater Good."));
        movieQuotes.put("Say that again...",new QuoteValues("Fantastic Four","Miles Teller says this at the end of the film."));
        movieQuotes.put("Perfectly balanced, as all things should be...",new QuoteValues("Avengers: Infinity War","Things don't end well for the heroes in this film."));
        movieQuotes.put("Oh, ho ho! You sly dog! You got me monologuing! I can't believe it...",new QuoteValues("The Incredibles","At one point, the person that said this was the protagonist's biggest fan."));
        movieQuotes.put("Some of you may die, but it's a sacrifice I am willing to make.",new QuoteValues("Shrek","This character is very short."));
        movieQuotes.put("You catch one super villain, doesn't make you a saint, does it?",new QuoteValues("Wallace and Gromit: Vengeance Most Fowl","This film features a bird as the main antagonist."));
        movieQuotes.put("Yesterday is history, tomorrow is a mystery, but today is a gift. That is why it is called the present.",new QuoteValues("Kung Fu Panda","This film was made by Dreamworks."));
        movieQuotes.put("You're still here? It's over! Go home. Go.",new QuoteValues("Ferris Bueller's Day Off","This film takes place in the city of Chicago."));

        // Initialize and shuffle the quoteOrder
        for (int i = 0; i < movieQuotes.size(); i++) {
            quoteOrder.add(i);
        }
        Collections.shuffle(quoteOrder);
    }

    // Get quote
    public String getNextQuote(int index) {
        int keyIndex = quoteOrder.get(index);
        String key = movieQuotes.keySet().toArray()[keyIndex].toString();
        return key;
    }

    // Get hint
    public String getHint(String quote) {
        String hint = movieQuotes.get(quote).hint;
        return hint;
    }

    // Check guess
    public boolean checkGuess(String quote, String guess) {
        String answer = movieQuotes.get(quote).movie;
        boolean guessCorrect = guess.equalsIgnoreCase(answer);
        return guessCorrect;
    }

}
