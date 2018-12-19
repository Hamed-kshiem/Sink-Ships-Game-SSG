package at.jku.ssw.battleship.model;

import java.util.Random;

public class Game {
    //fields
    static Field newField;
    static int numofShips;

    //constructor taking field as parameter
    public Game(Field field) {
        if (field == null) {
            throw new UnsupportedOperationException();
        }
        newField = field;
    }
//method returns the the created field
    public Field getField() {
        return newField;
    }
//running method returns true as long as ships left
    public boolean isRunning() {
        return numofShips > 0;
    }

    //Method that returns the number of ships
    public int getShipCount() {
        return numofShips;
    }

    /**
     * @return <code>true</code> if a ship has been hit, <code>false</code> otherwise.
     */
    public boolean fireAt(int row, int column) {
        if (row < 0 || column < 0) {
            throw new UnsupportedOperationException();
        } else {
            if (newField.getState(row, column) == State.SHIP) {
                newField.field[row][column] = State.HIT;
                fireArround(row, column);
                numofShips--;
                return true;
            } else if (newField.getState(row, column) == State.FREE) {
                newField.field[row][column] = State.MISS;
                return false;
            } else {
                return false;
            }
        }
    }
//Method for firing the target next to the main fired target
    public static boolean fireAtArround(int row, int column) {

        if (newField.getState(row, column) == State.SHIP) {
            newField.field[row][column] = State.HIT;
            numofShips--;
            return true;
        } else if (newField.getState(row, column) == State.FREE) {
            newField.field[row][column] = State.MISS;
            return false;
        } else {
            return false;
        }
    }
//method the fires around the 8 fields next to the main target
    static void fireArround(int row, int col) {

        int RowLong = newField.field.length;  //rows long
        int ColsLong = newField.field[0].length;//cols long


        if (row == 0 && col == 0) {//0  exception  corner
            fireAtArround(row + 1, col);
            fireAtArround(row, col + 1);
            fireAtArround(row + 1, col + 1);
        } else if (row == 0 && col == ColsLong - 1) {//1 exception corner
            fireAtArround(row, col - 1);
            fireAtArround(row + 1, col);
            fireAtArround(row + 1, col - 1);
        } else if (row == RowLong - 1 && col == 0) {//2 exception corner
            fireAtArround(row - 1, col);
            fireAtArround(row, col + 1);
            fireAtArround(row - 1, col + 1);
        } else if (row == RowLong - 1 && col == ColsLong - 1) {//3 exception corner
            fireAtArround(row - 1, col);
            fireAtArround(row, col - 1);
            fireAtArround(row - 1, col - 1);
        } else if (row == 0 && (col != 0 && col != ColsLong - 1)) {//4 exception north index
            fireAtArround(row, col - 1);
            fireAtArround(row, col + 1);
            fireAtArround(row + 1, col + 1);
            fireAtArround(row + 1, col);
            fireAtArround(row + 1, col - 1);
            fireAtArround(row + 1, col + 1);

        } else if (row == RowLong - 1 && (col != 0 && col != ColsLong - 1)) {//5 exception south index
            fireAtArround(row - 1, col);
            fireAtArround(row, col + 1);
            fireAtArround(row, col - 1);
            fireAtArround(row - 1, col - 1);
            fireAtArround(row - 1, col + 1);
        } else if ((row < ColsLong - 1 && row > 0) && col == 0) {//6 exception west index
            fireAtArround(row - 1, col);
            fireAtArround(row, col + 1);
            fireAtArround(row + 1, col);
            fireAtArround(row - 1, col + 1);
            fireAtArround(row + 1, col + 1);
        } else if ((row < ColsLong - 1 && row > 0) && col == ColsLong - 1) {//7 exception east index
            fireAtArround(row - 1, col);
            fireAtArround(row, col - 1);
            fireAtArround(row + 1, col);
            fireAtArround(row - 1, col - 1);
            fireAtArround(row + 1, col - 1);
        } else {//8 alle andren Indexen
            fireAtArround(row - 1, col);
            fireAtArround(row, col - 1);
            fireAtArround(row + 1, col);
            fireAtArround(row, col + 1);
            fireAtArround(row - 1, col + 1);
            fireAtArround(row - 1, col - 1);
            fireAtArround(row + 1, col + 1);
            fireAtArround(row + 1, col - 1);
        }


    }


    public static Field createRandomPlayingField(int fieldSize, int numShips) {
        //Exception
        if(fieldSize<0||numShips<0){
             throw new UnsupportedOperationException();

        }
        numofShips = 0;
        Field arrayField = new Field(fieldSize);
        System.out.println("----------");

        Random random = new Random();
        while (numofShips < numShips) {
            int x = random.nextInt(fieldSize);
            int y = random.nextInt(fieldSize);
           System.out.println(x + " " + y);//printing the solution to make it easy for testing the method
            if (arrayField.getState(x, y) == State.FREE) {
                arrayField.field[x][y] = State.SHIP;
                numofShips++;
            }
          //  System.out.println();
        }
        return arrayField;

    }
}
