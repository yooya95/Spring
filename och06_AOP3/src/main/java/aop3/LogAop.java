package aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	// aop3.buz 패키지 안에 있는 모든 메소드
//	@Pointcut("within(aop3.buz.*)")
//	private void pointcutMethod() {
//	}

	// @Around("pointcutMethod()")
	@Around("within(aop3.buz.*)")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심관심사 Method
        String 	signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start..");
        // 성능 측정(시작시간..)
		long startTime = System.currentTimeMillis();
		
		Object obj;
		
		try {
			// 핵심관심사 Method 수행 
			obj = joinPoint.proceed();
			return obj;
		} finally {
			//  핵심관심사 종료 시간
			long endTime = System.currentTimeMillis();
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 성능 경과시간 : " + (endTime - startTime));
			
		}
	}

	@Before("within(aop3.buz.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
    @After("within(aop3.buz.*)")
	public void afterAdvice() {
		System.out.println("AfterAdvice()");
	}	
	
}
