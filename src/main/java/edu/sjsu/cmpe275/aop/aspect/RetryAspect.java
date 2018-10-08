package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetService;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;

@Aspect
@Order(1)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */
	private static final String IOException = "java.io.IOException";
	private static final String IllegalArgumentException = "java.lang.IllegalArgumentException";
	
	private static int retryTweet = 2;

	private static int retryFollow = 0;

	private String user = "";
	private String message = "";

	private String follower = "";
	private String followee = "";
	
	private static ApplicationContext applicationcontextvariable;
	private static TweetService tweetservicecontextvariable;

//	@Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.*(..))")
//	public void dummyAdvice(ProceedingJoinPoint joinPoint) {
//		System.out.printf("Prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		Object result = null;
//		try {
//			result = joinPoint.proceed();
//			System.out.printf("Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
//		} catch (Throwable e) {
//			e.printStackTrace();
//			System.out.printf("Aborted the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		}
//	}
	
	
	@AfterThrowing(pointcut = "execution(public void  edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))", throwing = "error")
	public void retryTweet(JoinPoint joinPoint, Throwable error) throws IOException {
		try {
			applicationcontextvariable = new ClassPathXmlApplicationContext("context.xml");
			tweetservicecontextvariable = (TweetService) applicationcontextvariable.getBean("tweetService", TweetService.class);
			user = (String) joinPoint.getArgs()[0];
			message = (String) joinPoint.getArgs()[1];
			
			if (error.toString().equals(IOException)) {
				System.out.println("IO Exception occured " + error);
				retryTweet -= 1;
				if (retryTweet >= 0) {
					System.out.println("retryTweetMethod Counter= " + retryTweet);
					tweetservicecontextvariable.tweet(user, message);
				} else {
					retryTweet = 2;
					System.out.println("Max attempts (" + retryTweet);

				}
			} else if (error.toString().equals(IllegalArgumentException)) {
				System.out.println("Max length" + error);
			}
		} catch (IOException error1) {
			System.out.println("Exception caught " + error1.getClass());
		}

	}
	
	
	
	
	@AfterThrowing(pointcut = "execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.follow(..))", throwing = "error")
	public void retryFollow(JoinPoint joinPoint, Throwable error) throws IOException {
		applicationcontextvariable = new ClassPathXmlApplicationContext("context.xml");
		tweetservicecontextvariable = (TweetService) applicationcontextvariable.getBean("tweetService", TweetService.class);

		follower = (String) joinPoint.getArgs()[0];
		followee = (String) joinPoint.getArgs()[1];
		try {
			if (error.toString().equals(IOException)) {
				System.out.println("IO Exception occured " + error);
				retryFollow += 1;
				if (retryFollow <= 2) {
					System.out.println("Retrycounter= " + retryFollow);
					
					tweetservicecontextvariable.follow(follower, followee);
				} else {
					System.out.println("Max attempt reached");
					retryFollow = 0;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception caught " + e.getMessage().toString());
		}

	}
	

}
