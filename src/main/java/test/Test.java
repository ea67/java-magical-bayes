package test;

import bayes.BayesBrain;
import bayes.BayesClassifier;

import java.util.Arrays;

/**
 * 1    Chinese Beijing Chinese	                Chinese
 * 2	Chinese Chinese Shanghai	            Chinese
 * 3	Chinese Macao	                        Chinese
 * 4	Tokyo Japan Chinese	                    Not Chinese
 * test
 * 5	Chinese Chinese Chinese Tokyo Japan	    ?
 */
public class Test {
    public static void main(String[] args) {
        BayesBrain bayesBrain = new BayesBrain();
        BayesClassifier bayesClassifier = new BayesClassifier(
                bayesBrain
                        .learn("Chinese", "Chinese", "Beijing", "Chinese")
                        .learn("Chinese", "Chinese", "Chinese", "Shanghai")
                        .learn("Chinese", "Chinese", "Macao")
                        .learn("Not Chinese", "Tokyo", "Japan", "Chinese")
        );


        bayesBrain.show();

        String[] features = {"Chinese", "Chinese", "Chinese", "Tokyo", "Japan"};
        System.out.println(bayesClassifier.bayesProbabilityOf("Chinese",
                features));
        System.out.println(bayesClassifier.bayesProbabilityOf("Not Chinese",
                features));

        System.out.println(bayesClassifier.classify(features));


        test2();
    }

    private static void test2() {
        BayesBrain bayesBrain = new BayesBrain();
        final String[] positiveText = "I sunny love days".split("\\s");
        final String[] negativeText = "I hate rain".split("\\s");
        BayesClassifier bayesClassifier = new BayesClassifier(
                bayesBrain
                        .learn("love", positiveText)
                        .learn("hate", negativeText)
        );

        bayesBrain.show();
        final String[] unknownText1 = "today is a sunny day".split("\\s");
        final String[] unknownText2 = "there will be rain".split("\\s");



        System.out.println(bayesClassifier.probabilityOf(unknownText1));
        System.out.println(bayesClassifier.probabilityOf(unknownText2));

        System.out.println(bayesClassifier.classify(unknownText1));
        System.out.println(bayesClassifier.classify(unknownText2));
    }


}
