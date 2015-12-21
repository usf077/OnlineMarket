/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 7.3                                */
/* Date de création :  19/12/2015 11:38:06                      */
/*==============================================================*/

drop index IF EXISTS ANNONCE_PK;
drop index IF EXISTS  ASSOCIATION_8_FK;
drop table IF EXISTS Annonce;

drop index IF EXISTS pannier_produit_FK2;

drop index IF EXISTS pannier_produit_FK;

drop index IF EXISTS pannier_produit_PK;

drop table IF EXISTS pannier_produit;

drop index IF EXISTS CATEGORIE_PK;

drop table IF EXISTS Categorie;

drop index IF EXISTS ASSOCIATION_3_FK;

drop index IF EXISTS COMMANDE_PK;

drop table IF EXISTS Commande;

drop index IF EXISTS ASSOCIATION_6_FK;

drop index IF EXISTS COMPTE_PK;

drop table IF EXISTS Compte;

drop index IF EXISTS ASSOCIATION_4_FK;

drop index IF EXISTS PANNIER_PK;

drop table IF EXISTS Pannier;

drop index IF EXISTS ASSOCIATION_9_FK;

drop index IF EXISTS ASSOCIATION_7_FK;

drop index IF EXISTS PRODUIT_PK;

drop table IF EXISTS Produit;

drop index IF EXISTS USER_PK;

drop table  IF EXISTS "users";


/*==============================================================*/
/* Table : Annonce                                              */
/*==============================================================*/
create table Annonce (
id_annonce           SERIAL                not null,
id_user              INT4                 not null,
nom_a                VARCHAR(254)         null,
description_a        VARCHAR(254)         null,
date_creation        DATE                 null,
date_validation      DATE                 null,
nbr_prod             INT4                 null,
date_mise_vente      DATE                 null,
Attribut_8           INT4                 null,
constraint PK_ANNONCE primary key (id_annonce)
);

/*==============================================================*/
/* Index : ANNONCE_PK                                           */
/*==============================================================*/
create unique index ANNONCE_PK on Annonce (
id_annonce
);

/*==============================================================*/
/* Index : ASSOCIATION_8_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_8_FK on Annonce (
id_user
);

/*==============================================================*/
/* Table : pannier_produit                                       */
/*==============================================================*/
create table pannier_produit (
id_pannier           INT4                 not null,
id_produit           INT4                 not null,
constraint PK_pannier_produit primary key (id_pannier, id_produit)
);

/*==============================================================*/
/* Index : pannier_produit_PK                                    */
/*==============================================================*/
create unique index pannier_produit_PK on pannier_produit (
id_pannier,
id_produit
);

/*==============================================================*/
/* Index : pannier_produit_FK                                    */
/*==============================================================*/
create  index pannier_produit_FK on pannier_produit (
id_pannier
);

/*==============================================================*/
/* Index : pannier_produit_FK2                                   */
/*==============================================================*/
create  index pannier_produit_FK2 on pannier_produit (
id_produit
);

/*==============================================================*/
/* Table : Categorie                                            */
/*==============================================================*/
create table Categorie (
id_cat               INT4                 not null,
nom_c                VARCHAR(254)         null,
constraint PK_CATEGORIE primary key (id_cat)
);

/*==============================================================*/
/* Index : CATEGORIE_PK                                         */
/*==============================================================*/
create unique index CATEGORIE_PK on Categorie (
id_cat
);

/*==============================================================*/
/* Table : Commande                                             */
/*==============================================================*/
create table Commande (
id_cmd               SERIAL                 not null,
id_user              INT4                 not null,
date_cmd             DATE                 null,
qte_cmd              INT4                 null,
etat_cmd             VARCHAR(254)         null,
constraint PK_COMMANDE primary key (id_cmd)
);

/*==============================================================*/
/* Index : COMMANDE_PK                                          */
/*==============================================================*/
create unique index COMMANDE_PK on Commande (
id_cmd
);

/*==============================================================*/
/* Index : ASSOCIATION_3_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_3_FK on Commande (
id_user
);

/*==============================================================*/
/* Table : Compte                                               */
/*==============================================================*/
create table Compte (
id_compte            SERIAL                 not null,
id_user              INT4                 not null,
mail                 VARCHAR(254)         null,
pwd                  VARCHAR(254)         null,
constraint PK_COMPTE primary key (id_compte)
);

/*==============================================================*/
/* Index : COMPTE_PK                                            */
/*==============================================================*/
create unique index COMPTE_PK on Compte (
id_compte
);

/*==============================================================*/
/* Index : ASSOCIATION_6_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_6_FK on Compte (
id_user
);

/*==============================================================*/
/* Table : Pannier                                              */
/*==============================================================*/
create table Pannier (
id_pannier           SERIAL                 not null,
id_cmd               INT4                 not null,
constraint PK_PANNIER primary key (id_pannier)
);

/*==============================================================*/
/* Index : PANNIER_PK                                           */
/*==============================================================*/
create unique index PANNIER_PK on Pannier (
id_pannier
);

/*==============================================================*/
/* Index : ASSOCIATION_4_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_4_FK on Pannier (
id_cmd
);

/*==============================================================*/
/* Table : Produit                                              */
/*==============================================================*/
create table Produit (
id_produit           SERIAL                 not null,
id_cat               INT4                 not null,
id_annonce           INT4                 not null,
nom_p                VARCHAR(254)         null,
categorie            VARCHAR(254)         null,
prix                 FLOAT8               null,
date_mise_vente      DATE                 null,
description_p        VARCHAR(254)         null,
image                VARCHAR(254)         null,
constraint PK_PRODUIT primary key (id_produit)
);

/*==============================================================*/
/* Index : PRODUIT_PK                                           */
/*==============================================================*/
create unique index PRODUIT_PK on Produit (
id_produit
);

/*==============================================================*/
/* Index : ASSOCIATION_7_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_7_FK on Produit (
id_cat
);

/*==============================================================*/
/* Index : ASSOCIATION_9_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_9_FK on Produit (
id_annonce
);

/*==============================================================*/
/* Table : "User"                                               */
/*==============================================================*/
create table "User" (
id_user              SERIAL                  not null,
nom                  VARCHAR(254)         null,
prenom               VARCHAR(254)         null,
telephone            VARCHAR(254)         null,
adresse              VARCHAR(254)         null,
image_p              VARCHAR(254)         null,
constraint PK_USER primary key (id_user)
);

/*==============================================================*/
/* Index : USER_PK                                              */
/*==============================================================*/
create unique index USER_PK on "User" (
id_user
);

alter table Annonce
   add constraint FK_ANNONCE_ASSOCIATI_USER foreign key (id_user)
      references "User" (id_user)
      on delete restrict on update restrict;

alter table pannier_produit
   add constraint FK_ASSOCIAT_ASSOCIATI_PANNIER foreign key (id_pannier)
      references Pannier (id_pannier)
      on delete restrict on update restrict;

alter table pannier_produit
   add constraint FK_ASSOCIAT_ASSOCIATI_PRODUIT foreign key (id_produit)
      references Produit (id_produit)
      on delete restrict on update restrict;

alter table Commande
   add constraint FK_COMMANDE_ASSOCIATI_USER foreign key (id_user)
      references "User" (id_user)
      on delete restrict on update restrict;

alter table Compte
   add constraint FK_COMPTE_ASSOCIATI_USER foreign key (id_user)
      references "User" (id_user)
      on delete restrict on update restrict;

alter table Pannier
   add constraint FK_PANNIER_ASSOCIATI_COMMANDE foreign key (id_cmd)
      references Commande (id_cmd)
      on delete restrict on update restrict;

alter table Produit
   add constraint FK_PRODUIT_ASSOCIATI_CATEGORI foreign key (id_cat)
      references Categorie (id_cat)
      on delete restrict on update restrict;

alter table Produit
   add constraint FK_PRODUIT_ASSOCIATI_ANNONCE foreign key (id_annonce)
      references Annonce (id_annonce)
      on delete restrict on update restrict;

