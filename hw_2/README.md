## Домашнее задание №2


### Компиляция
```bash
g++ --coverage -fprofile-arcs -ftest-coverage -fno-exceptions -fno-inline -O0 main.cpp aho_corasick.cpp  -c main.o -lgcov

g++ --coverage -fprofile-arcs -ftest-coverage -fno-exceptions -fno-inline -O0 main.o aho_corasick.o -o program -lgcov
```

### Флаги компиляции

- `--O0`: Без оптимизаций, поскольку оптимизации могут изменять количество строк, а это скажется на измеряемом покрытии.

- `--coverage`, `-fprofile-arcs`, `-ftest-coverage`: просим компилятор собрать информацию, необходимую для подсчета покрытия.

- `-fno-inline`: не инлайним функции.

- `-fno-exceptions`: считаем, что функции не бросают исключений.

### Запуск программы
```bash
./program
```