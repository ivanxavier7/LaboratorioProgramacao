package model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.JSONArray;

import json.ConfigJSON;

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
    
    public static Boolean checkJSON(int textFieldNumber) {
    	Boolean result = false;
    	String data = null;
    	
        try {
            File myObj = new File(ConfigJSON.getPathMonitor());
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
            }
            myReader.close();
            
          } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao ler o ficheiro!");
            e.printStackTrace();
          }
        String[] items = data.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
                if(results[i]==textFieldNumber) {
                	result = true;
                }
                System.out.println(results[i]);
            } catch (NumberFormatException e) {
            };
        }
        return result;
    }
    
    
    public static String readJSON() {
    	String data = null;
        try {
            File myObj = new File(ConfigJSON.getPathMonitor());
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
    
    private void resetJSON() {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(ConfigJSON.getPathMonitor());
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
    	resetJSON();
    }
}
