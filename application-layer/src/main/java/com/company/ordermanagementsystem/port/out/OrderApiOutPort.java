package com.company.ordermanagementsystem.port.out;

import java.util.UUID;

public interface OrderApiOutPort {

    void updateInventory(UUID productId, int quantity);

}
