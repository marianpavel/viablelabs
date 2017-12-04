package ro.marianpavel.viablelabs.Utilities;

import java.util.ArrayList;
import java.util.List;

import ro.marianpavel.viablelabs.POJO.HumanPOJO;

/**
 * We define a HumanRepository to access it's data whenever we want
 */
public class HumanRepository {

    public List<HumanPOJO> humans = new ArrayList<>();
    private static final HumanRepository ourInstance = new HumanRepository();

    public static HumanRepository getInstance() {
        return ourInstance;
    }

    private HumanRepository() {
    }

    public List<HumanPOJO> getHumans() {
        return humans;
    }

    public static HumanRepository getOurInstance() {
        return ourInstance;
    }
}
