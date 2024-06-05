package fm.statify.backend_service.entities;

import java.util.List;

public class PlaylistWithSimpleTracks extends Playlist{

    List<SimpleTrack> tracks;

    public PlaylistWithSimpleTracks(String id, String name, String imageURL, List<SimpleTrack> tracks) {
        super(id, name, imageURL);
        this.tracks = tracks;
    }

    public List<SimpleTrack> getTracks() {
        return tracks;
    }
}
