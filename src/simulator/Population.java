package simulator;

import java.util.*;
/**
 * The Population class provides the habitat for all individuals.
 * @author Dat Dinh
 * @version Version 1
 * Assignment 6
 * Due Date: 27/11/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class Population {
	// species specified in the Map does not exist --> IllegalArgumentException message
	private static final String NO_SUCH_SPECIES = "Species does not exist! --only \"Cooperator\", "
			+ "\"Defector\", or \"PartialCooperator\"!";
	// Map does not contain all three phenotypes --> IllegalArgumentException message
	private static final String INCOMPLETE_MAP = "Include all Cooperator, PartialCooperator, and Defector in Map!";
	// Map is null or empty --> IllegalArgumentException message
	private static final String NULL_MAP = "Population map cannot be null or empty!";
	/**
	 *  When an organism cooperates, it gives up its one energy level to the other 8 organisms
	 *  -> conceptual, used universally in every class!
	 */
	public static final int COOPERATION_NUMBER = 8;
	/**
	 *  An organism needs at least 10 energy units to be able to reproduce -> conceptual, used universally 
	 *  in every class!
	 */
	public static final int REPRODUCTION_ENERGY_THRESHOLD = 10;
	// 3 phenotype names -> use getType() to maintain name consistency
	private final String SPECIES_1 = new Cooperator().getType();
	private final String SPECIES_2 = new Defector().getType();
	private final String SPECIES_3 = new PartialCooperator().getType();
	// the habitat for all organisms
	private LinkedList<Organism> population;
	/**
	 * constructs the ecology / microbiome based on information provided by the Map
	 * @param counts the Map
	 * @throws IllegalArgumentException if the Map provided is not satisfactory
	 */
	public Population(Map<String, Integer> counts) throws IllegalArgumentException
	{
		// null / empty map
		if (counts == null || counts.isEmpty()) {
	        throw new IllegalArgumentException(NULL_MAP);
	    }
		// map doesn't have enough all 3 phenotypes
		if (!counts.containsKey(SPECIES_1) || !counts.containsKey(SPECIES_2) || !counts.containsKey(SPECIES_3))
			throw new IllegalArgumentException(INCOMPLETE_MAP);
		// initiates the habitat
		population = new LinkedList<>();
		// loop through the "Map" and construct the initial microbiome
		Set<Map.Entry<String, Integer>> countsSet = counts.entrySet();
		// loop
		for (Map.Entry<String, Integer> species : countsSet)
			addPopInitial(species);
	}
	/**
	 * for each category: construct the organisms
	 * @param speciesEntry Map.Entry for each phenotype
	 */
	private void addPopInitial(Map.Entry<String, Integer> speciesEntry)
	{	
		String phenotype = speciesEntry.getKey();
		// loop through the value, construct the phenotype specified by the Key
		if (phenotype.equals(SPECIES_1))
			for (int i = 0; i < speciesEntry.getValue(); i++)
				population.add(new Cooperator());
		else if (phenotype.equals(SPECIES_2))
			for (int i = 0; i < speciesEntry.getValue(); i++)
				population.add(new Defector()); 
		else if (phenotype.equals(SPECIES_3))
			for (int i = 0; i < speciesEntry.getValue(); i++)
				population.add(new PartialCooperator());
		else
			// if the Map specifies the species not mentioned in program, throw
			throw new IllegalArgumentException(NO_SUCH_SPECIES);
	}
	/**
	 * increment energy level by 1 for every individual
	 * check to see if they cooperate and allow them to perform that cooperation
	 * check if they can reproduce and let them reproduce
	 */
	public void update()
	{
		// iterator
		Iterator<Organism> itOrganism = population.iterator();
		// for each individual
		while (itOrganism.hasNext())
		{
			Organism individual = itOrganism.next();
			// updates them (by calling their update method)
			individual.update();
			// checks to see if they cooperate
			toCooperate(individual);
			// checks to see if they reproduce
			if (individual.getEnergy() >= REPRODUCTION_ENERGY_THRESHOLD)
			{
				// If the organism has at least 10 energy units, then reproduces
				Organism offSpring = individual.reproduce();
				int indexReplacer = new Random().nextInt(population.size());
				// ... and replaces a random organism in the population
				population.set(indexReplacer, offSpring);
			}
		}
	}
	/**
	 * checks if an individual cooperates and allows their cooperation
	 * @param indiv Organism
	 */
	private void toCooperate(Organism indiv)
	{
		if (indiv.cooperates())
		{
			// cooperator decrements its energy
			indiv.decrementEnergy();
			// randomly chooses 8 other -> randomer
			Random receiverRandomer = new Random();
			// use Set to take 8 other organisms to grant energy -> make sure they are 8 different organism 
			// and are not the one that cooperated!
			Set<Organism> receivers = new HashSet<>();
			while (receivers.size() < COOPERATION_NUMBER)
			{
				// generate index in population List
				int receiverIndex = receiverRandomer.nextInt(population.size());
				// get the actual Organism based on that index
				Organism receiverCandidate = population.get(receiverIndex);
				// only add into the receivers' Set if they are unique and not the one that gave up its energy
				if (indiv != receiverCandidate)
					receivers.add(receiverCandidate);
			}
			// increment energy for chosen receivers
			for (Organism receiver : receivers)
				receiver.incrementEnergy();
		}
	}
	/**
	 * loops through every individual in the microbiome to calculate the average coop prob
	 * @return coop prob
	 */
	public double calculateCooperationMean()
	{
		double sumProbability = 0.0;
		// loop
		for (Organism organism : population)
			// accumulate
			sumProbability += organism.getCooperationProbability();
		// divide the accumulation by the pop size
		return sumProbability / population.size();
	}
	/**
	 * returns a Map to count individuals for all 3 phenotypes
	 * 
	 * 
	 * NOTE: Since the names of all 3 kinds of organism are the same as 
	 * the names specified in this java file, we can safely assume that 
	 * the names in this file are also considered as the Key in the Map 
	 * implemented in this method
	 * @return Map
	 */
	public Map<String, Integer> getPopulationCounts()
	{
		int mapInitial = 0;
		// new map
		Map<String, Integer> counts = new HashMap<>(Map.of(SPECIES_1, mapInitial, SPECIES_2, mapInitial
				, SPECIES_3, mapInitial));
		// loop
		for (Organism organism : population)
		{
			// use getType to fill in the Map
			// NOTE: getType() = SPECIES_1 / SPECIES_2 / SPECIES_3 -> Can also be reused as the Keys!
			Integer indivNum = counts.get(organism.getType());
			counts.put(organism.getType(), ++indivNum);
		}
		return counts;
	}
	
}
