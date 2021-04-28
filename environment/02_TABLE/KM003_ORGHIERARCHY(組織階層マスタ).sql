--‹¤’Êƒ}ƒXƒ^(k) - ‘gDŠK‘wƒ}ƒXƒ^(km003_orghierarchy)
create table km003_orghierarchy(
     organizationQcd varchar(10) not null,
     start_yearmonth varchar(6) not null,
     upperorganizationQcd varchar(10) not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km003_orghierarchy_pk primary key (organizationQcd, start_yearmonth)
);

comment on table km003_orghierarchy is '‘gDŠK‘wƒ}ƒXƒ^';
comment on column km003_orghierarchy.organizationQcd is '‘gDcd';
comment on column km003_orghierarchy.start_yearmonth is 'ŠJn”NŒ';
comment on column km003_orghierarchy.upperorganizationQcd is 'ãˆÊ‘gDcd';
comment on column km003_orghierarchy.insert_user is 'ì¬Ò';
comment on column km003_orghierarchy.insert_date is 'ì¬“ú';
comment on column km003_orghierarchy.update_user is 'XVÒ';
comment on column km003_orghierarchy.update_date is 'XV“ú';




alter table km003_orghierarchy add constraint km003_orghierarchy_fk1
      foreign key (organizationQcd)
      references km002_organization (organizationQcd);
alter table km003_orghierarchy add constraint km003_orghierarchy_fk2
      foreign key (upperorganizationQcd)
      references km002_organization (organizationQcd);
