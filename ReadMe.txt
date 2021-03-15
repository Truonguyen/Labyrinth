Truong Nguyen
tr736472

Both programs is written in JAVA

Problem 1: Minotaur’s Birthday Party (50 points)	
1. Go the the file directory				
2. Run the command 					
	javac Labyrinth.java && java Labyrinth		
4. Output should be display on the console							
==============================================================================================================================================================
Solution:
- every thread represent 1 guest, there will be 1 guest that will be in charge of counting total amount of guest who visited
- for every guest that will visit the labyrinth, eat the cake if it's cake, if they happen to revisit the labyrinth, do not eat the cake if already eaten once
- for everytime the counter guest visit, check if the cake is there. If it is, then refill the cake and increment the counter by one
- this will continue until the counter guest count up to n-1 (the reason why -1 is necessary it because it does not include itself)
- due to this implementation, the counter guest can know when all guest have visited without communication to each other



Problem 2: Minotaur’s Crystal Vase (50 points)		
1. Go the the file directory				
2. Run the command 				
	javac CrystalVase.java && java CrystalVase	
4. Output should be display on the console		
==============================================================================================================================================================
Solution: Strategy #2

I picked strategy #2 because it is a mutual exclusion solution. It is very similar to the Bob and Alice pet problem.
If one thread wants to go in, it checks if the flag/the room is available, then proceed to go in. Since the method is locked, only one thread
can access at a time. This will prevent from a deadlock from occuring since only one thread can access the room at a time.

    Pro: 
	- mutual exclusion, only one guest can visit at a time
	- deadlock cannot occur
    Con:
	- may be slower to the other methods
	- if one guest is delayed it will snowball to other guest entering
	- delays are common and unpredictable



 
