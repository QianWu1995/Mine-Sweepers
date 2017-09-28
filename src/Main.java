/**
 * Created by qianwu on 2017-03-22.
 */
public class Main {
    public static void main(String[] args) {

        ModelYouTube youtubeModel = new ModelYouTube("YOUR_API_KEY_HERE");
        youtubeModel.searchVideos("puppies");

        Model model = new Model();
        View view = new View();

        model.addObserver(view);

    }
}