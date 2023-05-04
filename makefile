default: Engine.java GameMap.java Item.java Room.java
	javac Engine.java GameMap.java Item.java Room.java

run: Engine.class GameMap.class Item.class Room.class
	java Engine

clean:
	rm -f *.class gamelog.txt