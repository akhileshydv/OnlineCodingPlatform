package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class User extends BaseEntity {
    String name;
    Long score;
    List<Question> questions;

    public User(String _name){
        name = _name;
        questions = new ArrayList<>();
    }
}
