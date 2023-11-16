package fm.statify.backend_service.entities;

public class Stream {

    private String trackId;

    private String unixTimestamp;

    private String userId;

    public Stream (String trackId, String unixTimestamp, String userId) {
        this.trackId = trackId;
        this.unixTimestamp = unixTimestamp;
        this.userId = userId;
    }

}
