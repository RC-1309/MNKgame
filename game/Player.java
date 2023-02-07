package game;

public interface Player {
    Move move(Position position, Cell cell);

    static String getName() {
        return null;
    }
}
