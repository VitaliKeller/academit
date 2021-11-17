import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Test1 a1 = new Test1();
        a1.start();
    }

    public static class Test1 {
        private int modCount;

        public void start() {
            System.out.println(modCount);

            int[] ar1 = {1, 2, 3};
            int[] ar2 = new int[1];
                    ar2 = Arrays.copyOf(ar1, ar1.length);

            System.out.println(Arrays.toString(ar2));
        }
    }
}
