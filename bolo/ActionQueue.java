package bolo;

class ActionQueue {
  private static final int MAX_SIZE = 1000;
  private Action[] currentActions = new Action[MAX_SIZE];
  private Action[] pendingActions = new Action[MAX_SIZE];
  private int size = 0;

  // can be called by any thread
  synchronized void add(Action newAction) {
    // ignores possibility that queue is full!!!!!!
	pendingActions[size++] = newAction;
  }

  private synchronized int startingDequeues() {
    // currentActions must be empty
    Action[] tmp = currentActions;
	currentActions = pendingActions;
	pendingActions = tmp;
	int currentSize = size;
	size = 0;
	return currentSize;
  }

  // can only be called by the animation thread
  void processActions() {
    int currentSize = startingDequeues();
	Action[] a = currentActions;
    for (int i = 0; i < currentSize; i++) {
	  a[i].action();
	  a[i] = null;
    }
  }
}