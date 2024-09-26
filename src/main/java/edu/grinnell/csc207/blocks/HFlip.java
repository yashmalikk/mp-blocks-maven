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

  // Flips a string.

  /**
   * Flips the characters of a string notFlipped and returns that string.
   * 
   * @param notFlipped A string to be flipped
   * 
   * @return A string with the characters of notFlipped flipped.
   */
  private String strFlip(String notFlipped) {
    return new StringBuilder(notFlipped).reverse().toString();
  } // strFlip(String)

  /**
   * Get one row from the block with alignment.
   * 
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    String originalRow = block.row(i);
    String flippedRow = strFlip(originalRow);
    
    // Adjusting width for alignment
    int width = width();
    int padding;

    if (block instanceof VComp) {
      VComp vComp = (VComp) block;
      switch (vComp.align) {
        case LEFT:
          return flippedRow;
        case CENTER:
          padding = (width - flippedRow.length()) / 2;
          return " ".repeat(padding) + flippedRow + " ".repeat(width - padding - flippedRow.length());
        case RIGHT:
          return " ".repeat(width - flippedRow.length()) + flippedRow;
        default:
          return flippedRow; // Default case
      } // switch
    } // if

    return flippedRow; // Default case for non-VComp blocks
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
    } // if
    return false;       
  } // eqv(AsciiBlock)
} // class HFlip
