package com.company.ordermanagementsystem;

import com.company.ordermanagementsystem.port.out.OrderApiOutPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InventoryAdapterMock implements OrderApiOutPort {

    @Override
    public void updateInventory(UUID productId, int quantity) {
        // This method should call the inventor service api to update the inventory
    }
}
