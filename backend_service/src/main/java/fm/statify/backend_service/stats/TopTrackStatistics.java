package fm.statify.backend_service.stats;

import java.util.Date;

public class TopTrackStatistics extends Statistics{

    private String userId;
    private String firstTrackId;
    private String secondTrackId;
    private String thirdTrackId;
    private String fourthTrackId;
    private String fifthTrackId;
    private Date generateDate;

    public TopTrackStatistics(String userId, String firstTrackId, String secondTrackId, String thirdTrackId, String fourthTrackId, String fifthTrackId) {
        super(userId);
        this.firstTrackId = firstTrackId;
        this.secondTrackId = secondTrackId;
        this.thirdTrackId = thirdTrackId;
        this.fourthTrackId = fourthTrackId;
        this.fifthTrackId = fifthTrackId;
    }

    public TopTrackStatistics(Date generateDate, String userId, String firstTrackId, String secondTrackId, String thirdTrackId, String fourthTrackId, String fifthTrackId) {
        super(generateDate, userId);
        this.firstTrackId = firstTrackId;
        this.secondTrackId = secondTrackId;
        this.thirdTrackId = thirdTrackId;
        this.fourthTrackId = fourthTrackId;
        this.fifthTrackId = fifthTrackId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstTrackId() {
        return firstTrackId;
    }

    public String getSecondTrackId() {
        return secondTrackId;
    }

    public String getThirdTrackId() {
        return thirdTrackId;
    }

    public String getFourthTrackId() {
        return fourthTrackId;
    }

    public String getFifthTrackId() {
        return fifthTrackId;
    }

    public Date getGenerateDate() {
        return generateDate;
    }
}
