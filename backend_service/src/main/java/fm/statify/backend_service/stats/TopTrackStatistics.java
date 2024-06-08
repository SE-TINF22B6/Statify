package fm.statify.backend_service.stats;

import fm.statify.backend_service.entities.SimpleTrack;
import fm.statify.backend_service.entities.Track;

import java.util.Date;

public class TopTrackStatistics extends Statistics {

    private SimpleTrack firstTrack;
    private SimpleTrack secondTrack;
    private SimpleTrack thirdTrack;
    private SimpleTrack fourthTrack;
    private SimpleTrack fifthTrack;

    public TopTrackStatistics(String userId, SimpleTrack firstTrack, SimpleTrack secondTrack, SimpleTrack thirdTrack, SimpleTrack fourthTrack, SimpleTrack fifthTrack) {
        super(userId);
        this.firstTrack = firstTrack;
        this.secondTrack = secondTrack;
        this.thirdTrack = thirdTrack;
        this.fourthTrack = fourthTrack;
        this.fifthTrack = fifthTrack;
    }

    public TopTrackStatistics(Date generateDate, String userId, SimpleTrack firstTrack, SimpleTrack secondTrack, SimpleTrack thirdTrack, SimpleTrack fourthTrack, SimpleTrack fifthTrack) {
        super(generateDate, userId);
        this.firstTrack = firstTrack;
        this.secondTrack = secondTrack;
        this.thirdTrack = thirdTrack;
        this.fourthTrack = fourthTrack;
        this.fifthTrack = fifthTrack;
    }


    public SimpleTrack getFirstTrack() {
        return firstTrack;
    }

    public SimpleTrack getSecondTrack() {
        return secondTrack;
    }

    public SimpleTrack getThirdTrack() {
        return thirdTrack;
    }

    public SimpleTrack getFourthTrack() {
        return fourthTrack;
    }

    public SimpleTrack getFifthTrack() {
        return fifthTrack;
    }

}
