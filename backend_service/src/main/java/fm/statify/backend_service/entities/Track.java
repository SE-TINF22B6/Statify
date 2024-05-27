package fm.statify.backend_service.entities;

import java.util.List;

public class Track {

    private String id;
    private String name;
    private String imageUrl;
    private List<String> artists;
    private int duration;
    private int popularity; // 0 - 100

    private AudioFeatures audioFeatures;

    public Track(String id, String name, String imageUrl, List<String> artists, int duration, int popularity, AudioFeatures audioFeatures) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.artists = artists;
        this.duration = duration;
        this.popularity = popularity;
        this.audioFeatures = audioFeatures;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getArtists() {
        return artists;
    }

    public int getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public AudioFeatures getAudioFeatures() {
        return audioFeatures;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
