import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Advent2024Day04 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int row = fileData.size();
        int column = fileData.get(0).length();
        String[][] grid = new String[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                grid[i][j]=fileData.get(i).substring(j,j+1);
            }
        }
        System.out.println(part1(grid));
        System.out.println(part2(grid));
    }

    public static int part1(String[][] grid){
        int count = 0;
        String word = "XMAS";
        int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j].equals("X")){
                    for (int[] d : dir){
                        int x = d[0];
                        int y = d[1];
                        boolean found = true;
                        for (int k = 1; k < 4; k++){
                            int nr = i + k * x;
                            int nc = j + k * y;
                            if ((nr<0||nr>grid.length-1||nc<0||nc>grid[0].length-1)) {
                                found = false;
                                break;
                            }
                            if (!(grid[nr][nc].equals(word.substring(k, k + 1)))) {
                                found = false;
                                break;
                            }
                        }
                        if (found)count++;

                    }
                }
            }
        }
        return count;
    }

    public static int part2(String[][] grid){
        int count = 0;
        int[][] dir = { {-1, -1}, {1, 1}, {-1, 1}, {1, -1} };
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if (grid[i][j].equals("A")){
                    int nr = i+dir[0][0];
                    int nc = j+dir[0][1];
                    int nr2 = i+dir[1][0];
                    int nc2 = j+dir[1][1];
                    int nr3 = i+dir[2][0];
                    int nc3 = j+dir[2][1];
                    int nr4 = i+dir[3][0];
                    int nc4 = j+dir[3][1];
                    boolean c1 = true;
                    boolean c2 = true;

                    if ((nr<0||nr>grid.length-1||nc<0||nc>grid[0].length-1)) {
                        c1 = false;
                    }
                    if ((nr2<0||nr2>grid.length-1||nc2<0||nc2>grid[0].length-1)) {
                        c1 = false;
                    }
                    if (c1&&(((grid[nr][nc].equals("M"))&&(grid[nr2][nc2].equals("S")))||((grid[nr][nc].equals("S"))&&(grid[nr2][nc2].equals("M"))))) {
                        c1 = true;
                    } else {
                        c1 = false;
                    }
                    if ((nr3<0||nr3>grid.length-1||nc3<0||nc3>grid[0].length-1)) {
                        c2 = false;
                    }
                    if ((nr4<0||nr4>grid.length-1||nc4<0||nc4>grid[0].length-1)) {
                        c2 = false;
                    }

                    if (c2&&(((grid[nr3][nc3].equals("M"))&&(grid[nr4][nc4].equals("S")))||((grid[nr3][nc3].equals("S"))&&(grid[nr4][nc4].equals("M"))))) {
                        c2 = true;
                    } else {
                        c2 = false;
                    }

                    if (c1&&c2){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}