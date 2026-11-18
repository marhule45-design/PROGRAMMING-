package mattresssales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;

    public ProductApp() {

        setTitle("Product App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));

        // Customer Name
        JLabel lblCustomerName = new JLabel("Customer Name:");
        txtCustomerName = new JTextField();

        // Product selection
        JLabel lblProduct = new JLabel("Select Product:");

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        cmbProduct = new JComboBox<>(products);

        // Product Price
        JLabel lblPrice = new JLabel("Enter Price:");
        txtPrice = new JTextField();

        // Process button
        btnProcess = new JButton("PROCESS");

        panel.add(lblCustomerName);
        panel.add(txtCustomerName);

        panel.add(lblProduct);
        panel.add(cmbProduct);

        panel.add(lblPrice);
        panel.add(txtPrice);

        panel.add(new JLabel(""));
        panel.add(btnProcess);

        add(panel);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem editClearItem = new JMenuItem("Clear");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem toolsClearItem = new JMenuItem("Clear");

        fileMenu.add(exitItem);
        editMenu.add(editClearItem);
        toolsMenu.add(processItem);
        toolsMenu.add(toolsClearItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        // Button actions
        btnProcess.addActionListener(e -> processSale());
        processItem.addActionListener(e -> processSale());

        editClearItem.addActionListener(e -> clearFields());
        toolsClearItem.addActionListener(e -> clearFields());

        exitItem.addActionListener(e -> System.exit(0));
    }

    // Process customer sale
    private void processSale() {

        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cmbProduct.getSelectedItem();
        String priceText = txtPrice.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter the customer name and product price."
            );
            return;
        }

        double price;

        try {
            price = Double.parseDouble(priceText);

            if (price <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a price greater than zero."
                );
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric price."
            );
            return;
        }

        // Calculate VAT, commission and total
        ProductCalculations calculations =
                new ProductCalculations(price);

        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        // Display results
        String message =
                "Customer Name: " + customerName +
                "\nProduct: " + productName +
                "\nVAT: R " + String.format("%.2f", vat) +
                "\nCOMMISSION: R " + String.format("%.2f", commission) +
                "\nTOTAL: R " + String.format("%.2f", total);

        JOptionPane.showMessageDialog(
            this,
            message,
            "Sales Information",
            JOptionPane.INFORMATION_MESSAGE
        );

        // Save information to data.txt
        saveToFile(customerName, productName,
                   vat, commission, total);
    }

    // Save sales information
    private void saveToFile(String customerName,
                            String productName,
                            double vat,
                            double commission,
                            double total) {

        try (PrintWriter writer =
             new PrintWriter(new FileWriter("data.txt", true))) {

            writer.println("********************************");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + productName);
            writer.println("VAT: R " + String.format("%.2f", vat));
            writer.println("COMMISSION: R " +
                           String.format("%.2f", commission));
            writer.println("TOTAL: R " +
                           String.format("%.2f", total));
            writer.println("********************************");

            JOptionPane.showMessageDialog(
                this,
                "Sales information saved successfully to data.txt"
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Error saving data: " + ex.getMessage()
            );
        }
    }

    // Clear all input fields
    private void clearFields() {

        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);

    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ProductApp().setVisible(true);
        });

    }
}
