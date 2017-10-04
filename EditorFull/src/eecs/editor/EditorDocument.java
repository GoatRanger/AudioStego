/*
 * Created on Jun 1, 2004 To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package eecs.editor;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import eecs.editor.jedit.SyntaxDocument;

/**
 * A Document with support for undo/redo.
 * 
 * @author Karl A. Gossett
 */
public class EditorDocument extends SyntaxDocument {
  CompoundEdit currentEdit = null;
  protected UndoManager undoManager = new UndoManager();
  int compoundEditCount = 0;

  /**
   * Starts a compound edit that can be undone in one operation.
   */
  public void beginCompoundEdit() {
    if (compoundEditCount == 0) {
      currentEdit = new CompoundEdit();
    }
    compoundEditCount++;
  }

  /**
   * Ends a compound edit that can be undone in one operation. Throws a NullPointerException if this method is called and there are no
   * compound edits to end.
   */
  public void endCompoundEdit() {
    if (compoundEditCount == 0) {
      throw new NullPointerException("No compound edit to end.");
    }
    compoundEditCount--;
    if (compoundEditCount == 0) {
      currentEdit.end();
      fireUndoableEditUpdate(new UndoableEditEvent(this, currentEdit));
    }
  }

  /**
   * Adds an undoable edit to this document's undo list. The edit should be ignored if something is currently being undone.
   * 
   * @param edit
   *          The undoable edit
   */
  public void addUndoableEdit(UndoableEdit edit) {
    if (compoundEditCount > 0) {
      currentEdit.addEdit(edit);
    }
    else {
      if (edit.isSignificant()) {
        super.fireUndoableEditUpdate(new UndoableEditEvent(this, edit));
      }
    }
  }

  /**
   * Fires when an undoable edit happens. Passes control to {@link #addUndoableEdit}to determine if the edit should be added to a compound
   * edit, passed along to the UndoableEditListeners, or just ignored (if not significant).
   * 
   * @see javax.swing.text.AbstractDocument#fireUndoableEditUpdate(javax.swing.event.UndoableEditEvent)
   */
  protected void fireUndoableEditUpdate(UndoableEditEvent e) {
    addUndoableEdit(e.getEdit());
  }
}