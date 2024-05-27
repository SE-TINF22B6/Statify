package fm.statify.backend_service.stats;

import java.util.Date;

public class Statistics {
    private Date generateDate;
    private String userId;

    public Statistics(String userId) {
        this.userId = userId;
        this.generateDate = new Date();
    }

    public Statistics(Date generateDate, String userId) {
        this.generateDate = generateDate;
        this.userId = userId;
    }
}
