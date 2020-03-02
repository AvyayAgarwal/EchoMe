# EchoMe

## Inspiration
Too scared to confess to your crush? Worried about what other people think of you? EchoME provides a absolute painless way for you to discover more about yourself, and also connect you with people in the way that you can never imagine.

## What it does
EchoME is a question-based social media platform. Users interact with each other by answering the questions that appear on their newsfeed. All questions are somewhat personal questions about users' friends (e.g. "If there are 5 people left in this world and Alex is one of them, would you date him/her?"), however users are not obligated to answer all questions that appeared on their newsfeed. 

When the user chooses to answer a question, the user gains the capability to visualize the statistics of how other his/her friends answered this question for him. 

If the number of questions that two users answer for each other has reached a threshold, a system event will be triggered and a SMS message will be sent to each users' phone to exchange phone numbers. (This is how you can get the phone number of your crush ;)  

Moreover, if a user believes two of his/her friends might actually be a great fit for each other, the user can give us a hint. Our analytic engine will use the information to adjust the model we used to generate new feed for that user's friends. 

## How we built it
Our backend is entirely built on top of Stdlib and it is connected to MongoDB as our main database. On the Android side we use Java and Retrofit library to build our mobile client

## Challenges we ran into
Since Stdlib is a complete stateless services, it is difficult to do a lot of stateful calculations such as personalize users question feed. Though eventually we came up with an efficient O(n) algorithm for it, the limitation remains. 

Also, Android Retrofit was not really cooperating with us. : (

## Accomplishments that we're proud of
Turns stateless Stdlib into a stateful API :D 

