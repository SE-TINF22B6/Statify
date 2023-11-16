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

    private String getId(){
        return id;
    }

    private String getName(){
        return name;
    }

    private String getImageUrl(){
        return imageUrl;
    }

    private void setId(String id){
        this.id = id;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
