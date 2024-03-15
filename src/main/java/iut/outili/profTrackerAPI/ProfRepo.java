package iut.outili.profTrackerAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProfRepo {
    List<Prof> profs;

    public ProfRepo() {
        profs = new ArrayList<Prof>();
    }

    public ProfRepo(List<Prof> profs) {
        this.profs = profs;
    }

    public List<Prof> getProfs() {
        return profs;
    }

    public void addProf(Prof prof) {
        profs.add(prof);
    }
}
