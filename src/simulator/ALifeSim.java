package simulator;

import java.util.*;
import java.io.*;
/**
 * The ALifeSim class provides the main driver for the microbiome.
 * @author Dat Dinh
 * @version Version 1
 * Assignment 6
 * Due Date: 27/11/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class ALifeSim {
	
	// configuration file
	private static final String CONFIG_FILE = "aLifeSim.txt";
	// FileNotFoundException message
	private static final String FILE_NOT_FOUND = "File not found!";
	// IOException message
	private static final String IO_ILLEGAL = "Illegal config --correct one includes: "
			+ "\"Iteration\", \"Cooperator\", \"PartialCooperator\", and \"Defector\". Iteration must be greater than 0";
	// NumberFormatException message
	private static final String NUMBER_ILLEGAL = "Pure integer value goes after each category!";
	// Map storing information about population at t = 0
	private Map<String, Integer> initialCounts;
	// arbitrary time units
	private int iteration;
	/**
	 * read config file, extract information about 3 phenotypes into the map 
	 * and information about the time units into "iteration".
	 */
	public ALifeSim()
	{
		// constants: Iteration + 3 phenotypes
		final String FIELD_1 = "Iteration";
		// 3 phenotypes are used with the type names of organisms to maintain name consistency
		final String FIELD_2 = new Cooperator().getType();
		final String FIELD_3 = new PartialCooperator().getType();
		final String FIELD_4 = new Defector().getType();
		// splits the category and the value
		final String SPLIT = " ";
		// initiates the Map
		initialCounts = new HashMap<>();
		// try - catch
		try (Scanner aLifeSimFile = new Scanner(new File(CONFIG_FILE)))
		{
			while (aLifeSimFile.hasNextLine())
			{
				String[] line = aLifeSimFile.nextLine().split(SPLIT);
				// get the categories
				String category = line[0];
				if (category.equals(FIELD_1))
					iteration = Integer.parseInt(line[1]);
				else if (category.equals(FIELD_2))
					initialCounts.put(FIELD_2, Integer.parseInt(line[1]));
				else if (category.equals(FIELD_3))
					initialCounts.put(FIELD_3, Integer.parseInt(line[1]));
				else if (category.equals(FIELD_4))
					initialCounts.put(FIELD_4, Integer.parseInt(line[1]));
				else
					// if the file maintains the name not specified in program
					throw new IOException(IO_ILLEGAL);
			}
			// make sure that map has to have both 3 phenotypes
			if (!initialCounts.containsKey(FIELD_2) || 
				    !initialCounts.containsKey(FIELD_3) || 
				    !initialCounts.containsKey(FIELD_4) ||
				    (iteration == 0)) {
				    throw new IOException(IO_ILLEGAL);
				}

		}
		catch (FileNotFoundException e)
		{
			System.err.println(FILE_NOT_FOUND);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.err.println(NUMBER_ILLEGAL);
			System.exit(0);
		}
		catch (NumberFormatException e)
		{
			System.err.println(NUMBER_ILLEGAL);
			System.exit(0);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}
	/**
	 * constructs the habitat for all organisms.
	 * simulates the time period: 100 time units
	 * get the counts after 100 time units
	 * shows the counts for each phenotype and average cooperation mean
	 */
	public void simulate()
	{
		// population
		Population bacteriaPool = new Population(initialCounts);
		// iterate through and update the pool
		for (int i = 0; i < iteration; i++)
			bacteriaPool.update();
		// get the Stats
		Map<String, Integer> counts = bacteriaPool.getPopulationCounts();
		// show the counts of each species
		showCount(counts);
		// report the mean coop prob
		System.out.printf("%nMean Cooperation Probability = %.3f%n", bacteriaPool.calculateCooperationMean());
	}
	/**
	 * takes in the Map, displays the contents inside as a nice format
	 * @param counts the Map
	 */
	private void showCount(Map<String, Integer> counts)
	{
		System.out.printf("After %d arbitrary time units:%n%n", iteration);
		// report each species
		for(Map.Entry<String, Integer> entry : counts.entrySet())
			System.out.printf("%ss = %d%n", entry.getKey(), entry.getValue());
	}
	/**
	 * main
	 * @param args program arguments = null
	 */
	public static void main(String[] args)
	{
		new ALifeSim().simulate();
	}

}
