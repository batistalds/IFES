
package bladeofnight.entidades;

import java.util.Date;

public class RankingDezDatas {
    
    private long codigoDezDatas;
    private Date dataDoRecorde;

    public RankingDezDatas(long codigoDezDatas, Date dataDoRecorde) {
        this.codigoDezDatas = codigoDezDatas;
        this.dataDoRecorde = dataDoRecorde;
    }

    public RankingDezDatas(long codigoDezDatas) {
        this.codigoDezDatas = codigoDezDatas;
    }

    public RankingDezDatas(Date dataDoRecorde) {
        this.dataDoRecorde = dataDoRecorde;
    }

    public long getCodigo() {
        return codigoDezDatas;
    }

    public Date getDataDoRecorde() {
        return dataDoRecorde;
    }

    public void setCodigo(long codigoDezDatas) {
        this.codigoDezDatas = codigoDezDatas;
    }

    public void setDataDoRecorde(Date dataDoRecorde) {
        this.dataDoRecorde = dataDoRecorde;
    }
    
}
