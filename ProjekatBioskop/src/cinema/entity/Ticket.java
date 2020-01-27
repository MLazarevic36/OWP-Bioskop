package cinema.entity;

import java.util.Date;

public class Ticket {

	private Integer id;
	private Integer projection;
	private Integer seat;
	private Date dateAndTimeOfPurchase;
	private Integer buyer;
	
	public Ticket() {
		
	}

	public Ticket(Integer id, Integer projection, Integer seat, Date dateAndTimeOfPurchase, Integer buyer) {
		
		this.id = id;
		this.projection = projection;
		this.seat = seat;
		this.dateAndTimeOfPurchase = dateAndTimeOfPurchase;
		this.buyer = buyer;
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
	
	
	
	
	
	
	
	
}
