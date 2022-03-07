package demoClasses;

import hardwareComponents.StockScanner;

public class DemoStockScanner implements StockScanner {
	
	private int stock;
	
	public DemoStockScanner(int initialStock) {
		this.stock = initialStock;
	}
	
	@Override
	public boolean checkAvailability() {
		return stock > 0;
	}
	
	public int getStock() {
		return stock;
	}
	
	protected void setStock(int stock) {
		this.stock = stock;
	}

}
