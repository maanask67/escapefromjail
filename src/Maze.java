import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class Maze {
    List<Tiles> listOfTiles = new ArrayList<Tiles>();

    public List<Tiles> getListOfTiles() {
        return listOfTiles;
    }

    public Maze(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            for (int lines = 0; lines < 8; lines++) {
                String platformLine = br.readLine();
                String[] splitted = platformLine.split("");
                int column = 0;
                for (String s : splitted) {
                    if (s.equals("*")) {
                        listOfTiles.add(new Tiles(column * 20, lines * 20, Color.BLACK));
                    } else if (s.equals("E")) {
                        listOfTiles.add(new Tiles(column * 20, lines * 20, Color.GREEN));
                    }
                    else if (s.equals("R")) {
                        listOfTiles.add(new Tiles(column * 20, lines * 20, Color.INDIANRED));
                    }
                    column++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
