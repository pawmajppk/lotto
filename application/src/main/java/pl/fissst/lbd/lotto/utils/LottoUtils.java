package pl.fissst.lbd.lotto.utils;

import java.util.Arrays;
import java.util.List;

public class LottoUtils {

    public static Integer calcHits(Integer[] drawNumbers, Integer[] couponNumbers) {
        List<Integer> couponValues = Arrays.asList(couponNumbers);

        return Math.toIntExact(Arrays.stream(drawNumbers).filter(i -> couponValues.contains(i)).count());
    }
}