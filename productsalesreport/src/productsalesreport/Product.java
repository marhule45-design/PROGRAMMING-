
package productsalesreport;

public class Product implements IProducts {

    private final String[] products;
    private final String[] months;
    private final int[][] sales;

    public Product() {

        products = new String[]{
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Plush"
        };

        months = new String[]{
            "January",
            "February",
            "March"
        };

        sales = new int[][]{
            {25, 10, 20},
            {15, 10, 12},
            {10, 15, 20}
        };
    }

    @Override
    public int GetTotalProductsSold() {

        int total = 0;

        for (int[] productSales : sales) {
            for (int quantity : productSales) {
                total += quantity;
            }
        }

        return total;
    }

    @Override
    public double GetAverageProductsSold() {

        int total = GetTotalProductsSold();

        int numberOfSales = products.length * months.length;

        return (double) total / numberOfSales;
    }

    @Override
    public String GetMostPopularProduct() {

        int highestTotal = 0;
        String mostPopular = "";

        for (int i = 0; i < products.length; i++) {

            int productTotal = 0;

            for (int j = 0; j < months.length; j++) {

                productTotal += sales[i][j];

            }

            if (productTotal > highestTotal) {

                highestTotal = productTotal;

                mostPopular = products[i];

            }
        }

        return mostPopular;
    }

    public void displayMonthlySales() {

        System.out.println("PRODUCT SALES REPORT");

        System.out.println("----------------------------------------");

        for (int i = 0; i < products.length; i++) {

            System.out.println("\nProduct: " + products[i]);

            for (int j = 0; j < months.length; j++) {

                System.out.println(
                    months[j] + ": " + sales[i][j]
                );

            }
        }
    }
}
