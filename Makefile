SRC = ./src/library \
./src
DST = ./build
MAIN = Home

.PHONY: clean compile run

clean:
	rm -f $$(find $(DST) -name "*.class")

compile:
	mkdir -p $(DST)
	javac -d $(DST) $$(find $(SRC) -name "*.java")

run:
	java -cp $(DST) $(MAIN)
