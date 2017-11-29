
package bladeofnight.armazenamento;

import bladeofnight.entidades.HUD;
import java.util.ArrayList;

public interface HudDAO {
    public HUD buscar(HUD hud);
    
    public boolean inserir(HUD hud);
    public boolean alterar(HUD hud);
    public boolean excluir(HUD hud);
    
    public ArrayList<HUD> getLista();
}
