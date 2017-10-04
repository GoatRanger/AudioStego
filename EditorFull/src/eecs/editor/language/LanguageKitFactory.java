package eecs.editor.language;

public class LanguageKitFactory {
  private LanguageKitFactory() {
    // Prevents Instantiation
  }

  public static LanguageKit getLanguageKit(String type) {
    LanguageKit kit = new DefaultLanguageKit();
    if (type.equals(LanguageKit.CSS)) {
      kit = new CSSKit();
    }
    else
      if (type.equals(LanguageKit.HTML)) {
        kit = new HTMLKit();
      }
      else
        if (type.equals(LanguageKit.JAVA)) {
          kit = new JavaKit();
        }
        else
          if (type.equals(LanguageKit.TEXT)) {
            kit = new DefaultLanguageKit();
          }
          else
            if (type.equals(LanguageKit.HUDSON)) {
              kit = new DefaultLanguageKit();
            }
    return kit;
  }
}