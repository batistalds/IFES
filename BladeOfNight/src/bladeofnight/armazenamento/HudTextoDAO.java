
package bladeofnight.armazenamento;

import bladeofnight.entidades.HUDTexto;
import java.util.ArrayList;

public interface HudTextoDAO {
    public HUDTexto buscar(HUDTexto hud);
    
    public boolean inserir(HUDTexto hud);
    public boolean alterar(HUDTexto hud);
    public boolean excluir(HUDTexto hud);
    
    public ArrayList<HUDTexto> getLista();
}
