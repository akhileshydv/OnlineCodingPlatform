package sevices;

import entities.User;

public interface UserService {
    public User createUser(String username);
    public void attendContest(String username, Long contestId);
    public void LeaderBoard();
    public void withdrawContest(String username, Long contestId);

}
