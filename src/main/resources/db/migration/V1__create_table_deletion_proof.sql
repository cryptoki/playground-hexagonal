create table deletion_proof (
  id           varchar2(64),
  reference_id varchar2(256) not null,
  message      varchar2(256) not null,
  status       varchar2(256) not null,
  constraint pk_deletion_proof primary key (id)
)
