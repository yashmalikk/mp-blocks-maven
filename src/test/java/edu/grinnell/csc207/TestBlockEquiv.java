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
  // | Hacks |
  // +-------+

  /**
   * Create rectangles without throwing exceptions.
   */
  static final AsciiBlock newRect(char ch, int width, int height) {
    try {
      return new Rect(ch, width, height);
    } catch (Exception e) {
      return new Line(String.format("Rect(%c, %d, %d)", ch, width, height));
    } // try/catch
  } // newRect

  // +----------------------------+----------------------------------
  // | Globals for the exes tests |
  // +----------------------------+

  // These tests compare 6x4 blocks of the letter X. 
  //
  // The A and B for the same block constructor should be the same.

  /** Using Rect */
  static AsciiBlock exesRect0a = newRect('X', 6, 4);

  /** Using Rect again. */
  static AsciiBlock exesRect0b = newRect('X', 6, 4);

  /** Rects, A side. */
  static AsciiBlock[] exesRectA =
    new AsciiBlock[] { exesRect0a };

  /** Rects, B side. */
  static AsciiBlock[] exesRectB =
    new AsciiBlock[] { exesRect0b };

  /** Using Grid. */
  static AsciiBlock exesGrid0a = new Grid(new Line("X"), 6, 4);

  /** Using Grid again. */
  static AsciiBlock exesGrid0b = new Grid(new Line("X"), 6, 4);

  /** Using Grid and Rect0a. */
  static AsciiBlock exesGrid1a = new Grid(exesRect0a, 1, 1);

  /** Using Grid and Rect0b. */
  static AsciiBlock exesGrid1b = new Grid(exesRect0b, 1, 1);

  /** Using a different Grid. */
  static AsciiBlock exesGrid2a = new Grid(newRect('X', 3, 2), 2, 2);

  /** And another. */
  static AsciiBlock exesGrid3a = new Grid(new Grid(new Line("X"), 3, 2), 2, 2);

  /** Grids, A side. */
  static AsciiBlock[] exesGridsA = 
      new AsciiBlock[] { exesGrid0a, exesGrid1a, exesGrid2a, exesGrid3a };
          
  /** Grids, B side. */
  static AsciiBlock[] exesGridsB = 
      new AsciiBlock[] { exesGrid0b, null, null, null };

  /** Something to help with our horizontal composition */
  static AsciiBlock tmp1 = newRect('X', 2, 4);

  /** Using horizontal composition. */
  static AsciiBlock exesHComp0a =
      new HComp(VAlignment.TOP, new AsciiBlock[] { tmp1, tmp1, tmp1 });

  /** Using an equivalent horizontal composition. */
  static AsciiBlock exesHComp0b =
      new HComp(VAlignment.TOP, 
          new AsciiBlock[] { 
              newRect('X', 2, 4), newRect('X', 2, 4), newRect('X', 2, 4) });

  /** Using horizontal composition of exesComp0a with empty. */
  static AsciiBlock exesHComp1a = 
      new HComp(VAlignment.TOP,
          new AsciiBlock[] { exesHComp0a, new Empty() });

  /** Using horizontal composition of exesComp0b with empty. */
  static AsciiBlock exesHComp1b = 
      new HComp(VAlignment.TOP,
          new AsciiBlock[] { exesHComp0b, new Empty() });

  /** Using horizontal composition of the 0a blocks and a diff alignment. */
  static AsciiBlock exesHComp2a = 
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] { exesHComp0a, new Empty() });

  /** Similar. */
  static AsciiBlock exesHComp2b = 
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] { exesHComp0b, new Empty() });

  /** Using horizontal composition of the 0a blocks and YA alignment. */
  static AsciiBlock exesHComp3a = 
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] { exesHComp0a, new Empty() });

  /** Similar. */
  static AsciiBlock exesHComp3b = 
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] { exesHComp0b, new Empty() });

  /** A lot like 3a, except the empty is on the other side. */
  static AsciiBlock exesHComp4a =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] { new Empty(), exesHComp0a });

   /** Ditto, but with 3a. */
  static AsciiBlock exesHComp4b =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] { new Empty(), exesHComp0a });

  /** Horizontal composition of the 4a block with empty. */
  static AsciiBlock exesHComp5a =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] { new Empty(), exesHComp4a, new Empty() });

  /** Horizontal composition of the 4b block with empty. */
  static AsciiBlock exesHComp5b =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] { new Empty(), exesHComp4b, new Empty() });

  /** Like 0a, but with a different alignment. */
  static AsciiBlock exesHComp6a =
      new HComp(VAlignment.BOTTOM, new AsciiBlock[] { tmp1, tmp1, tmp1 });

  /** Using an equivalent horizontal composition. */
  static AsciiBlock exesHComp6b =
      new HComp(VAlignment.BOTTOM, 
          new AsciiBlock[] { 
              newRect('X', 2, 4), newRect('X', 2, 4), newRect('X', 2, 4) });

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
        "E: Empty block is not equivalent to an empty vertical composition");
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
        "E: Line OOO is not equivalent to an empty vertical composition");
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
        "E: Sample rect is not equivalent to an empty vertical composition");
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
        "E: Sample rect is not equivalent to a 0-padded version of the same block");
  } // testNotEqvRect()

  // +-------+-------------------------------------------------------
  // | Boxed |
  // +-------+

  /**
   * Make sure two equivalent boxed blocks are equivalent.
   */
  @Test
  public void testEqvBoxed() throws Exception {
    assertTrue((new Boxed(new Empty())).eqv(new Boxed(new Empty())),
        "M: Boxed empty blocks are equivalent");
    assertTrue((new Boxed(new Line(""))).eqv(new Boxed(new Line(""))),
        "M: Boxed empty lines are equivalent");
    assertTrue((new Boxed(new Line("Foo"))).eqv(new Boxed(new Line("Foo"))),
        "M: Boxed nonempty lines are equivalent");
    assertTrue((new Boxed(new Rect('@', 3, 2))).eqv(
        new Boxed(new Rect('@', 3, 2))),
        "M: Boxed equivalent rectangles are equivalent");
    assertTrue((new Boxed(new Boxed(new Line("Foo")))).eqv(
        new Boxed(new Boxed(new Line("Foo")))),
        "M: Doubly boxed lines are equivalent");
  } // testEqvBoxed

  /**
   * Make sure that a box is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvBoxed() throws Exception {
    AsciiBlock box = new Boxed(new Line("line"));
    assertFalse((new Boxed(new Line("alpha"))).eqv(new Boxed(new Line("beta"))),
        "M: Two different boxed lines are different");
    assertFalse((new Boxed(new Rect('$', 3, 2))).eqv(
        new Boxed(new Rect('$', 2, 3))),
        "M: Two different boxed rects are different");
    assertFalse(box.eqv(new Line("line")),
        "M: Sample box is not equivalent to a line");
    assertFalse(box.eqv(new Rect('x', 2, 5)),
        "M: Sample box is not equivalent to a rectangle");
    assertFalse(box.eqv(new Surrounded(new Line("box"), '"')),
        "M: Sample box is not equivalent to a surrounded box");
    assertFalse(box.eqv(new Grid(box, 1, 1)),
        "M: Sample box is not equivalent to a 1x1 grid of the same box");
    assertFalse(box.eqv(new Grid(new Line("O"), 3, 1)),
        "M: Sample box is not equivalent to a 3x1 grid of O");
    assertFalse(box.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "M: Sample box is not equivalent to an empty horiz composition");
    assertFalse(
        box.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {box})),
        "M: Sample box is not equivalent to a singleton hcomp of that box");
    assertFalse(
        box.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {box, new Empty()})),
        "M: Sample box is not equivalent to an hcomp of that box and empty");
    assertFalse(
        box.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {new Line("O"), new Line("O"), new Line("O")})),
        "M: Sample box is not equivalent to an hcomp of three O's");
    assertFalse(box.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "M: Sample box is not equivalent to an empty vertical composition");
    assertFalse(
         box.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {box})),
        "M: Sample box is not equivalent to a singleton vcomp of the same box");
    assertFalse(box.eqv(new HFlip(box)),
        "M: Sample box is not equivalent to horizontally flipped box block");
    assertFalse(box.eqv(new VFlip(box)),
        "M: Sample box is not equivalent to vertically flipped box block");
    assertFalse(
        box.eqv(new Trimmed(new Line("OOOhhhh"), HAlignment.LEFT,
            VAlignment.TOP, 3, 1)),
        "M: Sample box is not equivalent to a trimmed box block");
    assertFalse(
        box.eqv(new Padded(box, 'x', HAlignment.LEFT, VAlignment.TOP,  
            3, 0)),
        "M: Sample box is not equivalent to a padded box block");
  } // testNotEqvBox()

  // +------------+--------------------------------------------------
  // | Surrounded |
  // +------------+

  /**
   * Make sure two equivalent surrounded blocks are equivalent.
   */
  @Test
  public void testEqvSurrounded() throws Exception {
    assertTrue(
        (new Surrounded(new Empty(), ' ')).eqv(
            new Surrounded(new Empty(), ' ')),
        "M: Surrounded empty blocks are equivalent");
    assertTrue(
        (new Surrounded(new Line(""), 'a')).eqv(
            new Surrounded(new Line(""), 'a')),
        "M: Surrounded empty lines are equivalent");
    assertTrue(
        (new Surrounded(new Line("Foo"), 'b')).eqv(
            new Surrounded(new Line("Foo"), 'b')),
        "M: Surrounded nonempty lines are equivalent");
    assertTrue(
        (new Surrounded(new Rect('@', 3, 2), 'd')).eqv(
            new Surrounded(new Rect('@', 3, 2), 'd')),
        "M: Surrounded equivalent rectangles are equivalent");
    assertTrue(
        (new Surrounded(new Surrounded(new Line("Foo"), ' '), '*')).eqv(
            new Surrounded(new Surrounded(new Line("Foo"), ' '), '*')),
        "M: Doubly surrounded lines are equivalent");
  } // testEqvSurrounded

  /**
   * Make sure that different surrounded boxes are different.
   */
  @Test
  public void testNotEqvDiffSurrounded() throws Exception {
    assertFalse(
       (new Surrounded(new Empty(), ' ')).eqv(
           new Surrounded(new Line(""), ' ')),
       "M: Surrounded empty is not the same as a surrounded line");
    assertFalse(
       (new Surrounded(new Line("a"), 'b')).eqv(
           new Surrounded(new Line("b"), 'b')),
       "M: Surrounded different lines are different");
    assertFalse(
       (new Surrounded(new Rect('c', 3, 2), 'd')).eqv(
           (new Surrounded(new Rect('c', 3, 3), 'd'))),
       "M: Surrounded different rectangles are different");
    assertFalse(
       (new Surrounded(new Surrounded(new Line ("a"), ' '), ' ')).eqv(
           (new Surrounded(new Line("a"), ' '))),
       "M: Doubly surrounded is not the same as singly surrounded.");
    assertFalse(
       (new Surrounded(new Line("a"), ' ')).eqv(
           (new Surrounded(new Surrounded(new Line ("a"), ' '), ' '))),
       "M: Singly surrounded is not the same as doubly surrounded.");
  } // testNotEqvDiffSurrounded()

  /**
   * Make sure that a box is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvSurrounded() throws Exception {
    AsciiBlock block = new Surrounded(new Line("A"), 'A');
    assertFalse(block.eqv(new Line("line")),
        "M: Sample surround is not equivalent to a line");
    assertFalse(block.eqv(new Rect('A', 3, 3)),
        "M: Sample surround is not equivalent to a similar rectangle");
    assertFalse(block.eqv(new Grid(block, 1, 1)),
        "E: Sample surround is not equivalent to a 1x1 grid of the same block");
    assertFalse(block.eqv(new Grid(new Line("A"), 3, 3)),
        "E: Sample surround is not equivalent to a 3x3 grid of A");
    assertFalse(block.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "M: Sample surround is not equivalent to an empty horiz composition");
    assertFalse(
        block.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {block})),
        "E: Sample surround is not eqv to a singleton hcomp of that block");
    assertFalse(
        block.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {block, new Empty()})),
        "M: Sample surround is not eqv to an hcomp of that block and empty");
    assertFalse(
        block.eqv(new HComp(VAlignment.BOTTOM, 
            new AsciiBlock[] {new Line("O"), new Line("O"), new Line("O")})),
        "M: Sample surround is not equivalent to an hcomp of three O's");
    assertFalse(block.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "M: Sample surround is not eqv to an empty vertical composition");
    assertFalse(
         block.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {block})),
        "E: Sample surround is not eqv a singleton vcomp of the block");
    assertFalse(block.eqv(new HFlip(block)),
        "M: Sample surround is not eqv to horizontally flipped surround");
    assertFalse(block.eqv(new VFlip(block)),
        "M: Sample surround is not eqv to vertically flipped surround");
    assertFalse(
        block.eqv(new Trimmed(block, HAlignment.LEFT,
            VAlignment.TOP, 3, 3)),
        "E: Sample surround is not equivalent to a trimmed surround");
    assertFalse(
        block.eqv(new Trimmed(new Surrounded(block, 'A'), HAlignment.CENTER,
            VAlignment.CENTER, 3, 3)),
        "E: Sample surround is not equivalent to a trimmed bigger surround");
    assertFalse(
        block.eqv(new Padded(block, 'x', HAlignment.LEFT, VAlignment.TOP,  
            3, 3)),
        "E: Sample surround is not equivalent to a padded box block");
    assertFalse(
        block.eqv(new Padded(new Line("A"), 'A', HAlignment.LEFT, 
            VAlignment.TOP, 3, 3)),
        "E: Sample surround is not eqv to a similar looking padded box");
  } // testNotEqvSurrounded()

  // +-------+-------------------------------------------------------
  // | Grids |
  // +-------+

  /**
   * Make sure two simple grids are equivalent.
   *
   * @throws Exception
   *   If the `Rect` constructor throws an exception.
   */
  @Test
  public void testEqvGrid() throws Exception {
    assertTrue(
        (new Grid(new Line("XYZ"), 1, 1)).eqv(new Grid(new Line("XYZ"), 1, 1)),
        "M: Two 1x1 grids are eqivalent");
    assertTrue(
        (new Grid(new Rect('x', 3, 5), 4, 4).eqv(
            new Grid(new Rect('x', 3, 5), 4, 4))),
        "M: Two 4x4 grids are eqivalent");
  } // testEqvGrid

  /**
   * Make sure that a grid is not equivalent to any other
   * type of block.
   *
   * @throws Exception
   *   If the `Grid` constructor happens to do so.
   */
  @Test
  public void testNotEqvGrid() throws Exception {
    AsciiBlock grid = new Grid(new Line("G"), 3, 2);
    assertFalse(grid.eqv(new Line("GGGGGGGGGGGG")),
        "M: Sample grid is not equivalent to a line");
    assertFalse(grid.eqv(new Grid(new Line("g"), 3, 2)),
        "M: Sample grid is not eqv to a diff grid of same dimensions");
    assertFalse(grid.eqv(new Grid(new Line("G"), 2, 3)),
        "M: Sample grid is not equivalent to a grid of inverted dimensions");
    assertFalse(grid.eqv(new Boxed(grid)),
        "M: Sample grid is not equivalent to a boxed grid");
    assertFalse(grid.eqv(new Surrounded(new Empty(), 'X')),
        "E: Sample grid is not eqv to a surrounded empty");
    assertFalse(grid.eqv(new Grid(grid, 1, 1)),
        "E: Sample grid is not equivalent to a 1x1 grid of the grid");
    assertFalse(grid.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Sample grid is not equivalent to an empty horiz composition");
    assertFalse(grid.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {grid})),
        "M: Sample grid is not equivalent to a singleton hcomp");
    AsciiBlock rg = new Rect('G', 1, 2);
    assertFalse(
        grid.eqv(new HComp(VAlignment.TOP, new AsciiBlock[] {rg, rg, rg})),
        "E: Sample grid is not equivalent to a similar hcomp");
    assertFalse(grid.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Sample grid is not equivalent to an empty vertical composition");
    assertFalse(
        grid.eqv(new VComp(HAlignment.RIGHT, 
             new AsciiBlock[] {new Line("GGG")})),
        "M: Sample grid is not equivalent to a singleton vcomp");
    assertFalse(
        grid.eqv(new VComp(HAlignment.LEFT, 
             new AsciiBlock[] {new Line("GGG"), new Line("GGG")})),
        "E: Sample grid is not equivalent to a similar-appearing vcomp");
    assertFalse(grid.eqv(new HFlip(grid)),
        "M: Sample grid is not equivalent to horizontally flipped version");
    assertFalse(grid.eqv(new VFlip(grid)),
        "M: Sample grid is not equivalent to vertically flipped version");
    assertFalse(
        grid.eqv(new Trimmed(grid, HAlignment.LEFT, VAlignment.TOP, 3, 2)),
        "E: Sample grid is not equivalent to the same grid, trimmed");
    assertFalse(
        grid.eqv(new Trimmed(new Rect('G', 10, 10), HAlignment.LEFT,
            VAlignment.TOP, 3, 2)),
        "E: Sample grid is not equivalent to a similar trimmed rect");
    assertFalse(
        grid.eqv(new Trimmed(new Grid(new Line("G"), 10, 10), HAlignment.RIGHT,
            VAlignment.BOTTOM, 3, 2)),
        "E: Sample grid is not equivalent to a similar trimmed grid");
    assertFalse(
        grid.eqv(new Padded(new Empty(), 'G', HAlignment.LEFT, VAlignment.TOP,  
            3, 2)),
        "E: Sample grid is not equivalent to a padded empty block");
    assertFalse(
        grid.eqv(new Padded(grid, 'G', HAlignment.LEFT, VAlignment.TOP,  
            3, 2)),
        "E: Sample grid is not eqv to a 0-padded version of the same grid");
  } // testNotEqvGrid()

  // +----------------------+----------------------------------------
  // | The exes comparisons |
  // +----------------------+

  /**
   * Rectangles vs rectangles that should be the same.
   */
  @Test
  public void exesRectVsRectSame() {
    assertTrue(AsciiBlock.equal(exesRect0a, exesRect0b),
        "*: equal(exesRect0a, exesRect0b)");
    assertTrue(AsciiBlock.equal(exesRect0b, exesRect0a),
        "*: equal(exesRect0b, exesRect0a)");
    assertTrue(AsciiBlock.eqv(exesRect0a, exesRect0b),
        "*: eqv(exesRect0a, exesRect0b)");
    assertTrue(AsciiBlock.eqv(exesRect0b, exesRect0a),
        "*: eqv(exesRect1g, exesRect0a)");
  } // exesRectVsRectSame

  /**
   * Rectangles vs grids.
   */
  @Test
  public void exesRectVsGrid() {
    assertTrue(AsciiBlock.equal(exesRect0a, exesGrid0a),
        "*: equal(exesRect0a, exesGrid0a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesGrid1a),
        "*: equal(exesRect0a, exesGrid1a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesGrid2a),
        "*: equal(exesRect0a, exesGrid2a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesGrid3a),
        "*: equal(exesRect0a, exesGrid3a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesGrid0a),
        "M: eqv(exesRect0a, exesGrid0a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesGrid1a),
        "M: eqv(exesRect0a, exesGrid1a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesGrid2a),
        "M: eqv(exesRect0a, exesGrid2a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesGrid3a),
        "M: eqv(exesRect0a, exesGrid3a)");
  } // exesRectVsGrid

  /**
   * Grids vs rectangles.
   */
  @Test
  public void exesGridVsRect() {
    assertTrue(AsciiBlock.equal(exesGrid0a, exesRect0a),
        "*: equal(exesGrid0a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesRect0a),
        "*: equal(exesGrid1a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesRect0a),
        "*: equal(exesGrid2a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesGrid3a, exesRect0a),
        "*: equal(exesGrid3a, exesRect0a)");

    assertFalse(AsciiBlock.eqv(exesGrid0a, exesRect0a),
        "M: eqv(exesGrid0a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesGrid1a, exesRect0a),
        "M: eqv(exesGrid1a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesGrid2a, exesRect0a),
        "M: eqv(exesGrid2a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesGrid3a, exesRect0a),
        "M: eqv(exesGrid3a, exesRect0a)");
  } // exesGridVsRect()

  /**
   * Grids vs grids that are the same.
   */
  @Test
  public void exesGridVsGridSame() {
    assertTrue(AsciiBlock.equal(exesGrid0a, exesGrid0b),
        "*: equal(exesGrid0a, exesGrid0b)");
    assertTrue(AsciiBlock.equal(exesGrid0b, exesGrid0a),
        "*: equal(exesGrid0b, exesGrid0a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesGrid1b),
        "*: equal(exesGrid1a, exesGrid1b)");
    assertTrue(AsciiBlock.equal(exesGrid1b, exesGrid1a),
        "*: equal(exesGrid1b, exesGrid1a)");

    assertTrue(AsciiBlock.eqv(exesGrid0a, exesGrid0b),
        "M: eqv(exesGrid0a, exesGrid0b)");
    assertTrue(AsciiBlock.eqv(exesGrid0b, exesGrid0a),
        "M: eqv(exesGrid0b, exesGrid0a)");
    assertTrue(AsciiBlock.eqv(exesGrid1a, exesGrid1b),
        "M: eqv(exesGrid1a, exesGrid1b)");
    assertTrue(AsciiBlock.eqv(exesGrid1b, exesGrid1a),
        "M: eqv(exesGrid1b, exesGrid1a)");
  } // exesGridVsGridSame()

  /**
   * Grids vs grids that are different.
   */
  public void exesGridVsGridDiff() {
    assertTrue(AsciiBlock.equal(exesGrid0a, exesGrid1a),
        "*: equal(exesGrid0a, exesGrid1a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesGrid0a),
        "*: equal(exesGrid1a, exesGrid0a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesGrid2a),
        "*: equal(exesGrid0a, exesGrid2a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesGrid0a),
        "*: equal(exesGrid2a, exesGrid0a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesGrid2a),
        "*: equal(exesGrid0a, exesGrid2a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesGrid1a),
        "*: equal(exesGrid2a, exesGrid0a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesGrid3a),
        "*: equal(exesGrid0a, exesGrid3a)");
    assertTrue(AsciiBlock.equal(exesGrid3a, exesGrid0a),
        "*: equal(exesGrid3a, exesGrid0a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesGrid3a),
        "*: equal(exesGrid1a, exesGrid3a)");
    assertTrue(AsciiBlock.equal(exesGrid3a, exesGrid1a),
        "*: equal(exesGrid3a, exesGrid1a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesGrid3a),
        "*: equal(exesGrid2a, exesGrid3a)");
    assertTrue(AsciiBlock.equal(exesGrid3a, exesGrid2a),
        "*: equal(exesGrid3a, exesGrid2a)");

    assertFalse(AsciiBlock.eqv(exesGrid0a, exesGrid1a),
        "M: eqv(exesGrid0a, exesGrid1a)");
    assertFalse(AsciiBlock.eqv(exesGrid1a, exesGrid0a),
        "M: eqv(exesGrid1a, exesGrid0a)");
    assertFalse(AsciiBlock.eqv(exesGrid0a, exesGrid2a),
        "M: eqv(exesGrid0a, exesGrid2a)");
    assertFalse(AsciiBlock.eqv(exesGrid2a, exesGrid0a),
        "M: eqv(exesGrid2a, exesGrid0a)");
    assertFalse(AsciiBlock.eqv(exesGrid1a, exesGrid2a),
        "M: eqv(exesGrid0a, exesGrid2a)");
    assertFalse(AsciiBlock.eqv(exesGrid2a, exesGrid1a),
        "M: eqv(exesGrid2a, exesGrid0a)");
    assertFalse(AsciiBlock.eqv(exesGrid0a, exesGrid3a),
        "M: eqv(exesGrid0a, exesGrid3a)");
    assertFalse(AsciiBlock.eqv(exesGrid3a, exesGrid0a),
        "M: eqv(exesGrid3a, exesGrid0a)");
    assertFalse(AsciiBlock.eqv(exesGrid1a, exesGrid3a),
        "M: eqv(exesGrid1a, exesGrid3a)");
    assertFalse(AsciiBlock.eqv(exesGrid3a, exesGrid1a),
        "M: eqv(exesGrid3a, exesGrid1a)");
    assertFalse(AsciiBlock.eqv(exesGrid2a, exesGrid3a),
        "M: eqv(exesGrid2a, exesGrid3a)");
    assertFalse(AsciiBlock.eqv(exesGrid3a, exesGrid2a),
        "M: eqv(exesGrid3a, exesGrid2a)");
  } // exesGridvsGridDiff

  /**
   * Rectangles vs HComps.
   */
  @Test
  public void exesRectVsHComp() {
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp0a),
        "*: equal(exesRect0a, exesHComp0a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp1a),
        "*: equal(exesRect0a, exesHComp1a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp2a),
        "*: equal(exesRect0a, exesHComp2a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp3a),
        "*: equal(exesRect0a, exesHComp3a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp4a),
        "*: equal(exesRect0a, exesHComp4a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp5a),
        "*: equal(exesRect0a, exesHComp5a)");
    assertTrue(AsciiBlock.equal(exesRect0a, exesHComp6a),
        "*: equal(exesRect0a, exesHComp6a)");

    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp0a),
        "M: eqv(exesRect0a, exesHComp0a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp1a),
        "M: eqv(exesRect0a, exesHComp1a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp2a),
        "M: eqv(exesRect0a, exesHComp2a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp3a),
        "M: eqv(exesRect0a, exesHComp3a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp4a),
        "M: eqv(exesRect0a, exesHComp4a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp5a),
        "M: eqv(exesRect0a, exesHComp5a)");
    assertFalse(AsciiBlock.eqv(exesRect0a, exesHComp6a),
        "M: eqv(exesRect0a, exesHComp6a)");
  } // exesRectVsHComp()

  /**
   * HComp vs Rect
   */
  @Test
  public void exesHCompVsRect() {
    assertTrue(AsciiBlock.equal(exesHComp0a, exesRect0a),
        "M: equal(exesHComp0a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesHComp1a, exesRect0a),
        "M: equal(exesHComp1a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesHComp2a, exesRect0a),
        "M: equal(exesHComp2a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesHComp3a, exesRect0a),
        "M: equal(exesHComp3a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesHComp4a, exesRect0a),
        "M: equal(exesHComp4a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesHComp5a, exesRect0a),
        "M: equal(exesHComp5a, exesRect0a)");
    assertTrue(AsciiBlock.equal(exesHComp6a, exesRect0a),
        "M: equal(exesHComp6a, exesRect0a)");

    assertFalse(AsciiBlock.eqv(exesHComp0a, exesRect0a),
        "M: eqv(exesHComp0a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesHComp1a, exesRect0a),
        "M: eqv(exesHComp1a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesHComp2a, exesRect0a),
        "M: eqv(exesHComp2a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesHComp3a, exesRect0a),
        "M: eqv(exesHComp3a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesHComp4a, exesRect0a),
        "M: eqv(exesHComp4a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesHComp5a, exesRect0a),
        "M: eqv(exesHComp5a, exesRect0a)");
    assertFalse(AsciiBlock.eqv(exesHComp6a, exesRect0a),
        "M: eqv(exesHComp6a, exesRect0a)");
  } // exesHCompVsRect()

  /**
   * Grid vs HComp.
   *
   * The combinatorial explosion starts (unless you think it started
   * already).
   */
  @Test
  public void exesGridVsHComp() {
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp0a),
        "*: equal(exesGrid0a, exesHComp0a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp1a),
        "*: equal(exesGrid0a, exesHComp1a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp2a),
        "*: equal(exesGrid0a, exesHComp2a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp3a),
        "*: equal(exesGrid0a, exesHComp3a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp4a),
        "*: equal(exesGrid0a, exesHComp4a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp5a),
        "*: equal(exesGrid0a, exesHComp5a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp6a),
        "*: equal(exesGrid0a, exesHComp6a)");

    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp0a),
        "*: equal(exesGrid1a, exesHComp0a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp1a),
        "*: equal(exesGrid1a, exesHComp1a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp2a),
        "*: equal(exesGrid1a, exesHComp2a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp3a),
        "*: equal(exesGrid1a, exesHComp3a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp4a),
        "*: equal(exesGrid1a, exesHComp4a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp5a),
        "*: equal(exesGrid1a, exesHComp5a)");
    assertTrue(AsciiBlock.equal(exesGrid1a, exesHComp6a),
        "*: equal(exesGrid1a, exesHComp6a)");

    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp0a),
        "*: equal(exesGrid2a, exesHComp0a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp1a),
        "*: equal(exesGrid2a, exesHComp1a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp2a),
        "*: equal(exesGrid2a, exesHComp2a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp3a),
        "*: equal(exesGrid2a, exesHComp3a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp4a),
        "*: equal(exesGrid2a, exesHComp4a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp5a),
        "*: equal(exesGrid2a, exesHComp5a)");
    assertTrue(AsciiBlock.equal(exesGrid2a, exesHComp6a),
        "*: equal(exesGrid2a, exesHComp6a)");

    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp0a),
        "*: equal(exesGrid0a, exesHComp0a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp1a),
        "*: equal(exesGrid0a, exesHComp1a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp2a),
        "*: equal(exesGrid0a, exesHComp2a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp3a),
        "*: equal(exesGrid0a, exesHComp3a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp4a),
        "*: equal(exesGrid0a, exesHComp4a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp5a),
        "*: equal(exesGrid0a, exesHComp5a)");
    assertTrue(AsciiBlock.equal(exesGrid0a, exesHComp6a),
        "*: equal(exesGrid0a, exesHComp6a)");

  } // exesGridVsHComp()

  /**
   * HComp vs. Grid
   */
  @Test
  public void exesHCompVsGrid() {
  } // exesHCompVsGrid()

  /**
   * HComp vs HComp where they should be the same.
   */
  @Test
  public void exesHCompVsHCompSame() {
  } // exesHCompVsHCompSame()

  /**
   * HComp vs HComp where they should be different. 
   *
   * The combinatorial explosion increases.
   */
  @Test
  public void exesHCompVsHCompDiff() {
  } // exesHCompVsHCompDiff()

} // class TestBlockEquiv
