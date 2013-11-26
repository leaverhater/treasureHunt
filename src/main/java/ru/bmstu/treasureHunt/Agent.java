package ru.bmstu.treasureHunt;

import java.util.ArrayList;
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
    private Path currentPath;
    private int currentStep;
    private int prevX;
    private int prevY;
    //True, если агент нашел рудник и возвращается на базу, иначе рандомно ходит, пока не найдет рудник
    private boolean ifBack = false;

    private List<Mine> knownMines;
    private List<Track> tracks = new ArrayList<Track>();

    private boolean withTrack = false;
    private int TRACK_NUM = 3;
    private int[] trackX = new int[TRACK_NUM];
    private int[] trackY = new int[TRACK_NUM];
    private int trackCount = 0;
    private int id;
    private static int idNum = 0;

    public boolean isIfBack() {
        return ifBack;
    }

    public void setIfBack(boolean ifBack, Path path) {
        currentPath = path;
        currentStep = 0;
        this.ifBack = ifBack;
    }


    public Agent(int y, int x) {
        this.y = y;
        this.x = x;
        this.prevY = y;
        this.prevX = x;
        this.id = idNum;
        idNum++;
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

    public void addKnownMine(Mine mine) {
        knownMines.add(mine);
    }

    public boolean ifKnownMine(Mine mine) {
        for (Mine knownMine : knownMines) {
            if (knownMine.equals(mine)) {
                return true;
            }
        }
        return false;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public int getPrevX() {

        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int nextStepToBase() {
        if (currentStep == currentPath.getSize() - 3) {
            ifBack = false;
//            System.out.println("Complete!");
            map.addResource();
        }
        currentStep++;
        return currentPath.getWay(currentStep);
    }

    public boolean isWithTrack() {
        return withTrack;
    }

    public void setWithTrack(boolean withTrack) {
        this.withTrack = withTrack;
        setTrackCount();
    }

    public int getTrackX(int index) {
        return trackX[index];
    }

    public int getTrackY(int index) {
        return trackY[index];
    }

    public void setTrackX(int index, int value) {
        trackX[index] = value;
    }

    public void setTrackY(int index, int value) {
        trackY[index] = value;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount() {
        trackCount = TRACK_NUM;
    }

    public int getId() {
        return id;
    }

    public static int getIdNum() {
        return idNum;
    }

    public void decTrackCount() {
        if (trackCount > 0) {
            trackCount--;
            for (Track track : tracks) {
                track.decLifeTime();
            }
        } else {
            withTrack = false;
        }
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void processTracks() {
        if (!withTrack) {
            Track trackToDelete = null;
//            System.out.println("Without track");
            for (Track track : tracks) {
                if (!track.isAlive()) {
//                    System.out.println("Removing track");
                    trackToDelete = track;
                }
                track.decLifeTime();
            }
            if (trackToDelete != null) {
                tracks.remove(trackToDelete);
            }
        } else {
//            System.out.println("With track");
            addTrack(new Track(prevX, prevY));
            decTrackCount();
        }
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
