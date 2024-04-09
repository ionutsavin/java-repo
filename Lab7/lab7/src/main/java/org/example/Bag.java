package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Bag {
    private final List<Tile> tiles;

    public Bag(int n) {
        tiles = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tiles.add(new Tile(i, j));
            }
        }
        Collections.shuffle(tiles);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany && !tiles.isEmpty(); i++) {
            extracted.add(tiles.remove(0));
        }
        return extracted;
    }
}
