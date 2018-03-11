import entities.Book;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator: Mazur Anton
 * Date: 23.02.18
 * Time: 17:43
 **/

public class MainWindow extends Application {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) throws Exception {
        launch(args);
        sessionFactory.close();
        return;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Controller.setMainStage(stage);
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Book book = new Book();
        book.setAuthors("slkfj");
        book.setEdition("lskfj");
        book.setEditionYear(111);
        book.setName("slfkj");
        session.persist(book);
        t.commit();
        List<Book> list = new ArrayList<Book>();
        list = session
                .createCriteria(Book.class).list();
        list.forEach(System.out::print);
        session.close();
        String fxmlFile = "/fxml/mainWindowUser.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Library(as user)");
        stage.setScene(new Scene(root));
        stage.show();
        return;
    }
}
