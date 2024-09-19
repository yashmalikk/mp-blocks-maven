package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;

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

    separator(pen);
    pen.println("Multi-line boxes");
    figure(pen, "Using an array",
        new Lines(new String[] {"this","and","that","or","whatever"}));
    figure(pen, "Using a multi-line string",
        new Lines("""
                  multi-line strings
                  were
                  introduced
                  in
                  Java 13
                  """));
    figure(pen, "Using a string with newlines",
        new Lines("alpha\nbeta\ngamma\ndelta\nepsilon"));

    separator(pen);
    pen.println("Fun with horizontal composition");
    Rect a = new Rect('A', 5, 2);
    Rect b = new Rect('B', 3, 3);
    Rect c = new Rect('C', 2, 6);
    figure(pen, "a", a);
    figure(pen, "b", b);
    figure(pen, "c", c);
    figure(pen, "Top composition",
        new HComp(VAlignment.TOP, new AsciiBlock[] {a, b, c}));
    figure(pen, "Center composition",
        new HComp(VAlignment.CENTER, new AsciiBlock[] {a, b, c}));
    figure(pen, "Bottom composition",
        new HComp(VAlignment.BOTTOM, new AsciiBlock[] {a, b, c}));

    pen.close();
  } // main(String[])
} // class Blocks
