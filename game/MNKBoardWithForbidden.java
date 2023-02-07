package game;

public class MNKBoardWithForbidden extends MNKBoard {
    public MNKBoardWithForbidden(int n, int m, int k) {
        super(n, m, k);
        clear();
    }

    @Override
    public Board createNewBoard() {
        return new MNKBoardWithForbidden(super.getN(), super.getM(), super.getK());
    }

    @Override
    public void clear() {
        super.clear();
        numOfEmptyCell = n * m;
//        this.cells = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j || i == m - j - 1) {
                    cells[i][j] = Cell.F;
                    numOfEmptyCell--;
                } else {
                    cells[i][j] = Cell.E;
                }
            }
        }
//        position = new MNKPosition(this);
    }
}
