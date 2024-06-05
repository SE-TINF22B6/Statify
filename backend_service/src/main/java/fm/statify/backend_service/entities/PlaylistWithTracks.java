package fm.statify.backend_service.entities;

import java.util.ArrayList;
import java.util.List;

public class PlaylistWithTracks extends Playlist{

    List<Track> tracks = new ArrayList<>();
    public PlaylistWithTracks(String id, String name, String imageURL, List<Track> tracks) {
        super(id, name, imageURL);
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public static PlaylistWithTracks create (Playlist playlist, List<Track>tracks ){
        return new PlaylistWithTracks(playlist.getId(), playlist.getName(), playlist.getImageURL(), tracks);
    }
}
