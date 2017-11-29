
package bladeofnight.armazenamento;

import bladeofnight.entidades.HUDPrioridade;
import java.util.ArrayList;

public interface HudPrioridadeDAO {
    public HUDPrioridade buscar(HUDPrioridade hud);
    
    public boolean inserir(HUDPrioridade hud);
    public boolean alterar(HUDPrioridade hud);
    public boolean excluir(HUDPrioridade hud);
    
    public ArrayList<HUDPrioridade> getLista();
}
