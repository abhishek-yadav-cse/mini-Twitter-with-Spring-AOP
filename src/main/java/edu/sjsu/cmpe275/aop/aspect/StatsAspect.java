package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;

@Aspect
@Order(0)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	private String user = "";
	private String message = "";
	private String follower = "";
	private String followee = "";
	private String oneWhoBlocks = "";
	private String oneWhoIsBlocked = "";
	private String previousUserTweeted = "";
	private int numberOfFollwersForMaxFollowee = 0;
	private String previousUserWhoTweeted = "";
	private String previousUserMessage = "";

	
	
	
	@Autowired TweetStatsImpl stats;
	
//	@After("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
//	public void dummyAfterAdvice(JoinPoint joinPoint) {
//		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//		//stats.resetStats();
//	}
//	
//	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
//	public void dummyBeforeAdvice(JoinPoint joinPoint) {
//		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
//	}
	
	@After("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))")
	public void tweetMessage(JoinPoint joinPoint) throws IOException {

		//System.out.println("IN the tweetTheMessage");
		user = (String) joinPoint.getArgs()[0];
		message = (String) joinPoint.getArgs()[1];
		
		if (message.length() > TweetStatsImpl.longestLength && message.length() <= 140) {
			TweetStatsImpl.longestLength = message.length();
		}
		
		
		ArrayList<String> messageForParticularUser = null;
		 
		if (!TweetStatsImpl.userMapForMessages.containsKey(user)) {
			messageForParticularUser = new ArrayList<String>();
		} else {
			messageForParticularUser = TweetStatsImpl.userMapForMessages.get(user);
		}
		if(message.length() <= 140) {
			messageForParticularUser.add(message);
		}
		
		Collections.sort(messageForParticularUser);
		TweetStatsImpl.userMapForMessages.put(user, messageForParticularUser);
		
		
		
		
//		ArrayList<String> messageForParticularUser = new ArrayList<String>();
//		messageForParticularUser.add(message);
//		if(message.length() <= 140) {
//		TweetStatsImpl.userMapForMostProductive.put(user, message);
//		}
//		
		
//		SAMPLE CODE TO VIEW KEY - VALUE PAIR FROM THE MAP
		
//		for (Map.Entry<String, ArrayList<String>> me : TweetStatsImpl.userMapForMessages.entrySet()) {
//			  String key = me.getKey();
//			  ArrayList<String> valueList = me.getValue();
//			  System.out.println("Key: " + key);
//			  System.out.print("Values: ");
//			  for (String s : valueList) {
//			    System.out.println(s + " ");
//		}
//		}
		
		for (Map.Entry<String, ArrayList<String>> me : TweetStatsImpl.userMapForMessages.entrySet()) {
		  String key = me.getKey();
		  ArrayList<String> valueList = me.getValue();
//		  System.out.println("Key: " + key);
//		  System.out.println("Values(length of string): ");
		  int sum = 0;
		  for (String s : valueList) {
		    sum = sum + s.length();
		  }
		TweetStatsImpl.userMapForMessageLength.put(key, sum);
		}
		
		
		
		 String maxKey = null;
		 for (String key : TweetStatsImpl.userMapForMessageLength.keySet())
		 {
		     if (maxKey == null || TweetStatsImpl.userMapForMessageLength.get(key) > TweetStatsImpl.userMapForMessageLength.get(maxKey))
		     {
		         maxKey = key;
		     }
		 }
		TweetStatsImpl.mostProductiveUser = maxKey;
		
		
	
		
		
		//FOR MOST POPULAR MESSAGE
		//checking in followers set whether any of the user is blocked or not

		//TweetStatsImpl.mostPopularMessage = TweetStatsImpl.userMapForMessages.get(TweetStatsImpl.mostFollowedUser).get(0);
		//System.out.printf("hahahahahaah",TweetStatsImpl.mostPopularMessage);

		
		
		
	}
	
	
	
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))")
	public void forPopularMessage(JoinPoint joinPoint) throws IOException {

		//System.out.println("Before tweet");
		previousUserWhoTweeted = (String) joinPoint.getArgs()[0];
		previousUserMessage = (String) joinPoint.getArgs()[1];

	}
	
	
	
	
	
	
	
	
	
	
	
	@After("execution(public * edu.sjsu.cmpe275.aop.TweetServiceImpl.follow(..))")
	public void followUser(JoinPoint joinPoint) throws IOException {

		follower = (String) joinPoint.getArgs()[0];
		followee = (String) joinPoint.getArgs()[1];
	
	     Set<String> followerSet = null; 

		 if(follower!=followee) {		 
			if (!TweetStatsImpl.userMapForMostFollowed.containsKey(follower)) {
				followerSet = new TreeSet<String>();
			} else {
				followerSet = TweetStatsImpl.userMapForMostFollowed.get(follower);
			}
			followerSet.add(followee);
			TweetStatsImpl.userMapForMostFollowed.put(follower, followerSet);
		} else {
			System.out.println("Error - Follower = Followee");
		}
		 
		 
		 String maxKey = null;
		 for (String key : TweetStatsImpl.userMapForMostFollowed.keySet())
		 {
		     if (maxKey == null || TweetStatsImpl.userMapForMostFollowed.get(key).size() > TweetStatsImpl.userMapForMostFollowed.get(maxKey).size())
		     {
		         maxKey = key;
		     }
		 }
		
		numberOfFollwersForMaxFollowee = TweetStatsImpl.userMapForMostFollowed.get(maxKey).size();
		TweetStatsImpl.mostFollowedUser = maxKey;
		
		


	}
	
	
	
	
	
//	@After("execution(public * edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..), public * edu.sjsu.cmpe275.aop.TweetServiceImpl.follow(..), public * edu.sjsu.cmpe275.aop.TweetServiceImpl.block(..))")
//	public void popularUser(JoinPoint joinPoint) throws IOException {
//		
//		for (Map.Entry<String, ArrayList<String>> me : TweetStatsImpl.userMapForMessages.entrySet()) {
//			  String key = me.getKey();
//			  ArrayList<String> valueList = me.getValue();
//			  System.out.println("Key: " + key);
//			  System.out.println("Values(length of string): ");
//			  int sum = 0;
//			  for (String s : valueList) {
//			    sum = sum + s.length();
//			  }
//			TweetStatsImpl.userMapForMessageLength.put(key, sum);
//		}
//	}
	
	
	
	
	
	
	@AfterReturning("execution(public * edu.sjsu.cmpe275.aop.TweetServiceImpl.block(..))")
	public void blockUser(JoinPoint joinPoint) throws IOException {

		oneWhoBlocks = (String) joinPoint.getArgs()[0];
		oneWhoIsBlocked = (String) joinPoint.getArgs()[1];
	
	     Set<String> userFollowerBlockSet = null; 
	     
	    // System.out.println("Before if statement");

		 if(oneWhoBlocks!=oneWhoIsBlocked) {	
//
			 //System.out.println("going inside first if");
//
			 
			if (!TweetStatsImpl.userMapForBlocked.containsKey(oneWhoBlocks)) {
				//
				// System.out.println("going inside second if");
	//
				userFollowerBlockSet = new TreeSet<String>();
				//
				 //System.out.println("printing userFollowedBlockSet");
				// System.out.println(userFollowerBlockSet);

	//
			} else {
				//
				 //System.out.println("going inside else of second if");
	//
				userFollowerBlockSet = TweetStatsImpl.userMapForBlocked.get(oneWhoBlocks);
			}//
//			 System.out.println("printing userFollowedBlockSet inside else of second if");
//			 System.out.println(userFollowerBlockSet);
//
			userFollowerBlockSet.add(oneWhoIsBlocked);
			//
//			 System.out.println("printing userFollowedBlockSet after adding");
//			 System.out.println(userFollowerBlockSet);
////
			TweetStatsImpl.userMapForBlocked.put(oneWhoBlocks, userFollowerBlockSet);
			//
//			 System.out.println("printing usermap");
//			 System.out.println(TweetStatsImpl.userMapForBlocked);//
		 	} else {
			//System.out.println("Error - Follower = Followee");
		 	}
		 
		 
			
		 	if (TweetStatsImpl.userMapForBlockedLength.containsKey(oneWhoIsBlocked)) {
		 		int temp;
		 		temp = TweetStatsImpl.userMapForBlockedLength.get(oneWhoIsBlocked);
				TweetStatsImpl.userMapForBlockedLength.put(oneWhoIsBlocked, temp + 1);
		 	}
		 	else {
				TweetStatsImpl.userMapForBlockedLength.put(oneWhoIsBlocked, 1);
		 	}
//			 System.out.println("printing userMapForBlockedLength");
//			 System.out.println(TweetStatsImpl.userMapForBlockedLength);//
//		 
		 		 
			 String maxKey = null;
			 for (String key : TweetStatsImpl.userMapForBlockedLength.keySet())
			 {
			     if (maxKey == null || TweetStatsImpl.userMapForBlockedLength.get(key) > TweetStatsImpl.userMapForBlockedLength.get(maxKey))
			     {
			         maxKey = key;
			     }
			 }
			TweetStatsImpl.mostBlockedUser = maxKey;
//		System.out.println("end of method");
		
		
		
		//finding most followed User after block
		if (TweetStatsImpl.userMapForBlocked.containsKey(TweetStatsImpl.mostFollowedUser)) {
	 		Set<String> tempSetOfBlockedUser;
	 		tempSetOfBlockedUser = TweetStatsImpl.userMapForBlocked.get(TweetStatsImpl.mostFollowedUser);
	 		Set<String> tempSetOfFollowedUser;
	 		tempSetOfFollowedUser = TweetStatsImpl.userMapForMostFollowed.get(TweetStatsImpl.mostFollowedUser);	
	 		
	 		tempSetOfFollowedUser.removeAll(tempSetOfBlockedUser);
	 		
	 	}
				
	}
	
}
