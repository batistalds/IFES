
package bladeofnight.armazenamento;

import bladeofnight.entidades.HUDNumero;
import java.util.ArrayList;

public interface HudNumeroDAO {
    public HUDNumero buscar(HUDNumero hud);
    
    public boolean inserir(HUDNumero hud);
    public boolean alterar(HUDNumero hud);
    public boolean excluir(HUDNumero hud);
    
    public ArrayList<HUDNumero> getLista();
}
