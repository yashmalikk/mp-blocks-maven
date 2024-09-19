package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests of the various ASCII Blocks.
 */
public class TestBlocks {
  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Determine if two blocks are equal in that they have the same,
   * width, height, and rows. 
   *
   * We do not rely on `AsciiBlock.equals` because someone may have
   * changed it to better pass tests.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are in the same memory location and false otherwise.
   */
  public static boolean same(AsciiBlock block1, AsciiBlock block2) {
    if (block1.width() != block2.width()) {
      return false;
    } // if
    if (block1.height() != block2.height()) {
      return false;
    } // if
    for (int i = 0; i < block1.height(); i++) {
      try {
        if (!block1.row(i).equals(block2.row(i))) {
          return false;
        } // if
      } catch (Exception e) {
        return false;
      } // try/catch
    } // for
    return true;
  } // same(AsciiBlock, AsciiBlock)

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Do we successfully build the empty block?
   */
  @Test
  public void testEmpty() {
    AsciiBlock empty = new Empty();
    assertEquals(0, empty.width());
    assertEquals(0, empty.height());
  } // testEmpty()

  /**
   * Do we successfully build lines?
   */
  @Test
  public void testLine() throws Exception {
    Line line = new Line("Hello");
    assertEquals(5, line.width());
    assertEquals(1, line.height());
    assertEquals("Hello", line.row(0));
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
} // class TestBlocks
