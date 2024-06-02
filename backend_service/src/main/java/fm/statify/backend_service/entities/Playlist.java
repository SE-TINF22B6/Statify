package fm.statify.backend_service.entities;

import java.awt.*;

public class Playlist {

    private String id;

    private String name;

    private String imageURL;

    public Playlist(String id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}
