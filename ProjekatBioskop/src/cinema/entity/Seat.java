package cinema.entity;

public class Seat {
	
	private Integer number;
	private Theater theater;
	
		
	public Seat() {
		
	}


	public Seat(Integer number, Theater theater) {
		
		this.number = number;
		this.theater = theater;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public Theater getTheater() {
		return theater;
	}


	public void setTheater(Theater theater) {
		this.theater = theater;
	}
	
	
	
	
	

}
