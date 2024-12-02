package simulator;
/**
 * The Organism class provides the common interface for all sub-phenotypes.
 * @author Dat Dinh
 * @version Version 1
 * Assignment 6
 * Due Date: 27/11/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 * 
 * 
 * To hone the uniqueness and identity of each individual, even if they descend from 
 * the same phenotype, the classes in this {@code Organism} hierarchy do not override 
 * {@code equals()} and {@code hashCode()} functions. 
 * <p>
 * These classes use identity as the sole basis of equality.
 * <p>
 * <strong>Warning:</strong> Please use this hierarchy with great caution. 
 * Otherwise, your program may exhibit unexpected behavior when relying on 
 * {@code equals()} or {@code hashCode()}, especially in collections like 
 * {@code HashMap} or {@code HashSet}.
 */
public abstract class Organism {
	// IllegalStateException message
	private static final String ENERGY_SHORTAGE = "Organism running out of energy";
	// energy level -> resource management
	private int energy;
	/**
	 * energy by default is 0
	 */
	public Organism()
	{
		this.energy = 0;
	}
	/**
	 * By default an organism updates its energy up by 1
	 */
	public void update()
	{
		this.energy++;
	}

	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}
	/**
	 * increments energy by 1
	 */
	public void incrementEnergy()
	{
		this.energy++;
	}
	/**
	 * decrements energy by 1 only if energy is more than 0
	 */
	public void decrementEnergy()
	{
		if (this.energy <= 0)
			throw new IllegalStateException(ENERGY_SHORTAGE);
		else
			this.energy--;
	}
	/**
	 * this is only used when once wants to reproduce
	 * cannot used outside of the Organism hierarchy
	 */
	protected void useUpEnergyReproduce()
	{
		// threshold = 10
		if (this.energy < Population.REPRODUCTION_ENERGY_THRESHOLD)
			throw new IllegalStateException(ENERGY_SHORTAGE);
		else
			this.energy -= Population.REPRODUCTION_ENERGY_THRESHOLD;
	}
	/**
	 * returns phenotype
	 * @return phenotype
	 */
	public abstract String getType();
	/**
	 * reproduces. uses up 10 energy units
	 * @return new Organism
	 */
	public abstract Organism reproduce();
	/**
	 * get the Coop Prob
	 * @return Coop Prob
	 */
	public abstract double getCooperationProbability();
	/**
	 * checks if an organism cooperates
	 * @return boolean state
	 */
	public abstract boolean cooperates();

}
