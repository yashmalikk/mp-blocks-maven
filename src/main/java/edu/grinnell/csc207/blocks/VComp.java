package edu.grinnell.csc207.blocks;

import java.util.Arrays;
/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Yash Malik
 * @author Richard Lin
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   *
   * @param topBlock
   *   The block on the top.
   *
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock, AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   *
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBlock[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= height()) {
      throw new Exception("Invalid row index: " + i);
    } // if

    int currentRow = i;
    for (AsciiBlock block : blocks) {
      int blockHeight = block.height();
      if (currentRow < blockHeight) {
        String contentRow = block.row(currentRow);
        // Handle horizontal alignment
        if (align == HAlignment.LEFT) {
          return contentRow + " ".repeat(width() - contentRow.length());
        } else if (align == HAlignment.CENTER) {
          // Calculate padding for center alignment
          int padding = (width() - contentRow.length()) / 2;
          return
            " ".repeat(padding) + contentRow + " ".repeat(width() - padding - contentRow.length());
        } else if (align == HAlignment.RIGHT) {
          // Calculate padding for right alignment
          return " ".repeat(width() - contentRow.length()) + contentRow;
        } // if/elseif/elseif
      } // if
      currentRow -= blockHeight;
    } // for
    // Should not reach here, as row index should be valid
    throw new Exception("Invalid row index: " + i);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int totalHeight = 0;
    for (AsciiBlock block : blocks) {
      totalHeight += block.height();
    } // for
    return totalHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int maxWidth = 0;
    for (AsciiBlock block : blocks) {
      maxWidth = Math.max(maxWidth, block.width());
    } // for
    return maxWidth;
  } // width()

  /**
   * Checks if the blocks[] that are in the both the fields of other and 'this' are equal,
   * i.e. they have the same length and the order of elements is the same.
   *
   * @param other VComp to be checked.
   *
   * @return boolean indicating if blocks[] in other and this are equal.
   */
  public boolean checkEqvBlocksArr(VComp other) {
    if (other.blocks.length != this.blocks.length) {
      return false;
    } // if

    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      } // if
    } // for

    return true;
  } // checkEqvBlocksArr(VComp)

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
    if (other instanceof VComp) {
      boolean alignCmp = (((VComp) other).align == this.align);
      return alignCmp && checkEqvBlocksArr((VComp) other);
    } // if
    return false;
  } // eqv(AsciiBlock)
} // class VComp
