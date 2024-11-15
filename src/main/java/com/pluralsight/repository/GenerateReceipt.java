package com.pluralsight.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.pluralsight.entity.Order;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GenerateReceipt {

    private static final Logger logger = LogManager.getLogger(GenerateReceipt.class);

    /**
     * Prints the receipt to the console.
     *
     * @param order the order object containing the receipt data
     */
    public static void printReceiptToConsole(Order order) throws JsonProcessingException {
        logger.info("Printing receipt to console for order number: {}", order.getOrderNumber());
        Utility.print.accept(order.toString());



    }

    /**
     * Writes the receipt to a JSON file.
     *
     * @param order the order object to be written to the file
     * @return true if the file is written successfully, false otherwise
     */
    public static boolean writeReceiptToJsonFile(Order order) {
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
            jsonOrder.registerModule(new JavaTimeModule());  // Register module for LocalDateTime support
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

    /**
     * Writes the receipt to a Text file.
     *
     * @param order the order object to be written to the file
     * @return true if the file is written successfully, false otherwise
     */
    public static boolean writeReceiptToTextFile(Order order) {
        try {
            Path receiptsDir = Path.of("receipts");
            if (!Files.exists(receiptsDir)) {
                Files.createDirectories(receiptsDir);
                logger.info("Receipts directory created at: {}", receiptsDir);
            }

            String fileName = LocalDateTime.now().format(ConstantValue.DATE_TIME_FORMATTER) + ".txt";
            Path filePath = receiptsDir.resolve(fileName);

            logger.info("Attempting to write receipt to file: {}", filePath);

            Files.write(filePath, order.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

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