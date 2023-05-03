import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Logo extends JPanel{
    ImageIcon i;
    ImageIcon newIcon;

    BufferedImage bi;

    Graphics g;

    public Logo()
    {
        i = new ImageIcon("Images/My project-1 (2).png");
        Image i1 = i.getImage();
        bi = new BufferedImage(i1.getWidth(null),i1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        g = bi.createGraphics();
        g.drawImage(i1,250,600,150,100,null);
        newIcon = new ImageIcon(bi);

        add(new JLabel(newIcon));
    }
}
