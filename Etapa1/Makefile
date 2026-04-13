JC = javac
JVM = java
BIN = bin
SRC = src
MAIN = T1Stage1

.PHONY: all run clean

all:
	mkdir -p $(BIN)
	$(JC) -encoding UTF-8 -d $(BIN) $(SRC)/*.java

run: all
	$(JVM) -cp $(BIN) $(MAIN) config.txt move.txt

clean:
	rm -rf $(BIN) output.csv