package bayes;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BayesClassifier {

    private static final String VERSION = "1.0.0";
    private static final Integer λ = 1;//平滑因子
    private BayesBrain bayesBrain;
    public BayesClassifier(BayesBrain bayesBrain) {
        this.bayesBrain = bayesBrain;
    }

    //P(xi) = count(xi)/sum(count(xj))
    private double probabilityOf(@NotNull String type, @NotNull HashMap<String, Integer> typesFrequency) {
        Integer frequency = typesFrequency.get(type);
        if (frequency == null) frequency = 0;
        int spaceSize = getSampleSpaceSize(typesFrequency);

        //laplace平滑校准
        frequency += λ;
        spaceSize += λ * bayesBrain.featuresFrequency.size();// λ * J
        return (double) frequency / spaceSize;
    }

    //P(feature)
    private double probabilityOfFeature(String feature) {
        return probabilityOf(feature, bayesBrain.featuresFrequency);
    }

    //P(category)
    private double probabilityOfCategory(String category) {
        @NotNull
        Integer categoryFrequency = bayesBrain.categoriesFrequency.get(category);
        int spaceSize = getSampleSpaceSize(bayesBrain.categoriesFrequency);
        return (double) categoryFrequency / spaceSize;
    }

    //P(feature|category)
    private double probabilityOfFeatureInCategory(String feature, String category) {
        HashMap<String, Integer> featuresFrequencyInCategory = bayesBrain.featuresFrequencyInEachCategory.get(category);
        return probabilityOf(feature, featuresFrequencyInCategory);
    }

    private int getSampleSpaceSize(HashMap<String, Integer> typeFrequency) {
        int spaceSize = 0;
        for (Integer frequency : typeFrequency.values()) spaceSize += frequency;
        return spaceSize;
    }

    //P(category|features...)
    //朴素贝叶斯计算特征数据属于某个类别的概率
    public double bayesProbabilityOf(String category, String... features) {
        double P = probabilityOfCategory(category);
        for (String feature : features) {
            P *= probabilityOfFeatureInCategory(feature, category);
        }
        double space = 1.0;
        for (String feature : features) space *= probabilityOfFeature(feature);
        System.out.println("P: " + P + ", space: " + space);
        return P / space;
    }


    public List<Classification> probabilityOf(String... features) {
        List<Classification> classifications = new ArrayList<>();
        for (String category : bayesBrain.categoriesFrequency.keySet()) {
            double probability = bayesProbabilityOf(category, features);
            Classification classification = new Classification(category, features, probability);
            classifications.add(classification);
        }
        classifications.sort(Classification::compareTo);
        return classifications;
    }

    public String classify(String... features) {
        String mostProbablyCategory = null;
        double maxProbability = Integer.MIN_VALUE;
        for (String category : bayesBrain.categoriesFrequency.keySet()) {
            double P = probabilityOfCategory(category);
            for (String feature : features) {
                P *= probabilityOfFeatureInCategory(feature, category);
            }
            if (P > maxProbability) {
                maxProbability = P;
                mostProbablyCategory = category;
            }
        }
        return mostProbablyCategory;
    }


}
