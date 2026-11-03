package me.zinoviev.railway.train;

import java.math.BigDecimal;

public enum CarriageType {

    PLATZKART(54, "1.0"),
    COUPE(36, "1.6"),
    SV(18, "2.5");

    private final int seatCount;
    private final BigDecimal priceMultiplier;

    CarriageType(int seatCount, String priceMultiplier){
        this.seatCount = seatCount;
        this.priceMultiplier = new BigDecimal(priceMultiplier);
    }

    public int getSeatCount(){
        return seatCount;
    }

    public BigDecimal getPriceMultiplier(){
        return priceMultiplier;
    }

}
