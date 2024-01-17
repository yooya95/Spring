
package aop2;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {  
	
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		//핵심관심사Method
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + "is start...");
		
		//성능(performance 측정) 시작시간...
		long startTime = System.currentTimeMillis();
		
		Object obj;
		
		try {
			// 핵심관심사 Method 수행
			obj = joinPoint.proceed();
			return obj;
	
		} finally {
			// 핵심관심사의 종료시간
			long endTime = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 성능 경과시간 :"+(endTime - startTime));
		}
		
	
	}

	public void beforeAdivce() {
		System.out.println("beforeAdivce()");
	}
	
	public void afterReturningAdvice() {
		System.out.println("afterReturningAdvice()");
	}
	public void afterThrowingAdvice() {
		System.out.println("afterThrowingAdivce()");
	}
	public void afterAdvice() {
		System.out.println("afterAdivce()");
	}
}
