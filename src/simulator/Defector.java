package simulator;
/**
 * The Defector class models a defector individual -> 100% defective.
 * @author Dat Dinh
 * @version Version 1
 * Assignment 6
 * Due Date: 27/11/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class Defector extends Organism {
	// type
	private static final String TYPE = "Defector";
	// never cooperates
	private static final double COOP_PROBABILITY = 0;
	// never coops
	private static final boolean IF_COOP = false;
	/**
	 * get type: Defector
	 */
	@Override
	public String getType()
	{
		return TYPE;
	}
	/**
	 * reproduce, returns new Defector
	 */
	@Override
	public Organism reproduce() 
	{
		// use up all energy to reproduce
		super.useUpEnergyReproduce();
		// reproduce
		return new Defector();
	}
	/**
	 * returns coop prob
	 */
	@Override
	public double getCooperationProbability()
	{
		return COOP_PROBABILITY;
	}
	/**
	 * cooperates, never does!
	 */
	@Override
	public boolean cooperates()
	{
		return IF_COOP;
	}

}
