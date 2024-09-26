package edu.grinnell.csc207.blocks;

import java.util.Arrays;
import javax.swing.GroupLayout.Alignment;
import java.io.PrintWriter;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
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
  } // HComp(Alignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  // Created by Richard
  // helper to row
  // Prints out what is the columns that the block is taking up (String to be concated)
  public String check(AsciiBlock block, int row, int diff) throws Exception{
    
    // Checks if row is where this box actually has text.
    if ((row >= diff) && (row <= diff + block.height())){
      return block.row(row - diff);
    }
    else {
      return (" ".repeat(block.width()));
    }
  }

  // Created by Richard
  // helper to row / check 
  // gives the difference in spacing from top to ascii block BASED ON ALIGNMENT
  public int giveDiff(AsciiBlock block, int totalHeight){
    int diff = 0;

    if (this.align == VAlignment.TOP){
      diff = 0;
    } else if (this.align == VAlignment.CENTER){
      diff = (totalHeight - block.height()) / 2; // truncates so that second part of spacing has one extra (WHICH IS WHAT REBELSKY WANTS)
    } else { // this.align == VAlignment.BOTTOM
      diff = (totalHeight - block.height());
    }

    return diff;
  }



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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    String row = "";
    int diff = 0;
    for (int j = 0; j < this.blocks.length; j++){
      row = row.concat(check(blocks[j], i, giveDiff(blocks[j], this.height())));
    }
    return row;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   * 
   * !!! Implemented by Richard
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;

    for (int i = 0; i < this.blocks.length; i++){
      height += this.blocks[i].height();
    }

    return height; 
  } // height()

  /**
   * Determine how many columns are in the block.
   * 
   * !!! Implemented by Richard
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;

    for (int i = 0; i < this.blocks.length; i++){
      width += this.blocks[i].width();
    }

    return width();
  } // width()


  // created by Richard
  // Checks if all blocks[] is equal to other blocks[] array with eqv as well.
  public boolean checkEqvBlocksArr(HComp other){
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
    // if same type
    if (other instanceof HComp){
      boolean alignCmp = (((HComp)other).align == this.align);
      return alignCmp && checkEqvBlocksArr((HComp)other);
    }
    return false;       
  } // eqv(AsciiBlock)
} // class HComp
