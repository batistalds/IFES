package cadastroestoque.Entidades;

import java.util.ArrayList;
import java.util.Date;

public class Compra implements Entidade {

    private long codigo;
    private Date data;
    private double valorTotal;
    private Comprador comprador;
    private Fornecedor forncedor;
    private ArrayList<ItemCompra> itensCompra;

    public Compra(long codigo, Date data, Comprador comprador, Fornecedor forncedor) {
        this.codigo = codigo;
        this.data = data;
        this.comprador = comprador;
        this.forncedor = forncedor;
        this.valorTotal = 0;
        this.itensCompra = new ArrayList<>();
    }

    public Compra(long codigo) {
        this.codigo = codigo;
    }

    public void atualizarValorTotal() {
        double vv = 0;
        for (ItemCompra ic : this.itensCompra) {
            vv += ic.getPrecoCompra() * ic.getQuantidade();
        }        
        this.valorTotal = vv;
    }

    public void inserirItemCompra(ItemCompra item) {
        this.itensCompra.add(item);
        atualizarValorTotal();
    }

    public boolean removerItemCompra(ItemCompra item) {
        ItemCompra itemRemover = null;
        for (ItemCompra itemLista : this.itensCompra) {
            if (itemLista.getCodigo() == item.getCodigo()) {
                itemRemover = itemLista;
            }
        }

        if (itemRemover != null) {
            this.itensCompra.remove(itemRemover);
            atualizarValorTotal();
            return true;
        }

        return false;
    }

    @Override
    public long getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Fornecedor getForncedor() {
        return forncedor;
    }

    public ArrayList<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public void setForncedor(Fornecedor forncedor) {
        this.forncedor = forncedor;
    }
}
