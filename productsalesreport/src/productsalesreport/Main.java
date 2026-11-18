
package productsalesreport;

public class Main {

    public static void main(String[] args) {

        // Create Product object
        Product product = new Product();

        // Create report object
        PrintProduct report = new PrintProduct();

        // Display monthly sales
        product.displayMonthlySales();

        // Print sales summary
        report.printReport(product);

    }
}
