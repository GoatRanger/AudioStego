//
// $Id: Warnings.java,v 1.2 2002/04/08 21:19:15 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/**
 * $Log: Warnings.java,v $
 * Revision 1.2  2002/04/08 21:19:15  plehegar
 * New
 *
 * Revision 2.3  1997/09/08 13:37:21  plehegar
 * bugs
 *
 * Revision 2.2  1997/09/08 13:35:34  plehegar
 * Added level
 *
 * Revision 2.1  1997/08/08 15:51:50  plehegar
 * Nothing
 *
 */
package org.w3c.css.util;

/**
 * Controls all warnings in the validator
 *
 * @version $Revision: 1.2 $
 * @see java.util.Vector
 */
public final class Warnings {

  private Warning[] warningData = new Warning[20];

  private int       warningCount;

  private final int capacityIncrement = 10;

  /**
   * Add a warning.
   *
   * @param warm the warning
   */  
  public final void addWarning(Warning warm) {
    resize(1);
    warningData[warningCount++] = warm;
  }

  /**
   * Add warnings.
   *
   * @param warnings All warnings
   */  
  public final void addWarnings(Warnings warnings) {
    resize(warnings.warningCount);
    System.arraycopy(warnings.warningData, 0, warningData, warningCount, 
		     warnings.warningCount);
    warningCount += warnings.warningCount;
  }

  /**
   * Get the number of warnings
   */  
  public final int getWarningCount() {
    return warningCount;
  }

  /**
   * Get an array with all warnings.
   */  
  public final Warning[] getWarnings() {
    int oldCapacity = warningData.length;
    if (warningCount < oldCapacity) {
      Warning oldData[] = warningData;
      warningData = new Warning[warningCount];
      System.arraycopy(oldData, 0, warningData, 0, warningCount);
    }
    return warningData;
  }

  /**
   * Sort all warnings by line and level
   */  
  public final void sort() {
    quickSort(0, warningCount-1);
  }

  private int partition(int part_low_ind, int part_high_ind) {
    int lastsmall;
    long median_val;
    int comp1;
    Warning transit;
    
    // swap median value an first value of array
    comp1 = ( part_low_ind + part_high_ind ) / 2;         
    
    transit = warningData[part_low_ind];
    warningData[part_low_ind] = warningData[comp1];
    warningData[comp1] = transit;
    median_val = warningData[part_low_ind].getInternalOrder();
    lastsmall = part_low_ind;
    for (int i = part_low_ind + 1; i<=part_high_ind; i++) {
      if (warningData[i].getInternalOrder() < median_val) {
          lastsmall++;
          // swap lastsmall and i
          transit=warningData[lastsmall];
          warningData[lastsmall]=warningData[i];
          warningData[i]=transit;
        }
    }
    // swap part_low_ind and lastsmall
    transit=warningData[part_low_ind];
    warningData[part_low_ind]=warningData[lastsmall];
    warningData[lastsmall]=transit;
    
    return lastsmall;
  }
  
  private void quickSort(int qk_low_ind, int qk_high_ind) {
    if (qk_low_ind < qk_high_ind) {
      int median = partition(qk_low_ind, qk_high_ind);
      quickSort(qk_low_ind, median);
      quickSort(median+1, qk_high_ind);
    }
  }

  /**
   * Get a warning with an index.
   * @param index the warning index.
   */  
  public final Warning getWarningAt(int index) {
    return warningData[index];
  }

  private final void resize(int increment) {
    int oldCapacity = warningData.length;
    if (warningCount + increment + 1 > oldCapacity) {
      Warning oldData[] = warningData;
      warningData = new Warning[oldCapacity + increment + 1];
      System.arraycopy(oldData, 0, warningData, 0, warningCount);
    }
  }
}
