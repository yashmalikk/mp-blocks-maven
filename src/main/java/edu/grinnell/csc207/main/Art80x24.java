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
   * @param args Command-line arguments (currently ignored).
   *
   * @exception Exception If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    
    String outer = new String("  mmmm  ");
    String outerMid = new String(" m    m ");
    String mid = new String("m      m");  

    // Create mini image to be used
    String[] miniArt = {outer, outerMid, mid, mid, outerMid, outer};

    AsciiBlock art = new Lines(miniArt);
    AsciiBlock artMutated = new OurBlock(art, 390);

    // Mutate the miniArt
    AsciiBlock art2 = new OurBlock(artMutated, 1);

    // Mutate the surrounded art
    AsciiBlock art3 = new Surrounded(art2, 'b');
    AsciiBlock art4 = new OurBlock(art3, 1);
    AsciiBlock art5 = new OurBlock(art4, 1);
    AsciiBlock art6 = new OurBlock(art5, 1);

    // Mutate and Create full row of art
    AsciiBlock[] Hcomp1 = {art3, art4, art5, art6};
    AsciiBlock Hcomp1Group = new HComp(VAlignment.CENTER, Hcomp1);
    AsciiBlock Hcomp2Group = new OurBlock(Hcomp1Group, 1);

    AsciiBlock[] HcompBig = {Hcomp1Group, Hcomp2Group};

    AsciiBlock row1 = new HComp(VAlignment.BOTTOM, HcompBig);

    // Mutate and Stack rows together
    AsciiBlock row2 = new OurBlock(row1, 494);
    AsciiBlock row3 = new OurBlock(row1, 1);

    AsciiBlock[] totalArt = {row1, row2, row3};

    AsciiBlock bigArt = new VComp(HAlignment.LEFT, totalArt);

    AsciiBlock.print(pen, bigArt);

    
    // This is the image printed since it takes too long to print sometimes.
    // Looks better printed since the overflow of characters on the edge is managed by the terminals spacing of characters.

    // bbbbbbbbbbccccccccccddddddddddeeeeeeeeeeccccccccccddddddddddeeeeeeeeeeffffffffff
    // bƧƧǴǴǴǴƧƧbcƨƨǵǵǵǵƨƨcdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪecƨƨǵǵǵǵƨƨcdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪefƫƫǸǸǸǸƫƫf
    // bƧǴƧƧƧƧǴƧbcƨǵƨƨƨƨǵƨcdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪecƨǵƨƨƨƨǵƨcdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪefƫǸƫƫƫƫǸƫf
    // bǴƧƧƧƧƧƧǴbcǵƨƨƨƨƨƨǵcdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷecǵƨƨƨƨƨƨǵcdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷefǸƫƫƫƫƫƫǸf
    // bǴƧƧƧƧƧƧǴbcǵƨƨƨƨƨƨǵcdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷecǵƨƨƨƨƨƨǵcdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷefǸƫƫƫƫƫƫǸf
    // bƧǴƧƧƧƧǴƧbcƨǵƨƨƨƨǵƨcdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪecƨǵƨƨƨƨǵƨcdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪefƫǸƫƫƫƫǸƫf
    // bƧƧǴǴǴǴƧƧbcƨƨǵǵǵǵƨƨcdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪecƨƨǵǵǵǵƨƨcdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪefƫƫǸǸǸǸƫƫf
    // bbbbbbbbbbccccccccccddddddddddeeeeeeeeeeccccccccccddddddddddeeeeeeeeeeffffffffff
    // ɐɐɐɐɐɐɐɐɐɐɑɑɑɑɑɑɑɑɑɑɒɒɒɒɒɒɒɒɒɒɓɓɓɓɓɓɓɓɓɓɑɑɑɑɑɑɑɑɑɑɒɒɒɒɒɒɒɒɒɒɓɓɓɓɓɓɓɓɓɓɔɔɔɔɔɔɔɔɔɔ
    // ɐΕΕϢϢϢϢΕΕɐɑΖΖϣϣϣϣΖΖɑɒΗΗϤϤϤϤΗΗɒɓΘΘϥϥϥϥΘΘɓɑΖΖϣϣϣϣΖΖɑɒΗΗϤϤϤϤΗΗɒɓΘΘϥϥϥϥΘΘɓɔΙΙϦϦϦϦΙΙɔ
    // ɐΕϢΕΕΕΕϢΕɐɑΖϣΖΖΖΖϣΖɑɒΗϤΗΗΗΗϤΗɒɓΘϥΘΘΘΘϥΘɓɑΖϣΖΖΖΖϣΖɑɒΗϤΗΗΗΗϤΗɒɓΘϥΘΘΘΘϥΘɓɔΙϦΙΙΙΙϦΙɔ
    // ɐϢΕΕΕΕΕΕϢɐɑϣΖΖΖΖΖΖϣɑɒϤΗΗΗΗΗΗϤɒɓϥΘΘΘΘΘΘϥɓɑϣΖΖΖΖΖΖϣɑɒϤΗΗΗΗΗΗϤɒɓϥΘΘΘΘΘΘϥɓɔϦΙΙΙΙΙΙϦɔ
    // ɐϢΕΕΕΕΕΕϢɐɑϣΖΖΖΖΖΖϣɑɒϤΗΗΗΗΗΗϤɒɓϥΘΘΘΘΘΘϥɓɑϣΖΖΖΖΖΖϣɑɒϤΗΗΗΗΗΗϤɒɓϥΘΘΘΘΘΘϥɓɔϦΙΙΙΙΙΙϦɔ
    // ɐΕϢΕΕΕΕϢΕɐɑΖϣΖΖΖΖϣΖɑɒΗϤΗΗΗΗϤΗɒɓΘϥΘΘΘΘϥΘɓɑΖϣΖΖΖΖϣΖɑɒΗϤΗΗΗΗϤΗɒɓΘϥΘΘΘΘϥΘɓɔΙϦΙΙΙΙϦΙɔ
    // ɐΕΕϢϢϢϢΕΕɐɑΖΖϣϣϣϣΖΖɑɒΗΗϤϤϤϤΗΗɒɓΘΘϥϥϥϥΘΘɓɑΖΖϣϣϣϣΖΖɑɒΗΗϤϤϤϤΗΗɒɓΘΘϥϥϥϥΘΘɓɔΙΙϦϦϦϦΙΙɔ
    // ɐɐɐɐɐɐɐɐɐɐɑɑɑɑɑɑɑɑɑɑɒɒɒɒɒɒɒɒɒɒɓɓɓɓɓɓɓɓɓɓɑɑɑɑɑɑɑɑɑɑɒɒɒɒɒɒɒɒɒɒɓɓɓɓɓɓɓɓɓɓɔɔɔɔɔɔɔɔɔɔ
    // ccccccccccddddddddddeeeeeeeeeeffffffffffddddddddddeeeeeeeeeeffffffffffgggggggggg
    // cƨƨǵǵǵǵƨƨcdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪefƫƫǸǸǸǸƫƫfdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪefƫƫǸǸǸǸƫƫfgƬƬǹǹǹǹƬƬg
    // cƨǵƨƨƨƨǵƨcdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪefƫǸƫƫƫƫǸƫfdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪefƫǸƫƫƫƫǸƫfgƬǹƬƬƬƬǹƬg
    // cǵƨƨƨƨƨƨǵcdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷefǸƫƫƫƫƫƫǸfdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷefǸƫƫƫƫƫƫǸfgǹƬƬƬƬƬƬǹg
    // cǵƨƨƨƨƨƨǵcdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷefǸƫƫƫƫƫƫǸfdǶƩƩƩƩƩƩǶdeǷƪƪƪƪƪƪǷefǸƫƫƫƫƫƫǸfgǹƬƬƬƬƬƬǹg
    // cƨǵƨƨƨƨǵƨcdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪefƫǸƫƫƫƫǸƫfdƩǶƩƩƩƩǶƩdeƪǷƪƪƪƪǷƪefƫǸƫƫƫƫǸƫfgƬǹƬƬƬƬǹƬg
    // cƨƨǵǵǵǵƨƨcdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪefƫƫǸǸǸǸƫƫfdƩƩǶǶǶǶƩƩdeƪƪǷǷǷǷƪƪefƫƫǸǸǸǸƫƫfgƬƬǹǹǹǹƬƬg
    // ccccccccccddddddddddeeeeeeeeeeffffffffffddddddddddeeeeeeeeeeffffffffffgggggggggg


    pen.close();
  } // main(String[])
} // class Art80x24
