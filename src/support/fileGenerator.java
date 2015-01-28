/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.List;
import javax.swing.JOptionPane;
import static support.fileWriter.theFiler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Jordan Rog
 */
public class fileGenerator {
    public static fileGenerator FileCabinet = new fileGenerator();
    
    private fileGenerator(){};
        
    public void generateFile(List<String> name , List<String> price, List<String> link, List<String> comment){
        if(1 != JOptionPane.showConfirmDialog(null,"Are you sure you are ready to generate the file?","Ready?",JOptionPane.OK_CANCEL_OPTION)){
            try {
                // TODO add your handling code here:
                String filePath = JOptionPane.showInputDialog("Please, choose a file name");
                if(filePath.isEmpty()){
                    JOptionPane.showMessageDialog(null, "File name will be Autogen.txt");
                    filePath  = "Autogen";
                }else{
                    filePath += ".Autogen";
                }
                theFiler.setPath(filePath + ".txt");
                theFiler.create();
                System.out.println("Attempting to copy");
                theFiler.copyFile(getClass().getResourceAsStream("HtmlTemplate.txt"));
                for(int x = 0; x< name.size(); x++){
                    theFiler.println("  <tr>");
                    theFiler.println("    <td id = \"column\"> " + name   .get(x) + " </td>");
                    theFiler.println("    <td id = \"column\"> " + price  .get(x) + " </td>");
                    if(link.get(x).equalsIgnoreCase("no link")){
                        theFiler.println("    <td id = \"column\"> " +link.get(x) + " </td>");
                    }else{
                        theFiler.print("    <td id = \"column\"> <a style = \"color: #a8aebb \" href= \"http://" + link.get(x) + "\"> Link </a></td>");
                    }
                    theFiler.println("    <td id = \"column\"> " + comment.get(x) + " </td>");
                }
                theFiler.println("</table>");
                theFiler.println("</body>");
            } catch (Exception ex) {
                System.out.println(ex);
            }
            
        }
        
    }

    private Object getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
