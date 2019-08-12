
#### 快速入门

```java
import bayes.BayesBrain;
import bayes.BayesClassifier;

public class Test {
    public static void main(String[] args) {
        //实例化 贝叶斯大脑
        BayesBrain bayesBrain = new BayesBrain();
        
        //构造分类器
        BayesClassifier bayesClassifier = new BayesClassifier(
                //训练贝叶斯大脑
                bayesBrain
                        .learn("Chinese", "Chinese", "Beijing", "Chinese")
                        .learn("Chinese", "Chinese", "Chinese", "Shanghai")
                        .learn("Chinese", "Chinese", "Macao")
                        .learn("Not Chinese", "Tokyo", "Japan", "Chinese")
        );


        bayesBrain.show();

        String[] features = {"Chinese", "Chinese", "Chinese", "Tokyo", "Japan"};
        //计算属于该类别的概率
        System.out.println(bayesClassifier.bayesProbabilityOf("Chinese",
                features));
        System.out.println(bayesClassifier.bayesProbabilityOf("Not Chinese",
                features));

        //对测试样本进行分类
        System.out.println(bayesClassifier.classify(features));

    }
}

```

#### 待更新...