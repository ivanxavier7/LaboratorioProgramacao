package json;

/**
 *  Classe respons�vel por configurar a localiza��o do ficheiro JSON
 *  Deve coincidir com a do programa jogador para funcionar corretamente
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class ConfigJSON {
    String pathMonitor = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\monitor.json");
    String pathPlayers = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\nrjogadores.json");
    //String pathEncrypt = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\encrypt.txt");
    //String pathDecrypt = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\decrypt.txt");
    static String pathInfoUser = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\infouser.json");

    /**
     *  Devolve a localiza��o do ficheiro onde vai armazenar e ler os n�meros sorteados
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public String getPathMonitor() {
		return pathMonitor;
	}
    /**
     *  Devolve a localiza��o do ficheiro onde vai armazenar e ler o n�mero de jogadores
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public String getPathPlayers() {
		return pathPlayers;
	}
    /**
     *  Devolve a localiza��o do ficheiro onde tencionava codificar a informacao a enviar por socket
     *  N�o foi implementada devido a problemas a establecer a comunica��o iniciao com o localhost em Windows,
     *  mesmo ap�s a cria��o de regras para autorizar as portas utilizadas, em IP alternativos fica � espera de liga��o
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	//public String getPathEncrypt() {
	//	return pathEncrypt;
	//}
    /**
     *  Devolve a localiza��o do ficheiro onde tencionava descodificar a informacao a enviar por socket
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	//public String getPathDecrypt() {
	//	return pathDecrypt;
	//}
    /**
     *  Devolve a localiza��o do ficheiro onde vou guardar os cat�es e apostas
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public static String getPathInfoUser() {
		return pathInfoUser;
	}
    
	
}