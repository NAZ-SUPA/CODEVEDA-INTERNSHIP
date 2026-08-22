import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class FILE_READER_WRITER {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder path = new StringBuilder(
                "D:/Desktop/GITHUB/CODVEDA-INTERNSHIP/CODEVEDA-INTERNSHIP/LEVEL 2/TASK 2/FILE_READER_WRITER/src/FILES/");
        System.out.println("WELCOM TO FILE READER AND WRITER : ");
        System.out.println();
        System.out.println(
                "WRITE A NAME OF A TEXT FILE THAT INSIDE THIS LOCATION " + path + ":");

        while (true) {
            try {
                String file_name = scanner.nextLine().trim();
                if (file_name.isEmpty()) {
                    System.out.println(
                            "WRITE A NAME OF A TEXT FILE THAT INSIDE THIS LOCATION " + path + ":");
                    continue;
                }
                String added = file_name.contains(".txt") ? file_name : file_name + ".txt";
                path.append(added);
                File object = new File(path.toString());
                if (!object.exists()) {
                    System.out.println("THE FILE " + file_name + " DOESN'T EXIST IN " + path);
                    path.delete(path.indexOf(added), path.length());
                    continue;
                } else {
                    if (!object.canRead()) {
                        System.out.println("ERROR OCCURED");
                    } else {
                        try (Scanner reader = new Scanner(object)) {
                            StringBuilder data = new StringBuilder();
                            while (reader.hasNextLine()) {
                                data.append(reader.nextLine());
                            }
                            String words[] = data.toString().split(" ");
                            String base = "D:/Desktop/GITHUB/CODVEDA-INTERNSHIP/CODEVEDA-INTERNSHIP/LEVEL 2/TASK 2/FILE_READER_WRITER/src/FILES/";
                            FileWriter writer = new FileWriter(base + "result.txt", false);
                            if (words.length == 1 && words[0] == "") {
                                writer.write("THE FILE IS EMPTY.");
                            } else {
                                writer.write("FILE " + file_name + ".txt" + " CONTAINS " + words.length + " WORDS.");
                            }
                            writer.close();
                            System.out.println("SUCCESS: DATA WRITTEN TO result.txt");
                            break;
                        } catch (FileNotFoundException e) {
                            System.out.println("THE FILE IS NOT FOUND.");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        scanner.close();
    }
}
