package sevices;

import entities.Contest;
import entities.Level;

public interface ContestService {
    public Contest CreateContest(String contestName, Level level, String userName);
    public Contest runContest(Long contestId, String userName) throws InterruptedException;
}
