
package bladeofnight.entidades;

public class HUD extends Imagem implements Entidade {

    private long codigoHUD;
    private int prioridadeImgs;
    private int listaTextos;
    private int listaNumeros;

    public HUD(long codigoHUD, int prioridadeImgs, int listaTextos, int listaNumeros) {
        this.codigoHUD = codigoHUD;
        this.prioridadeImgs = prioridadeImgs;
        this.listaTextos = listaTextos;
        this.listaNumeros = listaNumeros;
    }

    public HUD(long codigoHUD) {
        this.codigoHUD = codigoHUD;
    }
    
    @Override
    public long getCodigo() {
        return codigoHUD;
    }

    public int getPrioridadeImgs() {
        return prioridadeImgs;
    }

    public int getListaTextos() {
        return listaTextos;
    }

    public int getListaNumeros() {
        return listaNumeros;
    }

    public void setCodigoHUD(long codigoHUD) {
        this.codigoHUD = codigoHUD;
    }

    public void setPrioridadeImgs(int prioridadeImgs) {
        this.prioridadeImgs = prioridadeImgs;
    }

    public void setListaTextos(int listaTextos) {
        this.listaTextos = listaTextos;
    }

    public void setListaNumeros(int listaNumeros) {
        this.listaNumeros = listaNumeros;
    }
    
}
