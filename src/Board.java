import java.util.Arrays;

public class Board {
    int[][] board;

    public Board(String boardStr){
        boolean foundEmpty=false;
        String[] rows=boardStr.split("\\|");
        int colNum=rows[0].split(" ").length;
        int rowNum=rows.length;
        for(int i=0; i<rowNum; i++){
            String[] components = rows[i].split(" ");
            for(int j=0; j<colNum; j++){
                if(!foundEmpty && components[j]=="_"){
                    foundEmpty=true;
                    board[j][i]=0;
                }
                else{
                    board[j][i]=Integer.parseInt(components[j]);
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
