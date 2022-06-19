package tieredcompilation;

public class TieredCompilation {

    /**
     * jvm option -XX:+PrintCompilation -XX:TieredStopAtLevel=4
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1_000_000; i++) {
            Formatter formatter;
            if (i < 500_000) {
                formatter = new JsonFormatter();
            } else {
                formatter = new XmlFormatter();
            }
            formatter.format(new Article("Tiered Compilation in JVM", "Baeldung"));
        }
    }

}
