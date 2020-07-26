import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardListener implements NativeKeyListener {
    private static int keyPressed;

    public void nativeKeyPressed(NativeKeyEvent e) {
        keyPressed = e.getKeyCode();

        //If escape is pressed unregister hook
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        } else
        {
            riposteTime();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public static void initKeyboard(){
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new KeyboardListener());

        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    public  void riposteTime()
    {
        // Dont need to reset keyPressed because mouseBefore gets reset so this never gets runs when just pressing E
        if(NativeKeyEvent.getKeyText(keyPressed).equals("E") && MouseListener.getMouseBefore() == 2)
        {
            long startTime = MouseListener.getStartTime();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("Overhead - Time: " + time + "ms");

            MouseListener.setMouseBefore(0);
            MouseListener.setStartTime(0);
        }
    }
}