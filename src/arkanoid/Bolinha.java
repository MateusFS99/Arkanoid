package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Bolinha extends GameObject{
    
    protected Circle bolinha;
    protected int velx, vely, dificuldade;

    public Bolinha(double cx, double cy, int raio, Pane pane, int dificuldade){
       
        super(cx,cy,pane);
        this.dificuldade = dificuldade;
        bolinha = new Circle(cx,cy,raio);
        bolinha.setFill(Paint.valueOf("0xFF2D00"));
        velx = vely = dificuldade *= -1;
    }

    public double getMaxx(){
        return maxx;
    }

    public double getMaxy(){
        return maxy;
    }
    
    public Circle getCircle(){
        
        return bolinha;
    }
    
    public boolean colidiu(Bolinha outro){
        
        return bolinha.getBoundsInParent().intersects(outro.getCircle().getBoundsInParent());
    }
    
    public void reposicionar(double cx,double cy){
        
        this.cx = (int) cx;
        this.cy = (int) cy;
        this.vely *= -1;
        bolinha.setCenterX(cx);
        bolinha.setCenterY(cy);
    }
    
    public void movimentar(){
        
        cx += velx;
        cy += vely;
        if(cx >= getMaxx() - 8 || cx < 10)
           velx = velx * -1;
        if(cy <= 80)
           vely = vely * -1;

        bolinha.setCenterX(cx);
        bolinha.setCenterY(cy);
    }
    
    boolean colidiu(Raquete outro){
        
       return bolinha.getBoundsInParent().intersects(outro.raquete.getBoundsInParent());
    }
    
    boolean colidiu(AbstractBloco bloco){
        
        return this.bolinha.getBoundsInParent().intersects(bloco.getBlock().getBoundsInParent());
    }
    
    public void mudaBolinha(double cx){
        
        if(vely > 0)
            this.vely *= -1;
    }
    
    public void mudaBolinha(double cx,double cy){
        
        if(this.cx >= cx && this.cx <= cx + 100)
            vely *= -1;
        else if(this.cy >= cy - 10 && this.cy <= cy + 40)
            velx*=-1;
    }   

    public boolean perdeu(){
        
        return bolinha.getCenterY() >= getMaxy() + 30;
    }
}
