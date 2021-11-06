package Java.ProjetoJogo;

import java.util.Random;

public class Jogo {
    private int[][] matrix;
    //private double speed;
    public Jogo(){
        matrix = new int[10][2];
    }
    public void init(){
        createChar();
        createObstacle(8, 1);
        do{
            showMatrix();
            move();
            createObstacle(9,new Random().nextInt(2));
            
        }while(!isCrash());
        showMatrix();
    }
    private void createChar(){
        matrix[0][1] = 1;
    }
    private void createObstacle(int x, int y){
        matrix[x][y] = 1;
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
    private boolean isCrash(){ return(matrix[0][1] == 2? true:false);}
}
