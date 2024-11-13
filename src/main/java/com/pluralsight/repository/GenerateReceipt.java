package com.pluralsight.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.entity.Order;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class GenerateReceipt {

    private static final Logger logger = LogManager.getLogger(GenerateReceipt.class);

    /**
     * Prints the receipt to the console.
     *
     * @param order the order object containing the receipt data
     */
    public static void printReceiptToConsole(Order order) {
        logger.info("Printing receipt to console for order number: {}", order.getOrderNumber());

        order.getCart().forEach(item -> {
            logger.debug("Cart item: {}", item);
            System.out.println(item);
        });

        logger.info("Receipt printed to console successfully.");
    }

    /**
     * Writes the receipt to a JSON file.
     *
     * @param order the order object to be written to the file
     * @return true if the file is written successfully, false otherwise
     */
    public static boolean writeReceiptToFile(Order order) {
        try {
            Path receiptsDir = Path.of("receipts");
            if (!Files.exists(receiptsDir)) {
                Files.createDirectories(receiptsDir);
                logger.info("Receipts directory created at: {}", receiptsDir);
            }

            String fileName = LocalDateTime.now().format(ConstantValue.DATE_TIME_FORMATTER) + ".json";
            Path filePath = receiptsDir.resolve(fileName);

            logger.info("Attempting to write receipt to file: {}", filePath);
            ObjectMapper jsonOrder = new ObjectMapper();
            String jsonContent = jsonOrder.writeValueAsString(order);
            Files.write(filePath, jsonContent.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            logger.info("Receipt written to file successfully: {}", filePath);
            return true;
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON format for order number: {}. Exception: ", order.getOrderNumber(), e);
        } catch (IOException e) {
            logger.error("Error writing JSON data to file for order number: {}. Exception: ", order.getOrderNumber(), e);
        }
        return false;
    }

}