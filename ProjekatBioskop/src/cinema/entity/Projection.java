package cinema.entity;

import java.util.Date;

public class Projection {
	
	private Integer id;
	private Movie movie;
	private ProjectionType projectionType;
	private Theater theater;
	private Date dateAndTime;
	private double ticketPrice;
	private User adminCreator;
	
	public Projection() {
		
	}

	public Projection(Integer id, Movie movie, ProjectionType projectionType, Theater theater, Date dateAndTime,
			double ticketPrice, User adminCreator) {
		
		this.id = id;
		this.movie = movie;
		this.projectionType = projectionType;
		this.theater = theater;
		this.dateAndTime = dateAndTime;
		this.ticketPrice = ticketPrice;
		this.adminCreator = adminCreator;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ProjectionType getProjectionType() {
		return projectionType;
	}

	public void setProjectionType(ProjectionType projectionType) {
		this.projectionType = projectionType;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
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

	public User getAdminCreator() {
		return adminCreator;
	}

	public void setAdminCreator(User adminCreator) {
		this.adminCreator = adminCreator;
	}
	
	
	
	
	
	

}
