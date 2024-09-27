package edu.grinnell.csc207.blocks;

/**
 * A text block surrounded by a single letter.
 *
 * @author Samuel A. Rebelsky
 * @author Yash Malik
 * @author Richard Lin
 */
public class Surrounded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box.
   */
  AsciiBlock contents;

  /**
   * The character we put around the box.
   */
  String surroundChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   *
   * @param theChar
   *   The character that we use to surround the block.
   */
  public Surrounded(AsciiBlock blockContents, char theChar) {
    this.contents = blockContents;
    this.surroundChar = Character.toString(theChar);
  } // Surrounded(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

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
    if (i < 0 || i >= height()) {
      throw new Exception("Invalid row index: " + i);
    } // if

    if (i == 0 || i == height() - 1) {
      // Top or bottom border
      return surroundChar.repeat(width());
    } else {
      // Contents row
      String contentRow = contents.row(i - 1); // Adjust index for content rows
      return surroundChar + contentRow + surroundChar;
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return contents.height() + 2; // Adding top and bottom borders
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return contents.width() + 2; // Adding borders on both sides
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
    if (other instanceof Surrounded) {
      boolean strCmp = (((Surrounded) other).surroundChar.compareTo(this.surroundChar) == 0);
      boolean blockCmp = ((Surrounded) other).contents.eqv(this.contents);
      return strCmp && blockCmp;
    } // if
    return false;
  } // eqv(AsciiBlock)
} // class Surrounded
