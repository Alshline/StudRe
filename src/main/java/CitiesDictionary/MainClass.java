package CitiesDictionary;

import java.text.MessageFormat;
import java.util.*;

import static CitiesDictionary.CSVReader.*;

public class MainClass {
    public static void main(String[] args) {
        List<CityEntity> firsList = parseList();

        divideByRegionUseLyambda(firsList);
    }

    private static void sortByNameUseLyambda(List<CityEntity> citiesList) {
        citiesList.sort((o1,o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }

    private static void sortByNameUseCompare(List<CityEntity> citiesList) {
        citiesList.sort(new Comparator<CityEntity>() {
            @Override
            public int compare(CityEntity o1, CityEntity o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    private static void sortByDistrictAndName(List<CityEntity> cities) {
        cities.sort(Comparator.comparing(CityEntity::getDistrict).thenComparing(CityEntity::getName));
    }

    private static void sortByRegion(List<CityEntity> citiesList){
        citiesList.sort(new Comparator<CityEntity>() {
            @Override
            public int compare(CityEntity o1, CityEntity o2) {
                return o1.getRegion().compareToIgnoreCase(o2.getRegion());
            }
        });
    }

    private static void findByBruteMethod(List<CityEntity> cityList) {

        CityEntity[] citiesArray = getArrayOfCities(cityList);

        int bufferedValue=0;
        int index=0;

        for (int i = 0; i < citiesArray.length; i++) {
            int currentValue = citiesArray[i].getPopulation();
            if (bufferedValue<currentValue) {
                bufferedValue=currentValue;
                index=i;
            }
        }

        System.out.println("[" +index+ "] = " +bufferedValue);
    }

    private static void findByInsertionMethod(List<CityEntity> cityList) {
        CityEntity[] citiesArray = getArrayOfCities(cityList);

        for (int i=0; i<citiesArray.length;i++) {
            CityEntity currentEntity = citiesArray[i];
            int j =i-1;

            while(j>=0 && currentEntity.getPopulation() < citiesArray[j].getPopulation()) {
                citiesArray[j+1]=citiesArray[j];
                j--;
            }

            citiesArray[j+1] = currentEntity;
        }

        System.out.println(MessageFormat.format("[{0}] = {1}", citiesArray.length - 1, citiesArray[citiesArray.length - 1]));
    }

    private static void findByLyambdaMethod(List<CityEntity> cityList) {
        System.out.println(cityList.stream().max(Comparator.comparing(CityEntity::getPopulation)));
    }

    private static void divideByRegion(List<CityEntity> cityList){

        Map<String,Integer> mapOfRegions = new HashMap<>();

        for (int i=0; i< cityList.size();i++){

            String nameOfRegion = cityList.get(i).getRegion();

            if (!mapOfRegions.containsKey(nameOfRegion)){
                mapOfRegions.put(nameOfRegion,1);
            } else {
                mapOfRegions.put(nameOfRegion, mapOfRegions.get(nameOfRegion)+1);
            }
        }

        for (String key : mapOfRegions.keySet()) {
            System.out.println(MessageFormat.format("{0}={1}",key,mapOfRegions.get(key)));
        }
    }

    private static void divideByRegionUseLyambda(List<CityEntity> cityList){
        Map<String,Integer> mapOfRegions = new HashMap<>();
        cityList.forEach(cityEntity -> mapOfRegions.merge(cityEntity.getRegion(),1,Integer::sum));
        mapOfRegions.forEach((key,value) -> System.out.println(MessageFormat.format("{0}={1}",key,value)));
    }

    private static CityEntity[] getArrayOfCities(List<CityEntity> cityList) {
        CityEntity[] citiesArray = new CityEntity[cityList.size()];
        cityList.toArray(citiesArray);
        return citiesArray;
    }
}