package Java.ProjetoJogo;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private int[][] matriz;
    private int charX;
    private int charY;
    public Jogo(){
        matriz = new int[10][2];
        charX = 0;
        charY = 1;
    }
    public void init(){
        createChar();
        createObstacle(8, 1);
        startGame();
    }
    private void startGame(){
        new Thread() {
            @Override
            public void run() {
                do{       
                    showMatriz();            
                    move();
                    createObstacle(9,new Random().nextInt(2));
                    try{
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while(!isCrash());
            }
          }.start();
          new Thread() {
            @Override
            public void run() {
                Scanner tec = new Scanner(System.in);
                int chose;
                do{
                   chose = tec.nextInt();
                   switch (chose) {
                        case 1:
                            moveUp();
                            break;
                        case 2:
                            moveDown();
                            break;
                       default:
                            break;
                   }

                }while(!isCrash());
                tec.close();
            }
          }.start();
    }
    private void creator(){
    
    }
    private void move(){
        for(int lin = 0; lin < 2; lin++) {
            for(int col = 0; col< 9; col++ ){
                matriz[col][lin] = matriz[col+1][lin] + matriz[col][lin];
                matriz[col+1][lin] = 0;
            }
        }
        if(matriz[0][0] < 2 && matriz[0][1] < 2){
            matriz[0][0] = 0;
            matriz[0][1] = 0;
            createChar();
        }
    }
    public void showMatriz(){
        System.out.println("");
        for(int lin = 0; lin < 2; lin++) {
            for(int col = 0; col< 10; col++ ){
                System.out.print(matriz[col][lin]);
            }
            System.out.println("");
        }
    }
    private void moveUp(){
        matriz[0][0] = 1;
        matriz[0][1] = 0;
        charX = 0;
        charY = 0;
    }
    private void moveDown(){
        matriz[0][1] = 1;
        matriz[0][0] = 0;
        charX = 0;
        charY = 1;
    }   
    private void createChar(){
        matriz[charX][charY] = matriz[charX][charY]+1;
    }
    private void createObstacle(int x, int y){
        matriz[x][y] = 1;
    }
    private boolean isCrash(){ return(matriz[charX][charY] == 2? true:false);}

}
