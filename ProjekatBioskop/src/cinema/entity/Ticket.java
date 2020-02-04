package cinema.entity;

import java.util.Date;

public class Ticket {

	private Integer id;
	private Integer projection;
	private Integer seat;
	private Date dateAndTimeOfPurchase;
	private Integer buyer;
	private String dateOutput;
	private double price;
	private String buyerName;
	
	public Ticket() {
		
	}

	public Ticket(Integer id, Integer projection, Integer seat, Date dateAndTimeOfPurchase,
			Integer buyer, String dateOutput, double price, String buyerName) {
		
		this.id = id;
		this.projection = projection;
		this.seat = seat;
		this.dateAndTimeOfPurchase = dateAndTimeOfPurchase;
		this.buyer = buyer;
		this.dateOutput = dateOutput;
		this.price = price;
		this.buyerName = buyerName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjection() {
		return projection;
	}

	public void setProjection(Integer projection) {
		this.projection = projection;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Date getDateAndTimeOfPurchase() {
		return dateAndTimeOfPurchase;
	}

	public void setDateAndTimeOfPurchase(Date dateAndTimeOfPurchase) {
		this.dateAndTimeOfPurchase = dateAndTimeOfPurchase;
	}

	public Integer getBuyer() {
		return buyer;
	}

	public void setBuyer(Integer buyer) {
		this.buyer = buyer;
	}
	
	public String getDateOutput() {
		return dateOutput;
	}

	public void setDateOutput(String dateOutput) {
		this.dateOutput = dateOutput;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	
	
	
	
	
	
	
}
