create table employee
(
  id        int auto_increment
    primary key,
  name      varchar(20) not null,
  sex       char(2)     not null,
  birth     date        not null,
  idCard    char(18)    not null,
  telephone char(11)    not null,
  address   varchar(50) null,
  dep       int(3)      not null
  comment '部门',
  job       varchar(10) not null
  comment '职位',
  hiredate  date        not null
  comment '入职时间',
  email     varchar(20) not null,
  constraint depId
  foreign key (dep) references department (depId)
    on update cascade
    on delete cascade
);

create table department
(
  depId   int auto_increment
    primary key,
  depName varchar(20) not null
);

