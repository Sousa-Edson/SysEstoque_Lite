/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackupRestore.Novo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MenuPrincipal;

/**
 *
 * @author edson
 */
public class MainBackup {

    public static void postgresBackup_X64NOV() {
        try {
            PostgresBackup_X64NOV.realizaBackup();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void postgresBackup_X86NOV() {
        try {
            PostgresBackup_X86NOV.realizaBackup();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void postgresRestore_X64NOV() {
        PostgresRestore_X64NOV restore = new PostgresRestore_X64NOV();
    }

    public static void postgresRestore_X86NOV() {
        PostgresRestore_X86NOV restore = new PostgresRestore_X86NOV();
    }
}
