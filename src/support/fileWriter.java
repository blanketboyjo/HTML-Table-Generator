/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

/**
 *
 * @author Jordan Rog
 */
public class fileWriter {
    public static fileWriter theFiler = new fileWriter();
    
    private Path  m_target;
    private File  m_file;
    private BufferedWriter m_scribe;

    
    private fileWriter(){
    }
    
    public void setPath(String path){
        m_target = Paths.get(path);
    }
    
    public void create() throws IOException{
        boolean willMakeFile;
        m_file  = new File(m_target.toString());     
        if(Files.exists(m_target)){
           willMakeFile = (0 ==  JOptionPane.showConfirmDialog(null,"The file exists, do you want to overwrite?","Confirm",JOptionPane.YES_NO_OPTION));
        }else{
            willMakeFile = true;
        }
        if(willMakeFile){
            try{
            Files.delete(m_target);
            }catch(Exception e){
                //Goes unhandled
            }
            m_file.createNewFile();            
        }
        m_scribe= new BufferedWriter(new FileWriter(m_file.getName(),true));
    }
    
    public void print(String toFile) throws IOException{
        m_scribe.write(toFile);
        m_scribe.flush();
    }
    
    public void println(String toFile) throws IOException{
        m_scribe.write(toFile);
        m_scribe.newLine();
        m_scribe.flush();
    }
    
    public void copyFile(InputStream inStream) throws IOException{
        InputStreamReader x   = new InputStreamReader(inStream);
        BufferedReader reader = new BufferedReader(x);
        
        String lineToPrint;
        while(null != (lineToPrint = reader.readLine())){
            System.out.println(lineToPrint);
            println(lineToPrint);
        }    
        reader.close();
        x.close();
        inStream.close();
    }
    
}
