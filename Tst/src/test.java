public class test {
    public static void main(String[] args) {
        Test1 a1 = new Test1();
        a1.start();
    }

    public static class Test1 {
        private int modCount;

        public void start() {
            System.out.println(modCount);
        }

    }
}
