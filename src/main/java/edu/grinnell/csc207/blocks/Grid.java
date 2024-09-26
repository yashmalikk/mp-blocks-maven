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
   * !!! Implemented by Richard
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception { 
    int iRowInBlock = i % this.element.height();

    return this.element.row(iRowInBlock).repeat(this.hreps);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   * 
   * !!! Implemented by Richard
   *
   * @return the number of rows
   */
  public int height() {
    return this.element.height() * this.vreps;
  } // height()

  /**
   * Determine how many columns are in the block.
   * 
   * !!! Implemented by Richard
   *
   * @return the number of columns
   */
  public int width() {
    return this.element.width() * this.hreps;
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
    return ((other instanceof Grid) && (this.eqv((Grid) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another grid is structurally equivalent to this grid.
   *
   * @param other
   *   The grid to compare to this grid.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(Grid other) {
    return (this.hreps == other.hreps) && (this.vreps == other.vreps)
        && (this.element.eqv(other.element));
  } // eqv(Grid)
} // class Grid
