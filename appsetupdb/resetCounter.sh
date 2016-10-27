




echo $PATH
OSNAME=`uname -s`
DB_PATH=/var/lib/mysql-files/applifire/db/IH7ESZXRGDU63J1T2TZDW
ART_CREATE_PATH=/var/lib/mysql-files/applifire/db/IH7ESZXRGDU63J1T2TZDW/art/create
AST_CREATE_PATH=/var/lib/mysql-files/applifire/db/IH7ESZXRGDU63J1T2TZDW/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=Glass4#21
APPLFIREHOST=localhost

if [ $OSNAME = "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=appsetup
USER=algo
PASSWORD=algo
PORT=3306
HOST=localhost


echo 'resetCounter Starts...'

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "ALTER TABLE AddressMap AUTO_INCREMENT = 1; ALTER TABLE EmailContactMap AUTO_INCREMENT = 1; ALTER TABLE PhoneContactMap AUTO_INCREMENT = 1; ALTER TABLE SocialContactMap AUTO_INCREMENT = 1; ";

echo 'resetCounter ends...'

