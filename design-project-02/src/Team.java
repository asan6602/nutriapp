import java.util.ArrayList;

public class Team implements TeamManagment{
    private ArrayList<RealUser> teamMembers = new ArrayList<RealUser>();
    private String teamName;
    private Notification notify;
    private Challenge teamChallenge = null;
    public Team(String _name){
        this.teamName = _name;
    }

    @Override
    public void add(RealUser newMember) {
        // TODO Auto-generated method stub
        teamMembers.add(newMember);
    }

    @Override
    public void remove(RealUser oldMember) {
        // TODO Auto-generated method stub
        teamMembers.remove(oldMember);
    }
    public String getName(){
        return this.teamName;
    }
    public void setChallenge(Challenge _challenge){
        this.teamChallenge = _challenge;
    }

    @Override
    public void notifyObj(Notification _notify) {
        // TODO Auto-generated method stub
        notify = _notify;
        for(RealUser teamMate: teamMembers){
            teamMate.update(notify);
        }
    }

}
