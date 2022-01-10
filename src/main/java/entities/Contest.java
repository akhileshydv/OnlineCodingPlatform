package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Contest extends BaseEntity{
    String name;
    Status status;
    Level level;
    Long numQuestion;
    List<Question> questions;
    List<User> participants;
    LeaderBoard leaderBoard;
    Map<String, List<Question> > result;

    public Contest(String _name, User creator, Level _level){
        name = _name;
        level = _level;
        status = Status.CREATED;
        questions = new ArrayList<>();
        participants = new ArrayList<>();
        participants.add(creator);
    }
}
