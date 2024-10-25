#include "aho_corasick.hpp"

namespace aho_corasick {

void add_string_to_trie(Vertex* root, std::string& s) {
    auto v = root;
    for (char c_ : s) {
        int c = static_cast<int>(c_ - 'a');
        if (!v->to[c]) {
            v->to[c] = new Vertex(c, v);
        }
        v = v->to[c];
    }
    v->is_terminal = true;
    v->word = s;
}

Vertex* build_trie(std::vector<std::string>& words) {
    Vertex* root = new Vertex(-1, nullptr);
    for (std::string& word: words) {
        add_string_to_trie(root, word);
    }
    return root;
}

Vertex* go(Vertex* v, int c);

Vertex* link(Vertex* root, Vertex* v) {
    if (!v->link) {
        if (v == root || v->p == root)
            v->link = root;
        else {
            v->link = go(root, link(root, v->p), v->pch);
        }
    }
    return v->link;
}

Vertex* go(Vertex* root, Vertex* v, int c) {
    if (!v->go[c]) {
        if (v->to[c]){
            v->go[c] = v->to[c];
        }
        else if (v == root){
            v->go[c] = root;
        }
        else{
            v->go[c] = go(root, link(root, v), c);
        }
    }
    return v->go[c];
}

void search(Vertex* root, const std::string& text) {
    Vertex* current = root;
    for (size_t i = 0; i < text.size(); ++i) {
        int c = text[i] - 'a';
        current = go(root, current, c);

        Vertex* temp = current;
        while (temp != root) {
            if (temp->is_terminal) {
                std::cout << "Found substring: " << temp->word
                          << " \tstart index: "  << (i - temp->word.size() + 1)
                          << " \tend index: "    << i << std::endl;
            }
            temp = link(root, temp);
        }
    }
}

} // namespace aho_corasick