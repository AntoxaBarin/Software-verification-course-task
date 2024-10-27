#pragma once

#include <string>
#include <vector>
#include <iostream>

namespace aho_corasick {

const int alphabet_size = 26;

struct Vertex {
    Vertex* to[alphabet_size] = {0};
    Vertex* go[alphabet_size] = {0};
    Vertex* link = nullptr;
    bool is_terminal = false;
    std::string word;
    Vertex* p;
    int pch;

    Vertex (int pch_, Vertex* p_) : pch(pch_), p(p_) { }
};

void add_string_to_trie(Vertex* root, const std::string& s);
Vertex* build_trie(const std::vector<std::string>& words);
Vertex* go(Vertex* root, Vertex* v, int c);
Vertex* link(Vertex* root, Vertex* v);
std::vector<std::string>  search(Vertex* root, const std::string& text);

} // namespace aho_corasick