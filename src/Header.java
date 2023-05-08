import javax.swing.*;
import java.awt.*;

public class Header extends JLabel{
    JColorChooser c;
    Font f;

    enum Color{
        TEAL,
        TAN;
    }

    public Header(String s, Color c1)
    {
        setText(s);
        c = new JColorChooser();
        if(c1.equals(Color.TEAL))
        {
            c.setColor(0, 153,153);
            setForeground(c.getColor());
        }
        else
        {
            c.setColor(255,255,204);
            setForeground(c.getColor());
        }

        f = new Font(Font.DIALOG_INPUT, Font.PLAIN, 25);
        setFont(f);

    }
}
