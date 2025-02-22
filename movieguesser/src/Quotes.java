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
        movieQuotes.put("Quote 1",new QuoteValues("Movie 1","Hint 1"));
        movieQuotes.put("Quote 2",new QuoteValues("Movie 2","Hint 2"));
        movieQuotes.put("Quote 3",new QuoteValues("Movie 3","Hint 3"));
        movieQuotes.put("Quote 4",new QuoteValues("Movie 4","Hint 4"));
        movieQuotes.put("Quote 5",new QuoteValues("Movie 5","Hint 5"));

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
