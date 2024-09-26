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

  // Helper to get the row from the block
  public String check(AsciiBlock block, int row, int diff) throws Exception {
    if (row >= diff && row < diff + block.height()) {
      return block.row(row - diff);
    } else {
      return " ".repeat(block.width());
    }
  }

  // Helper to calculate the vertical difference based on alignment
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
    }
  }

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
    }

    StringBuilder row = new StringBuilder();
    int currentDiff = 0;

    for (AsciiBlock block : blocks) {
      currentDiff = giveDiff(block, height());
      row.append(check(block, i, currentDiff));
    }
    
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
  public boolean checkEqvBlocksArr(HComp other) {
    if (other.blocks.length != this.blocks.length) {
      return false;
    }

    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      }
    }

    return true;
  }

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
    }
    return false;
  } // eqv(AsciiBlock)
} // class HComp
