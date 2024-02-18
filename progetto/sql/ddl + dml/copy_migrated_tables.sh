#!/bin/sh
# Workbench Table Data copy script
# Workbench Version: 8.0.32
# 
# Execute this to copy table data from a source RDBMS to MySQL.
# Edit the options below to customize it. You will need to provide passwords, at least.
# 
# Source DB: Mysql@127.0.0.1:3306 (MySQL)
# Target DB: Mysql@127.0.0.1:3306


# Source and target DB passwords
arg_source_password=
arg_target_password=
arg_source_ssh_password=
arg_target_ssh_password=

if [ -z "$arg_source_password" ] && [ -z "$arg_target_password" ] && [ -z "$arg_source_ssh_password" ] && [ -z "$arg_target_ssh_password" ] ; then
    echo WARNING: All source and target passwords are empty. You should edit this file to set them.
fi
arg_worker_count=2
# Uncomment the following options according to your needs

# Whether target tables should be truncated before copy
# arg_truncate_target=--truncate-target
# Enable debugging output
# arg_debug_output=--log-level=debug3

/snap/mysql-workbench-community/12/usr/bin/wbcopytables \
 --mysql-source="nonnt66@127.0.0.1:3306" \
 --source-rdbms-type=Mysql \
 --target="nonnt66@127.0.0.1:3306" \
 --source-password="$arg_source_password" \
 --target-password="$arg_target_password" \
 --source-ssh-port="22" \
 --source-ssh-host="" \
 --source-ssh-user="" \
 --target-ssh-port="22" \
 --target-ssh-host="" \
 --target-ssh-user="" \
 --source-ssh-password="$arg_source_ssh_password" \
 --target-ssh-password="$arg_target_ssh_password" \
 --thread-count=$arg_worker_count \
 $arg_truncate_target \
 $arg_debug_output \
 --table '`dbBike`' '`Amministratore`' '`dbBikePort`' '`Amministratore`' '`email_admin`' '`email_admin`' '`email_admin`, `password_admin`, `nome_admin`' --table '`dbBike`' '`Bicicletta`' '`dbBikePort`' '`Bicicletta`' '`id_bici`' '`id_bici`' '`id_bici`, `categoria_bici`, `prezzo_bici`, `id_parcheggio`, `disponibile`, `km_effettuati`' --table '`dbBike`' '`Cliente`' '`dbBikePort`' '`Cliente`' '`email_cliente`' '`email_cliente`' '`email_cliente`, `nome_cliente`, `cognome_cliente`, `password_cliente`' --table '`dbBike`' '`Equipaggiamento`' '`dbBikePort`' '`Equipaggiamento`' '`id_equipaggiamento`' '`id_equipaggiamento`' '`id_equipaggiamento`, `nome_equipaggiamento`, `prezzo_equipaggiamento`' --table '`dbBike`' '`Paga`' '`dbBikePort`' '`Paga`' '-' '-' '`id_bici`, `email_cliente`, `km_effettuati`, `metodo_pagamento`, `importo`' --table '`dbBike`' '`Parcheggio`' '`dbBikePort`' '`Parcheggio`' '`id_parcheggio`' '`id_parcheggio`' '`id_parcheggio`, `luogo_parcheggio`, `numero_posti_parcheggio`' --table '`dbBike`' '`Possiede`' '`dbBikePort`' '`Possiede`' '`id_bici`,`id_equipaggiamento`' '`id_bici`,`id_equipaggiamento`' '`id_bici`, `id_equipaggiamento`' --table '`dbBike`' '`Prenota`' '`dbBikePort`' '`Prenota`' '`email_cliente`,`id_bici`' '`email_cliente`,`id_bici`' '`email_cliente`, `id_bici`, `ora_inizio_prenota`, `ora_fine_prenota`' --table '`dbBike`' '`Tariffa`' '`dbBikePort`' '`Tariffa`' '`nome_tariffa`' '`nome_tariffa`' '`nome_tariffa`, `orario_inizio_tariffa`, `orario_fine_tariffa`, `prezzo_tariffa`, `categoria_bici`'

