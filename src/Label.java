import javax.swing.*;
import java.awt.*;

public class Label extends JLabel{

    JColorChooser c;
    Font f;
    public Label(String s)
    {
        setText(s);
        c = new JColorChooser();
        c.setColor(0, 153,153);
        f = new Font(Font.DIALOG_INPUT, Font.PLAIN, 18);

        setForeground(c.getColor());
        setFont(f);

    }


}

