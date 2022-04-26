package CitiesDictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// another methods from getting list of cities and added method for printing in console

public class CSVReader {
	
	public static List<CityEntity> parseList(){
		List<CityEntity> citiesList = new ArrayList<>();
		try {
			String filePath = "src/main/resources/city_ru.csv"; // maybe need another path to file
			File cityFile = new File(filePath);
			Scanner scanner = new Scanner(cityFile);
			
			while (scanner.hasNextLine()) {
				citiesList.add(parseCity(scanner.nextLine()));
			}
			scanner.close();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return citiesList;
	}
	
	private static CityEntity parseCity(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(";");
		scanner.skip("\\d*");
		
		String name = scanner.next();
		String region = scanner.next();
		String disctrict = scanner.next();
		int population = scanner.nextInt();
		
		String foundation;
		if (scanner.hasNext()) {
			foundation = scanner.next(); 
		} else {
			foundation = null;
		}
		
		scanner.close();
		
		return new CityEntity(name, region, disctrict, population, foundation);
	}

	public static void printList(List<CityEntity> citiesList) {
		citiesList.forEach(System.out::println);
	}

	// first part
	public static Comparator<CityEntity> COMPARE_BY_NAME = new Comparator<CityEntity>() {
		@Override
		public int compare(CityEntity o1, CityEntity o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	//second part. need to test this
	// maybe this better only for multiple parameters
	public static Comparator compareByNameAndDistrict(){
		return Comparator.comparing(CityEntity::getDistrict).thenComparing(CityEntity::getName);
	}
}