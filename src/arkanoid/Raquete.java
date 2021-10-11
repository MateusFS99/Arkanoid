package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Raquete extends GameObject{
    
    protected Rectangle raquete;
    protected int maxx, maxy, velx, vely, direcao;
    
    public Raquete(double cx, double cy, double cw, double ch, Pane pane){
        
        super(cx,cy,cw,ch,pane);
        raquete = new Rectangle(cx,cy,cw,ch);
        raquete.setFill(Paint.valueOf("0x19AD0D"));
        maxx = (int)pane.getHeight();
        maxy = (int)pane.getHeight();
    }
    
    public Rectangle getRaquete(){
        
        return raquete;
    }
    
    public double getRaqPos(){
        
        return cx;
    }
   
    public void moverM(int direcao){
        
        this.raquete.setX(direcao);
        this.cx = (int)raquete.getX();
    }
    
    public void moverT2(){
        
        cx += direcao;
        this.raquete.setX(cx);
        if(cx >= maxx + 20)
            raquete.setX(this.cx = maxx + 20);
        else if(cx <= 0)
            raquete.setX(this.cx = 0);
    }
    
    public void moverT1(String direcao){
        
        switch(direcao) 
        {
            case "RIGHT":
                if(this.direcao < 0)
                    this.direcao = 2;
                else
                    this.direcao += 2;
            break;
            case "LEFT":
                if(this.direcao > 0)
                    this.direcao = -2;
                else 
                    this.direcao -= 2;
            break;
       }
    }
}
