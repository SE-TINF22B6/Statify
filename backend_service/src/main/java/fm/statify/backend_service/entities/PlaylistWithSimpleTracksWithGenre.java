package fm.statify.backend_service.entities;

import java.util.List;

public class PlaylistWithSimpleTracksWithGenre extends Playlist{

    List<SimplePlaylistTrack> tracks;

    public PlaylistWithSimpleTracksWithGenre(String id, String name, String imageURL, List<SimplePlaylistTrack> tracks) {
        super(id, name, imageURL);
        this.tracks = tracks;
    }

    public List<SimplePlaylistTrack> getTracks() {
        return tracks;
    }
}
