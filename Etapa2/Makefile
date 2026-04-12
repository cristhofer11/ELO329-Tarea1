# Automatización de tareas para la Etapa 2
JC = javac
BIN = bin
SRC = src
FLAGS = -d $(BIN)
CP = -cp $(BIN)

all:
	@mkdir -p $(BIN)
	$(JC) $(FLAGS) $(SRC)/*.java

run: all
	java $(CP) T1Stage2 config.txt move.txt

clean:
	rm -rf $(BIN)/*.class output.csv