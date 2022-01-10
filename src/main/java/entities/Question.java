package entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Question extends BaseEntity{
    private String name;
    private String question;
    private Level level;
    private Long score;
    public Question(String _name, String _question, Level _level){
        name = _name;
        question =_question;
        level = _level;
        switch (level){
            case LOW:
                score = 10L;
                break;
            case MEDIUM:
                score = 20L;
                break;
            case HIGH:
                score = 30L;
                break;
            default:
                break;
        }
    }

}
