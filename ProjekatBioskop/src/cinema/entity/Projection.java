package cinema.entity;

import java.util.Date;

public class Projection {
	
	private Integer id;
	private String movie;
	private String projectionType;
	private String theater;
	private Date dateAndTime;
	private double ticketPrice;
	private Integer adminCreator;
	private String dateOutput;
	
	public Projection() {
		
	}

	public Projection(Integer id, String movie, String projectionType, String theater, Date dateAndTime,
			double ticketPrice, Integer adminCreator, String dateOutput) {
		
		this.id = id;
		this.movie = movie;
		this.projectionType = projectionType;
		this.theater = theater;
		this.dateAndTime = dateAndTime;
		this.ticketPrice = ticketPrice;
		this.adminCreator = adminCreator;
		this.dateOutput = dateOutput;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getProjectionType() {
		return projectionType;
	}

	public void setProjectionType(String projectionType) {
		this.projectionType = projectionType;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getAdminCreator() {
		return adminCreator;
	}

	public void setAdminCreator(Integer adminCreator) {
		this.adminCreator = adminCreator;
	}
	
	public String getDateOutput() {
		return dateOutput;
	}

	public void setDateOutput(String dateOutput) {
		this.dateOutput = dateOutput;
	}
	
	
	
	
	
	

}
