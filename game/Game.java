package game;

public class Game {
    private static final String NAME = "game";
    private final boolean log;
    private static final String LINE_FEED = System.lineSeparator();
    private final Player[] players;

    public Game(final boolean log, Player[] players) {
        this.log = log;
        this.players = players;
    }

    static String getName() {
        return NAME;
    }

    public int[] play(Board board) {
        boolean[] losePlayer = new boolean[players.length];
        int numberOfLosePlayer = 0;
        while (true) {
            int idxPlayer = 1;
            for (Player player : players) {
                if (losePlayer[idxPlayer - 1]) {
                    idxPlayer++;
                    continue;
                }
                final int result = move(board, player, idxPlayer);
                if (result == 2) {
                    System.out.println("Player " + idxPlayer + " lose");
                    losePlayer[idxPlayer - 1] = true;
                    numberOfLosePlayer++;
                }
                if (numberOfLosePlayer == players.length - 1) {
                    for (int i = 0; i < losePlayer.length; i++) {
                        if (!losePlayer[i]) {
                            return new int[]{i + 1};
                        }
                    }
                }
                if (result == 1) {
                    return new int[]{idxPlayer};
                }
                if (result == 0) {
                    int[] drawPlayers = new int[players.length - numberOfLosePlayer];
                    int pos = 0;
                    for (int i = 0; i < losePlayer.length; i++) {
                        if (!losePlayer[i]) {
                            drawPlayers[pos++] = i + 1;
                        }
                    }
                    return drawPlayers;
                }
                idxPlayer++;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        try {
            //System.out.println("Player " + no + "'s move");
            log("Player " + no + "'s move");
            final Move move = player.move(board.getPosition(), board.getCell(no));
            final Result result = board.makeMove(move, no);
            log("Player " + no + " move: " + move);
            log("Position:" + LINE_FEED + board.getPosition());
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return 1;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return 2;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        } catch (ClassCastException e) {
            System.out.println("Player " + no + " lose because his actions were taken as cheats");
            return 3 - no;
        } catch (Exception e) {
            System.out.println("Player " + no + " lose because his move was incorrect");
            return 3 - no;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
