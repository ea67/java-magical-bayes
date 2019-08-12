package bayes;

import java.util.HashMap;


public class BayesBrain {

    private static final String VERSION = "1.0.0";
    HashMap<String, Integer> featuresFrequency = new HashMap<>();
    HashMap<String, Integer> categoriesFrequency = new HashMap<>();
    HashMap<String, HashMap<String, Integer>> featuresFrequencyInEachCategory = new HashMap<>();

    public BayesBrain learn(String category, String... features) {

        learn(featuresFrequency, features);

        categoriesFrequency.compute(category, (c, fre) -> {
            if (fre == null) return 1;
            else return fre + 1;
        });


        featuresFrequencyInEachCategory.compute(category, (c, featuresFrequencyInCategory) -> {
            if (featuresFrequencyInCategory == null) {
                featuresFrequencyInCategory = new HashMap<>();
            }

            learn(featuresFrequencyInCategory, features);

            return featuresFrequencyInCategory;
        });
        return this;
    }

    private void learn(HashMap<String, Integer> featuresFrequency, String[] features) {
        for (String feature : features)
            featuresFrequency.compute(feature, (f, fre) -> {
                if (fre == null) return 1;
                else return fre + 1;
            });
    }

    public void show() {
        System.out.println("~~~~~~~~~~~ Bayes Brain " + VERSION + " ~~~~~~~~~~~");
        System.out.println(featuresFrequency);
        System.out.println(categoriesFrequency);
        System.out.println(featuresFrequencyInEachCategory);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
