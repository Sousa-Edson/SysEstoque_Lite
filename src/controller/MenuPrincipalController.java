/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import view.MenuPrincipal;
import view.internal.LoginJIF;

/**
 *
 * @author edson
 */
public class MenuPrincipalController {

    LoginJIF ChamaLoginPrincipal = new LoginJIF();

    public MenuPrincipalController() {
    }

    public void chamaLogin(MenuPrincipal form) {
        if (ChamaLoginPrincipal.isVisible()) {
            ChamaLoginPrincipal.setVisible(false);
            form.getDesktopPrincipal().remove(ChamaLoginPrincipal);
        } else {
            form.getDesktopPrincipal().remove(ChamaLoginPrincipal);
            form.getDesktopPrincipal().add(ChamaLoginPrincipal);

            ChamaLoginPrincipal.setPosicao();

            ChamaLoginPrincipal.setBorder(null);//retirar bordas;

            ((BasicInternalFrameUI) ChamaLoginPrincipal.getUI()).setNorthPane(null);
            ((BasicInternalFrameUI) ChamaLoginPrincipal.getUI()).setSouthPane(null);
            ((BasicInternalFrameUI) ChamaLoginPrincipal.getUI()).setEastPane(null);
            ((BasicInternalFrameUI) ChamaLoginPrincipal.getUI()).setWestPane(null);

            ChamaLoginPrincipal.setVisible(true);

        }
    }

}
