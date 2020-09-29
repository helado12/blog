drop table if exists t_blog;
create table t_blog (
    id bigint(20) not null auto_increment,
    title varchar(255)  default null,
    content text default null,
    first_picture varchar(255) default null,
    flag varchar(255) default null,
    views int(11) default null,
    appreciation int(11) default 0,
    shareStatement int(11) default 0,
    commentabled int(11) default 0,
    published int(11) default 0,
    recommend int(11) default 0,
    create_time datetime default null,
    update_time datetime default null,
    user_id bigint(20) default null,
    type_id bigint(20) default null,
    tag_ids varchar(100) default null,
    primary key (id)
)engine = INNODB default charset = utf8;

drop table if exists t_blog_tags;
create table t_blog_tags(
    id int(11) not null auto_increment,
    tag_id bigint(20) default null,
    blog_id varchar(255) default null,
    primary key (id)
)engine = INNODB charset = utf8;

drop table if exists t_comment;
create table t_comment(
    id bigint(20) not null auto_increment,
    nickname varchar(255) default null,
    email varchar(255) default null,
    content varchar(255) default null,
    avatar varchar(255) default null,
    create_time datetime default null,
    blog_id bigint(20) default null,
    parent_comment_id bigint(20) default null,
    primary key (id)
)engine = INNODB charset = utf8;

drop table if exists t_type;
create table t_type(
    id bigint(20) not null auto_increment,
    name varchar(255) default null,
    primary key (id)
)engine = INNODB charset = utf8;

drop table if exists t_user;
create table t_user(
    id bigint(20) not null auto_increment,
    nickname varchar(255) default null,
    password varchar(255) default null,
    email varchar(255) default null,
    avatar varchar(255) default null,
    type int(10) default null,
    create_time datetime default null,
    update_time datetime default null,
    primary key (id)
)engine = INNODB charset = utf8;