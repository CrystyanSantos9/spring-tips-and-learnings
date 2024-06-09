 create table link (
    id number(19,0) generated as identity,
     categoria varchar2(255 char) not null,
     link varchar2(255 char) not null,
     titulo varchar2(255 char) not null,
     primary key (id)
 );
