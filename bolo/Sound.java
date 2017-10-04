package bolo;

import java.applet.*;
import javax.swing.*;
import java.net.URL;
import java.net.MalformedURLException;

class Sound {
  static boolean backgroundOn = true;
  static boolean eventSoundsOn = true;

  static final int MISSILE_EXPLOSION = 0;
  static final int ROBOT_EXPLOSION = 1;
  static final int FIRE_MISSILE = 2;
  static final int CHEER = 3; 
  static final int NO_MORE_TIME = 4;
  static final int TAPS = 5;
  static final int NO_SOUND = 6;
  private static final int NUM_SOUNDS = 7;

  // Jungle music from
  //  http://java.sun.com/docs/books/tutorial/sound/playing.html
  // Taps is from www.civilwarartillery.com
  // All other sounds from www.a1freesoundeffects.com
  private static final String BACKGROUND_FILE = "jungle.rmf";
  private static final String MISSILE_EX_FILE = "explosion.wav";
  private static final String ROBOT_EX_FILE = "implosion.wav";
  private static final String FIRE_FILE = "colt45.wav";
  private static final String CHEER_FILE = "crowdcheer.wav";
  private static final String NO_MORE_TIME_FILE = "thatsallfolks.wav";
  private static final String TAPS_FILE = "taps.wav";

  private static final String SOUND_FOLDER = "Sounds/";

  private static final AudioClip background = loadSound(BACKGROUND_FILE);
  private static final AudioClip[] clips = new AudioClip[NUM_SOUNDS];
  static {
    clips[MISSILE_EXPLOSION] = loadSound(MISSILE_EX_FILE);
	clips[ROBOT_EXPLOSION] = loadSound(ROBOT_EX_FILE);
	clips[FIRE_MISSILE] = loadSound(FIRE_FILE);
	clips[CHEER] = loadSound(CHEER_FILE);
	clips[NO_MORE_TIME] = loadSound(NO_MORE_TIME_FILE);
	clips[TAPS] = loadSound(TAPS_FILE);
  }

  private static AudioClip loadSound(String name) {
    try {
      URL resource = Sound.class.getResource(SOUND_FOLDER + name);
	  return Applet.newAudioClip(resource);
    }
	catch (Exception e) {
	  System.out.println("Warning: Could not load sound " + name + ".");
	  return null;
	}
  }

  static void startBackground() {
    if (background != null && backgroundOn) background.loop();
  }

  static void stopBackground() {
    if (background != null) background.stop();
  }

  static void play(int i) {
    if (clips[i] != null && eventSoundsOn) clips[i].play();
  }
}