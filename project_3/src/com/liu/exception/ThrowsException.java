package com.liu.exception;
/**
 * throws，
 * @author Administrator
 *Example方法生成的异常通过调用栈把异常对象传递给main()方法，由它来处理。
 */
public class ThrowsException {
	//定义方法 抛出异常
		static void Example(int keg) throws ArithmeticException,ArrayIndexOutOfBoundsException{
			System.out.println("---- In kegDepartment "+ keg+ "----");
			if(keg==10) {
				System.out.println("no Exception caught");
				return ;
			}else if (keg==20) {
				int iArray[]=new int[4];
				iArray[10]=3;
			}
		}
		//main
		public static void main(String[] args) {
			try {
				Example(10);
				Example(20);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Catch " +e);
			}finally {
				System.out.println("In Example finally");
			}
		}
}
