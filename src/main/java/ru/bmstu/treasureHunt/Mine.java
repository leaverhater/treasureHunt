package ru.bmstu.treasureHunt;

/**
 * User: voznyuk
 * Date: 22.11.13
 * Time: 21:00
 */
public class Mine {
    private int x;
    private int y;
    private Map map;
    private Path pathToBase;
    public Mine (int y, int x) {
        this.y = y;
        this.x = x;

    }
    public void setMap (Map map) {
        this.map = map;
    }

    public void setPathToBase(Path path) {
        this.pathToBase = path;
    }

    public int getX () {
        return x;
    }

    public Path getPathToBase() {
        return pathToBase;
    }

    public int getY() {
        return y;
    }


}
