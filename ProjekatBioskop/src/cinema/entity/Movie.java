package cinema.entity;

import java.util.Comparator;

public class Movie {
	
	private Integer id;
	private String title;
	private String duration;
	private String distributor;
	private String originCountry;
	private Integer yearOfProduction;
	
	public Movie() {
		
	}

	public Movie(Integer id, String title, String duration, String distributor, String originCountry,
			Integer yearOfProduction) {
		
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.distributor = distributor;
		this.originCountry = originCountry;
		this.yearOfProduction = yearOfProduction;
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
	
	
	public static Comparator<Movie> yearOfProductionComparator = new Comparator<Movie>() {
		
		@Override
		public int compare(Movie m1, Movie m2) {
			System.out.println("yearOfProduction");
			return m1.getYearOfProduction().compareTo(m2.yearOfProduction);
		}
	};
	
	public static Comparator<Movie> titleComparator = new Comparator<Movie>() {
		
		@Override
		public int compare(Movie m1, Movie m2) {
			System.out.println("title");
			String title1 = m1.getTitle();
			String title2 = m2.getTitle();
			return title1.compareToIgnoreCase(title2);
		}
	};
	
	public static Comparator<Movie> distributorComparator = new Comparator<Movie>() {
		
		@Override
		public int compare(Movie m1, Movie m2) {
			System.out.println("distributor");
			String distributor1 = m1.getDistributor();
			String distributor2 = m2.getDistributor();
			return distributor1.compareToIgnoreCase(distributor2);
		}
	};
	
	public static Comparator<Movie> originCountryComparator = new Comparator<Movie>() {
		
		@Override
		public int compare(Movie m1, Movie m2) {
			System.out.println("originCountry");
			String originCountry1 = m1.getOriginCountry();
			String originCountry2 = m2.getOriginCountry();
			return originCountry1.compareToIgnoreCase(originCountry2);
		}
	};
	
	

}
