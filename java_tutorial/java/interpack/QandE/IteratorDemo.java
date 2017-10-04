public class IteratorDemo implements java.util.Iterator {
    private String[] cardNames = {
             "2", "3", "4", "5", "6", "7", "8", "9",
	     "10", "Jack", "Queen", "King", "Ace" };
    private int current = 0;
    
    public boolean hasNext() {
        if (current == cardNames.length) {
            return false;
        } else {
            return true;
        }
    }
    public Object next() {
        return (Object)cardNames[current++];
    }
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        IteratorDemo i = new IteratorDemo();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
