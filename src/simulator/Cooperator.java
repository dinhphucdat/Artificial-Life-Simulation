package simulator;
/**
 * The Cooperator class models a cooperator individual -> 100% cooperative.
 * @author Dat Dinh
 * @version Version 1
 * Assignment 6
 * Due Date: 27/11/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class Cooperator extends Organism {
	// type name
	private static final String TYPE = "Cooperator";
	// coop prob always 100%
	private static final double COOP_PROBABILITY = 1.0;
	// always cooperates
	private static final boolean IF_COOP = true;
	/**
	 * returns type: Cooperator
	 */
	@Override
	public String getType()
	{
		return TYPE;
	}
	/**
	 * reproduce
	 */
	@Override
	public Organism reproduce()
	{
		// use up all energy to reproduce
		super.useUpEnergyReproduce();
		// reproduce
		return new Cooperator();
	}
	/**
	 * return 1.0 as coop prob
	 */
	@Override
	public double getCooperationProbability()
	{
		return COOP_PROBABILITY;
	}
	/**
	 * cooperates...
	 */
	@Override
	public boolean cooperates()
	{
		// ... only it has enough energy to spare / cost itself when doing that
		boolean energyRequest = getEnergy() > 0;
		if (energyRequest)
			return IF_COOP;
		else
			return energyRequest;
	}


}
