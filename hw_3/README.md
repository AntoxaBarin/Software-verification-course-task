## Домашнее задание №3

Структура данных: **Очередь Майкла-Скотта**.

Реализация структуры находится в файле `src/main/kotlin/MSQueue.kt`.

Тесты находятся в директории `src/test/kotlin`.

### Сборка без запуска тестов
```bash
gradle build -x test
```

### Сборка с запуском тестов
```bash
gradle build
```

### Запуск тестов
```bash
gradle test
```

## Описание тестов

#### Тест №1. MSQueueBasicTest

`StressTest` от Lincheck. Генерируется набор параллельных сценариев, в ходе которых исполняются методы `dequeue` и `enqueue`. Lincheck создает потоки, дожидается их готовности и одновременно запускает их на сценариях.

Ожидается, что этот тест проверяет корректность (не lock-freedom) структуры данных.

Тест исполняется порядка 10 секунд.

#### Тест №2. MSQueueObstructionFreedomTest

`checkObstructionFreedom` от Lincheck. Этот тест проверяет самую слабую форму общего прогресса системы (структуры данных) -- `obstruction-freedom`. 

> Obstruction-freedom, when any operation is completed in a bounded number of steps if all the other threads pause.

Тест исполняется порядка 40-50 секунд.

#### Тест №3. MSQueueSingleThreadTest

Несколько юнит-тестов для всех методов класса `MSQueue`. Проверка корректной работы `enqueue`, `dequeue`. Во время тестирования был выявлен баг в реализации метода `isEmpty` -- сравнивались не объекты, на которые ссылаются `AtomicReference`, а сами ссылки. Очевидно, что ссылки разные, поэтому метод всегда выдавал `false`.