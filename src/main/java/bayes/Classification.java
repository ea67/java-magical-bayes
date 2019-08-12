package bayes;

import java.util.Arrays;

public class Classification implements Comparable<Classification> {
    private String category;
    private String[] features;
    private double probability;

    public Classification(String category, String[] features, double probability) {
        this.category = category;
        this.features = features;
        this.probability = probability;
    }


    @Override
    public int compareTo(Classification o) {
        if (probability == o.probability) return 0;
        return probability - o.probability > 0 ? 1 : -1;
    }

    public double getProbability() {
        return probability;
    }

    public String getCategory() {
        return category;
    }

    public String[] getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "{" +
                "category='" + category + '\'' +
                ", features=" + Arrays.toString(features) +
                ", probability=" + probability +
                '}';
    }
}
