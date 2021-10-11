package arkanoid;

import javafx.scene.layout.Pane;

public class GameObject{
    
    protected double cx, cy, ch, cw, maxx, maxy;
    protected String cor;

    public GameObject(double cx,double cy,double ch, double cw,Pane pane){
        setCh(ch);
        setCw(cw);
        setCx(cx);
        setCy(cy);
        maxx=(int)pane.getWidth();
        maxy=(int)pane.getHeight();
    }
    
    public GameObject(double cx,double cy,Pane pane){
        setCx(cx);
        setCy(cy);
        maxx=(int)pane.getWidth();
        maxy=(int)pane.getHeight();
    }

    public double getCx(){
        return cx;
    }

    public double getCy(){
        return cy;
    }

    public double getCh(){
        return ch;
    }

    public double getCw(){
        return cw;
    }

    public void setCx(double cx){
        this.cx = cx;
    }

    public void setCy(double cy){
        this.cy = cy;
    }

    public void setCh(double ch){
        this.ch = ch;
    }

    public void setCw(double cw){
        this.cw = cw;
    }
    
}
