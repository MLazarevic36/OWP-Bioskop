package cinema.entity;

import java.util.Date;

public class Ticket {

	private Integer id;
	private Projection projection;
	private Seat seat;
	private Date dateAndTimeOfPurchase;
	private User buyer;
	
	public Ticket() {
		
	}

	public Ticket(Integer id, Projection projection, Seat seat, Date dateAndTimeOfPurchase, User buyer) {
		
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

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Date getDateAndTimeOfPurchase() {
		return dateAndTimeOfPurchase;
	}

	public void setDateAndTimeOfPurchase(Date dateAndTimeOfPurchase) {
		this.dateAndTimeOfPurchase = dateAndTimeOfPurchase;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	
	
	
	
	
	
	
}
