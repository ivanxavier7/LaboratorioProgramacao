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

/**
 *	Classe respons�vel pela sincroniza��o entre a interface e os dados
 *	A inten��o � criar uma zona para os programas partilharem mem�ria entre si.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class MonitorLoto {
    private JSONArray nrSorteado = new JSONArray();
    private static ConfigJSON configJSON = new ConfigJSON();
    private FileWriter file;
    
    /**
     *  Guarda um certo n�mero num JSONArray e guarda o Array num ficheiro
     *   
     *   @param		nrEscolhido		N�mero inteiro a ser guardado no JSON
     *   
     *   @see	#writeJSON(JSONArray)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public void nrEscolhido(int nrEscolhido) {
        try {
            nrSorteado.put(nrEscolhido);
            writeJSON(nrSorteado);
        }
        catch (Exception e) {
            System.out.println("Erro, " + e);
        }
    }
    
    /**
     *  Escreve o array no ficheiro, a sua localiza��o est� configurada
     *  na classe ConfigJSON
     *   
     *   @param		jsArray		Array em JSON para escrever no ficheiro
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    private void writeJSON(JSONArray jsArray) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(configJSON.getPathMonitor());
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
    
    /**
     *  Verifica se o n�mero existe no JSON
     *   
     *   @param	 	textFieldNumber		N�mero a verificar se existe no ficheiro
     *   
     *   @return 	result				Booleano que traduz se existe o n�mero no ficheiro
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Boolean checkJSON(int textFieldNumber) {
    	Boolean result = false;
    	String data = null;
    	
        try {
            File myObj = new File(configJSON.getPathMonitor());
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
    
    /**
     *  L� o conteudo do ficheiro JSON e devolve o mesmo
     *   
     *   @return	data	Conte�do do ficheiro JSON
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static String readJSON() {
    	String data = null;
        try {
            File myObj = new File(configJSON.getPathMonitor());
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
    
    /**
     *  Limpa o conte�do do ficheiro JSON
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    private void resetJSON() {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(configJSON.getPathMonitor());
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
    
    /**
     *  Guarda um certo n�mero num JSONArray e guarda o Array num ficheiro
     *   
     *   @see	#writeJSON(JSONArray)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public void save() {
    	writeJSON(nrSorteado);
    }
    
    /**
     *  Limpa o conte�do do ficheiro JSON
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public void reset() {
    	resetJSON();
    }
}
