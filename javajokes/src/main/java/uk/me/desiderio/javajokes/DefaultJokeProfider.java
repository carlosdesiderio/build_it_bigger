package uk.me.desiderio.javajokes;

/**
 * Provides 15 funniest jokes from the fringe festival
 *
 * Jokes from https://inews.co.uk/distractions/humour/15-funniest-jokes-edinburgh-fringe-festival-list-full/
 */

public class DefaultJokeProfider implements JokeProvider{

    @Override
    public String[] getJokes() {
        return new String[]{
                "I'm not a fan of the new pound coin, but then again, I hate all change",
                "Trump’s nothing like Hitler. There’s no way he could write a book.",
                "I’ve given up asking rhetorical questions. What’s the point?",
                "I’m looking for the girl next door type. I’m just gonna keep moving house till I find her.",
                "I like to imagine the guy who invented the umbrella was going to call it the ‘brella’. But he hesitated.",
                "Combine Harvesters. And you’ll have a really big restaurant.",
                "I’m rubbish with names. It’s not my fault, it’s a condition. There’s a name for it…",
                "I have two boys, 5 and 6. We’re no good at naming things in our house.",
                "I wasn’t particularly close to my dad before he died… which was lucky, because he trod on a land mine.",
                "Whenever someone says, ‘I don’t believe in coincidences.’ I say, ‘Oh my God, me neither!",
                "A friend tricked me into going to Wimbledon by telling me it was a men’s singles event.",
                "As a vegan, I think people who sell meat are disgusting; but apparently people who sell fruit and veg are grocer.",
                "For me dying is a lot like going camping. I don’t want to do it.",
                "I wonder how many chameleons snuck onto the Ark.",
                "I went to a Pretenders gig. It was a tribute act."
        };
    }
}
