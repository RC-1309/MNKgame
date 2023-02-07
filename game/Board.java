package game;

public interface Board {
    Position getPosition();

    Cell getCell(int playerNumber);

    Cell getCell(int x, int y);

    Result makeMove(Move move, int playerNumber);

    int getN();

    int getM();

    int getK();

    Board createNewBoard();

    void clear();
}
