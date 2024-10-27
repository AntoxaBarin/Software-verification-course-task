CXX = g++
CXXFLAGS = -O0 --coverage -fprofile-arcs -ftest-coverage -fno-exceptions -fno-inline

EXE = program

all:
	$(CXX) $(CXXFLAGS) main.cpp aho_corasick.cpp -c
	$(CXX) $(CXXFLAGS) main.o aho_corasick.o -o $(EXE)
	./$(EXE)

clean:
	rm aho_corasick.gcno
	rm aho_corasick.gcda
	rm main.gcno
	rm main.gcda
	rm main.o
	rm aho_corasick.o
	rm $(EXE)

coverage:
	gcovr -r .
	gcovr -r . --branches

.PHONY: clean all
