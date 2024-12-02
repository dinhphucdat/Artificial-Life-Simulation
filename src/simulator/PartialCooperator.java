package simulator;
/**
 * The PartialCooperator class models a partial cooperator individual -> 50% cooperative.
 * @author Dat Dinh
 * @version Version 1
 * Assignment 6
 * Due Date: 27/11/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class PartialCooperator extends Organism {
	// type
	private static final String TYPE = "PartialCooperator";
	// coop prob
	private static final double COOP_PROBABILITY = 0.5;
	/**
	 * constructor
	 */
	public PartialCooperator()
	{
		super();
	}
	/**
	 * returns type: Partial...
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
		return new PartialCooperator();
	}
	/**
	 * 0.5 as coop prob
	 */
	@Override
	public double getCooperationProbability() 
	{
		return COOP_PROBABILITY;
	}
	/**
	 * cooperates as boolean state...
	 * 
	 * NOTE: this program controls the coop prob to be 50%, 
	 * meaning that a PartialCooperator cooperates and cooperates in half of their life cycles. 
	 * Therefore, the program is to generate a random float number between 0 and 1.0, then check 
	 * if it falls into the range of 0 and 0.5.
	 */
	@Override
	public boolean cooperates()
	{
		// ... only if it has enough energy to spare
		boolean energyRequest = getEnergy() > 0;
		if (energyRequest)
		{
			return new java.util.Random().nextDouble() <= COOP_PROBABILITY;
		}
		else
			return energyRequest;
	}

}
