import static java.lang.Math.floor;

public class Workout {
    private static int minute;
    private static Intensity intensity;

    public Workout(int minute, Intensity intensity) {
        Workout.minute = minute;
        Workout.intensity = intensity;
    }

    public static void setMinute(int minute) {
        Workout.minute = minute;
    }

    public static void setIntensity(Intensity intensity) {
        Workout.intensity = intensity;
    }

    public static double calculateCalories(int minute, Intensity intensity){
        return switch (intensity) {
            case LOW -> minute * 5;
            case MEDIUM -> minute * 7.5;
            case HIGH -> minute * 10;
        };
    }

}
