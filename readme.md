liquibase - инструмент для контроля и версионирования схемы БД. 



Для создания схемы БД из логов liquibase ввести команду:</br>
mvn liquibase:update



Для генерации лога из уже имеющейся схемы БД(снапшот схемы) ввести команду:</br>
mvn liquibase:generateChangeLog
