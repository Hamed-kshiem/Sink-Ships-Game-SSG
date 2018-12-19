package at.jku.ssw.battleship.view;

import at.jku.ssw.battleship.model.Field;
import at.jku.ssw.battleship.model.Game;
import at.jku.ssw.battleship.model.State;
import inout.In;
import inout.Out;

import static at.jku.ssw.battleship.model.State.*;

public class BattleShip {
  private final Game game;
  private final Field field;

  public BattleShip() {
    int fieldSize = readFieldSize();
    int numShips = readNumShips();
    game = new Game(Game.createRandomPlayingField(fieldSize, numShips));
    field = game.getField();
  }

  public void run() {
    int rounds = 0;
    do {
      ++rounds;
      Out.println();
      Out.println(String.format("Round %d, ships left: %d", rounds, game.getShipCount()));

      printPlayingField();

      int[] target = readTarget();
      if (target == null) {
        Out.println("Goodby!");
        return;
      }

      if (game.fireAt(target[0], target[1])) {


        Out.println("Hit!");
      } else {
        Out.println("Off the mark ...");
      }
    } while (game.isRunning());

    printPlayingField();
    Out.println(String.format("Congratulations, you won after %d rounds!", rounds));
  }

  private static int readFieldSize() {
    int size;
    for (; ; ) {
      Out.print("Please enter the field size (1-26): ");
      size = In.readInt();
      if (In.done() && size >= 0 && size <= 26) break;
      In.readLine();
      Out.println("Invalid value: " + size);
    }
    return size;
  }

  private static int readNumShips() {
    int numShips;
    for (; ; ) {
      Out.print("Please enter the number of ships: ");
      numShips = In.readInt();
      if (In.done() && numShips >= 0) break;
      In.readLine();
      Out.println("Invalid value: " + numShips);
    }
    return numShips;
  }

  private void printPlayingField() {
    System.out.println("printing.....");
    char colChar = 'A';
    Out.print("\n   ");
    for (int col = 0; col < field.getSize(); col++) {
      Out.print(" " + colChar);
      colChar++;
    }
    Out.println();
    Out.println();
    for (int row = 0; row < field.getSize(); row++) {
      Out.print(String.format("%2d ", row));
      for (int col = 0; col < field.getSize(); col++) {
        State curPos = field.getState(row, col);
        if (curPos == MISS) {
          Out.print(" O");
        } else if (curPos == HIT) {
          Out.print(" X");
        } else {
          Out.print(" ~");
        }
      }
      Out.println();
    }
    Out.println();
  }

  private int[] readTarget() {
    final int fieldSize = field.getSize();
    final int lowerRowBound = 0;
    final int upperRowBound = fieldSize - 1;
    final char lowerColumnBound = (char) (lowerRowBound + 'A');
    final char upperColumnBound = (char) (upperRowBound + 'A');

    int[] target = null;

    for (; ; ) {
      Out.println("Where to fire next?");
      Out.print(String.format("[%d-%d] [%c-%c] or [.] to quit: ", lowerRowBound, upperRowBound, lowerColumnBound, upperColumnBound));

      if (In.peek() == '.') break;

      int row = In.readInt();
      char col = In.readChar();

      if (row >= lowerRowBound && row <= upperRowBound && col >= lowerColumnBound && col <= upperColumnBound) {
        target = new int[] {row, col - 'A'};
        break;
      }

      In.readLine();
      Out.println();
      Out.println("Invalid, please try again.");
    }
    return target;
  }
}
