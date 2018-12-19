package at.jku.ssw.battleship.model;

public class Field {
  public State[][] field;
  int size;
  Field(int x) {
    if (x <= 0) {
      throw new UnsupportedOperationException();
    } else {
      //setting the size of the field
      field = new State[x][x];
      //convert all the fields to State.FREE
      for (int r = 0; r < field.length; r++) {
        for (int c = 0; c < field[r].length; c++)
          field[r][c] = State.FREE;
      }
      size = x;
    }
  }
//method returns the State in specified pos according to (row,col)
  public State getState(int row, int column) {
    if(row<0||column<0){
      throw new UnsupportedOperationException();
    }else {
    }
    return field[row][column];
  }

//method returns the size of the field
  public int getSize() {
return size;
  }
}
