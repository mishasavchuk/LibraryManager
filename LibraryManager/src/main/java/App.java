import com.library.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.library.util.ConsoleHelpWrite.consoleWriter;

public class App {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        BookDao book = appContext.getBean(BookDao.class);
        consoleWriter(book);
    }
}
