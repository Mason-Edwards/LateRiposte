import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class RiposteCheckGUI extends JFrame
{

    private static JFrame frame;

    public RiposteCheckGUI()
    {
        frame = new JFrame();

        frame.pack();
        frame.setVisible( true );
        frame.setSize(250,250);

    }

    public static void checkRiposte(long time) throws InterruptedException {
        if(time < 200 || time > 250)
        {
            frame.getContentPane().setBackground(Color.red);
        } else
        {
            frame.getContentPane().setBackground(Color.green);
        }
        Thread.sleep(600);
        frame.getContentPane().setBackground(Color.white);

    }
}
