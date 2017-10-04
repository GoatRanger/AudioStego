public class OperatorEx {
    public static void main(String[] args) {
	int i = 10;
	if ((i-- % 5)>0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
