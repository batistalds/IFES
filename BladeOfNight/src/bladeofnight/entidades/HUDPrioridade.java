
package bladeofnight.entidades;

public class HUDPrioridade {
    
    private long codigoHUDPrioridades;
    private int valorPrioridade;

    public HUDPrioridade(long codigoHUDPrioridades, int valorPrioridade) {
        this.codigoHUDPrioridades = codigoHUDPrioridades;
        this.valorPrioridade = valorPrioridade;
    }
    
    public HUDPrioridade(long codigoHUDPrioridades) {
        this.codigoHUDPrioridades = codigoHUDPrioridades;
    }
    
    public HUDPrioridade(int valorPrioridade) {
        this.valorPrioridade = valorPrioridade;
    }

    public long getCodigo() {
        return codigoHUDPrioridades;
    }

    public int getValorPrioridade() {
        return valorPrioridade;
    }

    public void setCodigo(long codigoHUDPrioridades) {
        this.codigoHUDPrioridades = codigoHUDPrioridades;
    }

    public void setValorPrioridade(int valorPrioridade) {
        this.valorPrioridade = valorPrioridade;
    }
    
}
