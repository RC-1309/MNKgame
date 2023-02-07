package game;

import java.util.Map;

public class MNKPosition implements Position {
    private static final String LINE_FEED = System.lineSeparator();
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.M, '-',
            Cell.P, '|',
            Cell.F, 'F',
            Cell.E, '.'
    );

    private final Board board;

    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public int getM() {
        return board.getM();
    }

    @Override
    public int getK() {
        return board.getK();
    }

    @Override
    public Cell getCell(int x, int y) {
        return board.getCell(x, y);
    }

    MNKPosition(Board board) {
        this.board = board;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < board.getN()
                && 0 <= move.getColumn() && move.getColumn() < board.getM()
                && board.getCell(move.getRow(), move.getColumn()) == Cell.E;
    }

    public String toString() {
        int lenN = Integer.toString(board.getN() - 1).length();
        int lenM = Integer.toString(board.getM() - 1).length();
        final StringBuilder sb = new StringBuilder(String.format("%" + lenN + "s", ""));
        for (int i = 0; i < board.getM(); i++) {
            sb.append(String.format("%" + (lenM + 1) + "d", i));
        }
        for (int r = 0; r < board.getN(); r++) {
            sb.append(LINE_FEED);
            sb.append(String.format("%" + lenM + "d", r));
            for (int c = 0; c < board.getM(); c++) {
                sb.append(String.format("%" + lenM + "s", "")).append(SYMBOLS.get(board.getCell(r, c)));
            }
        }
        return sb.toString();
    }
}
