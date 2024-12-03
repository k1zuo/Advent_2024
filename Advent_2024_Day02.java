import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Advent_2024_Day02 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        System.out.println(getSafe(fileData, false));
        System.out.println(getSafe(fileData, true));
    }

    public static int getSafe(ArrayList<String> fileData, boolean damp){
        int count = 0;
        for (int i = 0; i < fileData.size(); i++){
            String[] splitData = fileData.get(i).split(" ");
            int[] f = new int[splitData.length];
            for (int j = 0; j < f.length; j++){
                f[j]=Integer.parseInt(splitData[j]);
            }
            boolean safe = safeCheck(f);
            if (safe)count++;
            if ((damp)&&!(safe)){
                for (int a = 0; a < f.length; a++){
                    int[] j = new int[f.length-1];
                    int ex = a;
                    int c = 0;
                    for (int b = 0; b < f.length; b++){
                        if (b!=a){
                            j[c]=f[b];
                            c++;
                        }
                    }
                    boolean dampSafe = safeCheck(j);
                    if (dampSafe){
                        count++;
                        break;
                    }
                }
            }

        }
        return count;
    }

    public static boolean safeCheck (int[] f){
        boolean check = true;
        boolean increase = true;
        boolean decrease = true;

        for (int l = 0; l < f.length-1;l++){
            if (f[l]>f[l+1]);
            else increase = false;
            if (f[l]<f[l+1]);
            else decrease = false;
        }
        for (int k = 0; k < f.length-1; k++){
            if (((increase)&&((f[k]-f[k+1])>0&&(f[k]-f[k+1])<4))||((decrease)&&((f[k]-f[k+1])<0&&(f[k]-f[k+1])>-4)));
            else check = false;
        }
        return check;
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