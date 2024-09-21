package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Tests of the various ASCII Blocks.
 */
public class TestBlocks {
  // +-------+-------------------------------------------------------
  // | Empty |
  // +-------+

  /**
   * Do we successfully build the empty block?
   */
  @Test
  public void testEmpty() {
    AsciiBlock empty = new Empty();
    assertEquals(0, empty.width(), 
        "R: Empty block has no width");
    assertEquals(0, empty.height(), 
        "R: Empty block has no height");
    assertEquals("", TestUtils.toString(empty),
        "R: Empty block has no contents");
  } // testEmpty()

  // +-------+-------------------------------------------------------
  // | Lines |
  // +-------+

  /**
   * Do we successfully build lines?
   */
  @Test
  public void testLine() throws Exception {
    Line line = new Line("Hello");
    assertEquals(5, line.width(),
        "R: Basic line has appropriate width");
    assertEquals(1, line.height(),
        "R: Basic line has appropriate height");
    assertEquals("Hello", line.row(0),
        "R: Basic line has appropriate contents");
  } // testLine()

  /**
   * Do we succesfully change lines?
   */
  @Test
  public void testLineChange() throws Exception {
    Line line = new Line("hi");
    assertEquals(2, line.width());
    assertEquals(1, line.height());
    assertEquals("hi", line.row(0));
    line.update("Good day");
    assertEquals(8, line.width());
    assertEquals(1, line.height());
    assertEquals("Good day", line.row(0));
  } // testLineChange()

  // +------------+--------------------------------------------------
  // | Rectangles |
  // +------------+

  /**
   * Do we successfully build rectangles?
   */
  @Test
  public void testRect() throws Exception {
    assertEquals("X\n", TestUtils.toString(new Rect('X', 1, 1)), 
        "R: 1x1 rectangle");
    assertEquals("""
                 YYY
                 YYY
                 """,
        TestUtils.toString(new Rect('Y', 3, 2)),
        "R: 3x2 rectangle");
  } // testRect()

  /**
   * Can we successfully change rectangles?
   */
  @Test
  public void testRectChange() throws Exception {
    Rect rect = new Rect('!', 3, 4);
    assertEquals("!!!\n!!!\n!!!\n!!!\n", TestUtils.toString(rect), 
        "R: original 3x4 rectangle");

    rect.wider();
    assertEquals("!!!!\n!!!!\n!!!!\n!!!!\n", TestUtils.toString(rect),
        "M: wider version of rectangle, now 4x4");

    rect.wider();
    assertEquals("!!!!!\n!!!!!\n!!!!!\n!!!!!\n", TestUtils.toString(rect),
        "M: even wider version of rectangle, now 5x4");

    rect.taller();
    assertEquals("!!!!!\n!!!!!\n!!!!!\n!!!!!\n!!!!!\n", 
        TestUtils.toString(rect),
        "M: taller version of rectangle, now 5x5");

    rect.shorter();
    rect.shorter();
    rect.shorter();
    assertEquals("!!!!!\n!!!!!\n", TestUtils.toString(rect),
        "M: much shorter version of rectangle, now 5x2");

    rect.shorter();
    assertEquals("!!!!!\n", TestUtils.toString(rect),
        "M: shortest version of rectangle, now 5x1");

    rect.shorter();
    assertEquals("!!!!!\n", TestUtils.toString(rect),
        "E: cannot shorten rectangle below 5x1");

    rect.taller();
    assertEquals("!!!!!\n!!!!!\n", TestUtils.toString(rect),
        "E: can make rectangle taller after shortening to base, now 5x2");

    rect.narrower();
    assertEquals("!!!!\n!!!!\n", TestUtils.toString(rect),
        "M: narrower version rectangle, now 4x2");

    rect.narrower();
    rect.narrower();
    rect.narrower();
    assertEquals("!\n!\n", TestUtils.toString(rect),
        "M: very narrow rectangle, now 1x2");

    rect.narrower();
    assertEquals("!\n!\n", TestUtils.toString(rect),
        "E: cannot make rectangle narrower than 1 column, still 1x2");

    rect.wider();
    assertEquals("!!\n!!\n", TestUtils.toString(rect),
        "E: widening after narrowing, now 2x2");

  } // testRectChange()

  // +-------+-------------------------------------------------------
  // | Boxed |
  // +-------+

  /**
   * Testing of basic boxed structures.
   */
  @Test
  void TestBoxedBasic() throws Exception {
    assertNotNull(new Boxed(new Line("Hello")),
        "R: Can box text lines");
    assertNotNull(new Boxed(new Rect('B', 3, 4)),
        "R: Can box rectangles");
  } // TestBoxedBasic()

  /**
   * Testing of basic boxed lines.
   */
  @Test
  void TestBoxedLine() {
    AsciiBlock boxedLine = new Boxed(new Line("Hello"));
    assertEquals(7, boxedLine.width(),
        "M: Correct width for a boxed line");
    assertEquals(3, boxedLine.height(),
        "M: Correct height for a boxed line");
    assertEquals("/-----\\\n|Hello|\n\\-----/\n", TestUtils.toString(boxedLine),
        "M: Correct contents for a boxed line");
  } // TestBoxedLine()

  /**
   * Testing of basic boxed rectangles.
   */
  @Test
  void TestBoxedRect() throws Exception {
    AsciiBlock boxedRect = new Boxed(new Rect('B', 3, 4));
    assertEquals(5, boxedRect.width(),
        "M: Correct width for a boxed rectangle");
    assertEquals(6, boxedRect.height(),
        "M: Correct height for a boxed rectangle");
    assertEquals("""
                 /---\\
                 |BBB|
                 |BBB|
                 |BBB|
                 |BBB|
                 \\---/
                 """,
        TestUtils.toString(boxedRect),
        "M: Correct contents for a boxed rectangle");
  } // TestBoxedRect()

  /**
   * Test of boxed lines that have been narrowed.
   */
  @Test
  public void testBoxedLineNarrower() {
    Line line = new Line("Hello");
    AsciiBlock boxedLine = new Boxed(line);

    line.update("Hi");
    assertEquals(4, boxedLine.width(),
        "E: Correct width after making boxed line narrower");
    assertEquals(3, boxedLine.height(),
        "E: Correct height after making boxed line narrower");
    assertEquals("/--\\\n|Hi|\n\\--/\n", TestUtils.toString(boxedLine),
        "E: Correct contents for narrowed boxed line");
  } // testBoxedLineNarrower()

  /**
   * Test of boxed lines that have been made wider.
   */
  @Test
  public void testBoxedLineWider() {
    Line line = new Line("Bye");
    AsciiBlock boxedLine = new Boxed(line);

    line.update("Goodbye");
    assertEquals(9, boxedLine.width(),
        "E: Correct width after making boxed line wider");
    assertEquals(3, boxedLine.height(),
        "E: Correct height after making boxed line wider");
    assertEquals("/-------\\\n|Goodbye|\n\\-------/\n", TestUtils.toString(boxedLine),
        "E: Correct contents for widened boxed line");
  } // testBoxedLineWider()

  /**
   * Test of boxed rects that change. We do this as one test so that
   * we can stack changes.
   */
  @Test
  public void testBoxedRectChange() throws Exception {
    Rect rect = new Rect('Q', 3, 4);
    AsciiBlock boxedRect = new Boxed(rect);
    rect.wider();

    assertEquals(6, boxedRect.width(),
        "E: Correct width for a wider boxed rectangle");
    assertEquals(6, boxedRect.height(),
        "E: Correct height for a wider boxed rectangle");
    assertEquals("""
                 /----\\
                 |QQQQ|
                 |QQQQ|
                 |QQQQ|
                 |QQQQ|
                 \\----/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a wider boxed rectangle");

    rect.narrower();
    rect.narrower();

    assertEquals(4, boxedRect.width(),
        "E: Correct width for a narrower boxed rectangle");
    assertEquals(6, boxedRect.height(),
        "E: Correct height for a narrower boxed rectangle");
    assertEquals("""
                 /--\\
                 |QQ|
                 |QQ|
                 |QQ|
                 |QQ|
                 \\--/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a narrower boxed rectangle");

    rect.shorter();
    rect.shorter();

    assertEquals(4, boxedRect.width(),
        "E: Correct width for a shorter boxed rectangle");
    assertEquals(4, boxedRect.height(),
        "E: Correct height for a shorter boxed rectangle");
    assertEquals("""
                 /--\\
                 |QQ|
                 |QQ|
                 \\--/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a shorter boxed rectangle");

    rect.taller();

    assertEquals(4, boxedRect.width(),
        "E: Correct width for a taller boxed rectangle");
    assertEquals(5, boxedRect.height(),
        "E: Correct height for a taller boxed rectangle");
    assertEquals("""
                 /--\\
                 |QQ|
                 |QQ|
                 |QQ|
                 \\--/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a taller boxed rectangle");
  } // testBoxedRectChange()

} // class TestBlocks
