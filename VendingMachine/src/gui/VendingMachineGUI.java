package gui;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;

import vendingMachine.Product;
import vendingMachine.VendingMachineController;

/**
 * A Graphical User Interface for the vending machine.
 * Allows the user to select products and perform actions
 * through pressing buttons on the screen, therefore a
 * touchscreen or cursor is expected.
 * 
 * This GUI was produced using the NetBeans IDE GUI builder tool:
 * 
 * Apache, 2021. NetBeans IDE (12.6) [desktop app].
 * Available from: https://netbeans.apache.org/download/nb125/nb125.html
 *
 * @author Jack
 */
public class VendingMachineGUI extends javax.swing.JFrame {

	// Logic Attributes
	private VendingMachineController controller; // pointer to the controller to be an action listener
	
	private HashMap<ButtonModel, Product> buttonProduct; // links each button to its associated product
	
	public static final String PLACEHOLDER = "Insert Credit..."; // shown when in idle mode
	public static final String CARD_LOGIN_MESSAGE = "Account linked"; // shown when a card is successfully scanned
	
	/*
	 * 
	 * The code below was researched at the following resource:
	 * 
	 * daiscog, 2015. Java Currency Number format. [Online] 
	 * Available at: https://stackoverflow.com/questions/2379221/java-currency-number-format
	 * [Accessed 7 December 2021].
	 * 
	 * The resource was used to find how to format an int into a currency
	 * format. The code has been adapted to suit this specific application
	 * and is not a direct copy
	 */
	private final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
	
    /**
     * Creates new VendingMachineGUI
     */
    public VendingMachineGUI(VendingMachineController controller) {
        this.controller = controller;
    	initComponents(); // set up components
    	pack(); // resize according to content
    	updatePrices(); // update to current product prices
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // quit the app when GUI is closed
        setTitle("Edge Hill Vender");
    }

    /**
     * Sets up all components of the JFrame
     */
    private void initComponents() {
		// initialise all the components
        productSelector = new ButtonGroup();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        contentPanel = new JPanel();
        outputPanel = new JPanel();
        output = new JLabel();
        selectionPanel = new JPanel();
        cokePanel = new JPanel();
        cokeButton = new JToggleButton();
        cokeLabelPanel = new JPanel();
        cokeLabel = new JLabel();
        cokePrice = new JLabel();
        lemonadePanel = new JPanel();
        lemonadeButton = new JToggleButton();
        lemonadeLabelPanel = new JPanel();
        lemonadeLabel = new JLabel();
        lemonadePrice = new JLabel();
        tangoPanel = new JPanel();
        tangoButton = new JToggleButton();
        tangoLabelPanel = new JPanel();
        tangoLabel = new JLabel();
        tangoPrice = new JLabel();
        waterPanel = new JPanel();
        waterButton = new JToggleButton();
        waterLabelPanel = new JPanel();
        waterLabel = new JLabel();
        waterPrice = new JLabel();
        pepsiPanel = new JPanel();
        pepsiButton = new JToggleButton();
        pepsiLabelPanel = new JPanel();
        pepsiLabel = new JLabel();
        pepsiPrice = new JLabel();
        spritePanel = new JPanel();
        spriteButton = new JToggleButton();
        spriteLabelPanel = new JPanel();
        spriteLabel = new JLabel();
        spritePrice = new JLabel();
        jPanel3 = new JPanel();
        purchaseButton = new JButton();
        clearButton = new JButton();
        cancelButton = new JButton();
        buttonProduct = new HashMap<ButtonModel, Product>();

        // set up title label for the GUI
        jLabel1.setFont(new java.awt.Font("Kefa", 0, 48)); // NOI18N
        jLabel1.setText("Edge Hill Café");
        jLabel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // add padding
        jPanel1.add(jLabel1);

        // add title to the top of the frame
        getContentPane().add(jPanel1, BorderLayout.NORTH);
        
        // set up panel that will hold all content with padding from the edge
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout());

        // set up panel to go at the top of content that displays output
        outputPanel.setLayout(new BorderLayout());

        output.setFont(new java.awt.Font("Silom", 0, 36));
        output.setHorizontalAlignment(SwingConstants.CENTER); // justify text to centre
        output.setText(PLACEHOLDER); // use placeholder text initially
        output.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10), BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 204), new java.awt.Color(102, 0, 102))));
        outputPanel.add(output, BorderLayout.CENTER);

        // add output to the top of the panel
        contentPanel.add(outputPanel, BorderLayout.NORTH);

        // set up panel for selecting a drink
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Select a product"));
        selectionPanel.setLayout(new java.awt.GridLayout(2, 3, 10, 10)); // set up grid for drinks

        // set up panel for coke -> button, name and price
        cokePanel.setLayout(new BorderLayout());

        productSelector.add(cokeButton);
        cokeButton.setIcon(new ImageIcon("assets/coke.png")); // show coke can image on button
        cokeButton.setEnabled(false); // initially disabled
        cokePanel.add(cokeButton, BorderLayout.CENTER);

        cokeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cokeLabel.setText("Coke -");
        cokeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        cokeLabelPanel.add(cokeLabel);

        cokeLabelPanel.add(cokePrice);

        cokePanel.add(cokeLabelPanel, BorderLayout.SOUTH);

        selectionPanel.add(cokePanel);
        
        // set up panel for lemonade -> button, name and price
        lemonadePanel.setLayout(new BorderLayout());

        productSelector.add(lemonadeButton);
        lemonadeButton.setIcon(new ImageIcon("assets/lemonade.png")); // show lemonade can image on button
        lemonadeButton.setEnabled(false); // initially disabled
        lemonadePanel.add(lemonadeButton, BorderLayout.CENTER);

        lemonadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lemonadeLabel.setText("Lemonade -");
        lemonadeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lemonadeLabelPanel.add(lemonadeLabel);

        lemonadeLabelPanel.add(lemonadePrice);

        lemonadePanel.add(lemonadeLabelPanel, BorderLayout.SOUTH);

        selectionPanel.add(lemonadePanel);
        
        // set up panel for tango -> button, name and price
        tangoPanel.setLayout(new BorderLayout());

        productSelector.add(tangoButton);
        tangoButton.setIcon(new ImageIcon("assets/tango.png")); // show tango can image on button
        tangoButton.setEnabled(false); // initially disabled
        tangoPanel.add(tangoButton, BorderLayout.CENTER);

        tangoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tangoLabel.setText("Tango -");
        tangoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        tangoLabelPanel.add(tangoLabel);

        tangoLabelPanel.add(tangoPrice);

        tangoPanel.add(tangoLabelPanel, BorderLayout.SOUTH);

        selectionPanel.add(tangoPanel);
        
        // set up panel for water -> button, name and price
        waterPanel.setLayout(new BorderLayout());

        productSelector.add(waterButton);
        waterButton.setIcon(new ImageIcon("assets/water.png")); // show water can image on button.setEnabled(false);
        waterButton.setEnabled(false); // initially disabled
        waterPanel.add(waterButton, BorderLayout.CENTER);
        
        waterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        waterLabel.setText("Water -");
        waterLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        waterLabelPanel.add(waterLabel);

        waterLabelPanel.add(waterPrice);

        waterPanel.add(waterLabelPanel, BorderLayout.SOUTH);

        selectionPanel.add(waterPanel);
        
        // set up panel for pepsi -> button, name and price
        pepsiPanel.setLayout(new BorderLayout());

        productSelector.add(pepsiButton);
        pepsiButton.setIcon(new ImageIcon("assets/pepsi.png")); // show pepsi can image on button
        pepsiButton.setEnabled(false); // initially disabled
        pepsiPanel.add(pepsiButton, BorderLayout.CENTER);

        pepsiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pepsiLabel.setText("Pepsi -");
        pepsiLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        pepsiLabelPanel.add(pepsiLabel);

        pepsiLabelPanel.add(pepsiPrice);

        pepsiPanel.add(pepsiLabelPanel, BorderLayout.SOUTH);

        selectionPanel.add(pepsiPanel);
        
        // set up panel for sprite -> button, name and price
        spritePanel.setLayout(new BorderLayout());

        productSelector.add(spriteButton);
        spriteButton.setIcon(new ImageIcon("assets/sprite.png")); // show sprite can image on button
        spriteButton.setEnabled(false); // initially disabled
        spritePanel.add(spriteButton, BorderLayout.CENTER);

        spriteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spriteLabel.setText("Sprite -");
        spriteLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        spriteLabelPanel.add(spriteLabel);

        spriteLabelPanel.add(spritePrice);

        spritePanel.add(spriteLabelPanel, BorderLayout.SOUTH);

        selectionPanel.add(spritePanel);

        // add product selector to the content panel
        contentPanel.add(selectionPanel, BorderLayout.CENTER);
        selectionPanel.getAccessibleContext().setAccessibleDescription("");

        // set up buttons for purchase, clear and cancel
        purchaseButton.setText("Purchase");
        purchaseButton.addActionListener(controller);
        jPanel3.add(purchaseButton);

        clearButton.setText("Clear");
        clearButton.addActionListener(controller);
        jPanel3.add(clearButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(controller);
        jPanel3.add(cancelButton);

        // add action buttons to bottom of content
        contentPanel.add(jPanel3, BorderLayout.PAGE_END);

        // add content to frame
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        // link each button to its corresponding product
        buttonProduct.put(cokeButton.getModel(), Product.COKE);
        buttonProduct.put(lemonadeButton.getModel(), Product.LEMONADE);
        buttonProduct.put(tangoButton.getModel(), Product.TANGO);
        buttonProduct.put(waterButton.getModel(), Product.WATER);
        buttonProduct.put(pepsiButton.getModel(), Product.PEPSI);
        buttonProduct.put(spriteButton.getModel(), Product.SPRITE);
    }
    
    /**
     * Updates the prices of each product on the display
     */
    public void updatePrices() {
		
    	// retrieve up-to-date price from the controller
		cokePrice.setText(moneyFormatter.format(controller.getPrice(Product.COKE)));
		lemonadePrice.setText(moneyFormatter.format(controller.getPrice(Product.LEMONADE)));
		tangoPrice.setText(moneyFormatter.format(controller.getPrice(Product.TANGO)));
		waterPrice.setText(moneyFormatter.format(controller.getPrice(Product.WATER)));
		pepsiPrice.setText(moneyFormatter.format(controller.getPrice(Product.PEPSI)));
		spritePrice.setText(moneyFormatter.format(controller.getPrice(Product.SPRITE)));
    
    }
    
    /**
     * Formats a double into a currency format
     * with a symbol and 2 decimal places
     * 
     * @param amount the money amount to convert
     * @return a String of the money amount, e.g. £1.23
     */
    public String formatCurrency(double amount) {
    	return moneyFormatter.format(amount);
    }
    
    /**
     * Clears the selection such that no product
     * is selected
     */
    public void clearSelection() {
    	productSelector.clearSelection();
    }
    
    /**
     * @return the output label of the GUI
     */
    public JLabel getOutput() {
    	return output;
    }
    
    /**
     * @return the currently selected product
     */
    public Product getSelectedProduct() {
    	// retrieve product associated with the selected button in the buttonProduct HashMap
    	return buttonProduct.get(productSelector.getSelection());
    }

    /**
     * Allows the product selector to be enabled and
     * disabled from the controller
     * 
     * @param enabled true to enable the product selector, false otherwise
     */
    public void setSelectorEnabled(boolean enabled) {
    	Enumeration<AbstractButton> e = productSelector.getElements();
    	while(e.hasMoreElements()) {
    		e.nextElement().setEnabled(enabled);
   		}
    }
    
	// Component Attributes
    private JButton cancelButton;
    private JButton clearButton;
    private JToggleButton cokeButton;
    private JLabel cokeLabel;
    private JPanel cokeLabelPanel;
    private JPanel cokePanel;
    private JLabel cokePrice;
    private JPanel contentPanel;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JToggleButton lemonadeButton;
    private JLabel lemonadeLabel;
    private JPanel lemonadeLabelPanel;
    private JPanel lemonadePanel;
    private JLabel lemonadePrice;
    private JLabel output;
    private JPanel outputPanel;
    private JToggleButton pepsiButton;
    private JLabel pepsiLabel;
    private JPanel pepsiLabelPanel;
    private JPanel pepsiPanel;
    private JLabel pepsiPrice;
    private ButtonGroup productSelector;
    private JButton purchaseButton;
    private JPanel selectionPanel;
    private JToggleButton spriteButton;
    private JLabel spriteLabel;
    private JPanel spriteLabelPanel;
    private JPanel spritePanel;
    private JLabel spritePrice;
    private JToggleButton tangoButton;
    private JLabel tangoLabel;
    private JPanel tangoLabelPanel;
    private JPanel tangoPanel;
    private JLabel tangoPrice;
    private JToggleButton waterButton;
    private JLabel waterLabel;
    private JPanel waterLabelPanel;
    private JPanel waterPanel;
    private JLabel waterPrice;
}
