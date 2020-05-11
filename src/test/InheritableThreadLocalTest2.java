public class InheritableThreadLocalTest2 {
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        InheritableThreadLocalTest2.inheritableThreadLocal.set("inheritableThreadLocal-main");
        new Thread(() -> {
            InheritableThreadLocalTest2.inheritableThreadLocal.set("inheritableThreadLocal-1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1==" + InheritableThreadLocalTest2.inheritableThreadLocal.get());

            new Thread(() -> {
                System.out.println("线程1的子线程==" + InheritableThreadLocalTest2.inheritableThreadLocal.get());
            }).start();

        }).start();

        new Thread(() -> {
            InheritableThreadLocalTest2.inheritableThreadLocal.set("inheritableThreadLocal-2");
            System.out.println("线程2==" + InheritableThreadLocalTest2.inheritableThreadLocal.get());
        }).start();

        System.out.println("主线程==" + InheritableThreadLocalTest2.inheritableThreadLocal.get());
    }

}
