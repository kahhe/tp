package happybit.habit;

public class Habit {

    protected String habitName;
    protected boolean isDone;

    /**
     * Constructor for Habit class.
     *
     * @param habitName Name of habit linked to a goal.
     */
    public Habit(String habitName) {
        this.habitName = habitName;
        this.isDone = false;
    }

    /**
     * Getter for habitName.
     *
     * @return String containing name of habit.
     */
    public String getHabitName() {
        return habitName;
    }

    /**
     * Getter for isDone.
     *
     * @return Boolean value of whether the habit has been completed.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Update the habit as completed.
     */
    public void setCompleted() {
        isDone = true;
    }
}
