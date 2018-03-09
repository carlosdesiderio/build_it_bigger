package uk.me.desiderio.javajokes;

import java.util.Random;

public class Joker {

    private Random random;
    private String[] jokes;

    public Joker() {
        this(new DefaultJokeProfider());
    }

    public Joker(JokeProvider provider) {
        this.jokes = provider.getJokes();
        this.random = new Random();
    }

    public String getNextJoke() {
        int index = random.nextInt(jokes.length);
        return jokes[index];
    }
}
