public class LateRiposte {
    public static void main(String[] args)
    {

    //Init listeners
    MouseListener.initListener();
    KeyboardListener.initKeyboard();

    //Init GUI
    ConsoleGUI.initFrame();

    }


    public void initListeners()
    {
        MouseListener.initListener();
        KeyboardListener.initKeyboard();
    }

}

