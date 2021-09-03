package pl.lbd.mapper;

import org.example.damagereport.model.CouponApi;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CouponMapper {

    public static CouponApi  mapToCoupon(String numbers)  {
        Set<Integer> lst = Arrays.stream(numbers.trim().split(","))
                .map(Integer::parseInt).filter(i->i>1&&i<49)
                .collect(Collectors.toSet());
        CouponApi couponApi = new CouponApi();
        couponApi.numbers(lst);
        return couponApi;
    }
}
