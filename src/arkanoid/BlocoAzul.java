package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class BlocoAzul extends AbstractBloco{
    
    protected boolean acionaB;
    
    public BlocoAzul(double cx, double cy, double cw, double ch, Pane pane){
        
        super(cx,cy,cw,ch,pane);
        acionaB = false;
        bloco.setFill(Paint.valueOf("0x1145E5"));
    }
    
    public void acionaBloco(){
        
        acionaB = true;
    }
    
    public void mover()
    {
        cy++;
        bloco.setY(cy);
    }
    
    public int getPontos(Raquete raquete)
    {
        return 40;
    }
    
    public int getPontos(Bolinha bolinha)
    {
        return 60;
    }
    
    public boolean blocoAtivado()
    {
        return acionaB;
    }
    
    public boolean colidiu(Raquete raquete)
    {
        return this.bloco.getBoundsInParent().intersects(raquete.raquete.getBoundsInParent());
    }
}
