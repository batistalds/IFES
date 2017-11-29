
package bladeofnight.entidades;

public class Item extends Imagem implements Entidade {
    
    private long codigoItem;
    //private String nome; Já existe em Imagem
    private String efeito;
    private int velY;
    
    public Item(long codigoItem, String nome, String efeito, int velY) {
        this.codigoItem = codigoItem;
        super.nome = nome;
        this.efeito = efeito;
        this.velY = velY;
    }

    public Item(long codigoItem) {
        this.codigoItem = codigoItem;
    }

    @Override
    public long getCodigo() {
        return codigoItem;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getVelY() {
        return velY;
    }

    public void setCodigo(long codigoItem) {
        this.codigoItem = codigoItem;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
}
