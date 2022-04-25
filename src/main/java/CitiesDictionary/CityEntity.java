package CitiesDictionary;

public class CityEntity {
	
	//private int id;		removed parameter
	
	private String name;
	private String region;
	private String district;
	private int population; // another type (String-->int)
	private String foundation;
	
	// added constructor and removed getters and setters
	public CityEntity(String name, String region, String district, int population, String foundation) {
		this.name=name;
		this.region=region;
		this.district=district;
		this.population=population;
		this.foundation=foundation;
	}
	
	public String toString() {
		return "City{name='" +name+ 
				"', region='" +region+ 
				"', district='" +district+ 
				"', population=" +population+ 
				", foundation='" +foundation+ "'}";
	}

	public String getName(){
		return this.name;
	}

	public String getDistrict(){
		return this.district;
	}
	
}