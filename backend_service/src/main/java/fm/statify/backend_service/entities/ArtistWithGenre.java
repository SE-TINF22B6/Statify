package fm.statify.backend_service.entities;

import java.util.List;

public class ArtistWithGenre extends Artist{
    List<String> genres;

    public ArtistWithGenre(String id, String name, String imageURL, List<String> genres) {
        super(id, name, imageURL);
        this.genres = genres;
    }

    public List<String> getGenres() {
        return genres;
    }
}
