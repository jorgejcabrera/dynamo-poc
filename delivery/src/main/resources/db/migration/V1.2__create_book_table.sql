CREATE TABLE `book`(
    `title`        varchar(255) not null,
    `category`     varchar(255) not null,
    `created_date` datetime     not null,
    `price`        double       null,
    `rating`       int          null,
    primary key (`title`, `category`)
)ENGINE=INNODB;

create index book_created_date_idx
    on book (created_date);

create index book_price_idx
    on book (price);