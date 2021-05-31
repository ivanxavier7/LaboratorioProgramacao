package json;

/**
 *  Classe responsável por configurar a localização do ficheiro JSON
 *  Deve coincidir com a do programa jogador para funcionar corretamente
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class ConfigJSON {
    String pathMonitor = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\monitor.json");
    String pathPlayers = System.getProperty("user.dir").replace("JogoLoto_Jogador", "JSON\\nrjogadores.json");

    /**
     *  Devolve a localização do ficheiro onde vai armazenar e ler os números sorteados
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public String getPathMonitor() {
		return pathMonitor;
	}
    /**
     *  Devolve a localização do ficheiro onde vai armazenar e ler o número de jogadores
     *  
     *   @author      Ivan Xavier
     *   @version     1.0
     *   @since       1.0
     */
	public String getPathPlayers() {
		return pathPlayers;
	}
    
}