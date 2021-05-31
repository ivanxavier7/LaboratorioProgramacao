package model.entities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;

import json.ConfigJSON;

/**
 *	Classe responsavel pela sincronizacao entre a interface e os dados
 *	A intencao e criar uma zona para os programas partilharem memoria entre si.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class MonitorLoto {
    private static JSONArray nrSorteado = new JSONArray();
    private static ConfigJSON configJSON = new ConfigJSON();
    private PrintWriter filePrint;
    private static FileWriter file;
   
    
    /**
     *  Guarda um certo numero num JSONArray e guarda o Array num ficheiro
     *   
     *   @param		nrEscolhido		Numero inteiro a ser guardado no JSON
     *   
     *   @see	#writeJSON(JSONArray)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static void nrEscolhido(int nrEscolhido) {
        try {
            nrSorteado.put(nrEscolhido);
            writeJSON(nrSorteado);
        }
        catch (Exception e) {
            System.out.println("Erro, " + e);
        }
    }
    
    /**
     *  Escreve o numero de jogadores num ficheiro, a sua localizacao
     *  esta configurada na classe ConfigJSON
     *   
     *   @param		nrJogadores		Numero de jogadores a escrever no JSON
     *   
     *   @see	ConfigJSON#getPathPlayers()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public void writeJogadoresJSON(int nrJogadores) {
        try {     	
            file = new FileWriter(configJSON.getPathPlayers());
            file.write(String.valueOf(nrJogadores));
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     *  Guarda um certo numero num JSONArray e guarda o Array num ficheiro
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
     *  Limpa o conteudo do ficheiro JSON
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public void reset() {
    	resetJSON();
    }
    
    /**
     *  Escreve o array no ficheiro, a sua localizacao esta configurada
     *  na classe ConfigJSON
     *   
     *   @param		jsArray		Array em JSON para escrever no ficheiro
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    private static void writeJSON(JSONArray jsArray) {
        try { 
            file = new FileWriter(configJSON.getPathMonitor());
            file.write(jsArray.toString());
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     *  Limpa o conteudo do ficheiro JSON
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    private void resetJSON() {
        try { 
            filePrint = new PrintWriter(configJSON.getPathMonitor());
            filePrint.write("");
            nrSorteado = new JSONArray();
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            filePrint.flush();
			filePrint.close();
        }
    }    
}
