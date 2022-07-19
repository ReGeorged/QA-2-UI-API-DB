package utils;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotClass {
    public static void robotsAreSoCool(String RelativePath){

        try {
            Robot rb = new Robot();

            // copying File path to Clipboard
            StringSelection str = new StringSelection(FileUtils.getAbsolutePath(RelativePath));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            // press Contol+V for pasting
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            // release Contol+V for pasting
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);

            // for pressing and releasing Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);





        } catch (AWTException e) {
            throw new RuntimeException(e);

        }


    }
}
