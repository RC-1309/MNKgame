# MNKgame
Игра mnk в консоли на Java 17

Оригинал задания доступен по [ссылке](https://www.kgeorgiy.info/courses/prog-intro/homeworks.html#game).

- В случае ошибочного хода пользователь имеет возможность сделать другой ход.
- В случае ошибки игрок автоматически проигрывает.
- У игрока нет возможности достать Board из Position.
- Добавлена возможность турнира на 20 человек. Каждый с каждым играет две партии, по одной каждой символом
- Очки начисляются по схеме:
    - 3 очка за победу
    - 1 очко за ничью
    - 0 очков за поражение
- Результат турнира выводится в виде таблицы и списка.
- Возможна игра на 3 и 4 игрока(добавлены | и -).
- Реализована поддержка препятствий, то есть клеток, в которые нельзя ходить. Для примера реализована доска с запрещенными диагоналями.
