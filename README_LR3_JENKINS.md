# Лабораторная работа №3 — Jenkins Pipeline (вариант 3: Фитнес-центр)

## Что добавлено

- `Jenkinsfile` в корне проекта.
- Этап `Project Information`, который выводит groupId, artifactId и version из Maven-проекта.
- Основные этапы `Build`, `Test`, `Package`.
- В этапе Package используется требуемая заданием команда `mvn package -DskipTests`.
- Сообщения `post` для успешного и неуспешного выполнения Pipeline.

## Перед запуском

1. Убедитесь, что `mvn compile`, `mvn test` и `mvn package` выполняются локально из папки, где находится `pom.xml`.
2. Установите/запустите Jenkins и убедитесь, что Jenkins может вызывать `mvn` и Java из PATH пользователя, под которым запущена служба Jenkins.
3. Создайте в Jenkins задачу типа **Pipeline**, например `Lab3-FitnessCenter`.
4. Откройте `<JENKINS_HOME>\workspace\Lab3-FitnessCenter` и скопируйте туда содержимое этой папки проекта. Файл `pom.xml` должен находиться прямо в корне workspace, а не во вложенной папке.
5. В настройках задачи, в секции **Pipeline**, выберите **Pipeline script** и вставьте туда содержимое файла `Jenkinsfile`. Сохраните.
6. Нажмите **Build Now**, затем откройте номер сборки → **Console Output**.

## Ожидаемые этапы

1. `Project Information` — выводит groupId, artifactId и version.
2. `Build` — запускает `mvn compile`.
3. `Test` — запускает `mvn test`.
4. `Package` — запускает `mvn package -DskipTests`.

Успешный запуск заканчивается `Finished: SUCCESS`. При ошибке теста этап `Test` должен завершиться с ошибкой, а `Package` не должен выполняться.

## Проверка реакции на ошибку теста

1. Временно поменяйте одно ожидаемое значение в `src/test/java/FitnessServiceTest.java`, чтобы один тест гарантированно упал.
2. Запустите **Build Now**. Ожидается ошибка на этапе `Test`; `Package` не должен запускаться.
3. Верните исходное значение в тесте.
4. Запустите Pipeline снова и убедитесь, что результат — `Finished: SUCCESS`.

## Важно для Windows

В `Jenkinsfile` используются команды `bat`, потому что Pipeline выполняется на Windows. Если Jenkins работает на Linux, команды `bat` нужно заменить на `sh`.
