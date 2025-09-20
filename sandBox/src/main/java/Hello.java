public class Hello {
    public static void main(String[] args) {
        var a = 1;
        var b = 1;
        if (b == 0) {
            System.out.println("Division by zero is not allowed");
        } else {
            var z = divide(a, b);
            System.out.println(z);
            System.out.println("Hello World");
        }


    }

    private static int divide(int a, int b) {
        var z = a / b;
        return z;
    }
}
