
package bladeofnight.entidades;

public class HUDNumero {
    
    private long codigoHUDNumeros;
    private int numero;

    public HUDNumero(long codigoHUDNumeros, int numeros) {
        this.codigoHUDNumeros = codigoHUDNumeros;
        this.numero = numeros;
    }

    public HUDNumero(long codigoHUDNumeros) {
        this.codigoHUDNumeros = codigoHUDNumeros;
    }

    public HUDNumero(int numeros) {
        this.numero = numeros;
    }

    public long getCodigo() {
        return codigoHUDNumeros;
    }

    public int getNumero() {
        return numero;
    }

    public void setCodigo(long codigoHUDNumeros) {
        this.codigoHUDNumeros = codigoHUDNumeros;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
