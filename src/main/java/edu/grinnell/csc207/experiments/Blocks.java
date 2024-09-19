package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;

import java.io.PrintWriter;

/**
 * Experiments with ASCII blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class Blocks {
  /**
   * Print a separator.
   *
   * @param pen
   *   What we use to print the separator.
   */
  static void separator(PrintWriter pen) {
    pen.printf("\n-------------\n\n");
  } // separator(PrintWriter)

  /**
   * Print a single AsciiBlock with a separator and a caption.
   *
   * @param pen
   *   The PrintWriter to use for printing.
   * @param caption
   *   The caption to print.
   * @param block
   *   The block to print.
   */
  static void figure(PrintWriter pen, String caption, AsciiBlock block) {
    separator(pen);
    pen.println(caption);
    AsciiBlock.print(pen, block);
  } // figure

  /**
   * Run the experiments.
   *
   * @param args
   *   The command-line parameters (ignored).
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    Line line = new Line("Hello");
    Rect exes = new Rect('X', 3, 3);
    AsciiBlock boxedLine = new Boxed(line);
    AsciiBlock boxedboxedLine = new Boxed(boxedLine);
    AsciiBlock boxedExes = new Boxed(exes);

    pen.println("Original Values");
    figure(pen, "line", line);
    figure(pen, "exes", exes);
    figure(pen, "boxedLine", boxedLine);
    figure(pen, "boxedboxedLine", boxedboxedLine);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("After changing the line.");
    line.update("Goodbye");
    figure(pen, "line", line);
    figure(pen, "boxedLine", boxedLine);
    figure(pen, "boxedboxedLine", boxedboxedLine);

    separator(pen);
    pen.println("After widening exes");
    exes.wider();
    figure(pen, "exes", exes);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("After shortening exes");
    exes.shorter();
    figure(pen, "exes", exes);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("After making exes narrower twice and taller twice");
    exes.narrower();
    exes.narrower();
    exes.taller();
    exes.taller();
    figure(pen, "exes", exes);
    figure(pen, "boxedExes", boxedExes);

    pen.close();
  } // main(String[])
} // class Blocks
