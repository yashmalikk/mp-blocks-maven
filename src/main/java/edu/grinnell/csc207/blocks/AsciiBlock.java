package edu.grinnell.csc207.blocks;

import java.io.PrintWriter;

/**
 * Blocks of ASCII text.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public interface AsciiBlock {
  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Print out a block.
   *
   * @param pen
   *   The PrintWriter used to print the block.
   *
   * @param block
   *   The block to print.
   */
  public static void print(PrintWriter pen, AsciiBlock block) {
    for (int i = 0; i < block.height(); i++) {
      try {
        pen.println(block.row(i));
      } catch (Exception e) {
        pen.printf("*** ERROR: Missing row %d ***\n", i);
      } // try/catch
    } // for
  } // print(PrintWriter, AsciiBlock)

  /**
   * Determine if two blocks are equal in that they occupy the same
   * memory location.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are in the same memory location and false otherwise.
   */
  public static boolean eq(AsciiBlock block1, AsciiBlock block2) {
    return block1 == block2;
  } // eq(AsciiBlock, AsciiBlock)

  /**
   * Determine if two blocks are equal in that they are structurally
   * equivalent. That is, two blocks are equal in this sense if they
   * were created with the same set of nested constructor calls.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are structurally equivalent and false otherwise.
   */
  public static boolean eqv(AsciiBlock block1, AsciiBlock block2) {
    return false;      // STUB
  } // eqv(AsciiBlock, AsciiBlock)

  /**
   * Determine if two blocks are equal in that they have the same,
   * width, height, and rows.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are in the same memory location and false otherwise.
   */
  public static boolean equal(AsciiBlock block1, AsciiBlock block2) {
    return false;      // STUB
  } // equal(AsciiBlock, AsciiBlock)

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
   * @pre
   *   0 <= i < this.height()
   *
   * @exception Exception
   *   if the row number is invalid.
   */
  public String row(int i) throws Exception;

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height();

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width();
} // interface AsciiBlock
