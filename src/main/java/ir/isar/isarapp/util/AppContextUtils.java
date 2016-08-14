package ir.isar.isarapp.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;

/**
 *
 * @author User
 */
public class AppContextUtils {

    private static Injector injector;

    public static void startAppContext() {
        ClassLoader classloader = AppContextUtils.class.getClassLoader();
        injector = Guice.createInjector(
                new WireModule( // auto-wires unresolved dependencies
                        new SpaceModule( // scans and binds @Named components
                                new URLClassSpace(classloader) // abstracts class/resource finding
                        )));
    }

    public static <T extends Object> T getBean(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    public static Injector getInjector() {
        return injector;
    }
}
