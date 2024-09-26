package edu.grinnell.csc207.blocks;

/**
 * An AsciiBlock where every character in it is mutated
 * 
 * @author Richard Lin
 * @author Yash Malik
 */
public class OurBlock implements AsciiBlock{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * The int that each character in block is incremented by.
   */
  int incrementChar;

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
  public OurBlock(AsciiBlock original, int increment) throws Exception{
    if (original == null) {
      throw new IllegalArgumentException("Original block cannot be null");
    }
    this.block = original;
    this.incrementChar = increment;
  } // OurBlock(AsciiBlock)

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

    String newRow = "";

    // Look through each character and incrementing by incrementChar
    for (int k = 0; k < this.block.width(); k ++){
      newRow = newRow.concat(String.valueOf((char)(block.row(i).charAt(k) + (char)this.incrementChar)));
    } // for

    return newRow;
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
    if (other instanceof OurBlock) {
      return this.block.eqv(((OurBlock) other).block) && (((OurBlock)other).incrementChar == this.incrementChar);
    } // if
    return false;       
  } // eqv(AsciiBlock)
} // class OurBlock


