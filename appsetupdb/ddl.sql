DROP TABLE IF EXISTS `art_query`;

CREATE TABLE `art_query` (
`query_id` varchar(64) NOT NULL ,
`jpql_query` text,
`query_type` tinyint(1) DEFAULT NULL,
`query_json` text,
`name` varchar(256) DEFAULT NULL,
`hidden_name` varchar(256) DEFAULT NULL,
`app_creator_id` varchar(256) DEFAULT NULL,
`project_id` varchar(256) DEFAULT NULL,
`project_version_id` varchar(256) DEFAULT NULL,
`created_by` int(11) DEFAULT NULL,
`created_date` datetime DEFAULT NULL,
`updated_by` int(11) DEFAULT NULL,
`updated_date` datetime DEFAULT NULL,
`version_id` int(11) DEFAULT NULL,
`active_status` tinyint(1) DEFAULT NULL,
`sql_query` text,
`user_access` tinyint(1) DEFAULT '0',
PRIMARY KEY (`query_id`)
);

DROP TABLE IF EXISTS `art_report_ui`;

CREATE TABLE `art_report_ui` (
  `report_name` VARCHAR(256) DEFAULT NULL,
  `report_id` VARCHAR(64) NOT NULL,
  `query_id` VARCHAR(64) DEFAULT NULL,
  `data_json` TEXT,
  `chart_json` LONGTEXT,
  `created_by` VARCHAR(64) NOT NULL,
  `created_date` DATE NOT NULL,
  `updated_by` VARCHAR(64) NOT NULL,
  `updated_date` DATE NOT NULL,
  `project_id` VARCHAR(256) NOT NULL,
  `project_version_id` INT(11) NOT NULL,
  `app_creator_id` VARCHAR(256) NOT NULL,
  `active_status` INT(1) NOT NULL,
  `version_id` INT(11) NOT NULL,
  `search_json` TEXT,
  `advance_config_json` VARCHAR(4000),
  PRIMARY KEY (`report_id`)
) ;

DROP TABLE IF EXISTS `art_component_config`;

CREATE TABLE `art_component_config` (
  `id` varchar(64) NOT NULL,
  `component_name` varchar(256) NOT NULL,
  `initializer` varchar(256) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `project_id` varchar(256) NOT NULL,
  `project_version_id` varchar(256) DEFAULT NULL,
  `app_creator_id` varchar(256) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `active_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `art_log_severity_m`;

CREATE TABLE `art_log_severity_m` ( `severityId` int(11) NOT NULL, `severity` varchar(64) NOT NULL, `versionId` INT(11) DEFAULT NULL, `createdBy` INT(11) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` INT(11) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`severityId`) ) ;

DROP TABLE IF EXISTS `art_chart_json`;

CREATE TABLE `art_chart_json` (

  `chart_json_id` varchar(64) NOT NULL ,

  `chart_json` longtext NOT NULL,

  `json_data_structure` longtext NOT NULL,

  `java_class` varchar(2048) DEFAULT NULL,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `active_status` int(1) NOT NULL,

  `data_field_id` VARCHAR(64) DEFAULT NULL,

  PRIMARY KEY (`chart_json_id`)

);

DROP TABLE IF EXISTS `art_chart_category`;

CREATE TABLE `art_chart_category` (

  `chart_id` varchar(64) NOT NULL ,

  `chart_tree_id` varchar(30) NOT NULL,

  `chart_label` varchar(100) NOT NULL,

  `chart_description` varchar(200) DEFAULT NULL,

  `chart_json_id` VARCHAR(64) NOT NULL,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `active_status` int(1) NOT NULL,

  `chart_point` int(1) NOT NULL DEFAULT '0',

  PRIMARY KEY (`chart_id`),

  KEY `Foreign Key` (`chart_json_id`),

  CONSTRAINT `Foreign Key` FOREIGN KEY (`chart_json_id`) REFERENCES `art_chart_json` (`chart_json_id`) ON DELETE CASCADE

);



DROP TABLE IF EXISTS `art_chart_type`;

CREATE TABLE `art_chart_type` (

  `chart_type_id` varchar(64) NOT NULL ,

  `chart_id` VARCHAR(64) NOT NULL,

  `chart_type` varchar(100) NOT NULL,

  `chart_default` varchar(1) NOT NULL,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `active_status` int(1) NOT NULL,

  PRIMARY KEY (`chart_type_id`),

  KEY `ForeignKey` (`chart_id`),

  CONSTRAINT `ForeignKey` FOREIGN KEY (`chart_id`) REFERENCES `art_chart_category` (`chart_id`) ON DELETE CASCADE

);

DROP TABLE IF EXISTS `art_chart_data_field_json`;

CREATE TABLE `art_chart_data_field_json` (

  `data_field_id` varchar(64) NOT NULL ,

  `data_field_name` varchar(256) DEFAULT NULL,

  `data_field_json` text,

  `created_by` int(11) DEFAULT NULL,

  `created_date` date DEFAULT NULL,

  `updated_by` int(11) DEFAULT NULL,

  `updated_date` date DEFAULT NULL,

  `version_id` int(11) DEFAULT NULL,

  `active_status` int(1) DEFAULT NULL,

  PRIMARY KEY (`data_field_id`)

);

DROP TABLE IF EXISTS `art_lang_master`;

CREATE TABLE `art_lang_master` (

  `lang_id` varchar(64) NOT NULL ,

  `lang_name` varchar(50) DEFAULT NULL,

  `country_code` varchar(10) NOT NULL,

  `country_name` varchar(100) DEFAULT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `active_status` int(1) NOT NULL,

  PRIMARY KEY (`lang_id`,`country_code`)

);

DROP TABLE IF EXISTS `art_chart_properties`;

CREATE TABLE `art_chart_properties` (

  `property_id` varchar(64) NOT NULL,

  `property_name` varchar(300) NOT NULL,

  `widgets` varchar(300) NOT NULL,

  `widgets_json` text,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `active_status` int(1) NOT NULL,

  PRIMARY KEY (`property_id`)

);

DROP TABLE IF EXISTS `art_chart_prop_language`;

CREATE TABLE `art_chart_prop_language` (

  `label_id` varchar(64) NOT NULL,

  `property_id` varchar(64) NOT NULL,

  `display_name` varchar(200) NOT NULL,

  `lang_id` varchar(64) NOT NULL,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `active_status` int(1) NOT NULL,

  PRIMARY KEY (`label_id`),

  KEY `property_id` (`property_id`),

  KEY `art_mst_chart_prop_language`	 (`lang_id`),

  CONSTRAINT `art_chart_prop_language` FOREIGN KEY (`lang_id`) REFERENCES `art_lang_master` (`lang_id`),

  CONSTRAINT `art_chart_prop_language1` FOREIGN KEY (`property_id`) REFERENCES `art_chart_properties` (`property_id`)

);

DROP TABLE IF EXISTS `art_chart_template`;

CREATE TABLE `art_chart_template` (

  `template_id` varchar(64) NOT NULL ,

  `template_name` varchar(500) NOT NULL,

  `template_json` longtext NOT NULL,

  `created_by` int(11) NOT NULL,

  `created_date` date NOT NULL,

  `updated_by` int(11) NOT NULL,

  `updated_date` date NOT NULL,

  `version_id` int(11) NOT NULL,

  `project_id` VARCHAR(256),

  `project_version_id` INT(11),

  `active_status` int(1) NOT NULL,

  `app_creator_id` varchar(256),

  PRIMARY KEY (`template_id`)

);

DROP TABLE IF EXISTS `art_report_builder_details`;

CREATE TABLE `art_report_builder_details` (
  `report_id` VARCHAR(64) NOT NULL,
  `report_name` VARCHAR(256) NOT NULL,
  `report_synopsis` TEXT,
  `report_help` TEXT,
  `query_criteria_json` TEXT,
  `grid_conf_json` TEXT,
  `chart_properties` TEXT,
  `drilldown_json` TEXT,
  `dataEndPoint_json` TEXT,
  `created_by` VARCHAR(64) NOT NULL,
  `created_date` DATE NOT NULL,
  `updated_by` VARCHAR(64) NOT NULL,
  `updated_date` DATE NOT NULL,
  `project_id` VARCHAR(256) NOT NULL,
  `project_version_id` INT(11) NOT NULL,
  `app_creator_id` VARCHAR(256) NOT NULL,
  `active_status` INT(1) NOT NULL,
  `version_id` INT(11) NOT NULL,
  `query_info` TEXT,
  `other_properties_json` TEXT,
  `search_json` TEXT,
  `edit_flag` INT(1) DEFAULT NULL,
  `bounded_context` VARCHAR(64) DEFAULT NULL,
  `system_flag` INT(1) DEFAULT '0',
  `data_browser` INT(1) DEFAULT '0',
  `advance_config_json` VARCHAR(4000),
  PRIMARY KEY (`report_id`)
) ;

DROP TABLE IF EXISTS `art_log_config_m`;

CREATE TABLE `art_log_config_m` ( `logConfigId` varchar(64) NOT NULL, `configData` longtext, `versionId` INT(11) DEFAULT NULL, `createdBy` INT(11) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` INT(11) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`logConfigId`) );

DROP TABLE IF EXISTS `art_log_config_attributes_m`;

CREATE TABLE `art_log_config_attributes_m` ( `attributeId` varchar(64) NOT NULL , `logConfigId` varchar(64) NOT NULL , `attributeName` VARCHAR(256) DEFAULT NULL, `attributeValue` VARCHAR(256) DEFAULT NULL, `attributeComment` VARCHAR(1000) DEFAULT NULL, `attributeDisplayName` VARCHAR(256) DEFAULT NULL, `versionId` INT(11) DEFAULT NULL, `createdBy` INT(11) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` INT(11) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`attributeId`), KEY `LogFK` (`logConfigId`), CONSTRAINT `ArtLogFK` FOREIGN KEY (`logConfigId`) REFERENCES `art_log_config_m` (`logConfigId`) ) ;

DROP TABLE IF EXISTS `art_log_connector_m`;

CREATE TABLE `art_log_connector_m` ( `connectorId`  varchar(64) NOT NULL , `logConfigId` varchar(64) NOT NULL , `connectorLogLevel` INT(11) NOT NULL, `cid` INT(11) NOT NULL, `id` VARCHAR(256) DEFAULT NULL, `enabled` TINYINT(1) NOT NULL, `connectorName` VARCHAR(256) NOT NULL, `className` VARCHAR(256) NOT NULL, `systemDefined` TINYINT(1) DEFAULT NULL, `versionId` INT(11) DEFAULT NULL, `createdBy` INT(11) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` INT(11) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`connectorId`), KEY `LogConfigFK` (`logConfigId`), CONSTRAINT `ArtLogConfigFK` FOREIGN KEY (`logConfigId`) REFERENCES `art_log_config_m` (`logConfigId`) ) ;

DROP TABLE IF EXISTS `art_log_connector_attributes_m`;

CREATE TABLE `art_log_connector_attributes_m` ( `attributeId` varchar(64) NOT NULL, `connectorId` varchar(64) NOT NULL , `attributeName` VARCHAR(256) NOT NULL, `attributeValue` VARCHAR(256) NOT NULL, `attributeComment` VARCHAR(1024) DEFAULT NULL, `attributeDisplayName` VARCHAR(256) DEFAULT NULL, `versionId` INT(11) DEFAULT NULL, `createdBy` INT(11) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` INT(11) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`attributeId`), KEY `ConnectorFK` (`connectorId`), CONSTRAINT `ArtConnectorFK` FOREIGN KEY (`connectorId`) REFERENCES `art_log_connector_m` (`connectorId`) );

DROP TABLE IF EXISTS `zen_health`;

CREATE TABLE `zen_health` (  `monitorId` varchar(64) NOT NULL,  `classUnloaded` int(11) NOT NULL,  `gcCollectionTime` int(11) NOT NULL,  `nonHeapCommitted` bigint(20) NOT NULL,  `startTime` datetime NOT NULL,  `cpuTime` double NOT NULL,  `cpuLoadTime` double NOT NULL DEFAULT '0',  `nonHeapUsed` bigint(20) NOT NULL,  `upTime` int(11) NOT NULL,  `freePhysicalSize` bigint(20) NOT NULL,  `totalThread` double NOT NULL,  `heapCommitted` bigint(20) NOT NULL,  `totalPhysicalMemorySize` bigint(20) NOT NULL,  `threadPeakCount` double NOT NULL,  `scheduledDateTime` datetime NOT NULL,  `maxMemory` bigint(20) NOT NULL,  `cpuLoad` double NOT NULL DEFAULT '0',  `threadStartedCount` double NOT NULL,  `threadDaemonCount` double NOT NULL,  `serverInstanceId` int(32) NOT NULL,  `totalClass` bigint(20) NOT NULL,  `freeMemory` bigint(20) NOT NULL,  `freeSwapSize` bigint(20) NOT NULL,  `serverIpAddress` varchar(45) NOT NULL,  `classLoaded` bigint(20) NOT NULL,  `heapUsed` bigint(20) NOT NULL,  `systemLoadAverage` double NOT NULL,  `threadCpuTime` double NOT NULL,  `totalMemory` bigint(20) NOT NULL,  `totalSwapSize` bigint(20) NOT NULL,  `availableProcessor` double NOT NULL,  `gcMemoryName` varchar(256) NOT NULL,  `threadUserTime` varchar(45) DEFAULT NULL,  `usedMemory` bigint(20) DEFAULT NULL,  `usedSwapSize` bigint(20) DEFAULT NULL,  `usedPhysicalMemory` bigint(20) DEFAULT NULL,  PRIMARY KEY (`monitorId`));

DROP TABLE IF EXISTS `zen_health_counter`;

CREATE TABLE `zen_health_counter` (  `counterId` varchar(64) NOT NULL ,  `serverInstanceId` int(11) NOT NULL,  `httpStatus` varchar(256) NOT NULL,  `serverIpAddress` varchar(256) NOT NULL,  `serviceName` varchar(32) NOT NULL,  `scheduledDateTime` datetime NOT NULL,  `statusCount` int(2) NOT NULL,  `methodName` varchar(100) NOT NULL,  `counterType` int(4) NOT NULL,  PRIMARY KEY (`counterId`)) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `zen_health_gauge`;

CREATE TABLE `zen_health_gauge` (  `gaugeId` varchar(64) NOT NULL ,  `scheduledDateTime` datetime NOT NULL,  `methodHitCount` double NOT NULL,  `serverInstanceId` int(11) NOT NULL,  `methodHitTime` double NOT NULL,  `serverIpAddress` varchar(256) NOT NULL,  `serviceName` varchar(256) NOT NULL,  `methodName` varchar(32) NOT NULL,  PRIMARY KEY (`gaugeId`));

DROP TABLE IF EXISTS `zen_health_status`;

CREATE TABLE `zen_health_status` (  `healthId` varchar(64) NOT NULL ,  `scheduledDateTime` datetime NOT NULL,  `freeSpace` double NOT NULL,  `status` varchar(256) NOT NULL,  `serverInstanceId` int(3) NOT NULL,  `usedSpace` double NOT NULL,  `serverIpAddress` varchar(30) NOT NULL,  `totalSpace` double NOT NULL,  `errorDescription` varchar(300), PRIMARY KEY (`healthId`)) ;

DROP TABLE IF EXISTS `zen_request_details`;

CREATE TABLE `zen_request_details` ( `requestId` varchar(46) NOT NULL,  `className` varchar(150) DEFAULT NULL,  `ipAddress` varchar(45) DEFAULT NULL,  `requestTime` datetime DEFAULT NULL,   `requestMethod` varchar(45) DEFAULT NULL,  `httpStatus` varchar(45) DEFAULT NULL,  `returnStatus` varchar(45) DEFAULT NULL,  `executionTime` double DEFAULT NULL,   `methodName` varchar(45) DEFAULT NULL,   `startTime` DATETIME DEFAULT NULL,   `endTime` datetime NOT NULL,  `typeofClass` varchar(11) DEFAULT NULL,   `callSeqId` int(11) DEFAULT NULL,   `exceptionId` int(11) DEFAULT NULL,   `userId` varchar(45) DEFAULT NULL,   `appSessionId` varchar(45) DEFAULT NULL ,`customerId` varchar(45) DEFAULT NULL);

DROP TABLE IF EXISTS `art_loginSession`;

CREATE TABLE `art_loginSession` (
  `sessionid` varchar(45) NOT NULL,
  `loginTime` timestamp NULL DEFAULT NULL,
  `logoutTime` timestamp NULL DEFAULT NULL,
  `clientIPAddress` varchar(45) DEFAULT NULL,
  `clientIPAddressInt` int(11) DEFAULT NULL,
  `clientNetworkAddress` int(11) DEFAULT NULL,
  `clientBrowser` varchar(1000) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sessionid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `art_session_data`;

CREATE TABLE `art_session_data` (
  `sessionid` varchar(45) NOT NULL,
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `sessionDataType` varchar(45) DEFAULT NULL,
  `sessionData` text,
  `version_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `art_user_last_status`;

CREATE TABLE `art_user_last_status` (

  `id` VARCHAR(64) NOT NULL,

  `user_id` VARCHAR(64) DEFAULT NULL,

  `menu_id` VARCHAR(64) DEFAULT NULL,

  `json` TEXT,

  `project_id` VARCHAR(256) DEFAULT NULL,

  `project_version_id` VARCHAR(256) DEFAULT NULL,

  `updated_by` INT(11) DEFAULT NULL,

  `updated_date` DATETIME DEFAULT NULL,

  `created_by` INT(11) DEFAULT NULL,

  `created_date` DATETIME DEFAULT NULL,

  `version_id` INT(11) DEFAULT NULL,

  `active_status` TINYINT(1) DEFAULT NULL,

  `app_creator_id` VARCHAR(256) DEFAULT NULL,

  PRIMARY KEY (`id`)

) ;

DROP TABLE IF EXISTS `art_password_algorithm`;

CREATE TABLE `art_password_algorithm` ( `algoId` varchar(256) NOT NULL, `algorithm` varchar(256) NOT NULL, `algoName` varchar(256) NOT NULL, `algoDescription` varchar(256) DEFAULT NULL, `active_status` int(1) DEFAULT '1', `created_date` datetime DEFAULT '1900-01-01 00:00:00', `updated_by` int(11) DEFAULT '-1', `version_id` int(11) DEFAULT '-1', `created_by` int(11) DEFAULT '-1', `updated_date` datetime DEFAULT '1900-01-01 00:00:00', PRIMARY KEY (`algoId`) );

DROP TABLE IF EXISTS `art_password_policy`;

CREATE TABLE `art_password_policy` ( `policyId` VARCHAR (256) NOT NULL, `policyName` VARCHAR (256) NOT NULL, `policyDescription` VARCHAR (512) NOT NULL, `minPwdLength` INT (11) NOT NULL, `maxPwdLength` INT (11) NOT NULL, `minCapitalLetters` INT (11) NOT NULL, `minSmallLetters` INT (11) NOT NULL, `minNumericValues` INT (11) NOT NULL, `minSpecialLetters` INT (11) NOT NULL, `allowedSpecialLetters` VARCHAR (256) DEFAULT NULL, `active_status` INT (1) DEFAULT '1', `version_id` INT (11) DEFAULT '-1', `updated_date` DATETIME DEFAULT '1900-01-01 00:00:00', `updated_by` INT (11) DEFAULT '-1', `created_date` DATETIME DEFAULT '1900-01-01 00:00:00', `created_by` INT (11) DEFAULT '-1', PRIMARY KEY (`policyId`) ) ;

DROP TABLE IF EXISTS `art_health_scheduler_config_m`;

CREATE TABLE `art_health_scheduler_config_m` ( `schedulerId` varchar(64) NOT NULL , `schedulerkey` VARCHAR(45) DEFAULT NULL, `refreshTime` INT(11) DEFAULT NULL, `refreshUnit` VARCHAR(45) DEFAULT NULL, `batchSize` INT(11) DEFAULT NULL, `enabled` VARCHAR(45) DEFAULT NULL, `connectorClass` VARCHAR(100) DEFAULT NULL, `dataModel` VARCHAR(100) DEFAULT NULL, `schedulerName` VARCHAR(45) DEFAULT NULL, `threadPoolSize` INT(11) DEFAULT NULL, `version_id` INT(11) DEFAULT NULL, `created_by` VARCHAR(45) DEFAULT NULL, `created_date` TIMESTAMP NULL DEFAULT NULL, `updated_by` VARCHAR(45) DEFAULT NULL, `updated_date` DATETIME DEFAULT NULL, `active_status` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`schedulerId`) );

DROP TABLE IF EXISTS `art_health_status_config_m`;

CREATE TABLE `art_health_status_config_m` ( `statusConfigId` varchar(64) NOT NULL , `diskPath` VARCHAR(100) DEFAULT NULL, `diskThreshold` INT(11) DEFAULT NULL, `executeSql` VARCHAR(250) DEFAULT NULL, `version_id` INT(11) DEFAULT NULL, `created_by` VARCHAR(45) DEFAULT NULL, `created_date` TIMESTAMP NULL DEFAULT NULL, `updated_by` VARCHAR(45) DEFAULT NULL, `updated_date` DATETIME DEFAULT NULL, `active_status` TINYINT(1) DEFAULT NULL, PRIMARY KEY (`statusConfigId`) );

DROP TABLE IF EXISTS `cloud_drive_file_content_type`;

CREATE TABLE `cloud_drive_file_content_type` ( `id` int(11) NOT NULL AUTO_INCREMENT, `application_type` varchar(300) NOT NULL, `file_mime_type` varchar(300) DEFAULT NULL, `extension` varchar(25) DEFAULT NULL, `image` varchar(25) DEFAULT NULL, PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `cloud_drive_tags`;

CREATE TABLE `cloud_drive_tags` ( `tag_id` varchar(64) NOT NULL, `tag_hierachy` VARCHAR(100) DEFAULT NULL, `tag_name` VARCHAR(50) DEFAULT NULL, `tag_image` VARCHAR(50) DEFAULT NULL, `created_by` VARCHAR(64) DEFAULT NULL, `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `updated_by` VARCHAR(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `version_id` INT(11) DEFAULT NULL, `active_status` INT(1) DEFAULT NULL, `app_creator_id` VARCHAR(64) DEFAULT NULL, KEY `tag_id` (`tag_id`) );

DROP TABLE IF EXISTS `cloud_drive_files`;

CREATE TABLE `cloud_drive_files` ( `file_id` varchar(64) NOT NULL, `file_name` VARCHAR(50) DEFAULT NULL, `file_extension` VARCHAR(100) DEFAULT NULL, `file_application_type` VARCHAR(300) DEFAULT NULL, `file_mime_type` VARCHAR(300) DEFAULT NULL, `system_name` VARCHAR(100) DEFAULT NULL, `system_path` VARCHAR(500) DEFAULT NULL, `size1` DECIMAL(10,0) DEFAULT NULL, `file_scope` CHAR(1) DEFAULT NULL, `created_by` VARCHAR(64) DEFAULT NULL, `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `updated_by` VARCHAR(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `version_id` VARCHAR(11) DEFAULT NULL, `app_creator_id` VARCHAR(64) DEFAULT NULL, `active_status` INT(1) DEFAULT NULL, PRIMARY KEY (`file_id`), KEY `file_id` (`file_id`), KEY `fk_file_content_id` (`file_mime_type`) );

DROP TABLE IF EXISTS `cloud_drive_tags_file`;

CREATE TABLE `cloud_drive_tags_file` ( `id` int(11) NOT NULL AUTO_INCREMENT, `file_id` varchar(64) DEFAULT NULL, `tag_id` varchar(64) DEFAULT NULL, PRIMARY KEY (`id`), KEY `fk_file_id` (`file_id`), CONSTRAINT `fk_file_id` FOREIGN KEY (`file_id`) REFERENCES `cloud_drive_files` (`file_id`) );

DROP TABLE IF EXISTS `cloud_drive_file_versions`;

CREATE TABLE `cloud_drive_file_versions` ( `base_file_Id` varchar(64) DEFAULT NULL, `file_id` varchar(64) NOT NULL, `file_version_id` INT(5) NOT NULL, `created_by` VARCHAR(64) DEFAULT NULL, `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `updated_by` VARCHAR(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `version_id` INT(5) DEFAULT NULL, `active_status` INT(1) DEFAULT NULL, PRIMARY KEY (`file_id`,`file_version_id`), CONSTRAINT `fk_file_version_id` FOREIGN KEY (`file_id`) REFERENCES `cloud_drive_files` (`file_id`) );

DROP TABLE IF EXISTS `cloud_drive_tag_shared`;

CREATE TABLE `cloud_drive_tag_shared` ( `tag_id` int(11) DEFAULT NULL, `shared_user_id` varchar(64) DEFAULT NULL, `updated_by` varchar(64) DEFAULT NULL, `updated_date` timestamp NULL DEFAULT NULL, `created_by` varchar(64) DEFAULT NULL, `created_date` timestamp NULL DEFAULT  NULL, `version_id` int(11) DEFAULT NULL, `active_status` int(1) DEFAULT NULL, `id` varchar(64) NOT NULL, PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `cloud_drive_file_shared`;

CREATE TABLE `cloud_drive_file_shared` ( `file_id` varchar(64) DEFAULT NULL, `shared_user_id` varchar(64) DEFAULT NULL, `updated_by` varchar(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `created_by` varchar(64) DEFAULT NULL, `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP, `version_id` int(11) DEFAULT NULL, `active_status` int(1) DEFAULT NULL, `id` varchar(64) NOT NULL, PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `art_external_integration`;

CREATE TABLE `art_external_integration` (
  `integration_id` varchar(64) NOT NULL,
  `integration_name` VARCHAR(500) DEFAULT NULL,
  `integration_config_json` TEXT,
  `integration_dsl` TEXT,
  `project_id` VARCHAR(50) DEFAULT NULL,
  `project_version_id` VARCHAR(50) DEFAULT NULL,
  `created_by` VARCHAR(64) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `app_creator_id` VARCHAR(500) DEFAULT NULL,
  `updated_by` VARCHAR(64) DEFAULT NULL,
  `updated_date` DATETIME DEFAULT NULL,
  `version_id` INT(16) DEFAULT NULL,
  `active_status` TINYINT(1) DEFAULT NULL,
 `connectorId`  varchar(50) DEFAULT NULL,
  PRIMARY KEY (`integration_id`)
);



DROP TABLE IF EXISTS `art_job_details`;

CREATE TABLE `art_job_details` (
  `jobId` varchar(64) NOT NULL,
  `jobName` varchar(128) DEFAULT NULL,
  `uiJson` text NOT NULL,
  `processJson` text,
  `project_id` varchar(256) DEFAULT NULL,
  `app_creator_id` varchar(256) DEFAULT NULL,
  `project_version_id` varchar(256) DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `active_status` tinyint(1) DEFAULT NULL,
  `beanName` varchar(64) DEFAULT NULL,
  `currentStatus` varchar(64) DEFAULT NULL,
  `statusTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`jobId`)
);

DROP TABLE IF EXISTS `art_scheduler_details`;

CREATE TABLE `art_scheduler_details` (
  `schedulerId` varchar(64) NOT NULL,
  `schedulerExpression` varchar(64) DEFAULT NULL,
  `jobId` varchar(64) DEFAULT NULL,
  `project_id` varchar(64) DEFAULT NULL,
  `app_creator_id` varchar(64) DEFAULT NULL,
  `project_version_id` varchar(64) DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `active_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`schedulerId`)
) ;

DROP TABLE IF EXISTS `art_schedule_config`;

CREATE TABLE `art_schedule_config` ( `schedule_id` VARCHAR(64) NOT NULL, `schedule_name` VARCHAR(256) NOT NULL, `schedule_job` VARCHAR(64) NOT NULL, `scheduler_expression` VARCHAR(256) DEFAULT NULL, `schedule_strategy` LONGTEXT NOT NULL, `created_by` VARCHAR(64) DEFAULT '-1', `created_date` DATETIME DEFAULT '1900-01-01 00:00:00', `updated_by` VARCHAR(64) DEFAULT '-1', `updated_date` DATETIME DEFAULT '1900-01-01 00:00:00', `version_id` INT(11) DEFAULT '-1', `active_status` INT(1) DEFAULT '1', PRIMARY KEY (`schedule_id`) ) ;

DROP TABLE IF EXISTS `art_app_notification_template`;

CREATE TABLE `art_app_notification_template` ( `template_id` VARCHAR (64) NOT NULL, `template_name` VARCHAR (256) NOT NULL, `template` LONGTEXT, `notification_type` INT(11) NOT NULL,`templateAttributes` LONGTEXT, `created_by` VARCHAR (64) DEFAULT '-1', `created_date` DATETIME DEFAULT NULL, `updated_by` VARCHAR (64) DEFAULT '-1', `updated_date` DATETIME DEFAULT NULL, `active_status` INT (1) DEFAULT NULL, `version_id` INT (11) DEFAULT NULL, PRIMARY KEY (`template_id`) ) ;

DROP TABLE IF EXISTS `art_bounded_context`;

CREATE TABLE `art_bounded_context`(`boundedContextId` varchar(256) NOT NULL, `boundedContextName` varchar(256) NOT NULL, `hiddenContextName` varchar(256) NOT NULL, `alarmPrefix` varchar(5) NOT NULL, `isDefault` int(1) DEFAULT '0', `isSystemDefined` int(1) DEFAULT '0', `createdBy` varchar(64) DEFAULT '-1', `createdDate` datetime DEFAULT '1900-01-01 00:00:00', `updatedBy` varchar(64) DEFAULT '-1', `updatedDate` datetime DEFAULT '1900-01-01 00:00:00', `versionId` int(11) DEFAULT '-1', `activeStatus` int(1) DEFAULT '1', PRIMARY KEY (`boundedContextId`) );

DROP TABLE IF EXISTS `art_domain`;

CREATE TABLE `art_domain`(`domainId` varchar(256) NOT NULL, `boundedContextId` varchar(256) DEFAULT NULL, `hiddenContextName` varchar(256) NOT NULL, `domainName` varchar(256) NOT NULL, `alarmPrefix` varchar(5) NOT NULL, `isDefault` int(1) DEFAULT '0', `createdBy` varchar(64) DEFAULT '-1', `createdDate` datetime DEFAULT '1900-01-01 00:00:00', `updatedBy` varchar(64) DEFAULT '-1', `updatedDate` datetime DEFAULT '1900-01-01 00:00:00', `versionId` int(11) DEFAULT '-1', `activeStatus` int(1) DEFAULT '1', PRIMARY KEY (`domainId`), KEY `fk_art_domain_1_idx` (`boundedContextId`), CONSTRAINT `fk_art_domain_1` FOREIGN KEY (`boundedContextId`) REFERENCES `art_bounded_context` (`boundedContextId`) ON DELETE NO ACTION ON UPDATE NO ACTION);

DROP TABLE IF EXISTS `art_log_alarm`;

CREATE TABLE `art_log_alarm` (
 `loggerPkId` varchar(256) NOT NULL,
  `alarmType` int(10) NOT NULL,
  `alarmData` longtext,
  `alarmVersion` int(10) NOT NULL,
  `created_by` varchar(64) DEFAULT '-1',
  `created_date` datetime DEFAULT '1900-01-01 00:00:00',
  `updated_by` varchar(64) DEFAULT '-1',
  `updated_date` datetime DEFAULT '1900-01-01 00:00:00',
  `version_id` int(11) DEFAULT '-1',
  `active_status` int(1) DEFAULT '1',
  `app_creator_id` varchar(64) NOT NULL,
  PRIMARY KEY (`loggerPkId`)
);

DROP TABLE IF EXISTS `art_log_architecture_layer_m`;

CREATE TABLE `art_log_architecture_layer_m` (`layerId` INT (11) NOT NULL, `layerName` VARCHAR (256) NOT NULL, `versionId` INT (11) DEFAULT NULL, `createdBy` varchar (64) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` varchar (64) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT (1) DEFAULT NULL, PRIMARY KEY (`layerId`));

DROP TABLE IF EXISTS `art_log_status_m`;

CREATE TABLE `art_log_status_m` (`statusId` INT (11) NOT NULL, `alarmStatus` VARCHAR (256) NOT NULL, `versionId` INT (11) DEFAULT NULL, `createdBy` varchar (64) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` varchar (64) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT (1) DEFAULT NULL, PRIMARY KEY (`statusId`));

DROP TABLE IF EXISTS `art_log_event_action_m`;

CREATE TABLE `art_log_event_action_m` (`eventActionId` INT (11) NOT NULL, `eventAction` VARCHAR (256) NOT NULL, `versionId` INT (11) DEFAULT NULL, `createdBy` varchar (64) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` varchar (64) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT (1) DEFAULT NULL, PRIMARY KEY (`eventActionId`));

DROP TABLE IF EXISTS `art_log_exception_m`;

CREATE TABLE `art_log_exception_m` (`exceptionId` INT (11) NOT NULL, `exception` VARCHAR (256) NOT NULL, `rootException` VARCHAR (256) NOT NULL, `exceptionName` VARCHAR (256) NOT NULL, `versionId` INT (11) DEFAULT NULL, `createdBy` varchar (64) DEFAULT NULL, `createdDate` TIMESTAMP NULL DEFAULT NULL, `updatedBy` varchar (64) DEFAULT NULL, `updatedDate` DATETIME DEFAULT NULL, `activeStatus` TINYINT (1) DEFAULT NULL, PRIMARY KEY (`exceptionId`));

DROP TABLE IF EXISTS `art_log_events_t`;

CREATE TABLE `art_log_events_t` ( logeventId VARCHAR(64) NOT NULL, eventDateTime DATETIME DEFAULT NULL, eventDate DATE DEFAULT NULL, alarmId VARCHAR(15) DEFAULT NULL, multiTenantId VARCHAR(64) DEFAULT NULL, userId VARCHAR(64) DEFAULT NULL, userIpAddress VARCHAR(20) DEFAULT NULL, userPort INT(10) DEFAULT NULL, geoLatitude DOUBLE DEFAULT NULL, geoLongitude DOUBLE DEFAULT NULL, sessionId VARCHAR(64) DEFAULT NULL, requestId VARCHAR(64) DEFAULT NULL, boundedContextId VARCHAR(5) DEFAULT NULL, domainId VARCHAR(5) DEFAULT NULL, className VARCHAR(500) DEFAULT NULL, methodName VARCHAR(200) DEFAULT NULL, message VARCHAR(4000) DEFAULT NULL, exception VARCHAR(3000) DEFAULT NULL, context INT(5) DEFAULT NULL, severity INT(5) DEFAULT NULL, layerId INT(5) DEFAULT NULL, eventAction INT(5) DEFAULT NULL, alarmStatus INT(5) DEFAULT NULL, PRIMARY KEY (logeventId), CONSTRAINT fk_severity FOREIGN KEY (severity) REFERENCES art_log_severity_m (severityId), CONSTRAINT fk_layer FOREIGN KEY (layerId) REFERENCES art_log_architecture_layer_m (layerId), CONSTRAINT fk_event_action FOREIGN KEY (eventAction) REFERENCES art_log_event_action_m (eventActionId), CONSTRAINT fk_status FOREIGN KEY (alarmStatus) REFERENCES art_log_status_m (statusId));

DROP TABLE IF EXISTS `art_document_template`;

CREATE TABLE art_document_template ( doc_temp_Id VARCHAR (256) NOT NULL, doc_name VARCHAR (256) DEFAULT NULL, doc_type INT (11) DEFAULT NULL, doc_template LONGTEXT, created_by VARCHAR (256) DEFAULT '-1', created_date DATETIME DEFAULT NULL, updated_by VARCHAR (256) DEFAULT '-1', updated_date DATETIME DEFAULT NULL, active_status TINYINT (1) DEFAULT NULL, version_id INT (11) DEFAULT NULL, PRIMARY KEY (doc_temp_Id) ) ;

DROP TABLE IF EXISTS `art_SMSConfig_M`;

CREATE TABLE `art_SMSConfig_M` ( `configId` VARCHAR(256) NOT NULL, `configName` VARCHAR(256) NOT NULL, `jsonData` TEXT NOT NULL, `baseUrl` VARCHAR(256) NOT NULL,`configurationBean` varchar(256) NOT NULL,`created_by` VARCHAR (64) DEFAULT '-1', `created_date` DATETIME DEFAULT NULL, `updated_by` VARCHAR (64) DEFAULT '-1', `updated_date` DATETIME DEFAULT NULL, `active_status` INT (1) DEFAULT NULL, `version_id` INT (11) DEFAULT NULL, PRIMARY KEY (`configId`));

DROP TABLE IF EXISTS `art_SMSNotification_M`;

CREATE TABLE `art_SMSNotification_M` ( `notificationId` VARCHAR(256) NOT NULL, `mobileNo` VARCHAR(256) NULL DEFAULT NULL, `smsText` VARCHAR(256) NULL DEFAULT NULL, `isSent` TINYINT(1) NULL DEFAULT NULL, `sendDate` TIMESTAMP NULL DEFAULT NULL, `responseStatus` VARCHAR(256) NULL DEFAULT NULL,`created_by` VARCHAR (64) DEFAULT '-1', `created_date` DATETIME DEFAULT NULL, `updated_by` VARCHAR (64) DEFAULT '-1', `updated_date` DATETIME DEFAULT NULL, `active_status` INT (1) DEFAULT NULL, `version_id` INT (11) DEFAULT NULL, PRIMARY KEY (`notificationId`));

DROP TABLE IF EXISTS `Timezone`;

CREATE TABLE `Timezone` ( `timeZoneId` VARCHAR(64) NOT NULL, `utcdifference` INT(11) NOT NULL, `gmtLabel` VARCHAR(256) NULL DEFAULT NULL, `timeZoneLabel` VARCHAR(256) NULL DEFAULT NULL, `country` VARCHAR(256) NULL DEFAULT NULL, `cities` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`timeZoneId`));

DROP TABLE IF EXISTS `Language`;

CREATE TABLE `Language` ( `languageId` VARCHAR(64) NOT NULL, `language` VARCHAR(256) NOT NULL, `languageType` VARCHAR(32) NULL DEFAULT NULL, `languageDescription` VARCHAR(256) NULL DEFAULT NULL, `languageIcon` VARCHAR(128) NULL DEFAULT NULL, `alpha2` VARCHAR(2) NULL DEFAULT NULL, `alpha3` VARCHAR(3) NULL DEFAULT NULL, `alpha4` VARCHAR(4) NULL DEFAULT NULL, `alpha4parentid` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`languageId`));

DROP TABLE IF EXISTS `Country`;

CREATE TABLE `Country` ( `countryId` VARCHAR(64) NOT NULL, `countryName` VARCHAR(128) NOT NULL, `countryCode1` VARCHAR(3) NULL DEFAULT NULL, `countryCode2` VARCHAR(3) NULL DEFAULT NULL, `countryFlag` VARCHAR(64) NULL DEFAULT NULL, `capital` VARCHAR(32) NULL DEFAULT NULL, `currencyCode` VARCHAR(3) NULL DEFAULT NULL, `currencyName` VARCHAR(128) NULL DEFAULT NULL, `currencySymbol` VARCHAR(32) NULL DEFAULT NULL, `capitalLatitude` INT(11) NULL DEFAULT NULL, `capitalLongitude` INT(11) NULL DEFAULT NULL, `isoNumeric` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`countryId`));

DROP TABLE IF EXISTS `State`;

CREATE TABLE `State` ( `stateId` VARCHAR(64) NOT NULL, `countryId` VARCHAR(64) NOT NULL, `stateName` VARCHAR(256) NOT NULL, `stateCode` INT(2) NULL DEFAULT NULL, `stateCodeChar2` VARCHAR(32) NOT NULL, `stateCodeChar3` VARCHAR(32) NULL DEFAULT NULL, `stateDescription` VARCHAR(256) NULL DEFAULT NULL, `stateFlag` VARCHAR(128) NULL DEFAULT NULL, `stateCapital` VARCHAR(128) NULL DEFAULT NULL, `stateCapitalLatitude` INT(11) NULL DEFAULT NULL, `stateCapitalLongitude` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`stateId`));

DROP TABLE IF EXISTS `City`;

CREATE TABLE `City` ( `cityId` VARCHAR(64) NOT NULL, `countryId` VARCHAR(64) NOT NULL, `stateId` VARCHAR(64) NOT NULL, `cityName` VARCHAR(256) NOT NULL, `cityCodeChar2` VARCHAR(32) NOT NULL, `cityCode` INT(3) NOT NULL, `cityDescription` VARCHAR(128) NULL DEFAULT NULL, `cityFlag` VARCHAR(128) NULL DEFAULT NULL, `cityLatitude` INT(11) NULL DEFAULT NULL, `cityLongitude` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`cityId`));

DROP TABLE IF EXISTS `AddressType`;

CREATE TABLE `AddressType` ( `addressTypeId` VARCHAR(64) NOT NULL, `addressType` VARCHAR(128) NOT NULL, `addressTypeDesc` VARCHAR(256) NULL DEFAULT NULL, `addressTypeIcon` VARCHAR(128) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`addressTypeId`));

DROP TABLE IF EXISTS `Address`;

CREATE TABLE `Address` ( `addressId` VARCHAR(64) NOT NULL, `addressTypeId` VARCHAR(64) NOT NULL, `addressLabel` VARCHAR(11) NULL DEFAULT NULL, `address1` VARCHAR(256) NULL DEFAULT NULL, `address2` VARCHAR(256) NULL DEFAULT NULL, `address3` VARCHAR(256) NULL DEFAULT NULL, `countryId` VARCHAR(64) NOT NULL, `stateId` VARCHAR(64) NOT NULL, `cityId` VARCHAR(64) NOT NULL, `zipcode` VARCHAR(6) NOT NULL, `latitude` VARCHAR(64) NULL DEFAULT NULL, `longitude` VARCHAR(64) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`addressId`));

DROP TABLE IF EXISTS `ContactType`;

CREATE TABLE `ContactType` ( `contactTypeId` VARCHAR(64) NOT NULL, `contactType` VARCHAR(128) NOT NULL, `contactTypeDesc` VARCHAR(256) NULL DEFAULT NULL, `contactTypeIcon` VARCHAR(128) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`contactTypeId`));

DROP TABLE IF EXISTS `Gender`;

CREATE TABLE `Gender` ( `genderId` VARCHAR(64) NOT NULL, `gender` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`genderId`));

DROP TABLE IF EXISTS `Title`;

CREATE TABLE `Title` ( `titleId` VARCHAR(64) NOT NULL, `titles` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`titleId`));

DROP TABLE IF EXISTS `CoreContacts`;

CREATE TABLE `CoreContacts` ( `contactId` VARCHAR(64) NOT NULL, `titleId` VARCHAR(64) NOT NULL, `firstName` VARCHAR(256) NOT NULL, `middleName` VARCHAR(256) NULL DEFAULT NULL, `lastName` VARCHAR(256) NOT NULL, `nativeLanguageCode` VARCHAR(64) NULL DEFAULT NULL, `nativeTitle` VARCHAR(128) NULL DEFAULT NULL, `nativeFirstName` VARCHAR(256) NULL DEFAULT NULL, `nativeMiddleName` VARCHAR(256) NULL DEFAULT NULL, `nativeLastName` VARCHAR(256) NULL DEFAULT NULL, `genderId` VARCHAR(64) NOT NULL, `dateofbirth` TIMESTAMP NULL DEFAULT NULL, `age` INT(11) NULL DEFAULT NULL, `approximateDOB` TIMESTAMP NULL DEFAULT NULL, `emailId` VARCHAR(256) NOT NULL, `phoneNumber` VARCHAR(20) NOT NULL, `timeZoneId` VARCHAR(64) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`contactId`));

DROP TABLE IF EXISTS `AddressMap`;

CREATE TABLE `AddressMap` ( `addMapId` INT(11) NOT NULL AUTO_INCREMENT, `contactId` VARCHAR(64) NOT NULL, `addressId` VARCHAR(64) NOT NULL, PRIMARY KEY (`addMapId`));

DROP TABLE IF EXISTS `EmailCategory`;

CREATE TABLE `EmailCategory` ( `emailCatId` VARCHAR(256) NOT NULL, `emailCatName` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`emailCatId`));

DROP TABLE IF EXISTS `PhoneCategory`;

CREATE TABLE `PhoneCategory` ( `phoneCatId` VARCHAR(256) NOT NULL, `phoneCatName` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`phoneCatId`));

DROP TABLE IF EXISTS `SocialCategory`;

CREATE TABLE `SocialCategory` ( `socialCatId` VARCHAR(256) NOT NULL, `socialCatName` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`socialCatId`));

DROP TABLE IF EXISTS `EmailCommunication`;

CREATE TABLE `EmailCommunication` ( `emailCommId` VARCHAR(256) NOT NULL, `commType` VARCHAR(256) NOT NULL, `email` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`emailCommId`));

DROP TABLE IF EXISTS `PhoneCommunication`;

CREATE TABLE `PhoneCommunication` ( `phoneId` VARCHAR(256) NOT NULL, `commType` VARCHAR(256) NOT NULL, `phoneNo` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`phoneId`));

DROP TABLE IF EXISTS `SocialCommunication`;

CREATE TABLE `SocialCommunication` ( `socialId` VARCHAR(256) NOT NULL, `commType` VARCHAR(256) NOT NULL, `social` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`socialId`));

DROP TABLE IF EXISTS `EmailContactMap`;

CREATE TABLE `EmailContactMap` ( `emailcontactmapPkey` INT(11) NOT NULL AUTO_INCREMENT, `contactId` VARCHAR(64) NOT NULL, `emailCommId` VARCHAR(256) NOT NULL, PRIMARY KEY (`emailcontactmapPkey`));

DROP TABLE IF EXISTS `PhoneContactMap`;

CREATE TABLE `PhoneContactMap` ( `phonecontactmapPkey` INT(11) NOT NULL AUTO_INCREMENT, `contactId` VARCHAR(64) NOT NULL, `phoneId` VARCHAR(256) NOT NULL, PRIMARY KEY (`phonecontactmapPkey`));

DROP TABLE IF EXISTS `SocialContactMap`;

CREATE TABLE `SocialContactMap` ( `socialcontactmapPkey` INT(11) NOT NULL AUTO_INCREMENT, `contactId` VARCHAR(64) NOT NULL, `socialId` VARCHAR(256) NOT NULL, PRIMARY KEY (`socialcontactmapPkey`));

DROP TABLE IF EXISTS `PasswordAlgo`;

CREATE TABLE `PasswordAlgo` ( `algoId` VARCHAR(64) NOT NULL, `algorithm` INT(11) NOT NULL, `algoName` VARCHAR(256) NOT NULL, `algoDescription` VARCHAR(256) NULL DEFAULT NULL, `algoIcon` VARCHAR(64) NULL DEFAULT NULL, `algoHelp` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`algoId`));

DROP TABLE IF EXISTS `PasswordPolicy`;

CREATE TABLE `PasswordPolicy` ( `policyId` VARCHAR(64) NOT NULL, `policyName` VARCHAR(256) NOT NULL, `policyDescription` VARCHAR(512) NULL DEFAULT NULL, `maxPwdLength` INT(11) NULL DEFAULT NULL, `minPwdLength` INT(11) NOT NULL, `minCapitalLetters` INT(11) NULL DEFAULT NULL, `minSmallLetters` INT(11) NULL DEFAULT NULL, `minNumericValues` INT(11) NULL DEFAULT NULL, `minSpecialLetters` INT(11) NULL DEFAULT NULL, `allowedSpecialLetters` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`policyId`));

DROP TABLE IF EXISTS `Question`;

CREATE TABLE `Question` ( `questionId` VARCHAR(64) NOT NULL, `levelid` INT(11) NOT NULL, `question` VARCHAR(256) NOT NULL, `questionDetails` TEXT NULL DEFAULT NULL, `questionIcon` VARCHAR(64) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`questionId`));

DROP TABLE IF EXISTS `UserAccessLevel`;

CREATE TABLE `UserAccessLevel` ( `userAccessLevelId` VARCHAR(64) NOT NULL, `userAccessLevel` INT(11) NOT NULL, `levelName` VARCHAR(256) NOT NULL, `levelDescription` VARCHAR(256) NOT NULL, `levelHelp` VARCHAR(2048) NULL DEFAULT NULL, `levelIcon` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userAccessLevelId`), UNIQUE KEY (`userAccessLevel`));

DROP TABLE IF EXISTS `UserAccessDomain`;

CREATE TABLE `UserAccessDomain` ( `userAccessDomainId` VARCHAR(64) NOT NULL, `userAccessDomain` INT(11) NOT NULL, `domainName` VARCHAR(256) NOT NULL, `domainDescription` VARCHAR(256) NOT NULL, `domainHelp` VARCHAR(2048) NULL DEFAULT NULL, `domainIcon` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userAccessDomainId`), UNIQUE KEY (`userAccessDomain`));

DROP TABLE IF EXISTS `UserDetail`;

CREATE TABLE `UserDetail` ( `userId` VARCHAR(64) NOT NULL, `userAccessCode` INT(11) NULL DEFAULT NULL, `userAccessLevelId` VARCHAR(64) NULL DEFAULT NULL, `userAccessDomainId` VARCHAR(64) NULL DEFAULT NULL, `multiFactorAuthEnabled` INT(1) NULL DEFAULT NULL, `genTempOneTimePassword` INT(1) NULL DEFAULT NULL, `allowMultipleLogin` INT(1) NULL DEFAULT NULL, `isLocked` INT(1) NULL DEFAULT NULL, `isDeleted` INT(1) NULL DEFAULT NULL, `changePasswordNextLogin` INT(1) NULL DEFAULT NULL, `passwordExpiryDate` TIMESTAMP NULL DEFAULT NULL, `passwordAlgo` VARCHAR(64) NULL DEFAULT '1', `lastPasswordChangeDate` TIMESTAMP NULL DEFAULT NULL, `sessionTimeout` INT(11) NULL DEFAULT '1800', `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userId`));

DROP TABLE IF EXISTS `Login`;

CREATE TABLE `Login` ( `loginPk` VARCHAR(64) NOT NULL, `loginId` VARCHAR(200) NOT NULL, `serverAuthImage` VARCHAR(64) NULL DEFAULT NULL, `serverAuthText` VARCHAR(32) NULL DEFAULT NULL, `userId` VARCHAR(64) NOT NULL, `contactId` VARCHAR(64) NOT NULL, `failedLoginAttempts` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`loginPk`));

DROP TABLE IF EXISTS `LoginSession`;

CREATE TABLE `LoginSession` ( `AppSessionId` VARCHAR(256) NOT NULL, `userId` VARCHAR(64) NOT NULL, `AppServerSessionId` VARCHAR(256) NOT NULL, `loginTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `lastAccessTime` TIMESTAMP NULL DEFAULT NULL, `logoutTime` TIMESTAMP NULL, `clientIPAddress` VARCHAR(128) NOT NULL, `clientIPAddressInt` BIGINT NULL DEFAULT NULL, `clientNetworkAddress` INT(11) NULL DEFAULT NULL, `clientBrowser` VARCHAR(256) NOT NULL, `sessionData` TEXT NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`AppSessionId`));

DROP TABLE IF EXISTS `PassRecovery`;

CREATE TABLE `PassRecovery` ( `passRecoveryId` VARCHAR(64) NOT NULL, `userId` VARCHAR(64) NOT NULL, `questionId` VARCHAR(64) NOT NULL, `answer` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`passRecoveryId`));

DROP TABLE IF EXISTS `UserData`;

CREATE TABLE `UserData` ( `userDataId` VARCHAR(64) NOT NULL, `userId` VARCHAR(64) NOT NULL, `password` VARCHAR(512) NOT NULL, `oneTimePassword` VARCHAR(32) NULL DEFAULT NULL, `oneTimePasswordExpiry` INT(11) NULL DEFAULT NULL, `oneTimePasswordGenDate` TIMESTAMP NULL DEFAULT NULL, `last5Passwords` VARCHAR(500) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userDataId`));

DROP TABLE IF EXISTS `SessionData`;

CREATE TABLE `SessionData` ( `dataId` VARCHAR(256) NOT NULL, `customerId` VARCHAR(64) NULL DEFAULT NULL, `userId` VARCHAR(64) NOT NULL, `sessionKey` VARCHAR(64) NOT NULL, `dataType` INT(10) NOT NULL, `numberValue` INT(10) NULL DEFAULT NULL, `dateTimeValue` TIMESTAMP NULL DEFAULT NULL, `stringValue` VARCHAR(2000) NULL DEFAULT NULL, `booleanValue` TINYINT(1) NULL DEFAULT NULL, `jsonValue` TEXT NULL DEFAULT NULL, `appSessionId` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`dataId`));

DROP TABLE IF EXISTS `Roles`;

CREATE TABLE `Roles` ( `roleId` VARCHAR(64) NOT NULL, `RoleName` VARCHAR(256) NOT NULL, `RoleDescription` VARCHAR(256) NOT NULL, `RoleIcon` VARCHAR(64) NULL DEFAULT NULL, `roleHelp` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`roleId`));

DROP TABLE IF EXISTS `AppMenus`;

CREATE TABLE `AppMenus` ( `menuId` VARCHAR(64) NOT NULL, `menuTreeId` VARCHAR(256) NOT NULL, `menuIcon` VARCHAR(256) NULL DEFAULT NULL, `menuAction` VARCHAR(256) NULL DEFAULT NULL, `menuCommands` VARCHAR(64) NULL DEFAULT NULL, `menuDisplay` TINYINT(1) NOT NULL, `menuHead` TINYINT(1) NOT NULL, `menuLabel` VARCHAR(256) NOT NULL, `uiType` VARCHAR(3) NULL DEFAULT NULL, `RefObjectId` VARCHAR(256) NULL DEFAULT NULL, `menuAccessRights` INT(11) NOT NULL, `appType` INT(1) NULL DEFAULT NULL, `appId` VARCHAR(256) NULL DEFAULT NULL, `autoSave` TINYINT(1) NOT NULL, `startDate` TIMESTAMP NULL DEFAULT NULL, `expiryDate` TIMESTAMP NULL DEFAULT NULL, `goLiveDate` TIMESTAMP NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`menuId`));

DROP TABLE IF EXISTS `RoleMenuBridge`;

CREATE TABLE `RoleMenuBridge` ( `roleMenuMapId` VARCHAR(64) NOT NULL, `roleId` VARCHAR(64) NOT NULL, `menuId` VARCHAR(64) NOT NULL, `isRead` TINYINT(1) NOT NULL, `isWrite` TINYINT(1) NOT NULL, `isExecute` TINYINT(1) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`roleMenuMapId`));

DROP TABLE IF EXISTS `UserRoleBridge`;

CREATE TABLE `UserRoleBridge` ( `roleUserMapId` VARCHAR(64) NOT NULL, `roleId` VARCHAR(64) NOT NULL, `userId` VARCHAR(64) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`roleUserMapId`));

DROP TABLE IF EXISTS `JwtConfig`;

CREATE TABLE `JwtConfig` ( `configId` VARCHAR(256) NOT NULL, `jwtAlgorithm` VARCHAR(256) NOT NULL, `expiration` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `tokenKey` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`configId`));

DROP TABLE IF EXISTS `PublicApi`;

CREATE TABLE `PublicApi` ( `apiId` VARCHAR(256) NOT NULL, `apiData` VARCHAR(256) NOT NULL, `schedulerDetails` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`apiId`));

ALTER TABLE `State` ADD CONSTRAINT FOREIGN KEY (`countryId`) REFERENCES `Country`(`countryId`);

ALTER TABLE `City` ADD CONSTRAINT FOREIGN KEY (`stateId`) REFERENCES `State`(`stateId`);

ALTER TABLE `City` ADD CONSTRAINT FOREIGN KEY (`countryId`) REFERENCES `Country`(`countryId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`stateId`) REFERENCES `State`(`stateId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`countryId`) REFERENCES `Country`(`countryId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`cityId`) REFERENCES `City`(`cityId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`addressTypeId`) REFERENCES `AddressType`(`addressTypeId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`timeZoneId`) REFERENCES `Timezone`(`timeZoneId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`nativeLanguageCode`) REFERENCES `Language`(`languageId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`titleId`) REFERENCES `Title`(`titleId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`genderId`) REFERENCES `Gender`(`genderId`);

ALTER TABLE `AddressMap` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `AddressMap` ADD CONSTRAINT FOREIGN KEY (`addressId`) REFERENCES `Address`(`addressId`);

ALTER TABLE `EmailCommunication` ADD CONSTRAINT FOREIGN KEY (`commType`) REFERENCES `EmailCategory`(`emailCatId`);

ALTER TABLE `PhoneCommunication` ADD CONSTRAINT FOREIGN KEY (`commType`) REFERENCES `PhoneCategory`(`phoneCatId`);

ALTER TABLE `SocialCommunication` ADD CONSTRAINT FOREIGN KEY (`commType`) REFERENCES `SocialCategory`(`socialCatId`);

ALTER TABLE `EmailContactMap` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `EmailContactMap` ADD CONSTRAINT FOREIGN KEY (`emailCommId`) REFERENCES `EmailCommunication`(`emailCommId`);

ALTER TABLE `PhoneContactMap` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `PhoneContactMap` ADD CONSTRAINT FOREIGN KEY (`phoneId`) REFERENCES `PhoneCommunication`(`phoneId`);

ALTER TABLE `SocialContactMap` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `SocialContactMap` ADD CONSTRAINT FOREIGN KEY (`socialId`) REFERENCES `SocialCommunication`(`socialId`);

ALTER TABLE `UserDetail` ADD CONSTRAINT FOREIGN KEY (`userAccessLevelId`) REFERENCES `UserAccessLevel`(`userAccessLevelId`);

ALTER TABLE `UserDetail` ADD CONSTRAINT FOREIGN KEY (`userAccessDomainId`) REFERENCES `UserAccessDomain`(`userAccessDomainId`);

ALTER TABLE `Login` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `Login` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `LoginSession` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `PassRecovery` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `PassRecovery` ADD CONSTRAINT FOREIGN KEY (`questionId`) REFERENCES `Question`(`questionId`);

ALTER TABLE `UserData` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `RoleMenuBridge` ADD CONSTRAINT FOREIGN KEY (`menuId`) REFERENCES `AppMenus`(`menuId`);

ALTER TABLE `RoleMenuBridge` ADD CONSTRAINT FOREIGN KEY (`roleId`) REFERENCES `Roles`(`roleId`);

ALTER TABLE `UserRoleBridge` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `UserRoleBridge` ADD CONSTRAINT FOREIGN KEY (`roleId`) REFERENCES `Roles`(`roleId`);

