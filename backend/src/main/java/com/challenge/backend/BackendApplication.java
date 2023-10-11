package com.challenge.backend;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.backend.dao.FarmRepository;
import com.challenge.backend.dao.MovementRepository;
import com.challenge.backend.model.Farm;
import com.challenge.backend.model.Movement;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private MovementRepository movementRepository;

	@Autowired
	private FarmRepository farmRepository;

	private List<String> farms;

	private Map<String, Integer> population;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		farms = new ArrayList<>();
		population = new HashMap<>();
		initalisePopulation();
		List<String> lines = readfile("data/movement.csv");
		loadMovements(lines);
	}

	private void initalisePopulation() {
		List<String> lines = readfile("data/population.csv");
		lines.forEach(line -> {
			String[] values = line.strip().split(",");
			population.put(values[1].substring(1, values[1].length() - 1), Integer.parseInt(values[2]));
		});
	}

	// private List<String> readData(String fileName) {
	// 	List<String> lines = new ArrayList<>();
	// 	try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	// 		String line = br.readLine();
	// 		while ((line = br.readLine()) != null) {
	// 			lines.add(line);
	// 		}
	// 	} catch (FileNotFoundException e) {
	// 		System.out.println("Exception encounterred. " + e.getMessage());
	// 	} catch (IOException e) {
	// 		System.out.println("Exception encounterred. " + e.getMessage());
	// 	}
	// 	return lines;
	// }

	private void loadMovements(List<String> lines) {
		if (!lines.isEmpty()) {
			lines.forEach(line -> {
				String[] values = line.strip().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				Farm origin = new Farm();
				origin.setAddress(values[4].substring(1, values[4].length() - 1));
				origin.setCity(values[5]);
				origin.setLatitude(Float.parseFloat(values[16]));
				origin.setLongitude(Float.parseFloat(values[17]));
				origin.setName(values[6]);
				origin.setPostalCode(values[7]);
				origin.setPremiseId(values[8]);
				origin.setState(values[9]);
				origin.setTotalAnimals(population.get(values[8]));

				Farm destination = new Farm();
				destination.setAddress(values[10].substring(1, values[10].length() - 1));
				destination.setCity(values[11]);
				destination.setLatitude(Float.parseFloat(values[18]));
				destination.setLongitude(Float.parseFloat(values[19]));
				destination.setName(values[12]);
				destination.setPostalCode(values[13]);
				destination.setPremiseId(values[14]);
				destination.setState(values[15]);
				destination.setTotalAnimals(population.get(values[14]));

				loadFarms(values, origin, destination);

				Movement movement = new Movement(Integer.parseInt(values[0]), values[1], values[2], values[3], origin,
						destination, Integer.parseInt(values[20]), values[21]);
				movementRepository.save(movement);
			});
		}
	}

	private void loadFarms(String[] values, Farm origin, Farm destination) {
		if (farms.size() == 0) {
			if (values[8] != values[14]) {
				farmRepository.save(destination);
				farms.add(values[14]);
			}
			farmRepository.save(origin);
			farms.add(values[8]);
		} else {
			if (!farms.contains(values[8])) {
				farmRepository.save(origin);
				farms.add(values[8]);
			}
			if (!farms.contains(values[14])) {
				farmRepository.save(destination);
				farms.add(values[14]);
			}
		}
	}

	private List<String> readfile(String fileName) {
		List<String> lines = new ArrayList<>();
		ClassLoader classLoader = BackendApplication.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		if (inputStream != null) {
			Scanner scanner = new Scanner(inputStream);
			String line = scanner.nextLine();
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				lines.add(line);
			}
			scanner.close();
		} else {
			System.out.println("File not found in resources: " + fileName);
		}
		return lines;
	}

}
