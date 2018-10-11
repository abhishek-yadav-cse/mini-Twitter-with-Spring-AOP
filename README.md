# mini-Twitter-with-Spring-AOP
A mini twitter application logic with Spring Framework. The application is backed by the concept of Aspect Oriented Programming of Spring framework. 


Implement the retry and stats concerns to a tweeting service through Aspect
Oriented Programming (AOP). 

The tweet service is defined as follows:

`
import java.io.IOException;
public interface TweetService {
/**
* @throws IllegalArgumentException if the message is more than 140 characters as
measured by string length.
* @throws IOException if there is a network failure
*/
void tweet(String user, String message) throws IllegalArgumentException, IOException;
/**
* @throws IOException if there is a network failure
*/
void follow(String follower, String followee) throws IOException;
/**
*
* @throws IOException if there is a network failure
*/
void block(String user, String followee) throws IOException;
}
`

Since network failure happens relatively frequently, add the feature to
automatically retry for up to three times for a network failure (indicated by an IOException).
(Please note the three retries are in addition to the original failed invocation.) 

Implement the following TweetStats service:

`
public interface TweetStats {
/**
* reset all the measurements and all the following/blocking relationship as well.
*/
void resetStatsandSystem();
/**
* @return the length of longest message successfully sent or attempted since the beginning
or last reset. Can be more than 140. If no messages succeeded or attempted , return 0.
* Failed messages are counted for this as well.
*/
int getLengthOfLongestTweet();
/**
* @return the user who has been followed by the biggest number of different users since the
beginning or last reset. If there is a tie,
* return the 1st of such users based on alphabetical order. If the follow action did not
succeed, it does not count toward the stats. If no users were successfully followed, return null.
Blocking or not does not affect this metric.
*/
String getMostFollowedUser();
/**
* @return the message that has been shared with the biggest number of different followers
when it is successfully tweaked. If the same message (based on string equality) has been
tweeted more than once, it is considered as different message for this purpose. Return based
on dictionary order if there is a tie.
*/
String getMostPopularMessage();
/**
* The most productive user is determined by the total length of all the messages successfully
tweeted since the beginning
* or last reset. If there is a tie, return the 1st of such users based on alphabetical order. If no
users successfully tweeted, return null.
* @return the most productive user.
*/
String getMostProductiveUser();
/**
* @return the user who has been successfully blocked by the biggest number of
* different users since the beginning or last reset. If there is a
* tie, return the 1st of such users based on alphabetical order.
* If no follower has been successfully blocked by anyone, return null.
*/
String getMostBlockedFollower();
}
`

Implementation of the two concerns need to be done in the two files: RetryAspect.java and
StatsAspect.java
Not need to worry about multi-threading; i.e., you can assume invocations on the tweet
service and stats service will come from only one thread.
W.r.t. follow and block , the two actions do not directly interfere with each other, i.e., Alice can
block Bob, and after that Bob can still successfully follow Alice, as far the success of service
invocations are considered. The end effect, however, is that when Alice sends a tweek, Bob
cannot receive it, since he has been blocked. Both follow and block get cleared upon system
reset.
