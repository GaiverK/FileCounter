import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

/**
 * This class sets up a global hook for the escape button
 */
public class KeyboardEscHook {
    private final GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(false);
    static boolean escCommandPressed = false;

    /**
     * Set hook for escape button
     */
    public void setEscHook(){
        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
            /**
             * If escape button was pressed, print message and shut down hook
             * @param event - key event
             */
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
                    escCommandPressed = true;
                    keyboardHook.shutdownHook();
                }
            }
        });
    }

    /**
     * Shut down hook if program finished normally
     */
    public void shutDownHook(){
        keyboardHook.shutdownHook();
    }
}
