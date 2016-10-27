




echo $PATH
OSNAME=`uname -s`
ART_DATA_PATH=/var/lib/mysql-files/applifire/db/IH7ESZXRGDU63J1T2TZDW/art/data
AST_DATA_PATH=/var/lib/mysql-files/applifire/db/IH7ESZXRGDU63J1T2TZDW/ast/data
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


echo ' load ART data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_query.csv' into table art_query FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_report_ui.csv' into table art_report_ui FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_component_config.csv' into table art_component_config FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_severity_m.csv' into table art_log_severity_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_json.csv' into table art_chart_json FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_category.csv' into table art_chart_category FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_type.csv' into table art_chart_type FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_data_field_json.csv' into table art_chart_data_field_json FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_lang_master.csv' into table art_lang_master FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_properties.csv' into table art_chart_properties FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_prop_language.csv' into table art_chart_prop_language FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_chart_template.csv' into table art_chart_template FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_config_m.csv' into table art_log_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_config_attributes_m.csv' into table art_log_config_attributes_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_connector_m.csv' into table art_log_connector_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_connector_attributes_m.csv' into table art_log_connector_attributes_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_password_algorithm.csv' into table art_password_algorithm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_password_policy.csv' into table art_password_policy FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_health_scheduler_config_m.csv' into table art_health_scheduler_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_health_status_config_m.csv' into table art_health_status_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/cloud_drive_file_content_type.csv' into table cloud_drive_file_content_type FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/cloud_drive_tags.csv' into table cloud_drive_tags FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_external_integration.csv' into table art_external_integration FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_job_details.csv' into table art_job_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_scheduler_details.csv' into table art_scheduler_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_schedule_config.csv' into table art_schedule_config FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_app_notification_template.csv' into table art_app_notification_template FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_bounded_context.csv' into table art_bounded_context FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_domain.csv' into table art_domain FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_alarm.csv' into table art_log_alarm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_architecture_layer_m.csv' into table art_log_architecture_layer_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_status_m.csv' into table art_log_status_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_event_action_m.csv' into table art_log_event_action_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_exception_m.csv' into table art_log_exception_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_document_template.csv' into table art_document_template FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_SMSConfig_M.csv' into table art_SMSConfig_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

echo 'load ART data ends at ' $(date)

echo ' load AST data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Timezone.csv' into table Timezone FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Language.csv' into table Language FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Country.csv' into table Country FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/State.csv' into table State FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/City.csv' into table City FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/AddressType.csv' into table AddressType FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Address.csv' into table Address FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/ContactType.csv' into table ContactType FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Gender.csv' into table Gender FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Title.csv' into table Title FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/CoreContacts.csv' into table CoreContacts FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/AddressMap.csv' into table AddressMap FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/EmailCategory.csv' into table EmailCategory FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PhoneCategory.csv' into table PhoneCategory FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/SocialCategory.csv' into table SocialCategory FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserAccessLevel.csv' into table UserAccessLevel FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserAccessDomain.csv' into table UserAccessDomain FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/User.csv' into table UserDetail FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Login.csv' into table Login FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/LoginSession.csv' into table LoginSession FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PassRecovery.csv' into table PassRecovery FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserData.csv' into table UserData FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/SessionData.csv' into table SessionData FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Roles.csv' into table Roles FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserRoleBridge.csv' into table UserRoleBridge FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

echo ' load AST data ends at ' $(date)


echo 'load Predefined AST data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/AppMenus.csv' into table AppMenus FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (menuId,menuTreeId,menuIcon,menuAction,menuCommands,menuDisplay,menuHead,menuLabel,uiType,RefObjectId,menuAccessRights,appType,appId,autoSave,updatedBy,updatedDate,createdBy,createdDate,versionId,activeStatus,txnAccessCode,autoSave,expiryDate)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/RoleMenuBridge.csv' into table RoleMenuBridge FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (roleMenuMapId,roleId,menuId,isRead,isWrite,isExecute,updatedBy,updatedDate,createdBy,createdDate,versionId,activeStatus,txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PasswordPolicy.csv' into table PasswordPolicy FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (policyId, policyName, policyDescription, maxPwdLength,  minPwdLength, minCapitalLetters, minSmallLetters, minNumericValues, minSpecialLetters, allowedSpecialLetters, createdBy, createdDate,  updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Question.csv' into table Question FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (questionId, levelid, question, questionDetails, questionIcon,createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus,txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PasswordAlgo.csv' into table PasswordAlgo FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (algoId, algorithm, algoName, algoDescription, algoIcon, algoHelp, createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus,txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/JwtConfig.csv' into table JwtConfig FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (configId, jwtAlgorithm, expiration, tokenKey, createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus)";

echo 'load Predefined AST data ends at ' $(date)
