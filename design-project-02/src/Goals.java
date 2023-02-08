
public interface Goals {

    /**
     * Adjust the user goal depending on their current weight compare to target weight and returns their calculated
     * daily target calories
     * @param newGoal new gaol to be updated
     */
    public double adjustGoal(Goals newGoal);

    /**
     * Changes the current goal to a new goal
     * @param newGoal new goal
     */
    public void setGoal(Goals newGoal);

}
