package arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class BlocoAmarelo extends AbstractBloco{
    
    BlocoAmarelo(double cx, double cy, double cw, double ch, Pane pane){
        
        super(cx,cy,cw,ch,pane);
        pontos = 10;
        bloco.setFill(Paint.valueOf("0xFCFF35"));
    }
}
