import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Advent2024Day06 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int row = fileData.size();
        int column = fileData.get(0).length();
        String[][] grid = new String[row][column];
        int[] index = new int[2];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                grid[i][j]=fileData.get(i).substring(j,j+1);
                if(grid[i][j].equals("^")){
                    index[0]=i;
                    index[1]=j;
                }
            }
        }
        solve(grid, index);
    }

    private static void solve(String[][] grid, int[] index){
        int count = 0;
        int dir = 0;
        int[] gg = index;
        boolean looping = false;
        ArrayList<String> indexes = new ArrayList<String>();
        ArrayList<String> indexes2 = new ArrayList<String>();

        int[][] direct = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        boolean out = false;
        while (!out){
            int[] newIndex = new int[2];
            int x = index[0]; int y = index[1];
            int nx = x+direct[dir][0]; int ny = y+direct[dir][1];
            newIndex[0]=nx; newIndex[1]=ny;
            if ((nx<0||nx>grid.length-1||ny<0||ny>grid[0].length-1)) {
                grid[x][y]="X";
                break;
            }
            if ((grid[nx][ny].equals("."))||(grid[nx][ny].equals("X"))){
                grid[x][y]="X";
                indexes2.add(x+" "+y+" "+dir);
                indexes.add(nx+" "+ny);
                index = newIndex;
            } else {
                dir++;
                if(dir==4)dir=0;
            }
        }
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals("X")) {
                    count++;
                    grid[i][j]=".";
                }
                if (i==gg[0]&&j==gg[1]){
                    grid[i][j]="^";
                }

            }
        }
        System.out.println(count);
        System.out.println(indexes2);
        System.out.println(indexes);

        System.out.println(solve2(grid,indexes,indexes2));

    }

    private static int solvep2(String[][] grid, int[] index, int z){
        HashSet<String> check = new HashSet<>();
        int count = 0;
        int dir = z;
        int[][] direct = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        boolean out = false;
        while (!out){
            int[] newIndex = new int[2];
            int x = index[0]; int y = index[1];
            int nx = x+direct[dir][0]; int ny = y+direct[dir][1];
            newIndex[0]=nx; newIndex[1]=ny;
            if ((nx<0||nx>grid.length-1||ny<0||ny>grid[0].length-1)) {
                break;
            }
            if ((grid[nx][ny].equals("."))||(grid[nx][ny].equals("^"))){
                if (check.contains(nx+" "+ny+" "+dir)){
                    count++;
                    break;
                }
                check.add(nx+" "+ny+" "+dir);
                index = newIndex;
            } else {
                dir++;
                if(dir==4)dir=0;
            }
        }
        return count;

    }

    private static int solve2(String[][] oggrid, ArrayList<String> indexes, ArrayList<String> indexes2){
        int count = 0;
        int dir = 0;
        int zz = 0;
        int[][] direct = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        for (int i = 0; i < indexes.size(); i++) {
            String[][] grid = new String[oggrid.length][oggrid[0].length];
            for(int j = 0; j < oggrid.length; j++) {
                for (int k = 0; k < oggrid[0].length; k++) {
                    grid[j][k]=oggrid[j][k];
                }
            }
            String[] s = indexes.get(i).split(" ");
            int x = Integer.parseInt(s[0]); int y = Integer.parseInt(s[1]);
            if (grid[x][y].equals("^")){
                zz++;
                continue;
            }
            if (!(indexes.indexOf(indexes.get(i))==i)){
                continue;
            }
            grid[x][y]="0";
            String[] s2 = indexes2.get(i).split(" ");
            int[] z = new int[2];
            z[0] = Integer.parseInt(s2[0]); z[1] = Integer.parseInt(s2[1]);
            System.out.println(s[0]+" "+s[1]);
            System.out.println(z[0]+" "+z[1]);
            count+=solvep2(grid, z, Integer.parseInt(s2[2]));
        }
        System.out.println(zz);
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

