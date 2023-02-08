public class Challenge {
    private Workout workout;
    private String desctription;
    public Challenge(Workout _Workout, String _description){
        this.workout = _Workout;
        this.desctription =_description;
    }
    //Sets the description of what the challenge is 
    public void setDescription(String _newDescription){
        this.desctription = _newDescription;
    }
    public Workout geWorkout(){
        return this.workout;
    }
    public String getDescription(){
        return this.desctription;
    }
}
