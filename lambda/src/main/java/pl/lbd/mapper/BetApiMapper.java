package pl.lbd.mapper;

import org.example.damagereport.model.BetApi;
import org.example.damagereport.model.CouponApi;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BetApiMapper {


   public static BetApi mapToBetApi(List<String> stringList) throws Exception {
            List<CouponApi> coupons= stringList.stream()
                    .map(CouponMapper::mapToCoupon)
                    .collect(Collectors.toList());
            if(coupons.stream().anyMatch(s->s.getNumbers().size()<6))
            {
                throw new IllegalArgumentException("Wrong Numbers");
            }
        BetApi api= new BetApi();
            api.setCoupons(coupons);
            return api;
    }

}
