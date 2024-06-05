package fm.statify.backend_service.entities;

import java.util.List;

public class PlaylistWithSimplePlaylistTracks extends Playlist{

    List<SimplePlaylistTrack> tracks;

    public PlaylistWithSimplePlaylistTracks(String id, String name, String imageURL, List<SimplePlaylistTrack> tracks) {
        super(id, name, imageURL);
        this.tracks = tracks;
    }

    public List<SimplePlaylistTrack> getTracks() {
        return tracks;
    }
}
