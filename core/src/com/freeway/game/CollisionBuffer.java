package com.freeway.game;

import java.util.concurrent.Semaphore;

public class CollisionBuffer {
    private Semaphore semFull;
    private Semaphore semEmpty;
    private int[][] colisionMatrix;
    private int width;
    public int refX, refXEnd;

    public CollisionBuffer(int width, int height, int refX){
        this.semFull = new Semaphore(0);
        this.semEmpty = new Semaphore(10);

        this.colisionMatrix = new int[width][height];
        this.width = width;
        this.refX = refX;
        this.refXEnd = refX + width;
    }
    public boolean checkCollision(int y, int endY){
        for (int i = 0; i < this.width; i++){
            for (int j = y; j < endY; j++){
                if (colisionMatrix[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void inserir(int y, int endY, int cb){
        try {
            boolean isOccupied = false;
            for (int i = 0; i < this.width; i++){
                for (int j = y; j < endY; j++){
                    if (colisionMatrix[i][j] == 1) {
                        isOccupied = true;
                        break;
                    }
                }
            }

            if (!isOccupied){
                semEmpty.acquire();
                for (int i = 0; i < this.width; i++){
                    for (int j = y; j < endY; j++){
                        colisionMatrix[i][j] = 1;
                    }
                }
//                System.out.println("Pos: "+ cb+"--" + y +" - "+ endY +" inserida");
                semFull.release();
            }



        }catch (InterruptedException exception){
            System.out.println("Collision Matrix: Erro ao inserir" + exception);
        }
    }

    public void retirar(int y, int endY, int cb){
        try {
            boolean isEmpty = false;
            for (int i = 0; i < this.width; i++){
                for (int j = y; j < endY; j++){
                    if (colisionMatrix[i][j] == 0) {
                        isEmpty = true;
                        break;
                    }
                }
            }

            if(!isEmpty){
                semFull.acquire();
                for (int i = 0; i < this.width; i++) {
                    for (int j = y; j < endY; j++) {
                        colisionMatrix[i][j] = 0;
                    }
                }
//                System.out.println("Pos: " + cb + "--" + y + " retirada");
                semEmpty.release();
            }
        }catch (InterruptedException exception){
            System.out.println("Collision Matrix: Erro ao retirar" + exception);
        }

    }


}
