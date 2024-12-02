import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/input.txt");
        System.out.println(getDistance(fileData));
        System.out.println(getSim(fileData));
    }

    public static int getDistance(ArrayList<String> fileData){
        int[] f = new int[fileData.size()];
        int[] s = new int[fileData.size()];
        for (int i = 0; i < fileData.size(); i++) {
            String[] splitData = fileData.get(i).split("   ");
            f[i]=Integer.parseInt(splitData[0]);
            s[i]=Integer.parseInt(splitData[1]);
        }
        Arrays.sort(f);
        Arrays.sort(s);
        int distance = 0;
        System.out.println();
        for (int i = 0; i < f.length; i++){
            distance+=Math.abs(f[i]-s[i]);

        }
        return distance;
    }


    public static int getSim(ArrayList<String> fileData){
        int[] f = new int[fileData.size()];
        int[] s = new int[fileData.size()];
        for (int i = 0; i < fileData.size(); i++) {
            String[] splitData = fileData.get(i).split("   ");
            f[i]=Integer.parseInt(splitData[0]);
            s[i]=Integer.parseInt(splitData[1]);
        }
        Arrays.sort(f);
        Arrays.sort(s);
        int sim = 0;
        for (int i = 0; i < f.length; i++){
            int dup = 0;
            for (int j = 0; j < s.length; j++){
                if (f[i]==s[j]) dup++;
            }
            sim+=(f[i]*dup);
        }
        return sim;
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
