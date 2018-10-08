package edu.sjsu.cmpe275.aop;

import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;


public class TweetStatsImpl implements TweetStats {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */
	public static int longestLength = 0;
	public static String mostFollowedUser = "";
	public static String mostProductiveUser = "";
	public static String mostBlockedUser = "";
	public static String mostPopularMessage = "";

	
	public static TreeMap<String, Set<String>> userMapForMostFollowed = new TreeMap<String, Set<String>>();
	public static TreeMap<String, ArrayList<String>> userMapForMessages = new TreeMap<String, ArrayList<String>>();
	public static TreeMap<String, Integer> userMapForMessageLength = new TreeMap<String, Integer>();
	public static TreeMap<String, Set<String>> userMapForBlocked = new TreeMap<String, Set<String>>();
	public static TreeMap<String, Integer> userMapForBlockedLength = new TreeMap<String, Integer>();

	
	

	@Override
	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
		System.out.println("Resetting all");
		longestLength = 0; 
		if (userMapForMostFollowed.size() != 0) {
			userMapForMostFollowed.clear();
		}
		if (userMapForMessages.size() != 0) {
			userMapForMessages.clear();
		}
		if (userMapForMessageLength.size() != 0) {
			userMapForMessageLength.clear();
		}
		if (userMapForBlocked.size() != 0) {
			userMapForBlocked.clear();
		}
		if (userMapForBlockedLength.size() != 0) {
			userMapForBlockedLength.clear();
		}
		mostFollowedUser = "";
		mostProductiveUser = "";
		mostBlockedUser = "";
		mostPopularMessage = "";
	}
    
	@Override
	public int getLengthOfLongestTweet() {
		// TODO Auto-generated method stub
		
		return longestLength;
	}

	@Override
	public String getMostFollowedUser() {
		// TODO Auto-generated method stub
		return mostFollowedUser;
	}

	@Override
	public String getMostPopularMessage() {
		// TODO Auto-generated method stub
		return mostPopularMessage;
	}
	
	@Override
	public String getMostProductiveUser() {
		// TODO Auto-generated method stub
		return mostProductiveUser;
	}

	@Override
	public String getMostBlockedFollower() {
		// TODO Auto-generated method stub
		return mostBlockedUser;
	}
}



