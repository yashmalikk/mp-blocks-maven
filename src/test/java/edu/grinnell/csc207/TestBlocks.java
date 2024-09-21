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
  // | Tests |
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

} // class TestBlocks
