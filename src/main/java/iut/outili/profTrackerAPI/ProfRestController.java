package iut.outili.profTrackerAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfRestController {

    @Autowired
    private ProfRepo profRepository;

    @GetMapping("/prof")
    public List<String> getProfs() {
        profRepository = new ProfRepo();
        profRepository.addProf(new Prof("Outili" , "Rayan", "rayan.outili@gmail.com"));
        List<String> profInfoList = new ArrayList<>();
        for (Prof prof : profRepository.getProfs()) {
            profInfoList.add(prof.toString());
        }

        return profInfoList;
    }

}
