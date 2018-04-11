import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage window;
    static Scene mainScene;
    static Scene derivativeScene;
    static Scene integralScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            window = primaryStage;
            window.setTitle("Calculus Calculator");
            window.setResizable(false);
            //icon source: <div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
            Image image = new Image("calculator.png");
            window.getIcons().add(image);

            Parent mainParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            Parent derivativeParent = FXMLLoader.load(getClass().getResource("DerivativeScene.fxml"));
            Parent integralParent = FXMLLoader.load(getClass().getResource("IntegralScene.fxml"));
            mainScene = new Scene(mainParent, 600, 400);
            derivativeScene = new Scene(derivativeParent, 600, 400);
            integralScene = new Scene(integralParent, 600, 400);
            window.setScene(mainScene);
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }

    //switch scene
    public void toMain() {
        Main.window.setScene(Main.mainScene);
    }
    public void toDerivative() {
        Main.window.setScene(Main.derivativeScene);
    }
    public void toIntegral() {
        Main.window.setScene(Main.integralScene);
    }

    //showing info
    public void showInfo() throws Exception {
        About about = new About();
        about.display();
    }
}
