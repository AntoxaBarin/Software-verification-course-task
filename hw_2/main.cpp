#include "aho_corasick.hpp"

int main() {
    std::vector<std::string> words = {"aba", "ba", "caba", "cc"};
    std::string text = "abacaba";

    auto root = aho_corasick::build_trie(words);
    aho_corasick::search(root, text);
}