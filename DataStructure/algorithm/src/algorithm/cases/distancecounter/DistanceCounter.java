package algorithm.cases.distancecounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//https://www.codechef.com/problems/CCONNECT
public class DistanceCounter {

	public int[] solution(int[] T) {
		int capital = selectCapital(T);
		Map<Integer, Integer> report = new HashMap<Integer, Integer>();
		Map<Integer, List<Integer>> cityConnections = new HashMap<Integer, List<Integer>>();

		createConnectionsByValue(T, capital, cityConnections);
		Map<Integer, Integer> citiesPerDistance = calculateDistanceTable(report, cityConnections, capital, 0);
		
		return distanceTableToReport(citiesPerDistance, T.length-1);
    }

	private int[] distanceTableToReport(Map<Integer, Integer> citiesPerDistance, int numberOfRoads) {
		int[] report = new int[numberOfRoads];
		for(Entry<Integer, Integer> distanceAndNumberOfCities : citiesPerDistance.entrySet()) {
			report[distanceAndNumberOfCities.getKey()] = distanceAndNumberOfCities.getValue();
		}
		return report;
	}

	private void createConnectionsByValue(int[] T, int capital, Map<Integer, List<Integer>> cityConnections) {
		for(int i = 0; i < T.length; i++) {
			if(isCapital(capital, i)) {
				continue;
			}
			
			List<Integer> cities = cityConnections.getOrDefault(T[i], new ArrayList<Integer>());
			cities.add(i);
			cityConnections.put(T[i], cities);
		}
	}

	private Map<Integer, Integer> calculateDistanceTable(Map<Integer, Integer> citiesPerDistance, Map<Integer, List<Integer>> cityConnections, int currentCity, int distance) {
		List<Integer> connectedCities = cityConnections.get(currentCity);
		Integer numberOfCities = citiesPerDistance.getOrDefault(distance, 0);
		
		for(Integer city : connectedCities) {
			numberOfCities++;
			if(cityConnections.containsKey(city)) {
				calculateDistanceTable(citiesPerDistance, cityConnections, city, distance+1);
			}
		}
		citiesPerDistance.put(distance, numberOfCities);
		
		return citiesPerDistance;
	}

	private boolean isCapital(int capital, int city) {
		return capital == city;
	}
	
	private int selectCapital(int[] t) {
		for(int i = 0; i < t.length; i++) {
			if(i == t[i]) {
				return i;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] T = new int[10];
		T[0] = 9; T[1] = 1; T[2] = 4;
		T[3] = 9; T[4] = 0; T[5] = 4;
		T[6] = 8; T[7] = 9; T[8] = 0;
		T[9] = 1;
		
		int[] solution = new DistanceCounter().solution(T);
		
		System.out.println("Result: " + Arrays.toString(solution));
	}
}
