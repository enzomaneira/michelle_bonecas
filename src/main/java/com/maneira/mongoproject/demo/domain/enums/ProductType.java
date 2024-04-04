package com.maneira.mongoproject.demo.domain.enums;

public enum ProductType {

    FELTRO(1),
    PANO(2),
    NATAL(3),
    ESCOLAR(4),
    DECORACAO(5),
    LEMBRANCINHA(6),
    FANTASIA(7),
    PASCOA(8),
    FANTOCHES(9),
    DIVERSOS(10),
    CONSERTO(11),
    QUIETBOOK(12),
    BRINQUEDOS(13),
    PAPELARIA(14);



    private int code;

    private ProductType(int code){
        this.code = code;
    }

    public int getCode(){return code;}

    public static ProductType valueOf(int code){
        for (ProductType value: ProductType.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code");
    }
}
