public class UserUtil {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> strhreadLocal = new ThreadLocal<>();

    public static User getUser() {
        return userThreadLocal.get();
    }
    public static void setUser(User user) {
         userThreadLocal.set(user);
    }
    public static void removeUser() {
        userThreadLocal.remove();
    }

    public static void main(String[] args) {
        new Thread(() ->{
            UserUtil.setUser(User.builder().age(11).name("11").build());
            strhreadLocal.set("111");
            System.out.println(Thread.currentThread() + "---" + strhreadLocal.get());
            System.out.println(Thread.currentThread() + "---" + UserUtil.getUser());
        }).start();

        new Thread(() ->{
            UserUtil.setUser(User.builder().age(22).name("22").build());
            strhreadLocal.set("222");
            System.out.println(Thread.currentThread() + "---" + strhreadLocal.get());
            System.out.println(Thread.currentThread() + "---" + UserUtil.getUser());
        }).start();

        strhreadLocal.set("main");
        UserUtil.setUser(User.builder().age(33).name("33").build());
        System.out.println(Thread.currentThread() + "---" + UserUtil.getUser());

        UserUtil.setUser(User.builder().age(44).name("44").build());
        System.out.println(Thread.currentThread() + "---" + UserUtil.getUser());
        System.out.println(Thread.currentThread() + "---" + strhreadLocal.get());
    }

}
