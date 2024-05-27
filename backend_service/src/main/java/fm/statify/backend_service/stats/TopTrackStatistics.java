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
}
