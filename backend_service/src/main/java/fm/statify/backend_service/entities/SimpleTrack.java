package fm.statify.backend_service.entities;

import java.util.List;

public class SimpleTrack {
    private String id;
    private String name;
    private String imageUrl;
    private List<String> artists;

    public SimpleTrack(String id, String name, String imageUrl, List<String> artists) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.artists = artists;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
}
