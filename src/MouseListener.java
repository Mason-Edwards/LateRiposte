import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.lang.annotation.Native;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MouseListener implements NativeMouseInputListener {

    private int mousePressed;
    private static int mouseBefore;
    private static long startTime;

    public void nativeMouseClicked(NativeMouseEvent e)
    {
        //System.out.println("Mouse Clicked: " + e.getClickCount());
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        //System.out.println("Mouse Pressed: " + e.getButton());
        mousePressed = e.getButton();
        try {
            riposteTime();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        //System.out.println("Mouse Released: " + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        //System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        //System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

    public static void initListener()
    {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Construct the example object.
        MouseListener example = new MouseListener();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);

        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    public void riposteTime() throws InterruptedException {
        if (mousePressed == 2) //Parry
        {
            startTime = System.currentTimeMillis();
            mouseBefore = 2;
        }

        if (mousePressed == 1 && mouseBefore == 2) //LMB Swing
        {

            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("LMB - Time: " + time + "ms");

            RiposteCheckGUI.checkRiposte(time);
            mouseBefore = 0;
            startTime = 0;
        }
    }
        public static int getMouseBefore(){return mouseBefore;}
        public static void setMouseBefore(int setMouseBefore){mouseBefore = setMouseBefore;}

        public static long getStartTime(){return startTime;}
        public static void setStartTime(int setStartTime){startTime = setStartTime;}

}