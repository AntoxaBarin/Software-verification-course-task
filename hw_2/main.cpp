#include "aho_corasick.hpp"

#include <functional>

using std::string;
using std::vector;
using TestFunc = std::function<bool(const vector<string>&, const string&, const vector<string>&)>;

bool run_test(const vector<string>& words, const string& text, const vector<string>& answer) {
    auto root = aho_corasick::build_trie(words);
    return aho_corasick::search(root, text) == answer;
}

void run_tests(const TestFunc& test_runner,
               const vector<vector<string>>& words,
               const vector<string>& texts,
               const vector<vector<string>>& answers) {
    size_t number_of_tests = answers.size();
    for (size_t i = 0; i < number_of_tests; ++i) {
        if (test_runner(words[i], texts[i], answers[i])) {
            std::cout << "Test #" << (i + 1) << " passed.\n";
        }
        else {
            std::cout << "Test #" << (i + 1) << " failed.\n";
        }
    }
}

int main() {
    vector<vector<string>> words = {
            {"aba", "ba", "caba", "cc"},
            {"ab", "aa"},
            {"bc", "cd", "de", "dy"},

    };

    vector<string> texts = {
            "abacaba",
            "abb",
            "abcdefgh",
    };

    vector<vector<string>> answers = {
            {"aba", "ba", "caba", "aba", "ba"},
            {"ab"},
            {"bc", "cd", "de"},
    };

    run_tests(run_test, words, texts, answers);
}