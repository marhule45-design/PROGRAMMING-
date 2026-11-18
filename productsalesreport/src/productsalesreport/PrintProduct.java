
package productsalesreport;

public class PrintProduct {

    public void printReport(Product product) {

        System.out.println("\nPRODUCT SALES SUMMARY");
        System.out.println("----------------------------------------");

        System.out.println(
            "Total Products Sold: "
            + product.GetTotalProductsSold()
        );

        System.out.printf(
            "Average Products Sold: %.2f%n",
            product.GetAverageProductsSold()
        );

        System.out.println(
            "Most Popular Product: "
            + product.GetMostPopularProduct()
        );

        System.out.println("----------------------------------------");
    }
}
