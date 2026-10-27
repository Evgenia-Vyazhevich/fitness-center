# Лабораторная работа №2 — вариант 3 «Фитнес-центр»

## Что сделано
- Проект переведён на стандартную структуру Maven.
- JUnit 5 подключён через `pom.xml`.
- Добавлены два Maven-профиля: `standard` и `expanded`.
- Профили задают разные значения максимального числа участников: 2 и 4.
- Maven подставляет значения в `src/main/resources/application.properties`.
- `AppConfig` читает настройки из ресурса.
- `Main` использует настройку при создании тренировки.
- JAR получает `Main-Class`, поэтому его можно запускать через `java -jar`.
- Исправлен тест заполненной тренировки: для третьего клиента ожидается `false`.

## Структура
- `src/main/java` — код приложения.
- `src/main/resources` — конфигурация, которую Maven обрабатывает с filtering.
- `src/test/java` — JUnit-тесты.
- `target` — результат сборки; Maven создаёт эту папку автоматически.

## Команды
Запускать команды в терминале из папки, где лежит `pom.xml`.

```powershell
mvn -version
mvn clean
mvn compile
mvn test
mvn package
java -jar target/fitness-center-1.0-SNAPSHOT.jar
```

Проверка профиля standard (максимум 2 участника):
```powershell
mvn clean package -Pstandard
java -jar target/fitness-center-1.0-SNAPSHOT.jar
```

Проверка профиля expanded (максимум 4 участника):
```powershell
mvn clean package -Pexpanded
java -jar target/fitness-center-1.0-SNAPSHOT.jar
```

После каждой сборки JAR перезаписывается. Поэтому запускай его сразу после нужной команды сборки.

## Ожидаемая демонстрация
Профиль `standard`: максимум 2; третья запись возвращает `false`.
Профиль `expanded`: максимум 4; третья запись возвращает `true`.

## Если команда mvn не распознана
Нужно установить Apache Maven и добавить его папку `bin` в PATH. Одного JDK недостаточно. После изменения PATH закрой и заново открой терминал. IntelliJ IDEA не обязательна: подойдёт VS Code или другая Java IDE.

## Если javac не распознан
Установить JDK (не только Java Runtime) и настроить `JAVA_HOME` и `PATH` на папку JDK и её `bin`. Проверить командами `java -version` и `javac -version`.

## Для защиты
«Вариант 3 — фитнес-центр. Для индивидуального задания созданы два Maven-профиля: standard и expanded. Первый задаёт максимум 2 участника, второй — 4. Maven фильтрует файл application.properties при сборке. Класс AppConfig считывает настройки из classpath, а Main передаёт лимит в объект Training. Команда `mvn clean package -Pstandard` собирает стандартный вариант, а `mvn clean package -Pexpanded` — расширенный. Разница видна в выводе программы: третья запись отклоняется в standard и разрешается в expanded. JUnit запускается командой `mvn test`, готовый JAR создаётся в target».
