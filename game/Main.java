package game;

import java.util.*;

public class Main {
    private static final int DEFAULT_N = 3;
    private static final int MAX_N = 1000;
    private static final int MIN_N = 2;

    private static final int DEFAULT_M = 3;
    private static final int MAX_M = 1000;
    private static final int MIN_M = 2;

    private static final int DEFAULT_K = 3;
    private static final int MIN_K = 2;
    private static final int MAX_NUMBER_OF_PLAYERS = 4;
    private static final Map<String, String> PLAYERS = Map.of(
            "1", HumanPlayer.getName(),
            "2", RandomPlayer.getName(),
            "3", SequentialPlayer.getName(),
            "4", StupidPlayer.getName()
    );

    private static Player getPlayer(String name) {
        Player player = null;
        if (name.equals(HumanPlayer.getName())) {
            player = new HumanPlayer();
        } else if (name.equals(RandomPlayer.getName())) {
            player = new RandomPlayer();
        } else if (name.equals(SequentialPlayer.getName())) {
            player = new SequentialPlayer();
        } else if (name.equals(StupidPlayer.getName())) {
            player = new StupidPlayer();
        }
        return player;
    }

    private static boolean checkParams(int n, int m, int k) {
        return (n < MIN_N || n > MAX_N || m < MIN_M || m > MAX_M || k < MIN_K || k > Integer.max(m, n));
    }

    private static int[] getNewParams(Scanner in) {
        int n = DEFAULT_N;
        int m = DEFAULT_M;
        int k = DEFAULT_K;
        System.out.println("Do you want to change params of game? (default: n=3, m=3, k=3)");
        System.out.println("Please, enter n(no) or new params(in order n m k)");
        System.out.println(MIN_N + " <= n <= " + MAX_N + ", " + MIN_M + " <= m <= " + MAX_M
                + ", " + MIN_K + " <= k <= max(n, m)");
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("n")) {
                break;
            }
            List<Integer> words = new ArrayList<>();
            Scanner thisLine = new Scanner(line);
            while (thisLine.hasNextInt()) {
                words.add(thisLine.nextInt());
            }
            if (words.size() != 3 || thisLine.hasNext()) {
                System.out.println("Incorrect input, try again");
                continue;
            }
            int newN = words.get(0);
            int newM = words.get(1);
            int newK = words.get(2);
            if (checkParams(newN, newM, newK)) {
                System.out.println("Please, enter correct params");
                continue;
            }
            System.out.println("New params: n=" + newN + ", m=" + newM + ", k=" + newK);
            n = newN;
            m = newM;
            k = newK;
            break;
        }
        return new int[]{n, m, k};
    }

    private static Player[] playerChoice(Scanner in, int numberOfPlayers) {
        Player[] players = new Player[numberOfPlayers];
        for (int idx = 0; idx < numberOfPlayers; idx++) {
            System.out.println("Please, enter the player who will be the " + (idx + 1));
            System.out.println("Enter the appropriate number:");
            for (int i = 1; i <= PLAYERS.size(); i++) {
                System.out.println(i + ": " + PLAYERS.get(Integer.toString(i)));
            }
            while (true) {
                String number = in.nextLine();
                if (PLAYERS.containsKey(number)) {
                    players[idx] = getPlayer(PLAYERS.get(number));
                    break;
                }
                System.out.println("Input incorrect, please, try again");
            }
        }
        return players;
    }

    private static boolean tournamentOrNot(Scanner in) {
        System.out.println("Do you want to play tournament(" + Tournament.getName()
                + ") or usual game("+ Game.getName() + ")?");
        System.out.println("Please, enter word:");
        while (true) {
            String line = in.nextLine();
            if (line.equals(Tournament.getName())) {
                return true;
            } else if (line.equals(Game.getName())) {
                return false;
            }
            System.out.println("Please, enter correct word");
        }
    }

    private static int getNumberOfPlayers(Scanner in, int maxNumberOfPLayers) {
        System.out.println("How many players will there be?");
        System.out.println("Enter a number from 2 to " + maxNumberOfPLayers);
        while (true) {
            Scanner line = new Scanner(in.nextLine());
            int number;
            if (line.hasNextInt()) {
                number = line.nextInt();
                if (!line.hasNext()) {
                    if (2 <= number && number <= maxNumberOfPLayers) {
                        return number;
                    }
                }
            }
            System.out.println("Please, enter correct number");
        }
    }

    private static Board getBoard(Scanner in, int n, int m, int k) {
        System.out.println("Which board do you want?");
        System.out.println("Usual board(board) or board with forbidden cell on diagonal(diag)");
        System.out.println("Please, enter a word");
        while (true) {
            String word = in.nextLine();
            if (word.equals("board")) {
                return new MNKBoard(n, m, k);
            } else if (word.equals("diag")) {
                return new MNKBoardWithForbidden(n, m, k);
            }
            System.out.println("Please, enter correct word");
        }
    }

    private static boolean playNextGame(Scanner in) {
        System.out.println("Do you want to play next game?");
        System.out.println("Please, enter yes(y) or no(n)");
        while (true) {
            String line = in.nextLine();
            if (line.equals("y")) {
                return true;
            } else if (line.equals("n")) {
                return false;
            }
            System.out.println("Please, enter correct symbol");
        }
    }

    private static boolean showLogs(Scanner in) {
        System.out.println("Do you want to see a log?");
        System.out.println("Please, enter yes(y) or no(n)");
        while (true) {
            String line = in.nextLine();
            if (line.equals("y")) {
                return true;
            } else if (line.equals("n")) {
                return false;
            }
            System.out.println("Please, enter correct word");
        }
    }

    private static void playTournament(Scanner in) {
        int numberOfPlayers = getNumberOfPlayers(in, Tournament.getMaxNumberOfPlayer());
        Tournament tournament = new Tournament(playerChoice(in, numberOfPlayers));
        int[] params = getNewParams(in);
        int n = params[0];
        int m = params[1];
        int k = params[2];
        Board board = getBoard(in, n, m, k);
        tournament.playGames(showLogs(in), board);
        System.out.println(tournament.showTable());
        System.out.println(tournament.showResults());
    }

    private static void playGames(Scanner in) {
        int numberOfPlayers = getNumberOfPlayers(in, MAX_NUMBER_OF_PLAYERS);
        Player[] players = playerChoice(in, numberOfPlayers);
        final Game game = new Game(showLogs(in), players);
        int[] params = getNewParams(in);
        int n = params[0];
        int m = params[1];
        int k = params[2];
        Board board = getBoard(in, n, m, k);
        while (true) {
            int[] result = game.play(board);
            if (result.length > 1) {
                System.out.println("Game result: Draw");
                System.out.println("This players not lose:");
                for (int player : result) {
                    System.out.println("Player " + player);
                }
            } else {
                System.out.println("Game result: " + "Player " + result[0] + " won");
            }
            if (!playNextGame(in)) {
                return;
            }
            board.clear();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (tournamentOrNot(in)) {
            playTournament(in);
        } else {
            playGames(in);
        }
    }
}
