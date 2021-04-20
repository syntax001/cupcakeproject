package business.persistence;

import business.services.OrderFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CupcakeMapperTest {
    CupcakeMapper cupcakeMapper = new CupcakeMapper();
    OrderFacade orderFacade = new OrderFacade();

    @Test
    public void testGetPrices() {
        double[] prices = cupcakeMapper.getPrices(8, 3);
        assertEquals(9.00, prices[0]);
        assertEquals(6.00, prices[1]);
    }

    @Test
    public void testCalcPrices() {
        double[] prices = orderFacade.calcPrices(8, 2, 7);
        assertEquals(98.00, prices[0]);
        assertEquals(14.00, prices[1]);
    }
}
