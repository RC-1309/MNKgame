package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    private static final String name = "Human";

    static String getName() {
        return name;
    }

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println("Enter row and column");
            final int row;
            final int column;
            Scanner line = new Scanner(in.nextLine());
            if (!line.hasNextInt()) {
                out.println("Move is invalid");
                continue;
            }
            row = line.nextInt();

            if (!line.hasNextInt()) {
                out.println("Move is invalid");
                continue;
            }
            column = line.nextInt();

            if (line.hasNext()) {
                out.println("Move is invalid");
                continue;
            }
            final Move move = new Move(row, column);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
