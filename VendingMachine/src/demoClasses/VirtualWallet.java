package demoClasses;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VirtualWallet extends javax.swing.JFrame {
	
	private DemoCashReceiver cashReceiver;
	private DemoCardScanner cardScanner;
	
	public VirtualWallet(DemoCashReceiver cashReceiver, DemoCardScanner cardScanner) {
		this.cashReceiver = cashReceiver;
		this.cardScanner = cardScanner;
		initComponents();
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Wallet");
        setAutoRequestFocus(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
		setResizable(false);
	}
	
	private void initComponents() {
        walletPanel = new JPanel();
        cashPanel = new JPanel();
        cashButton1p = new JButton();
        cashButton2p = new JButton();
        cashButton5p = new JButton();
        cashButton10p = new JButton();
        cashButton20p = new JButton();
        filler1 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        cashButton50p = new JButton();
        cashButton1pound = new JButton();
        cashButton2pound = new JButton();
        filler2 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        filler3 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        cashButton5pound = new JButton();
        cashButton10pound = new JButton();
        cashButton20pound = new JButton();
        filler7 = new Box.Filler(new Dimension(0, 25), new Dimension(0, 25), new Dimension(32767, 50));
        fakeCashPanel = new JPanel();
        fakeCashButton1p = new JButton();
        fakeCashButton2p = new JButton();
        fakeCashButton5p = new JButton();
        fakeCashButton10p = new JButton();
        fakeCashButton20p = new JButton();
        filler4 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        fakeCashButton50p = new JButton();
        cashButton1dollar = new JButton();
        cashButton2euro = new JButton();
        filler5 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        filler6 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        cashButton5dollar = new JButton();
        cashButton10yen = new JButton();
        fakeCashButton20pound = new JButton();;
        legitimateCardPanel = new JPanel();
        loyaltyCard1Button = new JButton();
        loyaltyCard2Button = new JButton();
        filler8 = new Box.Filler(new Dimension(0, 50), new Dimension(0, 25), new Dimension(32767, 50));
        filler9 = new Box.Filler(new Dimension(0, 50), new Dimension(0, 25), new Dimension(32767, 50));
        unrecognisedCardPanel = new JPanel();
        unrecognisedCard1Button = new JButton();
        unrecognisedCard2Button = new JButton();

        walletPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        walletPanel.setLayout(new javax.swing.BoxLayout(walletPanel, javax.swing.BoxLayout.PAGE_AXIS));

        cashPanel.setBorder(BorderFactory.createTitledBorder("Cash"));
        cashPanel.setLayout(new GridLayout(3, 0, 10, 10));

        cashButton1p.setText("1p");
        cashButton1p.addActionListener(cashReceiver);
        cashPanel.add(cashButton1p);

        cashButton2p.setText("2p");
        cashButton2p.addActionListener(cashReceiver);
        cashPanel.add(cashButton2p);

        cashButton5p.setText("5p");
        cashButton5p.addActionListener(cashReceiver);
        cashPanel.add(cashButton5p);

        cashButton10p.setText("10p");
        cashButton10p.addActionListener(cashReceiver);
        cashPanel.add(cashButton10p);

        cashButton20p.setText("20p");
        cashButton20p.addActionListener(cashReceiver);
        cashPanel.add(cashButton20p);
        
        cashPanel.add(filler1);

        cashButton50p.setText("50p");
        cashButton50p.addActionListener(cashReceiver);
        cashPanel.add(cashButton50p);

        cashButton1pound.setText("£1");
        cashButton1pound.addActionListener(cashReceiver);
        cashPanel.add(cashButton1pound);

        cashButton2pound.setText("£2");
        cashButton2pound.addActionListener(cashReceiver);
        cashPanel.add(cashButton2pound);
        
        cashPanel.add(filler2);
        cashPanel.add(filler3);

        cashButton5pound.setText("£5");
        cashButton5pound.addActionListener(cashReceiver);
        cashPanel.add(cashButton5pound);

        cashButton10pound.setText("£10");
        cashButton10pound.addActionListener(cashReceiver);
        cashPanel.add(cashButton10pound);

        cashButton20pound.setText("£20");
        cashButton20pound.addActionListener(cashReceiver);
        cashPanel.add(cashButton20pound);

        walletPanel.add(cashPanel);
        walletPanel.add(filler7);

        fakeCashPanel.setBorder(BorderFactory.createTitledBorder("Fake/Foreign Cash"));
        fakeCashPanel.setLayout(new GridLayout(3, 0, 10, 10));

        fakeCashButton1p.setText("1pee");
        fakeCashButton1p.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton1p);

        fakeCashButton2p.setText("2c");
        fakeCashButton2p.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton2p);

        fakeCashButton5p.setText("5d");
        fakeCashButton5p.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton5p);

        fakeCashButton10p.setText("ten");
        fakeCashButton10p.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton10p);

        fakeCashButton20p.setText("2-0p");
        fakeCashButton20p.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton20p);
        
        fakeCashPanel.add(filler4);

        fakeCashButton50p.setText("50");
        fakeCashButton50p.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton50p);

        cashButton1dollar.setText("$1");
        cashButton1dollar.addActionListener(cashReceiver);
        fakeCashPanel.add(cashButton1dollar);

        cashButton2euro.setText("€2");
        cashButton2euro.addActionListener(cashReceiver);
        fakeCashPanel.add(cashButton2euro);
        
        fakeCashPanel.add(filler5);
        fakeCashPanel.add(filler6);

        cashButton5dollar.setText("$5");
        cashButton5dollar.addActionListener(cashReceiver);
        fakeCashPanel.add(cashButton5dollar);

        cashButton10yen.setText("¥10");
        cashButton10yen.addActionListener(cashReceiver);
        fakeCashPanel.add(cashButton10yen);

        fakeCashButton20pound.setText("£2Ø");
        fakeCashButton20pound.addActionListener(cashReceiver);
        fakeCashPanel.add(fakeCashButton20pound);

        walletPanel.add(fakeCashPanel);

        GroupLayout walletDialogLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(walletDialogLayout);
        walletDialogLayout.setHorizontalGroup(
            walletDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(walletPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        walletDialogLayout.setVerticalGroup(
            walletDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(walletPanel, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        legitimateCardPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Legitimate Cards"), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        legitimateCardPanel.setLayout(new GridLayout(1, 0, 20, 0));

        loyaltyCard1Button.setText("Loyalty Card - 16489456");
        loyaltyCard1Button.setActionCommand("card1");
        loyaltyCard1Button.addActionListener(cardScanner);
        legitimateCardPanel.add(loyaltyCard1Button);

        loyaltyCard2Button.setText("Loyalty Card - 16485632");
        loyaltyCard2Button.setActionCommand("card2");
        loyaltyCard2Button.addActionListener(cardScanner);
        legitimateCardPanel.add(loyaltyCard2Button);

        walletPanel.add(filler8);
        walletPanel.add(legitimateCardPanel);
        walletPanel.add(filler9);

        unrecognisedCardPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Unrecognised Cards"), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        unrecognisedCardPanel.setLayout(new GridLayout(1, 0, 20, 0));

        unrecognisedCard1Button.setText("Loyalty Card - 17452957");
        unrecognisedCard1Button.setActionCommand("card3");
        unrecognisedCard1Button.addActionListener(cardScanner);
        unrecognisedCardPanel.add(unrecognisedCard1Button);

        unrecognisedCard2Button.setText("Loyalty Card - 15930563");
        unrecognisedCard2Button.setActionCommand("card4");
        unrecognisedCard2Button.addActionListener(cardScanner);
        unrecognisedCardPanel.add(unrecognisedCard2Button);

        walletPanel.add(unrecognisedCardPanel);
	}
	
	private JButton cashButton10p;
    private JButton fakeCashButton10p;
    private JButton cashButton10pound;
    private JButton cashButton10yen;
    private JButton cashButton1p;
    private JButton fakeCashButton1p;
    private JButton cashButton1pound;
    private JButton cashButton1dollar;
    private JButton cashButton20p;
    private JButton fakeCashButton20p;
    private JButton cashButton20pound;
    private JButton fakeCashButton20pound;
    private JButton cashButton2p;
    private JButton fakeCashButton2p;
    private JButton cashButton2pound;
    private JButton cashButton2euro;
    private JButton cashButton50p;
    private JButton fakeCashButton50p;
    private JButton cashButton5p;
    private JButton fakeCashButton5p;
    private JButton cashButton5pound;
    private JButton cashButton5dollar;
    private JPanel cashPanel;
    private JPanel fakeCashPanel;
    private Box.Filler filler1;
    private Box.Filler filler2;
    private Box.Filler filler3;
    private Box.Filler filler4;
    private Box.Filler filler5;
    private Box.Filler filler6;
    private Box.Filler filler7;
    private Box.Filler filler8;
    private Box.Filler filler9;
    private JPanel legitimateCardPanel;
    private JButton loyaltyCard1Button;
    private JButton loyaltyCard2Button;
    private JButton unrecognisedCard1Button;
    private JButton unrecognisedCard2Button;
    private JPanel unrecognisedCardPanel;
    private JPanel walletPanel;

}
