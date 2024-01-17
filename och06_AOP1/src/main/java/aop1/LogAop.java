package aop1;

import org.aspectj.lang.ProceedingJoinPoint;

//joinpoint 관심사를 구현한 코드에 끼워 넣을 수 있응 이벤트 
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
	
}
