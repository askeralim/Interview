package org.core.returnval;

public class TestFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestFinally f=new TestFinally();
		System.out.println(f.getExceptionVal());

	}
	public int getExceptionVal(){
		try{
			if(true)
				throw new IndexOutOfBoundsException();
			return 20;
		}catch(Exception e){
			return 30;
		}finally{
			return 10;
		}
	}

}
