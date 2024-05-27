package fm.statify.backend_service.stats;

import fm.statify.backend_service.entities.Artist;

import java.util.Date;

public class TopArtistStatistics extends Statistics{

    private Artist firstArtist;
    private Artist secondArtist;
    private Artist thirdArtist;
    private Artist fourthArtist;
    private Artist fifthArtist;

    public TopArtistStatistics(String userId, Artist firstArtist, Artist secondArtist, Artist thirdArtist, Artist fourthArtist, Artist fifthArtist) {
        super(userId);
        this.firstArtist = firstArtist;
        this.secondArtist = secondArtist;
        this.thirdArtist = thirdArtist;
        this.fourthArtist = fourthArtist;
        this.fifthArtist = fifthArtist;
    }

    public TopArtistStatistics(Date generateDate, String userId, Artist firstArtist, Artist secondArtist, Artist thirdArtist, Artist fourthArtist, Artist fifthArtist) {
        super(generateDate, userId);
        this.firstArtist = firstArtist;
        this.secondArtist = secondArtist;
        this.thirdArtist = thirdArtist;
        this.fourthArtist = fourthArtist;
        this.fifthArtist = fifthArtist;
    }

    public Artist getFirstArtist() {
        return firstArtist;
    }

    public void setFirstArtist(Artist firstArtist) {
        this.firstArtist = firstArtist;
    }

    public Artist getSecondArtist() {
        return secondArtist;
    }

    public void setSecondArtist(Artist secondArtist) {
        this.secondArtist = secondArtist;
    }

    public Artist getThirdArtist() {
        return thirdArtist;
    }

    public void setThirdArtist(Artist thirdArtist) {
        this.thirdArtist = thirdArtist;
    }

    public Artist getFourthArtist() {
        return fourthArtist;
    }

    public void setFourthArtist(Artist fourthArtist) {
        this.fourthArtist = fourthArtist;
    }

    public Artist getFifthArtist() {
        return fifthArtist;
    }

    public void setFifthArtist(Artist fifthArtist) {
        this.fifthArtist = fifthArtist;
    }
}
