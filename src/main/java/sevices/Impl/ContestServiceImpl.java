package sevices.Impl;

import entities.*;
import repositories.ContestDao;
import repositories.QuestionDao;
import repositories.UserDao;
import sevices.ContestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ContestServiceImpl extends Thread implements ContestService{
    ContestDao contestDao = new ContestDao();
    UserDao userDao = new UserDao();
    QuestionDao questionDao = new QuestionDao();
    Contest currentContest;
    private final Integer MAX_QUESTION = 10;

    @Override
    public Contest CreateContest(String contestName, Level level, String userName) {
        Contest contest = new Contest(contestName, userDao.findById(userName), level);
        contestDao.create(contest);
        return contest;
    }

    @Override
    public Contest runContest(Long contestId, String userName) throws InterruptedException {
        currentContest = contestDao.findById(contestId);
        List<Question> contestQuestions = getQuestion(currentContest.getLevel());
        currentContest.setQuestions(contestQuestions);
        currentContest.setNumQuestion((long) contestQuestions.size());
        currentContest.setStatus(Status.IN_PROGRESS);
        contestDao.update(currentContest);
        solveProblems(currentContest.getParticipants());
        Thread.sleep(5000L);
        currentContest.setStatus(Status.ENDED);
        contestDao.update(currentContest);
        return currentContest;
    }

    private List<Question> getQuestion(Level level){
        List<Question> questions = questionDao.findAll().stream().filter(question -> question.getLevel().equals(level)).collect(Collectors.toList());
        Collections.shuffle(questions, new Random());
        return questions.subList(0,  Math.max(questions.size(), MAX_QUESTION));
    }

    private void solveProblems(List<User> participants){
        ContestServiceImpl [] threads = new ContestServiceImpl[20];
        int index=0;
        for(User user: participants){
            simulateProblemSolvingForAUser(user);
        }
    }

    private void simulateProblemSolvingForAUser(User user){
        Random random = new Random();
        int numSolved = random.nextInt(Math.toIntExact(currentContest.getNumQuestion()))+1;
        List<Question> questions = new ArrayList<>(currentContest.getQuestions());
        Collections.shuffle(questions, new Random());
        currentContest.getResult().put(user.getName(), questions.subList(0,numSolved));
    }
}

