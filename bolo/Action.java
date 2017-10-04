package bolo;

/*
To minimize the use of synchronization, I have only one synchronized object:
a queue of action requests.  Anything else that might need synchronization
is added to the queue as an Action object.  The action will be performed
by the main animation loop, which can never be interrupted by a robot thread.

*/

abstract class Action {
  abstract void action();
}
