package hardwareComponents;

import java.math.BigDecimal;

public interface CashReceiver {

	public boolean charge(BigDecimal amount);
	
	public BigDecimal getCredit();
	
	public boolean isEmpty();
	
	public double eject();
}
