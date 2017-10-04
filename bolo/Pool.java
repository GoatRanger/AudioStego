// ASSUMES YOU WILL NEVER TRY TO ADD MORE THAN 100 OBJECTS
// ALSO DOES NO SYCHRONIZATION SO POSSIBLE PROBLEMS WITH CONCURRENT ACCESS

package bolo;

import java.awt.*;

class Pool {
  private static final int MAX_SIZE = 1000;
  private Thing[] a = new Thing[MAX_SIZE];
  private int size = 0;

  void add(Thing thing) {
    // ignore overflow!!!!!!
	a[size++] = thing;
  }

  void tick(double deltaT) {
    int i = 0;
	while (i < size) {
	  if (a[i].obsolete) {
	    a[i] = a[--size];
		a[size] = null;
	  }
	  else {
	    a[i++].tick(deltaT);
	  }
	}
  }

  void draw(Graphics2D g) {
    int i = 0;
	while (i < size) {
	  if (a[i].obsolete) {
	    a[i] = a[--size];
		a[size] = null;
	  }
	  else {
	    a[i++].draw(g);
	  }
	}
  }

  Iterator getIterator() {
    return new Iterator();
  }

  class Iterator {
    private int i;
	private Iterator() { i = 0; }
	private Iterator(int start) { i = start; }

    Thing next() {
	  while (i < size) {
	    if (!a[i].obsolete) 
		  return a[i++];
		else {
		  a[i] = a[--size];
		  a[size] = null;
        }
	  }
	  return null;
	}

	Iterator copy() {
	  return new Iterator(i);
	}
  }
}