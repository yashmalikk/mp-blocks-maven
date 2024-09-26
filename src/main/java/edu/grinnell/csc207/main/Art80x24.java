package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.OurBlock;
import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Yash Malik
 * @author Richard Lin
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Create a rectangular block filled with '^'
    // AsciiBlock art = new Rect('^', 80, 24);
    String outer = new String("  mmmm  ");
    String outerMid = new String(" m    m ");
    String mid = new String("m      m");
    
    String[] miniArt = {outer, outerMid, mid, mid, outerMid, outer};

    AsciiBlock art = new Lines(miniArt);
    AsciiBlock artMutated = new OurBlock(art, 390);

    AsciiBlock art2 = new OurBlock(artMutated,1);

    AsciiBlock art3 = new Surrounded(art2, 'b');
    AsciiBlock art4 = new OurBlock(art3,1);
    AsciiBlock art5 = new OurBlock(art4, 1);
    AsciiBlock art6 = new OurBlock(art5, 1);

    AsciiBlock[] Hcomp1 = {art3, art4, art5, art6};
    AsciiBlock Hcomp1Group = new HComp(VAlignment.CENTER, Hcomp1);
    AsciiBlock Hcomp2Group = new OurBlock(Hcomp1Group, 1);

    AsciiBlock[] HcompBig = {Hcomp1Group, Hcomp2Group};

    AsciiBlock row1 = new HComp(VAlignment.BOTTOM, HcompBig);

    AsciiBlock row2 = new OurBlock(row1, 494);
    AsciiBlock row3 = new OurBlock(row1, 1);

    AsciiBlock.print(pen, row1);
    AsciiBlock.print(pen, row2);
    AsciiBlock.print(pen, row3);

    


    pen.println("done");
    pen.close();
  } // main(String[])
} // class Art80x24
