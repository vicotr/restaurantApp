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
  LOCAL_ID                  integer,
  ETAT_ID                   integer,
  commentaires              varchar(255),
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

create table produit (
  pid                       integer auto_increment not null,
  productName               varchar(255),
  accessibleFournisseur     tinyint(1) default 0,
  unite                     varchar(255),
  categorie_catid           integer,
  constraint pk_produit primary key (pid))
;

create table stock_fournisseur (
  sfid                      integer auto_increment not null,
  quantite                  integer,
  lid_lid                   integer,
  commentaires              varchar(255),
  prixUnite                 integer,
  constraint pk_stock_fournisseur primary key (sfid))
;

create table stock_resto (
  srid                      integer auto_increment not null,
  produit_pid               integer,
  quantite                  integer,
  stockMax                  integer,
  stockMin                  integer,
  stockAlerte               integer,
  local_lid                 integer,
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


create table COMMANDE_DEMANDE (
  commande_cid                   integer not null,
  demande_did                    integer not null,
  constraint pk_COMMANDE_DEMANDE primary key (commande_cid, demande_did))
;

create table LOCAL_COMMANDE (
  local_lid                      integer not null,
  commande_cid                   integer not null,
  constraint pk_LOCAL_COMMANDE primary key (local_lid, commande_cid))
;

create table PLAT_PRODUIT (
  plat_plid                      integer not null,
  produit_pid                    integer not null,
  constraint pk_PLAT_PRODUIT primary key (plat_plid, produit_pid))
;
alter table commande add constraint fk_commande_etat_1 foreign key (etat_eid) references etat (eid) on delete restrict on update restrict;
create index ix_commande_etat_1 on commande (etat_eid);
alter table demande add constraint fk_demande_lid_2 foreign key (LOCAL_ID) references local (lid) on delete restrict on update restrict;
create index ix_demande_lid_2 on demande (LOCAL_ID);
alter table demande add constraint fk_demande_eid_3 foreign key (ETAT_ID) references etat (eid) on delete restrict on update restrict;
create index ix_demande_eid_3 on demande (ETAT_ID);
alter table produit add constraint fk_produit_categorie_4 foreign key (categorie_catid) references categorie (catid) on delete restrict on update restrict;
create index ix_produit_categorie_4 on produit (categorie_catid);
alter table stock_fournisseur add constraint fk_stock_fournisseur_lid_5 foreign key (lid_lid) references local (lid) on delete restrict on update restrict;
create index ix_stock_fournisseur_lid_5 on stock_fournisseur (lid_lid);
alter table stock_resto add constraint fk_stock_resto_produit_6 foreign key (produit_pid) references produit (pid) on delete restrict on update restrict;
create index ix_stock_resto_produit_6 on stock_resto (produit_pid);
alter table stock_resto add constraint fk_stock_resto_local_7 foreign key (local_lid) references local (lid) on delete restrict on update restrict;
create index ix_stock_resto_local_7 on stock_resto (local_lid);
alter table utilisateur add constraint fk_utilisateur_fonction_8 foreign key (fonction_fid) references fonction (fid) on delete restrict on update restrict;
create index ix_utilisateur_fonction_8 on utilisateur (fonction_fid);
alter table utilisateur add constraint fk_utilisateur_local_9 foreign key (local_lid) references local (lid) on delete restrict on update restrict;
create index ix_utilisateur_local_9 on utilisateur (local_lid);



alter table COMMANDE_DEMANDE add constraint fk_COMMANDE_DEMANDE_commande_01 foreign key (commande_cid) references commande (cid) on delete restrict on update restrict;

alter table COMMANDE_DEMANDE add constraint fk_COMMANDE_DEMANDE_demande_02 foreign key (demande_did) references demande (did) on delete restrict on update restrict;

alter table LOCAL_COMMANDE add constraint fk_LOCAL_COMMANDE_local_01 foreign key (local_lid) references local (lid) on delete restrict on update restrict;

alter table LOCAL_COMMANDE add constraint fk_LOCAL_COMMANDE_commande_02 foreign key (commande_cid) references commande (cid) on delete restrict on update restrict;

alter table PLAT_PRODUIT add constraint fk_PLAT_PRODUIT_plat_01 foreign key (plat_plid) references plat (plid) on delete restrict on update restrict;

alter table PLAT_PRODUIT add constraint fk_PLAT_PRODUIT_produit_02 foreign key (produit_pid) references produit (pid) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table categorie;

drop table commande;

drop table COMMANDE_DEMANDE;

drop table demande;

drop table etat;

drop table fonction;

drop table local;

drop table LOCAL_COMMANDE;

drop table plat;

drop table PLAT_PRODUIT;

drop table produit;

drop table stock_fournisseur;

drop table stock_resto;

drop table utilisateur;

SET FOREIGN_KEY_CHECKS=1;

