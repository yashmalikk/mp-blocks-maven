package edu.grinnell.csc207.blocks;

/**
 * A grid of a single text block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class Grid implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * One element of the grid.
   */
  AsciiBlock element;

  /**
   * The number of times the element is repeated horizontally.
   */
  int hreps;

  /**
   * The number of times the element is repeated vertically.
   */
  int vreps;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new grid with the specified arrangement.
   *
   * @param gridElement
   *   The element in the grid.
   * @param horizRepetitions
   *   The number of horizontal repetitions in the grid.
   * @param vertRepetitions
   *   THe number of vertical repetitions in the grid.
   */
  public Grid(AsciiBlock gridElement, int horizRepetitions,
      int vertRepetitions) {
    this.element = gridElement;
    this.hreps = horizRepetitions;
    this.vreps = vertRepetitions;
  } // Grid(AsciiBlock, int, int)

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
    throw new Exception("Not yet implemented"); // STUB
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return 0;   // STUB
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return 0;   // STUB
  } // width()
} // class Grid
