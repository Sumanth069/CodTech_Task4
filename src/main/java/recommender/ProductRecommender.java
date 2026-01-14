package recommender;
import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class ProductRecommender {

    public static void main(String[] args) {

        try {
            // absolute-safe path
            File file = new File(
                "C:/Users/kpsum/OneDrive/Desktop/Java Internship/Task4/data/ratings.csv"
            );

            DataModel model = new FileDataModel(file);

            UserSimilarity similarity =
                    new PearsonCorrelationSimilarity(model);

            UserNeighborhood neighborhood =
                    new NearestNUserNeighborhood(2, similarity, model);

            Recommender recommender =
                    new GenericUserBasedRecommender(
                            model, neighborhood, similarity
                    );

            List<RecommendedItem> result =
                    recommender.recommend(1, 3);

            System.out.println("Recommendations for User 1:");
            for (RecommendedItem r : result) {
                System.out.println(
                        "Item " + r.getItemID() +
                        " | score " + r.getValue()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}