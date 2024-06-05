package fm.statify.backend_service.entities;

import java.util.List;
import java.util.Set;

public class SimplePlaylistTrack extends SimpleTrack{

    private Set<String> genres;

    private int duration;

    public SimplePlaylistTrack(String id, String name, String imageUrl, List<String> artists, Set<String> genres, int duration) {
        super(id, name, imageUrl, artists);
        this.genres = genres;
        this.duration = duration;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public int getDuration() {
        return duration;
    }
}
