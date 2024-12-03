import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Advent2024day03 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        String data = fileData.toString();
        System.out.println(result(data, false));
        System.out.println(result(data, true));
    }

    public static int result(String data, boolean p2){
        Matcher m = Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(data);
        int sum = 0;
        int sum2 = 0;
        while (m.find()){
            sum+=(Integer.parseInt(m.group(1))*Integer.parseInt(m.group(2)));
            if (p2){
                String between = data.substring(0, m.start());
                int lastDo = between.lastIndexOf("do()");
                int lastDont = between.lastIndexOf("don't()");
                if (lastDo>lastDont||(lastDont*lastDo)==1) sum2+=(Integer.parseInt(m.group(1))*Integer.parseInt(m.group(2)));
            }
        }
        if (!p2)return sum;
        return sum2;
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






