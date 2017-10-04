package bolo;

abstract class Transient extends Thing {
  double remainingTime;

  Transient(double duration) {
    remainingTime = duration;
  }

  void tick(double deltaT) {
    remainingTime -= deltaT;
	obsolete = (remainingTime <= 0.0);
  }

}