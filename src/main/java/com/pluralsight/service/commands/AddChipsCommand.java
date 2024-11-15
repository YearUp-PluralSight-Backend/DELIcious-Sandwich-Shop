package com.pluralsight.service.commands;

import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.ChipBrand;
import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.sandwich.Size;
import com.pluralsight.service.Command;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to add chips to an order.
 */
public class AddChipsCommand implements Command {

    private final Order order;
    private final Chips chips = new Chips();
    private final Logger logger = LogManager.getLogger(AddChipsCommand.class);

    /**
     * Constructor to create an AddChipsCommand.
     *
     * @param order the order to which chips will be added
     */
    public AddChipsCommand(Order order) {
        this.order = order;
        initializeChipsAttributes();
    }

    /**
     * Executes the command to add chips to the order.
     */
    @Override
    public void execute() {
        order.getCart().add(chips);
        Utility.println.accept(chips.getName() + " added to your order");
        Utility.println.accept(chips.toString());
        logger.info("{} added to your order (Order Number: {})",
                chips.getName(), order.getOrderNumber());
        order.setTotalCalories(order.getTotalCalories());
        order.setTotalPrice(order.getTotalPrice());
    }

    /**
     * Initializes the attributes of the chips.
     */
    private void initializeChipsAttributes() {
        ChipBrand chipBrand = null;
        while (chipBrand == null) {
            Utility.print.accept(ConstantValue.CHIPS_MENU);
            try {
                int option = Utility.getInputAndReturnIntegerWithPrompt("->");
                chipBrand = ChipBrand.getByMenuOption(option);
            } catch (IllegalArgumentException e) {
                Utility.println.accept("Invalid option. Please try again.");
                logger.error("Error fetching chip brand: ", e);
            }
        }
        chips.setName(chipBrand.getBrandName());
        chips.setPrice(1.5);
        chips.setSize(Size.SMALL);
        chips.setCalories(130);
    }

}