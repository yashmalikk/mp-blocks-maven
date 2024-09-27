package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.OurBlock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestOurBlock {
  
  // Mock implementation of AsciiBlock for testing
  private static class MockAsciiBlock implements AsciiBlock {
    private final String[] data;

    MockAsciiBlock(String[] data) {
      this.data = data;
    }

    @Override
    public String row(int i) {
      return data[i];
    }

    @Override
    public int height() {
      return data.length;
    }

    @Override
    public int width() {
      return data[0].length();
    }

    @Override
    public boolean eqv(AsciiBlock other) {
      if (other instanceof MockAsciiBlock) {
        MockAsciiBlock otherMock = (MockAsciiBlock) other;
        return java.util.Arrays.equals(this.data, otherMock.data);
      }
      return false;
    }
  }

  @Test
  public void testConstructor_NullBlock() {
    assertThrows(IllegalArgumentException.class, () -> {
      new OurBlock(null, 1);
    });
  }

  @Test
  public void testRow_ValidRow() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    OurBlock ourBlock = new OurBlock(mockBlock, 1);
    
    assertEquals("bcd", ourBlock.row(0));
    assertEquals("efg", ourBlock.row(1));
  }

  @Test
  public void testRow_InvalidRow() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    OurBlock ourBlock = new OurBlock(mockBlock, 1);
    
    assertThrows(Exception.class, () -> {
      ourBlock.row(2); // Row index out of bounds
    });
  }

  @Test
  public void testHeight() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    OurBlock ourBlock = new OurBlock(mockBlock, 1);
    
    assertEquals(2, ourBlock.height());
  }

  @Test
  public void testWidth() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    OurBlock ourBlock = new OurBlock(mockBlock, 1);
    
    assertEquals(3, ourBlock.width());
  }

  @Test
  public void testEqv_SameBlock() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock1 = new MockAsciiBlock(data);
    AsciiBlock mockBlock2 = new MockAsciiBlock(data);
    
    OurBlock ourBlock1 = new OurBlock(mockBlock1, 1);
    OurBlock ourBlock2 = new OurBlock(mockBlock2, 1);
    
    assertTrue(ourBlock1.eqv(ourBlock2));
  }

  @Test
  public void testEqv_DifferentIncrement() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    
    OurBlock ourBlock1 = new OurBlock(mockBlock, 1);
    OurBlock ourBlock2 = new OurBlock(mockBlock, 2);
    
    assertFalse(ourBlock1.eqv(ourBlock2));
  }

  @Test
  public void testEqv_DifferentContents() throws Exception {
    String[] data1 = {"abc", "def"};
    String[] data2 = {"ghi", "jkl"};
    AsciiBlock mockBlock1 = new MockAsciiBlock(data1);
    AsciiBlock mockBlock2 = new MockAsciiBlock(data2);
    
    OurBlock ourBlock1 = new OurBlock(mockBlock1, 1);
    OurBlock ourBlock2 = new OurBlock(mockBlock2, 1);
    
    assertFalse(ourBlock1.eqv(ourBlock2));
  }

  @Test
  public void testRow_WithNegativeIncrement() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    OurBlock ourBlock = new OurBlock(mockBlock, -1);
    
    assertEquals("`ab", ourBlock.row(0));
    assertEquals("cde", ourBlock.row(1));
  }

  @Test
  public void testRow_WithZeroIncrement() throws Exception {
    String[] data = {"abc", "def"};
    AsciiBlock mockBlock = new MockAsciiBlock(data);
    OurBlock ourBlock = new OurBlock(mockBlock, 0);
    
    assertEquals("abc", ourBlock.row(0));
    assertEquals("def", ourBlock.row(1));
  }
}
