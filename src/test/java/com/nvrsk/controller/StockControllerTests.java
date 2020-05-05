package com.nvrsk.controller;

import com.nvrsk.exception.StockNotFoundException;
import com.nvrsk.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StockControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    private static void assertException(Exception exception, Class<? extends Throwable> exceptionClass, String message) {
        assertThat(exception).isNotNull().isExactlyInstanceOf(exceptionClass);
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void testGetAllStocks() throws Exception {
        mockMvc.perform(get("/api/stocks"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[" +
                                "{\"id\":1,\"name\":\"Stock1.L\",\"currentPrice\":2.0,\"lastUpdate\":\"2020-05-01T22:56:04Z\"}," +
                                "{\"id\":2,\"name\":\"Stock2.N\",\"currentPrice\":1.92,\"lastUpdate\":\"2020-05-02T21:08:47Z\"}" +
                                "]",
                        true));
    }

    @Test
    void testGetStockById() throws Exception {
        mockMvc.perform(get("/api/stocks/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\":2,\"name\":\"Stock2.N\",\"currentPrice\":1.92,\"lastUpdate\":\"2020-05-02T21:08:47Z\"}",
                        true));
    }

    @Test
    void testGetIncorrectId() throws Exception {
        Exception exception = mockMvc.perform(get("/api/stocks/3"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResolvedException();
        assertException(exception, StockNotFoundException.class, "Stock with id 3 not found");
    }
}
