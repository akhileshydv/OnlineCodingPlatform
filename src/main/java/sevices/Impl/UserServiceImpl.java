package sevices.Impl;

import entities.Contest;
import entities.User;
import repositories.ContestDao;
import repositories.UserDao;
import sevices.UserService;

public class UserServiceImpl  implements UserService {
    UserDao userDao = new UserDao();
    ContestDao contestDao = new ContestDao();

    @Override
    public User createUser(String name) {
        User user = new User(name);
        userDao.create(user);
        return user;
    }

    @Override
    public void attendContest(String username, Long contestId) {
        Contest contest = contestDao.findById(contestId);
        contest.getParticipants().add(userDao.findById(username));
        contestDao.update(contest);
    }

    @Override
    public void LeaderBoard() {
        userDao.findAll().stream().sorted((user1, user2)-> (int) (user2.getScore() - user1.getScore())).forEach(user -> System.out.println(user.getName()+ " " + user.getScore()));
    }

    @Override
    public void withdrawContest(String username, Long contestId) {
        Contest contest = contestDao.findById(contestId);
        contest.getParticipants().remove(userDao.findById(username));
        contestDao.update(contest);
    }
}
