
package bladeofnight.armazenamento;

import bladeofnight.entidades.Inimigo;
import java.util.ArrayList;

public interface InimigoDAO {
    public Inimigo buscar(Inimigo inimigo);
    public long buscarCodigo(Inimigo inimigo);
    public long buscarCodigoComNave(long codNave);
    
    public boolean inserir(Inimigo inimigo);
    public boolean alterar(Inimigo inimigo);
    public boolean excluir(Inimigo inimigo);
    
    public ArrayList<Inimigo> getLista();
}
