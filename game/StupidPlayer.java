package game;

import java.util.Random;

public class StupidPlayer implements Player {
    private static final String name = "Stupid";

    static String getName() {
        return name;
    }

    private final Random random;

    StupidPlayer() {
        random = new Random();
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        int r = random.nextInt(position.getN());
        int c = random.nextInt(position.getM());
        return new Move(r, c);
    }
}
