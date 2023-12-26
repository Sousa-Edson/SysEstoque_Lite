/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extras;

/**
 *
 * @author edson
 */
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Container;

public class InternalFrameUtil {

    private InternalFrameUtil() {
    }

    public static void removerIcone(JInternalFrame internalFrame) {
        internalFrame.setFrameIcon(null);

        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
    }
}
