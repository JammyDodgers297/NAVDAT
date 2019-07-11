import sheffield.*;

public class BitMap {
    public static void main(String[] args) {
        int gridSize = 28;
        char[][] map = new char [gridSize][gridSize];
        EasyWriter writer = new EasyWriter("map.txt");

        for (int x = 0; x < gridSize; x++) {
            String line = "";
            //System.out.println();
            for (int y = 0; y < gridSize; y++) {
                map[x][y] = '/';
                line += map[x][y];
            }
            writer.println(line);
            System.out.println(line);
        }
    }

    //public static int 
}