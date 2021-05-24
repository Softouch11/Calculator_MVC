import Calculatrice.View.View;
import Calculatrice.controller.Controller;
import Calculatrice.model.Model;

import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Model model = new Model();
            View view = new View();
            new Controller(model, view);

            model.addObserver(view);

        });

    }
}
