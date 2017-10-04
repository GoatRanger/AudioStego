class MatrixNode {
  int value;
  int previous;   // 1 == above, 2 == diagonal, 3 == left
  
  public MatrixNode() {
    value = 0;
    previous = 0;
  }

  public void setValue(int i) { value = i; }
  public int getValue() { return value; }
  public void setPrevious (int i) { previous = i; }
  public int getPrevious() { return previous; }
}
