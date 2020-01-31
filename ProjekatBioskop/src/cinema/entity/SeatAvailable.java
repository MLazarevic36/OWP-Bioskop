package cinema.entity;

public class SeatAvailable {
	
	Integer projection_id;
	Integer seat_id;
	Integer taken;
	
	public SeatAvailable() {
		
	}
	public SeatAvailable(Integer projection_id, Integer seat_id, Integer taken) {
		
		this.projection_id = projection_id;
		this.seat_id = seat_id;
		this.taken = taken;
	}
	public Integer getProjection_id() {
		return projection_id;
	}
	public void setProjection_id(Integer projection_id) {
		this.projection_id = projection_id;
	}
	public Integer getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(Integer seat_id) {
		this.seat_id = seat_id;
	}
	public Integer getTaken() {
		return taken;
	}
	public void setTaken(Integer taken) {
		this.taken = taken;
	}
	
	

}
