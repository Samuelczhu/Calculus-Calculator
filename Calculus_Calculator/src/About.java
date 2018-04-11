import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About {

    public void  display() throws Exception{
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setResizable(false);
        //icon source: <div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
        Image image = new Image("calculator.png");
        window.getIcons().add(image);

        Parent root = FXMLLoader.load(getClass().getResource("AboutScene.fxml"));
        Scene scene = new Scene(root, 500, 350);
        window.setScene(scene);
        window.showAndWait();
    }
}
