import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Advent2024Day05 {
    public static void main(String[] args) {
            ArrayList<String> fileData = getFileData("src/input.txt");
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> updates  = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++){
            if (fileData.get(i).contains("|")){
                rules.add(fileData.get(i));
            } else if (fileData.get(i).contains(",")){
                updates.add(fileData.get(i));
            }
        }
        System.out.println(part(rules, updates, false));
        System.out.println(part(rules, updates, true));

    }

    public static int part(ArrayList<String> rules, ArrayList<String> updates, boolean fix) {
        ArrayList<String> incorrect = new ArrayList<>();
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < updates.size(); i++) {
            boolean good = true;
            String[] splitUpdate = updates.get(i).split(",");
            ArrayList<Integer> update = new ArrayList<>();
            for (int k = 0; k < splitUpdate.length; k++) {
                update.add(Integer.parseInt(splitUpdate[k]));
            }
            for (int j = 0; j < update.size()-1; j++){
                int x = j;
                while (update.size()-1>x){
                    x++;
                    if (!rules.contains(update.get(j)+"|"+update.get(x))){
                        good = false;
                        break;
                    }
                }
                if (!good) break;
            }
            if (good){
                count+=update.get(update.size()/2);
            }
            else{
                while (!good){
                    for (int z = 0; z < update.size()-1; z++){
                        int s;
                        good = true;
                        if (!rules.contains(update.get(z)+"|"+update.get(z+1))){
                            s = update.get(z);
                            update.set(z, update.get(z+1));
                            update.set(z+1, s);
                            good = false;
                            break;
                        }
                    }
                }
                count2+=update.get(update.size()/2);

            }

        }
        if (fix) return count2;
        return count;
    }

//    public static ArrayList<Integer> order(ArrayList<Integer> incorrect, ArrayList<String> rules){
//        int index = 0;
//        for (int j = 0; j < incorrect.size()-1; j++){
//            int x = j;
//            while (incorrect.size()-1>x) {
//                x++;
//                if (!rules.contains(incorrect.get(j) + "|" + incorrect.get(x))) {
//                    System.out.println(j);
//                }
//            }
//        }
//        return incorrect;
//    }

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
