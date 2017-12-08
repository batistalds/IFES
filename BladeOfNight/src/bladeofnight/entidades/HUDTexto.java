
package bladeofnight.entidades;

public class HUDTexto {
    private long codigoHUDTextos;
    private String texto;

    public HUDTexto(long codigoHUDTextos, String texto) {
        this.codigoHUDTextos = codigoHUDTextos;
        this.texto = texto;
    }

    public HUDTexto(long codigoHUDTextos) {
        this.codigoHUDTextos = codigoHUDTextos;
    }

    public HUDTexto(String texto) {
        this.texto = texto;
    }

    public long getCodigo() {
        return codigoHUDTextos;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setCodigo(long codigoHUDTextos) {
        this.codigoHUDTextos = codigoHUDTextos;
    }
}
