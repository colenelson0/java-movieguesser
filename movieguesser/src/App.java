public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Test");
        Quotes quotes = new Quotes();
        quotes.initialize();
        String quote = quotes.getNextQuote(0);
        System.out.println(quote);
        System.out.println(quotes.getHint(quote));
        System.out.println(quotes.checkGuess(quote, "Movie 1"));
        System.out.println(quotes.checkGuess(quote, "Movie 2"));
        System.out.println(quotes.checkGuess(quote, "Movie 3"));
        System.out.println(quotes.checkGuess(quote, "Movie 4"));
        System.out.println(quotes.checkGuess(quote, "Movie 5"));
        quote = quotes.getNextQuote(1);
        System.out.println(quote);
        System.out.println(quotes.getHint(quote));
        System.out.println(quotes.checkGuess(quote, "Movie 1"));
        System.out.println(quotes.checkGuess(quote, "Movie 2"));
        System.out.println(quotes.checkGuess(quote, "Movie 3"));
        System.out.println(quotes.checkGuess(quote, "Movie 4"));
        System.out.println(quotes.checkGuess(quote, "Movie 5"));
        quote = quotes.getNextQuote(2);
        System.out.println(quote);
        System.out.println(quotes.getHint(quote));
        System.out.println(quotes.checkGuess(quote, "Movie 1"));
        System.out.println(quotes.checkGuess(quote, "Movie 2"));
        System.out.println(quotes.checkGuess(quote, "Movie 3"));
        System.out.println(quotes.checkGuess(quote, "Movie 4"));
        System.out.println(quotes.checkGuess(quote, "Movie 5"));
    }
}
