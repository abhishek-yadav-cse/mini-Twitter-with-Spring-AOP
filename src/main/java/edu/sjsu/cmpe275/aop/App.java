package edu.sjsu.cmpe275.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStats stats = (TweetStats) ctx.getBean("tweetStats");

        try {
            tweeter.tweet("alex", "1first tweet by alex");
            tweeter.tweet("bob", "2first tweet by bob");
            tweeter.tweet("bob", "3second tweet by bob");
            tweeter.tweet("ajack", "4first tweet by ajack");
            tweeter.tweet("smith", "4first tweet by bob");
            tweeter.tweet("smith", "5second tweet by bob");
            //tweeter.tweet("smith", "7third tweet by smith");
            tweeter.follow("bob", "alex");
            tweeter.follow("bob", "smith");
            //tweeter.follow("bob", "jack");
            tweeter.follow("ajack", "alex");
            tweeter.follow("ajack", "smith");
            tweeter.follow("smith", "ajack");
            //tweeter.follow("ajack", "lex");
            
            tweeter.block("alex", "bob");
            tweeter.block("bob", "alex");
            tweeter.block("bob", "smith");
            //tweeter.follow("bob", "jack");
            tweeter.block("ajack", "alex");
            tweeter.block("ajack", "smith");
            tweeter.block("smith", "ajack");
            
            
            
            
            
            
            
            
            
            
//            //ANSHUL
//            
////        	tweeter.tweet("tex", "first tweet");
////          tweeter.tweet("tex", "second tweet");
////          tweeter.tweet("wex", "first tweet");
////          tweeter.tweet("wex", "second tweet");
////          tweeter.tweet("raj", "third tweet");
////          
////          tweeter.tweet("Prasun", "first tweet");
////          tweeter.tweet("Prasun", "second tweet");
////         
////          tweeter.follow("prasun", "piyush");
////          tweeter.follow("bob", "raj");
////          tweeter.follow("shyam", "Ram");
////     
////          tweeter.follow("Shyam", "Suresh");
////          tweeter.follow("Prasun", "Suresh");
////          tweeter.follow("Raj", "Suresh");
////          
////          tweeter.follow("Shyam", "Prasun");
////          tweeter.follow("Raj", "Ram");
////          tweeter.follow("jai", "Ram");
////          tweeter.follow("kumar", "Ram");
////          tweeter.follow("pankaj", "Ram");
////          tweeter.block("Suresh", "Prasun");
////          tweeter.block("Suresh", "Raj");
////          tweeter.block("Suresh", "Shyam");
////          tweeter.block("Ram", "Raj");
////          tweeter.tweet("Prasun", "third tweet");
////          tweeter.tweet("Vikas",
////					"Nor hence hoped her after other known defer his. For county now sister engage had season better had waited. lkfjaw djf Occasional mrs interested far.");
////			tweeter.tweet("Vikas", "For county now sister engage had whose. askfjwHLBDFIJAHSOLEFIHAWHEBEILF CHWliuau bfa kjhflkajlas bljab sldifuawelfiu");
//			
//			
//          //tweeter.follow("ajay", "bob");
////			tweeter.tweet("Sagar", "This first message on Twitter!!");
////			tweeter.tweet("Sagar", "This is my second message");
////			tweeter.tweet("Sagar", "This is my third message on Twitter!!");
////			tweeter.tweet("Vikas",
////					"I am your big bro bro. I win dude. This is my second message on Twitter!!Do you get that ? or not ?????");
////			tweeter.tweet("Vikas",
////					"Nor hence hoped her after other known defer his. For county now sister engage had season better had waited. Occasional mrs interested far.");
////			tweeter.tweet("Vikas", "For county now sister engage had whose.");
////			tweeter.tweet("Tim",
////					"The arc pun waves the nostalgia past the sitting professional. The spigot abides after a shaky tongue. The gate argues. The verse grants a .");
////			tweeter.tweet("Tim",
////					"The arc pun waves the nostalgia past the sitting professional. The spigot abides after a shaky tongue. The gate argues. The verse grants a .");
////			// tweeter.follow("GG", "foobar");
////			// tweeter.follow("GG", "Dafle");
////			tweeter.follow("gg", "foobar");
////			tweeter.follow("qwerty", "Sadhana");
////			tweeter.follow("qwerty", "ee");
////			tweeter.follow("qwerty", "asap");
////			tweeter.follow("gg", "asap");
////			tweeter.follow("qwerty", "tt");
//      	
//      	tweeter.follow("a", "alex");
//      	tweeter.follow("b", "alex");
//      	tweeter.follow("c", "alex");
//      	tweeter.follow("d", "alex");
//      	tweeter.follow("e", "alex");
//      	tweeter.follow("f", "alex");
//      	
//      	tweeter.follow("a", "bob");
//      	tweeter.follow("b", "bob"); 	
//      	tweeter.follow("c", "bob");
//      	tweeter.follow("d", "bob");
//      	tweeter.follow("e", "bob");
////      	tweeter.follow("f", "bob");
//      	
//      	tweeter.block("alex", "a");
//      	
//      	
//          tweeter.tweet("alex", "atweet");
//
//          tweeter.tweet("bob", "btweet");
////          

        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("Most productive user: " + stats.getMostProductiveUser());
//        System.out.println("Most popular user: " + stats.getMostFollowedUser());
//        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
//        System.out.println("Most unpopular follower " + stats.getMostBlockedFollower());
//        System.out.println("Most followed user " + stats.getMostFollowedUser());
        
        
        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular/followed user: " + stats.getMostFollowedUser()); //
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet()); //
        System.out.println("Most unpopular follower " + stats.getMostBlockedFollower());
        System.out.println("Most popular message " + stats.getMostPopularMessage());

        ctx.close();
    }
}
