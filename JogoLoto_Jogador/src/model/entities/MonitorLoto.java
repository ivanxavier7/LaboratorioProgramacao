package model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.JSONArray;

public class MonitorLoto {
    private JSONArray nrSorteado = new JSONArray();
    private FileWriter file;
    
	public void nrEscolhido(int nrEscolhido) {
        try {
            nrSorteado.put(nrEscolhido);
            writeJSON(nrSorteado);
        }
        catch (Exception e) {
            System.out.println("Erro, " + e);
        }
    }
    
    private void writeJSON(JSONArray jsArray) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("/JogoLoto_Monitor_Handler/src/json/monitor.json");
            file.write(jsArray.toString());
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    public String readJSON() {
    	String data = null;
        try {
        	String filePath = new File("").getAbsolutePath();
            String path = new File( File.separator +".." +File.separator +"JogoLoto_Monitor_Handler/src/json/monitor.json")
                    .getAbsolutePath();
System.out.println(path);
            File myObj = new File("JogoLoto_Monitor_Handler/src/json/monitor.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
            }
            myReader.close();
            
          } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao ler o ficheiro!");
            e.printStackTrace();
          }
        return data;
    }
    
    private void resetJSON(JSONArray jsArray) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("/JogoLoto_Monitor_Handler/src/json/monitor.json");
            file.write("");
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    public void writeJogadoresJSON(int nrJogadores) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("/JogoLoto_Monitor_Handler/src/json/nrjogadores.json");
            file.write(String.valueOf(nrJogadores));
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    public void readJogadoresJSON() {	
        try {
        	Reader reader = new FileReader("/JogoLoto_Monitor_Handler/src/json/nrjogadores.json");
            int data = reader.read();  
            while (data != -1) {  
                System.out.print((char) data);  
                data = reader.read();  
            }  
            reader.close();  
        } catch (Exception ex) {  
            System.out.println(ex.getMessage());  
        } 
    }
    
    public void save() {
    	writeJSON(nrSorteado);
    }
    
    public void reset() {
    	resetJSON(nrSorteado);
    }
}
