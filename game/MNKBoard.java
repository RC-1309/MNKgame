package game;

import java.util.Arrays;

public class MNKBoard implements Board {
    protected Cell[][] cells;
    protected int numOfEmptyCell;
    private static final Cell[] SYMBOLS = {Cell.X, Cell.O, Cell.M, Cell.P};
    protected Cell turn;
    protected final int n;
    protected final int m;
    protected final int k;
    protected Position position;

    public MNKBoard(int n, int m, int k) {
        this.k = k;
        this.n = n;
        this.m = m;
        clear();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public Board createNewBoard() {
        return new MNKBoard(n, m, k);
    }

    @Override
    public void clear() {
        numOfEmptyCell = n * m;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = SYMBOLS[0];
        position = new MNKPosition(this);
    }

    public Cell getCell(int idxPLayer) {
        return SYMBOLS[idxPLayer - 1];
    }

    @Override
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    private boolean checkPos(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    protected boolean checkRes(int x, int y, int chX, int chY) {
        int numOfEq = 0;
        x -= k * chX;
        y -= k * chY;
        for (int i = 0; i < 2 * k; i++) {
            while (checkPos(x, y) && turn == cells[x][y]) {
                numOfEq++;
                x += chX;
                y += chY;
            }
            if (numOfEq >= k) {
                return true;
            }
            x += chX;
            y += chY;
            numOfEq = 0;
        }
        return false;
    }

    @Override
    public Result makeMove(final Move move, int playerNumber) {
        if (!position.isValid(move)) {
            System.out.println("It was incorrect move: " + move);
            return Result.LOSE;
        }

        int x = move.getRow();
        int y = move.getColumn();
        turn = getCell(playerNumber);
        cells[x][y] = turn;

        boolean isWin = checkRes(x, y, 1, 0);
        isWin |= checkRes(x, y, 0, 1);
        isWin |= checkRes(x, y, 1, 1);
        isWin |= checkRes(x, y, 1, -1);

        numOfEmptyCell--;
        return isWin ? Result.WIN : (numOfEmptyCell > 0 ? Result.UNKNOWN : Result.DRAW);
    }
}
