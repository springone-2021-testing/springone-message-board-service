alter table messages add column firstname varchar (255);
alter table messages add column lastname varchar (255);

update messages set firstname = SUBSTRING( username FROM 1 FOR POSITION( '_' IN username )-1),
                    lastname = SUBSTRING( username FROM POSITION( '_' IN username )+1 FOR LENGTH(username)-POSITION( '_' IN username ));

alter table messages drop column username;