package fm.statify.backend_service.stats;

import java.util.Date;

public class TopArtistStatistics extends Statistics {

    private String userId;
    private String firstArtistId;
    private String secondArtistId;
    private String thirdArtistId;
    private String fourthArtistId;
    private String fifthArtistId;
    private Date generateDate;

    public TopArtistStatistics(String userId, String firstArtistId, String secondArtistId, String thirdArtistId, String fourthArtistId, String fifthArtistId) {
        super(userId);
        this.firstArtistId = firstArtistId;
        this.secondArtistId = secondArtistId;
        this.thirdArtistId = thirdArtistId;
        this.fourthArtistId = fourthArtistId;
        this.fifthArtistId = fifthArtistId;
    }

    public TopArtistStatistics(Date generateDate, String userId, String firstArtistId, String secondArtistId, String thirdArtistId, String fourthArtistId, String fifthArtistId) {
        super(generateDate, userId);
        this.firstArtistId = firstArtistId;
        this.secondArtistId = secondArtistId;
        this.thirdArtistId = thirdArtistId;
        this.fourthArtistId = fourthArtistId;
        this.fifthArtistId = fifthArtistId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstArtistId() {
        return firstArtistId;
    }

    public String getSecondArtistId() {
        return secondArtistId;
    }

    public String getThirdArtistId() {
        return thirdArtistId;
    }

    public String getFourthArtistId() {
        return fourthArtistId;
    }

    public String getFifthArtistId() {
        return fifthArtistId;
    }

    public Date getGenerateDate() {
        return generateDate;
    }
}
