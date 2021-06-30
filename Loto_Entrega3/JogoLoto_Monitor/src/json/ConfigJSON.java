package json;

/**
 *  Classe responsavel por configurar a localizacao do ficheiro JSON
 *  Deve coincidir com a do programa jogador para funcionar corretamente
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class ConfigJSON {
    String pathMonitor = System.getProperty("user.dir").replace("JogoLoto_Monitor", "JSON\\monitor.json");
    String pathPlayers = System.getProperty("user.dir").replace("JogoLoto_Monitor", "JSON\\nrjogadores.json");
    //String pathEncrypt = System.getProperty("user.dir").replace("JogoLoto_Monitor", "JSON\\encrypt.txt");
    //String pathDecrypt = System.getProperty("user.dir").replace("JogoLoto_Monitor", "JSON\\decrypt.txt");
    static String pathInfoUser = System.getProperty("user.dir").replace("JogoLoto_Monitor", "JSON\\infouser.json");

	/**
     *  Devolve a localizacao do ficheiro onde vai armazenar e ler os numeros sorteados
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public String getPathMonitor() {
		return pathMonitor;
	}
    /**
     *  Devolve a localizacao do ficheiro onde vai armazenar e ler o numero de jogadores
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public String getPathPlayers() {
		return pathPlayers;
	}
	
//	public String getPathEncrypt() {
//		return pathEncrypt;
//	}
//	public String getPathDecrypt() {
//		return pathDecrypt;
//	}
    
    /**
     *  Devolve a localização do ficheiro onde vou guardar os catões e apostas
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public static String getPathInfoUser() {
		return pathInfoUser;
	}
}
