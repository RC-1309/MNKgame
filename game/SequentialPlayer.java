package game;

public class SequentialPlayer implements Player {
    private static final String name = "Sequential";

    static String getName() {
        return name;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        for (int r = 0; r < position.getN(); r++) {
            for (int c = 0; c < position.getM(); c++) {
                final Move move = new Move(r, c);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
