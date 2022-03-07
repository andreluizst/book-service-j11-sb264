create table book (
id int auto_increment primary key,
author longtext,
launch_date datetime(6) not null,
price decimal(65,2) not null,
title longtext
) engine=InnoDB charset=latin1;
