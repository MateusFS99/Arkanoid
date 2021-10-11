package arkanoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Game extends Pane{
    
    private final int dificuldade = 5;     
    private int vidas;
    private Random rand;
    private List <BlocoAmarelo> blocoam;
    private List <BlocoAzul> blocoaz;
    private List <BlocoVerde> blocove;
    private boolean mouse;
    private Score contador;
    private Button jogar, controles, sair;
    private Label score, vida, arkanoidtxt, version;
    AnimationTimer timer;
    
    public void adicionarBlocos(){
        
        int k = 30, l = 65, bl = 0, y = 0, g = 0, b;
        for(int i = 0; i < 5; i++)
        {
            for(int j=0 ; j<7 ; j++)
            {
                b = rand.nextInt(3) + 1;
                switch(b)
                {
                    case 1:
                            blocoam.add(new BlocoAmarelo(k,l,100,30,this));
                            getChildren().add(blocoam.get(y++).getBlock());
                            break;
                    case 2:
                            blocove.add(new BlocoVerde(k,l,100,30,this));
                            getChildren().add(blocove.get(g++).getBlock());
                            break;
                    case 3:
                            blocoaz.add(new BlocoAzul(k,l,100,30,this));
                            getChildren().add(blocoaz.get(bl++).getBlock());
                            break;
                }
                k += 105;
            }
            k = 30;
            l += 35;
        }
    }
    
    public void mostraComandos(){
        
        Button Voltar;
        
        Voltar = new Button("Voltar");
        Voltar.setPrefSize(100,50);
        Voltar.setLayoutX(25);
        Voltar.setLayoutY(25);
        
        getChildren().removeAll(jogar,controles,sair);
        getChildren().clear();
        
        Label texto = new Label("- Movimentar raquete: MOUSE\n\n- ESC: Sair\n\n - P: Pause");
        texto.setFont(new Font("Arial",15));
        texto.setTextFill(Paint.valueOf("#884E00"));
        texto.setLayoutX(300);
        texto.setLayoutY(250);
        
        getChildren().addAll(Voltar,texto);
        Voltar.setOnAction(e->{getChildren().removeAll(Voltar,texto);Menu();});
    }
    
    public void Menu(){
        
        getChildren().clear();
        
        arkanoidtxt = new Label("ARKANOID");
        arkanoidtxt.setFont(new Font("Chiller",50));
        arkanoidtxt.setTextFill(Paint.valueOf("#884E00"));
        arkanoidtxt.setLayoutX(290);
        arkanoidtxt.setLayoutY(85);
        
        version = new Label("v3.5");
        version.setFont(new Font("Arial",15));
        version.setTextFill(Paint.valueOf("#884E00"));
        version.setLayoutX(480);
        version.setLayoutY(103);
        
        jogar = new Button("Jogar");
        controles = new Button("Controles");
        sair = new Button("Sair");
        
        jogar.setPrefSize(150,75);
        controles.setPrefSize(150,75);
        sair.setPrefSize(150,75);
        
        jogar.setLayoutX(320);
        jogar.setLayoutY(200);
        controles.setLayoutX(320);
        controles.setLayoutY(350);
        sair.setLayoutX(320);
        sair.setLayoutY(500);
        
        getChildren().addAll(arkanoidtxt,version,jogar,controles,sair);
        
        jogar.setOnAction(e->{getChildren().clear();iniciarControles();});
        controles.setOnAction(e->{mostraComandos();});
        sair.setOnAction(e->{Platform.exit();System.exit(0);});
    }
    
    public void finaliza(){
        
        if(vidas == 0)
        {
            System.out.println("Perdeu!!!");
            timer.stop();
            getChildren().clear();
            Menu();
        }
        if(blocoam.isEmpty() && blocoaz.isEmpty() && blocove.isEmpty())
        {
            System.out.println("Ganhou!!!");
            timer.stop();
            getChildren().clear();
            Menu();
        }
    }
    
    public void iniciarControles(){
        
        vidas = 5;
        rand = new Random();
        blocoam = new ArrayList();
        blocove = new ArrayList();
        blocoaz = new ArrayList();
        contador = new Score();
        contador.zerar();
        
        Bolinha bolinha = new Bolinha(400,600,10,this,dificuldade);
        Raquete raquete = new Raquete(350,660,100,15,this);
        
        adicionarBlocos();
        getChildren().add(contador.getRetangulo());
        getChildren().addAll(bolinha.getCircle());
        getChildren().addAll(raquete.getRaquete());
        
        score = new Label("Pontos: "+contador.getPontos());
        vida = new Label("Vidas: "+vidas);
        score.setFont(new Font("Arial",15));
        score.setTextFill(Paint.valueOf("#884E00"));
        score.setLayoutX(35);
        score.setLayoutY(25);
        vida.setFont(new Font("Arial",15));
        vida.setTextFill(Paint.valueOf("#884E00"));
        vida.setLayoutX(700);
        vida.setLayoutY(25);
        getChildren().addAll(score,vida);
        
        timer = new AnimationTimer(){
            
            @Override
            public void handle(long now) 
            {
                bolinha.movimentar();
                if(!mouse)
                    raquete.moverT2();
                if(bolinha.perdeu())
                {
                    contador.subtrair(15);
                    bolinha.reposicionar(350,600);
                    vidas--;
                }
                    
                if(bolinha.colidiu(raquete))
                {
                    bolinha.mudaBolinha(raquete.raquete.getX());
                }
                for(BlocoAmarelo bloco: blocoam)
                {
                    if(bolinha.colidiu(bloco))
                    {
                        contador.somar(bloco.getPontos());
                        getChildren().remove(bloco.getBlock());
                        blocoam.remove(bloco);
                        bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                        break;
                    }
                }
                for(BlocoAzul bloco: blocoaz)
                {
                    if(bolinha.colidiu(bloco))
                    {
                        if(!bloco.blocoAtivado())
                            bloco.acionaBloco();
                        else
                        {
                            contador.somar(bloco.getPontos(bolinha));
                            getChildren().remove(bloco.getBlock());
                            blocoaz.remove(bloco);
                            break;
                        }
                        bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                    }
                    if(bloco.blocoAtivado())
                    {
                        bloco.mover();
                    }
                    if(bloco.colidiu(raquete))
                    {
                        contador.somar(bloco.getPontos(raquete));
                        getChildren().remove(bloco.getBlock());
                        blocoaz.remove(bloco);
                        break;
                    }
                    else if(bloco.getBlock().getY() >= getHeight())
                    {
                        contador.subtrair(30);
                        getChildren().remove(bloco.getBlock());
                        blocoaz.remove(bloco);
                        vidas--;
                        break;
                    }
                        
                }
                for(BlocoVerde bloco: blocove)
                {
                    if(bolinha.colidiu(bloco))
                    {
                        if(!bloco.blocoAtivado())
                            bloco.acionaBloco();
                        else
                        {
                            contador.somar(bloco.getPontos());
                            getChildren().remove(bloco.getBlock());
                            blocove.remove(bloco);
                            break;
                        }
                        bolinha.mudaBolinha(bloco.getPosX(),bloco.getPosY());
                    }
                    if(bloco.blocoAtivado())
                    {
                        if(bloco.getPosY() < 240)
                            bloco.moverY();
                        else
                            bloco.moverX();
                    }
                }
                finaliza();
            }
        };
        timer.start();
        getScene().setOnMouseMoved(e->{raquete.moverM((int)e.getSceneX() - 50);});
    }
    
}
