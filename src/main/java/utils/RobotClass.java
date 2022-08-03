package utils;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotClass {
    public static void uploadFileUsingRobot(String relativePath){
        try {
            Robot rb = new Robot();
            StringSelection str = new StringSelection(FileUtils.getAbsolutePath(relativePath));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
