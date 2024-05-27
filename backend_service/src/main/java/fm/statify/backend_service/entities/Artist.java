package fm.statify.backend_service.entities;

public class Artist {

    private String id;
    private String name;
    private String imageUrl;

    public Artist(String id, String name, String imageURL){
        this.id = id;
        this.name = name;
        this. imageUrl = imageURL;
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
}
