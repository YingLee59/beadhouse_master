create table employee
(
  id        varchar(12) not null
    primary key,
  name      varchar(20) not null,
  sex       char(2)     not null,
  birth     date        not null,
  idCard    varchar(20) not null,
  telephone char(11)    not null,
  address   varchar(50) not null,
  dep       varchar(10) not null
  comment '部门',
  job       varchar(10) not null
  comment '职位',
  hiredate  date        not null
  comment '入职时间',
  email     varchar(20) not null,
  salary    double      null
);

