package fm.statify.backend_service.entities;

import java.util.List;

public class Track extends SimpleTrack{

    private int duration;
    private int popularity; // 0 - 100

    private AudioFeatures audioFeatures;

    public Track(String id, String name, String imageUrl, List<String> artists, int duration, int popularity, AudioFeatures audioFeatures) {
        super(id, name, imageUrl, artists);
        this.duration = duration;
        this.popularity = popularity;
        this.audioFeatures = audioFeatures;
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

    public static Track create(SimpleTrack track, int duration, int popularity, AudioFeatures audioFeatures){
        return new Track(track.getId(), track.getName(), track.getImageUrl(), track.getArtists(), duration, popularity, audioFeatures);
    }

}
