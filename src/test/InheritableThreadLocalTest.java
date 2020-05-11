public class InheritableThreadLocalTest {

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        InheritableThreadLocalTest.inheritableThreadLocal.set("inheritableThreadLocal-main");
        new Thread(() -> {
            System.out.println("线程1==" + InheritableThreadLocalTest.inheritableThreadLocal.get());
        }).start();

        new Thread(() -> {
            System.out.println("线程2==" + InheritableThreadLocalTest.inheritableThreadLocal.get());
        }).start();

        System.out.println("主线程==" + InheritableThreadLocalTest.inheritableThreadLocal.get());
    }

}
