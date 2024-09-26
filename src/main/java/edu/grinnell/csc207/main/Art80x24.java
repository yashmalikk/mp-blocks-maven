package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Rect;
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
    String outerMid = new String(" m    m  ");
    String mid = new String("m      m");
    
    String[] miniArt = {outer, outerMid, mid, mid, outerMid, outer};

    AsciiBlock art = new Lines(miniArt);
    AsciiBlock artMutated = new OurBlock(art, 1);

    AsciiBlock art2 = new OurBlock(artMutated, -1);

    AsciiBlock.print(pen, art2);
    AsciiBlock.print(pen, artMutated);

    // Optionally flip the artwork horizontally
    // AsciiBlock flippedArt = new HFlip(art);

    // Print the original artwork
    //pen.println("Original Artwork:");
    //AsciiBlock.print(pen, art);

    // Print the flipped artwork
    // pen.println("\nFlipped Artwork:");
    // AsciiBlock.print(pen, flippedArt);

    pen.close();
  } // main(String[])
} // class Art80x24
