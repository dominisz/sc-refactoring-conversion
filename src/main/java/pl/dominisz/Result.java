package pl.dominisz;

import java.util.List;

public class Result {
    public List<ResultInfo> getInf() {
        return null;
    }

    public boolean isSuccessful() {
        return getInf().get(0).getCode() != -1;
    }
}
