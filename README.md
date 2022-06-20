# SpringBootRESTSensor

**SpringBootRESTSensor** - это RESTful Web Service.

**Общий стек технологий проекта:** _Spring Boot, PostgreSQL, Hibernate, Maven_.

### Взаимодействие происходит со следующими сущностями:
>*- Sensor (Integer id, String name);*  
*- Measurement (Integer id, Double value, Boolean raining, Sensor sensor, LocalDateTime cteatedAt);*   

### Описание:
**Sensor** - это метеорологический датчик (далее сенсор), который измеряет температуру окружающего воздуха и может определять, идет дождь или нет.
**Measurement** - измерения сенсора. Приложение получает данные с сенсора и сохраняет их в БД для дальнейшего анализа.   
Все получаемые данные проходят двойную валидацию (hibernate validator и spring validator):   
>*- Регистрируются сенсоры только с уникальным названием;*  
*- Измерения принимаются только от зарегистрированных сенсоров;*   
*- Пустые данные не принимаются;*   
*- Установлены лимиты по количеству символов и диапазону значений. И т.д.*   

### Endpoints:
:white_check_mark: (POST) http://localhost:8080/sensors/registration - зарегистрировать новый сенсор в БД   
:white_check_mark: (POST) http://localhost:8080/measurements/add - записать измерения сенсора в БД    
:white_check_mark: (GET) http://localhost:8080/sensors - получить список всех зарегистрированных сенсоров из БД   
:white_check_mark: (GET) http://localhost:8080/sensors/{id} - получить сенсор по id   
:white_check_mark: (GET) http://localhost:8080/measurements - получить список всех измерений из БД   
:white_check_mark: (GET) http://localhost:8080/measurements/{id} - получить измерение по id   
:white_check_mark: (GET) http://localhost:8080/measurements/rainyDaysCount - получить количество дождливых дней   

Для тестирования этого сервиса написано второе приложение-клиент - __ClientForSensor__.   
ClientForSensor взаимодействует с пользователем через консоль (куда и отдаёт полученные данные) + предоставляет график изменения температур.   

Также для тестирования можно использовать Postman или Insomnia.

| ***Для примера - скриншоты из Insomnia:*** |
|:------------------------------------------:|
|    ![](src/main/resources/static/1.jpg)    |
|    ![](src/main/resources/static/2.jpg)    | 
|    ![](src/main/resources/static/3.jpg)    |
|    ![](src/main/resources/static/4.jpg)    |
|    ![](src/main/resources/static/5.jpg)    |

### Инструкции для запуска приложения:

|             №             | Этапы выполнения                                                                                                                                                                                                                                                                                                                                                              |
|:-------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|             1             | [Скопируйте код на свой ПК](https://github.com/itbatia/SpringBootRESTSensor/archive/refs/heads/master.zip)                                                                                                                                                                                                                                                                    |
|             2             | Извлеките содержимое архива. В указанном месте появится папка HibernateCRUD-master.                                                                                                                                                                                                                                                                                           |
|             3             | Для локального использования приложения необходимо внести логин, пароль и URL от своей БД PostgreSQL.     Для этого измените файл pom.xml указав в теге <<properties>properties> свои <database.url>, <username<username>> и <password<password>>.     Соответствующие изменения внесите в файл src\main\resources\hibernate.cfg.xml в property: url, username и password.    |
|             4             | Создайте свою БД выполнив команду в PostgreSQL: CREATE DATABASE HibernateCRUD.                                                                                                                                                                                                                                                                                                |
|             5             | Зайдите в папку HibernateCRUD-master и в адресной строке пропишите: cmd                                                                                                                                                                                                                                                                                                       |
|                           | ![](screenshots/screen1.jpg)                                                                                                                                                                                                                                                                                                                                                  |
|             6             | Откроется командная строка, в которой необходимо прописать команду: mvn package. Результатом её выполнения будет примерно следующее:                                                                                                                                                                                                                                          |
|                           | ![](screenshots/screen2.jpg)                                                                                                                                                                                                                                                                                                                                                  |
|             7             | Запустите миграцию БД: mvn flyway:migrate                                                                                                                                                                                                                                                                                                                                     |
|                           | ![](screenshots/screen3.jpg)                                                                                                                                                                                                                                                                                                                                                  |
|             8             | Затем пропишите команду: mvn exec:java -Dexec.mainClass="com.itbatia.hibernate.Main"                                                                                                                                                                                                                                                                                          |
|                           | ![](screenshots/screen4.jpg)                                                                                                                                                                                                                                                                                                                                                  |
| :triangular_flag_on_post: | Программа запущена и готова к использованию!                                                                                                                                                                                                                                                                                                                                  |

*Для последующих запусков выполняйте только пункты № 5 и 8.*