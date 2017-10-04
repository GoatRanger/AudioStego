package eecs.util;

import java.util.HashMap;

public class LookUpTable extends HashMap {
  static HashMap instance = null;

  private LookUpTable() {
    // prevent instantiation
  }

  static public LookUpTable getInstance() {
    if (instance == null) {
      instance = new LookUpTable();
    }
    return (LookUpTable) instance;
  }
}