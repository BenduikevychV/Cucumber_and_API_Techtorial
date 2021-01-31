package API.pojo.football;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class CompetitionsPojo {

    private int count;
    private Object filters;
    private List<Map<String,Object>> competitions;

}
