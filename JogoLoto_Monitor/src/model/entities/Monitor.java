package model.entities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.JSONArray;

public class Monitor {
    private JSONArray nrSorteado = new JSONArray();
    private JSONArray nrJogador = new JSONArray();
    private FileWriter file;
    
	public void nrEscolhido(int nrEscolhido) {
        try {
            nrSorteado.put(nrEscolhido);
            writeJSON(nrSorteado);
            readJSON();
        }
        catch (Exception e) {
            System.out.println("Erro, " + e);
        }
    }
    
    private void writeJSON(JSONArray jsArray) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("C:\\Users\\Welcome\\Desktop\\JSON\\board.json");
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
    
    public void readJSON() {	
        try {
        	Reader reader = new FileReader("C:\\Users\\Welcome\\Desktop\\JSON\\board.json");
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
}
