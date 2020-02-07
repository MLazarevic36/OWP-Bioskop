package cinema.entity;


public class Movie {
	
	private Integer id;
	private String title;
	private String duration;
	private String distributor;
	private String originCountry;
	private Integer yearOfProduction;
	private Integer deleted;
	
	public Movie() {
		
	}

	public Movie(Integer id, String title, String duration, String distributor, String originCountry,
			Integer yearOfProduction, Integer deleted) {
		
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.distributor = distributor;
		this.originCountry = originCountry;
		this.yearOfProduction = yearOfProduction;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public Integer getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
	
}
