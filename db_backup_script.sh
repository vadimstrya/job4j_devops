#!/bin/bash

# Параметры базы данных
PG_USER="postgres"
PG_PASSWORD="postgres"
PG_DATABASE="job4j_devops"
PG_HOST="localhost"   # или укажите IP/hostname, если требуется

# Настройка временной метки и папки для бэкапов
TIMESTAMP=$(date +"%F_%H-%M-%S")
BACKUP_DIR="/backup/postgresql"
mkdir -p "$BACKUP_DIR"

# Имя файла бэкапа
BACKUP_FILE="$BACKUP_DIR/${PG_DATABASE}_$TIMESTAMP.sql"

# Экспорт переменной для пароля (не рекомендуется хранить пароль в скрипте, можно использовать .pgpass)
export PGPASSWORD="$PG_PASSWORD"

# Создание бэкапа с помощью pg_dump
pg_dump -U "$PG_USER" -h "$PG_HOST" "$PG_DATABASE" > "$BACKUP_FILE"

# Проверка успешности создания бэкапа
if [ $? -eq 0 ]; then
    echo "Бэкап успешно создан: $BACKUP_FILE"
    
    # Сжатие бэкапа с помощью gzip
    gzip "$BACKUP_FILE"
    if [ $? -eq 0 ]; then
        echo "Бэкап успешно сжат: ${BACKUP_FILE}.gz"
    else
        echo "Ошибка при сжатии бэкапа"
        exit 1
    fi
else
    echo "Ошибка при создании бэкапа"
    exit 1
fi
