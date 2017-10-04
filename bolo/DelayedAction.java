package bolo;

class DelayedAction extends Thing {
  Action delayedAction;
  double remainingTime;

  DelayedAction(double delay,Action delayedAction) {
    this.delayedAction = delayedAction;
	remainingTime = delay;
	Arena.actionQueue.add(this);
  }

  void action() {
    Arena.timedThings.add(this);
  }

  void tick(double deltaT) {
    remainingTime -= deltaT;
	if (remainingTime <= 0) {
	  delayedAction.action();
      obsolete = true;
	}
  }
}
