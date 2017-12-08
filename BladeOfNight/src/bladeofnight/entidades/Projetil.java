
package bladeofnight.entidades;

public class Projetil extends Imagem implements Entidade {
    
    private long codigoProjetil;
    private int velX;
    private int velY;
    private int angulo;
    private double delay;
    private int poder;

    public Projetil(long codigoProjetil, int velX, int velY, int angulo, double delay, int poder) {
        this.codigoProjetil = codigoProjetil;
        this.velX = velX;
        this.velY = velY;
        this.angulo = angulo;
        this.delay = delay;
        this.poder = poder;
    }
    
    public Projetil(int velX, int velY, int angulo, double delay, int poder) {
        this.velX = velX;
        this.velY = velY;
        this.angulo = angulo;
        this.delay = delay;
        this.poder = poder;
    }

    public Projetil(long codigoProjetil) {
        this.codigoProjetil = codigoProjetil;
    }

    @Override
    public long getCodigo() {
        return codigoProjetil;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public int getAngulo() {
        return angulo;
    }

    public double getDelay() {
        return delay;
    }

    public int getPoder() {
        return poder;
    }

    public void setCodigo(long codigoProjetil) {
        this.codigoProjetil = codigoProjetil;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

}
