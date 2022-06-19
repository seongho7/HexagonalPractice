package classloader;

public class ClassLoaderTest {
    public static void main(String args[]) {
        System.out.println("Class Loader Test");
        ClassLoaderTest test = new ClassLoaderTest();
        try {
            test.showClassLoaders();
        } catch(ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }
    public void showClassLoaders() throws ClassNotFoundException {
        System.out.println("Classloader of this class: " + ClassLoaderTest.class.getClassLoader());
        System.out.println("Classloader of Permission: " + java.sql.SQLPermission.class.getClassLoader());
        System.out.println("Classloader of LinkedList: " + java.util.LinkedList.class.getClassLoader());
        return;
    }
}