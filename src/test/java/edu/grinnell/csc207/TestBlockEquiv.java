package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests of the equivalence of various ASCII blocks.
 */
public class TestBlockEquiv {
  // +-------+-------------------------------------------------------
  // | Empty |
  // +-------+

  /**
   * Make sure two empty blocks are equivalent.
   */
  @Test
  public void testEqvEmpty() {
    assertTrue((new Empty()).eqv(new Empty()),
        "R: Empty blocks are equivalent");
  } // testEqvEmpty

  /**
   * Make sure that an empty block is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvEmpty() throws Exception {
    AsciiBlock empty = new Empty();
    assertFalse(empty.eqv(new Line("Hello")),
        "M: Empty block is not equivalent to a line");
    assertFalse(empty.eqv(new Line("Hello")),
        "E: Empty block is not equivalent to an empty line");
    assertFalse(empty.eqv(new Rect('x', 2, 5)),
        "M: Empty block is not equivalent to a rectangle");
    assertFalse(empty.eqv(new Boxed(new Rect('x', 2, 5))),
        "M: Empty block is not equivalent to a boxed rectangle");
    assertFalse(empty.eqv(new Surrounded(new Line("Whatever"), 'x')),
        "M: Empty block is not equivalent to a surrounded line");
    assertFalse(empty.eqv(new Grid(empty, 1, 1)),
        "E: Empty block is not equivalent to a 1x1 grid of the empty block");
    assertFalse(empty.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Empty block is not equivalent to an empty horiz composition");
    assertFalse(
        empty.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {new Line("One")})),
        "M: Empty block is not equivalent to a singleton hcomp");
    assertFalse(empty.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Empty block is not equivalent to an empty horiz composition");
    assertFalse(
         empty.eqv(new VComp(HAlignment.RIGHT, 
             new AsciiBlock[] {new Line("One")})),
        "M: Empty block is not equivalent to a singleton vcomp");
    assertFalse(empty.eqv(new HFlip(empty)),
        "M: Empty block is not equivalent to horizontally flipped empty block");
    assertFalse(empty.eqv(new VFlip(empty)),
        "M: Empty block is not equivalent to vertically flipped empty block");
    assertFalse(
        empty.eqv(new Trimmed(new Line("Hello"), HAlignment.LEFT,
            VAlignment.TOP, 0, 0)),
        "E: Empty block is not equivalent to a trimmed empty block");
    assertFalse(
        empty.eqv(new Padded(empty, 'x', HAlignment.LEFT, VAlignment.TOP,  
            0, 0)),
        "E: Empty block is not equivalent to a padded empty block");
  } // testNotEqvEmpty()

  // +-------+-------------------------------------------------------
  // | Lines |
  // +-------+

  /**
   * Make sure two equivalent lines are equivalent.
   */
  @Test
  public void testEqvLine() {
    assertTrue((new Line("")).eqv(new Line("")),
        "M: Empty lines are equivalent");
    assertTrue((new Line("Hello")).eqv(new Line("Hello")),
        "M: Lines with the same string are equivalent");
  } // testEqvLine

  /**
   * Make sure that lines become equivalent after changing.
   */
  @Test
  public void testEqvLineChange() {
    Line line1 = new Line("Hello");
    Line line2 = new Line("Goodbye");
    line2.update("Hello");
    assertTrue(line1.eqv(line2), 
        "E: Updated lines are equivalent");
    line1.update("Hi");
    line2.update("Hi");
    assertTrue(line2.eqv(line2), 
        "E: Re-updated lines are equivalent");
  } // testEqvLineChange()

  /**
   * Make sure that a line is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvLine() throws Exception {
    AsciiBlock line = new Line("OOO");
    assertFalse(line.eqv(new Line("Hello")),
        "M: Line OOO is not equivalent to a different line");
    assertFalse(line.eqv(new Rect('x', 2, 5)),
        "M: Line OOO is not equivalent to a rectangle");
    assertFalse(line.eqv(new Rect('O', 3, 1)),
        "E: Line OOO is not equivalent a rectangle that appears the same");
    assertFalse(line.eqv(new Boxed(new Rect('O', 3, 1))),
        "M: Line OOO is not equivalent to a boxed rectangle");
    assertFalse(line.eqv(new Surrounded(new Line("O"), 'O')),
        "M: Line OOO is not equivalent to a surrounded line");
    assertFalse(line.eqv(new Grid(line, 1, 1)),
        "E: Line OOO is not equivalent to a 1x1 grid of the same line");
    assertFalse(line.eqv(new Grid(new Line("O"), 3, 1)),
        "E: Line OOO is not equivalent to a 3x1 grid of O");
    assertFalse(line.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Line OOO is not equivalent to an empty horiz composition");
    assertFalse(
        line.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {line})),
        "M: Line OOO is not equivalent to a singleton hcomp of that line");
    assertFalse(
        line.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {line, new Empty()})),
        "M: Line OOO is not equivalent to an hcomp of that line and empty");
    assertFalse(
        line.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {new Line("O"), new Line("O"), new Line("O")})),
        "M: Line OOO is not equivalent to an hcomp of three O's");
    assertFalse(line.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Line OOO is not equivalent to an empty horiz composition");
    assertFalse(
         line.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {line})),
        "M: Line OOO is not equivalent to a singleton vcomp of the same line");
    assertFalse(line.eqv(new HFlip(line)),
        "M: Line OOO is not equivalent to horizontally flipped line block");
    assertFalse(line.eqv(new VFlip(line)),
        "M: Line OOO is not equivalent to vertically flipped line block");
    assertFalse(
        line.eqv(new Trimmed(new Line("OOOhhhh"), HAlignment.LEFT,
            VAlignment.TOP, 3, 1)),
        "E: Line OOO is not equivalent to a trimmed line block");
    assertFalse(
        line.eqv(new Padded(line, 'x', HAlignment.LEFT, VAlignment.TOP,  
            3, 0)),
        "E: Line OOO is not equivalent to a padded line block");
  } // testNotEqvLine()

  // +------------+--------------------------------------------------
  // | Rectangles |
  // +------------+

  /**
   * Make sure two rectangles are equivalent.
   *
   * @throws Exception
   *   If the `Rect` constructor happens to do so.
   */
  @Test
  public void testEqvRect() throws Exception {
    assertTrue((new Rect('x', 1, 1)).eqv(new Rect('x', 1, 1)),
        "M: Two 1x1 rectangles are eqivalent");
    assertTrue((new Rect('y', 3, 5)).eqv(new Rect('y', 3, 5)),
        "M: Two 3x5 rectangles are eqivalent");
  } // testEqvRect

  /**
   * Make sure that two updated rectangles are equivalent.
   *
   * @throws Exception
   *   If the `Rect` constructor happens to do so.
   */
  @Test
  public void testEqvRectChange() throws Exception {
    Rect rect1 = new Rect('R', 3, 2);
    Rect rect2 = new Rect('R', 3, 3);
    Rect rect3 = new Rect('R', 3, 1);

    rect2.shorter();
    assertTrue(rect1.eqv(rect2),
        "M: Equivalent after shortening");
    assertTrue(rect2.eqv(rect1),
        "M: Equivalent after shortening/inverse");

    rect3.taller();
    assertTrue(rect2.eqv(rect3),
        "M: One rectangle shorter, one taller");
    assertTrue(rect3.eqv(rect2),
        "M: One rectangle taller, one shorter");

    rect1.narrower();
    rect2.narrower();
    assertTrue(rect1.eqv(rect2),
        "M: Both rectangles narrower");
  } // testEqvRectChange()

  /**
   * Make sure that a rectangle is not equivalent to any other
   * type of block.
   *
   * @throws Exception
   *   If the `Rect` constructor happens to do so.
   */
  @Test
  public void testNotEqvRect() throws Exception {
    AsciiBlock rect = new Rect('R', 3, 2);
    assertFalse(rect.eqv(new Line("RRRRRR")),
        "M: Sample rect is not equivalent to a line");
    assertFalse(rect.eqv(new Rect('r', 3, 2)),
        "M: Sample rect is not eqv to a diff rectangle of same dimensions");
    assertFalse(rect.eqv(new Rect('R', 2, 3)),
        "M: Sample rect is not equivalent to a rectangle of inverted dimensions");
    assertFalse(rect.eqv(new Boxed(rect)),
        "M: Sample rect is not equivalent to a boxed rectangle");
    assertFalse(rect.eqv(new Surrounded(new Empty(), 'X')),
        "E: Sample rect is not eqv to a surrounded empty");
    assertFalse(rect.eqv(new Grid(rect, 1, 1)),
        "E: Sample rect is not equivalent to a 1x1 grid of the rect block");
    assertFalse(rect.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Sample rect is not equivalent to an empty horiz composition");
    assertFalse(rect.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {rect})),
        "M: Sample rect is not equivalent to a singleton hcomp");
    AsciiBlock rr = new Rect('R', 1, 2);
    assertFalse(
        rect.eqv(new HComp(VAlignment.TOP, new AsciiBlock[] {rr, rr, rr})),
        "E: Sample rect is not equivalent to a similar hcomp");
    assertFalse(rect.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Sample rect is not equivalent to an empty horiz composition");
    assertFalse(
        rect.eqv(new VComp(HAlignment.RIGHT, 
             new AsciiBlock[] {new Line("RRR")})),
        "M: Sample rect is not equivalent to a singleton vcomp");
    assertFalse(
        rect.eqv(new VComp(HAlignment.LEFT, 
             new AsciiBlock[] {new Line("RRR"), new Line("RRR")})),
        "E: Sample rect is not equivalent to a similar-appearing vcomp");
    assertFalse(rect.eqv(new HFlip(rect)),
        "M: Sample rect is not equivalent to horizontally flipped version");
    assertFalse(rect.eqv(new VFlip(rect)),
        "M: Sample rect is not equivalent to vertically flipped version");
    assertFalse(
        rect.eqv(new Trimmed(rect, HAlignment.LEFT, VAlignment.TOP, 3, 2)),
        "E: Sample rect is not equivalent to the same rect, trimmed");
    assertFalse(
        rect.eqv(new Trimmed(new Rect('R', 10, 10), HAlignment.LEFT,
            VAlignment.TOP, 3, 2)),
        "E: Sample rect is not equivalent to a similar trimmed rect");
    assertFalse(
        rect.eqv(new Padded(new Empty(), 'R', HAlignment.LEFT, VAlignment.TOP,  
            3, 2)),
        "E: Sample rect is not equivalent to a padded empty block");
    assertFalse(
        rect.eqv(new Padded(rect, 'R', HAlignment.LEFT, VAlignment.TOP,  
            3, 2)),
        "E: Sample rect is not equivalent to a 0-padded version of rhe same block");
  } // testNotEqvRect()

} // class TestBlockEquiv
