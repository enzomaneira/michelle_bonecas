package com.maneira.mongoproject.demo.domain.enums;

public enum OrderStatus {

    ESPERA(1),
    EM_CONFECCAO(2),
    PRONTO(3),
    ENTREGUE(4),
    PAGO(5),
    CANCELADO(6);

    private int code;

    private OrderStatus(int code){
        this.code = code;
    }

    public int getCode(){return code;}

    public static OrderStatus valueOf(int code){
        for (OrderStatus value: OrderStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code");
    }
}
