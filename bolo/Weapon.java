package bolo;

class Weapon {
  int rounds;
  final Ammunition.Properties properties;
  final Ammunition.Factory factory;

  Weapon(int rounds,Ammunition.Properties properties,
         Ammunition.Factory factory) {
    this.rounds = rounds;
	this.properties = properties;
	this.factory = factory;
  }

  boolean fire(double x,double y,double heading,double distance) {
    if (rounds == 0)
	  return false;
    else {
	  rounds--;
	  factory.makeInstance(x,y,heading,distance);
	  return true;
    }
  }
}
