# Variables para facilitar cambios después
JC = javac
JVM = java
BIN = bin
SRC = src
MAIN = T1Stage3

.PHONY: all run clean

all:
	mkdir -p $(BIN)
	$(JC) -d $(BIN) $(SRC)/*.java

run: all
	$(JVM) -cp $(BIN) $(MAIN) config.txt move.txt

clean:
	rm -rf $(BIN) output.csv