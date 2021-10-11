package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class AbstractBloco extends GameObject{
    
    protected Rectangle bloco = new Rectangle();
    protected int pontos;
    
    public AbstractBloco(double cx, double cy, double cw, double ch, Pane pane){
        
        super(cx,cy,cw,ch,pane);
        bloco = new Rectangle(cx,cy,cw,ch);
    }
    
    public Rectangle getBlock(){
        
        return bloco;
    }
    
    public double getPosX(){
        
        return bloco.getX();
    }
    
    public double getPosY(){
        
        return bloco.getY();
    }
    
    public int getPontos(){
        
        return pontos;
    }
    
}
