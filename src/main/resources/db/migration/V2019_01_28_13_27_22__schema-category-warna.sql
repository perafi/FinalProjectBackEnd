create table warna_category
(
    id varchar(64) not null primary key,
    warna varchar(150) not null,
    kode varchar(64) not null,
    description text not null
) engine = InnoDB;

