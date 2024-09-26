package edu.grinnell.csc207.blocks;

/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Yash Malik
 * @author Richard Lin
 */
public class HFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   * @throws IllegalArgumentException if original is null
   */
  public HFlip(AsciiBlock original) {
    if (original == null) {
      throw new IllegalArgumentException("Original block cannot be null");
    }
    this.block = original;
  } // HFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  // flips a string.
  public String strFlip(String notFlipped) {
    char[] flipped = new char[notFlipped.length()];
    char[] notflipped = notFlipped.toCharArray();

    for (int i = 0; i < notflipped.length; i++) {
      flipped[i] = notflipped[notflipped.length - 1 - i];
    }

    return new String(flipped); // Corrected
  }

  /**
   * Get one row from the block.
   * 
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    return strFlip(block.row(i));
  } // row(int)

  /**
   * Determine how many rows are in the block.
   * 
   * @return the number of rows
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   * 
   * @return the number of columns
   */
  public int width() {
    return this.block.width();  
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   * 
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    if (other instanceof HFlip) {
      return this.block.eqv(((HFlip) other).block);
    }
    return false;       
  } // eqv(AsciiBlock)
} // class HFlip
