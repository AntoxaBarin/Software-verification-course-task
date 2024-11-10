.PHONY: all clean coverage clang

CXX = g++
CXXFLAGS = -O0 --coverage -fprofile-arcs -ftest-coverage -fno-exceptions -fno-inline

EXE = program

all:
	$(CXX) $(CXXFLAGS) main.cpp aho_corasick.cpp -c
	$(CXX) $(CXXFLAGS) main.o aho_corasick.o -o $(EXE)
	./$(EXE)

clang:
	clang++ -O0 -fprofile-instr-generate -fcoverage-mapping -fcoverage-mcdc -c aho_corasick.cpp
	clang++ -c main.cpp
	clang++ -O0 -fprofile-instr-generate -fcoverage-mapping main.o aho_corasick.o -o program
	LLVM_PROFILE_FILE="program.profraw" ./program
	llvm-profdata merge -sparse program.profraw -o program.profdata
	llvm-cov report -show-branch-summary=false -show-region-summary=false -show-mcdc-summary ./program -instr-profile=program.profdata


clean:
	rm -rf *.gcno *.gcda *.o $(EXE) *.profdata *.profraw

coverage:
	gcovr -r .
	gcovr -r . --branches