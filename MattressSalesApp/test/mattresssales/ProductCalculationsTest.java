
package mattresssales;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductCalculationsTest {

    // UNIT TEST 1: Test VAT calculation
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {

        ProductCalculations product =
                new ProductCalculations(3500);

        assertEquals(525.0, product.GetVat(), 0.01);
    }

    // UNIT TEST 2: Test Commission calculation
    @Test
    public void GetCommission_WithValidData_ReturnsCorrectValue() {

        ProductCalculations product =
                new ProductCalculations(3500);

        assertEquals(175.0, product.GetCommission(), 0.01);
    }
}
