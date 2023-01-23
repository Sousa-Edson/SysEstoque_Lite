/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackupRestore.Novo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class PostgresBackup_X86NOV {

    public PostgresBackup_X86NOV() {

    }

    public static void realizaBackup() throws IOException, InterruptedException {
        
          JFileChooser save;
        int flag = 0;
        final List<String> comandos = new ArrayList<String>();
        
        save = new JFileChooser(new File("C:/BKPBANCO/"));
            int op = save.showSaveDialog(null);
            if (op == JFileChooser.APPROVE_OPTION) {
                File arq = save.getSelectedFile();
                String nomeDoArquivo = save.getName(arq);//aqui pega somente o nome do arquivo
                String pathDoArquivo = arq.toString(); //aqui pega o caminho do backup  
                //aqui você testa se a string recebeu o caminho do arquivo   
        
        String dir = "C:/BKPBANCO";
        comandos.add("C:\\Program Files (x86)\\PostgreSQL\\9.4\\bin\\pg_dump.exe"); //cecom
//                    comandos.add("C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe"); //cecom
        //comandos.add("C:\\Arquivos de programas\\PostgreSQL\\9.2\\bin\\pg_dump.exe"); 
//                comandos.add("C:\\Program Files\\PostgreSQL\\9.6\\bin\\pg_dump.exe");
//C:\Program Files (x86)\PostgreSQL\8.4
        comandos.add("-i");
        comandos.add("-h");
        comandos.add("localhost");
        //comandos.add("192.168.0.25");
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");

        //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+JOptionPane.showInputDialog(null,"Digite o nome do Backup")+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+(Character.getNumericValue(recebe)+1)+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
//                       comandos.add("C:\\BKPBANCO");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
//                      comandos.add("C:\\BKPBANCO\\"+1+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
//                        comandos.add("C:\\BKPBANCO\\Backup\\" +"SysEstoque_(X)_Banco de dados !"+"  "+JOptionPane.showInputDialog(null,"Digite o nome do Backup")+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        comandos.add(pathDoArquivo + "  [SysEstoque(Versao51)] [Banco de dados]" + "  " + getDateTime() + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.                         
        comandos.add("SysEstoqueVersao51");
        ProcessBuilder pb = new ProcessBuilder(comandos);

        pb.environment().put("PGPASSWORD", "1");

        try {
            final Process process = pb.start();

            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
                        line = r.readLine();
                    if (line != null) {
                        flag = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "erro");
                    }
            
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();

            process.waitFor();
            process.destroy();
             if (flag == 1) {
                      JOptionPane.showMessageDialog(null, "backup realizado com sucesso.");
                    } else {
                    }

        } catch (IOException e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "IOException e\nErro\nTente C:\\Program Files");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            JOptionPane.showMessageDialog(null, "InterruptedException ie\nErro\nTente C:\\Program Files");
        }
    }}

    public static void main(String args[]) throws IOException, InterruptedException {
        PostgresBackup_X86NOV b = new PostgresBackup_X86NOV();
        b.realizaBackup();
    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
