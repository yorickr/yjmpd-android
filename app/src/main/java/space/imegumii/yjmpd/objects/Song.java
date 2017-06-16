package space.imegumii.yjmpd.objects;

import android.support.annotation.NonNull;

/**
 * Created by imegumii on 06/06/2017.
 */

public class Song implements Comparable{
    public int id;
    public String artist;
    public String album;
    public String genre;
    public String title;

    public Song (int id) {
        this.id = id;
    }

    public void addArtist (String a) {
        this.artist = a;
    }

    public void addAlbum (String a) { this.album = a;
    }

    public void addGenre (String g) {
        this.genre = g;
    }

    public void addTitle (String t) {
        this.title = t;
    }

    @Override
    public String toString() {
        return "Song " + id + " : " + title + " " + artist + " " + album + " " + genre;
    }

    @Override
    public int compareTo (@NonNull Object o) {
        return (this.id < ((Song)o).id) ? -1 : 1;
    }
}


