package CitiesDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static CitiesDictionary.CSVReader.*;

public class MainClass {
    public static void main(String[] args) {
        List<CityEntity> firstList = new ArrayList<>();
        firstList = parseList();
        Collections.sort(firstList, COMPARE_BY_NAME);
        //printList(firstList);

        List<CityEntity> secondList = new ArrayList<>();
        secondList = parseList();

        Collections.sort(secondList, compareByNameAndDistrict());

        printList(secondList);
    }
}