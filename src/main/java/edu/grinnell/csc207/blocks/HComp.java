package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Yash Malik
 * @author Richard Lin
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(VAlignment, AsciiBlock[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+


  /**
   * Returns a row.
   * 
   * @param block AsciiBlock
   * 
   * @param row the number of the row to be looked at
   * 
   * @param diff the spacing difference
   * 
   * @return Returns the row at int row
   * 
   * @throws Exception 
   *  if row is out of bounds for the range of valid rows.
   */
  public String check(AsciiBlock block, int row, int diff) throws Exception {
    if (row < 0 || row >= height()) {
      throw new Exception("Invalid row index: " + row);
    } // if
    if (row >= diff && row < diff + block.height()) {
      return block.row(row - diff);
    } // if
    else {
      return " ".repeat(block.width());
    } // if/else
  } // check(AsciiBlock, int, int)

  /**
   * Gives the height difference between block and align based on this.align
   * 
   * @param block the Asciiblock
   * 
   * @param totalHeight Total height 'this'
   * 
   * @return An int representing the vertical height difference based on alignment
   */
  public int giveDiff(AsciiBlock block, int totalHeight) {
    switch (this.align) {
      case TOP:
        return 0;
      case CENTER:
        return (totalHeight - block.height()) / 2; // Truncates as needed
      case BOTTOM:
        return totalHeight - block.height();
      default:
        return 0; // Fallback (shouldn't happen)
    } // switch
  } // giveDiff(AsciiBlock, int)

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= height()) {
      throw new Exception("Invalid row index: " + i);
    } // if
 
    StringBuilder row = new StringBuilder();
    int currentDiff = 0;

    for (AsciiBlock block : blocks) {
      currentDiff = giveDiff(block, height());
      row.append(check(block, i, currentDiff));
    } // for
    
    return row.toString();
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int maxHeight = 0;

    for (AsciiBlock block : blocks) {
      maxHeight = Math.max(maxHeight, block.height());
    }

    return maxHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int totalWidth = 0;

    for (AsciiBlock block : blocks) {
      totalWidth += block.width();
    }

    return totalWidth;
  } // width()

  // Checks if all blocks[] are equal to other blocks[] array with eqv as well.

  /**
   * A boolean value indicating whether or not other and this have the same blocks[].
   * 
   * @param other HComp to be compared to
   * 
   * @return boolean checking if blocks[] field contain the same block's.
   */
  public boolean checkEqvBlocksArr(HComp other) {
    if (other.blocks.length != this.blocks.length) {
      return false;
    } // if

    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      } // if
    } // for

    return true;
  } // checkEqvBlocksArr(HComp)

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
    if (other instanceof HComp) {
      boolean alignCmp = (((HComp) other).align == this.align);
      return alignCmp && checkEqvBlocksArr((HComp) other);
    } // if
    return false;
  } // eqv(AsciiBlock)
} // class HComp
