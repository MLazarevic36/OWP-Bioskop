package cinema.entity;

import java.util.Date;

public class SingleTicket {
	
	private String movieTitle;
	private Date dateAndTimeOfProjection;
	private String dateOutputTimeOfProjection;
	private Integer seat;
	private String theater;
	private String projectionType;
	private double price;
	private String buyer;
	private Integer projectionId;
	
	
	public SingleTicket() {
		
	}

	public SingleTicket(String movieTitle, Date dateAndTimeOfProjection, String dateOutputTimeOfProjection,
			Integer seat, String theater, String projectionType, double price, String buyer, Integer projectionId) {
		
		this.movieTitle = movieTitle;
		this.dateAndTimeOfProjection = dateAndTimeOfProjection;
		this.dateOutputTimeOfProjection = dateOutputTimeOfProjection;
		this.seat = seat;
		this.theater = theater;
		this.projectionType = projectionType;
		this.price = price;
		this.buyer = buyer;
		this.projectionId = projectionId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Date getDateAndTimeOfProjection() {
		return dateAndTimeOfProjection;
	}

	public void setDateAndTimeOfProjection(Date dateAndTimeOfProjection) {
		this.dateAndTimeOfProjection = dateAndTimeOfProjection;
	}

	public String getDateOutputTimeOfProjection() {
		return dateOutputTimeOfProjection;
	}

	public void setDateOutputTimeOfProjection(String dateOutputTimeOfProjection) {
		this.dateOutputTimeOfProjection = dateOutputTimeOfProjection;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getProjectionType() {
		return projectionType;
	}

	public void setProjectionType(String projectionType) {
		this.projectionType = projectionType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public Integer getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(Integer projectionId) {
		this.projectionId = projectionId;
	}
	
	
	
	
	
	
}
