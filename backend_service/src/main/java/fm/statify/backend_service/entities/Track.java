package fm.statify.backend_service.entities;

import java.util.List;

public class Track {

    private String id;
    private String name;
    private List<Artist> artists;
    private int duration;
    private int popularity; // 0 - 100

    private AudioFeatures audioFeatures;


}
