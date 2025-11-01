package com.company.ordermanagementsystem;

import com.company.ordermanagementsystem.controller.objectmother.CreateOrderRequestObjectMother;
import com.company.ordermanagementsystem.dto.CreateOrderRequest;
import com.company.ordermanagementsystem.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderIntegrationTest {

    private static final String BASE_PATH = "/api/v1/orders";

    private UUID orderId;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldCreateAndGetANewOrder() throws Exception {
        CreateOrderRequest createOrderRequest = CreateOrderRequestObjectMother.aRandomCreateOrderRequest();
        UUID expectedCustomerId = createOrderRequest.customerId();
        ResultActions createResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(createOrderRequest))));

        createResult.andExpect(status().isCreated());
        String uuidAsAString = createResult.andReturn().getResponse().getContentAsString();
        orderId = jsonToObject(uuidAsAString, UUID.class);

        ResultActions getByIdResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/" + orderId));

        getByIdResult.andExpect(status().isOk());
        String orderDTOAsAString = getByIdResult.andReturn().getResponse().getContentAsString();
        OrderDTO orderDTO = jsonToObject(orderDTOAsAString, OrderDTO.class);

        UUID actualCustomerId = orderDTO.customerId();

        assertEquals(expectedCustomerId, actualCustomerId);
        UUID actualId = orderDTO.id();
        assertNotNull(actualId);
        String expectedCreatedAt = "PENDING";
        String expectedStatus = orderDTO.status();
        assertEquals(expectedCreatedAt, expectedStatus);
        assertTrue(orderDTO.createdAt().isBefore(LocalDateTime.now()));
        assertTrue(orderDTO.createdAt().isAfter(LocalDateTime.now().minusSeconds(10)));
        assertEquals(createOrderRequest.items().size(), orderDTO.items().size());
        for(int i = 0; i < createOrderRequest.items().size(); i++) {
            assertEquals(createOrderRequest.items().get(i).productId(), orderDTO.items().get(i).productId());
            assertEquals(createOrderRequest.items().get(i).quantity(), orderDTO.items().get(i).quantity());
            assertEquals(
                    createOrderRequest.items().get(i).unitPrice().setScale(2, RoundingMode.HALF_UP),
                    orderDTO.items().get(i).unitPrice().setScale(2, RoundingMode.HALF_UP));
        }

        double expectedTotalAmount = createOrderRequest.items().stream()
                .mapToDouble(item -> item.unitPrice().doubleValue() * item.quantity())
                .sum();
        expectedTotalAmount = BigDecimal.valueOf(expectedTotalAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double actualTotalAmount = orderDTO.totalAmount();
        assertEquals(expectedTotalAmount, actualTotalAmount);

        ResultActions getAllOrdersResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH));

        getAllOrdersResult.andExpect(status().isOk());
        String allOrdersAsAString = getAllOrdersResult.andReturn().getResponse().getContentAsString();
        List<OrderDTO> allOrders = jsonToObject(allOrdersAsAString, List.class);

        assertEquals(1, allOrders.size());
    }

    @Test
    void itShouldCreateAndDeleteANewOrder() throws Exception {
        CreateOrderRequest createOrderRequest = CreateOrderRequestObjectMother.aRandomCreateOrderRequest();
        ResultActions createResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(createOrderRequest))));
        createResult.andExpect(status().isCreated());
        String uuidAsAString = createResult.andReturn().getResponse().getContentAsString();
        orderId = jsonToObject(uuidAsAString, UUID.class);
        
        ResultActions deleteResult = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_PATH + "/" + orderId));
        deleteResult.andExpect(status().isNoContent());

        ResultActions getByIdResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/" + orderId));
        getByIdResult.andExpect(status().isNotFound());

        ResultActions getAllOrdersResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH));
        getAllOrdersResult.andExpect(status().isOk());
        String allOrdersAsAString = getAllOrdersResult.andReturn().getResponse().getContentAsString();
        List<OrderDTO> allOrders = jsonToObject(allOrdersAsAString, List.class);
        assertEquals(0, allOrders.size());
    }

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to JSON");
            return null;
        }
    }

    private <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            fail("Failed to convert JSON to object");
            return null;
        }
    }
}
