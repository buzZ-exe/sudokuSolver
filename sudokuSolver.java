public class sudokuSolver{

    public static final int GRID = 9;
    public static void main(String[] args){

        int[][] board = {
            {1, 6, 0, 0, 0, 0, 0, 7, 3},
            {0, 0, 0, 0, 0, 5, 0, 0, 6},
            {4, 0, 8, 0, 7, 0, 0, 9, 0},
            {0, 8, 0, 0, 0, 0, 0, 4, 0},
            {0, 0, 0, 8, 6, 0, 0, 0, 9},
            {3, 0, 6, 5, 0, 9, 0, 0, 0},
            {6, 9, 0, 7, 5, 4, 8, 0, 2},        //Get any board from sudoku.com
            {0, 0, 0, 0, 0, 3, 1, 0, 4},        //Blank spaces are replaced with 0s cuz int
            {2, 3, 4, 6, 1, 0, 9, 0, 7} 
        };

        printBoard(board);                      //Before solving
        
        boardSolver(board);                     //Black magic

        if (boardSolver(board)){
            System.out.println("\nSolved Successfully!\n");
            printBoard(board);                  //After solving  
        }
        else{
            System.out.println("\nBoard cannot be solved!\n");
        }
    }

    private static void printBoard(int[][] board){
        for (int i = 0; i < GRID; i++){
            if (i % 3 == 0 && i != 0){
                System.out.println("-----------");      //Line after every 3 rows
            }
            for (int j = 0; j < GRID; j++){
                if (j % 3 == 0 && j != 0){
                    System.out.print("|");              //Line after every 3 columns
                }

                System.out.print(board[i][j]);
                }
            System.out.println();
        }
    }

    private static boolean numberInRow(int[][] board, int number, int row){
        for (int j = 0; j < GRID; j++){
            if (board[row][j] == number){
                return true;
            }
        }
        return false;
    }   

    private static boolean numberInColumn(int[][] board, int number, int column){
        for (int i = 0; i < GRID; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;
    }   

    private static boolean numberInBox(int[][] board, int number, int row, int column){
        
        int smallBoxRow = row - row % 3;        
        int smallBoxColumn = column - column % 3;

        for (int i = smallBoxRow; i < smallBoxRow + 3; i++){
            for (int j = smallBoxColumn; j < smallBoxColumn + 3; j++){
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPossiblePlace(int[][] board, int number, int row, int column){
        return !numberInColumn(board, number, column) && !numberInRow(board, number, row) && !numberInBox(board, number, row, column);
    }

    //FML this is gonna get messy
    private static boolean boardSolver(int[][] board){

        for (int i = 0; i < GRID; i++) {            //Row count
            for (int j = 0; j < GRID; j++) {        //Column count
              if (board[i][j] == 0) {
                for (int tempNumber = 1; tempNumber <= GRID; tempNumber++) {

                  if (isPossiblePlace(board, tempNumber, i, j)) {
                    board[i][j] = tempNumber;
                    
                    if (boardSolver(board)) {        //If the following recursions also return true only then is this statement true
                      return true;
                    }
                    else {
                      board[i][j] = 0;              //Otherwise we backtrack and set it to 0 and try again
                    }
                  }
                }
                return false;                       //Unsolvable board
              }
            }
          }
          return true;                              //Mission passed
    }

}
