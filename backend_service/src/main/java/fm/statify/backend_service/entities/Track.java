package fm.statify.backend_service.entities;

import java.util.List;

public class Track {

    private String id;
    private String name;
    private List<String> artists;
    private int duration;
    private int popularity; // 0 - 100

    private AudioFeatures audioFeatures;

    public Track(String id, String name, List<String> artists, int duration, int popularity, AudioFeatures audioFeatures) {
        this.id = id;
        this.name = name;
        this.artists = artists;
        this.duration = duration;
        this.popularity = popularity;
        this.audioFeatures = audioFeatures;
    }
}
