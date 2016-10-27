




echo $PATH
DB_PATH=/var/lib/mysql-files/applifire/db/IH7ESZXRGDU63J1T2TZDW
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
PORT=3306
HOST=localhost
echo 'drop db starts at ' $(date)
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends at ' $(date)

