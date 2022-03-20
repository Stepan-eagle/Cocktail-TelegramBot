-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS cocktails;

-- Create tg_user table
CREATE TABLE cocktails
(id INTEGER not NULL AUTO_INCREMENT,
 name VARCHAR(200),
 ingr VARCHAR(2000),
 pick VARCHAR(2000),
 technique BLOB,
 comps VARCHAR(2000),
 PRIMARY KEY ( id ));

--utf
--ALTER TABLE cocktails CONVERT TO CHARACTER SET utf8;