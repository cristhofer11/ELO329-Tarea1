JC = javac
JVM = java
JFLAGS = -g -d bin
SRC = src
BIN = bin

all:
	mkdir -p $(BIN)
	$(JC) $(JFLAGS) $(SRC)/*.java

run: all
	$(JVM) -cp $(BIN) T1Stage1 config.txt move.txt

clean:
	rm -rf $(BIN)
	rm -f output.csv