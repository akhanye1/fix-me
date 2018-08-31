package fixmecore.types;

import lombok.Getter;
import lombok.Setter;

public class InstrumentObject
{
	protected Long id;
	private int order_price;
	private int order_quantity;
	private String instrument_name;
	private static Long idCounter = 0L;

	public InstrumentObject(String instrumentName, int quantity, int price)
	{
		this.id = nextId();
		this.instrument_name = instrumentName;
		this.order_price = price;
		this.order_quantity = quantity;
	}

	private Long nextId()
	{
		return(++idCounter);
	}

	 public Long getId() {
        return id;
    }

    public int getPrice() {
        return order_price;
    }

    public void setPrice(int price) {
        this.order_price = price;
    }

    public int getQuantity() {
        return order_quantity;
    }

    public void setQuantity(int quantity) {
        this.order_quantity = quantity;
    }

    public String getName() {
        return instrument_name;
    }	
};
