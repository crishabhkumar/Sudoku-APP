package com.rishabh;

public class SudokuSolver {
    //To check that no move have been made at this block.
    public static boolean isBlockValid(int x, int y, int board[][]){
        return board[x][y] == 0;
    }

    //To check if the game is completed or not.
    public static boolean isComplete(int board[][]){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0) return false;
            }
        }
        return true;
    }

    //To check if current move is valid or not.
    public static boolean isMoveValid(int x, int y, int move, int board[][]){

        //To check if the number entered is valid or not.
        if(x < 0 || y < 0 || x > 9 || y > 9) return false;

        //To check the column constraint.
        for(int i = 0; i < 9; i++){
            if(board[i][y] == move) return false;
        }

        //To check the row constraint.
        for(int i = 0; i < 9; i++){
            if(board[x][i] == move) return false;
        }

        //To check the sub-box.
        for(int i = (x/3)*3; i < (x/3)*3 + 3; i++){
            for(int j = (y/3)*3; j < (y/3)*3 + 3; j++){
                if(board[i][j] == move) return false;
            }
        }

        //After all checks, return true.
        return true;
    }

    //To get the next empty point in the board, and if not return null.
    public static Point getNext(int x, int y, int board[][]){

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0) return new Point(i, j);
            }
        }
        return null;
    }

    public static boolean solve(int x, int y, int move, int board[][]){

        //Get the next empty point.
        Point next = getNext(x, y, board);

        if(next != null){
            //Starting the moves.
            for(int i = 1; i < 10; i++){
                if(isMoveValid(next.x, next.y, i, board)){
                    board[next.x][next.y] = i;
                    if(solve(next.x, next.y, i, board)){
                        return true;
                    }
                    board[next.x][next.y] = 0;
                }
            }
        } else return true;
        return false;
    }

    public static int[][] solve(int[][] board){
        int garbageBoard[][] = new int[9][9];
        garbageBoard[0][0] = -1;
        int i = 0 , j = 0;
        for(int k = 1; k < 10; k++){
            if(solve(i, j, k, board))
                return board;
        }
        return garbageBoard;
    }
}
 
