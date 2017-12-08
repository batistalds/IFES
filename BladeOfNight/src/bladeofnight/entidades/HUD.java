
package bladeofnight.entidades;

public class HUD extends Imagem implements Entidade {

    private long codigoHUD;
    private long prioridadeImgs;
    private long listaTextos;
    private long listaNumeros;

    public HUD(long codigoHUD, long prioridadeImgs, long listaTextos, long listaNumeros) {
        this.codigoHUD = codigoHUD;
        this.prioridadeImgs = prioridadeImgs;
        this.listaTextos = listaTextos;
        this.listaNumeros = listaNumeros;
    }

    public HUD(long codigoHUD) {
        this.codigoHUD = codigoHUD;
    }

    public HUD(long prioridadeImgs, long listaTextos, long listaNumeros) {
        this.prioridadeImgs = prioridadeImgs;
        this.listaTextos = listaTextos;
        this.listaNumeros = listaNumeros;
    }
    
    @Override
    public long getCodigo() {
        return codigoHUD;
    }

    public long getPrioridadeImgs() {
        return prioridadeImgs;
    }

    public long getListaTextos() {
        return listaTextos;
    }

    public long getListaNumeros() {
        return listaNumeros;
    }

    public void setCodigoHUD(long codigoHUD) {
        this.codigoHUD = codigoHUD;
    }

    public void setPrioridadeImgs(long prioridadeImgs) {
        this.prioridadeImgs = prioridadeImgs;
    }

    public void setListaTextos(long listaTextos) {
        this.listaTextos = listaTextos;
    }

    public void setListaNumeros(long listaNumeros) {
        this.listaNumeros = listaNumeros;
    }
    
}
