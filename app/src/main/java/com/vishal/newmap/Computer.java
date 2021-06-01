package com.vishal.newmap;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    public Metrics computeMetrics(List<SimpleLocation> locations) {

        if (locations == null || locations.size() < 2) {
            return null;
        }

        ArrayList<Float> distances = new ArrayList<>();
        for (int i = 0; i < locations.size() - 1; i++) {
            SimpleLocation startLocation = locations.get(i);
            SimpleLocation nextLocation = locations.get(i + 1);
            float distance = startLocation.distanceTo(nextLocation);
            distances.add(distance);
        }

        List<Double> times = new ArrayList<>();
        for (SimpleLocation location : locations) {
            times.add(location.ts);
        }

        List<Double> timeDeltas = new ArrayList<>();
        int index = 0;
        while (index < (times.size() - 1)) {
            timeDeltas.add(times.get(index + 1) - times.get(index));
            index++;
        }

        List<Double> speeds = new ArrayList<>();
        for (int j = 0; j < distances.size(); j++) {
            speeds.add(distances.get(j) / timeDeltas.get(j));
        }

        float sum = 0;
        for (int i = 0; i < speeds.size(); i++) {
            sum += speeds.get(i);
        }

        double dis = 0.0;
        for (int i = 0; i < distances.size(); i++) {
            dis += distances.get(i);
        }
        float averageSpeed = sum / speeds.size();

        double duration = locations.get(locations.size() - 1).ts - locations.get(0).ts;
        int calories = getCalories(duration, averageSpeed);
        return new Metrics(calories, duration, dis, averageSpeed);
    }

    public int getCalories(double duration, double speed) {
        // Formula ((MET * W * 3.5) / 200) * T
        // Where W = body weight in Kg and T = duration in minutes
        // 3.5 and 200 are magic numbers.
        double metValue = getMetValue(metersPerSecondsToMilesPerHour(speed));
        int weight = 70;
        double caloriesPerMinute = ((metValue * weight * 3.5) / 200) * secondsToMinutes(duration);
        return (int) caloriesPerMinute;
    }

    private double secondsToMinutes(double duration) {
        return duration / 60;
    }


    private double metersPerSecondsToMilesPerHour(double speed) {
        return (speed >= 0) ? 2.237 * speed : 0.0;
    }

    /**
     * Returns the Metabolic Equivalent of Task (MET) for a given running speed. MET is a measurement
     * of the energy cost of physical activity for a period of time.
     *
     * @param speed running speed in miles per hour
     * @see [Runner's MET Values]  <a href="https://captaincalculator.com/health/calorie/calories-burned-running-calculator/">Runner's
     * MET Values</a>
     */
    private double getMetValue(double speed) {
        if (speed <= 0) {
            return 0.0;
        } else if (speed > 0 && speed < 4) {
            return 3.5;
        }
        if (speed >= 4 && speed < 5) {
            return 5;
        } else if (speed >= 5 && speed < 5.2) {
            return 8.3;
        } else if (speed >= 5.2 && speed < 6) {
            return 9;
        } else if (speed >= 6 && speed < 6.7) {
            return 9.8;
        } else if (speed >= 6.7 && speed < 7) {
            return 10.5;
        } else if (speed >= 7 && speed > 7.5) {
            return 11;
        } else if (speed >= 7.5 && speed < 8) {
            return 11.5;
        } else if (speed >= 8 && speed < 8.6) {
            return 11.8;
        } else if (speed >= 8.6 && speed < 9) {
            return 12.3;
        } else if (speed >= 9 && speed < 10) {
            return 12.8;
        } else if (speed >= 10 && speed < 11) {
            return 14.5;
        } else if (speed >= 11 && speed < 12) {
            return 16;
        } else if (speed >= 12 && speed < 13) {
            return 19;
        } else if (speed >= 13 && speed < 14) {
            return 19.8;
        } else if (speed >= 14 && speed <= 15) {
            return 23;
        }
        return 26;
    }
}
