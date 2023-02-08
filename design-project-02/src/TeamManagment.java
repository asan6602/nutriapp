public interface TeamManagment{
    public void add(RealUser newMember);
    public void remove(RealUser oldMember);
    public void notifyObj(Notification notify);
}