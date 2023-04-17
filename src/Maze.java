import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    List<Tiles> listOfTiles = new ArrayList<Tiles>();

    public Maze(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            for (int lines=0;lines<8;lines++) {
                String platformLine = br.readLine();
                String[] splitted=platformLine.split("");
                int column=0;
                for(String s : splitted){
                    if (s.equals("*")){
                        listOfTiles.add(new Tiles(column*20,lines*20));
                    }
                    column++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
