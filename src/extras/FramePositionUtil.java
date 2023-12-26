/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extras;

/**
 *
 * @author edson
 */
import java.awt.Dimension;
import javax.swing.JInternalFrame;

public class FramePositionUtil {

    private FramePositionUtil() {
    }

    public static void setCenteredPosition(JInternalFrame internalFrame) {
        Dimension desktopSize = internalFrame.getDesktopPane().getSize();
        internalFrame.setLocation((desktopSize.width - internalFrame.getSize().width) / 2,
                                  (desktopSize.height - internalFrame.getSize().height) / 2);
    }
}
