package fm.statify.backend_service.entities;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Track extends SimpleTrack {

    private String duration;
    private int popularity; // 0 - 100

    private AudioFeatures audioFeatures;

    public Track(String id, String name, String imageUrl, List<String> artists, int duration, int popularity, AudioFeatures audioFeatures) {
        super(id, name, imageUrl, artists);
        this.duration = millisecondsToMinutes(duration);
        this.popularity = popularity;
        this.audioFeatures = audioFeatures;
    }

    public String getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public AudioFeatures getAudioFeatures() {
        return audioFeatures;
    }

    public static Track create(SimpleTrack track, int duration, int popularity, AudioFeatures audioFeatures) {
        return new Track(track.getId(), track.getName(), track.getImageUrl(), track.getArtists(), duration, popularity, audioFeatures);
    }

    private String millisecondsToMinutes(int duration_ms) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) duration_ms);
        // get the rest to compute second
        long rest = (long) duration_ms - TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(rest);

        // add 0 if it is one digit
        String secondsString = Long.toString(seconds);
        if (secondsString.length() == 1) {
            secondsString = "0" + secondsString;
        }
        return minutes + ":" + secondsString;
    }

}
