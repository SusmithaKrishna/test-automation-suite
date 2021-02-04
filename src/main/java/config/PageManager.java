package config;

import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;


public class PageManager {

    private WebDriver driver;
    private boolean isAllPagesScanned = false;
    private String packagePath = "pages";
    private Set<Class<? extends PageEngine>> allPageClasses;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * It will dynamically load the page instance at runtime instead of doing it compile time
     * It will save your memory leakage issue as it allows you to pick only those classes that you
     * needed at runtime or for a specific test case.
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public Object getPageInstance(String page) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        String pageClassName = page.toLowerCase().endsWith("page") ? page.replace(" ", "") :
                page.replace(" " , "") + "Page";

        String pageClass = null;
        if (!isAllPagesScanned) {
            Reflections reflections = new Reflections(packagePath);
            allPageClasses = reflections.getSubTypesOf(PageEngine.class);
            isAllPagesScanned = true;
        }


        for (Class pageClazz: allPageClasses) {
            if (pageClazz.getName().endsWith(pageClassName)) {
                pageClass = pageClazz.getName();
            }
        }

        Class<?> aClass = this.getClass().getClassLoader().loadClass(pageClass);
        Constructor<?> constructor = aClass.getConstructor(WebDriver.class);
        return constructor.newInstance(this.driver);

    }

}


