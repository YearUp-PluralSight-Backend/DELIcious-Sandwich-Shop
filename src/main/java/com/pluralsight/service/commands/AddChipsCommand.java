package com.pluralsight.service.commands;

import com.pluralsight.entity.Food;
import com.pluralsight.entity.Order;
import com.pluralsight.entity.otherfood.ChipBrand;
import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.sandwich.types.Size;
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
        initializedChipsAttributes();

    }

    /**
     * Initializes the attributes of the chips.
     */
    private void initializedChipsAttributes() {
        ChipBrand chipBrand = null;
        while (chipBrand == null) {
            try {
                Utility.print.accept(ConstantValue.CHIPS_MENU);
                int option = Utility.getInputAsIntegerWithPrompt("->");
                chipBrand = ChipBrand.getByMenuOption(option);
            } catch (IllegalArgumentException e) {
                logger.error("Error fetching chip brand: ", e);
            }
        }
        chips.setName(chipBrand.getBrandName());
        chips.setPrice(1.5);
        chips.setSize(Size.SMALL);
        chips.setCalories(130);
    }

    /**
     * Executes the command to add chips to the order.
     */
    @Override
    public void execute() {
        order.getCart().add(chips);
        String message = String.format("\nChips added to your order (Order Number: %d)\nName: %s\nPrice: $%.2f\nCalories: %.2f",
                order.getOrderNumber(), chips.getName(), chips.getPrice(), chips.getCalories());
        logger.info(message);
    }
}