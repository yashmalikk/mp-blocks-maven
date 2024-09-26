package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
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
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
    return "";  // STUB
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

  // Checks if all blocks[] is equal to other blocks[] array with eqv as well.
  public boolean checkEqvBlocksArr(VComp other){
    boolean isGood = true;

    if (other.blocks.length == this.blocks.length){
      for(int i = 0; i < this.blocks.length; i++){
        if (this.blocks[i].getClass() == other.blocks[i].getClass()){
          isGood = isGood && (this.blocks[i].eqv(other.blocks[i]));
        } else {
          return false;
        }
      }
    } else {
      return false;
    }

    return isGood;
  }

  // created by Richard
  // Checks if aligns are the same.
  public boolean checkEqvAlignment(VComp other){
    if (this.align == other.align){
      return true;
    }
    return false;
  }


  /**
   * Determine if another block is structurally equivalent to this block.
   * 
   * !!! implemented by Richard
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    if (other instanceof VComp){
      boolean alignCmp = (((VComp)other).align == this.align);
      return alignCmp && checkEqvBlocksArr((VComp)other);
    }
    return false;       
  } // eqv(AsciiBlock)
} // class VComp
