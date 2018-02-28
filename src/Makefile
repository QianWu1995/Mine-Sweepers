JFLAGS = -g
JC = javac
JAVA = java
.SUFFIXES: .java .class
.java.class:
	$(JC) -cp "libs/*" $(JFLAGS) *.java

CLASSES = DotInfo.java GameController.java GameModel.java GameView.java Minesweeper.java

default: classes

classes: $(CLASSES:.java=.class)

ifeq ($(OS),Windows_NT)
run: default
	$(JAVA) -cp "libs/*;" Minesweeper
clean:
	del *.class
else
run: default
	$(JAVA) -cp ":libs/*" Main
clean:
	rm *.class
endif

