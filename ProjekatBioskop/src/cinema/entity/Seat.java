package cinema.entity;

public class Seat {
	
	private Integer id;
	private Integer number;
	private int theater;
	private int available;
	
		
	public Seat() {
		
	}


	public Seat(Integer id, Integer number, int theater, int available) {
		
		this.id = id;
		this.number = number;
		this.theater = theater;
		this.available = available;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public int getTheater() {
		return theater;
	}


	public void setTheater(int theater) {
		this.theater = theater;
	}
	
	public int getAvailable() {
		return available;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	

}
