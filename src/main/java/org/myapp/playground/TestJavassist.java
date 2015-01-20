package org.myapp.playground;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class TestJavassist {
	public static int STACK_TRACE_DEP = 2;
	
	public static void chkMethodByStackTrace() {
		Method method = null;
		StackTraceElement stack = Thread.currentThread().getStackTrace()[STACK_TRACE_DEP];
		String className = stack.getClassName();
		String methodName = stack.getMethodName();
		System.out.println("Line number: " + stack.getLineNumber());

		try {
			method = Class.forName(className).getMethod(methodName, int.class);
			
			for (Method m : Class.forName(className).getDeclaredMethods()) {
				System.out.println(m.getName() + " " + m.getParameterTypes());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dummy() {
		chkMethodByStackTrace();
	}
	
	public static void dummy(int x) {
		chkMethodByStackTrace();
	}

	public static void chkMethodByJavassit() throws Exception {
		String methodName = "dummy";
		
		System.out.println("Get method line number with javassist\n");

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("org.myapp.playground.TestJavassist");
//		CtMethod methodX = cc.getDeclaredMethod("dummy");
//		int xlineNumber = methodX.getMethodInfo().getLineNumber(1);
		
		for (CtMethod m : cc.getDeclaredMethods()) {
			//System.out.println("LET ME SEE: " + m.getName());
			
			//String nm = m.getName();
			//if (nm.equals(methodName)) System.out.println("HAHA...");
			
			if (! "dummy".equals(m.getName())) continue; 
//			m.getMethodInfo().getLineNumber(0);
			
			System.out.println(methodName + ": " + m.getMethodInfo().getLineNumber(0));
		}

//		System.out.println("method x is on line " + xlineNumber + "\n");

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		chkMethodByJavassit();
		//dummy(1);
	}

}
