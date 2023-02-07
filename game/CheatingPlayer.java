package game;

public class CheatingPlayer implements Player {
    private static final String name = "Cheater";

    static String getName() {
        return name;
    }

    @Override
    public Move move(Position position, Cell cell) {
        final MNKBoard board = (MNKBoard) position;
        Move first = null;
        for (int r = 0; r < position.getN(); r++) {
            for (int c = 0; c < position.getM(); c++) {
                final Move move = new Move(r, c);
                if (position.isValid(move)) {
                    if (first == null) {
                        first = move;
                    } else {
                        board.makeMove(move, 1);
                    }
                }
            }
        }
        return first;
    }
}