package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Bag {
    private final List<Tile> tiles;
    int n;

    public Bag(int n) {
        this.n = n;
        tiles = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    tiles.add(new Tile(i, j));
                }
            }
        }
        Collections.shuffle(tiles);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getN() {
        return n;
    }

    public synchronized List<Tile> extractTiles(int index) {
        List<Tile> extracted = new ArrayList<>();
        if (index >= 0 && index < tiles.size()) {
            extracted.add(tiles.remove(index));
        }
        return extracted;
    }
}
