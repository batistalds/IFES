
package bladeofnight.armazenamento;

import bladeofnight.entidades.Jogador;
import java.util.ArrayList;

public interface JogadorDAO {
    public Jogador buscar(Jogador jogador);
    public long buscarCodigoComNave(long codNave);
    
    public boolean inserir(Jogador jogador);
    public boolean alterar(Jogador jogador);
    public boolean excluir(Jogador jogador);
    
    public ArrayList<Jogador> getLista();
}
