package game;

public class Tournament {
    private static final String NAME = "tour";
    private static final int MAX_NUMBER_OF_PLAYER = 20;
    private static final String LINE_FEED = System.lineSeparator();
    private final Player[] players;
    private final int[][] table;

    public Tournament(Player[] players) {
        this.players = players;
        table = new int[players.length][players.length];
    }

    static String getName() {
        return NAME;
    }

    static int getMaxNumberOfPlayer() {
        return MAX_NUMBER_OF_PLAYER;
    }

    public void playGames(boolean log, Board board) {
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players.length; j++) {
                if (i != j) {
                    final Game game = new Game(log, new Player[]{players[i], players[j]});
                    System.out.println("Player " + (i + 1) + " vs " + "Player " + (j + 1));
                    int[] result = game.play(board);
                    board.clear();
                    if (result.length > 1) {
                        table[i][j] += 1;
                        table[j][i] += 1;
                        System.out.println("Game result: Draw");
                    } else {
                        int wonPlayer = (result[0] == 1 ? i : j);
                        table[i][j] += (wonPlayer == i ? 3 : 0);
                        table[j][i] += (wonPlayer == j ? 3 : 0);
                        System.out.println("Game result: " + "Player " + (result[0] == 1 ? i + 1 : j + 1) + " won");
                    }
                }
            }
        }
    }

    public String showTable() {
        int lenN = Integer.toString(table.length).length();
        int lenM = Integer.toString(table[0].length).length();
        final StringBuilder sb = new StringBuilder(String.format("%" + lenN + "s", ""));
        for (int i = 1; i <= table[0].length; i++) {
            sb.append(String.format("%" + (lenM + 1) + "d", i));
        }
        for (int r = 1; r <= table.length; r++) {
            sb.append(LINE_FEED);
            sb.append(String.format("%" + lenM + "d", r));
            for (int c = 1; c <= table[0].length; c++) {
                sb.append(String.format("%" + lenM + "s", "")).append(table[r - 1][c - 1]);
            }
        }
        return sb.toString();
    }

    public String showResults() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            int res = 0;
            for (int j : table[i]) {
                res += j;
            }
            sb.append(i + 1).append(": ").append(res).append(LINE_FEED);
        }
        return sb.toString();
    }

}
