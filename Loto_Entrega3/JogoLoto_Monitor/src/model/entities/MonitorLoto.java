package model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

import org.json.JSONArray;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
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
    private static double valorApostado;
    private int[] linhas = new int[3];
   
    
    
    
    public static double getValorApostado() {
		return valorApostado;
	}

	public static void setValorApostado(double valorApostado) {
		MonitorLoto.valorApostado = valorApostado;
	}
	

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
    	resetJSONInfo();
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
     *  Escreve o cartão e da aposta no ficheiro, a sua localização está configurada
     *  na classe ConfigJSON
     *   
     *   @param		string		Array em JSON para escrever no ficheiro
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static void writeInfoJSON(String string) {
        try { 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(ConfigJSON.getPathInfoUser());
            file.write(string);
 
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
     *  Lê o conteudo do ficheiro JSON e devolve o mesmo
     *   
     *   @return	data	Conteúdo do ficheiro JSON
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static String readJSONInfo() {
    	String data = null;
        try {
            File myObj = new File(ConfigJSON.getPathInfoUser());
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
    
    public static String formatJSONInfo() {
    	String data = readJSONInfo();
    	try {
    		int[][] cartaoFinal = new int[3][9];
	    	System.out.println(data.length());
	    	if (data.length()>80) {
	    	data = data.replace(", [[", "MARCA")
	    		.replace("]], ", "MARCA")
	    		.replace(";", "MARCA");

	    	String[] dataArray = data.split("MARCA");
	    	System.out.println(data +"MARCAçÃO /n");
	    	int userCounter = dataArray.length / 3; // 12 3
	    	int userIndex = 0;
	    	// Ramificação dos três tipos de data e envio da mesma para zonas diferentes
	    	for (int i = 0; i < userCounter; i++) {
		    	System.out.print("DATA1 - "+dataArray[0 + 3 * i] + " - DATA1\n");
		    	String cartao[] = dataArray[1 + 3 * i].split("\\], \\[");
		    	for (int x=0; x<3; x++) {
		    		String[] cartaoLinha = cartao[x].split(", ");
		    		for (int y=0; y<cartaoLinha.length; y++) {
		    			cartaoFinal[x][y] = Integer.valueOf(cartaoLinha[y]); 
		    		}
		    	}
		    	System.out.print("DATA3 - "+ dataArray[2 + 3 * i] + " - DATA2\n");
	    		  System.out.println(dataArray[i]);
	    		}
	    	} else {
	    		data ="Á espera dos jogadores";
	    	}
    	} catch (Exception e) {
    		data ="Á espera dos jogadores";
    	}
    	return data;
    }
    
    public static int[][] formatJSONCard() {
    	String data = readJSONInfo();
    	int[][] cartaoFinal = new int[3][9];
    	try {
	    	if (data.length()>80) {
	    	data = data.replace(", [[", "MARCA")
	    		.replace("]], ", "MARCA")
	    		.replace(";", "MARCA");

	    	String[] dataArray = data.split("MARCA");
	    	int userCounter = dataArray.length / 3; // 12 3
	    	int userIndex = 0;
	    	// Ramificação dos três tipos de data e envio da mesma para zonas diferentes
	    	for (int i = 0; i < userCounter; i++) {
		    	String cartao[] = dataArray[1 + 3 * i].split("\\], \\[");
		    	for (int x=0; x<3; x++) {
		    		String[] cartaoLinha = cartao[x].split(", ");
		    		for (int y=0; y<cartaoLinha.length; y++) {
		    			cartaoFinal[x][y] = Integer.valueOf(cartaoLinha[y]); 
		    		}
		    	}
	    	}
	    	} else {
	    		data ="Á espera dos jogadores";
	    	}
    	} catch (Exception e) {
    		data ="Á espera dos jogadores";
    	}
    	return cartaoFinal;
    }
    
    public static String formatJSONID() {
    	String data = readJSONInfo();
    	try {
    		int[][] cartaoFinal = new int[3][9];
	    	if (data.length()>80) {
	    	data = data.replace(", [[", "MARCA")
	    		.replace("]], ", "MARCA")
	    		.replace(";", "MARCA");

	    	String[] dataArray = data.split("MARCA");
	    	int userCounter = dataArray.length / 3; // 12 3
	    	int userIndex = 0;
	    	// Ramificação dos três tipos de data e envio da mesma para zonas diferentes
	    	for (int i = 0; i < userCounter; i++) {
	    		data = dataArray[2 + 3 * i];
	    		}
	    	} else {
	    		data ="Á espera dos jogadores";
	    	}
    	} catch (Exception e) {
    		data ="Á espera dos jogadores";
    	}
    	return data;
    }
    
    public static String formatJSONBet() {
    	String data = readJSONInfo();
    	try {
    		int[][] cartaoFinal = new int[3][9];
	    	if (data.length()>80) {
	    	data = data.replace(", [[", "MARCA")
	    		.replace("]], ", "MARCA")
	    		.replace(";", "MARCA");

	    	String[] dataArray = data.split("MARCA");
	    	int userCounter = dataArray.length / 3; // 12 3
	    	int userIndex = 0;
	    	// Ramificação dos três tipos de data e envio da mesma para zonas diferentes
	    	for (int i = 0; i < userCounter; i++) {
	    		data = dataArray[0 + 3 * i];
	    		}
	    	} else {
	    		data ="Á espera dos jogadores";
	    	}
    	} catch (Exception e) {
    		data ="Á espera dos jogadores";
    	}
    	return data;
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
    
    /**
     *  Limpa o conteudo do ficheiro JSON com informações dos jogadores
     *   
     *   @see	ConfigJSON#getPathMonitor()
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    private void resetJSONInfo() {
        try { 
            filePrint = new PrintWriter(ConfigJSON.getPathInfoUser());
            filePrint.write("");
            nrSorteado = new JSONArray();
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            filePrint.flush();
			filePrint.close();
        }
    }  
    
    /**
     * Checks whether the card is complete
     * <p>
     * This method goes through all the elements of the list and checks if there is no number besides the negatives,
     * which represent the user's choice and the elements with 99, which represent the blanks, if any, increment in the counter.
     * <p>
     * The card will only be completed when there are no missing numbers
     * @param cartao int[3x][9x] card where you want to check if it is complete
     * @return boolean translates the verification result to truth or lie
     * @since 1.0
     */
    public boolean isConclusaoCartao(int[][] cartao, int id, double aposta) {
        int nrEmFalta = 0;
        for (int lin = 0; lin<3; lin++) {
            int nrEmFaltaNaLinha = 0;
            for (int col = 0; col < 9; col++) {
                if (cartao[lin][col] >= 0 && cartao[lin][col] != 99) {
                    nrEmFalta ++;
                    nrEmFaltaNaLinha ++;
                }
            }
            if(nrEmFaltaNaLinha == 0) {                
                if(linhas[lin] == 0) {                   
                    System.out.println("\nA linha " + (lin + 1) + " foi concluída!\n");
                    Alerts.showAlert("Alguém concluio a linha", "Alguém concluio a linha", "O jogador com a identificação:\n" + id + "\n Ganhou: " + aposta * 2 +"€", AlertType.INFORMATION);
                    linhas[lin] = 1;
                }    
            }     
        }
        if(nrEmFalta == 0) {
        	Alerts.showAlert("Alguém acabou o jogo", "Alguém acabou o jogo", "O jogador com a identificação:\n" + id + "\n Ganhou: " + aposta * 10 +"€", AlertType.INFORMATION);
            return true;
        } return false;
        
    }
    
    
    
}
