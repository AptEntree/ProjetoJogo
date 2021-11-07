package Java.ProjetoJogo;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private int[][] matrix;
    public Jogo(){
        matrix = new int[10][2];
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
                    showMatrix();
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
    
    
    private void move(){
        for(int lin = 0; lin < 2; lin++) {
            for(int col = 0; col< 9; col++ ){
               matrix[col][lin] = matrix[col][lin] + matrix[col+1][lin];
               matrix[col+1][lin] = 0;
            }
        }
    }
    public void showMatrix(){
        System.out.println("");
        for(int lin = 0; lin < 2; lin++) {
            for(int col = 0; col< 10; col++ ){
                System.out.print(matrix[col][lin]);
            }
            System.out.println("");
        }
    }
    private void moveUp(){
        matrix[0][0] = 1;
        matrix[0][1] = 0;
    }
    private void moveDown(){
        matrix[0][1] = 1;
        matrix[0][0] = 0;
    }   
    private void createChar(){
        matrix[0][1] = 1;
    }
    private void createObstacle(int x, int y){
        matrix[x][y] = 1;
    }
    private boolean isCrash(){ return(matrix[0][1] == 2? true:false);}

}
