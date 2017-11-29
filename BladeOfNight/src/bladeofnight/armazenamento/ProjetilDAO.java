
package bladeofnight.armazenamento;

import bladeofnight.entidades.Projetil;
import java.util.ArrayList;

public interface ProjetilDAO {
    public Projetil buscar(Projetil projetil);
    
    public boolean inserir(Projetil projetil);
    public boolean alterar(Projetil projetil);
    public boolean excluir(Projetil projetil);
    
    public ArrayList<Projetil> getLista();
}
