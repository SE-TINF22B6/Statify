package fm.statify.backend_service.stats;

import java.util.Date;

public class PlaylistStatistics extends Statistics {
    private String userId;
    private String playlistId;
    private String name;
    private int tracksNumber;
    private int duration;
    private String topGenre;
    private int topGenreTracksNumber;
    private String topArtist;
    private int topArtistTracksNumber;
    private Date generateDate;

    public PlaylistStatistics(Date generateDate, String userId, String playlistId, String name, int tracksNumber, int duration, String topGenre, int topGenreTracksNumber, String topArtist, int topArtistTracksNumber) {
        super(generateDate, userId);
        this.playlistId = playlistId;
        this.name = name;
        this.tracksNumber = tracksNumber;
        this.duration = duration;
        this.topGenre = topGenre;
        this.topGenreTracksNumber = topGenreTracksNumber;
        this.topArtist = topArtist;
        this.topArtistTracksNumber = topArtistTracksNumber;
    }

    public PlaylistStatistics(String userId, String playlistId, String name, int tracksNumber, int duration, String topGenre, int topGenreTracksNumber, String topArtist, int topArtistTracksNumber) {
        super(userId);
        this.playlistId = playlistId;
        this.name = name;
        this.tracksNumber = tracksNumber;
        this.duration = duration;
        this.topGenre = topGenre;
        this.topGenreTracksNumber = topGenreTracksNumber;
        this.topArtist = topArtist;
        this.topArtistTracksNumber = topArtistTracksNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }

    public int getTracksNumber() {
        return tracksNumber;
    }

    public int getDuration() {
        return duration;
    }

    public String getTopGenre() {
        return topGenre;
    }

    public int getTopGenreTracksNumber() {
        return topGenreTracksNumber;
    }

    public String getTopArtist() {
        return topArtist;
    }

    public int getTopArtistTracksNumber() {
        return topArtistTracksNumber;
    }

    public Date getGenerateDate() {
        return generateDate;
    }
}
