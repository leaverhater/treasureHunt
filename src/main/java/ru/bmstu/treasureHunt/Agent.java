package ru.bmstu.treasureHunt;

import java.util.List;

/**
 * User: voznyuk
 * Date: 22.11.13
 * Time: 20:58
 */
public class Agent {
    private int x;
    private int y;
    private Map map;

    //True, если агент нашел рудник и возвращается на базу, иначе рандомно ходит, пока не найдет рудник
    public boolean ifBack = false;

    private List<Mine> knownMines;
    public Agent(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getY() {
        return y;
    }

    public int getX() {

        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void addKnownMine (Mine mine) {
        knownMines.add(mine);
    }
}
