public class Notification {
    private String message;
    private Team teamRecipient;
    private boolean Accepted;
    private Challenge challenge;

    //Notifaction when a user joins a team
    public Notification(Team _team, boolean _choice, String userName){
        this.teamRecipient = _team;
        this.Accepted = _choice;
        this.message = userName + " has joined Team " + _team.getName() + "\n";
    }
    //Notification user sends and invite
    public Notification(String userName,Team _team){
        this.teamRecipient = _team;
        this.message = "You sent an invitation for "  + userName + " to join Team: " + teamRecipient.getName() + "\n";
    }
    //Notification when a user recieves a request to join a team
    public Notification(String sender, String reciever, Team _team){
        this.teamRecipient = _team;
        this.message = reciever + " has received an invatation to join the team " + _team.getName() +  " from " + sender + "\n";
    }
    //Notifaction when a team member sets a new challenge
    public Notification(Team _team, Challenge _challenge, String userName){
        this.teamRecipient = _team;
        this.challenge = _challenge;
        this.message = userName + " has issued a new challenge: " + challenge.getDescription() + " to " + _team.getName() + "\n";
    }
    //Notification when a User leaves a team
    public Notification (Team _team, String userName){
        this.teamRecipient = _team;
        this.message = userName + " has left the Team " + _team.getName() + "\n";
    }
    public String getMessage(){
        return this.message;
    }


}
