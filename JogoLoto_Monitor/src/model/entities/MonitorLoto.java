package model.entities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import org.json.JSONArray;

import json.ConfigJSON;

public class MonitorLoto {
    private static JSONArray nrSorteado = new JSONArray();
    private PrintWriter filePrint;
    private static FileWriter file;
    
	public static void nrEscolhido(int nrEscolhido) {
        try {
            nrSorteado.put(nrEscolhido);
            writeJSON(nrSorteado);
        }
        catch (Exception e) {
            System.out.println("Erro, " + e);
        }
    }
    
    private static void writeJSON(JSONArray jsArray) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(ConfigJSON.getPathMonitor());
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
        	Reader reader = new FileReader(ConfigJSON.getPathMonitor());
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
    
    private void resetJSON(JSONArray jsArray) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            filePrint = new PrintWriter(ConfigJSON.getPathMonitor());
            filePrint.write("");
            nrSorteado = new JSONArray();
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            filePrint.flush();
			filePrint.close();
        }
    }
    
    
    public void writeJogadoresJSON(int nrJogadores) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(ConfigJSON.getPathPlayers());
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
        	Reader reader = new FileReader(ConfigJSON.getPathPlayers());
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
