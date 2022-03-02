package hardwareComponents;

public class StockScanner {
	
	private int stock;
	
	public StockScanner(int initialStock) {
		this.stock = initialStock;
	}
	
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