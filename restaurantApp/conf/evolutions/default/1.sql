# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categorie (
  catid                     integer auto_increment not null,
  categoryName              varchar(255),
  constraint pk_categorie primary key (catid))
;

create table commande (
  cid                       integer auto_increment not null,
  etat_eid                  integer,
  commentaires              varchar(255),
  constraint pk_commande primary key (cid))
;

create table demande (
  did                       integer auto_increment not null,
  ETAT_ID                   integer,
  commentaires              varchar(255),
  date                      varchar(255),
  LOCAL_ID                  integer,
  constraint pk_demande primary key (did))
;

create table etat (
  eid                       integer auto_increment not null,
  name                      varchar(255),
  constraint pk_etat primary key (eid))
;

create table fonction (
  fid                       integer auto_increment not null,
  activity                  varchar(255),
  constraint uq_fonction_activity unique (activity),
  constraint pk_fonction primary key (fid))
;

create table local (
  lid                       integer auto_increment not null,
  localName                 varchar(255),
  address                   varchar(255),
  codePostal                varchar(255),
  type                      varchar(255),
  constraint uq_local_codePostal unique (codePostal),
  constraint uq_local_type unique (type),
  constraint pk_local primary key (lid))
;

create table plat (
  plid                      integer auto_increment not null,
  Plat                      varchar(255),
  constraint pk_plat primary key (plid))
;

create table plat_produit (
  plpid                     integer auto_increment not null,
  produit_pid               integer,
  plat_plid                 integer,
  quantite                  integer,
  unite                     varchar(255),
  constraint pk_plat_produit primary key (plpid))
;

create table produit (
  pid                       integer auto_increment not null,
  productName               varchar(255),
  accessibleFournisseur     tinyint(1) default 0,
  unite                     varchar(255),
  categorie_catid           integer,
  constraint pk_produit primary key (pid))
;

create table produit_demande (
  pdid                      integer auto_increment not null,
  produit_pid               integer,
  demande_did               integer,
  quantite                  integer,
  constraint pk_produit_demande primary key (pdid))
;

create table stock_fournisseur (
  sfid                      integer auto_increment not null,
  produit_pid               integer,
  quantite                  integer,
  lid_lid                   integer,
  commentaires              varchar(255),
  prixUnite                 integer,
  constraint pk_stock_fournisseur primary key (sfid))
;

create table stock_resto (
  srid                      integer auto_increment not null,
  quantite                  integer,
  stockMax                  integer,
  stockMin                  integer,
  stockAlerte               integer,
  local_lid                 integer,
  produit_pid               integer,
  constraint pk_stock_resto primary key (srid))
;

create table utilisateur (
  uid                       integer auto_increment not null,
  lastName                  varchar(255),
  firstName                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  pseudo                    varchar(255),
  fonction_fid              integer,
  local_lid                 integer,
  constraint uq_utilisateur_email unique (email),
  constraint uq_utilisateur_password unique (password),
  constraint uq_utilisateur_pseudo unique (pseudo),
  constraint pk_utilisateur primary key (uid))
;


create table commande_demande (
  commande_cid                   integer not null,
  demande_did                    integer not null,
  constraint pk_commande_demande primary key (commande_cid, demande_did))
;

create table LOCAL_COMMANDE (
  local_lid                      integer not null,
  commande_cid                   integer not null,
  constraint pk_LOCAL_COMMANDE primary key (local_lid, commande_cid))
;
alter table commande add constraint fk_commande_etat_1 foreign key (etat_eid) references etat (eid) on delete restrict on update restrict;
create index ix_commande_etat_1 on commande (etat_eid);
alter table demande add constraint fk_demande_etat_2 foreign key (ETAT_ID) references etat (eid) on delete restrict on update restrict;
create index ix_demande_etat_2 on demande (ETAT_ID);
alter table demande add constraint fk_demande_local_3 foreign key (LOCAL_ID) references local (lid) on delete restrict on update restrict;
create index ix_demande_local_3 on demande (LOCAL_ID);
alter table plat_produit add constraint fk_plat_produit_produit_4 foreign key (produit_pid) references produit (pid) on delete restrict on update restrict;
create index ix_plat_produit_produit_4 on plat_produit (produit_pid);
alter table plat_produit add constraint fk_plat_produit_plat_5 foreign key (plat_plid) references plat (plid) on delete restrict on update restrict;
create index ix_plat_produit_plat_5 on plat_produit (plat_plid);
alter table produit add constraint fk_produit_categorie_6 foreign key (categorie_catid) references categorie (catid) on delete restrict on update restrict;
create index ix_produit_categorie_6 on produit (categorie_catid);
alter table produit_demande add constraint fk_produit_demande_produit_7 foreign key (produit_pid) references produit (pid) on delete restrict on update restrict;
create index ix_produit_demande_produit_7 on produit_demande (produit_pid);
alter table produit_demande add constraint fk_produit_demande_demande_8 foreign key (demande_did) references demande (did) on delete restrict on update restrict;
create index ix_produit_demande_demande_8 on produit_demande (demande_did);
alter table stock_fournisseur add constraint fk_stock_fournisseur_produit_9 foreign key (produit_pid) references produit (pid) on delete restrict on update restrict;
create index ix_stock_fournisseur_produit_9 on stock_fournisseur (produit_pid);
alter table stock_fournisseur add constraint fk_stock_fournisseur_lid_10 foreign key (lid_lid) references local (lid) on delete restrict on update restrict;
create index ix_stock_fournisseur_lid_10 on stock_fournisseur (lid_lid);
alter table stock_resto add constraint fk_stock_resto_local_11 foreign key (local_lid) references local (lid) on delete restrict on update restrict;
create index ix_stock_resto_local_11 on stock_resto (local_lid);
alter table stock_resto add constraint fk_stock_resto_produit_12 foreign key (produit_pid) references produit (pid) on delete restrict on update restrict;
create index ix_stock_resto_produit_12 on stock_resto (produit_pid);
alter table utilisateur add constraint fk_utilisateur_fonction_13 foreign key (fonction_fid) references fonction (fid) on delete restrict on update restrict;
create index ix_utilisateur_fonction_13 on utilisateur (fonction_fid);
alter table utilisateur add constraint fk_utilisateur_local_14 foreign key (local_lid) references local (lid) on delete restrict on update restrict;
create index ix_utilisateur_local_14 on utilisateur (local_lid);



alter table commande_demande add constraint fk_commande_demande_commande_01 foreign key (commande_cid) references commande (cid) on delete restrict on update restrict;

alter table commande_demande add constraint fk_commande_demande_demande_02 foreign key (demande_did) references demande (did) on delete restrict on update restrict;

alter table LOCAL_COMMANDE add constraint fk_LOCAL_COMMANDE_local_01 foreign key (local_lid) references local (lid) on delete restrict on update restrict;

alter table LOCAL_COMMANDE add constraint fk_LOCAL_COMMANDE_commande_02 foreign key (commande_cid) references commande (cid) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table categorie;

drop table commande;

drop table commande_demande;

drop table demande;

drop table etat;

drop table fonction;

drop table local;

drop table LOCAL_COMMANDE;

drop table plat;

drop table plat_produit;

drop table produit;

drop table produit_demande;

drop table stock_fournisseur;

drop table stock_resto;

drop table utilisateur;

SET FOREIGN_KEY_CHECKS=1;

