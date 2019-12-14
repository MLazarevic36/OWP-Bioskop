package cinema.entity;

import java.util.ArrayList;

public class Theater {
	
	private Integer id;
	private String name;
	private ArrayList<ProjectionType> projectionTypes;
	
	public Theater() {
		
	}
	
	public Theater(Integer id, String name, ArrayList<ProjectionType> projectionTypes) {
		
		this.id = id;
		this.name = name;
		this.projectionTypes = projectionTypes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ProjectionType> getProjectionTypes() {
		return projectionTypes;
	}

	public void setProjectionTypes(ArrayList<ProjectionType> projectionTypes) {
		this.projectionTypes = projectionTypes;
	}
	
	

	
	
	
	

}
