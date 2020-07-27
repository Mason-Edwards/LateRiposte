public class LateRiposte {
    public static void main(String[] args)
    {

    //Init listeners
    initListeners();

    //Init GUI
    ConsoleGUI.initFrame();


        RiposteCheckGUI riposteCheckGUI = new RiposteCheckGUI();
    }


    public static void initListeners()
    {
        MouseListener.initListener();
        KeyboardListener.initKeyboard();
    }

}

