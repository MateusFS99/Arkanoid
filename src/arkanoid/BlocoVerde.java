package arkanoid;

import javafx.scene.paint.Paint;

public class BlocoVerde extends AbstractBloco{
    
    protected int velx;
    protected boolean acionaB;
    
    public BlocoVerde(double cx, double cy, double cw, double ch, Game pane)
    {
        super(cx,cy,cw,ch,pane);
        pontos = 45;
        acionaB = false;
        velx = 1;
        bloco.setFill(Paint.valueOf("0x49E80D"));
    }
    
    public double getMax(){
        
        return maxx;
    }
    
    public void acionaBloco(){
        
        acionaB = true;
    }
    
    public void moverY(){
        
        cy++;
        bloco.setY(cy);
    }
    
    public void moverX(){
      
        cx += velx;
        if(cx >= getMax() - 100 || cx < 0)
        {
            velx = velx * -1;
        }
        bloco.setX(cx);
    }
    
    public boolean blocoAtivado(){
        
        return acionaB;
    }
}
